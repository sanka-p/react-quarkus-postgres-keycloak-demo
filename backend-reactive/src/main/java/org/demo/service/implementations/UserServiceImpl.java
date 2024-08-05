package org.demo.service.implementations;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.demo.dto.ItemDTO;
import org.demo.dto.UserDTO;
import org.demo.entity.User;
import org.demo.repository.UserRepository;
import org.demo.response.ErrorResponse;
import org.demo.response.Response;
import org.demo.response.SuccessResponse;
import org.demo.service.UserService;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.StatusCode;

import java.util.ArrayList;
import java.util.List;

import static org.demo.util.UserMapper.mapUser;
import static org.demo.util.UserMapper.mapUserDTO;

@ApplicationScoped
public class UserServiceImpl implements UserService {
    @Inject
    EventBus bus;

    /**
     * {@inheritDoc}
     */
    public Uni<Response<?>> getAllUsers(){
        return bus.<List<UserDTO>>request("get-all-users", null)
                .onItem().transform(Message::body)
                .onItem().transform(userDTOs -> {
                    Response<List<UserDTO>> response = new Response<>();
                    response.setBody(userDTOs);
                    response.setStatusCode(RestResponse.StatusCode.OK);
                    return response;
                });
    }

    /**
     * {@inheritDoc}
     */
    public Uni<Response<?>> getUser(Long id){
        return bus.<UserDTO>request("get-user", id)
                .onItem().transform(Message::body)
                .onItem().transform(userDTO -> {
                    Response<UserDTO> response = new Response<>();
                    response.setBody(userDTO);
                    response.setStatusCode(RestResponse.StatusCode.OK);
                    return response;
                });
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Uni<Response<?>> saveUser(UserDTO userDTO)  {
        // Check for unique constraint violations
        if (userRepository.count("phoneNumber", userDTO.getPhoneNumber()) > 0){
            Response<String> response = new ErrorResponse();
            response.setStatusCode(StatusCode.CONFLICT);
            response.setBody("Record with phone number already exists");
            return response;
        }

        // Save new user
        User user = mapUser(userDTO);
        userRepository.persist(user);

        // Retrieve newly created user
        Long id = user.getId();
        UserDTO savedUserDTO = mapUserDTO(userRepository.findById(id));

        Response<UserDTO> response = new SuccessResponse<>();
        response.setStatusCode(StatusCode.ACCEPTED);
        response.setBody(savedUserDTO);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Uni<Response<?>> updateUser(Long id, UserDTO userDTO) {
        // Check for unique constraint violations
        // TODO: This cant be handled like this
        // if (userRepository.count("phoneNumber", userDTO.getPhoneNumber()) > 1){ // Since the previous phone number already exist
        //     Response<String> response = new ErrorResponse();
        //     response.setStatusCode(StatusCode.CONFLICT);
        //     response.setBody("Record with phone number already exists");
        //     return response;
        // }

        // Check if record exist to update
        if (userRepository.findById(id) == null){
            Response<String> response = new ErrorResponse();
            response.setStatusCode(StatusCode.NOT_FOUND);
            response.setBody("Record not found");
            return response;
        }

        User user = userRepository.findById(id);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        if (userDTO.getPassword() != null) user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        userRepository.persist(user);

        Response<UserDTO> response = new SuccessResponse<>();
        response.setStatusCode(StatusCode.ACCEPTED);
        response.setBody(mapUserDTO(userRepository.findById(id)));
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Uni<Response<?>> deleteUser(Long id) {
        User user = userRepository.findById(id);
        if(user == null) {
            Response<String> response = new ErrorResponse();
            response.setStatusCode(StatusCode.NOT_FOUND);
            response.setBody("Record not found");
            return response;
        }

        userRepository.delete(user);
        Response<UserDTO> response = new SuccessResponse<>();
        response.setStatusCode(StatusCode.OK);
        response.setBody(mapUserDTO(user));
        return response;
    }
}
