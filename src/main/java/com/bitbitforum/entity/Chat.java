package com.bitbitforum.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_id_gen")
    @SequenceGenerator(name = "chat_id_gen", sequenceName = "chat_id_chat_seq", allocationSize = 1)
    @Column(name = "id_chat", nullable = false)
    private Integer id;

    @Column(name = "name_chat", nullable = false, length = 100)
    private String nameChat;

    @Column(name = "password_chat")
    private String passwordChat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

}