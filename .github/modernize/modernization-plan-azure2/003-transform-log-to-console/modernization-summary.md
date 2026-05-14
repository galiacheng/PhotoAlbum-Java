# 003-transform-log-to-console

## Summary of changes
- Added `src/main/resources/logback-spring.xml` to enforce console-only logging.
- Configured Logback to include Spring Boot console appender and removed any file-appender usage by design.
- Preserved existing logger names and levels via property-based logger level mappings:
  - `com.photoalbum`
  - `org.springframework.web`
  - `org.hibernate.SQL`
- Kept root logger level configurable via `logging.level.root` with `INFO` default.

## Validation
- Build and unit tests pass after migration.
- Consistency check completed with no Critical or Major issues.
