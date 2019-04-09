package com.detelin.caseforce.domain.entities;

import com.detelin.caseforce.domain.entities.enums.CaseStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cases")
public class Case {
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

    public Case() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column(name = "subject",updatable = false,nullable = false)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    @Column(name = "description",columnDefinition = "TEXT",updatable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    public CaseStatus getStatus() {
        return status;
    }

    public void setStatus(CaseStatus status) {
        this.status = status;
    }
    @Column(name = "opened_time")
    public LocalDateTime getOpenedTime() {
        return openedTime;
    }

    public void setOpenedTime(LocalDateTime openedTime) {
        this.openedTime = openedTime;
    }
    @Column(name = "closed_time")
    public LocalDateTime getClosedTime() {
        return closedTime;
    }

    public void setClosedTime(LocalDateTime closedTime) {
        this.closedTime = closedTime;
    }
    @OneToMany(targetEntity = Comment.class)
    @JoinColumn(name = "comment_id",referencedColumnName = "id")
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "owner_id",referencedColumnName = "id")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @Column(name = "subcategory",nullable = false)
    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }
}
