package com.hivetech.ine2.htine2.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class TesAuthController {

    public static final String USER = "USER";
    
    public static final String ADMIN = "ADMIN";
    
    public static final String MERCHANT = "MERCHANT";

    @GetMapping("/user")
    @RolesAllowed(USER)
    public ModelAndView userHome() {
        ModelAndView modelAndView = new ModelAndView("user");
        return modelAndView;
    }

    @GetMapping("/admin")
    @RolesAllowed(ADMIN)
    public ModelAndView adminHome() {
        ModelAndView modelAndView = new ModelAndView("admin");
        return modelAndView;
    }

    @GetMapping("/admin/order")
    @RolesAllowed({ADMIN, MERCHANT})
    public ModelAndView adminOrder() {
        ModelAndView modelAndView = new ModelAndView("well_come");
        return modelAndView;
    }

    @GetMapping("/merchant")
    @RolesAllowed(MERCHANT)
    public ModelAndView merchantHome() {
        ModelAndView modelAndView = new ModelAndView("merchant");
        return modelAndView;
    }
}
