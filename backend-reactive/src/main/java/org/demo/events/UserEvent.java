package org.demo.events;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.demo.dto.ItemDTO;
import org.demo.dto.UserDTO;
import org.demo.entity.Item;
import org.demo.entity.User;
import org.demo.repository.ItemRepository;
import org.demo.repository.UserRepository;
import org.demo.util.ItemMapper;
import org.demo.util.UserMapper;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.ArrayList;
import java.util.List;

import static org.demo.util.ItemMapper.mapItem;
import static org.demo.util.ItemMapper.mapItemDTO;
import static org.demo.util.UserMapper.mapUserDTO;

@ApplicationScoped
public class UserEvent {
    @Inject
    Mutiny.SessionFactory sessionFactory;

    @Inject
    UserRepository userRepository;

    @ConsumeEvent("get-all-users")
    public Uni<List<UserDTO>> getAllUsers (String message) {
        return sessionFactory.withSession(session ->
                userRepository.listAll()
                        .onItem().transform(users -> {
                            List<UserDTO> userDTOS = new ArrayList<>();

                            for(User user: users){
                                UserDTO userDTO = mapUserDTO(user);
                                userDTOS.add(userDTO);
                            }

                            return userDTOS;
                        })
                        .onFailure().recoverWithNull()
        );
    }

    @ConsumeEvent("get-user")
    public Uni<UserDTO> getUser (Long id) {
        return sessionFactory.withSession(session ->
                userRepository.findById(id)
                        .onItem().transform(UserMapper::mapUserDTO)
                        .onFailure().recoverWithNull()
        );
    }

    @ConsumeEvent("save-item")
    public Uni<ItemDTO> saveItem (ItemDTO itemDTO) {
        return sessionFactory.withSession(session ->
                itemRepository.persist(mapItem(itemDTO))
                        .onItem().transform(ItemMapper::mapItemDTO)
                        .onFailure().recoverWithNull()
        );
    }
}
