package com.bitbitforum.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "discussion")
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discussion_id_gen")
    @SequenceGenerator(name = "discussion_id_gen", sequenceName = "discussion_id_disc_seq", allocationSize = 1)
    @Column(name = "id_disc", nullable = false)
    private Long id;

    @Column(name = "theme", nullable = false, length = 200)
    private String theme;

    @Column(name = "text_disc", nullable = false, length = Integer.MAX_VALUE)
    private String textDisc;

    @ColumnDefault("0")
    @Column(name = "answers_cnt")
    private Integer answersCnt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "date_disc")
    private LocalDateTime dateDisc;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "author_id")
    private User author;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getDateDisc() {
        return dateDisc;
    }

    public void setDateDisc(LocalDateTime dateDisc) {
        this.dateDisc = dateDisc;
    }

    public Integer getAnswersCnt() {
        return answersCnt;
    }

    public void setAnswersCnt(Integer answersCnt) {
        this.answersCnt = answersCnt;
    }

    public String getTextDisc() {
        return textDisc;
    }

    public void setTextDisc(String textDisc) {
        this.textDisc = textDisc;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}