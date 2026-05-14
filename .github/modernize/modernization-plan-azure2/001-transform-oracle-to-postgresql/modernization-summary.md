# Modernization Summary - 001-transform-oracle-to-postgresql

## Completed Migration Scope
- Replaced Oracle JDBC dependency (`ojdbc8`) with PostgreSQL JDBC (`org.postgresql:postgresql:42.7.11`).
- Added `com.azure:azure-identity-extensions:1.2.2` for Azure PostgreSQL JDBC authentication plugin support.
- Updated datasource and Hibernate configuration from Oracle to PostgreSQL in `application.properties` and `application-docker.properties`.
- Converted default datasource URL to PostgreSQL format and added managed identity URL parameters plus service principal example comments.
- Migrated entity mapping in `Photo`:
  - Removed Oracle-specific column definitions (`NUMBER(19,0)`, `TIMESTAMP DEFAULT SYSTIMESTAMP`).
  - Mapped photo binary data to PostgreSQL-compatible `bytea`.
- Converted Oracle-specific SQL in `PhotoRepository` to PostgreSQL-compatible SQL:
  - `ROWNUM` pagination -> `LIMIT/OFFSET`
  - `NVL` -> `COALESCE`
  - Standardized SQL identifiers to lowercase while keeping SQL keywords uppercase.
- Updated code comments/logs and Docker Compose setup from Oracle to PostgreSQL.
- Removed obsolete Oracle initialization scripts.

## Verification
- Consistency validation executed for this task; no Critical or Major issues reported.
- Build and unit tests passed:
  - `mvn -B clean test`

## Outcome
The application's database layer is migrated from Oracle Database to Azure Database for PostgreSQL compatibility, including dependency, configuration, schema mapping, and query compatibility updates.
