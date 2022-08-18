package com.unipi.ipap.springnativecrudgraalvm.service;

import com.unipi.ipap.springnativecrudgraalvm.dto.ItemDto;
import com.unipi.ipap.springnativecrudgraalvm.dto.ItemsToCartRequestDto;
import com.unipi.ipap.springnativecrudgraalvm.entity.Cart;
import com.unipi.ipap.springnativecrudgraalvm.entity.Item;
import com.unipi.ipap.springnativecrudgraalvm.entity.Product;
import com.unipi.ipap.springnativecrudgraalvm.repository.CartRepository;
import com.unipi.ipap.springnativecrudgraalvm.repository.CustomerRepository;
import com.unipi.ipap.springnativecrudgraalvm.repository.ItemRepository;
import com.unipi.ipap.springnativecrudgraalvm.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public List<Cart> findAllCarts() {
        return cartRepository.findAll();
    }

    public CartService(CustomerRepository customerRepository, ItemRepository itemRepository,
                       CartRepository cartRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public List<Cart> saveAllCarts(List<Cart> carts) {
        return cartRepository.saveAll(carts);
    }

    @Transactional
    public Cart addItemsToCart(ItemsToCartRequestDto itemsToCartRequestDto) {
        Cart cart = cartRepository.findById(UUID.fromString(itemsToCartRequestDto.cartId()))
                .orElseThrow(EntityNotFoundException::new);

        // Map Dto String UUIDs to a Set of UUIDs
        List<UUID> uuids = itemsToCartRequestDto.items().stream()
                .map(ItemDto::itemId)
                .map(UUID::fromString)
                .toList();

        List<Item> items = itemRepository.findAllById(uuids);
        items.stream()
                .filter(Item::getAvailable)
                .peek(item -> {
                    itemRepository.updateItemAvailabilityById(false, item.getId());
                    Product product = productRepository.getReferenceById(item.getProduct().getId());
                    product.setQuantity(product.getQuantity() - 1);
                })
                .forEach(cart::addItem);
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart removeItemFromCart(UUID cartId, UUID itemId) {
        Cart cart = cartRepository.getReferenceById(cartId);
        Item item = cart.getItems().stream()
                .filter(itm-> itm.getId().equals(itemId))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
        cart.getItems().remove(item);
        itemRepository.updateItemAvailabilityById(true, itemId);
        Product product = productRepository.getReferenceById(item.getProduct().getId());
        product.setQuantity(product.getQuantity() + 1);
        return cartRepository.save(cart);
    }
}
