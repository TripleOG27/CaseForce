package com.detelin.caseforce.domain.entities;

import com.detelin.caseforce.domain.entities.enums.CommentMode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
    private String text;
    private CommentMode mode;
    private LocalDateTime time;

    public Comment() {
    }
    @Column(name = "text",columnDefinition = "TEXT")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    @Column(name = "mode")
    @Enumerated(value = EnumType.STRING)
    public CommentMode getMode() {
        return mode;
    }

    public void setMode(CommentMode mode) {
        this.mode = mode;
    }
    @Column(name = "added")
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
