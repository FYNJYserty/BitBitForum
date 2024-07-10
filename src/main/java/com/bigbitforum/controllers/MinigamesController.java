package com.bigbitforum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MinigamesController {
    @GetMapping("/minigames")
    public String minigames(Model model) {
        return "minigames";
    }
}
