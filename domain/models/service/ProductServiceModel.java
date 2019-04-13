package com.detelin.caseforce.domain.models.service;

import com.detelin.caseforce.domain.entities.enums.ProductStatus;

import java.util.List;

public class ProductServiceModel {
    private String name;
    private String imageUrl;
    private ProductStatus availability;
    private List<String> subcategories;

    public ProductServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ProductStatus getAvailability() {
        return availability;
    }

    public void setAvailability(ProductStatus availability) {
        this.availability = availability;
    }

    public List<String> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<String> subcategories) {
        this.subcategories = subcategories;
    }
}
