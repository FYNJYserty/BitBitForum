package com.bitbitforum.service;

import com.bitbitforum.entity.*;
import com.bitbitforum.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    public AttachmentService(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    public void saveWithParams(List<MultipartFile> attachments, Disccomment disccom, Chatcomment chatcom, User user) {
        for (MultipartFile attachment : attachments) {
            if (attachment != null && !attachment.isEmpty()) {
                try {
                    Attachment attach = new Attachment();
                    attach.setFilename(attachment.getOriginalFilename());
                    attach.setMimeType(attachment.getContentType());
                    attach.setFile(attachment.getBytes());
                    attach.setUsr(user);
                    if (disccom != null) {
                        attach.setDisccom(disccom);
                    } else {
                        attach.setChatcom(chatcom);
                    }

                    attachmentRepository.save(attach);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to save attachment: " + attachment.getOriginalFilename(), e);
                }
            }
        }
    }
}
