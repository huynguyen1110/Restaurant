package com.hivetech.ine2.htine2.service;

import com.hivetech.ine2.htine2.model.storage.entity.User;
import com.hivetech.ine2.htine2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Converter converter;

    public User register(User user) {
        String firstName = converter.convertName(user.getFirstName());
        String lastName = converter.convertName(user.getLastName());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return userRepository.save(user);
    }

    public boolean isValidName(String name, String lastName, ModelAndView modelAndView) {
        if (name.startsWith(" ") || name.endsWith(" ")) {
            modelAndView.addObject("firstNameError", "Names without starting and ending spaces");
            return true;
        }
        if (lastName.startsWith(" ") || lastName.endsWith(" ")) {
            modelAndView.addObject("lastNameError", "Names without starting and ending spaces");
            return true;
        }
        return false;
    }

    public boolean isValidFields(User newUser, ModelAndView modelAndView, String confirmPassword) {
        if (isValidName(newUser.getFirstName(), newUser.getLastName(), modelAndView)) {
            return true;
        }
        if (isValidEmail(newUser.getEmail(), modelAndView)) {
            return true;
        }
        if (isValidPassword(newUser.getPassword(), modelAndView)) {
            return true;
        }
        if (!confirmPassword.equals(newUser.getPassword())) {
            modelAndView.addObject("errorConfirmPassword", "Password incorrect");
            return true;
        }
        return false;
    }

    public boolean isValidPassword(String password, ModelAndView modelAndView) {
        int lowercase = 0;
        int uppercase = 0;
        int digit = 0;
        int special = 0;
        int countCondition = 0;
        if (password.length() < 15) {
            modelAndView.addObject("error", "Password form 15 characters");
            return true;
        }
        for (int i = 0; i < password.length(); i++) {
            char character = password.charAt(i);
            if (character < 35 || (character > 38 && character < 42) || (character > 43 && character < 48) || (character > 57 && character < 64) || (character > 90
                    && character < 97) || character > 122) {
                modelAndView.addObject("error", "Password contains only characters(a->z,A->Z,0-9,'@,#,$,%,^,&,* , _ , +') and no spaces");
                return true;
            } else if (digit < 1 && character >= 48 && character <= 57) {
                digit++;
                countCondition += digit;
            } else if (uppercase < 1 && character >= 65 && character <= 90) {
                uppercase++;
                countCondition += uppercase;
            } else if (lowercase < 1 && character >= 97 && character <= 122) {
                lowercase++;
                countCondition += lowercase;
            } else if (special < 1 && character >= 35 && character <= 38 || character == 64 || character == 95 || character == 42 || character == 43) {
                special++;
                countCondition += special;
            }
        }
        if (countCondition < 4) {
            if (uppercase == 0) {
                modelAndView.addObject("error", "Password must have at least 1 uppercase letter");
                return true;
            }
            if (lowercase == 0) {
                modelAndView.addObject("error", "Password must be at least 1 lowercase letter");
                return true;
            }
            if (digit == 0) {
                modelAndView.addObject("error", "Minimum 1 digit password");
                return true;
            }
            if (special == 0) {
                modelAndView.addObject("error", "Password must be at least 1 special characters");
                return true;
            }
        }
        return false;
    }

    public boolean isValidEmail(String email, ModelAndView modelAndView) {
        String[] arrayEmail = email.split("@");
        if (arrayEmail.length != 2) {
            modelAndView.addObject("errorEmail", "Invalid email format");
            return true;
        } else if (arrayEmail.length == 2) {
            String localPart = arrayEmail[0];
            String domain = arrayEmail[1];
            String localPartAndDomain = localPart.concat(domain);
            if (email.length() > 150) {
                modelAndView.addObject("errorEmail", "Email Too Long");
                return true;
            }
            if (4 > localPart.length() || 63 < localPart.length()) {
                modelAndView.addObject("errorEmail", "Email the local part only from 4 to 63 characters");
                return true;
            }
            for (int i = 0; i < localPartAndDomain.length(); i++) {
                char character = localPartAndDomain.charAt(i);
                if (!Character.isLetterOrDigit(character)
                        && (character != '_' && character != '-' && character != '.')) {
                    modelAndView.addObject("errorEmail", "Invalid email format");
                    return true;
                }
            }
        }
        return false;
    }

    public Page<User> getAllUser(String userName, Pageable pageable) {
        return userRepository.findUsers(userName, pageable);
    }

    public String isSearchName(String searchName) {
        String search = "";
        if (searchName.length() < 3) {
            return search;
        }
        return searchName;
    }

}
