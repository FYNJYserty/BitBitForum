package com.bitbitforum.controller;

import com.bitbitforum.dto.FormMessageDto;
import com.bitbitforum.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelpController {

    @Autowired
    private MessageService messageService;

    // Get methods
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
     * @param model
     * @param success
     * @return
     */
    @GetMapping("/help")
    public String help(Model model, @RequestParam(value = "success", required=false) String success) {
        if (success != null) {
            model.addAttribute("successMessage", "Message sent successfully");
        }
        return "other/help";
    }

    // Post methods
    /**
     * Post send help message
     * @param model
     * @param formMessageDto
     * @return
     */
    @PostMapping("/help/send")
    public String sendMessage(Model model, @ModelAttribute FormMessageDto formMessageDto) {
        model.addAttribute("successMessage", "Message sent successfully");
        messageService.sendMessageHelp(formMessageDto);
        return "redirect:/help";
    }
}
