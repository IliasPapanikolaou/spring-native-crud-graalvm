package com.unipi.ipap.springnativecrudgraalvm.entity;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "CART")
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    @OneToOne(mappedBy = "cart")
    private Customer customer;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Item> items;

    // No Args Constructor
    public Cart() {
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public UUID getId() {
        return id;
    }

    public Set<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", items=" + items +
                '}';
    }
}
