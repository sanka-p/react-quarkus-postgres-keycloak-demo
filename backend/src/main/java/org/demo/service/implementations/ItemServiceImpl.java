package org.demo.service.implementations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.demo.dto.ItemDTO;
import org.demo.entity.Item;
import org.demo.repository.ItemRepository;
import org.demo.response.Response;
import org.demo.response.SuccessResponse;
import org.demo.service.ItemService;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.ArrayList;
import java.util.List;

import static org.demo.util.ItemMapper.mapItem;
import static org.demo.util.ItemMapper.mapItemDTO;

@ApplicationScoped
public class ItemServiceImpl implements ItemService {
    @Inject
    ItemRepository itemRepository;

    /**
     * {@inheritDoc}
     */
    public Response<?> getAllItems(){
        List<Item> allItems = itemRepository.listAll();
        List<ItemDTO> allItemDTOs= new ArrayList<ItemDTO>();

        for (Item item : allItems) {
            allItemDTOs.add(mapItemDTO(item));
        }

        Response<List<ItemDTO>> response = new SuccessResponse<>();
        response.setStatusCode(RestResponse.StatusCode.OK);
        response.setBody(allItemDTOs);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Response<?> saveItem(ItemDTO itemDTO){
        Item item = mapItem(itemDTO);
        itemRepository.persist(item);

        Response<ItemDTO> response = new SuccessResponse<>();
        response.setStatusCode(RestResponse.StatusCode.OK);
        response.setBody(mapItemDTO(item));
        return response;
    }

}

