# Modernization Plan: modernization-plan-0420

**Project**: photo-album

---

## Technical Framework

- **Language**: Java 1.8
- **Framework**: Spring Boot 2.7.18
- **Build Tool**: Maven
- **Database**: Oracle (ojdbc8, photos stored as BLOBs)
- **Key Dependencies**: Spring Data JPA, Spring Web, Thymeleaf, Commons IO

---

## Overview

This migration upgrades the Photo Album application from a legacy Java 8 / Spring Boot 2.x stack to a modern, Azure-ready architecture. The application currently uses Oracle database for relational data and photo BLOB storage, runs on Java 8, and uses Spring Boot 2.7.18 which has reached end of OSS support. The new architecture will:

- Upgrade to Java 21 and Spring Boot 3.x for long-term support and modern language features
- Migrate from Oracle database to Azure Database for PostgreSQL for a fully managed, cost-effective database service
- Use managed identity for secure, passwordless database authentication, eliminating plaintext credentials in configuration files
- Update containerization to use the modernized Java 21 runtime

The migration follows a phased approach: upgrade the runtime and framework first, then transform the database layer, and finally update containerization.

---

## Migration Impact Summary

| Application | Original Service | New Azure Service             | Authentication   | Comments                   |
|-------------|------------------|-------------------------------|------------------|----------------------------|
| photo-album | Java 8 /         | Java 21 /                     | N/A              | Spring Boot 3.x upgrade   |
|             | Spring Boot 2.7  | Spring Boot 3.x               |                  | includes Jakarta EE        |
| photo-album | Oracle DB        | Azure Database for PostgreSQL | Managed Identity | Migrate Oracle-specific    |
|             |                  |                               |                  | SQL and JDBC driver        |
| photo-album | Docker (JDK 8)   | Docker (JDK 21)               | N/A              | Update existing Dockerfile |
