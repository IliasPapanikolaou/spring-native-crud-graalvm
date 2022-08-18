package com.unipi.ipap.springnativecrudgraalvm.controller;

import com.unipi.ipap.springnativecrudgraalvm.entity.Item;
import com.unipi.ipap.springnativecrudgraalvm.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/add")
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        return new ResponseEntity<>(itemService.addItem(item), HttpStatus.CREATED);
    }

    @PostMapping("/items/add")
    public ResponseEntity<List<Item>> addItems(@RequestBody List<Item> items) {
        return new ResponseEntity<>(itemService.addItems(items), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable UUID id) {
        return ResponseEntity.of(itemService.getItem(id));
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemService.getItems());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id) {
        itemService.deleteItemById(id);
        return ResponseEntity.noContent().build();
    }

}
