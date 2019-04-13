package com.detelin.caseforce.domain.models.binding;

import com.detelin.caseforce.domain.entities.enums.ProductStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class ProductAddBindingModel {
    private String name;
    private MultipartFile imageUrl;
    private ProductStatus availability;
    private List<String> subcategories;

    public ProductAddBindingModel() {
        this.setSubcategories(new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(MultipartFile imageUrl) {
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
