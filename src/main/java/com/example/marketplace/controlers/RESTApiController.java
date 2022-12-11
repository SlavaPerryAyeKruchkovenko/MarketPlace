package com.example.marketplace.controlers;

import com.example.marketplace.models.Item;
import com.example.marketplace.models.ItemResponse;
import com.example.marketplace.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RESTApiController {
    private final ItemService itemService;

    public RESTApiController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    private Iterable<Item> list() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    private Optional<Item> getById(@PathVariable("id") Integer id) {
        return itemService.getById(id);
    }

    @DeleteMapping("/remove/{id}")
    private void delete(@PathVariable("id") Integer id) {
        itemService.remove(id);
    }

    @PatchMapping("/update/{id}")
    private void update(@PathVariable("id") Integer id) {
        itemService.getById(id).ifPresent(item -> {
            item.setSale(!item.getSale());
            itemService.save(item);
        });

    }
    @PostMapping("/add")
    private Item addProduct(@RequestBody ItemResponse response) {
        return itemService.add(response);
    }
}
