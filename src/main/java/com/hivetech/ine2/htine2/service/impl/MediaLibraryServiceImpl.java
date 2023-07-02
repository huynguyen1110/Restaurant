package com.hivetech.ine2.htine2.service.impl;

import com.hivetech.ine2.htine2.dto.ImageInfor;
import com.hivetech.ine2.htine2.dto.PageDTO;
import com.hivetech.ine2.htine2.model.Image;
import com.hivetech.ine2.htine2.repository.ImageRepository;
import com.hivetech.ine2.htine2.service.MediaLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MediaLibraryServiceImpl implements MediaLibraryService {
    @Autowired
    private ImageRepository imageRepository;

    public PageDTO<ImageInfor> images(int currentPage, String sortBy, String sortDirection, String keyword) {
        Sort.Direction direction = Sort.Direction.DESC.toString().equals(sortBy) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(currentPage - 1, 15, sort);
        Page<Image> imagePage = imageRepository.findImageByOwner1(keyword, pageable);
        List<Image> images = imagePage.getContent();
        List<ImageInfor> data = new ArrayList<>();
        for (Image image : images) {
            ImageInfor dto = ImageInfor.builder()
                    .collectionId(image.getCollectionId())
                    .imageId(image.getImageId())
                    .collectionName(image.getCollectionName())
                    .imageName(image.getImageName())
                    .created(image.getCreated())
                    .build();
            data.add(dto);
        }
        return new PageDTO(data, imagePage.getTotalPages(), imagePage.getTotalElements(), 15, currentPage);
    }

    @Override
    public Integer uploadImage(ImageInfor imageInfor) {
        Image image1 = Image.builder()
                .imageId(imageInfor.getImageId())
                .collectionId(imageInfor.getCollectionId())
                .collectionName(imageInfor.getCollectionName())
                .imageName(imageInfor.getImageName())
                .owner("Admin")
                .inGalleryList(false)
                .created(LocalDateTime.now())
                .build();
        imageRepository.save(image1);
        return 1;
    }

    @Transactional
    public Integer deleteImages(Set<String> imageUrls) {
        for (String url : imageUrls) {
            imageRepository.deleteByImageId(url);
        }
        return imageUrls.size();
    }
}
