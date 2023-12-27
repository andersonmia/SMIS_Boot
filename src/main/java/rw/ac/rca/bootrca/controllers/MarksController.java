package rw.ac.rca.bootrca.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.bootrca.DTO.MarksDTO;
import rw.ac.rca.bootrca.models.Course;
import rw.ac.rca.bootrca.models.Marks;
import rw.ac.rca.bootrca.models.Student;
import rw.ac.rca.bootrca.repositories.CourseRepository;
import rw.ac.rca.bootrca.repositories.MarksRepository;
import rw.ac.rca.bootrca.repositories.StudentRepository;
import rw.ac.rca.bootrca.services.impl.MarksServiceImpl;
import rw.ac.rca.bootrca.utils.CustomResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/marks")
public class MarksController {
    MarksServiceImpl marksService;

    @PostMapping("/add")
    public ResponseEntity<CustomResponse<Marks>> addMarks(@Valid @RequestBody MarksDTO marksDTO){
        return ResponseEntity.ok(new CustomResponse<>(marksService.addMarks(marksDTO)));
    }

   @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse<Marks>> updateMarks(@PathVariable("id") UUID id, @RequestBody MarksDTO marksDTO){
        return ResponseEntity.ok(new CustomResponse<>(marksService.updateMarks(id, marksDTO)));
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<CustomResponse<List<Marks>>> getMarksByCourse(@PathVariable("id") UUID id){
        return ResponseEntity.ok(new CustomResponse<>(marksService.listMarksByCourse(id)));
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<CustomResponse<List<Marks>>> getMarksByStudent(@PathVariable("id") UUID id){
        return ResponseEntity.ok(new CustomResponse<>(marksService.listMarksByStudent(id)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<Marks>> deleteMarks(@PathVariable("id") UUID id){
        marksService.deleteMarks(id);
        return ResponseEntity.ok().build();
    }
}

