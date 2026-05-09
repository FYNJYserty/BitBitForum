package com.bitbitforum.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "attachment")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attachment_id_gen")
    @SequenceGenerator(name = "attachment_id_gen", sequenceName = "attachment_id_att_seq", allocationSize = 1)
    @Column(name = "id_att", nullable = false)
    private Integer id;

    @Column(name = "mime_type", nullable = false, length = 100)
    private String mimeType;

    @Column(name = "file", nullable = false)
    private byte[] file;

    @Column(name = "filename", nullable = false)
    private String filename;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "chatcom_id")
    private Chatcomment chatcom;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "disccom_id")
    private Disccomment disccom;

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

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Chatcomment getChatcom() {
        return chatcom;
    }

    public void setChatcom(Chatcomment chatcom) {
        this.chatcom = chatcom;
    }

    public Disccomment getDisccom() {
        return disccom;
    }

    public void setDisccom(Disccomment disccom) {
        this.disccom = disccom;
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

}