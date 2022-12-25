package com.example.marketplace;

import com.example.marketplace.models.Item;
import com.example.marketplace.models.ItemResponse;
import com.example.marketplace.repository.ItemRepository;
import com.example.marketplace.service.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ServiceTests {
    @Autowired
    private ItemService itemService;
    @MockBean
    private ItemRepository itemRepository;

    @Test
    public void addItem(){
        ItemResponse resp = new ItemResponse();
        resp.setName("Петушня");
        Item added = itemService.add(resp);
        assertThat(added).isNotNull();
        assertThat(added.getName().equals("Петушня"));
    }

    @Test
    public void getItem(){
        ItemResponse resp = new ItemResponse();
        resp.setName("Петушня 2");

        Item item = itemService.add(resp);

        assertThat(item).isNotNull();
        Optional<Item> result = Optional.of(new Item("Петушня 2"));

        result.get().setItemId(3);
        Mockito.doReturn(result).when(itemRepository).findById(3);

        assertThat(result.isPresent());
        assertThat(result.get().getName().equals("Петушня 2"));
    }
}
