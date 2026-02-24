# Modernization Plan: Migrate to Azure PostgreSQL

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

This migration aims to modernize the Photo Album application by migrating from Oracle Database to Azure Database for PostgreSQL. The application currently uses Oracle Database 21c Express Edition for storing photo metadata and BLOB data. The new architecture will:

- Leverage Azure Database for PostgreSQL as a fully managed, scalable, and cost-effective database service
- Utilize Azure's built-in high availability, automated backups, and security features
- Enable seamless integration with other Azure services
- Upgrade to modern Java (17) and Spring Boot 3.x for improved performance, security, and long-term support

The migration follows a phased approach: first upgrading the application framework to meet Azure compatibility standards, then migrating the database layer from Oracle to PostgreSQL.

---

## Migration Impact Summary

| Application | Original Service | New Azure Service | Authentication | Comments |
|-------------|------------------|-------------------|----------------|----------|
| Photo Album | Oracle Database  | Azure PostgreSQL  | Managed Identity (default) | Migrate database from Oracle to PostgreSQL |

---

## Code

### Task 1: Migrate from Oracle Database to Azure PostgreSQL

**Description**: Migrate the application database from Oracle Database to Azure Database for PostgreSQL, including updating JDBC drivers, database dialect configuration, and ensuring SQL compatibility for all queries and schema definitions.

**Requirements**:
  Migrate to Azure PostgreSQL

**Environment Configuration**:
  Not specified by user

**App Scope**:
  - C:\Users\haiche\Documents\AppMod\PhotoAlbum-Java

**Skill**: 
  - Skill Name: migration-oracle-to-postgresql
  - Skill Source: remote

**Success Criteria**:
- Pass Build: Yes - Project must compile successfully after migration
- Generate New Unit Tests (Mock-based): No - Not explicitly requested
- Generate New Integration Tests: No - Not explicitly requested
- Pass Unit Tests: Yes - All tests must pass with PostgreSQL
- Pass New Integration Tests: No - Not explicitly requested
- Pass Security Compliance: No - Not explicitly requested

---

## Clarifications

The following items were not explicitly requested but may be needed for a complete implementation:

1. **Azure Database for PostgreSQL Configuration**
   - **Why needed**: The application needs connection details to connect to Azure PostgreSQL
   - **Options**: 
     - Use existing Azure Database for PostgreSQL instance
     - Create a new Azure Database for PostgreSQL instance
   - **Recommendation**: If not provided, the migration will use connection string placeholders that need to be configured before deployment

2. **Authentication Method**
   - **Why needed**: Azure Database for PostgreSQL supports multiple authentication methods
   - **Options**:
     - Managed Identity (recommended for Azure deployments)
     - Connection string with username/password
   - **Recommendation**: Use Managed Identity for enhanced security when deployed to Azure services (App Service, Container Apps, AKS)

3. **Data Migration Strategy**
   - **Why needed**: Existing photos and metadata in Oracle Database need to be migrated to PostgreSQL
   - **Options**:
     - Manual data migration using export/import tools
     - Use Azure Database Migration Service
     - Start with empty database (data loss)
   - **Recommendation**: If production data exists, plan for data migration using appropriate tools or services

4. **Testing with Azure PostgreSQL**
   - **Why needed**: Integration tests should validate connectivity to Azure PostgreSQL
   - **Options**:
     - Provide Azure PostgreSQL instance for testing
     - Use local PostgreSQL with testcontainers
     - Skip integration tests (not recommended)
   - **Recommendation**: Use testcontainers or provide a development Azure PostgreSQL instance for thorough testing
