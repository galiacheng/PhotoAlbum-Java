
# Upgrade Java Project

## 🖥️ Project Information
- **Project path**: /private/var/folders/53/mmzvzmx94mgbpc1xzcy76nsm0000gp/T/tmp.jW3k95c3HB/PhotoAlbum-Java
- **Java version**: 17
- **Build tool type**: Maven
- **Build tool path**: /opt/homebrew/Cellar/maven/3.9.10/bin

## 🎯 Goals

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

### Code commits

All code changes have been committed to branch `main`, here are the details:
3 files changed, 7 insertions(+), 7 deletions(-)

- 158c7d4 -- Upgrade Spring Boot to 3.3.13 and migrate javax.* to jakarta.*

- fec1801 -- Upgrade Spring Boot to 3.4.3

- 7ffcf5f -- Upgrade Spring Boot to 3.5.0
### Potential Issues

#### CVEs
- commons-io:commons-io:2.11.0:
  - [**HIGH**][CVE-2024-47554](https://github.com/advisories/GHSA-78wr-2p64-hpwj): Apache Commons IO: Possible denial of service attack on untrusted input to XmlStreamReader
