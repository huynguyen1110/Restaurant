package com.hivetech.ine2.htine2.repository;

import com.hivetech.ine2.htine2.model.storage.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    Integer deleteByImageId(String imageId);

    @Query("SELECT i FROM Image i " +
            "WHERE CONCAT(i.imageId, ' ', i.imageName) LIKE %:keyword% ")
    Page<Image> findImageByOwner1(@Param("keyword") String keyword, Pageable pageable);
}
