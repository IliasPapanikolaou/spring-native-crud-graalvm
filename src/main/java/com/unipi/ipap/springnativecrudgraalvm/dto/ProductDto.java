package com.unipi.ipap.springnativecrudgraalvm.dto;

public record ProductDto(
        String productId,
        String description,
        Integer quantity,
        Double price
) {
}
