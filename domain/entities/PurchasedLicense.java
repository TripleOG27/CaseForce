package com.detelin.caseforce.domain.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "name")
public class PurchasedLicense extends License {
    private List<Product> product;
    private List<User> clients;

    private LocalDate purchasedDate;
    private long period;
    private LocalDate validThrough;

    public PurchasedLicense() {

    }
    @Column(name = "purchased_date")
    public LocalDate getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(LocalDate purchasedDate) {
        this.purchasedDate = purchasedDate;
    }
    @ManyToMany(targetEntity = User.class,cascade = CascadeType.ALL)
    @JoinTable(name = "clients_licenses",joinColumns =
    @JoinColumn(name = "client_id",referencedColumnName = "id"),inverseJoinColumns =
    @JoinColumn(name = "license_id",referencedColumnName = "id"))
    public List<User> getClients() {
        if(this.clients==null)this.clients= new ArrayList<>();
        return clients;
    }

    public void setClients(List<User> clients) {
        this.clients = clients;
    }
    @ManyToMany(targetEntity = Product.class,cascade = CascadeType.ALL)
    @JoinTable(name = "licenses_products",joinColumns =
    @JoinColumn(name = "license_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id"))
    public List<Product> getProduct()
    {
        if(this.product==null)this.product=new ArrayList<>();
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
    @Column(name = "valid_time")
    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }
    @Transient
    public LocalDate getValidThrough() {
        this.validThrough=this.getPurchasedDate().plusMonths(this.getPeriod());
        return this.validThrough;
    }

    public void setValidThrough(LocalDate validThrough) {
        this.validThrough = validThrough;
    }
}
