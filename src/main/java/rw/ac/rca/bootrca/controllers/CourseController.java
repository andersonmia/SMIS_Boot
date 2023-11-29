package rw.ac.rca.bootrca.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.bootrca.DTO.CourseDTO;
import rw.ac.rca.bootrca.models.Course;
import rw.ac.rca.bootrca.models.Instructor;
import rw.ac.rca.bootrca.repositories.CourseRepository;
import rw.ac.rca.bootrca.repositories.InstructorRepository;
import rw.ac.rca.bootrca.repositories.StudentRepository;
import rw.ac.rca.bootrca.utils.CourseDuration;
import rw.ac.rca.bootrca.utils.CustomResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/course")
public class CourseController extends BaseController{
    String ok = "Operation Successful...";
    String fail = "Course Not Found ...";

    final CourseRepository courseRepository;
    final InstructorRepository instructorRepository;

    public CourseController(StudentRepository studentRepository, CourseRepository courseRepository, InstructorRepository instructorRepository) {
        super(studentRepository);
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<List<Course>>> listAll(){
        List<Course> courses = courseRepository.findAll();
        return ResponseEntity.ok(new CustomResponse<>(ok, courses));
    }

    @PostMapping("/add")
    public ResponseEntity<CustomResponse<Course>> add(@RequestBody @Valid CourseDTO courseDTO){

        Optional<Instructor> optionalInstructor = Optional.ofNullable(instructorRepository.searchInstructorByFirstNameAndLastName(courseDTO.getInstructorFirstName(), courseDTO.getInstructorLastName()));
        CourseDuration newDuration = processCourseDuration(courseDTO.getCourseDuration());

        if (optionalInstructor.isEmpty())
            return ResponseEntity.ok(new CustomResponse<>(fail));

        Instructor newInstructor = optionalInstructor.get();
        Course newCourse = new Course(courseDTO.getCourseName(), courseDTO.getCourseCode(), courseDTO.getTotalMarks(), newDuration, newInstructor);

        return ResponseEntity.ok(new CustomResponse<>(ok, courseRepository.save(newCourse)));
    }

    @PutMapping("/update/{courseCode}")
    public ResponseEntity<CustomResponse<Course>> updateByCourseCode(@PathVariable("courseCode") String courseCode, @RequestBody @Valid CourseDTO courseDTO){
        Optional<Course> optionalCourse = Optional.ofNullable(courseRepository.searchCourseByCourseCode(courseCode));
        Optional<Instructor> optionalInstructor = Optional.ofNullable(instructorRepository.searchInstructorByFirstNameAndLastName(courseDTO.getInstructorFirstName(), courseDTO.getInstructorLastName()));

        if (optionalCourse.isEmpty())
            return ResponseEntity.ok(new CustomResponse<>(fail));

        Course existingCourse = optionalCourse.get();

        CourseDuration newDuration = processCourseDuration(courseDTO.getCourseDuration());
        existingCourse.setCourseDuration(newDuration);
        existingCourse.setCourseName(courseDTO.getCourseName());

        if (optionalInstructor.isPresent()){
            Instructor existingInstructor = optionalInstructor.get();
            existingCourse.setInstructor(existingInstructor);
        }
        existingCourse.setCourseCode(courseDTO.getCourseCode());
        existingCourse.setTotalMarks(courseDTO.getTotalMarks());

        return ResponseEntity.ok(new CustomResponse<>(ok, courseRepository.save(existingCourse)));

    }


}
