package com.unipi.ipap.springnativecrudgraalvm.dto;

public record AddressDto(
        String street,
        String number,
        String city,
        String postalCode,
        String county
) {
}
