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
    private List<String> subcategories;
    private List<License> licenses;

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
    @ManyToMany(targetEntity = License.class)
    @JoinTable(name = "products_licenses",joinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id")
            ,inverseJoinColumns = @JoinColumn(name = "license_id",referencedColumnName = "id"))
    public List<License> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<License> licenses) {
        this.licenses = licenses;
    }
    @Column(name = "subcategories")
    @ElementCollection
    public List<String> getSubcategories() {
        this.subcategories = new LinkedList<>();
        return subcategories;
    }

    public void setSubcategories(List<String> subcategories) {
        this.subcategories = subcategories;
    }
}
