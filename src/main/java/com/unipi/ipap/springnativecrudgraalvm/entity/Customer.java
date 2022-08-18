package com.unipi.ipap.springnativecrudgraalvm.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Size(min = 5, max = 30, message = "Name should be between 5 and 30 characters")
    private String name;
    @Min(value = 18, message = "Customer should be at least 18")
    @Max(value = 100, message = "Customer can't be greater than 100")
    private Integer age;
    @Email(message = "Email should be valid")
    private String email;
    @NotNull(message = "Phone should not be empty")
    private String phone;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Address> addressList;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    // No Args Constructor
    public Customer() {
        this.cart = new Cart(this);
    }

    // Args Constructor
    public Customer(String name, Integer age, String email, String phone, Set<Address> address) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.addressList = address;
        this.cart = new Cart(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(Set<Address> address) {
        this.addressList = address;
    }

    public Cart getCart() {
        return cart;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;

        if (!Objects.equals(id, customer.id)) return false;
        if (!Objects.equals(name, customer.name)) return false;
        if (!Objects.equals(age, customer.age)) return false;
        if (!Objects.equals(email, customer.email)) return false;
        if (!Objects.equals(phone, customer.phone)) return false;
        if (!Objects.equals(addressList, customer.addressList)) return false;
        return Objects.equals(cart, customer.cart);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (addressList != null ? addressList.hashCode() : 0);
        result = 31 * result + (cart != null ? cart.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + addressList +
                ", cart=" + cart +
                '}';
    }
}
