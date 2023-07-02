package com.hivetech.ine2.htine2.controller;

import com.hivetech.ine2.htine2.model.storage.entity.User;
import com.hivetech.ine2.htine2.util.enumuration.SortType;
import com.hivetech.ine2.htine2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/v1/user")
public class AdminUserController {
    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public ModelAndView addUser() {
        ModelAndView modelAndView = new ModelAndView("private/admin/user/add_new");
        User user = new User();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveUser(@Valid @ModelAttribute("user") User newUser, BindingResult bindingResult, @RequestParam(name = "confirmPassword") String confirmPassword) {
        ModelAndView modelAndView = new ModelAndView("private/admin/user/add_new");
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        if (userService.isValidFields(newUser, modelAndView, confirmPassword)) {
            return modelAndView;
        }
        userService.register(newUser);
        return new ModelAndView("redirect:/api/v1/user/list");
    }

    @RequestMapping("/list")
    public ModelAndView getAllUser(@RequestParam(name = "searchName", defaultValue = "", required = false) String search,
                                   @RequestParam(name = "pages", defaultValue = "1", required = false) Integer pages,
                                   @RequestParam(name = "size", defaultValue = "15", required = false) Integer size,
                                   @RequestParam(name = "sortType", defaultValue = "ASC", required = false) SortType sortType,
                                   @RequestParam(name = "sortField", defaultValue = "user_name", required = false) String sortField ){
        ModelAndView modelAndView = new ModelAndView("private/admin/user/user_list");
        Sort sort = Sort.by(sortField);
        sort = sortType.name().equals(SortType.ASC.name()) ? sort.ascending() : sort.descending();
        String searchName = userService.isSearchName(search);
        Page<User> page = userService.getAllUser(searchName, PageRequest.of(pages - 1, size, sort));
        int totalPage = page.getTotalPages();
        modelAndView.addObject("users", page.getContent());
        modelAndView.addObject("totalPage", totalPage);
        modelAndView.addObject("pages", pages);
        modelAndView.addObject("searchName", searchName);
        modelAndView.addObject("totalElements", page.getTotalElements());
        modelAndView.addObject("sortType", sortType);
        modelAndView.addObject("sortField", sortField);
        return modelAndView;
    }
}
