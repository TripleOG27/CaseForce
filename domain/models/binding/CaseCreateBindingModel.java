package com.detelin.caseforce.domain.models.binding;

import com.detelin.caseforce.domain.entities.Comment;
import com.detelin.caseforce.domain.entities.User;
import com.detelin.caseforce.domain.entities.enums.CaseStatus;

import java.time.LocalDateTime;
import java.util.List;

public class CaseCreateBindingModel {
    private String subject;
    private String description;
    private List<Comment> comments;
    private String customer;
    private String category;
    private String subcategory;

    public CaseCreateBindingModel() {
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
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
