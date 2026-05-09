package com.bitbitforum.controller;

import com.bitbitforum.entity.Discussion;
import com.bitbitforum.entity.User;
import com.bitbitforum.repository.AttachmentRepository;
import com.bitbitforum.repository.ChatRepository;
import com.bitbitforum.repository.DiscussionRepository;
import com.bitbitforum.repository.UserRepository;
import com.bitbitforum.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserRepository userRepository; // User repository
    @Autowired
    private ChatService chatService; // Chat service
    @Autowired
    private DiscussionRepository discussionRepository; // Discussion repository
    @Autowired
    private AttachmentRepository attachmentRepository; // Attachment repository
    @Autowired
    private ChatRepository chatRepository; // Chat repository

    // Get methods
    /**
     * Get all users
     * @param model
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/users")
    public String panelUsers(Model model) {
        List<User> usr = userRepository.findAll();
        model.addAttribute("users", usr);
        return "admin/adminpanel";
    }

    /**
     * Get all chats
     * @param model
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/chats")
    public String panelChat (Model model) {
        model.addAttribute("chats", chatService.findAllChatsWithInfo());
        return "admin/adminpanel";
    }

    /**
     * Get all discussions
     * @param model
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/discussions")
    public String panelDiscussion (Model model) {
        List<Discussion> discussion = discussionRepository.findAll();
        model.addAttribute("discussions", discussion);
        return "admin/adminpanel";
    }

    /**
     * Get all attachments
     * @param model
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/attachments")
    public String panelAttachments (Model model) {
        model.addAttribute("attachments", attachmentRepository.findAll());
        return "admin/adminpanel";
    }

    /**
     * Download attachment file by ID
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadAttachment(@PathVariable Long id) {
        return attachmentRepository.findById(id)
                .map(attachment -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.parseMediaType(attachment.getMimeType()));
                    headers.setContentDispositionFormData("attachment", attachment.getFilename());
                    headers.setContentLength(attachment.getFile().length);
                    
                    return ResponseEntity.ok()
                            .headers(headers)
                            .body(attachment.getFile());
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete methods
    /**
     * Delete user by ID
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }

    /**
     * Delete discussion by ID
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/discussions/delete/{id}")
    public String deleteDiscussion(@PathVariable Long id) {
        discussionRepository.deleteById(id);
        return "redirect:/admin/discussions";
    }

    /**
     * Delete attachment by ID
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/attachments/delete/{id}")
    public String deleteAttachment(@PathVariable Long id) {
        attachmentRepository.deleteById(id);
        return "redirect:/admin/attachments";
    }

    /**
     * Delete chat by ID
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/chats/delete/{id}")
    public String deleteChat(@PathVariable Long id) {
        chatRepository.deleteById(id);
        return "redirect:/admin/chats";
    }

}
