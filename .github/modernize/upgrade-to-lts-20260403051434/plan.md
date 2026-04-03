# Upgrade Plan

## Overview

Upgrade the PhotoAlbum-Java project to the latest LTS versions:
- **Java 21** (latest LTS)
- **Spring Boot 3.4** (includes Spring Framework 6.x and Jakarta EE migration)

The Spring Boot 3.x upgrade encompasses upgrading the JDK to 21, migrating `javax.*` packages to `jakarta.*`, and updating Spring Framework to 6.x - all handled in a single task.

## Tasks

See `tasks.json` for the detailed task breakdown.

| ID | Description |
|----|-------------|
| 001-upgrade-java-spring-boot | Upgrade to Java 21 and Spring Boot 3.4 |
