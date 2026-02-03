package com.bitbitforum.controller;

import com.bitbitforum.config.CustomUserDetails;
import com.bitbitforum.entity.Discussion;
import com.bitbitforum.entity.User;
import com.bitbitforum.service.DiscussionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.*;

@Controller
public class DiscussionsController {

    private final DiscussionService discussionService;

    public DiscussionsController(DiscussionService discussionService) {
        this.discussionService = discussionService;
    }
    // Get methods
    /**
     * Get all discussions page
     * @param model
     * @return
     * @throws SQLException
     */
    @GetMapping("/discussions")
    public String discussions(Model model) throws SQLException {
        model.addAttribute("discussions", discussionService.findAll());
        return "discussions/discussions";
    }
    /**
     * Get current discussion page
     * @param id
     * @param model
     * @return
     * @throws SQLException
     */
    @GetMapping("/discussions/{id}")
    public String getDiscussion(@PathVariable Integer id, Model model) throws SQLException {
        return "discussions/currentdisc";
    }

    // Post methods
}
