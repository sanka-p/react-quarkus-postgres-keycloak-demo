package org.demo.events;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.demo.dto.ItemDTO;
import org.demo.repository.ItemRepository;
import org.demo.util.ItemMapper;
import org.hibernate.reactive.mutiny.Mutiny;
import org.demo.entity.Item;


import java.util.ArrayList;
import java.util.List;

import static org.demo.util.ItemMapper.mapItem;
import static org.demo.util.ItemMapper.mapItemDTO;

@ApplicationScoped
public class ItemEvent {
    @Inject
    Mutiny.SessionFactory sessionFactory;

    @Inject
    ItemRepository itemRepository;

    @ConsumeEvent("get-all-items")
    public Uni<List<ItemDTO>> getAllItems (String message) {
        return sessionFactory.withSession(session ->
                itemRepository.listAll()
                        .onItem().transform(items -> {
                            List<ItemDTO> itemDTOS = new ArrayList<>();

                            for(Item item: items){
                                ItemDTO itemDTO = mapItemDTO(item);
                                itemDTOS.add(itemDTO);
                            }

                            return itemDTOS;
                        })
                        .onFailure().recoverWithNull()
        );
    }

    @ConsumeEvent("save-item")
    public Uni<ItemDTO> saveItem (ItemDTO itemDTO) {
        return sessionFactory.withSession(session ->
            itemRepository.persist(mapItem(itemDTO))
                    .onItem().transform(ItemMapper::mapItemDTO)
                    .onFailure().recoverWithNull()
        );
    }
}
