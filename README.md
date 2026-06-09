# Photo Album Application - Java Spring Boot with PostgreSQL

A photo gallery application built with Spring Boot and PostgreSQL, featuring drag-and-drop upload, responsive gallery view, and full-size photo details with navigation.

## Features

- 📤 Photo upload (drag-and-drop or file picker)
- 🖼️ Responsive gallery and detail view
- 📊 Metadata display (size, dimensions, upload time)
- ✅ File type and size validation
- 🗄️ Binary photo storage in PostgreSQL (`bytea`)
- 🗑️ Photo deletion and navigation

## Technology Stack

- Spring Boot 2.7.18 (Java 8)
- PostgreSQL 16
- Thymeleaf
- Maven
- Docker & Docker Compose

## Quick Start

```bash
docker-compose up --build -d
```

This starts PostgreSQL and the Spring Boot app. Open http://localhost:8080.

## Database Configuration

### Default app profile (Azure PostgreSQL + Managed Identity)

`src/main/resources/application.properties` uses a PostgreSQL JDBC URL with managed identity plugin:

- `user=${MANAGED_IDENTITY_NAME}`
- `sslmode=require`
- `authenticationPluginClassName=com.azure.identity.extensions.jdbc.postgresql.AzurePostgresqlAuthenticationPlugin`
- `azure.managedIdentityEnabled=true`
- `azure.clientId=${MANAGED_IDENTITY_CLIENT_ID}`

An example service principal URL is included as a comment in the same file.

### Docker profile (local PostgreSQL)

`src/main/resources/application-docker.properties` points to:

- URL: `jdbc:postgresql://postgres-db:5432/photoalbum`
- Username/password: `photoalbum/photoalbum`

## Build and Test

```bash
mvn clean test
```

## Project Structure

```
PhotoAlbum-Java/
├── src/
├── docker-compose.yml
├── Dockerfile
├── pom.xml
└── README.md
```
