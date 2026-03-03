# Modernization Summary: Upgrade to Java 21 LTS

## Task: 001-upgrade-java-version

**Description:** Upgrade JDK to version 21 (LTS), update build configuration, migrate deprecated APIs, and ensure compatibility with Java 21.

## Changes Made

### 1. `pom.xml`
- Upgraded Spring Boot parent from `2.7.18` to `3.2.5` (required for official Java 21 LTS support)
- Updated `java.version` from `1.8` to `21`
- Updated `maven.compiler.source` from `8` to `21`
- Updated `maven.compiler.target` from `8` to `21`

### 2. `src/main/java/com/photoalbum/model/Photo.java`
- Migrated `javax.persistence.*` → `jakarta.persistence.*` (Spring Boot 3.x / Jakarta EE 10 namespace change)
- Migrated `javax.validation.constraints.*` → `jakarta.validation.constraints.*`

### 3. `Dockerfile`
- Updated build image from `maven:3.9.6-eclipse-temurin-8` to `maven:3.9.6-eclipse-temurin-21`
- Updated runtime image from `eclipse-temurin:8-jre` to `eclipse-temurin:21-jre`

## Rationale

Spring Boot 2.7.x only officially supports up to Java 17. To properly support Java 21 as an LTS target, Spring Boot 3.2.x (which bundles Spring Framework 6 and Hibernate 6) is required. Spring Boot 3.x moved from the `javax.*` namespace to `jakarta.*` as part of the Jakarta EE 10 migration.

## Verification

- **Build:** `mvn clean test` — **SUCCESS**
- **Unit Tests:** 1 test run, 0 failures, 0 errors — **PASSED**
