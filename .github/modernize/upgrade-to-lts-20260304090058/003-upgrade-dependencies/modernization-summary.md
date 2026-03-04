# Task 003: Upgrade Dependencies – Modernization Summary

## Overview
Updated all third-party (non-Spring) project dependencies to their latest compatible versions, ensuring compatibility with Java 21 and Spring Boot 3.4.7, and resolving known security vulnerabilities.

## Changes Made

### `pom.xml`

| Dependency | Previous Version | Updated Version | Notes |
|---|---|---|---|
| `commons-io:commons-io` | 2.11.0 | 2.21.0 | Multiple bug-fixes and security patches |
| `com.oracle.database.jdbc:ojdbc11` | 23.5.0.24.07 (BOM-managed) | 23.26.1.0.0 | Latest Oracle JDBC 11 driver; explicit version pinned |
| `com.h2database:h2` | 2.3.232 (BOM-managed) | 2.4.240 | Latest H2 in-memory DB for tests; explicit version pinned |

Spring Boot starter dependencies remain at **3.4.7** (stable release) – milestone versions (4.1.0-M2) were intentionally excluded.

## Verification

- **Build**: `mvn clean test` (Java 21) — **SUCCESS**
- **Unit Tests**: 1 test run, 0 failures, 0 errors — **PASSED**
- **Security Compliance**: All updated libraries are latest stable releases with no known CVEs as of the update date.
