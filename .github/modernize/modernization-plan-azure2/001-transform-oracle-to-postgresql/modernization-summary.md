# Modernization Summary - 001-transform-oracle-to-postgresql

## Completed Migration Changes
- Replaced Oracle JDBC dependency (`ojdbc8`) with PostgreSQL JDBC (`org.postgresql:postgresql`) in `pom.xml`.
- Added `com.azure:azure-identity-extensions:1.2.2` for PostgreSQL JDBC managed identity authentication.
- Updated datasource and JPA dialect configuration from Oracle to PostgreSQL in:
  - `src/main/resources/application.properties`
  - `src/main/resources/application-docker.properties`
- Updated datasource URL patterns to PostgreSQL Azure-compatible JDBC URLs with managed identity defaults and service principal example comments.
- Removed Oracle-specific JPA column definitions (`NUMBER(19,0)`, `TIMESTAMP DEFAULT SYSTIMESTAMP`) from `Photo` entity.
- Migrated Oracle-specific SQL in `PhotoRepository` to PostgreSQL-compatible SQL:
  - `ROWNUM` pagination -> `LIMIT/OFFSET`
  - `NVL` -> `COALESCE`
  - `TO_CHAR` year/month filters -> `EXTRACT` + `CAST`
  - standardized SQL identifier casing (lowercase identifiers, uppercase SQL keywords).
- Updated Oracle-specific code comments/log messages to PostgreSQL equivalents in service/controller/model classes.
- Migrated container support from Oracle to PostgreSQL:
  - `docker-compose.yml` now uses `postgres:16`
  - added `postgres-init/01-init.sql`
  - removed obsolete `oracle-init/*` scripts
  - updated `.gitignore` from `/oracle-data` to `/postgres-data`.

## Validation
- Build and tests executed successfully using Maven (`mvn test`).
- Unit tests passed with no failures.
