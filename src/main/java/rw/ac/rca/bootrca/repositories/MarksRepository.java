package rw.ac.rca.bootrca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.bootrca.models.Course;
import rw.ac.rca.bootrca.models.Marks;
import rw.ac.rca.bootrca.models.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MarksRepository extends JpaRepository<Marks, UUID> {
    List<Marks> getMarksByStudentId(UUID studentID);
    List<Marks> getMarksByCourseIdOrderByStudent(UUID courseID);
}
