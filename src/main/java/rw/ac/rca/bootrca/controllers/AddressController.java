package rw.ac.rca.bootrca.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.bootrca.models.Address;
import rw.ac.rca.bootrca.repositories.AddressRepository;
import rw.ac.rca.bootrca.utils.CustomResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    String ok = "Operation Successful...";
    String fail = "Address Not Found ...";

    final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<List<Address>>> listAll(){
        List<Address> addresses = addressRepository.findAll();
        return ResponseEntity.ok(new CustomResponse<>(ok, addresses));
    }

    @PostMapping("/add")
    public ResponseEntity<CustomResponse<Address>> add(@RequestBody Address address){
        return ResponseEntity.ok(new CustomResponse<>(ok, addressRepository.save(address)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse<Address>> update(@PathVariable("id") Long id, @RequestBody Address address){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()){
            Address newAddress = optionalAddress.get();
            newAddress.setDistrict(address.getDistrict());
            newAddress.setProvince(address.getProvince());
            newAddress.setCell(address.getCell());
            newAddress.setSector(address.getSector());
            newAddress.setVillage(address.getVillage());

            return ResponseEntity.ok(new CustomResponse<>(ok, addressRepository.save(newAddress)));
        }else {
            return ResponseEntity.ok(new CustomResponse<>(fail));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<Address>> delete(@PathVariable("id") Long id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()){
            addressRepository.delete(optionalAddress.get());
            return ResponseEntity.ok(new CustomResponse<>(ok));
        }else {
            return ResponseEntity.ok(new CustomResponse<>(fail));
        }
    }
}
