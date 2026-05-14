# Modernization Plan: Migrate PhotoAlbum-Java to Azure

**Project**: PhotoAlbum-Java

---

## Technical Framework

- **Language**: Java 8
- **Framework**: Spring Boot 2.7.18
- **Build Tool**: Maven
- **Database**: Oracle Database (FREEPDB1) — photos stored as BLOBs
- **Key Dependencies**: Spring Data JPA, Spring Web, Thymeleaf, ojdbc8

---

## Overview

This migration modernizes the PhotoAlbum-Java application to run on Azure. The application currently stores photos as binary BLOBs in an Oracle database and uses password-based authentication with plaintext credentials. The new architecture will:

- Replace Oracle Database with Azure Database for PostgreSQL for managed, cloud-native relational data storage
- Enable passwordless database authentication using Azure Managed Identity, eliminating hardcoded credentials
- Adopt console-only logging for cloud-native observability aligned with Azure Monitor
- Remediate known CVE vulnerabilities to ensure security compliance before deployment

The migration follows a phased approach: database migration first, then secure authentication, then logging modernization, and finally security hardening.

---

## Migration Impact Summary

| Application     | Original Service    | New Azure Service                    | Authentication     | Comments                              |
|-----------------|---------------------|--------------------------------------|--------------------|---------------------------------------|
| PhotoAlbum-Java | Oracle Database     | Azure Database for PostgreSQL        | Managed Identity   | Photos stored as BLOB/bytea           |
| PhotoAlbum-Java | Password-based auth | Azure Managed Identity (PostgreSQL)  | Managed Identity   | Remove plaintext DB password          |
| PhotoAlbum-Java | File logging        | Console logging                      | N/A                | Cloud-native logging for Azure Monitor|

---

## Migration Tasks

### Task 1 — Migrate Oracle Database to Azure Database for PostgreSQL

Migrate the application's database layer from Oracle Database to Azure Database for PostgreSQL. This includes updating the JDBC driver, JPA dialect, SQL syntax, and handling the photo binary data (BLOB → bytea) stored in the `photos` table.

**Skill**: `migration-oracle-to-postgresql`

---

### Task 2 — Enable Managed Identity Authentication for Azure Database for PostgreSQL

Replace the hardcoded password-based datasource configuration with Azure Managed Identity for passwordless, secure authentication to Azure Database for PostgreSQL.

**Skill**: `migration-mi-postgresql`
**Depends on**: Task 1

---

### Task 3 — Migrate Logging to Console Output

Update the application logging configuration to output exclusively to console, removing any file-based log appenders. This ensures compatibility with Azure's cloud-native logging and Azure Monitor integration.

**Skill**: `migration-log-to-console`