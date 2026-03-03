
# Upgrade Java Project

## 🖥️ Project Information
- **Project path**: C:\Users\haiche\AppData\Local\Temp\modernize-e2e-20260303-110057\PhotoAlbum-Java
- **Java version**: 21
- **Build tool type**: Maven
- **Build tool path**: C:\Users\haiche\.maven\apache-maven-3.9.11\bin

## 🎯 Goals

- Upgrade Java to 21

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
| Java | 8 | 21 | Root Module |

### Code commits

All code changes have been committed to branch `main`, here are the details:
4 files changed, 9 insertions(+), 9 deletions(-)

- a8b051b -- fix issues
### Potential Issues

#### CVEs
- commons-io:commons-io:2.11.0:
  - [**HIGH**][CVE-2024-47554](https://github.com/advisories/GHSA-78wr-2p64-hpwj): Apache Commons IO: Possible denial of service attack on untrusted input to XmlStreamReader

- com.h2database:h2:2.1.214:
  - [**HIGH**][CVE-2022-45868](https://github.com/advisories/GHSA-22wj-vf5f-wrvj): Password exposure in H2 Database
