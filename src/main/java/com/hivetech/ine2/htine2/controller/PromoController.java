package com.hivetech.ine2.htine2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PromoController {

    @GetMapping("promo/coupon")
    public ModelAndView showCoupon(){
        return new ModelAndView("admin/coupon");
    }
}
