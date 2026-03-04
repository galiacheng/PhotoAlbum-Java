# Modernization Summary: 001-upgrade-java-version

## Task
Upgrade Java Development Kit (JDK) to version 21 (LTS)

## Changes Made

### 1. `pom.xml`
- Upgraded Spring Boot parent from `2.7.18` → `3.4.7` (required for Java 21 LTS support)
- Updated `java.version` from `1.8` → `21`
- Updated `maven.compiler.source` from `8` → `21`
- Updated `maven.compiler.target` from `8` → `21`

### 2. `src/main/java/com/photoalbum/model/Photo.java`
- Migrated `javax.persistence.*` → `jakarta.persistence.*`
- Migrated `javax.validation.constraints.*` → `jakarta.validation.constraints.*`

  This change is required because Spring Boot 3 / Jakarta EE 10 renamed the `javax.*` namespace to `jakarta.*`.

### 3. `Dockerfile`
- Updated build stage base image from `maven:3.9.6-eclipse-temurin-8` → `maven:3.9.6-eclipse-temurin-21`
- Updated runtime base image from `eclipse-temurin:8-jre` → `eclipse-temurin:21-jre`

## Verification

| Criterion            | Result  |
|----------------------|---------|
| Build passes         | ✅ PASS |
| Unit tests pass      | ✅ PASS (1/1) |
| Integration tests    | N/A (not required) |
| Security check       | N/A (not required) |

Build output: `BUILD SUCCESS` — `Tests run: 1, Failures: 0, Errors: 0, Skipped: 0`
