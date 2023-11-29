package rw.ac.rca.bootrca.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.bootrca.DTO.ParentDTO;
import rw.ac.rca.bootrca.models.Address;
import rw.ac.rca.bootrca.models.Parent;
import rw.ac.rca.bootrca.utils.PhoneNumber;
import rw.ac.rca.bootrca.repositories.AddressRepository;
import rw.ac.rca.bootrca.repositories.ParentRepository;
import rw.ac.rca.bootrca.repositories.StudentRepository;
import rw.ac.rca.bootrca.utils.CustomResponse;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parent")
public class ParentController extends BaseController {
    String ok = "Operation Successful...";
    String fail = "Parent Not Found ...";
    final ParentRepository parentRepository;
    final AddressRepository addressRepository;

    public ParentController(StudentRepository studentRepository, ParentRepository parentRepository, AddressRepository addressRepository) {
        super(studentRepository);
        this.parentRepository = parentRepository;
        this.addressRepository = addressRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<List<Parent>>> listAll(){
        List<Parent> parents = parentRepository.findAll();
        return ResponseEntity.ok(new CustomResponse<>(ok,parents));
    }

    @PostMapping("/add")
    public ResponseEntity<CustomResponse<Parent>> add(@RequestBody ParentDTO parentDTO) throws ParseException {

        Optional<Address> optionalAddress = Optional.ofNullable(addressRepository.searchAddressByVillage(parentDTO.getVillageName()));
        List<PhoneNumber> validPhoneNumbers = processPhoneNumbers(parentDTO.getPhoneNumbers());
        Date dateOfBirth = processStringDate(parentDTO.getDateOfBirth());
        if (optionalAddress.isPresent()) {
            Parent newParent = new Parent(parentDTO.getFirstName(), parentDTO.getLastName(), parentDTO.getGender().toUpperCase(), parentDTO.getEmail(), dateOfBirth, optionalAddress.get(), validPhoneNumbers);
            return ResponseEntity.ok(new CustomResponse<>(ok, parentRepository.save(newParent)));
        }else {
            return ResponseEntity.ok(new CustomResponse<>(fail));
        }
    }

    @PostMapping("/update/{parent_id}")
    public ResponseEntity<CustomResponse<Parent>> update(@PathVariable("parent_id") Long parent_id, @RequestBody @Valid ParentDTO parentDTO) throws ParseException {

        Optional<Parent> optionalParent = parentRepository.findById(parent_id);
        if (optionalParent.isEmpty()) {
            return ResponseEntity.ok(new CustomResponse<>(fail));
        }

        Parent existingParent = optionalParent.get();

        Optional<Address> optionalAddress = Optional.ofNullable(addressRepository.searchAddressByVillage(parentDTO.getVillageName()));
        optionalAddress.ifPresent(existingParent::setAddress);

        existingParent.setFirstName(parentDTO.getFirstName());
        existingParent.setLastName(parentDTO.getLastName());
        existingParent.setGender(parentDTO.getGender().toUpperCase());
        existingParent.setEmail(parentDTO.getEmail());

        List<PhoneNumber> validPhoneNumbers = processPhoneNumbers(parentDTO.getPhoneNumbers());
        existingParent.setPhoneNumbers(validPhoneNumbers);

        Date dateOfBirth = processStringDate(parentDTO.getDateOfBirth());
        existingParent.setDateOfBirth(dateOfBirth);

        Parent updatedParent = parentRepository.save(existingParent);

        return ResponseEntity.ok(new CustomResponse<>(ok, updatedParent));
    }




}
