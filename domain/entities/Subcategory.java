package com.detelin.caseforce.domain.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "subcategories")
public class Subcategory extends BaseEntity {
    private String subcategory;

    public Subcategory() {
    }
    @Column(name = "name")
    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }
}
