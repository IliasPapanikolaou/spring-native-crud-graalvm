package com.unipi.ipap.springnativecrudgraalvm.util;

import com.unipi.ipap.springnativecrudgraalvm.dto.*;
import com.unipi.ipap.springnativecrudgraalvm.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class Mapper {

    public static CustomerDto toDto(Customer customer) {
        Set<AddressDto> addressDtos = customer.getAddressList().stream()
                .map(Mapper::toDto)
                .collect(Collectors.toSet());

        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getAge(),
                customer.getEmail(),
                customer.getPhone(),
                addressDtos,
                toDto(customer.getCart())
        );
    }

    public static AddressDto toDto(Address address) {
        return new AddressDto(
                address.getStreet(),
                address.getNumber(),
                address.getCity(),
                address.getPostalCode(),
                address.getCountry()
        );
    }

    public static CartDto toDto(Cart cart) {
        List<ItemDto> itemDtos =
        Objects.nonNull(cart.getItems())
                ? cart.getItems().stream().map(Mapper::toDto).toList()
                : new ArrayList<>();

        return new CartDto(cart.getId().toString(), itemDtos);
    }

    public static ItemDto toDto(Item item) {
        return new ItemDto(
                item.getId().toString(),
                toDto(item.getProduct())
        );
    }

    public static ProductDto toDto(Product product) {
        String id = Objects.nonNull(product.getId()) ? product.getId().toString() : null;
        return new ProductDto(
                id,
                product.getDescription(),
                product.getQuantity(),
                product.getPrice());
    }

    public static Customer toEntity(CustomerDto customerDto) {
        Set<Address> addressList = customerDto.addressDtos().stream()
                .map(Mapper::toEntity)
                .collect(Collectors.toSet());

        return new Customer(
                customerDto.name(),
                customerDto.age(),
                customerDto.email(),
                customerDto.phone(),
                addressList);
    }

    public static Address toEntity(AddressDto addressDto) {
        return new Address(
                addressDto.street(),
                addressDto.number(),
                addressDto.city(),
                addressDto.postalCode(),
                addressDto.city());
    }

    public static Product toEntity(ProductDto productDto) {
        return new Product(
                productDto.description(),
                productDto.quantity(),
                productDto.price());
    }

    public static Item toEntity(ItemDto itemDto) {
        return new Item(toEntity(itemDto.product()));
    }

}
