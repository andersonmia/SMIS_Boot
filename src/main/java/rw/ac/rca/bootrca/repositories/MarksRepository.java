package rw.ac.rca.bootrca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.bootrca.models.Course;
import rw.ac.rca.bootrca.models.Marks;
import rw.ac.rca.bootrca.models.Student;

import java.util.List;
import java.util.Optional;

public interface MarksRepository extends JpaRepository<Marks, Long> {
    List<Marks> getMarksByStudent(Student student);
    List<Marks> getMarksByCourseOrderByStudent(Course course);
}
