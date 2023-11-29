package rw.ac.rca.bootrca.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.bootrca.DTO.UserDTO;
import rw.ac.rca.bootrca.models.Address;
import rw.ac.rca.bootrca.models.User;
import rw.ac.rca.bootrca.repositories.AddressRepository;
import rw.ac.rca.bootrca.repositories.StudentRepository;
import rw.ac.rca.bootrca.repositories.UserRepository;
import rw.ac.rca.bootrca.utils.CustomResponse;
import rw.ac.rca.bootrca.utils.UserRole;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController{

    String ok = "Operation Successful...";
    String fail = "Student Not Found ...";
    final UserRepository userRepository;
    final AddressRepository addressRepository;

    public UserController(StudentRepository studentRepository, UserRepository userRepository, AddressRepository addressRepository) {
        super(studentRepository);
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @PostMapping("/signup")
   public ResponseEntity<CustomResponse<User>> add(@RequestBody @Valid UserDTO userDTO) throws ParseException {

        UserRole userRole = processUserRole(userDTO.getUserRole());
        Date dateOfBirth = processStringDate(userDTO.getDateOfBirth());
        Optional<Address> optionalAddress = Optional.ofNullable(addressRepository.searchAddressByVillage(userDTO.getVillageName()));

        if (optionalAddress.isPresent()) {
            User newUser = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getGender(), userDTO.getEmail(), dateOfBirth, optionalAddress.get(), userDTO.getUsername(), userDTO.getPassword(), userRole);
            return ResponseEntity.ok(new CustomResponse<>(ok, userRepository.save(newUser)));

        }else {
            return ResponseEntity.ok(new CustomResponse<>("User Registration Failed"));
        }
   }

   @GetMapping("/all")
  public ResponseEntity<CustomResponse<List<User>>> listAll(){
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(new CustomResponse<>(ok, users));
  }

    @PutMapping("/update/{username}")
    public ResponseEntity<CustomResponse<User>> update(@PathVariable("username") String username, @RequestBody @Valid UserDTO userDTO) throws ParseException {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByUsername(username));
        if (optionalUser.isPresent()){
            User existingUser = optionalUser.get();

            existingUser.setFirstName(userDTO.getFirstName());
            existingUser.setLastName(userDTO.getLastName());
            existingUser.setGender(userDTO.getGender());
            existingUser.setEmail(userDTO.getEmail());

            Date dateOfBirth = processStringDate(userDTO.getDateOfBirth());
            existingUser.setDateOfBirth(dateOfBirth);
            UserRole userRole = processUserRole(userDTO.getUserRole());
            existingUser.setUserRole(userRole);

            Optional<Address> optionalAddress = Optional.ofNullable(addressRepository.searchAddressByVillage(userDTO.getVillageName()));
            optionalAddress.ifPresent(existingUser::setAddress);

            existingUser.setUsername(userDTO.getUsername());
            existingUser.setPassword(userDTO.getPassword());

            return ResponseEntity.ok(new CustomResponse<>(ok, userRepository.save(existingUser)));
        } else {
            return ResponseEntity.ok(new CustomResponse<>("User Update Failed"));
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

