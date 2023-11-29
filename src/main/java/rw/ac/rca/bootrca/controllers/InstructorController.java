package rw.ac.rca.bootrca.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.bootrca.DTO.InstructorDTO;
import rw.ac.rca.bootrca.models.Address;
import rw.ac.rca.bootrca.models.Instructor;
import rw.ac.rca.bootrca.models.PhoneNumber;
import rw.ac.rca.bootrca.repositories.AddressRepository;
import rw.ac.rca.bootrca.repositories.CourseRepository;
import rw.ac.rca.bootrca.repositories.InstructorRepository;
import rw.ac.rca.bootrca.repositories.StudentRepository;
import rw.ac.rca.bootrca.utils.CustomResponse;

import java.text.ParseException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController extends BaseController{
    String ok = "Operation Successful...";
    String fail = "Instructor Not Found ...";

    final InstructorRepository instructorRepository;
    final  AddressRepository addressRepository;
    final CourseRepository courseRepository;

    public InstructorController(StudentRepository studentRepository, InstructorRepository instructorRepository, AddressRepository addressRepository, CourseRepository courseRepository) {
        super(studentRepository);
        this.instructorRepository = instructorRepository;
        this.addressRepository = addressRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<List<Instructor>>> listAll(){
        List<Instructor> instructors = instructorRepository.findAll();
        return ResponseEntity.ok(new CustomResponse<>(ok, instructors));
    }

    @PostMapping("/add")
    public ResponseEntity<CustomResponse<Instructor>> add(@RequestBody InstructorDTO instructorDTO) throws ParseException {

        List<PhoneNumber> validPhoneNumbers = processPhoneNumbers(instructorDTO.getPhoneNumbers());
        Date dateOfBirth = processStringDate(instructorDTO.getDateOfBirth());
        Optional<Address> optionalAddress = Optional.ofNullable(addressRepository.searchAddressByVillage(instructorDTO.getVillageName()));

        if (optionalAddress.isEmpty())
            return ResponseEntity.ok(new CustomResponse<>("Enter Valid Address"));

        Instructor instructor = new Instructor(instructorDTO.getFirstName(), instructorDTO.getLastName(), instructorDTO.getGender(),instructorDTO.getEmail(), dateOfBirth, optionalAddress.get(), validPhoneNumbers);
        return ResponseEntity.ok(new CustomResponse<>(ok, instructorRepository.save(instructor)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse<Instructor>> update(@PathVariable("id") Long id, @RequestBody InstructorDTO instructorDTO){

        Optional<Instructor> optionalInstructor = instructorRepository.findById(id);
        Optional<Address> optionalAddress = Optional.ofNullable(addressRepository.searchAddressByVillage(instructorDTO.getVillageName()));
        List<PhoneNumber> validPhoneNumbers = processPhoneNumbers(instructorDTO.getPhoneNumbers());

        if (optionalInstructor.isPresent()){
            Instructor newInstructor = optionalInstructor.get();

            if (instructorDTO.getEmail() != null)
                newInstructor.setEmail(instructorDTO.getEmail());
            newInstructor.setPhoneNumbers(validPhoneNumbers);
            optionalAddress.ifPresent(newInstructor::setAddress);
            if (instructorDTO.getPhoneNumbers() != null)
                newInstructor.setGender(instructorDTO.getGender());
            if (instructorDTO.getFirstName() != null)
                newInstructor.setFirstName(instructorDTO.getFirstName());
            if (instructorDTO.getLastName() != null)
                newInstructor.setLastName(instructorDTO.getLastName());

            return ResponseEntity.ok(new CustomResponse<>(ok, instructorRepository.save(newInstructor)));
        }else {
            return ResponseEntity.ok(new CustomResponse<>(fail));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<Instructor>> delete(@PathVariable("id") Long id){
        Optional<Instructor> optionalInstructor = instructorRepository.findById(id);
        if (optionalInstructor.isPresent()){
            instructorRepository.delete(optionalInstructor.get());
            return ResponseEntity.ok(new CustomResponse<>(ok));
        }else {
            return ResponseEntity.ok(new CustomResponse<>(fail));
        }

    }
}
