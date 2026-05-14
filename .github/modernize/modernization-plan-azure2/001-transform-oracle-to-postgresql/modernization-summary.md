# Modernization Summary: 001-transform-oracle-to-postgresql

## Task
Migrate the application's database layer from Oracle Database to Azure Database for PostgreSQL.

## Changes Made

### `pom.xml`
- Removed Oracle JDBC driver (`com.oracle.database.jdbc:ojdbc8`)
- Added PostgreSQL JDBC driver (`org.postgresql:postgresql`)
- Added `com.azure:azure-identity-extensions:1.2.2` for managed identity / passwordless authentication
- Updated project description to reference PostgreSQL

### `src/main/resources/application.properties`
- Replaced Oracle JDBC URL with PostgreSQL JDBC URL using managed identity parameters (`authenticationPluginClassName`, `azure.managedIdentityEnabled`, `azure.clientId`, `sslmode=require`)
- Commented out `username` and `password` (managed identity is used instead)
- Updated `spring.datasource.driver-class-name` to `org.postgresql.Driver`
- Updated `spring.jpa.database-platform` to `org.hibernate.dialect.PostgreSQLDialect`
- Added service principal authentication example URL as a comment

### `src/main/resources/application-docker.properties`
- Same changes as `application.properties` (PostgreSQL JDBC URL with managed identity, driver, dialect)
- Removed duplicate property entries present in the original

### `src/main/java/com/photoalbum/model/Photo.java`
- Removed `@Lob` annotation from `photoData` field; replaced with `@Column(columnDefinition = "bytea")` for correct PostgreSQL binary storage
- Removed Oracle-specific `columnDefinition = "NUMBER(19,0)"` from `fileSize` field
- Removed Oracle-specific `columnDefinition = "TIMESTAMP DEFAULT SYSTIMESTAMP"` from `uploadedAt` field
- Updated field comment from "Oracle database" to "database"

### `src/main/java/com/photoalbum/repository/PhotoRepository.java`
- Converted all native SQL queries from Oracle to PostgreSQL syntax:
  - `ROWNUM <= 10` → `LIMIT 10`
  - Oracle double-nested ROWNUM pagination → `ROW_NUMBER() OVER (ORDER BY ...)` window function with range filter
  - `NVL(file_path, 'default_path')` → `COALESCE(file_path, 'default_path')`
  - `TO_CHAR` usage preserved (PostgreSQL-compatible)
  - `RANK() OVER` and `SUM() OVER` window functions preserved (PostgreSQL-compatible)
- Converted all SQL identifiers and table names from UPPERCASE to lowercase per PostgreSQL convention
- Updated query comment ("Oracle ROWNUM", "Oracle specific", "Oracle analytical functions") to be database-agnostic

### `src/main/java/com/photoalbum/service/impl/PhotoServiceImpl.java`
- Updated log messages referencing "Oracle database" to reference "PostgreSQL database"
- Updated comment referencing "Oracle database" BLOB storage to "database" bytea storage

### `docker-compose.yml`
- Replaced Oracle Database service (`gvenzl/oracle-free`) with PostgreSQL service (`postgres:15`)
- Replaced Oracle-specific environment variables and volumes with PostgreSQL equivalents
- Updated application service environment to use `PGHOST`/`PGPORT` and added comments for remaining required environment variables (`PGDATABASE`, `MANAGED_IDENTITY_NAME`, `MANAGED_IDENTITY_CLIENT_ID`)

## Verification
- ✅ Build: `mvn clean test` — **BUILD SUCCESS**
- ✅ Tests: 1 test run, 0 failures, 0 errors
- ✅ Consistency check: 0 Critical, 0 Major issues; 1 Minor issue (docker-compose env var documentation) addressed with comments
