package com.detelin.caseforce.domain.models.view;

import com.detelin.caseforce.domain.entities.Comment;
import com.detelin.caseforce.domain.entities.User;
import com.detelin.caseforce.domain.entities.enums.CaseStatus;

import java.time.LocalDateTime;
import java.util.List;

public class CaseViewModel {
    private long id;
    private String subject;
    private String description;
    private CaseStatus status;
    private LocalDateTime openedTime;
    private LocalDateTime closedTime;
    private List<Comment> comments;
    private User owner;
    private User customer;
    private String category;
    private String subcategory;

    public CaseViewModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CaseStatus getStatus() {
        return status;
    }

    public void setStatus(CaseStatus status) {
        this.status = status;
    }

    public LocalDateTime getOpenedTime() {
        return openedTime;
    }

    public void setOpenedTime(LocalDateTime openedTime) {
        this.openedTime = openedTime;
    }

    public LocalDateTime getClosedTime() {
        return closedTime;
    }

    public void setClosedTime(LocalDateTime closedTime) {
        this.closedTime = closedTime;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }
}
