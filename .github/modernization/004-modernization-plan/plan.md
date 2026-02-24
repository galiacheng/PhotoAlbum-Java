# Modernization Plan: Migrate Oracle Database to Azure PostgreSQL

**Project**: PhotoAlbum-Java  

---

## Technical Framework

- **Language**: Java 8
- **Framework**: Spring Boot 2.7.18
- **Build Tool**: Maven 3.x
- **Database**: Oracle Database 21c Express Edition
- **Key Dependencies**: Spring Data JPA, Hibernate ORM, Oracle JDBC Driver (ojdbc8)

---

## Overview

> This migration transforms the PhotoAlbum application from using Oracle Database to Azure Database for PostgreSQL. The application currently stores photo metadata and BLOB data in an Oracle 21c XE database using Spring Data JPA. The new architecture will:
> 
> - Replace Oracle Database with Azure Database for PostgreSQL for improved scalability and reduced operational overhead
> - Modernize the Java runtime and Spring Boot framework to the latest stable versions for enhanced security and performance
> - Leverage Azure-native authentication mechanisms for secure database connectivity
> 
> The migration follows a phased approach: first upgrading the application framework to ensure compatibility, then migrating the database layer to PostgreSQL.

---

## Migration Impact Summary

| Application | Original Service | New Azure Service | Authentication | Comments |
|-------------|------------------|-------------------|----------------|----------|
| PhotoAlbum-Java | Oracle 21c XE | Azure PostgreSQL | Managed Identity | Migrate photo storage app |

---

## Code

### Task 1: Migrate from Oracle to Azure PostgreSQL

**Description**: Migrate the database layer from Oracle Database to Azure Database for PostgreSQL, including updating JDBC drivers, SQL dialects, and data access patterns. The migration will leverage managed identity for secure authentication to Azure PostgreSQL.

**Requirements**:
- Migrate from Oracle Database to Azure PostgreSQL
- Use managed identity for authentication

**Environment Configuration**:
  (Not specified - Azure PostgreSQL endpoint and configuration to be provided during execution)

**App Scope**:
  C:\Users\haiche\Documents\AppMod\PhotoAlbum-Java

**Skill**: 
  - Skill Name: migration-mi-postgresql-azure-sdk-public-cloud
  - Skill Source: remote

**Success Criteria**:
- Pass Build: Yes - Project must compile successfully after migration
- Generate New Unit Tests (Mock-based): Yes
- Generate New Integration Tests: No
- Pass Unit Tests: Yes - All tests must pass with mocked Azure resources
- Pass New Integration Tests: No
- Pass Security Compliance: No

---

## Clarifications

The following items were not explicitly requested but may be needed for a complete implementation:

1. **Azure PostgreSQL Resource**: 
   - **Why needed**: The migration requires an Azure Database for PostgreSQL instance to connect to
   - **Options**: 
     - Use existing Azure PostgreSQL instance (provide connection details)
     - Create new Azure PostgreSQL instance during migration
   - **Recommendation**: If not provided, the migration skill will guide through creating a new instance or provide instructions for configuration

2. **Schema Migration Strategy**: 
   - **Why needed**: Existing Oracle database schema and data may need to be migrated to PostgreSQL
   - **Options**: 
     - Start with fresh schema (Hibernate will recreate tables)
     - Migrate existing schema and data from Oracle to PostgreSQL
   - **Recommendation**: If preserving existing data, use Azure Database Migration Service or pg_dump/pg_restore tools before running the application migration

3. **BLOB Storage Considerations**: 
   - **Why needed**: The application stores photo data as BLOBs in the database, which may impact PostgreSQL performance at scale
   - **Options**: 
     - Keep BLOB storage in PostgreSQL (BYTEA type)
     - Migrate to Azure Blob Storage for photo files (recommended for production)
   - **Recommendation**: For this migration, maintain BLOB storage in database for simplicity. Consider Azure Blob Storage migration as a future enhancement

4. **Testing Environment**: 
   - **Why needed**: Integration tests may require actual Azure PostgreSQL connection or test containers
   - **Options**: 
     - Provide Azure PostgreSQL test instance credentials
     - Use PostgreSQL test containers for local testing
     - Mock Azure SDK clients for unit testing
   - **Recommendation**: Use test containers (Testcontainers with PostgreSQL) for integration testing if Azure resources are not available
