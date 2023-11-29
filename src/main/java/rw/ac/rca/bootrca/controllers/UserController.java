package rw.ac.rca.bootrca.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.bootrca.models.User;
import rw.ac.rca.bootrca.repositories.UserRepository;
import rw.ac.rca.bootrca.utils.CustomResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    String ok = "Operation Successful...";
    String fail = "Student Not Found ...";
    final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   @PostMapping("/signup")
   public ResponseEntity<CustomResponse<User>> add(@RequestBody User user){
        return ResponseEntity.ok(new CustomResponse<>(ok, userRepository.save(user)));
   }

   @GetMapping("/all")
  public ResponseEntity<CustomResponse<List<User>>> listAll(){
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(new CustomResponse<>(ok, users));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<CustomResponse<User>> update(@PathVariable("id") Long id, @RequestBody User user){

      Optional<User> optionalUser = userRepository.findById(id);
      if (optionalUser.isPresent()){
          User newUser = optionalUser.get();
          newUser.setFirstName(user.getFirstName());
          newUser.setLastName(user.getLastName());
          newUser.setEmail(user.getEmail());
          newUser.setDateOfBirth(user.getDateOfBirth());
          newUser.setGender(user.getGender());
          newUser.setUserRole(user.getUserRole());
          newUser.setUsername(user.getUsername());
          newUser.setPassword(user.getPassword());

          return ResponseEntity.ok(new CustomResponse<>(ok, userRepository.save(newUser)));
      }else {
          return ResponseEntity.ok(new CustomResponse<>(fail));
      }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<CustomResponse<User>> delete(@PathVariable("id") Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            userRepository.delete(optionalUser.get());
            return ResponseEntity.ok(new CustomResponse<>(ok));
        }else {
            return ResponseEntity.ok(new CustomResponse<>(fail));
        }
  }


}

