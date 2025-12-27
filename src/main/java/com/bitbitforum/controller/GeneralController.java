package com.bitbitforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/general")
public class GeneralController {
    @GetMapping
    public String general(Model model) {
        // Flash-атрибуты автоматически добавляются в модель Spring
        return "general/general";
    }
}
