package com.bitbitforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/onlinechat")
public class OnlinechatController {
    @GetMapping
    public String onlinechat(Model model) {return "chat/onlinechat";}
}
