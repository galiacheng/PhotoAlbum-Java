# Modernization Plan: PhotoAlbum Java Migration to Azure

**Project**: photo-album

---

## Technical Framework

- **Language**: Java 1.8
- **Framework**: Spring Boot 2.7.18
- **Build Tool**: Maven
- **Database**: Oracle DB (BLOB storage for photos, Oracle-specific SQL)
- **Key Dependencies**: Spring Data JPA, Thymeleaf, Commons IO, ojdbc8

---

## Overview

This migration upgrades and modernizes the PhotoAlbum application for Azure. The application currently runs on Java 8 with Spring Boot 2.7.18 and uses an Oracle database for storing photo metadata and binary data as BLOBs. The new architecture will:

- Upgrade the runtime to Java 21 and Spring Boot 3.x to benefit from long-term support, security patches, and modern language features
- Migrate the database from Oracle to Azure Database for PostgreSQL for better cost-effectiveness and native Azure integration
- Enable secure, credential-free authentication using Azure Managed Identity, eliminating hardcoded passwords in configuration files
- Update the container image to align with the upgraded Java 21 runtime

The migration follows a phased approach: upgrade first, then transform database and authentication, and finally update containerization.

---

## Migration Impact Summary

| Application | Original Service     | New Azure Service                    | Authentication | Comments                              |
|-------------|----------------------|--------------------------------------|----------------|---------------------------------------|
| photo-album | Java 8 / Spring Boot | Java 21 / Spring Boot 3.x           | N/A            | Runtime and framework upgrade         |
|             | 2.7.18               |                                      |                |                                       |
| photo-album | Oracle Database      | Azure Database for PostgreSQL        | Managed        | Migrate Oracle-specific SQL,          |
|             |                      |                                      | Identity       | drivers, and dialect                  |
| photo-album | Password-based DB    | Azure Managed Identity               | Managed        | Remove hardcoded credentials          |
|             | authentication       |                                      | Identity       | from configuration files              |
| photo-album | Dockerfile (Java 8)  | Dockerfile (Java 21)                 | N/A            | Update container base images          |

---

## Tasks

### Task 1: Upgrade Spring Boot to 3.x
Upgrade the project from Spring Boot 2.7.18 to Spring Boot 3.x, which includes upgrading Java from 1.8 to 21, Spring Framework from 5.x to 6.x, and migrating from javax.* to jakarta.* namespace.

### Task 2: Migrate Oracle Database to PostgreSQL
Migrate from Oracle DB to PostgreSQL, including converting Oracle-specific SQL functions (TO_CHAR, ROWNUM, RANK, analytical functions), updating JDBC drivers, JPA dialect, and BLOB handling for photo storage.

### Task 3: Migrate to Azure Database for PostgreSQL with Managed Identity
Migrate from password-based PostgreSQL authentication to Azure Managed Identity for secure, credential-free connectivity to Azure Database for PostgreSQL.

### Task 4: Update Containerization for Java 21
Update the existing Dockerfile to use Java 21 base images and ensure compatibility with the upgraded runtime for Azure deployment.
