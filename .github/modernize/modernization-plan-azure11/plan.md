# Modernization Plan: modernization-plan-azure11

**Project**: PhotoAlbum-Java

---

## Technical Framework

- **Language**: Java 21
- **Framework**: Spring Boot 3.4.5
- **Build Tool**: Maven
- **Database**: Azure Database for PostgreSQL (Flexible Server) with Managed Identity (already configured)
- **Key Dependencies**: Spring Data JPA, Thymeleaf, azure-identity-extensions 1.2.2, Commons IO 2.11.0

---

## Overview

The PhotoAlbum application has already been upgraded to Spring Boot 3.4.5 / Java 21, migrated from Oracle to PostgreSQL, and configured to connect to Azure Database for PostgreSQL using Managed Identity for credential-free authentication. A multi-stage Dockerfile using Java 21 is also in place.

The remaining migration steps to complete the Azure migration are:

- Move photo binary storage from PostgreSQL bytea columns to Azure Blob Storage for improved scalability, performance, and cost efficiency when handling large volumes of image data
- Deploy the containerized application to Azure Container Apps for cloud-native, fully managed hosting with built-in scaling and Managed Identity support

The migration follows a phased approach: transform the photo storage layer first, then deploy to Azure.

---

## Migration Impact Summary

| Application     | Original Service              | New Azure Service      | Authentication   | Comments                                      |
|-----------------|-------------------------------|------------------------|------------------|-----------------------------------------------|
| PhotoAlbum-Java | PostgreSQL bytea (photo_data) | Azure Blob Storage     | Managed Identity | Move binary photo data out of DB to blob store|
| PhotoAlbum-Java | Docker Compose / Local        | Azure Container Apps   | Managed Identity | Containerized Spring Boot web application     |

---

## Tasks

### Phase 1 — Transform

| # | Task                                                          | Type      | Skill |
|---|---------------------------------------------------------------|-----------|-------|
| 1 | Migrate photo binary storage to Azure Blob Storage            | transform | —     |

### Phase 2 — Deployment

| # | Task                                               | Type       | Skill |
|---|----------------------------------------------------|------------|-------|
| 2 | Deploy application to Azure Container Apps         | deployment | —     |
