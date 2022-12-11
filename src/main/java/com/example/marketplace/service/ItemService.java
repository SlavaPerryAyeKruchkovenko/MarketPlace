package com.example.marketplace.service;

import com.example.marketplace.models.Item;
import com.example.marketplace.models.ItemResponse;
import com.example.marketplace.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    ItemRepository productRepository;

    public Item add(ItemResponse response) {
        return productRepository.save(new Item(response.getName()));
    }

    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<Item> getById(Long id) {
        return productRepository.findById(id);
    }

    public Iterable<Item> getAllItems() {
        return productRepository.findAll();
    }

    public Item save(Item product) {
        return productRepository.save(product);
    }
}
