# Modernization Plan: Migrate to Azure PostgreSQL

**Project**: Photo Album Application

---

## Technical Framework

- **Language**: Java 8
- **Framework**: Spring Boot 2.7.18
- **Build Tool**: Maven 3.x
- **Database**: Oracle Database 21c Express Edition
- **Key Dependencies**: Spring Data JPA, Hibernate, Oracle JDBC Driver (ojdbc8), Thymeleaf

---

## Overview

This migration will transition the Photo Album application from Oracle Database to Azure Database for PostgreSQL. The application currently uses Oracle Database 21c Express Edition with BLOB storage for photo data and Hibernate ORM for data access. The new architecture will:

- Replace Oracle Database with Azure Database for PostgreSQL for improved scalability and cloud-native capabilities
- Update database connectivity to use Azure PostgreSQL with managed identity for secure, credential-free authentication
- Migrate database schema and data types from Oracle-specific formats to PostgreSQL-compatible formats
- Upgrade Java and Spring Boot versions to ensure compatibility and leverage modern features

The migration follows a phased approach: first upgrading the application stack to current LTS versions, then migrating the database platform.

---

## Migration Impact Summary

| Application | Original Service | New Azure Service | Authentication | Comments |
|-------------|------------------|-------------------|----------------|----------|
| Photo Album | Oracle DB 21c XE | Azure PostgreSQL  | Managed Identity | Migrate to PostgreSQL |

---

## Code

### Task 1: Upgrade Spring Boot to 3.x

**Description**: Upgrade the application to Spring Boot 3.x to ensure compatibility with Azure SDKs and modern Java features.

**Requirements**:
- Upgrade Spring Boot from 2.7.18 to latest 3.x stable version
- This upgrade includes JDK 17, Spring Framework 6.x, and migration from JavaEE (javax.*) to Jakarta EE (jakarta.*)

**Environment Configuration**:
  (Not specified by user)

**App Scope**:
  - Root project folder: C:\Users\haiche\Documents\AppMod\PhotoAlbum-Java

**Skill**: 
  - Skill Name: migration-spring-boot-upgrade
  - Skill Source: project

**Success Criteria**:
- Pass Build: Yes - Project must compile successfully after migration
- Generate New Unit Tests (Mock-based): No
- Generate New Integration Tests: No
- Pass Unit Tests: Yes - All tests must pass
- Pass New Integration Tests: No
- Pass Security Compliance: No

---

### Task 2: Migrate from Oracle Database to Azure PostgreSQL

**Description**: Migrate the application database from Oracle Database to Azure Database for PostgreSQL with managed identity authentication for secure, credential-free access.

**Requirements**:
- Replace Oracle JDBC driver with PostgreSQL JDBC driver
- Update Hibernate dialect from OracleDialect to PostgreSQL dialect
- Migrate schema including BLOB storage for photos to PostgreSQL bytea type
- Configure Azure managed identity for database authentication

**Environment Configuration**:
  (To be provided for actual deployment - PostgreSQL endpoint, database name)

**App Scope**:
  - Root project folder: C:\Users\haiche\Documents\AppMod\PhotoAlbum-Java

**Skill**: 
  - Skill Name: migration-mi-postgresql-azure-sdk-public-cloud
  - Skill Source: project

**Success Criteria**:
- Pass Build: Yes - Project must compile successfully after migration
- Generate New Unit Tests (Mock-based): No
- Generate New Integration Tests: No
- Pass Unit Tests: Yes - All tests must pass; mock Azure PostgreSQL resources if not provided
- Pass New Integration Tests: No
- Pass Security Compliance: No

---

## Clarifications

The following items were not explicitly requested but may be needed for a complete implementation:

1. **Oracle to PostgreSQL Data Type Mapping**
   - **Why needed**: Oracle-specific data types (BLOB, NUMBER, VARCHAR2, SYSTIMESTAMP) need to be mapped to PostgreSQL equivalents (bytea, numeric/bigint, varchar, current_timestamp)
   - **Options**: 
     - Automatic mapping through Hibernate dialect changes
     - Manual schema adjustments if needed
   - **Recommendation**: Start with automatic Hibernate dialect mapping; adjust manually only if specific Oracle features are used

2. **Azure PostgreSQL Resource Configuration**
   - **Why needed**: To deploy and test against Azure PostgreSQL, resource details are required
   - **Options**: 
     - Use existing Azure PostgreSQL instance (provide endpoint, database name)
     - Create new Azure PostgreSQL Flexible Server instance
   - **Recommendation**: If not provided, the migration will use local PostgreSQL for development and testing, with configuration ready for Azure deployment

3. **Data Migration Strategy**
   - **Why needed**: Existing photo data in Oracle needs to be migrated to PostgreSQL
   - **Options**: 
     - Fresh start with empty database (suitable for development)
     - Data export/import using pg_dump or custom migration scripts
   - **Recommendation**: For this plan, assume a fresh start; create separate data migration plan if production data exists

4. **Integration Testing**
   - **Why needed**: To validate the database migration works correctly
   - **Options**: 
     - Run existing tests with H2 database (no Azure PostgreSQL connectivity)
     - Create integration tests with test containers using PostgreSQL
     - Test against actual Azure PostgreSQL instance
   - **Recommendation**: Use existing H2 tests for unit testing; optionally add PostgreSQL test containers for integration testing if requested
