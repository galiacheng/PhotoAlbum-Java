# Modernization Summary: 001-transform-migration-s3-to-azure-blob-storage

## Task
Migrate photo binary data storage from Oracle Database BLOBs to Azure Blob Storage for scalable and cost-effective object storage.

## Changes Made

### New Files
| File | Description |
|------|-------------|
| `src/main/java/com/photoalbum/config/AzureBlobStorageConfig.java` | Spring `@Configuration` that creates a `BlobContainerClient` bean using `DefaultAzureCredential` (Managed Identity). Skipped under the `test` Spring profile via `@Profile("!test")`. |
| `src/main/java/com/photoalbum/service/BlobStorageService.java` | Service wrapping Azure Blob Storage operations: `uploadBlob`, `downloadBlob`, and `deleteBlob`. Conditionally created via `@ConditionalOnBean(BlobContainerClient.class)`. |

### Modified Files
| File | Change |
|------|--------|
| `pom.xml` | Added `azure-storage-blob` (12.27.1) and `azure-identity` (1.13.3) dependencies. |
| `src/main/java/com/photoalbum/model/Photo.java` | Removed the `photoData` (`@Lob byte[]`) field, its getter/setter, and the constructor that accepted binary data. Removed Oracle-specific `SYSTIMESTAMP` column definition from `uploaded_at` (value is always set in code). |
| `src/main/java/com/photoalbum/repository/PhotoRepository.java` | Removed `PHOTO_DATA` column reference from all five native SQL queries. |
| `src/main/java/com/photoalbum/service/impl/PhotoServiceImpl.java` | Injected `BlobStorageService`. Upload flow: reads bytes for dimension extraction, uploads to Azure Blob Storage, saves metadata to Oracle DB (with rollback on failure). Delete flow: deletes blob from Azure, then deletes metadata from Oracle DB. |
| `src/main/java/com/photoalbum/controller/PhotoFileController.java` | Injected `BlobStorageService`. Replaced BLOB-from-entity retrieval with `blobStorageService.downloadBlob(photo.getStoredFileName())`. |
| `src/main/resources/application.properties` | Added `azure.blob.storage.account-name` and `azure.blob.storage.container-name` properties (resolved from env vars `AZURE_STORAGE_ACCOUNT_NAME` / `AZURE_STORAGE_CONTAINER_NAME`). |
| `src/main/resources/application-docker.properties` | Added same Azure Blob Storage properties. |
| `src/test/resources/application-test.properties` | Set `azure.blob.storage.account-name=` to ensure the Azure config bean is not activated in tests. |
| `src/test/java/com/photoalbum/PhotoAlbumApplicationTests.java` | Added `@MockBean BlobStorageService` for test isolation without requiring Azure credentials. |

## Architecture After Migration

```
Upload:  MultipartFile → BlobStorageService.uploadBlob() → Azure Blob Storage
                       → PhotoRepository.save()           → Oracle DB (metadata only)

Serve:   GET /photo/{id} → PhotoRepository.findById()    → Oracle DB (metadata)
                         → BlobStorageService.downloadBlob() → Azure Blob Storage (binary)

Delete:  BlobStorageService.deleteBlob()                 → Azure Blob Storage
         PhotoRepository.delete()                        → Oracle DB
```

## Authentication
- Uses `DefaultAzureCredential` which automatically uses **Managed Identity** when deployed to Azure.
- No credentials or connection strings are stored in code or configuration.

## Configuration Required
| Property | Environment Variable | Description |
|----------|----------------------|-------------|
| `azure.blob.storage.account-name` | `AZURE_STORAGE_ACCOUNT_NAME` | Azure Storage account name |
| `azure.blob.storage.container-name` | `AZURE_STORAGE_CONTAINER_NAME` | Blob container name (default: `photos`) |

## Security Summary
- CodeQL analysis: **0 alerts found**
- No credentials committed to source code
- Managed Identity authentication eliminates need for shared secrets
