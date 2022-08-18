package com.unipi.ipap.springnativecrudgraalvm.controller;

import com.unipi.ipap.springnativecrudgraalvm.dto.CartDto;
import com.unipi.ipap.springnativecrudgraalvm.dto.ItemsToCartRequestDto;
import com.unipi.ipap.springnativecrudgraalvm.service.CartService;
import com.unipi.ipap.springnativecrudgraalvm.util.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PutMapping("/add")
    public ResponseEntity<CartDto> addItemsToCustomerCart(@RequestBody ItemsToCartRequestDto itemsToCartRequestDto) {
        return ResponseEntity.ok(Mapper.toDto(cartService.addItemsToCart(itemsToCartRequestDto)));
    }

    @PutMapping("/remove")
    public ResponseEntity<CartDto> removeItemFromCart(@RequestParam String cartId, @RequestParam String itemId) {
        return ResponseEntity.ok(Mapper.toDto(
                cartService.removeItemFromCart(UUID.fromString(cartId), UUID.fromString(itemId))));
    }
}
