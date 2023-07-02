package com.hivetech.ine2.htine2.controller;


import com.hivetech.ine2.htine2.dto.ImageInfor;
import com.hivetech.ine2.htine2.dto.PageDTO;
import com.hivetech.ine2.htine2.dto.ResponseEntityDto;
import com.hivetech.ine2.htine2.service.impl.MediaLibraryServiceImpl;
import com.hivetech.ine2.htine2.util.enumuration.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/api/v1/admin")
public class MediaLibraryController {
    @Autowired
    private MediaLibraryServiceImpl mediaLibraryService;
    @Value("${pocketBase.host}")
    private String pocketBaseHost;
    @Value("${pocketBase.collectionName}")
    private String pocketBaseCollection;
    @Value("${pocketBase.userName}")
    private String pocketBaseEmail;
    @Value("${pocketBase.password}")
    private String pocketBasePassword;

    @GetMapping("/get-value")
    @ResponseBody
    public Map returnValue() {
        return Map.of("pocketBaseHost", pocketBaseHost,
                "pocketBaseCollection", pocketBaseCollection,
                "pocketBaseEmail", pocketBaseEmail,
                "pocketBasePassword", pocketBasePassword);
    }

    @GetMapping("/upload-image")
    public ModelAndView uploadImage() {
        ModelAndView modelAndView = new ModelAndView("/private/admin/upload_image");
        return modelAndView;
    }

    @PostMapping("/save-image")
    public ResponseEntity saveImage(@RequestBody ImageInfor imageInfor) {
        mediaLibraryService.uploadImage(imageInfor);
        return ResponseEntity.ok(new ResponseEntityDto<ImageInfor>(imageInfor));
    }

    @GetMapping("/media-library")
    public ModelAndView getImages(@RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                                  @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                  @RequestParam(value = "sortType", defaultValue = "ASC") SortType sortType,
                                  @RequestParam(value = "keyword", defaultValue = "") String keyword
    ) {
        PageDTO<ImageInfor> listImages = mediaLibraryService.images(currentPage, sortField, sortType.toString(), keyword);
        ModelAndView modelAndView = new ModelAndView("private/admin/media_library");
        modelAndView.addObject("sortField", sortField);
        modelAndView.addObject("sortType", sortType);
        modelAndView.addObject("currentPage1", currentPage);
        modelAndView.addObject("images", listImages);
        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject("totalPages", listImages.getTotalPages());
        modelAndView.addObject("totalItems", listImages.getTotalItems());
        modelAndView.addObject("pocketBaseUrl", pocketBaseHost);
        return modelAndView;
    }

    @DeleteMapping("/delete-image")
    public ResponseEntity deleteImages(@RequestBody Set<String> imageUrls) {
        mediaLibraryService.deleteImages(imageUrls);
        return ResponseEntity.ok(new ResponseEntityDto("deleted"));
    }
}
