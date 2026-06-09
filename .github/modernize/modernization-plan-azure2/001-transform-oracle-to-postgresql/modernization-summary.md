# Modernization Summary: 001-transform-oracle-to-postgresql

## Task Description
Migrate the application's database layer from Oracle Database to Azure Database for PostgreSQL.

## Changes Made

### pom.xml
- Replaced Oracle JDBC driver (`ojdbc8`) with PostgreSQL JDBC driver (`org.postgresql:postgresql`)
- Added `azure-identity-extensions` (v1.2.2) dependency for passwordless managed identity authentication
- Updated project description from Oracle to PostgreSQL

### src/main/resources/application.properties
- Replaced Oracle datasource URL with PostgreSQL JDBC URL using Azure Managed Identity
- Commented out `username` and `password` (managed identity handles authentication)
- Changed driver class from `oracle.jdbc.OracleDriver` to `org.postgresql.Driver`
- Changed JPA dialect from `OracleDialect` to `PostgreSQLDialect`
- Added environment variable references (`PGHOST`, `PGPORT`, `PGDATABASE`, `MANAGED_IDENTITY_NAME`, `MANAGED_IDENTITY_CLIENT_ID`)
- Added Azure sovereign cloud documentation comments
- Added service principal example URL as a comment

### src/main/resources/application-docker.properties
- Applied same datasource and dialect changes as `application.properties`
- Removed duplicate configuration entries (pre-existing technical debt)

### src/main/java/com/photoalbum/model/Photo.java
- Removed `@Lob` annotation from `photoData` field (PostgreSQL uses `bytea` natively for `byte[]`)
- Removed Oracle-specific `columnDefinition = "NUMBER(19,0)"` from `fileSize` column
- Removed Oracle-specific `columnDefinition = "TIMESTAMP DEFAULT SYSTIMESTAMP"` from `uploadedAt` column
- Updated comment to reference PostgreSQL bytea instead of Oracle BLOB

### src/main/java/com/photoalbum/repository/PhotoRepository.java
- Converted all native SQL queries from Oracle to PostgreSQL syntax:
  - Used lowercase identifiers for all table and column names
  - Replaced Oracle `ROWNUM`-based pagination with `LIMIT` clause
  - Replaced Oracle `NVL()` with PostgreSQL `COALESCE()`
  - Replaced Oracle double-nested `ROWNUM` pagination with `ROW_NUMBER() OVER (...)` window function
  - Retained `TO_CHAR()` (supported by both Oracle and PostgreSQL)
  - Retained analytical window functions (`RANK() OVER`, `SUM() OVER`) which are standard SQL
- Updated query comments to remove Oracle-specific references

### src/main/java/com/photoalbum/service/impl/PhotoServiceImpl.java
- Updated log messages to reference PostgreSQL instead of Oracle
- Updated code comment from "BLOB storage" to "bytea storage"

### docker-compose.yml
- Replaced Oracle Database service (`gvenzl/oracle-free`) with PostgreSQL 15 service
- Updated port mapping from `1521` to `5432`
- Updated health check command from Oracle-specific to `pg_isready`
- Updated environment variables for PostgreSQL connection
- Updated volume name from `oracle_data` to `postgres_data`

## Build & Test Results
- Build: ✅ SUCCESS
- Unit Tests: ✅ 1/1 passed (`contextLoads`)

## Consistency Check
All critical and major issues resolved. No Oracle-specific references remain in source code, configuration, or build files.
