# Modernization Summary: Upgrade Java and Spring Boot

**Task ID:** 001-upgrade-java-spring-boot  
**Status:** ✅ Completed Successfully

---

## Overview

Upgraded the PhotoAlbum Java application from Java 8 / Spring Boot 2.7.18 to **Java 21 / Spring Boot 3.4.5**, following a two-milestone approach via OpenRewrite automated migration recipes.

---

## Changes Made

### 1. `pom.xml`
- Upgraded `spring-boot-starter-parent` from `2.7.18` → `3.4.5`
- Updated `java.version` from `1.8` → `21`
- Updated `maven.compiler.source` from `8` → `21`
- Updated `maven.compiler.target` from `8` → `21`

### 2. `src/main/java/com/photoalbum/model/Photo.java`
- Migrated `javax.persistence.*` → `jakarta.persistence.*`
- Migrated `javax.validation.constraints.*` → `jakarta.validation.constraints.*`

### 3. `src/main/java/com/photoalbum/service/impl/PhotoServiceImpl.java`
- Replaced `String.format(...)` with `"...".formatted(...)` (Java 15+ idiom)
- Replaced `!Optional.isPresent()` with `Optional.isEmpty()` (Java 11+ idiom)
- Replaced `List.get(0)` with `List.getFirst()` (Java 21+ idiom)

### 4. `src/main/java/com/photoalbum/controller/DetailController.java`
- Replaced `!Optional.isPresent()` with `Optional.isEmpty()`

### 5. `src/main/java/com/photoalbum/controller/PhotoFileController.java`
- Replaced `!Optional.isPresent()` with `Optional.isEmpty()`

### 6. `src/main/java/com/photoalbum/controller/HomeController.java`
- Retained explicit `@RequestParam("files")` name (reverted OpenRewrite's removal of explicit name — required for Spring Framework 6.2 compatibility where implicit parameter name resolution is stricter)

### 7. `Dockerfile`
- Updated build stage from `maven:3.9.6-eclipse-temurin-8` → `maven:3.9.6-eclipse-temurin-21`
- Updated runtime stage from `eclipse-temurin:8-jre` → `eclipse-temurin:21-jre`

---

## Dependency Version Changes (Transitive via Spring Boot BOM)

| Dependency | Before | After |
|---|---|---|
| spring-boot-starter-parent | 2.7.18 | 3.4.5 |
| spring-boot-starter-web | 2.7.18 | 3.4.5 |
| spring-boot-starter-thymeleaf | 2.7.18 | 3.4.5 |
| spring-boot-starter-data-jpa | 2.7.18 | 3.4.5 |
| spring-boot-starter-validation | 2.7.18 | 3.4.5 |
| spring-boot-starter-test | 2.7.18 | 3.4.5 |
| spring-boot-devtools | 2.7.18 | 3.4.5 |
| com.oracle.database.jdbc:ojdbc8 | 21.5.0.0 | 23.5.0.24.07 |
| com.h2database:h2 | 2.1.214 | 2.3.232 |
| Java (JDK) | 8 | 21 |
| Spring Framework (transitive) | 5.3.31 | 6.2.x |

---

## Validation Results

| Check | Result |
|---|---|
| Build | ✅ Passed |
| Unit Tests | ✅ Passed (1/1) |
| CVE Scan (new) | ✅ No new critical/high CVEs introduced |

### Pre-existing CVE Note
- `commons-io:commons-io:2.11.0` has a pre-existing HIGH CVE (CVE-2024-47554). This was present before the upgrade and is not in scope for this task.

---

## Migration Approach

The upgrade was performed in two milestones using [OpenRewrite](https://docs.openrewrite.org/) automated recipes:

1. **Milestone 1**: Applied `org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_3` + `org.openrewrite.java.migrate.UpgradeToJava21` → upgraded to Spring Boot 3.3.13 / Java 21
2. **Milestone 2**: Manually bumped Spring Boot parent to `3.4.5`
