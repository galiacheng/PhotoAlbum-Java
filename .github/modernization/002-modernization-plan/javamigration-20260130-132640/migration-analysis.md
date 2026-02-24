# Migration Analysis for PostgreSQL with Managed Identity

## Project: PhotoAlbum-Java
## Skill: migration-mi-postgresql-azure-sdk-public-cloud

### Current State
- **Database**: Oracle Database (ojdbc8)
- **Dialect**: org.hibernate.dialect.OracleDialect
- **Driver**: oracle.jdbc.OracleDriver
- **BLOB Storage**: Using @Lob annotation with byte[] for photo_data column
- **Connection**: Traditional username/password authentication

### Target State
- **Database**: Azure PostgreSQL
- **Dialect**: org.hibernate.dialect.PostgreSQLDialect
- **Driver**: org.postgresql.Driver
- **BLOB Storage**: PostgreSQL bytea type (compatible with @Lob byte[])
- **Connection**: Managed Identity using AzurePostgresqlAuthenticationPlugin

### Files Requiring Changes

#### 1. pom.xml (Dependency Changes)
- Remove: com.oracle.database.jdbc:ojdbc8
- Add: org.postgresql:postgresql (PostgreSQL JDBC driver)
- Add: com.azure:azure-identity-extensions (for managed identity support)

#### 2. application.properties (Database Configuration)
- Update: spring.datasource.url (Oracle → PostgreSQL format)
- Update: spring.datasource.driver-class-name (OracleDriver → org.postgresql.Driver)
- Update: spring.jpa.database-platform (OracleDialect → PostgreSQLDialect)
- Remove: hardcoded password
- Update: username to use managed identity name
- Add: authentication plugin configuration

#### 3. application-docker.properties (Docker Configuration)
- Similar changes as application.properties

#### 4. Photo.java (Entity Model)
- Update: @Column columnDefinition for file_size (NUMBER(19,0) → BIGINT)
- Update: @Column columnDefinition for uploaded_at (remove Oracle-specific DEFAULT SYSTIMESTAMP)
- Verify: @Lob byte[] is compatible with PostgreSQL bytea

#### 5. Code Files Using DriverManager (if any)
- Search for DriverManager.getConnection() usage
- Apply managed identity authentication plugin

### Migration Challenges
1. Oracle-specific SQL syntax in column definitions
2. BLOB vs bytea type handling
3. Default timestamp handling differences
4. Connection string format changes
5. Managed identity configuration

### Change Order (Dependency Graph)
1. pom.xml (dependencies first)
2. Photo.java (entity model)
3. application.properties
4. application-docker.properties
5. Any service/DAO files using DriverManager
