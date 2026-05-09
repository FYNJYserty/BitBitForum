package com.bitbitforum.controller;

import com.bitbitforum.dto.FormSuccessDto;
import com.bitbitforum.entity.User;
import com.bitbitforum.repository.UserRepository;
import com.bitbitforum.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class RegistController {

    @Autowired
    private PasswordEncoder passwordEncoder; // Password encoder
    @Autowired
    private UserRepository userRepository; // User repository
    @Autowired
    private MessageService messageService; // Message service

    @GetMapping("/regist")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "registration/regist";
    }

    @PostMapping("/regist")
    public String register(User user, RedirectAttributes redirectAttributes, @ModelAttribute FormSuccessDto formSuccessDto) {
        // Check if user exists
        if (userRepository.findByLogin(user.getLogin()).isPresent()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error: Username is already in use!");
            return "redirect:/regist?error";
        }

        // Saving new user
        user.setPasswd(passwordEncoder.encode(user.getPasswd()));
        user.setDateReg(LocalDateTime.now());
        user.setRoleUsr("USER");
        userRepository.save(user);

        // Add success label on page
        redirectAttributes.addFlashAttribute("successMessage", "User has been registered successfully!");

        // Send email message of successful registration
        formSuccessDto.setTextMessage("Your account has been registered successfully on Bitbitforum!");
        messageService.sendMessage(formSuccessDto);
        return "redirect:/login?success";
    }
}