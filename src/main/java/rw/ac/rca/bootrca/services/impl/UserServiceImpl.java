package rw.ac.rca.bootrca.services.impl;

import org.springframework.stereotype.Service;
import rw.ac.rca.bootrca.DTO.UserDTO;
import rw.ac.rca.bootrca.exceptions.UserNotFoundException;
import rw.ac.rca.bootrca.models.User;
import rw.ac.rca.bootrca.repositories.UserRepository;
import rw.ac.rca.bootrca.services.UserService;
import rw.ac.rca.bootrca.utils.UserRole;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    StudentServiceImpl studentService;

    @Override
    public User addUser(UserDTO userDTO) {
        User newUser = new User();

        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());
        newUser.setRole(UserRole.valueOf(userDTO.getUserRole()));
        System.out.println(newUser);
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(UUID userID, UserDTO userDTO) {
        User existingUser = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException("User " +userID+ " Not Found"));

        if (userDTO.getUsername() != null) {
            existingUser.setUsername(userDTO.getUsername());
        }
        if (userDTO.getPassword() != null) {
            existingUser.setPassword(userDTO.getPassword());
        }
        return userRepository.save(existingUser);    }

    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(UUID userID) {
        User existingUser = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException("User " +userID+ " Not Found"));

        userRepository.delete(existingUser);
    }
}
