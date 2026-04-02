# Modernization Summary: Upgrade to Java 21 and Spring Boot 3.4

## Task ID
`001-upgrade-java-spring-boot`

## Overview
Upgraded the PhotoAlbum Java application from Java 8 / Spring Boot 2.7.18 to Java 21 / Spring Boot 3.4.5, including Spring Framework 6.x and full Jakarta EE namespace migration.

## Changes Made

### `pom.xml`
- Upgraded Spring Boot parent from `2.7.18` to `3.4.5`
- Updated `java.version` from `1.8` to `21`
- Updated `maven.compiler.source` and `maven.compiler.target` from `8` to `21`
- Spring Framework upgraded transitively from 5.x to 6.x via Spring Boot 3.4.5

### `src/main/java/com/photoalbum/model/Photo.java`
- Migrated `javax.persistence.*` → `jakarta.persistence.*`
- Migrated `javax.validation.constraints.*` → `jakarta.validation.constraints.*`

### `Dockerfile`
- Updated build image from `maven:3.9.6-eclipse-temurin-8` to `maven:3.9.6-eclipse-temurin-21`
- Updated runtime image from `eclipse-temurin:8-jre` to `eclipse-temurin:21-jre`

## Notes
- `javax.imageio.ImageIO` in `PhotoServiceImpl.java` was intentionally left unchanged — it is part of the Java SE standard library and is not subject to the Jakarta EE namespace migration.
- All other `javax.*` references have been migrated to `jakarta.*` as required by Jakarta EE 9+ (used by Spring Framework 6.x / Spring Boot 3.x).

## Verification
- **Build**: ✅ `mvn clean test` passes successfully
- **Unit Tests**: ✅ 1 test run, 0 failures, 0 errors
