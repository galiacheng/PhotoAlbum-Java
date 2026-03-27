
# Upgrade Java Project

## 🖥️ Project Information
- **Project path**: C:\Users\haiche\Documents\AppMod\PhotoAlbum-Java
- **Java version**: 21
- **Build tool type**: Maven
- **Build tool path**: C:\Users\haiche\.maven\apache-maven-3.9.11\bin

## 🎯 Goals

- Upgrade Java to 21
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
| org.springframework.boot:spring-boot-starter-web | 2.7.18 | 3.5.3 | photo-album |
| org.springframework.boot:spring-boot-starter-thymeleaf | 2.7.18 | 3.5.3 | photo-album |
| org.springframework.boot:spring-boot-starter-data-jpa | 2.7.18 | 3.5.3 | photo-album |
| com.oracle.database.jdbc:ojdbc8 | 21.5.0.0 | 23.7.0.25.01 | photo-album |
| org.springframework.boot:spring-boot-starter-validation | 2.7.18 | 3.5.3 | photo-album |
| org.springframework.boot:spring-boot-starter-json | 2.7.18 | 3.5.3 | photo-album |
| org.springframework.boot:spring-boot-starter-test | 2.7.18 | 3.5.3 | photo-album |
| com.h2database:h2 | 2.1.214 | 2.3.232 | photo-album |
| org.springframework.boot:spring-boot-devtools | 2.7.18 | 3.5.3 | photo-album |
| Java | 8 | 21 | Root Module |

### Code commits

All code changes have been committed to branch `appmod/java-upgrade-20260326081509`, here are the details:
6 files changed, 16 insertions(+), 16 deletions(-)

- ced9112 -- Upgrade Spring Boot to 3.3.13 and Java to 21 via OpenRewrite

- c820d4e -- Upgrade Spring Boot to 3.4.7 (milestone 2)

- 3dabda0 -- Upgrade Spring Boot to 3.5.3 (milestone 3)
### Potential Issues

#### CVEs
- commons-io:commons-io:2.11.0:
  - [**HIGH**][CVE-2024-47554](https://github.com/advisories/GHSA-78wr-2p64-hpwj): Apache Commons IO: Possible denial of service attack on untrusted input to XmlStreamReader
