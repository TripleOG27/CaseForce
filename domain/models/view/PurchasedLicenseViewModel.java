package com.detelin.caseforce.domain.models.view;

import com.detelin.caseforce.domain.entities.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PurchasedLicenseViewModel {
    private String name;
    private BigDecimal price;
    private List<Product> product;
    private LocalDate purchasedDate;
    private long period;
    private LocalDate validThrough;

    public PurchasedLicenseViewModel() {
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

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public LocalDate getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(LocalDate purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public LocalDate getValidThrough() {
        return this.validThrough;
    }

    public void setValidThrough(LocalDate validThrough) {
        this.validThrough = validThrough;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }
}
