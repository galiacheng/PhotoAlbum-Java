# Modernization Plan: Migrate from Oracle Database to Azure PostgreSQL

**Project**: Photo Album Application  

---

## Technical Framework

- **Language**: Java 8
- **Framework**: Spring Boot 2.7.18
- **Build Tool**: Maven
- **Database**: Oracle Database 21c Express Edition
- **Key Dependencies**: Spring Data JPA, Hibernate ORM, Oracle JDBC Driver (ojdbc8)

---

## Overview

This migration moves the Photo Album application from Oracle Database to Azure Database for PostgreSQL. The application currently stores photo metadata and BLOB data in an Oracle 21c Express Edition database using Spring Data JPA with Hibernate. The new architecture will:

- Replace Oracle Database with Azure Database for PostgreSQL for improved cloud-native scalability and managed service benefits
- Update database drivers from Oracle JDBC (ojdbc8) to PostgreSQL JDBC driver
- Migrate Hibernate dialect from OracleDialect to PostgreSQLDialect for optimal PostgreSQL compatibility
- Leverage Azure's managed PostgreSQL service for automatic backups, high availability, and reduced operational overhead

The migration follows a systematic approach to update database dependencies, connection configurations, and dialect settings while preserving all application functionality.

---

## Migration Impact Summary

| Application | Original Service | New Azure Service | Authentication | Comments |
|-------------|------------------|-------------------|----------------|----------|
| PhotoAlbum  | Oracle 21c XE    | Azure PostgreSQL  | Managed Identity | Migrate database from Oracle to Azure PostgreSQL |

---

## Code

### Task 1: Upgrade Spring Boot to 3.x

**Description**: Upgrade the application from Spring Boot 2.7.18 to Spring Boot 3.x to support modern Java features and Azure integration. This upgrade includes JDK 17, Spring Framework 6.x, and migration from JavaEE (javax.*) to Jakarta EE (jakarta.*).

**Requirements**:
  User requested to migrate to Azure PostgreSQL; upgrading Spring Boot is a prerequisite for optimal Azure SDK integration and long-term support.

**Environment Configuration**:
  Not specified by user

**App Scope**:
  - Full application (src/main, src/test, pom.xml)

**Skill**: 
  - Skill Name: migration-spring-boot-upgrade
  - Skill Source: project

**Success Criteria**:
- Pass Build: Yes - Project must compile successfully after Spring Boot 3.x upgrade
- Generate New Unit Tests (Mock-based): No
- Generate New Integration Tests: No
- Pass Unit Tests: Yes - All existing tests must pass with mocked dependencies
- Pass New Integration Tests: No
- Pass Security Compliance: No

---

### Task 2: Migrate Oracle Database to Azure PostgreSQL

**Description**: Migrate the Photo Album application's database from Oracle Database 21c Express Edition to Azure Database for PostgreSQL. This includes updating JDBC drivers, connection strings, Hibernate dialect configuration, and ensuring data type compatibility for photo BLOB storage.

**Requirements**:
  Migrate from Oracle Database to Azure PostgreSQL as requested by user

**Environment Configuration**:
  Not specified by user - will need PostgreSQL connection details (endpoint, database name, credentials or managed identity configuration)

**App Scope**:
  - Full application (src/main, src/test, pom.xml, application.properties)

**Skill**: 
  - Skill Name: migration-mi-postgresql-azure-sdk-public-cloud
  - Skill Source: project

**Success Criteria**:
- Pass Build: Yes - Project must compile successfully after PostgreSQL migration
- Generate New Unit Tests (Mock-based): No
- Generate New Integration Tests: Yes - Create integration tests for PostgreSQL connectivity with Azure managed identity
- Pass Unit Tests: Yes - All tests must pass with mocked Azure PostgreSQL connections
- Pass New Integration Tests: No - Integration tests will be created but require Azure PostgreSQL resource to run
- Pass Security Compliance: No

---

## Clarifications

The following items were not explicitly requested but may be needed for a complete implementation:

1. **Azure PostgreSQL Resource Details**
   - **Why needed**: The migration skill requires connection details to configure the application for Azure Database for PostgreSQL
   - **Options**: 
     - Use existing Azure PostgreSQL instance (provide endpoint, database name)
     - Create new Azure PostgreSQL Flexible Server instance
   - **Recommendation**: If not provided, the plan will configure the application with placeholder values that need to be updated before deployment

2. **Authentication Method Confirmation**
   - **Why needed**: The plan assumes Managed Identity for secure, credential-free authentication
   - **Options**:
     - Managed Identity (recommended for production)
     - Connection string with username/password (simpler for development)
   - **Recommendation**: Use Managed Identity as default; can be switched to username/password if needed

3. **Data Migration Strategy**
   - **Why needed**: This plan covers code migration but not data migration from existing Oracle database
   - **Options**:
     - Manual data export/import using database tools
     - Use Azure Database Migration Service
     - Start with empty database (suitable for development/testing)
   - **Recommendation**: If existing Oracle data needs to be preserved, plan a separate data migration task using appropriate Azure tooling

4. **BLOB Storage Consideration**
   - **Why needed**: Application stores photos as BLOBs in database; PostgreSQL handles large objects differently than Oracle
   - **Options**:
     - Continue using database BYTEA storage (PostgreSQL equivalent of BLOB)
     - Migrate to Azure Blob Storage for better scalability (requires additional code changes)
   - **Recommendation**: Keep database storage for this migration (BYTEA in PostgreSQL). Consider Azure Blob Storage as a future enhancement for better scalability.

5. **Spring Boot 3.x Upgrade Timing**
   - **Why needed**: Current Java 8 is end-of-life; Spring Boot 3.x requires Java 17 minimum
   - **Options**:
     - Upgrade Java and Spring Boot first, then migrate database (recommended)
     - Attempt PostgreSQL migration on Spring Boot 2.7.18 (limited Azure SDK support)
   - **Recommendation**: Perform Spring Boot 3.x upgrade first (includes Java 17) to ensure best compatibility with Azure SDK for managed identity
