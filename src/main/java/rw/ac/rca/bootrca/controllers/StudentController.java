package rw.ac.rca.bootrca.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rw.ac.rca.bootrca.models.Student;
import rw.ac.rca.bootrca.repositories.CourseRepository;
import rw.ac.rca.bootrca.repositories.MarksRepository;
import rw.ac.rca.bootrca.repositories.StudentRepository;
import rw.ac.rca.bootrca.utils.CustomResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    String ok = "Operation Successful...";
    String fail = "Student Not Found ...";

    final StudentRepository studentRepository;
    final MarksRepository marksRepository;
    final CourseRepository courseRepository;

    public StudentController(StudentRepository studentRepository, MarksRepository marksRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.marksRepository = marksRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<List<Student>>> listAll(){
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(new CustomResponse<>(ok, students));
    }


    @PostMapping("/add")
    public ResponseEntity<CustomResponse<Student>> add(@RequestBody Student student){
        return ResponseEntity.ok(new CustomResponse<>(ok,studentRepository.save(student)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse<Student>> update(@PathVariable("id") Long id, @RequestBody Student student){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            Student newStudent = optionalStudent.get();
            newStudent.setFirstName(student.getFirstName());
            newStudent.setLastName(student.getLastName());
            newStudent.setEmail(student.getEmail());
            newStudent.setGender(student.getGender());
            newStudent.setParent(student.getParent());
            newStudent.setDateOfBirth(student.getDateOfBirth());
            newStudent.setAddress(student.getAddress());
            newStudent.setStudentStatus(student.getStudentStatus());
            newStudent.setMarks(student.getMarks());

            return ResponseEntity.ok(new CustomResponse<>(ok, studentRepository.save(newStudent)));
        }else {
            return ResponseEntity.ok(new CustomResponse<>(fail));
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<Student>> delete(@PathVariable("id") Long id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()){
            studentRepository.delete(studentOptional.get());
            return ResponseEntity.ok(new CustomResponse<>(ok));
        }else {
            return ResponseEntity.ok(new CustomResponse<>(fail));
        }
    }
}
