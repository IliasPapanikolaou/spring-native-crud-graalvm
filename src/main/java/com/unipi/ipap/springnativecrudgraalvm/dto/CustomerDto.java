package com.unipi.ipap.springnativecrudgraalvm.dto;

import java.util.Set;

public record CustomerDto(
        Long id,
        String name,
        Integer age,
        String email,
        String phone,
        Set<AddressDto> addressDtos,
        CartDto cart
) {
}
