
# Upgrade Java Project

## 🖥️ Project Information
- **Project path**: C:\Users\haiche\Documents\AppMod\PhotoAlbum-Java
- **Java version**: 21
- **Build tool type**: Maven
- **Build tool path**: C:\Users\haiche\.maven\apache-maven-3.9.11\bin

## 🎯 Goals

- Upgrade Java to 21
- Upgrade Spring Boot to 3.4.x

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
| org.springframework.boot:spring-boot-starter-web | 2.7.18 | 3.4.5 | photo-album |
| org.springframework.boot:spring-boot-starter-thymeleaf | 2.7.18 | 3.4.5 | photo-album |
| org.springframework.boot:spring-boot-starter-data-jpa | 2.7.18 | 3.4.5 | photo-album |
| org.springframework.boot:spring-boot-starter-validation | 2.7.18 | 3.4.5 | photo-album |
| org.springframework.boot:spring-boot-starter-json | 2.7.18 | 3.4.5 | photo-album |
| org.springframework.boot:spring-boot-starter-test | 2.7.18 | 3.4.5 | photo-album |
| com.h2database:h2 | 2.1.214 | 2.3.232 | photo-album |
| org.springframework.boot:spring-boot-devtools | 2.7.18 | 3.4.5 | photo-album |
| Java | 8 | 21 | Root Module |

#### Added Dependencies
|   Dependency   | Version | Module |
|----------------|---------|--------|
| com.oracle.database.jdbc:ojdbc11 | 23.5.0.24.07 | photo-album |

#### Removed Dependencies
|   Dependency   | Version | Module |
|----------------|---------|--------|
| com.oracle.database.jdbc:ojdbc8 | 21.5.0.0 | photo-album |

### Code commits

All code changes have been committed to branch `main`, here are the details:
6 files changed, 17 insertions(+), 17 deletions(-)

- d44495b -- Milestone 2: Upgrade Spring Boot to 3.4.5

- 48a2e39 -- fix issues
### Potential Issues

#### CVEs
- commons-io:commons-io:2.11.0:
  - [**HIGH**][CVE-2024-47554](https://github.com/advisories/GHSA-78wr-2p64-hpwj): Apache Commons IO: Possible denial of service attack on untrusted input to XmlStreamReader
