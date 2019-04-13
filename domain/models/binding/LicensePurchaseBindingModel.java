package com.detelin.caseforce.domain.models.binding;

import java.util.List;

public class LicensePurchaseBindingModel {
    private String license;
    private List<String> products;

    public LicensePurchaseBindingModel() {
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }
}
