package rw.ac.rca.bootrca.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.bootrca.DTO.MarksDTO;
import rw.ac.rca.bootrca.models.Course;
import rw.ac.rca.bootrca.models.Marks;
import rw.ac.rca.bootrca.models.Student;
import rw.ac.rca.bootrca.repositories.CourseRepository;
import rw.ac.rca.bootrca.repositories.MarksRepository;
import rw.ac.rca.bootrca.repositories.StudentRepository;
import rw.ac.rca.bootrca.utils.CustomResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marks")
public class MarksController {
    String ok = "Operation Successful...";
    String fail = "Marks Not Found ...";
    final CourseRepository courseRepository;
    final MarksRepository marksRepository;
    final StudentRepository studentRepository;

    public MarksController(CourseRepository courseRepository, MarksRepository marksRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.marksRepository = marksRepository;
        this.studentRepository = studentRepository;
    }
//================================================Reading Marks By Students, Instructors, and Parents================================================================

    @GetMapping("/listMarks/course/{course_id}")
    public ResponseEntity<CustomResponse<List<Marks>>> listMarksByCourse(@PathVariable("course_id") Long course_id){
        Optional<Course> optionalCourse = courseRepository.findById(course_id);
        if (optionalCourse.isPresent()){
            Course existingCourse = optionalCourse.get();
            List<Marks> marksList= marksRepository.getMarksByCourseOrderByStudent(existingCourse);
            return ResponseEntity.ok(new CustomResponse<>(ok,marksList));
        }else {
            return ResponseEntity.ok(new CustomResponse<>(fail));
        }
    }

    @GetMapping("/listMarks/student/{student_id}")
    public ResponseEntity<CustomResponse<List<Marks>>> listMarksByStudentId(@PathVariable("student_id") Long student_id){
        Optional<Student> optionalStudent = studentRepository.findById(student_id);
        if (optionalStudent.isPresent()){
            Student existingStudent = optionalStudent.get();
            List<Marks> marksList = marksRepository.getMarksByStudent(existingStudent);
            return ResponseEntity.ok(new CustomResponse<>(ok,marksList));
        }else {
            return ResponseEntity.ok(new CustomResponse<>(fail));
        }

    }

//================================================Entering, Updating, and Deleting Marks For By Instructors================================================================

    @PostMapping("/add")
    public ResponseEntity<CustomResponse<Marks>> addMarksByStudentAndCourse(@RequestBody @Valid MarksDTO marksDTO){
        Optional<Student> optionalStudent = Optional.ofNullable(studentRepository.searchStudentByStudentCode(marksDTO.getStudentCode()));
        Optional<Course> optionalCourse = Optional.ofNullable(courseRepository.searchCourseByCourseCode(marksDTO.getCourseCode()));

        if (optionalStudent.isPresent() & optionalCourse.isPresent()){
            Marks marks = new Marks();
            marks.setStudent(optionalStudent.get());
            marks.setCourse(optionalCourse.get());
            marks.setScore(marksDTO.getScore());

            return ResponseEntity.ok(new CustomResponse<>(ok, marksRepository.save(marks)));
        }else {
            return ResponseEntity.ok(new CustomResponse<>(fail));
        }
    }

    @PutMapping("/update/{marks_id}")
    public ResponseEntity<CustomResponse<Marks>> update(@PathVariable("marks_id") Long marks_id, @RequestBody @Valid MarksDTO marksDTO){
       Optional<Marks> optionalMarks = marksRepository.findById(marks_id);
        if (optionalMarks.isPresent()){
            Marks newMarks = optionalMarks.get();
            newMarks.setScore(marksDTO.getScore());

            return ResponseEntity.ok(new CustomResponse<>(ok, marksRepository.save(newMarks)));
        }else {
            return ResponseEntity.ok(new CustomResponse<>(fail));
        }
    }


}

