package com.unipi.ipap.springnativecrudgraalvm.dto;

import java.util.List;

public record ItemsToCartRequestDto(
        String cartId,
        List<ItemDto> items
) {
}
