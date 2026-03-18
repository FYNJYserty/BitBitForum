package com.bitbitforum.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "disccomment")
public class Disccomment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disccomment_id_gen")
    @SequenceGenerator(name = "disccomment_id_gen", sequenceName = "disccomment_id_disc_comment_seq", allocationSize = 1)
    @Column(name = "id_disc_comment", nullable = false)
    private Long id;

    @Column(name = "text_com", nullable = false, length = Integer.MAX_VALUE)
    private String textCom;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "date_com")
    private LocalDateTime dateCom;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usr_id")
    private User usr;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "disc_id")
    private Discussion disc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextCom() {
        return textCom;
    }

    public void setTextCom(String textCom) {
        this.textCom = textCom;
    }

    public LocalDateTime getDateCom() {
        return dateCom;
    }

    public void setDateCom(LocalDateTime dateCom) {
        this.dateCom = dateCom;
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    public Discussion getDisc() {
        return disc;
    }

    public void setDisc(Discussion disc) {
        this.disc = disc;
    }

}