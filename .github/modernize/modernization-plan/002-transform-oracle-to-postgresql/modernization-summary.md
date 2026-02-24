# Modernization Summary: 002-transform-oracle-to-postgresql

## Task Description
Migrated the PhotoAlbum application from Oracle Database to Azure Database for PostgreSQL with managed identity authentication for secure, credential-free database access.

## Changes Made

### 1. `pom.xml`
- Removed Oracle JDBC driver (`ojdbc8`)
- Added PostgreSQL JDBC driver (`org.postgresql:postgresql`)
- Added `com.azure:azure-identity-extensions:1.2.2` for managed identity authentication via `AzurePostgresqlAuthenticationPlugin`
- Updated project description to reflect PostgreSQL

### 2. `src/main/resources/application.properties`
- Replaced Oracle datasource (`jdbc:oracle:thin:...`) with Azure PostgreSQL datasource using managed identity
- Connection URL includes `authenticationPluginClassName=com.azure.identity.extensions.jdbc.postgresql.AzurePostgresqlAuthenticationPlugin` for passwordless authentication
- Username set to `${AZURE_MANAGED_IDENTITY_NAME}` (managed identity display name as database user)
- No password configured — managed identity token is used instead
- Updated JPA dialect from `OracleDialect` to `PostgreSQLDialect`

### 3. `src/main/resources/application-docker.properties`
- Replaced Oracle datasource with PostgreSQL datasource pointing to `postgres-db:5432`
- Updated JPA dialect to `PostgreSQLDialect`
- Removed duplicate properties

### 4. `src/main/java/com/photoalbum/model/Photo.java`
- Changed `columnDefinition = "NUMBER(19,0)"` → `"BIGINT"` for `fileSize` field (PostgreSQL type)
- Changed `columnDefinition = "TIMESTAMP DEFAULT SYSTIMESTAMP"` → `"TIMESTAMP DEFAULT CURRENT_TIMESTAMP"` for `uploadedAt` field
- Updated comment: BLOB → BYTEA (PostgreSQL stores binary data as `bytea`)

### 5. `src/main/java/com/photoalbum/repository/PhotoRepository.java`
- Replaced all Oracle-specific native SQL queries with PostgreSQL-compatible equivalents:
  - Lowercased table/column names (PostgreSQL folds unquoted identifiers to lowercase)
  - `ROWNUM <= 10` → `LIMIT 10`
  - Oracle nested-subquery ROWNUM pagination → `LIMIT ... OFFSET ...`
  - `NVL(...)` → `COALESCE(...)`
  - Removed Oracle-specific comments
- `RANK() OVER` and `SUM() OVER` window functions are standard SQL and work unchanged in PostgreSQL

### 6. `src/main/java/com/photoalbum/service/impl/PhotoServiceImpl.java`
- Updated log messages referencing "Oracle database" to "PostgreSQL database"
- Updated comment referencing "BLOB" to "BYTEA"

### 7. `docker-compose.yml`
- Replaced Oracle Database Free (`gvenzl/oracle-free`) service with PostgreSQL 16 (`postgres:16`)
- Updated container name, environment variables, ports (5432), and volume name
- Updated app environment to use PostgreSQL JDBC URL
- Removed Oracle-specific `oracle-init` volume mount

## Authentication Pattern
The production configuration uses **Azure Managed Identity** (passwordless):
- `AzurePostgresqlAuthenticationPlugin` from `azure-identity-extensions` obtains an OAuth2 token from Azure AD on behalf of the managed identity
- No database password is stored or transmitted
- Username must match the PostgreSQL database user mapped to the managed identity

## Required Environment Variables (Production/Azure)
| Variable | Description |
|---|---|
| `AZURE_POSTGRESQL_HOST` | Azure Database for PostgreSQL server hostname |
| `AZURE_POSTGRESQL_DATABASE` | Database name |
| `AZURE_MANAGED_IDENTITY_NAME` | Managed identity display name (database user) |

## Build & Test Results
- ✅ Build: Passed
- ✅ Unit Tests: Passed (H2 in-memory database used for tests)
- ✅ CodeQL Security: No alerts found
