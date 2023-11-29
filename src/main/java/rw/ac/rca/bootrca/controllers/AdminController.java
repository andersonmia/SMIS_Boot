package rw.ac.rca.bootrca.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rw.ac.rca.bootrca.models.*;
import rw.ac.rca.bootrca.repositories.*;
import rw.ac.rca.bootrca.utils.CustomResponse;

import java.util.Optional;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    String fail = "Entity Deletion Failed";
    String ok = "Entity Deleted Successfully";

    final CourseRepository courseRepository;
    final InstructorRepository instructorRepository;
    final MarksRepository marksRepository;
    final ParentRepository parentRepository;
    final StudentRepository studentRepository;
    final UserRepository userRepository;

    public AdminController(CourseRepository courseRepository, InstructorRepository instructorRepository, MarksRepository marksRepository, ParentRepository parentRepository, StudentRepository studentRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        this.marksRepository = marksRepository;
        this.parentRepository = parentRepository;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @DeleteMapping("/delete/{courseCode}")
    public ResponseEntity<CustomResponse<Course>> deleteCourseByCourseCode(@PathVariable("courseCode") String courseCode){
        Optional<Course> optionalCourse = Optional.ofNullable(courseRepository.searchCourseByCourseCode(courseCode));
        if (optionalCourse.isEmpty())
            return ResponseEntity.ok(new CustomResponse<>(""));
        courseRepository.delete(optionalCourse.get());
        return ResponseEntity.ok(new CustomResponse<>(ok));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<Instructor>> deleteInstructorById(@PathVariable("id") Long id){
        Optional<Instructor> optionalInstructor = instructorRepository.findById(id);
        if (optionalInstructor.isPresent()){
            instructorRepository.delete(optionalInstructor.get());
            return ResponseEntity.ok(new CustomResponse<>(ok));
        }else {
            return ResponseEntity.ok(new CustomResponse<>(fail));
        }

    }

    @DeleteMapping("/delete/{marks_id}")
    public ResponseEntity<CustomResponse<Marks>> deleteMarksById(@PathVariable("marks_id") Long marks_id){
        Optional<Marks> optionalMarks = marksRepository.findById(marks_id);
        if (optionalMarks.isPresent()){
            marksRepository.delete(optionalMarks.get());
            return ResponseEntity.ok(new CustomResponse<>(ok));
        }else {
            return ResponseEntity.ok(new CustomResponse<>(fail));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<Parent>> deleteParentById(@PathVariable("id") Long id){
        Optional<Parent> optionalParent = parentRepository.findById(id);
        if (optionalParent.isPresent()){
            parentRepository.delete(optionalParent.get());
            return ResponseEntity.ok(new CustomResponse<>(ok));
        }else {
            return ResponseEntity.ok(new CustomResponse<>(fail));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<Student>> deleteStudentById(@PathVariable("id") Long id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()){
            studentRepository.delete(studentOptional.get());
            return ResponseEntity.ok(new CustomResponse<>(ok));
        }else {
            return ResponseEntity.ok(new CustomResponse<>(fail));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<User>> deleteUserById(@PathVariable("id") Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            userRepository.delete(optionalUser.get());
            return ResponseEntity.ok(new CustomResponse<>(ok));
        }else {
            return ResponseEntity.ok(new CustomResponse<>(fail));
        }
    }

}
