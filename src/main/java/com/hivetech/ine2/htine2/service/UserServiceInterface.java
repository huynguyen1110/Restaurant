package com.hivetech.ine2.htine2.service;

import com.hivetech.ine2.htine2.model.storage.dto.LoginRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public interface UserServiceInterface {

    String errUserNameMessage(String userName);

    boolean isValidUserName(String userName);

    boolean isContainsNumber(String input);

    boolean isContainsSpecialChar(String input);

    boolean isContainsUppercaseChar(String input);

    boolean isContainsLowercaseChar(String input);

    boolean isContainsNumberAndSpecChar(String input);

    boolean isValidPasswordLength(String input);

    boolean isValidPassword(String input);

    ModelAndView validateLogin(LoginRequest loginRequest, HttpServletResponse response, RedirectAttributes redirectAttributes);

    ModelAndView doLogout(LoginRequest loginRequest, RedirectAttributes redirectAttributes);
}
