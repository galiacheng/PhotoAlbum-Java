# Upgrade Plan

## Overview

Upgrade the PhotoAlbum-Java project to the latest LTS versions: **Java 21** and **Spring Boot 3.4** (with Spring Framework 6.x and Jakarta EE namespace migration).

## Tasks

See `tasks.json` for the detailed task breakdown.

| # | Task | Type | Status |
|---|------|------|--------|
| 001 | Upgrade to Java 21 and Spring Boot 3.4 | upgrade | success |

## Key Changes Included

- **JDK**: Upgrade to Java 21 (latest LTS)
- **Spring Boot**: Upgrade to 3.4.x
- **Spring Framework**: Upgrade to 6.x (included with Spring Boot 3.x)
- **Jakarta EE Migration**: Migrate `javax.*` imports to `jakarta.*` namespace
- **Build configuration**: Update Maven/Gradle to target Java 21
