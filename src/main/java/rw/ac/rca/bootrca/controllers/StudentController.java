package rw.ac.rca.bootrca.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.bootrca.DTO.StudentDTO;
import rw.ac.rca.bootrca.DTO.UserDTO;
import rw.ac.rca.bootrca.models.Student;
import rw.ac.rca.bootrca.services.impl.StudentServiceImpl;
import rw.ac.rca.bootrca.utils.CustomResponse;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;


@RestController
@AllArgsConstructor
@RequestMapping("/api/student")
public class StudentController{

    StudentServiceImpl studentService;

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<List<Student>>> getAllStudents(){
        return ResponseEntity.ok(new CustomResponse<>(studentService.listAllStudents()));
    }

    @PostMapping("/add")
    public ResponseEntity<CustomResponse<Student>> addStudent(@RequestBody StudentDTO studentDTO) throws ParseException {
        return ResponseEntity.ok(new CustomResponse<>(studentService.addStudent(studentDTO)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse<Student>> updateStudent(@PathVariable("id") UUID id, @RequestBody StudentDTO studentDTO) throws ParseException {
        return ResponseEntity.ok(new CustomResponse<>(studentService.updateStudent(id, studentDTO)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<Student>> deleteStudent(@PathVariable("id") UUID id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

}
