package com.photoalbum.config;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configuration for Azure Blob Storage using Managed Identity authentication
 */
@Configuration
@Profile("!test")
public class AzureBlobStorageConfig {

    @Bean
    @ConditionalOnMissingBean
    public BlobContainerClient blobContainerClient(
            @Value("${azure.blob.storage.account-name}") String accountName,
            @Value("${azure.blob.storage.container-name:photos}") String containerName) {
        String endpoint = String.format("https://%s.blob.core.windows.net", accountName);
        BlobServiceClient serviceClient = new BlobServiceClientBuilder()
                .endpoint(endpoint)
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();
        BlobContainerClient containerClient = serviceClient.getBlobContainerClient(containerName);
        if (!containerClient.exists()) {
            containerClient.create();
        }
        return containerClient;
    }
}
