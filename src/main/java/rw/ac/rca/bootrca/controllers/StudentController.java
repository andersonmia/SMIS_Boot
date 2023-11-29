package rw.ac.rca.bootrca.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rw.ac.rca.bootrca.DTO.StudentDTO;
import rw.ac.rca.bootrca.models.Address;
import rw.ac.rca.bootrca.models.Parent;
import rw.ac.rca.bootrca.models.Student;
import rw.ac.rca.bootrca.repositories.*;
import rw.ac.rca.bootrca.utils.CustomResponse;
import rw.ac.rca.bootrca.utils.StudentStatus;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController extends BaseController{

    String ok = "Operation Successful...";

    final MarksRepository marksRepository;
    final CourseRepository courseRepository;
    final ParentRepository parentRepository;
    final AddressRepository addressRepository;

    public StudentController(StudentRepository studentRepository, MarksRepository marksRepository, CourseRepository courseRepository, ParentRepository parentRepository, AddressRepository addressRepository) {
        super(studentRepository);
        this.marksRepository = marksRepository;
        this.courseRepository = courseRepository;
        this.parentRepository = parentRepository;
        this.addressRepository = addressRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<List<Student>>> listAll(){
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(new CustomResponse<>(ok, students));
    }


    @PostMapping("/add")
    public ResponseEntity<CustomResponse<Student>> add(@RequestBody @Valid StudentDTO studentDTO) throws ParseException {

        Optional<Parent> optionalParent = Optional.ofNullable(parentRepository.searchParentByFirstNameAndLastName(studentDTO.getParentFirstName(), studentDTO.getParentLastName()));
        Date dateOfBirth = processStringDate(studentDTO.getDateOfBirth());
        Optional<Address> optionalAddress = Optional.ofNullable(addressRepository.searchAddressByVillage(studentDTO.getVillageName()));
        StudentStatus studentStatus = processStudentStatus(studentDTO.getStudentStatus());

        if (optionalParent.isPresent() & optionalAddress.isPresent()){
            Student newStudent = new Student(studentDTO.getFirstName(), studentDTO.getLastName(), studentDTO.getGender().toUpperCase(), studentDTO.getEmail(), dateOfBirth, optionalAddress.get(), studentDTO.getStudentCode(), optionalParent.get(),studentStatus);
            return ResponseEntity.ok(new CustomResponse<>(ok,studentRepository.save(newStudent)));
        }else {
            return ResponseEntity.ok(new CustomResponse<>("Student Registration Failed"));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse<Student>> update(@PathVariable("id") Long id, @RequestBody @Valid Student student){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            Student newStudent = optionalStudent.get();
            newStudent.setFirstName(student.getFirstName());
            newStudent.setLastName(student.getLastName());
            newStudent.setEmail(student.getEmail());
            newStudent.setGender(student.getGender().toUpperCase());
            newStudent.setParent(student.getParent());
            newStudent.setDateOfBirth(student.getDateOfBirth());
            newStudent.setAddress(student.getAddress());
            newStudent.setStudentStatus(student.getStudentStatus());
            newStudent.setMarks(student.getMarks());

            return ResponseEntity.ok(new CustomResponse<>(ok, studentRepository.save(newStudent)));
        }else {
            return ResponseEntity.ok(new CustomResponse<>("Student Not Found"));
        }
    }

}
