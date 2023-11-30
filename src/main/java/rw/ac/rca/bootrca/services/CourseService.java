package rw.ac.rca.bootrca.services;

import rw.ac.rca.bootrca.DTO.CourseDTO;
import rw.ac.rca.bootrca.models.Course;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    Course addCourse(CourseDTO courseDTO);
    Course updateCourse(CourseDTO courseDTO);
    void deleteCourse(CourseDTO courseDTO);
    List<Course> listAllCourses();
    List<Course> listCoursesByInstructor(UUID instructorID);
    List<Course> listCoursesByStudent(UUID studentID);
    Course updateCourse(UUID courseID, CourseDTO courseDTO);
}
