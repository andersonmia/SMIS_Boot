package rw.ac.rca.bootrca.services;

import rw.ac.rca.bootrca.DTO.StudentDTO;
import rw.ac.rca.bootrca.models.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    List<Student> listAllStudents();
    Student addStudent(StudentDTO studentDTO);
    Student updateStudent(UUID studentID, StudentDTO studentDTO);
    List<Student> listStudentsByCourse(UUID courseID);
    void deleteStudent(UUID studentID, StudentDTO studentDTO);
}
