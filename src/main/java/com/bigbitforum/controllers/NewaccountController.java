package com.bigbitforum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/newaccount")
public class NewaccountController {
    @GetMapping
    public String newaccount(Model model) {
        return "newaccount";
    }
}
