package com.hivetech.ine2.htine2.controller;

import com.hivetech.ine2.htine2.model.storage.dto.LoginRequest;
import com.hivetech.ine2.htine2.service.GetLogInfoService;
import com.hivetech.ine2.htine2.service.UserServiceInterface;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/api/v1/public")
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private UserServiceInterface userServiceInterface;

    @Autowired
    private GetLogInfoService getLogInfoService;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("public/login_form");
        modelAndView.addObject("loginRequest", new LoginRequest());
        return modelAndView;
    }

    @GetMapping("/err")
    public ModelAndView loginErr() {
        ModelAndView modelAndView = new ModelAndView("err");
        modelAndView.addObject("loginRequest", new LoginRequest());
        return modelAndView;
    }


    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("loginRequest") LoginRequest loginRequest, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        return userServiceInterface.validateLogin(loginRequest, response, redirectAttributes);
    }


    @GetMapping("/logout")
    public ModelAndView logout(LoginRequest loginRequest, RedirectAttributes redirectAttributes) {
        return userServiceInterface.doLogout(loginRequest, redirectAttributes);
    }


    @GetMapping("/logtable")
    public String logtable(Model model) {
        return getLogInfoService.fetchData(model);
    }
}
