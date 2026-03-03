package com.photoalbum;

import com.photoalbum.service.BlobStorageService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PhotoAlbumApplicationTests {

    @MockBean
    private BlobStorageService blobStorageService;

    @Test
    void contextLoads() {
        // This test ensures that the Spring context loads correctly
    }
}