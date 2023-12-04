package rw.ac.rca.bootrca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.bootrca.models.Course;
import rw.ac.rca.bootrca.models.Instructor;
import rw.ac.rca.bootrca.models.Student;

import java.util.List;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
    List<Course> searchCoursesByInstructorId(UUID instructorID);
}
