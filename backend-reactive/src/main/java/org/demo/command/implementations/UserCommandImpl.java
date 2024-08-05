package org.demo.command.implementations;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.demo.command.UserCommand;
import org.demo.dto.UserDTO;
import org.demo.response.Response;

@ApplicationScoped
public class UserCommandImpl implements UserCommand {
    @Inject
    EventBus bus;

    /**
     * {@inheritDoc}
     */
    public Uni<Response> saveUser(UserDTO userDTO)  {
        return bus.<Response>request("save-user", userDTO)
                .onItem().transform(Message::body);
    }

    /**
     * {@inheritDoc}
     */
    public Uni<Response> updateUser(Long id, UserDTO userDTO) {
        return bus.<Response>request("update-user", userDTO)
                .onItem().transform(Message::body);
    }

    /**
     * {@inheritDoc}
     */
    public Uni<Response> deleteUser(Long id) {
        return bus.<Response>request("delete-user", id)
                .onItem().transform(Message::body);
    }
}
