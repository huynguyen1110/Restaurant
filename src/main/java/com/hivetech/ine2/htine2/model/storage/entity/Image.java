package com.hivetech.ine2.htine2.model.storage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imageId;
    private String collectionId;
    private String collectionName;
    private String imageName;
    private Boolean inGalleryList;
    private String owner;
    private LocalDateTime created;
}
