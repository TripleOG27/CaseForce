package com.detelin.caseforce.domain.entities;

import com.detelin.caseforce.domain.entities.enums.ProductStatus;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    private String name;
    private String imageUrl;
    private ProductStatus availability;
    private List<Subcategory> subcategories;
    private List<License> licenses;
    private List<User> clients;

    public Product() {

    }
    @Column(name = "category")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @Column(name = "availability")
    @Enumerated(value = EnumType.STRING)
    public ProductStatus getAvailability() {
        return availability;
    }

    public void setAvailability(ProductStatus availability) {
        this.availability = availability;
    }
    @ManyToMany(targetEntity = License.class,cascade = CascadeType.ALL)
    public List<License> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<License> licenses) {
        this.licenses = licenses;
    }

    @OneToMany(targetEntity = Subcategory.class,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id",referencedColumnName = "id",nullable = true)
    public List<Subcategory> getSubcategories() {
        this.subcategories = new LinkedList<>();
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories=subcategories;
    }
    @ManyToMany(targetEntity = User.class)
    public List<User> getClients() {
        return clients;
    }

    public void setClients(List<User> clients) {
        this.clients = clients;
    }
}
