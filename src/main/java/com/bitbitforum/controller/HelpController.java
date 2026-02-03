package com.bitbitforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelpController {
    /**
     * About website page get method
     * @return
     */
    @GetMapping("/aboutus")
    public String aboutus() {
        return "other/about";
    }

    /**
     * Help website page get method
     * @return
     */
    @GetMapping("help")
    public String help() {
        return "other/help";
    }
}
