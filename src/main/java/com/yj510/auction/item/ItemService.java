package com.yj510.auction.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    List<Item> itemFindAll(){
        return itemRepository.findAll();
    }

    void itemSave(Item item){
        itemRepository.save(item);
    }

    Optional<Item> itemfindById (Long id){
        return itemRepository.findById(id);
    }

    void itemDeleteById(Long id){
        itemRepository.deleteById(id);
    }
}
