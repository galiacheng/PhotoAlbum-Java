# Modernization Plan: Migrate Photo Album Java Application to Azure

**Project**: Photo Album Application

---

## Technical Framework

- **Language**: Java 8
- **Framework**: Spring Boot 2.7.18
- **Build Tool**: Maven 3.9.6
- **Database**: Oracle Database Free 23ai
- **Key Dependencies**: Spring Data JPA, Hibernate ORM, Oracle JDBC Driver (ojdbc8), Thymeleaf, Commons IO

---

## Overview

This migration will modernize the Photo Album Java application to run on Azure cloud services. The application currently runs on Docker containers with Spring Boot 2.7.18 and Java 8, using Oracle Database for photo storage as BLOBs. The new architecture will:

- Upgrade to modern Java and Spring Boot versions for improved security, performance, and long-term support
- Migrate from Oracle Database to Azure-managed database services for better cloud integration and reduced operational overhead
- Leverage Azure-native services for secure, scalable, and cost-effective operations
- Enable cloud-native deployment patterns with Azure services

The migration follows a phased approach starting with framework upgrades, followed by database migration, and concluding with Azure service integrations.

---

## Migration Impact Summary

| Application | Original Service | New Azure Service | Authentication | Comments |
|-------------|------------------|-------------------|----------------|----------|
| Photo Album | Oracle Database  | Azure PostgreSQL  | Managed Identity | Migrate to Azure managed database |
| Photo Album | Java 8           | Java 17+          | N/A            | Upgrade to latest LTS Java version |
| Photo Album | Spring Boot 2.7  | Spring Boot 3.x   | N/A            | Includes Spring Framework 6.x upgrade |

---

## Code

### Task 1: Upgrade Spring Boot to 3.x

**Description**: Upgrade the application from Spring Boot 2.7.18 to Spring Boot 3.x to leverage the latest features, security updates, and performance improvements.

**Requirements**:
- Upgrade Spring Boot from 2.7.18 to the latest 3.x version
- This upgrade includes JDK 17, Spring Framework 6.x, and migration from JavaEE (javax.*) to Jakarta EE (jakarta.*)
- Ensure all dependencies are compatible with Spring Boot 3.x
- Update application code to use Jakarta EE namespaces instead of javax.*

**Environment Configuration**:
(Not specified)

**App Scope**:
- Root directory: C:\Users\haiche\Documents\AppMod\PhotoAlbum-Java

**Skills**: 
- Skill Name: migration-spring-boot-upgrade
  - Skill Location: builtin

**Success Criteria**:
- Pass Build: Yes - Project must compile successfully after migration
- Generate New Unit Tests (Mock-based): No - Create mock-based unit tests for newly added Azure integration code to ensure test coverage
- Generate New Integration Tests: No - Create integration tests for Azure service interactions when requested
- Pass Unit Tests: Yes - All tests must pass; mock dependent Azure resources if not provided
- Pass New Integration Tests: No - Integration tests must pass when generated
- Pass Security Compliance: No - No known CVEs exist in project dependencies

---

### Task 2: Migrate from Oracle Database to Azure PostgreSQL

**Description**: Migrate the application from Oracle Database to Azure Database for PostgreSQL with managed identity authentication for secure, credential-free database access.

**Requirements**:
- Migrate from Oracle Database to Azure Database for PostgreSQL
- Use managed identity for authentication
- Migrate database schema including the PHOTOS table with BLOB storage
- Update Spring Data JPA configuration for PostgreSQL
- Ensure photo data (BLOBs) are properly migrated to PostgreSQL BYTEA type

**Environment Configuration**:
(Not specified)

**App Scope**:
- Root directory: C:\Users\haiche\Documents\AppMod\PhotoAlbum-Java

**Skills**: 
- Skill Name: migration-mi-postgresql-azure-sdk-public-cloud
  - Skill Location: builtin

**Success Criteria**:
- Pass Build: Yes - Project must compile successfully after migration
- Generate New Unit Tests (Mock-based): No - Create mock-based unit tests for newly added Azure integration code to ensure test coverage
- Generate New Integration Tests: No - Create integration tests for Azure service interactions when requested
- Pass Unit Tests: Yes - All tests must pass; mock dependent Azure resources if not provided
- Pass New Integration Tests: No - Integration tests must pass when generated
- Pass Security Compliance: No - No known CVEs exist in project dependencies

---

## Clarifications

The following items were not explicitly requested but may be needed for a complete implementation:

1. **Azure Deployment Strategy**
   - **Why needed**: The plan focuses on code modernization but doesn't specify deployment to Azure services (AKS, ACA, App Service)
   - **Options**: 
     - Azure App Service (simplest, PaaS)
     - Azure Container Apps (serverless containers)
     - Azure Kubernetes Service (full orchestration)
   - **Recommendation**: If deployment is needed, Azure App Service is recommended for this application type unless container orchestration is required

2. **File Storage for Photos**
   - **Why needed**: Currently photos are stored as BLOBs in the database, which is functional but may not be optimal for cloud deployments
   - **Options**: 
     - Continue storing in database (PostgreSQL BYTEA)
     - Migrate to Azure Blob Storage for better scalability and CDN integration
   - **Recommendation**: Keep database storage for simplicity unless large-scale photo volumes require object storage

3. **Azure PostgreSQL Configuration**
   - **Why needed**: Azure PostgreSQL requires specific configuration details
   - **Options**: 
     - Use existing Azure PostgreSQL instance (requires connection details)
     - Create new Azure PostgreSQL instance during migration
   - **Recommendation**: If not provided, the migration will prepare the code for Azure PostgreSQL but won't provision infrastructure

4. **Environment Configuration and Secrets Management**
   - **Why needed**: Cloud deployments typically require secure management of configuration and secrets
   - **Options**: 
     - Azure Key Vault for secrets management
     - Azure App Configuration for application settings
   - **Recommendation**: Consider Azure Key Vault integration if managing sensitive configuration beyond database credentials
