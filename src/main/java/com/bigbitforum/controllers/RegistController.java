package com.bigbitforum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistController {
    @GetMapping("/regist")
    public String getMain () {

        return "regist";
    }
}
