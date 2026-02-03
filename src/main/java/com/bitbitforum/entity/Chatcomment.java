package com.bitbitforum.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "chatcomment")
public class Chatcomment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatcomment_id_gen")
    @SequenceGenerator(name = "chatcomment_id_gen", sequenceName = "chatcomment_id_com_seq", allocationSize = 1)
    @Column(name = "id_com", nullable = false)
    private Integer id;

    @Column(name = "text_com", nullable = false, length = Integer.MAX_VALUE)
    private String textCom;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "date_com")
    private Instant dateCom;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usr_id")
    private User usr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextCom() {
        return textCom;
    }

    public void setTextCom(String textCom) {
        this.textCom = textCom;
    }

    public Instant getDateCom() {
        return dateCom;
    }

    public void setDateCom(Instant dateCom) {
        this.dateCom = dateCom;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

}