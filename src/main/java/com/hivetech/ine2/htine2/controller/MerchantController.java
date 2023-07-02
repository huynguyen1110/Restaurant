package com.hivetech.ine2.htine2.controller;

import com.hivetech.ine2.htine2.dto.MerchantDTO;
import com.hivetech.ine2.htine2.dto.PageDTO;
import com.hivetech.ine2.htine2.service.MerchantService;
import com.hivetech.ine2.htine2.util.enumuration.MerchantStatus;
import com.hivetech.ine2.htine2.util.enumuration.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("api/v1/admin")
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @GetMapping("/merchants")
    public ModelAndView showListMerchants(@RequestParam(value = "page", defaultValue = "1") int currentPage,
                                          @RequestParam(value = "limit", defaultValue = "15") int limit,
                                          @RequestParam(value = "sortField", defaultValue = "contactName") String sortField,
                                          @RequestParam(value = "sortType", defaultValue = "ASC") SortType sortType,
                                          @RequestParam(value = "keyword", defaultValue = "") String keyword,
                                          @RequestParam(value = "status", defaultValue = "") MerchantStatus status) {

        ModelAndView modelAndView = new ModelAndView("private/admin/merchant/index");

        PageDTO<MerchantDTO> listMerchants = merchantService.getAllMerchant(currentPage, limit, sortField, sortType.toString(), keyword, status);

        modelAndView.addObject("currentPage", currentPage);
        modelAndView.addObject("numberOfItems", limit);
        modelAndView.addObject("sortField", sortField);
        modelAndView.addObject("sortType", sortType);
        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject("status", status == null ? "" : status);
        modelAndView.addObject("result", listMerchants);
        modelAndView.addObject("totalPages", listMerchants.getTotalPages());
        modelAndView.addObject("totalItems", listMerchants.getTotalItems());

        return modelAndView;
    }

}
