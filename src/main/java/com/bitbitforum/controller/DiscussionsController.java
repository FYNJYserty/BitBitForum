package com.bitbitforum.controller;

import com.bitbitforum.config.CustomUserDetails;
import com.bitbitforum.entity.*;
import com.bitbitforum.repository.DiscStatusRepository;
import com.bitbitforum.repository.DisccommentRepository;
import com.bitbitforum.repository.DiscussionRepository;
import com.bitbitforum.repository.AttachmentRepository;
import com.bitbitforum.service.AttachmentService;
import com.bitbitforum.service.DiscussionService;
import jakarta.persistence.UniqueConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/discussions")
public class DiscussionsController {

    @Autowired
    private DiscussionService discussionService; // Discussion service
    @Autowired
    private DiscussionRepository discussionRepository; // Discussion repository
    @Autowired
    private DisccommentRepository commentRepository; // Discussion comment repository
    @Autowired
    private AttachmentService attachmentService; // Attachment service
    @Autowired
    private AttachmentRepository attachmentRepository; // Attachment repository
    @Autowired
    private DiscStatusRepository statusRepository; // Discussion status repository

    // Get methods
    /**
     * Get all discussions page
     * @param model
     * @return
     * @throws SQLException
     */
    @GetMapping
    public String discussions(@RequestParam(required = false) String error,
                              @AuthenticationPrincipal CustomUserDetails userDetails,
                              Model model) throws SQLException {
        model.addAttribute("discussions", discussionService.findAll());
        model.addAttribute("statusUser", userDetails.getUser().getIdUsr());
        if (error != null && !error.isEmpty()) {
            model.addAttribute("errorMessage", error);
        }
        return "discussions/discussions";
    }
    /**
     * Get current discussion page
     * @param id
     * @param model
     * @return
     * @throws SQLException
     */
    @GetMapping("/{id}")
    public String getDiscussion(@PathVariable Long id, Model model) throws SQLException {
        // Check if discussion exists
        if (!discussionRepository.existsById(id)) {
            return "redirect:/discussions?error=Discussion with ID " + id + " does not exist";
        }
        
        Discussion discussion = discussionRepository.findById(id).get();
        model.addAttribute("discussioninfo", discussion);
        List<Disccomment> comments = commentRepository.findAllByDisc(discussion);
        
        // Load attachments for each comment
        Map<Long, List<Attachment>> attachmentsMap = comments.stream()
            .collect(Collectors.toMap(
                comment -> comment.getId(),
                comment -> attachmentRepository.findByDisccom(comment)
            ));
        
        model.addAttribute("comments", comments);
        model.addAttribute("attachmentsMap", attachmentsMap);
        return "discussions/currentdisc";
    }
    /**
     * Get create new discussion
     * @param model
     * @return
     */
    @GetMapping("/createnew")
    public String createNewDiscussion(Model model) {
        model.addAttribute("discussion", new Discussion());
        return "discussions/newdiscussion";
    }
    /**
     * Get my discussions
     * @param userDetails
     * @param model
     * @return
     * @throws SQLException
     */
    @GetMapping("/mydisc")
    public String findMyDisc(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) throws SQLException {
        model.addAttribute("discussions", discussionService.findAllByUser(userDetails.getUser()));
        model.addAttribute("statusUser", userDetails.getUser().getIdUsr());
        return "discussions/discussions";
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
     * Post create new discussion method
     * @param discussion
     * @param redirectAttributes
     * @param userDetails
     * @return
     * @throws SQLException
     */
    @PostMapping("/create")
    public String createNewDiscussion(Discussion discussion,
                                      RedirectAttributes redirectAttributes,
                                      @AuthenticationPrincipal CustomUserDetails userDetails) throws SQLException {
        // Creating new discussion
        discussion.setAuthor(userDetails.getUser());
        discussion.setDateDisc(LocalDateTime.now());
        discussion.setAnswersCnt(0);
        discussionRepository.save(discussion);
        // Update discussion status id
        DiscstatusId discstatusId = new DiscstatusId();
        discstatusId.setDiscId(discussion.getId());
        discstatusId.setUsrId(userDetails.getUser().getIdUsr());
        // Updating discussion status
        Discstatus discstatus = new Discstatus();
        discstatus.setId(discstatusId);
        discstatus.setStatus("admin");
        discstatus.setUsr(userDetails.getUser());
        discstatus.setDisc(discussion);
        statusRepository.save(discstatus);
        return "redirect:/discussions/mydisc";
    }

    /**
     * Post create new comment
     * @param userDetails
     * @param id
     * @param text
     * @param files
     * @return
     * @throws SQLException
     */
    @PostMapping("/{id}")
    public String createNewComment(@AuthenticationPrincipal CustomUserDetails userDetails,
                                   @PathVariable Long id,
                                   @RequestParam("text") String text,
                                   @RequestParam(value = "files", required = false) MultipartFile[] files) throws SQLException {
        // Saving the comment of the user
        Disccomment comment = new Disccomment();
        comment.setDateCom(LocalDateTime.now());
        comment.setTextCom(text);
        comment.setDisc(discussionRepository.findById(id).get());
        comment.setUsr(userDetails.getUser());
        commentRepository.save(comment);

        // Saving files of comment if exists
        List<MultipartFile> allFiles = new ArrayList<>();
        if (files != null) {
            allFiles.addAll(Arrays.stream(files).toList());
        }

        attachmentService.saveWithParams(allFiles,
                commentRepository.findById(comment.getId()).get(),
                null,
                userDetails.getUser());
        return "redirect:/discussions/" + id;
    }
    // Delete method

    /**
     * Delete user's discussion
     * @param id
     * @return
     * @throws SQLException
     */
    @Transactional
    @DeleteMapping("/delete/{id}")
    public String deleteDiscussion(@PathVariable Long id) throws SQLException {
        // First check if discussion exists
        if (!discussionRepository.existsById(id)) {
            return "redirect:/discussions?error=Discussion not found";
        }
        // Delete attachments
        // TODO delete attachments in deleted discussion

        // Get discussion before deleting related records
        Discussion discussion = discussionRepository.findById(id).get();
        
        // Delete in correct order: first comments, then statuses, then discussion
        commentRepository.deleteByDiscId(id);
        statusRepository.deleteByDiscId(id);
        discussionRepository.delete(discussion);
        
        return "redirect:/discussions";
    }
}
