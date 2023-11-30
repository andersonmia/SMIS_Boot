package rw.ac.rca.bootrca.services;

import rw.ac.rca.bootrca.DTO.UserDTO;
import rw.ac.rca.bootrca.models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User addUser(UserDTO userDTO);
    User updateUser(UUID userID, UserDTO userDTO);
    List<User> listAllUsers();
    void deleteUser(UUID userID);
}
