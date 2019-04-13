package com.detelin.caseforce.domain.models.binding;

import java.math.BigDecimal;
import java.util.List;

public class LicenseAddBindingModel {
    private String name;
    private BigDecimal price;
    private long period;


    public LicenseAddBindingModel() {
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
