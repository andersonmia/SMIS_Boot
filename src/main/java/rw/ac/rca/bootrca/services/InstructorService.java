package rw.ac.rca.bootrca.services;

import rw.ac.rca.bootrca.DTO.InstructorDTO;
import rw.ac.rca.bootrca.DTO.UserDTO;
import rw.ac.rca.bootrca.models.Instructor;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

public interface InstructorService {
    Instructor createInstructor(InstructorDTO instructorDTO) throws ParseException;
    List<Instructor> listAllInstructors();
    Instructor updateInstructor(UUID instructorID, InstructorDTO instructorDTO) throws ParseException;
    void deleteInstructor(UUID instructorID);
}
