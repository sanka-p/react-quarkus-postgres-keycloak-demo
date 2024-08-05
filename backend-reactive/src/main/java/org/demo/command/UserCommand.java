package org.demo.command;

import io.smallrye.mutiny.Uni;
import org.demo.dto.UserDTO;
import org.demo.response.Response;

public interface UserCommand {
    /**
     * Issue command to save a new user.
     *
     * @param userDTO The UserDTO object containing the user information to be saved.
     * @return A Response object containing the HTTP status code and body.
     *         The body contains the details of the newly saved user.
     */
    Uni<Response> saveUser(UserDTO userDTO);

    /**
     * Issue command to update an existing user.
     *
     * @param id The ID of the user to update.
     * @param userDTO The UserDTO object containing the updated user information.
     * @return A Response object containing the HTTP status code and body.
     *         The body includes newly updated user details.
     */
    Uni<Response> updateUser(Long id, UserDTO userDTO);

    /**
     * Issue command to delete a user.
     *
     * @param id The ID of the user to delete.
     * @return A Response object containing the HTTP status code and body.
     *         The body contains details of the deleted user.
     */
    Uni<Response> deleteUser(Long id);
}
