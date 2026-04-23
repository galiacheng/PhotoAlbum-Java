# Modernization Plan: PhotoAlbum-Java Migration to Azure

**Project**: PhotoAlbum-Java

---

## Technical Framework

- **Language**: Java 8
- **Framework**: Spring Boot 2.7.18
- **Build Tool**: Maven 3.9
- **Database**: Oracle Database Free (23ai) — photo metadata and binary photo data (BLOB)
- **Key Dependencies**: Spring Data JPA, Hibernate, Thymeleaf, Commons IO

---

## Overview

> This migration moves the PhotoAlbum-Java application from an Oracle database backend
> to Azure-managed cloud services. The application currently stores all photo metadata
> and binary image data (as Oracle BLOBs) in an on-premises Oracle database container.
> The new architecture will:
>
> - Replace Oracle Database with Azure Database for PostgreSQL, improving cloud-native
>   scalability and eliminating Oracle licensing costs
> - Migrate photo binary data from Oracle BLOBs to Azure Blob Storage, enabling
>   cost-effective, highly durable, and scalable image storage with CDN support
> - Eliminate plaintext database credentials by adopting Azure Managed Identity for
>   passwordless, secure authentication to Azure services
>
> The migration follows a phased approach: first modernizing the database tier
> (Oracle → Azure PostgreSQL), then externalizing binary photo storage to Azure Blob
> Storage, and finally securing all service authentication with Managed Identity.

---

## Migration Impact Summary

| Application      | Original Service     | New Azure Service             | Authentication     | Comments                             |
|------------------|----------------------|-------------------------------|--------------------|--------------------------------------|
| PhotoAlbum-Java  | Oracle Database      | Azure Database for PostgreSQL | Managed Identity   | Metadata + schema migration          |
| PhotoAlbum-Java  | Oracle BLOB storage  | Azure Blob Storage            | Managed Identity   | Binary photo data offloaded from DB  |
| PhotoAlbum-Java  | Plaintext credentials| Azure Key Vault / MI          | Managed Identity   | Remove hardcoded DB credentials      |

---

## Migration Tasks

### Task 1 — Migrate Oracle Database to Azure Database for PostgreSQL

Migrate all Oracle-specific database code, JPA configuration, Hibernate dialect, and SQL
dialect to be compatible with Azure Database for PostgreSQL.

**Skill**: `migration-oracle-to-postgresql`

---

### Task 2 — Enable Managed Identity for Azure Database for PostgreSQL

Replace password-based JDBC authentication with Azure Managed Identity for secure,
credential-free connectivity to Azure Database for PostgreSQL.

**Skill**: `migration-mi-postgresql-azure-sdk-public-cloud`

*Depends on*: Task 1

---

### Task 3 — Migrate Photo Binary Data from Database to Azure Blob Storage

Migrate the photo binary data currently stored as BLOBs in Oracle (and subsequently
PostgreSQL) to Azure Blob Storage. Update the application to upload photos to and serve
them from Azure Blob Storage using Managed Identity authentication.

*Depends on*: Task 1

---

### Task 4 — Migrate Plaintext Credentials to Azure Key Vault

Remove hardcoded database credentials from `application.properties` and migrate them to
Azure Key Vault. Update the application to retrieve secrets securely at runtime.

**Skill**: `migration-plaintext-credential-to-azure-keyvault`

*Depends on*: Task 1, Task 2

---
