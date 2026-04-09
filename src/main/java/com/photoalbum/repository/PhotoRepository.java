package com.photoalbum.repository;

import com.photoalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for Photo entity operations
 */
@Repository
public interface PhotoRepository extends JpaRepository<Photo, String> {

    /**
     * Find all photos ordered by upload date (newest first)
     * @return List of photos ordered by upload date descending
     */
    // Migrated from Oracle to PostgreSQL according to java check item 1: Convert all table and column names from uppercase to lowercase in native SQL queries.
    @Query(value = "SELECT id, original_file_name, blob_name, stored_file_name, file_path, file_size, " +
                   "mime_type, uploaded_at, width, height " +
                   "FROM photos " +
                   "ORDER BY uploaded_at DESC", 
           nativeQuery = true)
    List<Photo> findAllOrderByUploadedAtDesc();

    /**
     * Find photos uploaded before a specific photo (for navigation)
     * @param uploadedAt The upload timestamp to compare against
     * @return List of photos uploaded before the given timestamp
     */
    // Migrated from Oracle to PostgreSQL according to java check item: Replaced Oracle ROWNUM-based subquery pagination with PostgreSQL LIMIT clause.
    @Query(value = "SELECT id, original_file_name, blob_name, stored_file_name, file_path, file_size, " +
                   "mime_type, uploaded_at, width, height " +
                   "FROM photos " +
                   "WHERE uploaded_at < :uploadedAt " +
                   "ORDER BY uploaded_at DESC " +
                   "LIMIT 10", 
           nativeQuery = true)
    List<Photo> findPhotosUploadedBefore(@Param("uploadedAt") LocalDateTime uploadedAt);

    /**
     * Find photos uploaded after a specific photo (for navigation)
     * @param uploadedAt The upload timestamp to compare against
     * @return List of photos uploaded after the given timestamp
     */
    // Migrated from Oracle to PostgreSQL according to java check item: Replaced Oracle NVL with standard COALESCE; converted uppercase identifiers to lowercase.
    @Query(value = "SELECT id, original_file_name, blob_name, stored_file_name, " +
                   "COALESCE(file_path, 'default_path') AS file_path, file_size, " +
                   "mime_type, uploaded_at, width, height " +
                   "FROM photos " +
                   "WHERE uploaded_at > :uploadedAt " +
                   "ORDER BY uploaded_at ASC", 
           nativeQuery = true)
    List<Photo> findPhotosUploadedAfter(@Param("uploadedAt") LocalDateTime uploadedAt);

    /**
     * Find photos by upload month
     * @param year The year to search for
     * @param month The month to search for
     * @return List of photos uploaded in the specified month
     */
    // Migrated from Oracle to PostgreSQL according to java check item 4: Replaced Oracle TO_CHAR date function with PostgreSQL EXTRACT equivalent.
    @Query(value = "SELECT id, original_file_name, blob_name, stored_file_name, file_path, file_size, " +
                   "mime_type, uploaded_at, width, height " +
                   "FROM photos " +
                   "WHERE EXTRACT(YEAR FROM uploaded_at)::text = :year " +
                   "AND LPAD(EXTRACT(MONTH FROM uploaded_at)::text, 2, '0') = :month " +
                   "ORDER BY uploaded_at DESC", 
           nativeQuery = true)
    List<Photo> findPhotosByUploadMonth(@Param("year") String year, @Param("month") String month);

    /**
     * Get paginated photos using PostgreSQL LIMIT/OFFSET
     * @param limit  Number of rows to return
     * @param offset Zero-based starting offset
     * @return List of photos within the specified range
     */
    // Migrated from Oracle to PostgreSQL according to java check item: Replaced Oracle ROWNUM double-subquery pagination with PostgreSQL LIMIT/OFFSET.
    @Query(value = "SELECT id, original_file_name, blob_name, stored_file_name, file_path, file_size, " +
                   "mime_type, uploaded_at, width, height " +
                   "FROM photos " +
                   "ORDER BY uploaded_at DESC " +
                   "LIMIT :limit OFFSET :offset", 
           nativeQuery = true)
    List<Photo> findPhotosWithPagination(@Param("limit") int limit, @Param("offset") int offset);

    /**
     * Find photos with file size statistics using standard window functions
     * @return List of photos with running totals and rankings
     */
    // Migrated from Oracle to PostgreSQL according to java check item 1: Converted uppercase identifiers to lowercase. RANK() window function is preserved as it is standard SQL valid in PostgreSQL.
    @Query(value = "SELECT id, original_file_name, blob_name, stored_file_name, file_path, file_size, " +
                   "mime_type, uploaded_at, width, height, " +
                   "RANK() OVER (ORDER BY file_size DESC) AS size_rank, " +
                   "SUM(file_size) OVER (ORDER BY uploaded_at ROWS UNBOUNDED PRECEDING) AS running_total " +
                   "FROM photos " +
                   "ORDER BY uploaded_at DESC", 
           nativeQuery = true)
    List<Object[]> findPhotosWithStatistics();
}