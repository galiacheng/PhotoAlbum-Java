# Modernization Plan: Photo Album Azure Migration

**Project**: photo-album

---

## Technical Framework

- **Language**: Java 1.8 (target: Java 21)
- **Framework**: Spring Boot 2.7.18 (target: Spring Boot 3.x), Spring Framework 5.x (target: 6.x)
- **Build Tool**: Maven
- **Database**: Oracle DB (target: Azure Database for PostgreSQL)
- **Key Dependencies**: Spring Data JPA, Hibernate, Spring Boot Starter Web, Thymeleaf, Commons IO

---

## Overview

This migration modernizes the Photo Album application from a legacy Java/Spring Boot stack to a cloud-ready architecture on Azure. The application currently runs on Java 8 and Spring Boot 2.7.18 (End of OSS Support), uses Oracle Database with plaintext credentials embedded in configuration files, and stores binary photo data directly in the database. The new architecture will:

- Upgrade to Java 21 and Spring Boot 3.x for long-term support, modern APIs, and Jakarta EE namespace alignment
- Migrate from Oracle Database to Azure Database for PostgreSQL with managed identity for secure, credential-free authentication
- Eliminate plaintext credentials by leveraging Azure Key Vault for secrets management
- Update the existing Dockerfile to use the Java 21 runtime after the upgrade

The migration follows a phased approach: first upgrading the runtime and framework, then migrating the database and securing credentials, and finally updating containerization for the new Java version.

---

## Migration Impact Summary

| Application | Original Service         | New Azure Service                    | Authentication   | Comments                              |
|-------------|--------------------------|--------------------------------------|------------------|---------------------------------------|
| photo-album | Java 8 / Spring Boot 2.x | Java 21 / Spring Boot 3.x            | N/A              | Mandatory: legacy version, EOL        |
| photo-album | Oracle DB                | Azure Database for PostgreSQL        | Managed Identity | Mandatory: Oracle migration to Azure  |
| photo-album | Plaintext DB credentials | Azure Key Vault                      | Managed Identity | Potential: remove hardcoded passwords |

---

## Modernization Tasks

### Upgrade Tasks

- **001-upgrade-spring-boot-3**: Upgrade from Spring Boot 2.7.18 / Java 8 to Spring Boot 3.x / Java 21, including the required Jakarta EE namespace migration (`javax.*` → `jakarta.*`)

### Migration Tasks

- **002-transform-oracle-to-postgresql**: Migrate Oracle-specific database code, JPA mappings, SQL dialects, and schema definitions to PostgreSQL
- **003-transform-mi-postgresql**: Reconfigure the PostgreSQL connection to use Azure Managed Identity for secure, credential-free authentication against Azure Database for PostgreSQL
- **004-transform-keyvault-credentials**: Migrate plaintext credentials found in configuration files to Azure Key Vault

### Containerization

- **005-containerization**: Update the existing `Dockerfile` to use the Java 21 runtime image, aligned with the upgraded application
