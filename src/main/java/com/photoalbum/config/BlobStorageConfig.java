package com.photoalbum.config;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configuration for Azure Blob Storage client using managed identity.
 * Excluded from the test profile to avoid requiring Azure connectivity during tests.
 */
@Configuration
@Profile("!test")
public class BlobStorageConfig {

    @Bean
    public BlobServiceClient blobServiceClient(
            @Value("${azure.storage.account-name}") String accountName) {
        String endpoint = String.format("https://%s.blob.core.windows.net", accountName);
        return new BlobServiceClientBuilder()
                .endpoint(endpoint)
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();
    }
}
