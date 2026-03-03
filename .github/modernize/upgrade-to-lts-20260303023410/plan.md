# Java 21 Upgrade Plan

## Overview
This plan outlines the steps to upgrade the PhotoAlbum-Java project to Java 21 and Spring Boot 3.4, ensuring compatibility with the latest LTS versions and modern Java ecosystem.

## Upgrade Targets
- **Java**: Version 21 (LTS)
- **Spring Boot**: Version 3.4
- **Spring Framework**: Version 6.x
- **Namespace Migration**: javax.* → jakarta.*

## Tasks
See `tasks.json` for the detailed task breakdown and execution order.

### Task Summary
1. **Java Version Upgrade** - Migrate to Java 21 with build configuration updates
2. **Spring Boot Upgrade** - Update to Spring Boot 3.4 and Spring Framework 6.x
3. **Dependency Updates** - Ensure all dependencies are compatible with Java 21 and Spring Boot 3.4

## Success Criteria
- Project builds successfully with Java 21
- All unit tests pass
- No security vulnerabilities in dependencies
- Code follows modern Java and Spring Boot best practices
