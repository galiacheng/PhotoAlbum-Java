package com.photoalbum.config;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Azure Blob Storage configuration using Managed Identity for authentication
 */
@Configuration
@ConditionalOnProperty(name = "azure.storage.account-name")
public class BlobStorageConfig {

    @Bean
    public BlobServiceClient blobServiceClient(@Value("${azure.storage.account-name}") String accountName) {
        String endpoint = "https://" + accountName + ".blob.core.windows.net";
        return new BlobServiceClientBuilder()
                .endpoint(endpoint)
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();
    }
}
