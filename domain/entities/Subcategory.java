package com.detelin.caseforce.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "subcategories")
public class Subcategory extends BaseEntity {
    private String subcategory;
    private Product product;

    public Subcategory() {
    }
    @Column(name = "name")
    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }
    @ManyToOne(targetEntity = Product.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
