# Modernization Plan: PhotoAlbum Java Azure Migration

**Project**: photo-album

---

## Technical Framework

- **Language**: Java 1.8
- **Framework**: Spring Boot 2.7.18
- **Build Tool**: Maven
- **Database**: Oracle (ojdbc8)
- **Key Dependencies**: Spring Data JPA, Thymeleaf,
  Hibernate (OracleDialect), Commons IO

---

## Overview

> This migration upgrades the PhotoAlbum application
> from legacy Java 8 / Spring Boot 2.7.18 to the latest
> supported versions and migrates the Oracle database to
> Azure Database for PostgreSQL. The application currently
> runs on an end-of-support Java and Spring Boot stack
> with an Oracle database. The new architecture will:
>
> - Upgrade to Java 21 and Spring Boot 3.x for long-term
>   support, security patches, and modern features
> - Migrate from Oracle database to Azure Database for
>   PostgreSQL for a fully managed, cost-effective
>   cloud-native database service
> - Enable secure authentication using Azure Managed
>   Identity, eliminating hardcoded credentials
>
> The migration follows a phased approach: first upgrading
> the runtime and framework, then migrating the database
> to Azure.

---

## Migration Impact Summary

| Application | Original Service        | New Azure Service                | Authentication   | Comments                          |
|-------------|-------------------------|----------------------------------|------------------|-----------------------------------|
| photo-album | Java 1.8 /              | Java 21 /                        | N/A              | Upgrade runtime                   |
|             | Spring Boot 2.7.18      | Spring Boot 3.x                  |                  | and framework                     |
| photo-album | Oracle Database          | Azure Database for PostgreSQL    | Managed Identity | Migrate database                  |
|             |                          |                                  |                  | from Oracle to PostgreSQL         |
