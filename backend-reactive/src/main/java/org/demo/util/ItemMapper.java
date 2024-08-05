package org.demo.util;

import org.demo.dto.ItemDTO;
import org.demo.entity.Item;

public class ItemMapper {

    /**
     * Maps an Item entity to an ItemDTO data transfer object.
     *
     * @param item The Item entity to be mapped.
     * @return An ItemDTO containing the mapped data from the Item entity.
     */
    public static ItemDTO mapItemDTO(Item item){
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setId(item.getId());
            itemDTO.setName(item.getName());
            itemDTO.setPrice(item.getPrice());
            return itemDTO;
    }

    /**
     * Maps an ItemDTO data transfer object to an Item entity.
     *
     * @param itemDTO The ItemDTO to be mapped.
     * @return An Item entity containing the mapped data from the ItemDTO.
     */
    public static Item mapItem(ItemDTO itemDTO){
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        return item;
    }
}
