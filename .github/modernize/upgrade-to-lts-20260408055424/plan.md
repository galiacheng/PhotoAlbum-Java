# Upgrade Plan

## Overview

Upgrade **PhotoAlbum-Java** to the latest Java LTS versions: **Java 21** and **Spring Boot 3.4** (with Spring Framework 6.x and Jakarta EE namespace migration).

## Tasks

See `tasks.json` for the detailed task breakdown.

| # | Task | Description |
|---|------|-------------|
| 001 | upgrade-java-spring-boot | Upgrade to Java 21, Spring Boot 3.4, Spring Framework 6.x, and migrate javax.* to jakarta.* |

## Notes

- Spring Boot 3.x requires a minimum of Java 17; this plan targets Java 21 (latest LTS).
- The Spring Boot 3.x upgrade includes: Spring Framework 6.x upgrade and Jakarta EE namespace migration (javax.* to jakarta.*).
- All dependent libraries will be updated to versions compatible with Spring Boot 3.4 and Java 21.
