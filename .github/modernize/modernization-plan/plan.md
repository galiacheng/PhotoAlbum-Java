# Modernization Plan: PhotoAlbum Java Migration to Azure

**Project**: PhotoAlbum-Java

---

## Technical Framework

- **Language**: Java 8
- **Framework**: Spring Boot 2.7.18 / Spring MVC 5.x (javax.*)
- **Build Tool**: Maven 3.9
- **Database**: Oracle Database (FREEPDB1) with BLOB photo storage
- **Key Dependencies**: Spring Data JPA, Hibernate, Thymeleaf, ojdbc8, commons-io 2.11

---

## Overview

This migration moves the PhotoAlbum application from its current Oracle Database backend to Azure-managed cloud services. The application currently stores all photos as binary BLOBs directly in Oracle, and uses Oracle-specific SQL queries (ROWNUM pagination, TO_CHAR, NVL, window functions). The new architecture will:

- Upgrade the runtime to Java 21 and Spring Boot 3.x, enabling modern Azure SDK compatibility and jakarta.* namespace
- Migrate the relational data from Oracle to Azure Database for PostgreSQL using managed identity for secure, credential-free authentication
- Migrate binary photo storage from database BLOBs to Azure Blob Storage, improving scalability and performance

The migration follows a phased approach: runtime upgrade first, then database migration, then cloud-native photo storage.

---

## Migration Impact Summary

```
| Application  | Original Service          | New Azure Service                       | Authentication   | Comments                              |
|--------------|---------------------------|-----------------------------------------|------------------|---------------------------------------|
| PhotoAlbum   | Oracle DB (FREEPDB1)      | Azure Database for PostgreSQL           | Managed Identity | Migrate Oracle SQL to PostgreSQL      |
| PhotoAlbum   | Oracle BLOB Photo Storage | Azure Blob Storage                      | Managed Identity | Move photo binaries out of DB         |
```

---

## Modernization Tasks

### Task 1 — Upgrade Spring Boot and Java Runtime

Upgrade the application from Spring Boot 2.7.18 / Java 8 to Spring Boot 3.x / Java 21. This includes migrating from `javax.*` to `jakarta.*` namespaces and updating all incompatible dependencies to versions compatible with Spring Boot 3.x.

### Task 2 — Migrate Oracle DB to Azure Database for PostgreSQL

Convert all Oracle-specific SQL and JPA configuration to PostgreSQL-compatible equivalents. This includes replacing Oracle ROWNUM pagination, TO_CHAR date formatting, NVL null handling, and Oracle-dialect window functions with standard PostgreSQL equivalents.

### Task 3 — Connect to Azure Database for PostgreSQL with Managed Identity

Reconfigure the database connection to use Azure Database for PostgreSQL with managed identity authentication, replacing the plaintext credentials currently in `application.properties`.

### Task 4 — Migrate Photo Storage from DB BLOB to Azure Blob Storage

Refactor photo upload, retrieval, and delete operations to use Azure Blob Storage instead of storing binary data as database BLOBs. Update `PhotoServiceImpl` and `PhotoFileController` to interact with Azure Blob Storage SDK, using managed identity for secure access.

---

## Clarifications

The following items were not explicitly requested but may be needed for a complete implementation:

1. **Photo data migration**: The plan covers code-level migration to Azure Blob Storage, but existing photos in the Oracle database will need a one-time data migration script to move BLOBs to Azure Blob Storage. This is outside the scope of code migration tasks.
   - **Why needed**: Existing photo records in Oracle have binary data that won't automatically transfer.
   - **Options**: (a) Accept data loss and start fresh, (b) Write a one-time migration script.
   - **Recommendation**: Provide a migration script as part of the deployment process.

2. **Containerization and Deployment**: The existing Dockerfile uses Java 8 and will need to be updated after the Java 21 upgrade. No deployment target was specified.
   - **Why needed**: The Dockerfile will break after the Spring Boot 3.x upgrade.
   - **Options**: Update Dockerfile for Java 21; deploy to Azure Container Apps, AKS, or App Service.
   - **Recommendation**: Update Dockerfile as part of the upgrade task; specify deployment target if needed.
