package com.hivetech.ine2.htine2.service;

import com.hivetech.ine2.htine2.model.storage.entity.User;
import com.hivetech.ine2.htine2.model.storage.dto.JwtResponse;
import com.hivetech.ine2.htine2.model.storage.dto.LoginRequest;
import com.hivetech.ine2.htine2.repository.UserRepository;
import com.hivetech.ine2.htine2.security.jwt.JwtUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceInterfaceImpl implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private GetLogInfoService getLogInfoService;

    private static String token;

    private static String globalUserName;

    @Override
    public String errUserNameMessage(String userName) {
        String errorMessage = "";
        boolean isPhoneNumber = false;
        for (int index = 0; index < userName.length(); index++) {
            isPhoneNumber = Character.isDigit(userName.charAt(index));
        }
        if (isPhoneNumber) {
            if (!isValidPhoneNumber(userName)) {
                errorMessage = "Invalid phone number";
            }
        } else {
            if (!isValidUserName(userName)) {
                errorMessage = "Only receive English alphabet";
            }
        }
        return errorMessage;
    }

    public boolean isValidUserName(String input) {
        boolean isValidUserName = false;
        boolean isPhoneNumber = isPhoneNumber(input);
        if (isPhoneNumber) {
            if (isValidPhoneNumber(input)) {
                isValidUserName = true;
            }
        }
        if (onlyContainsEngChar(input)) {
            isValidUserName = true;
        }
        return isValidUserName;
    }

    public boolean isPhoneNumber(String input) {
        boolean isPhoneNumber = false;
        for (int index = 0; index < input.length(); index++) {
            if (Character.isDigit(input.charAt(index))) {
                isPhoneNumber = true;
            } else {
                isPhoneNumber = false;
                break;
            }
        }
        return isPhoneNumber;
    }

    public boolean onlyContainsEngChar(String input) {
        boolean isValid = false;
        for (int index = 0; index < input.length(); index++) {
            if (Character.isLetter(input.charAt(index)) && input.charAt(index) <= 122) {
                isValid = true;
                continue;
            } else {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    // 9 - 3- 7- 8
    public boolean isValidPhoneNumber(String input) {
        if (isValidPhoneNumberLength(input)) {
            return (input.charAt(0) == 48)
                    && (input.charAt(1) == 51
                    || input.charAt(1) == 55
                    || input.charAt(1) == 56
                    || input.charAt(1) == 57);
        }
        return false;
    }

    public boolean isValidPhoneNumberLength(String input) {
        return input.length() <= 11 && input.length() >= 10;
    }

    @Override
    public boolean isContainsUppercaseChar(String input) {
        for (int index = 0; index < input.length(); index++) {
            if (Character.isUpperCase(input.charAt(index))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isContainsLowercaseChar(String input) {
        for (int index = 0; index < input.length(); index++) {
            if (Character.isLowerCase(input.charAt(index))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isContainsNumberAndSpecChar(String input) {
        return isContainsNumber(input) && isContainsSpecialChar(input);
    }

    @Override
    public boolean isContainsSpecialChar(String input) {
        boolean isValid = false;
        for (int index = 0; index < input.length(); index++) {
            if (!Character.isDigit(input.charAt(index))
                    && !Character.isLetter(input.charAt(index))
                    && !Character.isWhitespace(input.charAt(index))) {
                isValid = true;
            }
        }
        return isValid;
    }

    @Override
    public boolean isContainsNumber(String input) {
        boolean isValid = false;
        for (int index = 0; index < input.length(); index++) {
            if (Character.isDigit(input.charAt(index))) {
                isValid = true;
            }
        }
        return isValid;
    }

    @Override
    public boolean isValidPasswordLength(String input) {
        return input.length() > 12;
    }

    @Override
    public boolean isValidPassword(String input) {
        return (isContainsUppercaseChar(input)
                && isContainsLowercaseChar(input)
                && isContainsNumberAndSpecChar(input)
                && isValidPasswordLength(input));
    }

    public String getUsername(String name) {
        String username = name;
        return username;
    }

    @Override
    public ModelAndView validateLogin(@ModelAttribute("loginRequest") LoginRequest loginRequest,
                                      HttpServletResponse response, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = null;
        globalUserName = loginRequest.getUserName();
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        String errUserNameMess = errUserNameMessage(userName);
        Optional<User> user = userRepository.findByUserName(userName);

        if (!isValidUserName(userName)) {
            redirectAttributes.addFlashAttribute("loginRequest", loginRequest);
            redirectAttributes.addFlashAttribute("errMessageUserName", errUserNameMess);
            modelAndView = new ModelAndView("redirect:/api/v1/public/login");
            return modelAndView;
        }

        if (!isValidPasswordLength(password)) {
            redirectAttributes.addFlashAttribute("loginRequest", loginRequest);
            redirectAttributes.addFlashAttribute("errorPasswordMessage",
                    "The password length must be more than 12");
            modelAndView = new ModelAndView("redirect:/api/v1/public/login");
            return modelAndView;
        }

        if (!isValidPassword(password)) {
            redirectAttributes.addFlashAttribute("loginRequest", loginRequest);
            redirectAttributes.addFlashAttribute("errorPasswordMessage",
                    "The password must contain at least One upppercase lowercase number and special character");
            modelAndView = new ModelAndView("redirect:/api/v1/public/login");
            return modelAndView;
        }

        if (!user.isPresent()) {
            String errMessageUserName = "Username or password incorrect";
            redirectAttributes.addFlashAttribute("loginRequest", loginRequest);
            redirectAttributes.addFlashAttribute("errMessageUserName", errMessageUserName);
            modelAndView = new ModelAndView("redirect:/api/v1/public/login");
            return modelAndView;
        }

        boolean matchPassword = encoder.matches(password, user.get().getPassword());
        if (!matchPassword) {
            String errorPasswordMessage = "Username or password incorrect";
            redirectAttributes.addFlashAttribute("loginRequest", loginRequest);
            redirectAttributes.addFlashAttribute("errorPasswordMessage", errorPasswordMessage);
            modelAndView = new ModelAndView("redirect:/api/v1/public/login");
            return modelAndView;
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        token = getCookies(jwt);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setId(userDetails.getId());
        jwtResponse.setToken(jwt);
        jwtResponse.setUsername(userDetails.getUsername());
        jwtResponse.setRoles(roles);
        Cookie cookie = new Cookie("token", jwt);
        cookie.setPath("/");
        response.addCookie(cookie);

        if (!roles.isEmpty()) {
            if ("ROLE_USER".equals(roles.get(0))) {
                getLogInfoService.getLogInInfo(userName);
                getUsername(userName);
                redirectAttributes.addFlashAttribute("loginRequest", loginRequest);
                modelAndView = new ModelAndView("redirect:/api/v1/user");
            } else if ("ROLE_ADMIN".equals(roles.get(0))) {
                getLogInfoService.getLogInInfo(userName);
                getUsername(userName);
                redirectAttributes.addFlashAttribute("loginRequest", loginRequest);
                modelAndView = new ModelAndView("redirect:/api/v1/admin");
            } else if ("ROLE_MERCHANT".equals(roles.get(0))) {
                getLogInfoService.getLogInInfo(userName);
                getUsername(userName);
                redirectAttributes.addFlashAttribute("loginRequest", loginRequest);
                modelAndView = new ModelAndView("redirect:/api/v1/merchant");
            }
        } else {
            modelAndView = new ModelAndView("redirect:/api/v1/err");
        }
        return modelAndView;
    }

    public String getCookies(String inputToken) {
        String cookie = inputToken;
        return cookie;
    }

    @Override
    public ModelAndView doLogout(@ModelAttribute("loginRequest") LoginRequest loginRequest, RedirectAttributes redirectAttributes) {
        getLogInfoService.getLogOutInfo(globalUserName);
        return new ModelAndView("redirect:/api/v1/public/login");
    }
}
