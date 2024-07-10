package com.bigbitforum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiscussionsController {
    @GetMapping("/discussions")
    public String discussions(Model model) {return "discussions";}
}
