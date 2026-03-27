# Modernization Plan: modernization-plan-migrate-to-azure4

**Project**: PhotoAlbum-Java

---

## Technical Framework

- **Language**: Java 8
- **Framework**: Spring Boot 2.7.18
- **Build Tool**: Maven
- **Database**: Oracle Database (Free 23ai)
- **Key Dependencies**: Spring Data JPA, Hibernate (Oracle Dialect), Thymeleaf, Commons IO 2.11.0

---

## Overview

This migration moves the PhotoAlbum Java application from a self-hosted Oracle Database environment to a fully managed Azure cloud infrastructure. The application currently runs as a Spring Boot 2.7.18 / Java 8 web app storing photo BLOBs in an Oracle Database, deployed via Docker Compose.

The new architecture will:

- Upgrade the application to Spring Boot 3.x and Java 21 for long-term support and Azure SDK compatibility
- Replace Oracle Database with Azure Database for PostgreSQL using Managed Identity for secure, credential-free authentication
- Update the container image to use Java 21 runtime
- Deploy the application to Azure Container Apps for cloud-native, fully managed hosting

The migration follows a phased approach: upgrade first, then transform the data layer, then containerize and deploy to Azure.

---

## Migration Impact Summary

| Application     | Original Service | New Azure Service             | Authentication   | Comments                                  |
|-----------------|------------------|-------------------------------|------------------|-------------------------------------------|
| PhotoAlbum-Java | Oracle Database  | Azure Database for PostgreSQL | Managed Identity | Oracle dialect → PostgreSQL; BLOB → bytea |
| PhotoAlbum-Java | Docker Compose   | Azure Container Apps          | Managed Identity | Containerized Spring Boot web application |

---

## Tasks

### Phase 1 — Upgrade

| # | Task | Type | Skill |
|---|------|------|-------|
| 1 | Upgrade Spring Boot to 3.x and Java to 21 | upgrade | create-java-upgrade-plan |

### Phase 2 — Transform

| # | Task | Type | Skill |
|---|------|------|-------|
| 2 | Migrate database layer from Oracle to PostgreSQL | transform | migration-oracle-to-postgresql |
| 3 | Configure Azure Database for PostgreSQL with Managed Identity | transform | migration-mi-postgresql-azure-sdk-public-cloud |

### Phase 3 — Containerization & Deployment

| # | Task | Type | Skill |
|---|------|------|-------|
| 4 | Update Dockerfile for Java 21 runtime | containerization | — |
| 5 | Deploy application to Azure Container Apps | deployment | — |

---

## Clarifications

The following items were not explicitly specified but may be needed for a complete implementation:

1. **Photo BLOB Storage Strategy**: Photos are currently stored as BLOBs in Oracle. After migration they will be stored as `bytea` in Azure Database for PostgreSQL.
   - **Why needed**: Storing large binary data in the database can affect performance and cost at scale.
   - **Options**: Keep as `bytea` in Azure Database for PostgreSQL (simpler, no additional code change), or move to Azure Blob Storage (better performance/cost for large volumes of images).
   - **Recommendation**: Retain `bytea` in PostgreSQL for simplicity unless the volume of photos is expected to be large.
