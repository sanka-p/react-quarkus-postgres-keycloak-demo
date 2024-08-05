package org.demo.service.implementations;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.demo.dto.ItemDTO;
import org.demo.response.Response;
import org.demo.service.ItemService;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@ApplicationScoped
public class ItemServiceImpl implements ItemService {
    @Inject
    EventBus bus;

    @Override
    public Uni<Response<?>> getAllItems() {
        return bus.<List<ItemDTO>>request("get-all-items", null)
                .onItem().transform(Message::body)
                .onItem().transform(itemDTOs -> {
                    Response<List<ItemDTO>> response = new Response<>();
                    response.setBody(itemDTOs);
                    response.setStatusCode(RestResponse.StatusCode.OK);
                    return response;
                });
    }

    public Uni<Response<?>> saveItem(ItemDTO itemDTO){
        return bus.<ItemDTO>request("save-item", itemDTO)
                .onItem().transform(Message::body)
                .onItem().transform(savedItemDTO -> {
                    Response<ItemDTO> response = new Response<>();
                    response.setBody(savedItemDTO);
                    response.setStatusCode(RestResponse.StatusCode.OK);
                    return response;
                });
    }
}
