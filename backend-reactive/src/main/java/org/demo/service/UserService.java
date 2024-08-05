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
    Uni<Response<?>> getAllUsers();

    /**
     * Retrieves a specific user.
     *
     * @param id The ID of the user to retrieve.
     * @return A Response object containing the HTTP status code and body.
     *         The body contains the user details.
     */
    Uni<Response<?>> getUser(Long id);

    /**
     * Saves a new user.
     *
     * @param userDTO The UserDTO object containing the user information to be saved.
     * @return A Response object containing the HTTP status code and body.
     *         The body contains the details of the newly saved user.
     */
    Uni<Response<?>> saveUser(UserDTO userDTO);

    /**
     * Updates an existing user.
     *
     * @param id The ID of the user to update.
     * @param userDTO The UserDTO object containing the updated user information.
     * @return A Response object containing the HTTP status code and body.
     *         The body includes newly updated user details.
     */
    Uni<Response<?>> updateUser(Long id, UserDTO userDTO);

    /**
     * Deletes a user.
     *
     * @param id The ID of the user to delete.
     * @return A Response object containing the HTTP status code and body.
     *         The body contains details of the deleted user.
     */
    Uni<Response<?>> deleteUser(Long id);
}
