package com.hivetech.ine2.htine2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ImageInfor {
    String collectionId;
    String collectionName;
    String imageId;
    String imageName;
    LocalDateTime created;
}
