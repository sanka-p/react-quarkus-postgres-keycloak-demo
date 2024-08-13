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
import org.demo.response.Response;
import org.demo.util.ItemMapper;
import org.demo.util.UserMapper;
import org.hibernate.reactive.mutiny.Mutiny;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.demo.util.ItemMapper.mapItem;
import static org.demo.util.ItemMapper.mapItemDTO;
import static org.demo.util.UserMapper.mapUser;
import static org.demo.util.UserMapper.mapUserDTO;

@ApplicationScoped
public class UserEvent {
    @Inject
    Mutiny.SessionFactory sessionFactory;

    @Inject
    UserRepository userRepository;

    @ConsumeEvent("save-user")
    public Uni<Response> saveUser (UserDTO userDTO) {
        return sessionFactory.withSession(session -> {
//                    userRepository.count("phoneNumber", userDTO.getPhoneNumber())
//                            .map(count -> {
//                                if (count > 0) {
//                                    return Response.errorResponse(
//                                            RestResponse.StatusCode.CONFLICT,
//                                            "Record with phone number already exists"
//                                    );
//                                }
//                            });
            
        // With the above pattern how to do multiple validations??

                    // Save user
                    User savedUser = new User();
                    savedUser.setFirstName(userDTO.getFirstName());
                    savedUser.setLastName(userDTO.getLastName());
                    savedUser.setEmail(userDTO.getEmail());
                    savedUser.setPassword(userDTO.getPassword());
                    savedUser.setDateOfBirth(userDTO.getDateOfBirth());
                    savedUser.setPhoneNumber(userDTO.getPhoneNumber());

//                    return session.withTransaction(transaction -> {
//
//                    });

                    return session.persist(savedUser)
                        .onItem().transform(_void -> {
                            return Response.successResponse(
                                    RestResponse.StatusCode.OK,
                                    mapUserDTO(savedUser));
                        })
                        .onFailure().recoverWithItem(throwable -> {
                            return Response.errorResponse(
                                    RestResponse.StatusCode.INTERNAL_SERVER_ERROR,
                                    "Failed to save user (Error in persist of record)");
                        });
                })
        .onFailure().recoverWithItem(throwable -> {
            return Response.errorResponse(
                    RestResponse.StatusCode.INTERNAL_SERVER_ERROR,
                    "Failed to save user (Error in retrieval of record)"
            );
        });
    }

    @ConsumeEvent("update-user")
    public Uni<Response> updateUser(UserDTO userDTO) {
        return sessionFactory.withSession(session -> {
                    return userRepository.findById(userDTO.getId())
                            .onItem().ifNotNull().transform(currentUser -> {
                                //TODO: handle constraint validations

                                // Update user
                                currentUser.setFirstName(Optional.ofNullable(userDTO.getFirstName()).orElse(currentUser.getFirstName()));
                                currentUser.setLastName(Optional.ofNullable(userDTO.getLastName()).orElse(currentUser.getLastName()));
                                currentUser.setEmail(Optional.ofNullable(userDTO.getEmail()).orElse(currentUser.getEmail()));
                                currentUser.setDateOfBirth(Optional.ofNullable(userDTO.getDateOfBirth()).orElse(currentUser.getDateOfBirth()));
                                currentUser.setPhoneNumber(Optional.ofNullable(userDTO.getPhoneNumber()).orElse(currentUser.getPhoneNumber()));

                                session.merge(currentUser);

                                return Response.successResponse(
                                        RestResponse.StatusCode.OK,
                                        mapUserDTO(currentUser)
                                );
                            })
                            .onItem().ifNull().switchTo(()->{
                                Response response = Response.errorResponse(
                                        RestResponse.StatusCode.NOT_FOUND,
                                        "User does not exist"
                                );
                                return Uni.createFrom().item(response);
                            });
                })
                .onFailure().recoverWithItem(throwable -> {
                    return Response.errorResponse(
                            RestResponse.StatusCode.INTERNAL_SERVER_ERROR,
                            "Failed to update user"
                    );
                });
    }

    @ConsumeEvent("delete-user")
    public Uni<Response> deleteUser(Long userId) {
        return sessionFactory.withSession(session -> {
                    return userRepository.findById(userId)
                            .onItem().ifNotNull().transform(currentUser -> {
                                session.remove(currentUser);

                                return Response.successResponse(
                                        RestResponse.StatusCode.OK,
                                        currentUser
                                );
                            })
                            .onItem().ifNull().switchTo(()->{
                                Response response = Response.errorResponse(
                                        RestResponse.StatusCode.NOT_FOUND,
                                        "User does not exist"
                                );
                                return Uni.createFrom().item(response);
                            });
                })
                .onFailure().recoverWithItem(throwable -> {
                    return Response.errorResponse(
                            RestResponse.StatusCode.INTERNAL_SERVER_ERROR,
                            "Failed to delete user"
                    );
                });
    }
}