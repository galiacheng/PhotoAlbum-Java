# Modernization Plan: Migrate Photo Storage to Azure Blob Storage

**Project**: Photo Album Application

---

## Technical Framework

- **Language**: Java 8
- **Framework**: Spring Boot 2.7.18
- **Build Tool**: Maven 3.9
- **Database**: Oracle Database 21c Express Edition
- **Key Dependencies**: Spring Data JPA, Hibernate, Thymeleaf, Commons IO

---

## Overview

This migration moves photo storage from Oracle Database BLOBs to Azure Blob Storage. The application currently stores all photo binary data as BLOBs within the Oracle database, which increases database size and can impact performance. The new architecture will:

- Store photo binary data in Azure Blob Storage for scalable and cost-effective object storage
- Maintain photo metadata (filename, dimensions, upload date) in Oracle Database
- Use Azure Managed Identity for secure, credential-free authentication to Blob Storage
- Preserve all existing functionality including upload, retrieval, deletion, and navigation

The migration follows a single-task approach to transition the photo storage layer from database BLOBs to Azure Blob Storage while maintaining backward compatibility with the existing application structure.

---

## Migration Impact Summary

| Application | Original Service | New Azure Service | Authentication | Comments |
|-------------|------------------|-------------------|----------------|----------|
| Photo Album | Database BLOB    | Azure Blob Storage| Managed Identity| Migrate photo binary storage |

---

## Code

### Task 1: Migrate Photo Storage from Database BLOBs to Azure Blob Storage

**Description**: Migrate photo binary data storage from Oracle Database BLOBs to Azure Blob Storage for scalable and cost-effective object storage.

**Requirements**:
  Migrate from database BLOB storage to Azure Blob Storage. Update the photo upload, retrieval, and deletion operations to use Azure Blob Storage instead of storing binary data in the Oracle database. Maintain metadata in Oracle Database. Use Managed Identity for authentication.

**Environment Configuration**:
  

**App Scope**:
  PhotoAlbum-Java

**Skills**:
  - Skill Name: migration-s3-to-azure-blob-storage
    - Skill Location: builtin

**Success Criteria**:
- Pass Build: true - Project must compile successfully after migration
- Generate New Unit Tests (Mock-based): false - Create mock-based unit tests for newly added Azure integration code to ensure test coverage
- Generate New Integration Tests: false - Create integration tests for Azure service interactions when requested:
  - If Azure resources are provided/accessible: Create tests that interact with actual Azure services
  - If no Azure resources are provided: Create integration tests with mocked Azure SDK clients using test containers or in-memory implementations where possible
- Pass Unit Tests: true - All tests must pass; mock dependent Azure resources if not provided
- Pass New Integration Tests: false - Integration tests must pass when generated:
  - With Azure resources: Tests validate actual Azure service connectivity and operations
  - Without Azure resources: Tests validate integration logic using mocked Azure clients, ensuring the integration layer works correctly
- Pass Security Compliance: false - No known CVEs exist in project dependencies

---

## Clarifications

The following items were not explicitly requested but may be needed for a complete implementation:

1. **Java Version Upgrade**: The application is currently running on Java 8, which is outdated and no longer receiving public updates.
   - **Why needed**: Java 17 is the current LTS version with better performance, security, and compatibility with modern Azure SDKs
   - **Options**: 
     - Upgrade to Java 17 before migration
     - Perform migration on Java 8 and upgrade later
   - **Recommendation**: Consider upgrading to Java 17 as part of this modernization effort for better long-term support

2. **Database BLOB Data Migration**: Existing photos stored as BLOBs in the database will need to be migrated to Azure Blob Storage.
   - **Why needed**: To maintain access to existing photos after the migration
   - **Options**: 
     - Create a one-time migration script to transfer existing BLOBs to Azure Blob Storage
     - Implement lazy migration (migrate photos on first access)
     - Start fresh with empty storage
   - **Recommendation**: Create a migration script to preserve existing photo data
