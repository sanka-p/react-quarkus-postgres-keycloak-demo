package org.demo.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.demo.response.Response;
import org.demo.service.BillService;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.jboss.resteasy.reactive.RestResponse;

import io.quarkus.security.Authenticated;

@Authenticated
@SecurityRequirement(name = "Keycloak")
@ApplicationScoped
@Path("/annual-report")
public class AnnualReportController {
    @Inject
    BillService billService;

    /**
     * Retrieves total sales across all bills.
     *
     * @return A RestResponse containing the status code and body of the HTTP response.
     *         The body contains the total sales value or the error message
     */
    @GET
    @Path("/total-sales")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<?> getTotalSales(){
        Response<?> response = billService.getTotalSales();

        return RestResponse.ResponseBuilder.create(response.getStatusCode())
                .entity(response.getBody())
                .build();
    }

    /**
     * Retrieves the item(s) that was sold the least across all bills.
     *
     * @return A RestResponse containing the status code and body of the HTTP response.
     *         The body contains a list of the minimally sold items or the error message
     */
    @GET
    @Path("/min-sale-items")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<?> getMinSalesItems(){
        Response<?> response = billService.getMinSaleItems();

        return RestResponse.ResponseBuilder.create(response.getStatusCode())
                .entity(response.getBody())
                .build();
    }

    /**
     * Retrieves the item(s) that was sold the most across all bills.
     *
     * @return A RestResponse containing the status code and body of the HTTP response.
     *         The body contains a list of the maximally sold items or the error message
     */
    @GET
    @Path("/max-sale-items")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<?> getMaxSalesItems(){
        Response<?> response = billService.getMaxSaleItems();

        return RestResponse.ResponseBuilder.create(response.getStatusCode())
                .entity(response.getBody())
                .build();
    }

    /**
     * Retrieves the average user spending's across all bills.
     *
     * @return A RestResponse containing the status code and body of the HTTP response.
     *         The body contains the average user spending's or the error message
     */
    @GET
    @Path("/avg-user-spendings")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<?> getAverageUserSpendings(){
        Response<?> response = billService.getAverageUserSpendings();

        return RestResponse.ResponseBuilder.create(response.getStatusCode())
                .entity(response.getBody())
                .build();
    }

    /**
     * Retrieves the user count with at least one bill.
     *
     * @return A RestResponse containing the status code and body of the HTTP response.
     *         The body contains the active user count or the error message
     */
    @GET
    @Path("/active-user-count")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<?> getActiveUserCount(){
        Response<?> response = billService.getActiveUserCount();

        return RestResponse.ResponseBuilder.create(response.getStatusCode())
                .entity(response.getBody())
                .build();
    }

}
