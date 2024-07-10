package com.bigbitforum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OnlinechatController {
    @GetMapping("/onlinechat")
    public String onlinechat(Model model) {return "onlinechat";}
}
