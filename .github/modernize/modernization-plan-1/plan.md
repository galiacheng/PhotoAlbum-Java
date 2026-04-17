# Modernization Plan: PhotoAlbum-Java Azure Migration

**Project**: photo-album

---

## Technical Framework

- **Language**: Java 1.8
- **Framework**: Spring Boot 2.7.18 (Spring Framework 5.x)
- **Build Tool**: Maven
- **Database**: Oracle (ojdbc8, Oracle JDBC driver)
- **Key Dependencies**: Spring Data JPA, Hibernate,
  Thymeleaf, Commons IO, Spring Boot Validation

---

## Overview

This migration upgrades and modernizes the PhotoAlbum-Java
application for Azure. The application currently runs on
Java 8 with Spring Boot 2.7.18 and uses an Oracle database
to store photo metadata and binary image data. The new
architecture will:

- Upgrade to Spring Boot 3.x with Java 21 to move off
  end-of-life frameworks and gain modern language features
- Migrate from Oracle database to Azure Database for
  PostgreSQL for a fully managed, cost-effective database
  service
- Replace plaintext password-based authentication with
  Azure Managed Identity for secure, credential-free
  database connectivity
- Containerize the application with an updated Dockerfile
  suitable for the upgraded runtime

The migration follows a phased approach: upgrade first,
then transform database and authentication, then
containerize for Azure deployment.

---

## Migration Impact Summary

| Application | Original Service     | New Azure Service                  | Authentication | Comments                          |
|-------------|----------------------|------------------------------------|----------------|-----------------------------------|
| photo-album | Oracle Database      | Azure Database for PostgreSQL      | Managed Identity | Migrate Oracle-specific SQL       |
| photo-album | Password-based auth  | Azure Managed Identity             | Managed Identity | Credential-free DB connectivity   |
| photo-album | Java 8 / Spring 2.x | Java 21 / Spring Boot 3.x         | N/A            | Framework & language upgrade      |

---

## Tasks

### Task 1: Upgrade Spring Boot to 3.x (ID: 001-upgrade-spring-boot-3x)

**Type**: upgrade

Upgrade the project from Spring Boot 2.7.18 (Java 8) to
Spring Boot 3.x (Java 21). This includes upgrading Spring
Framework from 5.x to 6.x and migrating javax.* namespaces
to jakarta.* as required by Spring Boot 3.x.

### Task 2: Migrate Oracle to PostgreSQL (ID: 002-transform-migration-oracle-to-postgresql)

**Type**: transform

Migrate the application's data layer from Oracle database
to PostgreSQL. This includes converting Oracle-specific
native SQL queries (ROWNUM, TO_CHAR, RANK() OVER with
Oracle syntax), updating JDBC drivers, connection strings,
Hibernate dialect, and JPA configuration to target
PostgreSQL.

**Skill**: migration-oracle-to-postgresql

### Task 3: Managed Identity for PostgreSQL (ID: 003-transform-migration-postgresql-managed-identity)

**Type**: transform

Migrate from password-based database authentication to
Azure Managed Identity for connecting to Azure Database for
PostgreSQL. This eliminates plaintext credentials from
configuration files and follows Azure security best
practices.

**Skill**: migration-mi-postgresql-azure-sdk-public-cloud

### Task 4: Containerization (ID: 004-containerization)

**Type**: containerization

Update the existing Dockerfile to use JDK 21 base images
and ensure the containerized application is ready for
deployment to Azure.

### Task 5: Deployment (ID: 005-deployment)

**Type**: deployment

Deploy the modernized PhotoAlbum application to Azure.
