# Modernization Plan: PhotoAlbum-Java Migration to Azure

**Project**: PhotoAlbum-Java
**Plan Name**: modernization-plan-09

---

## Technical Framework

- **Language**: Java 8
- **Framework**: Spring Boot 2.7.18
- **Build Tool**: Maven 3.9
- **Database**: Oracle Database (ojdbc8, Oracle Free 23ai)
- **Key Dependencies**: Spring Data JPA, Hibernate (OracleDialect), Spring MVC, Thymeleaf

---

## Overview

This migration moves the PhotoAlbum-Java application from Oracle Database to Azure-managed
services. The application currently stores photo metadata and binary photo data as BLOBs
in Oracle Database, with plaintext credentials in configuration files. The new
architecture will:

- Replace Oracle Database with Azure Database for PostgreSQL for structured photo
  metadata, eliminating licensing overhead and gaining cloud-native scalability.
- Migrate photo binary (BLOB) storage from the database to Azure Blob Storage for
  cost-effective, scalable, and durable photo storage.
- Adopt Azure Managed Identity for database authentication, removing all plaintext
  credentials from configuration.

The migration follows a phased approach: database schema migration first, then photo
binary storage migration, followed by authentication hardening, and finally
containerization for Azure deployment.

---

## Migration Impact Summary

| Application    | Original Service         | New Azure Service                  | Authentication   | Comments                                  |
|----------------|--------------------------|------------------------------------|------------------|-------------------------------------------|
| PhotoAlbum     | Oracle Database          | Azure Database for PostgreSQL      | Managed Identity | Migrate schema and queries from Oracle    |
| PhotoAlbum     | Oracle DB BLOB Storage   | Azure Blob Storage                 | Managed Identity | Move photo binary data out of the database|
| PhotoAlbum     | Password-based DB auth   | Azure Managed Identity (PostgreSQL)| Managed Identity | Replace plaintext credentials in config   |

---

## Migration Tasks

### Task 1 — Migrate Oracle Database to Azure Database for PostgreSQL

Migrate the Oracle Database schema, JPA/Hibernate configuration, and all database queries
to work with Azure Database for PostgreSQL. This includes replacing Oracle-specific
dialect settings, JDBC driver, and any Oracle-specific SQL constructs.

### Task 2 — Migrate Photo Binary Storage to Azure Blob Storage

Migrate photo binary data (currently stored as BLOBs in the Oracle database) to Azure
Blob Storage. Update the application to upload photos to Azure Blob Storage and retrieve
them via secure URLs instead of loading binary data from the database.

### Task 3 — Migrate Database Authentication to Azure Managed Identity

Replace the plaintext username/password database credentials with Azure Managed Identity
authentication for the PostgreSQL connection. This removes credentials from configuration
files and follows Azure security best practices.

### Task 4 — Update Containerization for Azure Deployment

Review and update the existing Dockerfile and container configuration to ensure the
application is production-ready for deployment to Azure container services.

---
