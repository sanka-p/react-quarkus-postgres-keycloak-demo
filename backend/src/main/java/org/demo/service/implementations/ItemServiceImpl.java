package org.demo.service.implementations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.demo.dto.ItemDTO;
import org.demo.entity.Item;
import org.demo.repository.ItemRepository;
import org.demo.response.ErrorResponse;
import org.demo.response.Response;
import org.demo.response.SuccessResponse;
import org.demo.service.ItemService;
import org.demo.util.ItemMapper;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.StatusCode;

import java.util.ArrayList;
import java.util.List;

import static org.demo.util.ItemMapper.mapItem;
import static org.demo.util.ItemMapper.mapItemDTO;

import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

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
    public ItemDTO getItem(Long id){
//        Uni<Item> itemUni = itemReactiveRepository.findById(id);
//        itemUni.onItem()
//            .transform(ItemMapper::mapItemDTO)
//            .subscribe().with(
//                itemDTO -> {
//                    return Uni.createFrom().item(itemDTO);
//                },
//                failure -> {
//                    Response<String> response = new ErrorResponse();
//                    response.setStatusCode(StatusCode.NOT_FOUND);
//                    response.setBody("Record not found");
//                    return response;
//                }
//            );
        return new ItemDTO();
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

