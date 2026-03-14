# Upgrade Plan

## Overview

Upgrade the PhotoAlbum-Java project to the latest LTS versions: Java 21 and Spring Boot 3.4.

## Tasks

See `tasks.json` for detailed task breakdown.

### Task Summary

| ID | Description | Status |
|----|-------------|--------|
| 001-upgrade-java-spring-boot | Upgrade to Java 21 and Spring Boot 3.4 | Pending |

## Details

- **Java**: Upgrade to JDK 21 (latest LTS)
- **Spring Boot**: Upgrade to 3.4 (latest stable)
- **Spring Framework**: Upgrade to 6.x
- **Namespace migration**: Migrate `javax.*` to `jakarta.*` if needed
- **Build configuration**: Update Maven/Gradle to target Java 21
