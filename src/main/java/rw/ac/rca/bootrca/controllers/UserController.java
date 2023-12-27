package rw.ac.rca.bootrca.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.bootrca.DTO.UserDTO;
import rw.ac.rca.bootrca.models.User;
import rw.ac.rca.bootrca.services.impl.UserServiceImpl;
import rw.ac.rca.bootrca.utils.CustomResponse;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    UserServiceImpl userService;

    @GetMapping("/test")
    public ResponseEntity<CustomResponse<String>> test(){
        return ResponseEntity.ok(new CustomResponse<>("Hello World"));
    }

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<List<User>>> getAllUsers(){
        return ResponseEntity.ok(new CustomResponse<>(userService.listAllUsers()));
    }

    @PostMapping("/add")
    public ResponseEntity<CustomResponse<User>> addUser(@RequestBody UserDTO user){
        return ResponseEntity.ok(new CustomResponse<>(userService.addUser(user)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse<User>> updateUser(@PathVariable("id") UUID id, @RequestBody UserDTO user){
        return ResponseEntity.ok(new CustomResponse<>(userService.updateUser(id, user)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<User>> deleteUser(@PathVariable("id") UUID id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}

