# Modernization Summary: 001-upgrade-spring-boot-3x

## Task
Upgrade the application from Spring Boot 2.7.18 to Spring Boot 3.3.6.

## Changes Made

### 1. `pom.xml`
- Upgraded `spring-boot-starter-parent` from `2.7.18` → `3.3.6`
- Updated `java.version` from `1.8` → `17`
- Updated `maven.compiler.source` and `maven.compiler.target` from `8` → `17`

This pulls in:
- Spring Framework 6.1.x (from Spring Framework 5.3.x)
- Hibernate ORM 6.5.x (from Hibernate 5.6.x)
- Jakarta EE 10 APIs (replacing JavaEE / javax.* APIs)

### 2. `src/main/java/com/photoalbum/model/Photo.java`
- Migrated `javax.persistence.*` → `jakarta.persistence.*`
- Migrated `javax.validation.constraints.NotBlank` → `jakarta.validation.constraints.NotBlank`
- Migrated `javax.validation.constraints.NotNull` → `jakarta.validation.constraints.NotNull`
- Migrated `javax.validation.constraints.Positive` → `jakarta.validation.constraints.Positive`
- Migrated `javax.validation.constraints.Size` → `jakarta.validation.constraints.Size`

> **Note:** `javax.imageio.ImageIO` in `PhotoServiceImpl.java` was intentionally left unchanged — it is part of the Java SE standard library (`java.desktop` module), not a Jakarta EE API.

## Verification

| Check | Result |
|-------|--------|
| `mvn clean test` | ✅ BUILD SUCCESS |
| Unit tests (`PhotoAlbumApplicationTests.contextLoads`) | ✅ PASSED (1/1) |

## Compatibility Notes
- All existing Spring Boot starters (`web`, `thymeleaf`, `data-jpa`, `validation`, `test`) are compatible with Spring Boot 3.3.6.
- Oracle JDBC driver (`ojdbc8`) version is managed by the Spring Boot BOM and remains compatible.
- H2 in-memory database used for testing is compatible with Hibernate 6.x auto-DDL.
