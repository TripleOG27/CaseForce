package com.detelin.caseforce.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "licenses")
public class License extends BaseEntity {
    private String name;
    private BigDecimal price;
    private long period;


    public License() {
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    @Column(name = "valid_time")
    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }



}
