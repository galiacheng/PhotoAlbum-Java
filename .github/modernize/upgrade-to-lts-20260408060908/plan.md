# Upgrade Plan

## Overview

Upgrade **PhotoAlbum-Java** to the latest LTS versions: **Java 21** and **Spring Boot 3.4** (with Spring Framework 6.x). This upgrade includes migration from `javax.*` to `jakarta.*` namespaces as required by Spring Boot 3.x / Jakarta EE.

## Tasks

See [tasks.json](./tasks.json) for the detailed task breakdown.

| # | ID | Description | Type | Status |
|---|-----|-------------|------|--------|
| 1 | 001-upgrade-java-spring-boot | Upgrade to Java 21 and Spring Boot 3.4 | upgrade | pending |

## Key Changes Included

- **JDK**: Upgrade to Java 21 (LTS)
- **Spring Boot**: Upgrade to 3.4.x
- **Spring Framework**: Upgrade to 6.x
- **Jakarta EE**: Migrate `javax.*` to `jakarta.*` namespace
- **Dependencies**: Update all compatible dependencies to versions supporting Java 21 / Spring Boot 3.x
