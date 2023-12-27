package rw.ac.rca.bootrca.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.bootrca.DTO.ParentDTO;
import rw.ac.rca.bootrca.models.Parent;
import rw.ac.rca.bootrca.repositories.ParentRepository;
import rw.ac.rca.bootrca.repositories.StudentRepository;
import rw.ac.rca.bootrca.services.impl.ParentServiceImpl;
import rw.ac.rca.bootrca.utils.CustomResponse;

import java.text.ParseException;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/parent")
public class ParentController {
    ParentServiceImpl parentService;

    @PostMapping("/add")
    public ResponseEntity<CustomResponse<Parent>> addParent(@RequestBody ParentDTO parentDTO) throws ParseException {
        return ResponseEntity.ok(new CustomResponse<>(parentService.createParent(parentDTO)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse<Parent>> updateParent(@PathVariable("id") UUID id, @RequestBody ParentDTO parentDTO) throws ParseException {
        return ResponseEntity.ok(new CustomResponse<>(parentService.updateParent(id, parentDTO)));
    }

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<Iterable<Parent>>> getAllParents(){
        return ResponseEntity.ok(new CustomResponse<>(parentService.listAllParents()));
    }


}
