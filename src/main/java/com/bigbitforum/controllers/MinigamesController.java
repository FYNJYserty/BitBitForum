package com.bigbitforum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/minigames")
public class MinigamesController {
    @GetMapping
    public String minigames(Model model) {
        return "minigames";
    }
}
