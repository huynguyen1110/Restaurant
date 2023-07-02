package com.hivetech.ine2.htine2.service;

import com.hivetech.ine2.htine2.dto.ImageInfor;
import com.hivetech.ine2.htine2.dto.PageDTO;

import java.util.Set;

public interface MediaLibraryService {
    Integer uploadImage(ImageInfor imageInfor);

    Integer deleteImages(Set<String> imageUrls);

    PageDTO<ImageInfor> images(int currentPage, String sortBy, String sortDirection, String keyword);
}
