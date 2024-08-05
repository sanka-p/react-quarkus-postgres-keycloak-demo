package org.demo.util;

import org.demo.dto.UserDTO;
import org.demo.entity.User;

public class UserMapper {
    /**
     * Maps a User entity to a UserDTO data transfer object.
     *
     * @param user The User entity to be mapped.
     * @return A UserDTO containing the mapped data from the User entity.
     */
    public static UserDTO mapUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        return userDTO;
    }

    /**
     * Maps a UserDTO data transfer object to a User entity.
     *
     * @param userDTO The UserDTO to be mapped.
     * @return A User entity containing the mapped data from the UserDTO.
     */
    public static User mapUser(UserDTO userDTO){
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        return user;
    }
}
