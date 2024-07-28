package org.demo.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.demo.dto.BillDTO;
import org.demo.dto.UserDTO;
import org.demo.response.Response;
import org.demo.service.BillService;
import org.demo.service.UserService;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestResponse;

import io.quarkus.security.Authenticated;

//@Authenticated // TODO
//@SecurityRequirement(name = "Keycloak") // TODO
@Path("/users")
public class UserController {
    @Inject
    UserService userService;

    @Inject
    BillService billService;

    /**
     * Retrieves all users.
     *
     * @return A RestResponse containing the status code and body of the HTTP response.
     *         The body contains the list of users or the error message
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<?> getAllUsers(){
        System.out.println("I was triggered");

        Response<?> response = userService.getAllUsers();

        return RestResponse.ResponseBuilder.create(response.getStatusCode())
                .entity(response.getBody())
                .build();
    }

    /**
     * Retrieves a user by the user ID.
     *
     * @param id The ID of the user to retrieve.
     * @return A RestResponse containing the status code and body of the HTTP response.
     *         The body contains the user details or the error message
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<?> getUser(@RestPath Long id){
        Response<?> response = userService.getUser(id);

        return RestResponse.ResponseBuilder.create(response.getStatusCode())
                .entity(response.getBody())
                .build();
    }

    /**
     * Saves a user.
     *
     * @param userDTO The UserDTO object containing the user information to be saved.
     * @return A RestResponse containing the status code and body of the HTTP response.
     *         The body contains the newly saved user details or the error message
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<?> saveUser(UserDTO userDTO){
        Response<?> response = userService.saveUser(userDTO);

        return RestResponse.ResponseBuilder.create(response.getStatusCode())
                .entity(response.getBody())
                .build();
    }

    /**
     * Updates a user.
     *
     * @param id The ID of the user to be updated.
     * @param userDTO The UserDTO object containing the user information to be updated.
     * @return A RestResponse containing the status code and body of the HTTP response.
     *         The body contains the newly updated user details or the error message
     */
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<?> updateUser(@RestPath Long id, UserDTO userDTO){
        Response<?> response = userService.updateUser(id, userDTO);

        return RestResponse.ResponseBuilder.create(response.getStatusCode())
                .entity(response.getBody())
                .build();
    }

    /**
     * Deletes a user.
     *
     * @param id The ID of the user to be deleted.
     * @return A RestResponse containing the status code and body of the HTTP response.
     *         The body contains the deleted user details or the error message
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<?> deleteUser(@RestPath Long id){
        Response<?> response = userService.deleteUser(id);

        return RestResponse.ResponseBuilder.create(response.getStatusCode())
                .entity(response.getBody())
                .build();
    }

    /**
     * Retrieves all bills for a user.
     *
     * @param id The ID of the user whose bills belong to.
     * @return A RestResponse containing the status code and body of the HTTP response.
     *         The body contains a list of bills or the error message
     */
    @GET
    @Path("/{id}/purchases")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<?> getAllPurchases(@RestPath Long id){
        Response<?> response = billService.getAllBillsForUser(id);

        return RestResponse.ResponseBuilder.create(response.getStatusCode())
                .entity(response.getBody())
                .build();
    }

    /**
     * Retrieves a bill for a user by the bill ID.
     *
     * @param id The ID of the user whose bill belong to.
     * @param billId The ID of the bill
     * @return A RestResponse containing the status code and body of the HTTP response.
     *         The body contains a list of bills or the error message
     */
    @GET
    @Path("/{id}/purchases/{billId}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<?> getPurchase(@RestPath Long id, @RestPath Long billId){
        Response<?> response = billService.getBillForUser(id, billId);

        return RestResponse.ResponseBuilder.create(response.getStatusCode())
                .entity(response.getBody())
                .build();
    }

    /**
     * Creates a new purchase bill for a user.
     *
     * @param id The ID of the user creating the purchase.
     * @param billDTO The BillDTO object containing the new bill information
     * @return A RestResponse containing the status code and body of the HTTP response.
     *         The body contains the newly created bill details or the error message
     */
    @POST
    @Path("/{id}/purchases")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<?> savePurchase(@RestPath Long id, @Valid BillDTO billDTO){
        Response<?> response = billService.saveBill(id, billDTO);

        return RestResponse.ResponseBuilder.create(response.getStatusCode())
                .entity(response.getBody())
                .build();
    }
}
