# Modernization Plan: PhotoAlbum Java – Migrate to Azure

**Project**: PhotoAlbum

---

## Technical Framework

- **Language**: Java 8
- **Framework**: Spring Boot 2.7.18
- **Build Tool**: Maven 3
- **Database**: Oracle Database Free (23ai), photos stored as BLOBs
- **Key Dependencies**: Spring Data JPA, Hibernate (OracleDialect), ojdbc8, Thymeleaf, commons-io

---

## Overview

This migration modernizes the PhotoAlbum Java application to run on Azure. The application currently runs on Java 8 / Spring Boot 2.7.18 and uses an Oracle database (with photo binary data stored as BLOBs) deployed via Docker Compose. The new architecture will:

- Upgrade the runtime from Java 8 / Spring Boot 2.7.18 to Java 21 / Spring Boot 3.x, enabling Jakarta EE APIs, improved security, and long-term support.
- Replace the Oracle database with Azure Database for PostgreSQL, enabling a fully managed, cloud-native relational database with secure credential-free access via Managed Identity.
- Update the container image to use a Java 21 base, aligned with the upgraded runtime.

The migration follows a phased approach: runtime upgrade first, then service migration, then containerization update.

---

## Migration Impact Summary

| Application   | Original Service     | New Azure Service                   | Authentication    | Comments                                    |
|---------------|----------------------|-------------------------------------|-------------------|---------------------------------------------|
| PhotoAlbum    | Oracle Database      | Azure Database for PostgreSQL       | Managed Identity  | Includes BLOB data migration to bytea       |

---

## Migration Tasks

### Phase 1 – Runtime Upgrade

| # | Task                            | Type    | Skill                        |
|---|---------------------------------|---------|------------------------------|
| 1 | Upgrade to Spring Boot 3.x / Java 21 | upgrade | create-java-upgrade-plan (builtin) |

### Phase 2 – Service Migration

| # | Task                                   | Type      | Skill                              |
|---|----------------------------------------|-----------|------------------------------------|
| 2 | Migrate Oracle DB to Azure PostgreSQL  | transform | migration-oracle-to-postgresql     |

### Phase 3 – Containerization

| # | Task                               | Type              | Notes                               |
|---|------------------------------------|-------------------|-------------------------------------|
| 3 | Update Dockerfile for Java 21      | containerization  | Update base image from Java 8 to 21 |

---

## Clarifications

The following item was not explicitly requested but may be needed for a complete implementation:

1. **Photo BLOB storage strategy**: Photos are currently stored as binary BLOBs in Oracle. When migrating to PostgreSQL, two approaches are available:
   - **Option A** *(recommended default)*: Store photo binary data as `bytea` columns in Azure Database for PostgreSQL. This preserves the existing architecture with minimal code changes.
   - **Option B**: Extract photos from the database and store them in Azure Blob Storage, referencing them by URL in PostgreSQL. This is more cloud-native and avoids large binary data in the database.
   - **Recommendation**: Proceed with Option A (`bytea` in PostgreSQL) unless you prefer to separate binary data from relational data.
