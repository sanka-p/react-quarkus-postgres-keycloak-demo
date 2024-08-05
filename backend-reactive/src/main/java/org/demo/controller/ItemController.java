package org.demo.controller;

import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.demo.dto.ItemDTO;
import org.demo.entity.Item;
import org.demo.response.Response;
import org.demo.service.ItemService;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

import io.quarkus.security.Authenticated;

import java.util.List;

//@Authenticated
//@SecurityRequirement(name = "Keycloak")
@Path("/items")
public class ItemController {
    @Inject
    ItemService itemService;

    /**
     * Retrieves all items.
     *
     * @return A RestResponse containing the status code and body of the HTTP response.
     *         The body contains the list of items or the error message
     */
    //@RolesAllowed({"customer", "manager", "employee"})
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Uni<RestResponse<?>> getAllItems(){
//        return itemService.getAllItems().onItem().transform(
//                response -> {
//                    return RestResponse.ResponseBuilder.create(response.getStatusCode())
//                            .entity(response.getBody())
//                            .build();
//                }
//        );
//    }

    /**
     * Saves an item.
     *
     * @param itemDTO The ItemDTO object containing the item information to be saved.
     * @return A RestResponse containing the status code and body of the HTTP response.
     *         The body contains the newly saved item details or the error message
     */
    //@RolesAllowed({"manager"})
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    public Uni<RestResponse<?>> saveItem(ItemDTO itemDTO){
//        return itemService.saveItem(itemDTO).onItem().transform(
//                response -> {
//                    return RestResponse.ResponseBuilder.create(response.getStatusCode())
//                            .entity(response.getBody())
//                            .build();
//                }
//        );
//    }
}
