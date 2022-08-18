package com.unipi.ipap.springnativecrudgraalvm.dto;

import java.util.List;

public record CartDto(
        String cartId,
        List<ItemDto> items
) {
}
