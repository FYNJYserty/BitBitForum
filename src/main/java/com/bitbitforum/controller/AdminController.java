package com.bitbitforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/adminpanel")
    public String panel (Model model) {
        return "admin/adminpanel";
    }
}
