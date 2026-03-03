package com.photoalbum.service;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.options.BlobParallelUploadOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Service for Azure Blob Storage operations: upload, download, and delete blobs
 */
@Service
@ConditionalOnBean(BlobContainerClient.class)
public class BlobStorageService {

    private static final Logger logger = LoggerFactory.getLogger(BlobStorageService.class);

    private final BlobContainerClient containerClient;

    public BlobStorageService(BlobContainerClient containerClient) {
        this.containerClient = containerClient;
    }

    /**
     * Upload a blob to Azure Blob Storage
     * @param blobName  The name of the blob (unique filename)
     * @param inputStream  The input stream containing the blob data
     * @param length  The length of the data in bytes
     * @param contentType  The MIME type of the blob
     */
    public void uploadBlob(String blobName, InputStream inputStream, long length, String contentType) {
        BlobClient blobClient = containerClient.getBlobClient(blobName);
        BlobHttpHeaders headers = new BlobHttpHeaders().setContentType(contentType);
        BlobParallelUploadOptions options = new BlobParallelUploadOptions(BinaryData.fromStream(inputStream, length))
                .setHeaders(headers);
        blobClient.uploadWithResponse(options, null, null);
        logger.info("Uploaded blob {} ({} bytes) to Azure Blob Storage", blobName, length);
    }

    /**
     * Download a blob from Azure Blob Storage
     * @param blobName  The name of the blob to download
     * @return  Byte array of blob content
     */
    public byte[] downloadBlob(String blobName) {
        BlobClient blobClient = containerClient.getBlobClient(blobName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        blobClient.downloadStream(outputStream);
        logger.info("Downloaded blob {} from Azure Blob Storage", blobName);
        return outputStream.toByteArray();
    }

    /**
     * Delete a blob from Azure Blob Storage
     * @param blobName  The name of the blob to delete
     */
    public void deleteBlob(String blobName) {
        containerClient.getBlobClient(blobName).deleteIfExists();
        logger.info("Deleted blob {} from Azure Blob Storage", blobName);
    }
}
