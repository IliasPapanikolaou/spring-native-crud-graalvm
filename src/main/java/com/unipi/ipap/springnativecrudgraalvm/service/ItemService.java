package com.unipi.ipap.springnativecrudgraalvm.service;

import com.unipi.ipap.springnativecrudgraalvm.entity.Item;
import com.unipi.ipap.springnativecrudgraalvm.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> addItems(List<Item> items) {
        return itemRepository.saveAll(items);
    }

    public Optional<Item> getItem(UUID id) {
        return itemRepository.findById(id);
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @Transactional
    public int setItemAvailability(Boolean isAvailable, UUID uuid) {
        return itemRepository.updateItemAvailabilityById(isAvailable, uuid);
    }

    @Transactional
    public void deleteItemById(UUID id) {
        itemRepository.deleteById(id);
    }

}
