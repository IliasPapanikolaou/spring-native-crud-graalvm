package com.unipi.ipap.springnativecrudgraalvm.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.IntStream;

@Entity
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;
    @Size(min = 5, max = 100, message = "Description should be between 5 and 255 characters")
    private String description;
    private Integer quantity;
    private Double price;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Item> items;

    // No Args Constructor
    public Product() {
        this.items = new ArrayList<>();
    }

    // Args Constructor
    public Product(String description, Integer quantity, Double price) {
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.items = addProductItems(description, quantity, price);
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> addProductItems(String description, Integer quantity, Double price) {
        return IntStream.range(0, quantity)
                .mapToObj(item -> new Item(this))
                .toList();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
