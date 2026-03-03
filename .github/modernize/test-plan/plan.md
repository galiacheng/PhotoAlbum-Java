# Modernization Plan: Migrate to Azure Blob Storage

**Project**: PhotoAlbum-Java

---

## Technical Framework

- **Language**: Java 8
- **Framework**: Spring Boot 2.7.18
- **Build Tool**: Maven 3.9
- **Database**: Oracle Database 21c Express Edition
- **Key Dependencies**: Spring Data JPA, Thymeleaf, Commons IO

---

## Overview

This migration will transition the photo storage architecture from Oracle Database BLOB storage to Azure Blob Storage. The application currently stores all photo files as BLOB data directly in the Oracle database. The new architecture will:

- Store photo files in Azure Blob Storage for scalable object storage
- Maintain metadata (filename, size, dimensions, timestamps) in the Oracle database
- Use Azure Managed Identity for secure, credential-free authentication to Azure services
- Improve scalability and reduce database storage requirements

The migration follows a phased approach: first upgrading the Java and Spring Boot versions for compatibility, then migrating the photo storage layer to Azure Blob Storage.

---

## Migration Impact Summary

| Application | Original Service | New Azure Service | Authentication | Comments |
|-------------|------------------|-------------------|----------------|----------|
| PhotoAlbum  | Oracle DB BLOB   | Azure Blob Storage| Managed Identity | Migrate photo storage |

---

## Code

### Task 1: Upgrade Spring Boot to 3.x

**Description**: Upgrade the application to Spring Boot 3.x to ensure compatibility with modern Azure SDKs and Java 17 runtime.

**Requirements**:
  

**Environment Configuration**:
  

**App Scope**:
  /private/var/folders/53/mmzvzmx94mgbpc1xzcy76nsm0000gp/T/tmp.jW3k95c3HB/PhotoAlbum-Java

**Skills**:
  - Skill Name: migration-spring-boot-upgrade
    - Skill Location: builtin

**Success Criteria**:
- Pass Build: true
- Generate New Unit Tests (Mock-based): false
- Generate New Integration Tests: false
- Pass Unit Tests: true
- Pass New Integration Tests: false
- Pass Security Compliance: false

---

### Task 2: Migrate Photo Storage to Azure Blob Storage

**Description**: Migrate photo storage from Oracle Database BLOBs to Azure Blob Storage for scalable object storage.

**Requirements**:
  migrate from AWS S3 to Azure Blob Storage

**Environment Configuration**:
  

**App Scope**:
  /private/var/folders/53/mmzvzmx94mgbpc1xzcy76nsm0000gp/T/tmp.jW3k95c3HB/PhotoAlbum-Java

**Prompt**:
  Migrate this application from using AWS S3 to Azure Blob Storage. Ensure all usages including both code and configuration are migrated and build passed.

**Success Criteria**:
- Pass Build: true
- Generate New Unit Tests (Mock-based): false
- Generate New Integration Tests: false
- Pass Unit Tests: true
- Pass New Integration Tests: false
- Pass Security Compliance: false

---

## Clarifications

The following items were not explicitly requested but may be needed for a complete implementation:

1. **AWS S3 Detection**: The current application stores photos in Oracle Database BLOBs, not AWS S3. 
   - **Why needed**: The user requested migration from AWS S3, but no AWS S3 usage was detected in the codebase.
   - **Options**: 
     - Proceed with migrating from Oracle BLOB to Azure Blob Storage
     - Confirm if AWS S3 integration exists elsewhere
   - **Recommendation**: Proceed with Oracle BLOB to Azure Blob Storage migration, as this aligns with cloud-native best practices

2. **Data Migration Strategy**: Existing photos stored in Oracle BLOBs need to be migrated to Azure Blob Storage.
   - **Why needed**: Photos currently in the database must be transferred to Azure for the migration to be complete
   - **Options**: 
     - Create a one-time migration script to export BLOBs to Azure Blob Storage
     - Manual data export and import
     - Run-time migration (lazy migration as photos are accessed)
   - **Recommendation**: Create a migration script to export all existing photos to Azure Blob Storage and update database references

3. **Azure Blob Storage Container Configuration**: Need to specify container name and configuration.
   - **Why needed**: Application needs to know which Azure Blob Storage container to use
   - **Options**: 
     - Use default container name 'photos'
     - User-specified container name
   - **Recommendation**: Use 'photos' as the default container name unless specified otherwise
