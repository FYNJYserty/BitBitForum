package com.bigbitforum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewaccountController {
    @GetMapping("/newaccount")
    public String newaccount(Model model) {
        return "newaccount";
    }
}
