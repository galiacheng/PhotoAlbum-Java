# Modernization Summary: 002-transform-oracle-to-postgresql

## Task
Migrate database layer from Oracle Database to PostgreSQL.

## Status
✅ Completed — build passes, all unit tests pass.

## Changes Made

### 1. `pom.xml`
- **Removed** Oracle JDBC driver: `com.oracle.database.jdbc:ojdbc11`
- **Added** PostgreSQL JDBC driver: `org.postgresql:postgresql` (version managed by Spring Boot BOM)
- **Added** Azure Identity Extensions: `com.azure:azure-identity-extensions:1.2.2` for passwordless managed identity authentication

### 2. `src/main/resources/application.properties`
- Replaced Oracle JDBC URL (`jdbc:oracle:thin:@oracle-db:1521/FREEPDB1`) with PostgreSQL URL using Azure Managed Identity passwordless authentication
- Replaced `oracle.jdbc.OracleDriver` with `org.postgresql.Driver`
- Replaced `org.hibernate.dialect.OracleDialect` with `org.hibernate.dialect.PostgreSQLDialect`
- Commented out `username`/`password` properties (managed identity authenticates without credentials)
- Added commented example URL for Service Principal authentication

### 3. `src/main/resources/application-docker.properties`
- Replaced Oracle JDBC URL with `jdbc:postgresql://${PGHOST:postgres-db}:${PGPORT:5432}/${PGDATABASE:photoalbum}`
- Replaced Oracle driver and dialect with PostgreSQL equivalents
- Set `spring.datasource.username`/`password` to `${POSTGRES_USERNAME}`/`${POSTGRES_PASSWORD}` env vars with defaults

### 4. `src/test/resources/application-test.properties`
- Updated H2 URL from `jdbc:h2:mem:testdb` to `jdbc:h2:mem:testdb;MODE=PostgreSQL;DB_CLOSE_DELAY=-1` for PostgreSQL-compatible behaviour in tests (supports `bytea` type)

### 5. `src/main/java/com/photoalbum/model/Photo.java`
- **Removed** `@Lob` annotation from `photoData` field — Oracle BLOB mapped via `@Lob` is incompatible with PostgreSQL `bytea`
- **Added** `columnDefinition = "bytea"` to `photo_data` `@Column` for correct PostgreSQL binary column mapping
- **Removed** Oracle-specific `columnDefinition = "NUMBER(19,0)"` from `file_size` column
- **Replaced** `columnDefinition = "TIMESTAMP DEFAULT SYSTIMESTAMP"` with `TIMESTAMP DEFAULT CURRENT_TIMESTAMP` for `uploaded_at` column

### 6. `src/main/java/com/photoalbum/repository/PhotoRepository.java`
All five native SQL queries rewritten for PostgreSQL:

| Method | Change |
|---|---|
| `findAllOrderByUploadedAtDesc` | Lowercased all table/column identifiers |
| `findPhotosUploadedBefore` | Replaced Oracle `ROWNUM`-based double-subquery with `LIMIT 10` |
| `findPhotosUploadedAfter` | Replaced `NVL()` with `COALESCE()`, lowercased identifiers |
| `findPhotosByUploadMonth` | Replaced `TO_CHAR(col, 'YYYY'/'MM')` with `EXTRACT(YEAR/MONTH FROM col)` |
| `findPhotosWithPagination` | Replaced Oracle `ROWNUM` double-subquery with `ROW_NUMBER() OVER (ORDER BY uploaded_at DESC)` window function |
| `findPhotosWithStatistics` | Lowercased all identifiers (`RANK()` and `SUM() OVER` are standard SQL, valid in PostgreSQL) |

### 7. `docker-compose.yml`
- Replaced `oracle-db` service (`gvenzl/oracle-free:latest`) with `postgres-db` service (`postgres:16`)
- Updated health check to `pg_isready`
- Replaced Oracle volume `oracle_data` with `postgres_data`
- Updated app service environment variables to use `PGHOST`, `PGPORT`, `PGDATABASE`, `POSTGRES_USERNAME`, `POSTGRES_PASSWORD`
- Removed Oracle-init volume mount (`./oracle-init`)

## Migration Rules Applied
- Build file check item 1: Oracle JDBC driver → PostgreSQL driver
- Build file check item 5: Added azure-identity-extensions for passwordless connection
- Property check item 1: Oracle JDBC URL → PostgreSQL JDBC URL
- Property check item 2: `oracle.jdbc.OracleDriver` → `org.postgresql.Driver`
- Property check item 3: `OracleDialect` → `PostgreSQLDialect`
- Property check item 5: Credentials replaced with env var placeholders
- Property check item 8: Passwordless managed identity connection in main config
- Property check item 9: Service principal authentication example added
- Java check item 1: Table/column names lowercased in SQL strings
- Java check item 4: `TO_CHAR(date, 'YYYY'/'MM')` → `EXTRACT(YEAR/MONTH FROM date)`
- Java check item 17: `ROWNUM` pagination → `LIMIT`/`ROW_NUMBER() OVER`
- Java check item 26: H2 changed from no-mode to `MODE=PostgreSQL`
- Java check item 9999: Oracle BLOB (`@Lob` + `bytea`) migration, `NUMBER` type removal, `SYSTIMESTAMP` → `CURRENT_TIMESTAMP`, `NVL` → `COALESCE`

## Verification
- ✅ `mvn package` — BUILD SUCCESS
- ✅ `mvn test` — All tests PASSED (Spring context loads with H2 in PostgreSQL mode)
