package com.cdac.caneadviser.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdac.caneadviser.entity.User;
import com.cdac.caneadviser.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // @GetMapping("/login")
    // public String login() {
    //     return "login";
    // }

    @PostMapping("/Adminlogin")
    public String loginUser(String email, String password, RedirectAttributes redirectAttributes) {
        User user = userService.loginUser(email, password);
        if (user != null) {
            // Successful login, you can redirect to a dashboard or another page
            return "redirect:/adminDashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Email and password do not match.");
            return "redirect:/Adminlogin";

        }
    }
}