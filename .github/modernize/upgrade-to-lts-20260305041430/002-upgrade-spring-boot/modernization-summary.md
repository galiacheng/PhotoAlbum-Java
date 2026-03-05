# Task 002: Upgrade Spring Boot to 3.4.x

## Summary

Successfully upgraded Spring Boot from **2.7.18** to **3.4.5** using a milestone-based approach (2.7.18 → 3.3.13 → 3.4.5). The project builds successfully and all unit tests pass.

## Changes Made

### `pom.xml`
- Upgraded `spring-boot-starter-parent` from `2.7.18` to `3.4.5`

### `src/main/java/com/photoalbum/model/Photo.java`
- Migrated `javax.persistence.*` → `jakarta.persistence.*`
- Migrated `javax.validation.constraints.*` → `jakarta.validation.constraints.*`

### `src/main/java/com/photoalbum/controller/HomeController.java`
- Simplified `@RequestParam("files")` → `@RequestParam` (functionally equivalent; Spring infers parameter name `files` from method signature)

## Dependency Upgrades (transitive, via Spring Boot BOM)

| Dependency | Before | After |
|---|---|---|
| Spring Boot | 2.7.18 | 3.4.5 |
| Spring Framework | ~5.3.x | ~6.2.x |
| Hibernate ORM | ~5.6.x | ~6.6.x |
| Jakarta Persistence API | javax.persistence 2.x | jakarta.persistence 3.x |
| Jakarta Validation API | javax.validation 2.x | jakarta.validation 3.x |
| H2 Database (test) | 2.1.214 | 2.3.232 |
| Oracle JDBC (ojdbc8) | 21.5.0.0 | 23.5.0.24.07 |

## Success Criteria

| Criteria | Result |
|---|---|
| passBuild | ✅ |
| generateNewUnitTests | N/A (not required) |
| generateNewIntegrationTests | N/A (not required) |
| passUnitTests | ✅ (1/1 passed) |
| passIntegrationTests | N/A (not required) |
