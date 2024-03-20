package com.bigbitforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteController {
    @GetMapping("/regist")
    public String getMain () {
        return "regist";
    }
}
