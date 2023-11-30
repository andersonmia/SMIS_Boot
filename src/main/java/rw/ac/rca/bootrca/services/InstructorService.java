package rw.ac.rca.bootrca.services;

import rw.ac.rca.bootrca.DTO.InstructorDTO;
import rw.ac.rca.bootrca.models.Instructor;

import java.util.List;
import java.util.UUID;

public interface InstructorService {
    Instructor createInstructor(InstructorDTO instructorDTO);
    List<Instructor> listAllInstructors();
    Instructor updateInstructor(UUID instructorID, InstructorDTO instructorDTO);
    void deleteInstructor(UUID instructorID);
}
