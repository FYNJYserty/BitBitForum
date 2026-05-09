package com.bitbitforum.controller;

import com.bitbitforum.config.CustomUserDetails;
import com.bitbitforum.entity.*;
import com.bitbitforum.repository.AttachmentRepository;
import com.bitbitforum.repository.ChatCommentRepository;
import com.bitbitforum.repository.ChatRepository;
import com.bitbitforum.repository.ChatStatusRepository;
import com.bitbitforum.service.AttachmentService;
import com.bitbitforum.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private ChatRepository chatRepository; // Chat repository
    @Autowired
    private ChatService chatService; // Chat service
    @Autowired
    private ChatCommentRepository chatCommentRepository; // Chat comment repository
    @Autowired
    private PasswordEncoder passwordEncoder; // Password encoder
    @Autowired
    private ChatStatusRepository chatStatusRepository; // Chat status repository
    @Autowired
    private AttachmentService attachmentService; // Attachment service
    @Autowired
    private AttachmentRepository attachmentRepository; // Attachment repository

    /**
     * Get all user's chats
     * @param userDetails
     * @param model
     * @return
     * @throws SQLException
     */
    // Get methods
    @GetMapping
    public String onlineChat(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) throws SQLException {
        Long userId = userDetails.getUser().getIdUsr();
        model.addAttribute("chats", chatService.findAllChatsWithInfoByUserId(userId));
        model.addAttribute("currentUserId", userId);
        return "chat/chats";
    }
    /**
     * Get menu for entering the chat
     * @param model
     * @return
     * @throws SQLException
     */
    @GetMapping("/enterchat")
    public String enterChat(Model model) throws SQLException {
        model.addAttribute("chat", new Chat());
        return "chat/enterchat";
    }
    /**
     * Get menu of creating new chat
     * @param model
     * @return
     * @throws SQLException
     */
    @GetMapping("/createnew")
    public String createNew(Model model) throws SQLException {
        model.addAttribute("chat", new Chat());
        return "chat/newchat";
    }

    /**
     * Get current chat
     * @param id
     * @param userDetails
     * @param model
     * @return
     * @throws SQLException
     */
    @GetMapping("/{id}")
    public String getCurrentChat(@PathVariable Long id,
                                 @AuthenticationPrincipal CustomUserDetails userDetails,
                                 Model model) throws SQLException {
        List<Chatcomment> messages = chatCommentRepository.findAllByChatId(id);
        model.addAttribute("chatMessages", messages);
        model.addAttribute("userId", userDetails.getUser().getIdUsr());

        // Load attachments for each message
        Map<Long, List<Attachment>> attachmentsMap = messages.stream()
            .collect(Collectors.toMap(
                message -> message.getId(),
                message -> attachmentRepository.findByChatcom(message)
            ));
        
        model.addAttribute("attachmentsMap", attachmentsMap);

        Chat chat = chatRepository.findChatById(id);
        model.addAttribute("chatInfo", chat);
        return "chat/currentchat";
    }

    /**
     * Get download attachment
     * @param attachmentId
     * @return
     */
    @GetMapping("/download/{attachmentId}")
    public ResponseEntity<Resource> downloadAttachment(@PathVariable Long attachmentId) {
        Attachment attachment = attachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));

        ByteArrayResource resource = new ByteArrayResource(attachment.getFile());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getMimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFilename() + "\"")
                .body(resource);
    }

    // Post methods

    /**
     * Post create new chat
     * @param userDetails
     * @param chat
     * @param model
     * @return
     * @throws SQLException
     */
    @PostMapping("/createnew")
    public String createNewChat(@AuthenticationPrincipal CustomUserDetails userDetails,
                                @ModelAttribute("chat") Chat chat,
                                Model model) throws SQLException {
        // Creating new chat
        chat.setPasswordChat(passwordEncoder.encode(chat.getPasswordChat()));
        chatRepository.save(chat);

        // Updating chat status id
        ChatstatusId newChatStatusId = new ChatstatusId();
        newChatStatusId.setChatId(chat.getId());
        newChatStatusId.setUsrId(userDetails.getUser().getIdUsr());
        // Updating chat status
        Chatstatus chatStatus = new Chatstatus();
        chatStatus.setId(newChatStatusId);
        chatStatus.setChat(chat);
        chatStatus.setUsr(userDetails.getUser());
        chatStatus.setStatus("admin");
        chatStatusRepository.save(chatStatus);
        return "redirect:/chats/" + chat.getId();
    }
    /**
     * Post entering the chat
     * @param userDetails
     * @param chat
     * @param model
     * @return
     * @throws SQLException
     */
    @PostMapping("/enterchat")
    public String enterChat(@AuthenticationPrincipal CustomUserDetails userDetails,
                            @ModelAttribute("chat") Chat chat,
                            Model model) throws SQLException {
        // Check if
        if (!chatRepository.existsById(chat.getId())) { // If chat doesn't exist
            model.addAttribute("errorMessage", "The chat does not exist");
            return "chat/enterchat";
        } else if (chatStatusRepository.existsByChatIdAndUsrId(chat.getId(), userDetails.getUser().getIdUsr())) { // If user is still in a chat
            model.addAttribute("errorMessage", "The user is in a chat");
            return "chat/enterchat";
        } else if (!chatService.isCorrectPassword(chat.getId(), chat.getPasswordChat())) { // If wrong password
            model.addAttribute("errorMessage", "The password is incorrect");
            return "chat/enterchat";
        }
        // Updating chat status id entity
        ChatstatusId chatstatusId = new ChatstatusId();
        chatstatusId.setChatId(chat.getId());
        chatstatusId.setUsrId(userDetails.getUser().getIdUsr());
        // Updating chat status
        Chatstatus chatStatus = new Chatstatus();
        chatStatus.setId(chatstatusId);
        chatStatus.setChat(chat);
        chatStatus.setUsr(userDetails.getUser());
        chatStatus.setStatus("user");
        chatStatusRepository.save(chatStatus);
        return "redirect:/chats/" + chat.getId();
    }

    /**
     * Post create new message
     * @param id
     * @param text
     * @param files
     * @param userDetails
     * @return
     */
    @PostMapping("/{id}")
    public String createNewMessage(@PathVariable Long id,
                                   @RequestParam("text") String text,
                                   @RequestParam(value = "files", required = false) MultipartFile[] files,
                                   @AuthenticationPrincipal CustomUserDetails userDetails) {
        // Saving new message by the current user
        Chatcomment chatcomment = new Chatcomment();
        chatcomment.setUsr(userDetails.getUser());
        chatcomment.setDateCom(LocalDateTime.now());
        chatcomment.setTextCom(text);
        chatcomment.setChat(chatRepository.findChatById(id));
        chatCommentRepository.save(chatcomment);

        // Saving files of comment if exists
        List<MultipartFile> allFiles = new ArrayList<>();
        if (files != null) {
            allFiles.addAll(Arrays.stream(files).toList());
        }

        attachmentService.saveWithParams(allFiles,
                null,
                chatCommentRepository.findById(chatcomment.getId()).get(),
                userDetails.getUser());
        return "redirect:/chats/" + id;
    }
    // Delete methods

    /**
     * Delete chat
     * @param id
     * @param model
     * @return
     * @throws SQLException
     */
    @Transactional
    @DeleteMapping("/delete/{id}")
    public String deleteChat(@PathVariable Long id, Model model) throws SQLException {
        // Deleting chat comments
        chatCommentRepository.deleteAllByChatId(id);
        // Deleting chat status
        chatStatusRepository.deleteAllByChatId(id);
        // Deleting chat
        chatRepository.deleteById(id);
        return "redirect:/chats";
    }
}
