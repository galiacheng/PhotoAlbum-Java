
# Upgrade Java Project

## 🖥️ Project Information
- **Project path**: /private/var/folders/53/mmzvzmx94mgbpc1xzcy76nsm0000gp/T/tmp.jW3k95c3HB/PhotoAlbum-Java
- **Java version**: 21
- **Build tool type**: Maven
- **Build tool path**: /opt/homebrew/Cellar/maven/3.9.10/bin

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

- 714376d -- fix issues
### Potential Issues

#### CVEs
- commons-io:commons-io:2.11.0:
  - [**HIGH**][CVE-2024-47554](https://github.com/advisories/GHSA-78wr-2p64-hpwj): Apache Commons IO: Possible denial of service attack on untrusted input to XmlStreamReader

- com.h2database:h2:2.1.214:
  - [**HIGH**][CVE-2022-45868](https://github.com/advisories/GHSA-22wj-vf5f-wrvj): Password exposure in H2 Database
