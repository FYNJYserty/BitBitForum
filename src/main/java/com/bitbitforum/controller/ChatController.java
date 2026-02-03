package com.bitbitforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
    @GetMapping("/chats")
    public String onlinechat(Model model) {
        return "chat/chats";
    }
}
