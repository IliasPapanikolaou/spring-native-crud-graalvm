package com.unipi.ipap.springnativecrudgraalvm.controller;

import com.unipi.ipap.springnativecrudgraalvm.dto.ItemDto;
import com.unipi.ipap.springnativecrudgraalvm.entity.Item;
import com.unipi.ipap.springnativecrudgraalvm.service.ItemService;
import com.unipi.ipap.springnativecrudgraalvm.util.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
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
    public ResponseEntity<ItemDto> addItem(@RequestBody Item item) {
        return new ResponseEntity<>(Mapper.toDto(itemService.addItem(item)), HttpStatus.CREATED);
    }

    @PostMapping("/items/add")
    public ResponseEntity<List<ItemDto>> addItems(@RequestBody List<Item> items) {
        List<ItemDto> itemDtos = itemService.addItems(items).stream().map(Mapper::toDto).toList();
        return new ResponseEntity<>(itemDtos, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable UUID id) {
        ItemDto itemDto = itemService.getItem(id).map(Mapper::toDto).orElseThrow(EntityExistsException::new);
        return ResponseEntity.ok(itemDto);
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemDto>> getAllItems() {
        List<ItemDto> itemDtos = itemService.getItems().stream().map(Mapper::toDto).toList();
        return ResponseEntity.ok(itemDtos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id) {
        itemService.deleteItemById(id);
        return ResponseEntity.noContent().build();
    }

}
