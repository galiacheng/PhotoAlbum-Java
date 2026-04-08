# Upgrade Plan

## Overview

Upgrade the PhotoAlbum-Java project to the latest LTS versions: **Java 21** and **Spring Boot 3.4** (with Spring Framework 6.x and Jakarta EE migration).

This upgrade includes:
- JDK upgrade to **Java 21** (latest LTS)
- Spring Boot upgrade to **3.4**
- Spring Framework upgrade to **6.x**
- Migration of `javax.*` packages to `jakarta.*` (Jakarta EE namespace)

## Tasks

See [tasks.json](./tasks.json) for the detailed task breakdown.

| # | Task | Type | Status |
|---|------|------|--------|
| 001 | Upgrade to Java 21 and Spring Boot 3.4 | upgrade | pending |

## Notes

- Spring Boot 3.x requires a minimum of Java 17; Java 21 (LTS) is the recommended target.
- The `javax.*` to `jakarta.*` namespace migration is included as part of the Spring Boot 3.x upgrade task.
- All existing unit tests must pass after the upgrade.
