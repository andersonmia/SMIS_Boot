package rw.ac.rca.bootrca.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.bootrca.DTO.MarksDTO;
import rw.ac.rca.bootrca.exceptions.CourseNotFoundException;
import rw.ac.rca.bootrca.exceptions.MarksNotFoundException;
import rw.ac.rca.bootrca.exceptions.StudentNotFoundException;
import rw.ac.rca.bootrca.models.Course;
import rw.ac.rca.bootrca.models.Marks;
import rw.ac.rca.bootrca.models.Student;
import rw.ac.rca.bootrca.repositories.CourseRepository;
import rw.ac.rca.bootrca.repositories.MarksRepository;
import rw.ac.rca.bootrca.repositories.StudentRepository;
import rw.ac.rca.bootrca.services.MarksService;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MarksServiceImpl implements MarksService {


    final MarksRepository marksRepository;
    final StudentRepository studentRepository;
    final CourseRepository courseRepository;
    @Override
    public List<Marks> listMarksByCourse(UUID courseID) {
        return marksRepository.getMarksByCourseIdOrderByStudent(courseID);
    }

    @Override
    public List<Marks> listMarksByStudent(UUID studentID) {
        return marksRepository.getMarksByStudentId(studentID);
    }

    @Override
    public Marks addMarks(MarksDTO marksDTO) {
        Student existingStudent = studentRepository.findById(marksDTO.getStudentID())
                .orElseThrow(
                        () -> new StudentNotFoundException("Student "+marksDTO.getStudentID()+" Not Found")
                );
        Course existingCourse = courseRepository.findById(marksDTO.getCourseID())
                .orElseThrow(
                        () -> new CourseNotFoundException("Course "+marksDTO.getCourseID()+" Not Found")
                );

        Marks newMarks = new Marks();
        newMarks.setScore(marksDTO.getScore());
        newMarks.setStudent(existingStudent);
        newMarks.setCourse(existingCourse);
        return marksRepository.save(newMarks);
    }

    @Override
    public Marks updateMarks(UUID marksID, MarksDTO marksDTO) {
        Marks existingMarks = marksRepository.findById(marksID)
                .orElseThrow(
                        () -> new MarksNotFoundException("Marks "+marksID+" Not Found")
                );
        existingMarks.setScore(marksDTO.getScore());
        return marksRepository.save(existingMarks);
    }

    @Override
    public void deleteMarks(UUID marksID) {
        Marks existingMarks = marksRepository.findById(marksID)
                .orElseThrow(
                        () -> new MarksNotFoundException("Marks "+marksID+" Not Found")
                );
        marksRepository.delete(existingMarks);
    }
}
