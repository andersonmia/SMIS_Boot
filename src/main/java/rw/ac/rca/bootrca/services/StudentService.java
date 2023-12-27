package rw.ac.rca.bootrca.services;

import rw.ac.rca.bootrca.DTO.StudentDTO;
import rw.ac.rca.bootrca.DTO.UserDTO;
import rw.ac.rca.bootrca.models.Student;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

public interface StudentService {
    List<Student> listAllStudents();
    Student addStudent(StudentDTO studentDTO) throws ParseException;
    Student updateStudent(UUID studentID, StudentDTO studentDTO) throws ParseException;
    void deleteStudent(UUID studentID);
}
