
# Upgrade Java Project

## 🖥️ Project Information
- **Project path**: C:\Users\haiche\Documents\AppMod\PhotoAlbum-Java
- **Java version**: 17
- **Build tool type**: Maven
- **Build tool path**: C:\Users\haiche\.maven\maven-3.9.11\bin

## 🎯 Goals

- Upgrade Java to 17
- Upgrade Spring Boot to 3.5.x

## 🔀 Changes

### Test Changes
|     | Total | Passed | Failed | Skipped | Errors |
|-----|-------|--------|--------|---------|--------|
| Before | 1 | 1 | 0 | 0 | 0 |
| After | 1 | 1 | 0 | 0 | 0 |
### Dependency Changes


#### Upgraded Dependencies
| Dependency | Original Version | Current Version | Module |
|------------|------------------|-----------------|--------|
| org.springframework.boot:spring-boot-starter-web | 2.7.18 | 3.5.0 | photo-album |
| org.springframework.boot:spring-boot-starter-thymeleaf | 2.7.18 | 3.5.0 | photo-album |
| org.springframework.boot:spring-boot-starter-data-jpa | 2.7.18 | 3.5.0 | photo-album |
| com.oracle.database.jdbc:ojdbc8 | 21.5.0.0 | 23.7.0.25.01 | photo-album |
| org.springframework.boot:spring-boot-starter-validation | 2.7.18 | 3.5.0 | photo-album |
| org.springframework.boot:spring-boot-starter-json | 2.7.18 | 3.5.0 | photo-album |
| org.springframework.boot:spring-boot-starter-test | 2.7.18 | 3.5.0 | photo-album |
| com.h2database:h2 | 2.1.214 | 2.3.232 | photo-album |
| org.springframework.boot:spring-boot-devtools | 2.7.18 | 3.5.0 | photo-album |
| Java | 8 | 17 | Root Module |

### Code commits

All code changes have been committed to branch `002-modernization-plan`, here are the details:
6 files changed, 14 insertions(+), 14 deletions(-)

- a94d27f -- Upgrade project to use `Spring Boot 3.3.x`, `Java 17` using openrewrite.

- 3e7850a -- Upgrade Spring Boot from 3.3.13 to 3.4.3

- 71cae80 -- Upgrade Spring Boot from 3.4.3 to 3.5.0
### Potential Issues

#### CVEs
- commons-io:commons-io:
  - [**HIGH**][CVE-2024-47554](https://github.com/advisories/GHSA-78wr-2p64-hpwj): Apache Commons IO: Possible denial of service attack on untrusted input to XmlStreamReader
