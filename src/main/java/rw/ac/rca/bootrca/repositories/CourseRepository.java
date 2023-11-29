package rw.ac.rca.bootrca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.bootrca.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course searchCourseByCourseCode(String courseCode);
}
