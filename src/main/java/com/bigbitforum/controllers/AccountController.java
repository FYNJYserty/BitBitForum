package com.bigbitforum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @GetMapping("/account")
    public String AccountController(Model model) {
        return "account";
    }
}
