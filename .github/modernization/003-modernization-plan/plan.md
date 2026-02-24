# Modernization Plan: Migrate Oracle Database to Azure PostgreSQL

**Project**: Photo Album Java Application

---

## Technical Framework

- **Language**: Java 8
- **Framework**: Spring Boot 2.7.18
- **Build Tool**: Maven 3.x
- **Database**: Oracle Database 21c Express Edition
- **Key Dependencies**: Spring Data JPA, Hibernate, Oracle JDBC Driver (ojdbc8)

---

## Overview

This migration moves the Photo Album application from Oracle Database to Azure Database for PostgreSQL. The application currently uses Oracle Database 21c Express Edition with BLOB storage for photo data. The new architecture will:

- Replace Oracle Database with Azure Database for PostgreSQL for scalable, managed database service in Azure
- Update database connectivity to use PostgreSQL JDBC driver and dialect
- Ensure compatibility with PostgreSQL data types, especially for BLOB storage and column definitions
- Maintain all existing functionality including photo upload, gallery view, and metadata management

The migration follows a phased approach starting with Java/Spring Boot upgrade, followed by database driver and configuration updates.

---

## Migration Impact Summary

| Application | Original Service | New Azure Service | Authentication | Comments |
|-------------|------------------|-------------------|----------------|----------|
| Photo Album | Oracle DB 21c XE | Azure PostgreSQL  | Default (MI)   | Migrate to Azure PostgreSQL |

---

## Code

### Task 1: Upgrade Spring Boot to 3.x

**Description**: Upgrade the application from Spring Boot 2.7.18 to Spring Boot 3.x to leverage the latest features, security updates, and ensure compatibility with modern Azure services. This upgrade includes JDK 17, Spring Framework 6.x, and migration from JavaEE (javax.*) to Jakarta EE (jakarta.*).

**Requirements**:
- Upgrade Spring Boot from 2.7.18 to latest 3.x version
- Upgrade Java from version 8 to version 17
- Migrate JavaEE imports (javax.*) to Jakarta EE (jakarta.*)
- Update all related dependencies for compatibility

**Environment Configuration**:
(No specific environment configuration required)

**App Scope**:
- Root folder: C:\Users\haiche\Documents\AppMod\PhotoAlbum-Java

**Skill**: 
- Skill Name: migration-spring-boot-upgrade
- Skill Source: remote

**Success Criteria**:
- Pass Build: Yes - Project must compile successfully after Spring Boot 3.x upgrade
- Generate New Unit Tests (Mock-based): No
- Generate New Integration Tests: No
- Pass Unit Tests: Yes - All existing tests must pass
- Pass New Integration Tests: No
- Pass Security Compliance: No

---

### Task 2: Migrate from Oracle Database to Azure Database for PostgreSQL

**Description**: Migrate the application's data persistence layer from Oracle Database to Azure Database for PostgreSQL. This includes updating database drivers, connection configuration, SQL dialects, and ensuring compatibility of data types (especially BLOB storage for photos).

**Requirements**:
- Replace Oracle JDBC driver with PostgreSQL JDBC driver
- Update database connection configuration for Azure PostgreSQL
- Change Hibernate dialect from OracleDialect to PostgreSQLDialect
- Ensure BLOB storage and column definitions are compatible with PostgreSQL
- Update any Oracle-specific SQL or database configurations

**Environment Configuration**:
(Azure PostgreSQL connection details to be provided during execution)

**App Scope**:
- Root folder: C:\Users\haiche\Documents\AppMod\PhotoAlbum-Java

**Skill**: 
- Skill Name: migration-oracle-to-postgresql
- Skill Source: remote

**Success Criteria**:
- Pass Build: Yes - Project must compile successfully after PostgreSQL migration
- Generate New Unit Tests (Mock-based): No
- Generate New Integration Tests: Yes - Create integration tests for PostgreSQL connectivity:
  - If Azure PostgreSQL resources are provided: Tests validate actual Azure PostgreSQL service connectivity
  - If no Azure resources provided: Tests validate integration logic using test containers or H2 compatibility mode
- Pass Unit Tests: Yes - All tests must pass with PostgreSQL configuration
- Pass New Integration Tests: Yes - Integration tests must validate database operations with PostgreSQL
- Pass Security Compliance: No

---

## Clarifications

The following items were not explicitly requested but may be needed for a complete implementation:

1. **Azure PostgreSQL Resource Details**
   - **Why needed**: To configure the application connection to Azure Database for PostgreSQL, we need server endpoint, database name, authentication method, and credentials
   - **Options**: 
     - Use existing Azure PostgreSQL server (provide connection details)
     - Create new Azure PostgreSQL server during migration
     - Use Managed Identity for authentication (recommended for production)
     - Use connection string with username/password
   - **Recommendation**: If not provided, the migration will configure the application for Azure PostgreSQL with placeholder connection settings that can be updated via environment variables or Azure App Configuration

2. **Data Migration Strategy**
   - **Why needed**: The plan addresses code migration, but existing photo data in Oracle Database needs to be migrated to PostgreSQL
   - **Options**:
     - Manual export/import of data using database tools
     - Use Azure Database Migration Service
     - Start with empty database (for development/testing scenarios)
   - **Recommendation**: Start with empty database for initial testing. If data migration is required, use Azure Database Migration Service or provide guidance on export/import process

3. **Test Database Configuration**
   - **Why needed**: Integration tests currently use H2 in-memory database; need to verify test strategy with PostgreSQL
   - **Options**:
     - Continue using H2 for unit tests (may have compatibility issues)
     - Use Testcontainers with PostgreSQL for integration tests
     - Use separate Azure PostgreSQL test database
   - **Recommendation**: Use Testcontainers with PostgreSQL for integration tests to ensure test environment matches production

4. **Oracle-Specific Features**
   - **Why needed**: Application may use Oracle-specific SQL functions, stored procedures, or features that need PostgreSQL equivalents
   - **Options**:
     - Review and replace Oracle-specific SQL with PostgreSQL equivalents
     - Identify and refactor any Oracle-specific database features
   - **Recommendation**: Code review will identify any Oracle-specific features during migration and provide PostgreSQL-compatible alternatives
