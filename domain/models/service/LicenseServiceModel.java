package com.detelin.caseforce.domain.models.service;

import java.math.BigDecimal;
import java.util.List;

public class LicenseServiceModel {
    private String name;
    private BigDecimal price;
    private long period;
    private List<String> product;

    public LicenseServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public List<String> getProduct() {
        return product;
    }

    public void setProduct(List<String> product) {
        this.product = product;
    }
}
