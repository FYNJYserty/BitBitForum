package com.bitbitforum.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_id_gen")
    @SequenceGenerator(name = "chat_id_gen", sequenceName = "chat_id_chat_seq", allocationSize = 1)
    @Column(name = "id_chat", nullable = false)
    private Long id;

    @Column(name = "name_chat", nullable = false, length = 100)
    private String nameChat;

    @Column(name = "password_chat")
    private String passwordChat;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Chatstatus> chatstatuses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameChat() {
        return nameChat;
    }

    public void setNameChat(String nameChat) {
        this.nameChat = nameChat;
    }

    public String getPasswordChat() {
        return passwordChat;
    }

    public void setPasswordChat(String passwordChat) {
        this.passwordChat = passwordChat;
    }

    public List<Chatstatus> getChatstatuses() {
        return chatstatuses;
    }

    public void setChatstatuses(List<Chatstatus> chatstatuses) {
        this.chatstatuses = chatstatuses;
    }
}