package com.unipi.ipap.springnativecrudgraalvm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ITEM")
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;
    private Boolean available;
    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName = "id")
    @JsonBackReference
    private Product product;

    // No Args Constructor
    public Item() {
        this.available = true;
    }

    // Args Constructor
    public Item(Product product) {
        this.available = true;
        this.product = product;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UUID getId() {
        return id;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", available=" + available +
                ", product=" + product +
                '}';
    }
}
