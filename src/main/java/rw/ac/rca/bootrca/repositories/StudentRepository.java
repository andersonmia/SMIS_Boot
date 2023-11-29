package rw.ac.rca.bootrca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.bootrca.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student searchStudentByFirstNameAndLastName(String firstname, String lastname);
    Student searchStudentByStudentCode(String studentCode);
}
