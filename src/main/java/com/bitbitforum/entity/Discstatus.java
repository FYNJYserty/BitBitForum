package com.bitbitforum.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "discstatus")
public class Discstatus {
    @SequenceGenerator(name = "discstatus_id_gen", sequenceName = "discussion_id_disc_seq", allocationSize = 1)
    @EmbeddedId
    private DiscstatusId id;

    @MapsId("usrId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usr_id", nullable = false)
    private User usr;

    @MapsId("discId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "disc_id", nullable = false)
    private Discussion disc;

    @ColumnDefault("'user'")
    @Column(name = "status", length = 20)
    private String status;

    public DiscstatusId getId() {
        return id;
    }

    public void setId(DiscstatusId id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}