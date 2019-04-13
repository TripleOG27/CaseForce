package com.detelin.caseforce.domain.models.view;

import com.detelin.caseforce.domain.entities.Product;

import java.math.BigDecimal;

public class LicenseAllViewModel {
    private String name;
    private BigDecimal price;
    private long period;

    public LicenseAllViewModel() {
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
}
