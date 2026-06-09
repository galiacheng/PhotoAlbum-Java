# Task 002: Migrate to Azure Managed Identity for PostgreSQL

## Summary

Migrated the PhotoAlbum Spring Boot application from `azure-identity-extensions` JDBC URL plugin approach to the Spring Cloud Azure `spring-cloud-azure-starter-jdbc-postgresql` starter for passwordless Azure Database for PostgreSQL authentication.

## Changes

### `pom.xml`
- Replaced `com.azure:azure-identity-extensions:1.2.2` with `com.azure.spring:spring-cloud-azure-starter-jdbc-postgresql`
- Added `com.azure.spring:spring-cloud-azure-dependencies:4.20.0` BOM in `dependencyManagement` (compatible with Spring Boot 2.7.x)

### `src/main/resources/application.properties`
- Replaced JDBC URL with embedded auth plugin parameters with a clean JDBC URL (`sslmode=require`)
- Set `spring.datasource.username` to `${MANAGED_IDENTITY_NAME}`
- Added `spring.datasource.azure.passwordless-enabled=true`
- Added `spring.cloud.azure.credential.client-id=${MANAGED_IDENTITY_CLIENT_ID}`
- Added `spring.cloud.azure.credential.managed-identity-enabled=true`
- Removed commented-out `spring.datasource.password`
- Added comments for service principal auth and Azure sovereign cloud configuration

### `src/main/resources/application-docker.properties`
- Same changes as `application.properties` for the Docker profile

## Verification
- Build: ✅ Passes (`mvn clean test`)
- Unit Tests: ✅ All pass (H2 in-memory DB used for tests)
- Consistency Check: ✅ No issues found
