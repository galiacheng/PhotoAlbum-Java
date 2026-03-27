# Modernization Plan: Migrate PhotoAlbum to Azure

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
- Package the updated application in a modernized container image
- Deploy the application to Azure for cloud-native hosting

The migration follows a phased approach: upgrade first, then transform data layer dependencies, then containerize and deploy.

---

## Migration Impact Summary

| Application      | Original Service | New Azure Service                     | Authentication   | Comments                                  |
|------------------|------------------|---------------------------------------|------------------|-------------------------------------------|
| PhotoAlbum-Java  | Oracle Database  | Azure Database for PostgreSQL         | Managed Identity | Oracle dialect → PostgreSQL; BLOB → bytea |

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

1. **Target Azure Compute Service**: The user requested migration to Azure but did not specify a compute target.
   - **Why needed**: Determines the deployment topology, containerization requirements, and networking model.
   - **Options**: Azure Container Apps (recommended for containerized web apps), Azure App Service (simpler PaaS), AKS (for Kubernetes-native workloads).
   - **Recommendation**: Azure Container Apps — best fit for a single containerized Spring Boot service.

2. **Photo BLOB Storage Strategy**: Photos are currently stored as BLOBs in Oracle. After migration they will be stored as `bytea` in PostgreSQL.
   - **Why needed**: Storing large binary data in the database can affect performance and cost at scale.
   - **Options**: Keep as `bytea` in Azure Database for PostgreSQL (simpler, no code change beyond DB migration), or move to Azure Blob Storage (better performance/cost for large volumes of images).
   - **Recommendation**: Retain `bytea` in PostgreSQL for simplicity unless volume of photos is expected to be large.
