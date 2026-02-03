package com.bitbitforum.service;

import com.bitbitforum.entity.Discussion;
import com.bitbitforum.entity.User;
import com.bitbitforum.repository.DiscussionRepository;
import com.bitbitforum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiscussionService {
    @Autowired
    private DiscussionRepository discussionRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Map<String, Object>> findAll() {
        List<Discussion> discussions = discussionRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();
        
        for (Discussion discussion : discussions) {
            Map<String, Object> discussionMap = new HashMap<>();
            discussionMap.put("id", discussion.getId());
            discussionMap.put("theme", discussion.getTheme());
            discussionMap.put("answersCnt", discussion.getAnswersCnt());
            
            if (discussion.getDateDisc() != null) {
                LocalDateTime dateTime = LocalDateTime.ofInstant(discussion.getDateDisc(), java.time.ZoneId.systemDefault());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                discussionMap.put("date", dateTime.format(formatter));
            } else {
                discussionMap.put("date", "N/A");
            }
            
            if (discussion.getAuthor() != null) {
                discussionMap.put("author", discussion.getAuthor().getUsername());
            } else {
                discussionMap.put("author", "Unknown");
            }
            
            result.add(discussionMap);
        }
        
        return result;
    }

    public List<Map<String, Object>> findAllByUser(User user) {
        List<Discussion> discussions = discussionRepository.findByAuthorIdUsr(user.getIdUsr());
        List<Map<String, Object>> result = new ArrayList<>();

        for (Discussion discussion : discussions) {
            Map<String, Object> discussionMap = new HashMap<>();
            discussionMap.put("id", discussion.getId());
            discussionMap.put("theme", discussion.getTheme());
            discussionMap.put("answersCnt", discussion.getAnswersCnt());

            if (discussion.getDateDisc() != null) {
                LocalDateTime dateTime = LocalDateTime.ofInstant(discussion.getDateDisc(), java.time.ZoneId.systemDefault());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                discussionMap.put("date", dateTime.format(formatter));
            } else {
                discussionMap.put("date", "N/A");
            }

            if (discussion.getAuthor() != null) {
                discussionMap.put("author", discussion.getAuthor().getUsername());
            } else {
                discussionMap.put("author", "Unknown");
            }

            result.add(discussionMap);
        }

        return result;
    }
}
