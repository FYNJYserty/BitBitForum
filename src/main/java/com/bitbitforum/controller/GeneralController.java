package com.bitbitforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {
    /**
     * Get main page
     * @param model
     * @return
     */
    @GetMapping("/general")
    public String general(Model model) {
        return "general/general";
    }
}
