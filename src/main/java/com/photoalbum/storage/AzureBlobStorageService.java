package com.photoalbum.storage;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service for Azure Blob Storage operations (upload, download, delete)
 */
@Service
public class AzureBlobStorageService {

    private static final Logger logger = LoggerFactory.getLogger(AzureBlobStorageService.class);

    private final BlobContainerClient containerClient;

    public AzureBlobStorageService(
            BlobServiceClient blobServiceClient,
            @Value("${azure.storage.container-name:photos}") String containerName) {
        this.containerClient = blobServiceClient.getBlobContainerClient(containerName);
        containerClient.createIfNotExists();
        logger.info("Azure Blob Storage container '{}' is ready", containerName);
    }

    /**
     * Upload photo bytes to Azure Blob Storage
     */
    public void uploadBlob(String blobName, byte[] data, String contentType) {
        BlobClient blobClient = containerClient.getBlobClient(blobName);
        blobClient.upload(BinaryData.fromBytes(data), true);
        blobClient.setHttpHeaders(new BlobHttpHeaders().setContentType(contentType));
        logger.debug("Uploaded blob '{}' ({} bytes)", blobName, data.length);
    }

    /**
     * Download photo bytes from Azure Blob Storage
     */
    public byte[] downloadBlob(String blobName) {
        BlobClient blobClient = containerClient.getBlobClient(blobName);
        return blobClient.downloadContent().toBytes();
    }

    /**
     * Delete a blob from Azure Blob Storage
     */
    public void deleteBlob(String blobName) {
        BlobClient blobClient = containerClient.getBlobClient(blobName);
        boolean deleted = blobClient.deleteIfExists();
        if (deleted) {
            logger.debug("Deleted blob '{}'", blobName);
        } else {
            logger.warn("Blob '{}' not found for deletion", blobName);
        }
    }
}
