# Modernization Plan: PhotoAlbum-Java – Migrate to Azure

**Project**: PhotoAlbum-Java

---

## Technical Framework

- **Language**: Java 8 (OpenJDK 8)
- **Framework**: Spring Boot 2.7.18
- **Build Tool**: Maven 3
- **Database**: Oracle Database (Oracle JDBC ojdbc8, FREEPDB1)
- **Key Dependencies**: Spring Data JPA, Hibernate (OracleDialect), Thymeleaf, Commons IO 2.11.0
- **Container**: Existing Dockerfile (Eclipse Temurin 8 / multi-stage Maven build)

---

## Overview

> This migration modernizes the PhotoAlbum Java application for deployment on Azure. The application currently uses Oracle Database as both its relational store and binary photo storage (Oracle BLOBs), and runs as a containerized Spring Boot application.
>
> The new architecture will:
>
> - Replace Oracle Database with Azure Database for PostgreSQL, enabling a fully managed, cloud-native relational database with passwordless authentication via Managed Identity
> - Migrate photo binary storage from Oracle BLOBs to Azure Blob Storage, providing scalable, cost-effective object storage decoupled from the database
> - Remediate known CVEs in project dependencies to ensure security before deployment
> - Deploy the containerized application to Azure Container Apps using the existing Dockerfile
>
> The migration follows a sequential approach: database migration first, then photo storage migration, then security hardening, and finally deployment to Azure Container Apps.

---

## Migration Impact Summary

| Application     | Original Service    | New Azure Service               | Authentication   | Comments                                |
|-----------------|---------------------|---------------------------------|------------------|-----------------------------------------|
| PhotoAlbum-Java | Oracle Database     | Azure Database for PostgreSQL   | Managed Identity | Schema + SQL syntax conversion required |
| PhotoAlbum-Java | Oracle BLOB storage | Azure Blob Storage              | Managed Identity | Photo binaries moved out of DB          |
| PhotoAlbum-Java | Local container     | Azure Container Apps            | N/A              | Existing Dockerfile reused              |

---

## Open Questions & Questionnaire

- [x] Q: Should the plan include environment/infrastructure provisioning? → A: No — no separate infrastructure provisioning; deployment task will create Azure resources as needed.
- [x] Q: Should the plan include integration testing? → A: No — integration testing not requested; skipped.
- [x] Q: Should the plan include a security scan and CVE remediation task? → A: Yes — default security/CVE remediation included.
- [x] Q: Which Azure deployment target should the plan use? → A: Azure Container Apps (default) — the existing Dockerfile will be reused.
