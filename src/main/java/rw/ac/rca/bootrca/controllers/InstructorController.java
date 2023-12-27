package rw.ac.rca.bootrca.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.bootrca.DTO.InstructorDTO;
import rw.ac.rca.bootrca.DTO.UserDTO;
import rw.ac.rca.bootrca.models.Instructor;
import rw.ac.rca.bootrca.services.impl.InstructorServiceImpl;
import rw.ac.rca.bootrca.utils.CustomResponse;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/instructor")
public class InstructorController {

    InstructorServiceImpl instructorService;

    @PostMapping("/add")
    public ResponseEntity<CustomResponse<Instructor>> addInstructor(@RequestBody InstructorDTO instructor) throws ParseException {
        return ResponseEntity.ok(new CustomResponse<>(instructorService.createInstructor(instructor)));
    }

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<List<Instructor>>> getAllInstructors(){
        return ResponseEntity.ok(new CustomResponse<>(instructorService.listAllInstructors()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse<Instructor>> updateInstructor(@PathVariable("id") UUID id, @RequestBody InstructorDTO instructorDTO) throws ParseException {
        return ResponseEntity.ok(new CustomResponse<>(instructorService.updateInstructor(id, instructorDTO)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<Instructor>> deleteInstructor(@PathVariable("id") UUID id){
        instructorService.deleteInstructor(id);
        return ResponseEntity.ok().build();
    }

}
