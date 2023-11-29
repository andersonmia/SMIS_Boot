package rw.ac.rca.bootrca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.bootrca.models.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    Instructor searchInstructorByFirstNameAndLastName(String first, String last);
}
