package com.bitbitforum.controller;

import com.bitbitforum.config.CustomUserDetails;
import com.bitbitforum.dto.UserUpdateDto;
import com.bitbitforum.entity.User;
import com.bitbitforum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Get methods
    /**
     * Get current user info
     * @param userDetails
     * @param model
     * @return
     */
    @GetMapping
    public String currentAccount(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        User user = userDetails.getUser();
        model.addAttribute("user", user);
        model.addAttribute("userUpdateDto", new UserUpdateDto());
        return "user/account";
    }

    // Post methods
    /**
     * Post update settings of current account
     * @param userDetails
     * @param userUpdateDto
     * @param redirectAttributes
     * @return
     */
    @PatchMapping("/update")
    public String updateAccount(@AuthenticationPrincipal CustomUserDetails userDetails,
                                @ModelAttribute UserUpdateDto userUpdateDto,
                                RedirectAttributes redirectAttributes) {
        User user = userDetails.getUser();
        
        try {
            // Validate current password if trying to change password
            if (userUpdateDto.getNewPassword() != null && !userUpdateDto.getNewPassword().isEmpty()) {
                if (userUpdateDto.getCurrentPassword() == null || userUpdateDto.getCurrentPassword().isEmpty()) {
                    redirectAttributes.addFlashAttribute("error", "Current password is required to change password");
                    return "redirect:/account";
                }
                
                if (!passwordEncoder.matches(userUpdateDto.getCurrentPassword(), user.getPasswd())) {
                    redirectAttributes.addFlashAttribute("error", "Current password is incorrect");
                    return "redirect:/account";
                }
                
                if (!userUpdateDto.getNewPassword().equals(userUpdateDto.getConfirmPassword())) {
                    redirectAttributes.addFlashAttribute("error", "New passwords do not match");
                    return "redirect:/account";
                }
                
                // Update password
                user.setPasswd(passwordEncoder.encode(userUpdateDto.getNewPassword()));
            }
            
            // Update profile information
            boolean profileUpdated = false;
            
            if (userUpdateDto.getUsername() != null && !userUpdateDto.getUsername().isEmpty() 
                && !userUpdateDto.getUsername().equals(user.getUsername())) {
                user.setUsername(userUpdateDto.getUsername());
                profileUpdated = true;
            }
            
            if (userUpdateDto.getEmail() != null && !userUpdateDto.getEmail().isEmpty() 
                && !userUpdateDto.getEmail().equals(user.getEmail())) {
                user.setEmail(userUpdateDto.getEmail());
                profileUpdated = true;
            }
            
            if (userUpdateDto.getAgeUsr() != null && !userUpdateDto.getAgeUsr().equals(user.getAgeUsr())) {
                user.setAgeUsr(userUpdateDto.getAgeUsr());
                profileUpdated = true;
            }
            
            // Save changes
            userRepository.save(user);
            
            if (profileUpdated) {
                redirectAttributes.addFlashAttribute("success", "Profile information updated successfully");
            }
            if (userUpdateDto.getNewPassword() != null && !userUpdateDto.getNewPassword().isEmpty()) {
                redirectAttributes.addFlashAttribute("success", "Password changed successfully");
            }
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating account: " + e.getMessage());
        }
        
        return "redirect:/account";
    }
}
