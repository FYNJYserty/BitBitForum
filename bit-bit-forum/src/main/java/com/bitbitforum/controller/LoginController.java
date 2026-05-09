package com.bitbitforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLogin(
            Model model,
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        // Login error message
        if (error != null) {
            model.addAttribute("loginError",
                    "Wrong username or password. Please try again.");
        }

        // Logout message
        if (logout != null) {
            model.addAttribute("logoutMessage",
                    "You successfully logged out.");
        }

        return "registration/login";
    }
}