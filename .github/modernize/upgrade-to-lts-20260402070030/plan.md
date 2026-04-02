# Upgrade Plan

## Overview

Upgrade **PhotoAlbum-Java** to the latest LTS versions: **Java 21** and **Spring Boot 3.4** (Spring Framework 6.x), including migration from `javax.*` to `jakarta.*` namespaces.

## Tasks

See [tasks.json](./tasks.json) for detailed task breakdown.

| # | Task | Type | Status |
|---|------|------|--------|
| 001 | Upgrade to Java 21 and Spring Boot 3.4 | upgrade | pending |

## Notes

- Spring Boot 3.x requires a minimum of Java 17; the plan targets Java 21 (latest LTS).
- Spring Boot 3.x upgrades Spring Framework to 6.x and requires migrating `javax.*` imports to `jakarta.*`.
- All dependent libraries will be updated to versions compatible with Spring Boot 3.4 and Java 21.
