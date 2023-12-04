package rw.ac.rca.bootrca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.bootrca.models.Instructor;

import java.util.UUID;

public interface InstructorRepository extends JpaRepository<Instructor, UUID> {
    Instructor searchInstructorByFirstNameAndLastName(String first, String last);
}
