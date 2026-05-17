package com.bitbitforum.service;

import com.bitbitforum.entity.Chat;
import com.bitbitforum.entity.ChatstatusId;
import com.bitbitforum.repository.ChatRepository;
import com.bitbitforum.repository.ChatStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ChatStatusRepository statusRepository;

    // Check password
    public boolean isCorrectPassword(Long chatId, String password) {
        Optional<Chat> chatOptional = chatRepository.findById(chatId);
        
        if (!chatOptional.isPresent()) {
            return false;
        }
        
        Chat chat = chatOptional.get();
        String chatPassword = chat.getPasswordChat();
        
        // If chat has no password, allow access (regardless of input)
        if (chatPassword == null || chatPassword.trim().isEmpty()) {
            return true;
        }
        
        // If chat has password, check if input matches
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        
        return passwordEncoder.matches(password, chatPassword);
    }
    // All user's chats with information
    public List<Map<String, Object>> findAllChatsWithInfoByUserId(Long userId) {
        List<Chat> allUserChats = chatRepository.findChatsByUserId(userId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Chat chat : allUserChats) {
            Map<String, Object> map = new HashMap<>();

            map.put("id", chat.getId());
            map.put("name", chat.getNameChat());
            map.put("members", statusRepository.findAllUsersByChatId(chat.getId()));
            map.put("ownersId", statusRepository.findUsrIdByChatId(chat.getId()));

            result.add(map);
        }
        return result;
    }
    // All chats with information
    public List<Map<String, Object>> findAllChatsWithInfo() {
        List<Chat> allChats = chatRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Chat chat : allChats) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", chat.getId());
            map.put("name", chat.getNameChat());
            map.put("members", statusRepository.findAllUsersByChatId(chat.getId()).toString().replaceAll("\\[|\\]", ""));
            map.put("ownersId", statusRepository.findUsrIdByChatId(chat.getId()).toString().replaceAll("\\[|\\]", ""));
            result.add(map);
        }
        return result;
    }
}
