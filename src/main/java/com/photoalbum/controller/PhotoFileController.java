package com.photoalbum.controller;

import com.photoalbum.model.Photo;
import com.photoalbum.service.PhotoService;
import com.photoalbum.storage.AzureBlobStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Controller for serving photo files from Azure Blob Storage
 */
@Controller
@RequestMapping("/photo")
public class PhotoFileController {

    private static final Logger logger = LoggerFactory.getLogger(PhotoFileController.class);

    private final PhotoService photoService;
    private final AzureBlobStorageService blobStorageService;

    public PhotoFileController(PhotoService photoService, AzureBlobStorageService blobStorageService) {
        this.photoService = photoService;
        this.blobStorageService = blobStorageService;
    }

    /**
     * Serves a photo file by ID, downloading from Azure Blob Storage
     */
    @GetMapping("/{id}")
    public ResponseEntity<Resource> servePhoto(@PathVariable String id) {
        if (id == null || id.trim().isEmpty()) {
            logger.warn("Photo file request with null or empty ID");
            return ResponseEntity.notFound().build();
        }

        try {
            Optional<Photo> photoOpt = photoService.getPhotoById(id);

            if (!photoOpt.isPresent()) {
                logger.warn("Photo with ID {} not found", id);
                return ResponseEntity.notFound().build();
            }

            Photo photo = photoOpt.get();

            // Download photo data from Azure Blob Storage
            byte[] photoData;
            try {
                photoData = blobStorageService.downloadBlob(photo.getStoredFileName());
            } catch (Exception ex) {
                logger.error("Error downloading blob '{}' from Azure Blob Storage", photo.getStoredFileName(), ex);
                return ResponseEntity.status(500).build();
            }

            if (photoData == null || photoData.length == 0) {
                logger.error("No photo data retrieved for photo ID {} (blob: {})", id, photo.getStoredFileName());
                return ResponseEntity.notFound().build();
            }

            Resource resource = new ByteArrayResource(photoData);

            logger.info("Serving photo ID {} ({}, {} bytes) from Azure Blob Storage",
                    id, photo.getOriginalFileName(), photoData.length);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(photo.getMimeType()))
                    .header(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate, private")
                    .header(HttpHeaders.PRAGMA, "no-cache")
                    .header(HttpHeaders.EXPIRES, "0")
                    .header("X-Photo-ID", String.valueOf(id))
                    .header("X-Photo-Name", photo.getOriginalFileName())
                    .header("X-Photo-Size", String.valueOf(photoData.length))
                    .body(resource);
        } catch (Exception ex) {
            logger.error("Error serving photo with ID {} from Azure Blob Storage", id, ex);
            return ResponseEntity.status(500).build();
        }
    }
}