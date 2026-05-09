package com.bitbitforum.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class ChatstatusId implements java.io.Serializable {
    private static final long serialVersionUID = 278447766718275335L;
    @Column(name = "chat_id", nullable = false)
    private Long chatId;

    @Column(name = "usr_id", nullable = false)
    private Long usrId;

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ChatstatusId entity = (ChatstatusId) o;
        return Objects.equals(this.chatId, entity.chatId) &&
                Objects.equals(this.usrId, entity.usrId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, usrId);
    }

}