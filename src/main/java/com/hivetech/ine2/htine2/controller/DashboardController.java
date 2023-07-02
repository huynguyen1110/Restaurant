package com.hivetech.ine2.htine2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1/admin")
public class DashboardController {
    @GetMapping("/dashboard")
    public ModelAndView home() {
        return new ModelAndView("private/admin/dashboard");
    }
}
