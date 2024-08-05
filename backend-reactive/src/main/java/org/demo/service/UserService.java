package org.demo.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.demo.dto.UserDTO;
import org.demo.response.Response;

import java.util.List;

public interface UserService {

    /**
     * Retrieves all users.
     *
     * @return A Response object containing the HTTP status code and body .
     *         The body contains a list of users.
     */
//    Uni<Response<?>> getAllUsers();

    /**
     * Retrieves a specific user.
     *
     * @param id The ID of the user to retrieve.
     * @return A Response object containing the HTTP status code and body.
     *         The body contains the user details.
     */
//    Uni<Response<?>> getUser(Long id);
}
