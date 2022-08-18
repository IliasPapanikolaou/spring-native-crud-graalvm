package com.unipi.ipap.springnativecrudgraalvm.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 30, message = "Street name should be between 2 and 30 characters")
    private String street;
    @NotNull(message = "Street number should not be empty")
    private String number;
    @Size(min = 2, max = 20, message = "City name should be between 2 and 20 characters")
    private String city;
    @Size(min = 3, max = 10, message = "Postal Code should be between 3 and 10 characters")
    private String postalCode;
    @Size(min = 2, max = 60, message = "Country name should be between 2 and 60 characters")
    private String country;

    // No Args Constructor
    public Address() {
    }

    // Args Constructor
    public Address(String street, String number, String city, String postalCode, String country) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long addressId) {
        this.id = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;

        if (!Objects.equals(id, address.id)) return false;
        if (!Objects.equals(street, address.street)) return false;
        if (!Objects.equals(number, address.number)) return false;
        return Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
