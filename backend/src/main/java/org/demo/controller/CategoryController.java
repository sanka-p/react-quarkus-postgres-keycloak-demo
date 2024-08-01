package org.demo.controller;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.demo.response.Response;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/categories")
public class CategoryController {
    @Inject
    EventBus bus;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<RestResponse<?>> getAllCategories(){
        return bus.request("get-all-items", "")
                .onItem().transform(message -> {
                    return RestResponse.ResponseBuilder.create(message.response.getStatusCode())
                            .entity(response.getBody())
                            .build();
                });
    }
}
