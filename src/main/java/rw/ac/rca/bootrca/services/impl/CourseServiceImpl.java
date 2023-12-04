package rw.ac.rca.bootrca.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.bootrca.DTO.CourseDTO;
import rw.ac.rca.bootrca.exceptions.CourseNotFoundException;
import rw.ac.rca.bootrca.exceptions.InstructorNotFoundException;
import rw.ac.rca.bootrca.models.Course;
import rw.ac.rca.bootrca.models.Instructor;
import rw.ac.rca.bootrca.repositories.CourseRepository;
import rw.ac.rca.bootrca.repositories.InstructorRepository;
import rw.ac.rca.bootrca.services.CourseService;
import rw.ac.rca.bootrca.utils.CourseDuration;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    final InstructorRepository instructorRepository;
    final CourseRepository courseRepository;


    @Override
    public Course addCourse(CourseDTO courseDTO) {
        Instructor instructor = instructorRepository.findById(courseDTO.getInstructorID())
                .orElseThrow(
                        () -> new InstructorNotFoundException("Instructor "+courseDTO.getInstructorID()+" Not Found")
                );

        Course newCourse = new Course();
        newCourse.setCourseCode(courseDTO.getCourseCode());
        newCourse.setCourseName(courseDTO.getCourseName());
        newCourse.setTotalMarks(courseDTO.getTotalMarks());
        newCourse.setInstructor(instructor);
        newCourse.setCourseDuration(CourseDuration.valueOf(courseDTO.getCourseDuration()));
        return courseRepository.save(newCourse);
    }

    @Override
    public void deleteCourse(UUID courseID) {
        Course course = courseRepository.findById(courseID)
                .orElseThrow(
                        () -> new CourseNotFoundException("Course "+courseID+" Not Found")
                );

        courseRepository.delete(course);
    }

    @Override
    public List<Course> listAllCourses() {
        return courseRepository.findAll();

    }

    @Override
    public List<Course> listCoursesByInstructor(UUID instructorID) {
        return courseRepository.searchCoursesByInstructorId(instructorID);
    }


    @Override
    public Course updateCourse(UUID courseID, CourseDTO courseDTO) {
        Course existingCourse = courseRepository.findById(courseID)
                .orElseThrow(
                        () -> new CourseNotFoundException("Course "+courseID+" Not Found")
                );

        if (courseDTO.getCourseCode() != null)
            existingCourse.setCourseCode(courseDTO.getCourseCode());
        if (courseDTO.getCourseName() != null)
            existingCourse.setCourseName(courseDTO.getCourseName());
        if (courseDTO.getCourseDuration() != null)
            existingCourse.setCourseDuration(CourseDuration.valueOf(courseDTO.getCourseDuration()));
        if (courseDTO.getTotalMarks() != null)
            existingCourse.setTotalMarks(courseDTO.getTotalMarks());
        if (courseDTO.getInstructorID() != null) {
            Optional<Instructor> optionalInstructor = instructorRepository.findById(courseDTO.getInstructorID());
            optionalInstructor.ifPresent(existingCourse::setInstructor);
        }
        return courseRepository.save(existingCourse);
    }
}
