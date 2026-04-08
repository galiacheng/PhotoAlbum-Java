# Modernization Plan: PhotoAlbum Azure Migration

**Project**: PhotoAlbum Java

---

## Technical Framework

- **Language**: Java 8 (target: Java 21)
- **Framework**: Spring Boot 2.7.18 / Spring Framework 5.x (target: Spring Boot 3.x / Spring Framework 6.x)
- **Build Tool**: Maven
- **Database**: Oracle Database Free 23ai (target: Azure Database for PostgreSQL)
- **Key Dependencies**: Spring Data JPA, Hibernate, Thymeleaf, Oracle JDBC (ojdbc8)

---

## Overview

This migration moves the PhotoAlbum application from an Oracle-based on-premises stack to
Azure cloud services. The application currently runs on Java 8 with Spring Boot 2.7.18 and
stores photos as BLOBs in an Oracle database. The new architecture will:

- Upgrade to Java 21 and Spring Boot 3.x for modern runtime support, long-term support,
  and Jakarta EE compliance
- Migrate from Oracle Database to Azure Database for PostgreSQL with Managed Identity
  authentication for secure, credential-free database access
- Update the container image to reflect the Java 21 runtime environment

The migration follows a phased approach: first upgrading the runtime, then migrating the
database, and finally updating the containerization configuration.

---

## Migration Impact Summary

```
| Application  | Original Service       | New Azure Service              | Authentication    | Comments                              |
|--------------|------------------------|--------------------------------|-------------------|---------------------------------------|
| PhotoAlbum   | Java 8 / Spring Boot   | Java 21 / Spring Boot 3.x     | N/A               | Jakarta EE migration (javax→jakarta)  |
|              | 2.7.18                 |                                |                   |                                       |
| PhotoAlbum   | Oracle Database        | Azure Database for PostgreSQL  | Managed Identity  | Oracle-specific SQL queries must be   |
|              | Free 23ai              | Flexible Server                |                   | refactored for PostgreSQL             |
```

---

## Migration Tasks

### Task 1 — Upgrade Java and Spring Boot

Upgrade the application from Java 8 / Spring Boot 2.7.18 to Java 21 / Spring Boot 3.x,
including Jakarta EE namespace migration (javax.* → jakarta.*).

### Task 2 — Migrate Oracle Database to Azure PostgreSQL

Migrate from Oracle Database to Azure Database for PostgreSQL Flexible Server using Managed
Identity for credential-free authentication. This includes refactoring Oracle-specific
SQL (ROWNUM, TO_CHAR, RANK() window functions) to PostgreSQL-compatible equivalents and
replacing the Oracle JDBC driver with the PostgreSQL driver.

### Task 3 — Update Containerization for Java 21

Update the existing Dockerfile to use the Java 21 base image, replacing the current Java 8
Maven and runtime images.

---

## Clarifications

The following items were not explicitly requested but may be needed for a complete migration:

1. **Photo BLOB Storage Migration**: Currently, photos are stored as BLOB columns
   directly in the Oracle database. After migration to PostgreSQL, they will be stored
   as `bytea` columns (PostgreSQL equivalent).
   - **Why needed**: For large-scale deployments, Azure Blob Storage offers better
     performance, cost efficiency, and scalability compared to storing binary files in
     a relational database.
   - **Options**:
     - (A) Keep photos as `bytea` in Azure PostgreSQL — simpler migration, no code
       changes beyond DB migration
     - (B) Move photos to Azure Blob Storage — cloud-native approach, better for scale
   - **Recommendation**: Option A (keep in PostgreSQL) for now unless the user has
     scalability or storage cost concerns.
