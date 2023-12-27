package rw.ac.rca.bootrca.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.bootrca.DTO.CourseDTO;
import rw.ac.rca.bootrca.models.Course;
import rw.ac.rca.bootrca.services.impl.CourseServiceImpl;
import rw.ac.rca.bootrca.utils.CustomResponse;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/course")
public class CourseController{

    CourseServiceImpl courseService;
    @GetMapping("/all")
        public ResponseEntity<CustomResponse<List<Course>>> getAllCourses(){
            return ResponseEntity.ok(new CustomResponse<>(courseService.listAllCourses()));
        }

        @PostMapping("/add")
        public ResponseEntity<Course> addCourse(CourseDTO courseDTO){
            return ResponseEntity.ok(courseService.addCourse(courseDTO));
        }

        @PutMapping("/update/{id}")
        public ResponseEntity<Course> updateCourse(@PathVariable("id") UUID id, @RequestBody CourseDTO courseDTO){
            return ResponseEntity.ok(courseService.updateCourse(id, courseDTO));
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Course> deleteCourse(@PathVariable("id") UUID id){
            courseService.deleteCourse(id);
            return ResponseEntity.ok().build();
        }

        @GetMapping("/instructor/{id}")
        public ResponseEntity<List<Course>> getCoursesByInstructor(@PathVariable("id") UUID id){
            return ResponseEntity.ok(courseService.listCoursesByInstructor(id));
        }
}
