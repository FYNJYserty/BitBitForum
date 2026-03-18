package com.bitbitforum.controller;

import com.bitbitforum.utils.ParserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class GeneralController {
    /**
     * Get main page
     * @param model
     * @return
     * @throws IOException
     */
    @GetMapping("/general")
    public String general(Model model) throws IOException {
        ArrayList<Map<String, String>> newsList = ParserUtil.parse();
        model.addAttribute("newsList", newsList);
        return "general/general";
    }
}
