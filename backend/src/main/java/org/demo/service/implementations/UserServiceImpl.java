package org.demo.service.implementations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.demo.dto.UserDTO;
import org.demo.entity.User;
import org.demo.repository.UserRepository;
import org.demo.response.ErrorResponse;
import org.demo.response.Response;
import org.demo.response.SuccessResponse;
import org.demo.service.UserService;
import org.jboss.resteasy.reactive.RestResponse.StatusCode;

import java.util.ArrayList;
import java.util.List;

import static org.demo.util.UserMapper.mapUser;
import static org.demo.util.UserMapper.mapUserDTO;

@ApplicationScoped
public class UserServiceImpl implements UserService {
    @Inject
    UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    public Response<?> getAllUsers(){
        List<User> allUsers = userRepository.listAll();
        List<UserDTO> allUserDTOs = new ArrayList<>();

        for (User user : allUsers){
            allUserDTOs.add(mapUserDTO(user));
        }

        Response<List<UserDTO>> response = new SuccessResponse<>();
        response.setStatusCode(StatusCode.OK);
        response.setBody(allUserDTOs);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    public Response<?> getUser(Long id){
        UserDTO userDTO = mapUserDTO(userRepository.findById(id));
        Response<UserDTO> response = new SuccessResponse<>();
        response.setStatusCode(StatusCode.OK);
        response.setBody(userDTO);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Response<?> saveUser(UserDTO userDTO)  {
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
    public Response<?> updateUser(Long id, UserDTO userDTO) {
        // Check for unique constraint violations
        if (userRepository.count("phoneNumber", userDTO.getPhoneNumber()) > 0){
            Response<String> response = new ErrorResponse();
            response.setStatusCode(StatusCode.CONFLICT);
            response.setBody("Record with phone number already exists");
            return response;
        }

        // Check if record exist to update
        if (userRepository.findById(id) == null){
            Response<String> response = new ErrorResponse();
            response.setStatusCode(StatusCode.NOT_FOUND);
            response.setBody("Record not found");
            return response;
        }

        userRepository.persist(mapUser(userDTO));

        Response<UserDTO> response = new SuccessResponse<>();
        response.setStatusCode(StatusCode.ACCEPTED);
        response.setBody(mapUserDTO(userRepository.findById(id)));
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Response<?> deleteUser(Long id) {
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
