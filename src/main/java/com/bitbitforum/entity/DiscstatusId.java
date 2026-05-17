package com.bitbitforum.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class DiscstatusId implements java.io.Serializable {
    private static final long serialVersionUID = 1282327485336960166L;
    @Column(name = "usr_id", nullable = false)
    private Long usrId;

    @Column(name = "disc_id", nullable = false)
    private Long discId;

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    public Long getDiscId() {
        return discId;
    }

    public void setDiscId(Long discId) {
        this.discId = discId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DiscstatusId entity = (DiscstatusId) o;
        return Objects.equals(this.discId, entity.discId) &&
                Objects.equals(this.usrId, entity.usrId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discId, usrId);
    }

}