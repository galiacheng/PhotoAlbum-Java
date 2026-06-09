package com.photoalbum.service;

import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.options.BlobParallelUploadOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;

/**
 * Service for managing photo binaries in Azure Blob Storage using managed identity.
 */
@Service
public class BlobStorageService {

    private static final Logger logger = LoggerFactory.getLogger(BlobStorageService.class);

    private final BlobServiceClient blobServiceClient;
    private final String containerName;

    public BlobStorageService(
            BlobServiceClient blobServiceClient,
            @Value("${azure.storage.container-name}") String containerName) {
        this.blobServiceClient = blobServiceClient;
        this.containerName = containerName;
    }

    /**
     * Ensures the blob container exists. Called once at application startup.
     */
    @PostConstruct
    public void initContainer() {
        blobServiceClient.getBlobContainerClient(containerName).createIfNotExists();
        logger.info("Azure Blob Storage container '{}' is ready", containerName);
    }

    /**
     * Uploads photo binary data to Azure Blob Storage.
     *
     * @param blobName    unique name for the blob (e.g. UUID + extension)
     * @param data        raw byte content of the photo
     * @param contentType MIME type of the photo
     */
    public void uploadBlob(String blobName, byte[] data, String contentType) {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        BlobClient blobClient = containerClient.getBlobClient(blobName);

        BlobHttpHeaders headers = new BlobHttpHeaders().setContentType(contentType);
        BlobParallelUploadOptions options = new BlobParallelUploadOptions(BinaryData.fromBytes(data))
                .setHeaders(headers);

        try {
            blobClient.uploadWithResponse(options, null, Context.NONE);
        } catch (Exception ex) {
            logger.error("Failed to upload blob {} to Azure Blob Storage", blobName, ex);
            throw new RuntimeException("Error uploading photo to Azure Blob Storage", ex);
        }

        logger.info("Uploaded blob {} ({} bytes) to container {}", blobName, data.length, containerName);
    }

    /**
     * Downloads photo binary data from Azure Blob Storage.
     *
     * @param blobName name of the blob to download
     * @return raw byte content of the photo
     */
    public byte[] downloadBlob(String blobName) {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        BlobClient blobClient = containerClient.getBlobClient(blobName);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            blobClient.downloadStream(outputStream);
            byte[] data = outputStream.toByteArray();
            logger.info("Downloaded blob {} ({} bytes) from container {}", blobName, data.length, containerName);
            return data;
        } catch (Exception ex) {
            logger.error("Failed to download blob {} from Azure Blob Storage", blobName, ex);
            throw new RuntimeException("Error downloading photo from Azure Blob Storage", ex);
        }
    }

    /**
     * Deletes a blob from Azure Blob Storage.
     *
     * @param blobName name of the blob to delete
     */
    public void deleteBlob(String blobName) {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        BlobClient blobClient = containerClient.getBlobClient(blobName);

        try {
            blobClient.deleteIfExists();
            logger.info("Deleted blob {} from container {}", blobName, containerName);
        } catch (Exception ex) {
            logger.error("Failed to delete blob {} from Azure Blob Storage", blobName, ex);
            throw new RuntimeException("Error deleting photo from Azure Blob Storage", ex);
        }
    }
}
