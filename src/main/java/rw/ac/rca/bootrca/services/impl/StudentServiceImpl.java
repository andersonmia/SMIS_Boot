package rw.ac.rca.bootrca.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.bootrca.DTO.PersonDTO;
import rw.ac.rca.bootrca.DTO.StudentDTO;
import rw.ac.rca.bootrca.DTO.UserDTO;
import rw.ac.rca.bootrca.exceptions.ParentNotFoundException;
import rw.ac.rca.bootrca.models.Parent;
import rw.ac.rca.bootrca.models.Student;
import rw.ac.rca.bootrca.models.User;
import rw.ac.rca.bootrca.repositories.CourseRepository;
import rw.ac.rca.bootrca.repositories.ParentRepository;
import rw.ac.rca.bootrca.repositories.StudentRepository;
import rw.ac.rca.bootrca.services.StudentService;
import rw.ac.rca.bootrca.utils.DateParser;
import rw.ac.rca.bootrca.utils.StudentStatus;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    final StudentRepository studentRepository;
    final CourseRepository courseRepository;
    final ParentRepository parentRepository;

    UserServiceImpl userService;
    @Override
    public List<Student> listAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(StudentDTO studentDTO) throws ParseException {
        User user = userService.addUser(studentDTO.getUserDTO());

        DateParser dateParser = new DateParser();
        Date dateOfBirth = dateParser.convertStringToDate(studentDTO.getDateOfBirth());

        Parent parent = parentRepository.findById(studentDTO.getParentID())
                .orElseThrow(
                        () -> new ParentNotFoundException("Parent  " + studentDTO.getParentID() + " Not Found")
                );

        Student student = new Student();
        student.setStudentStatus(StudentStatus.valueOf(studentDTO.getStudentStatus()));
        student.setStudentCode(studentDTO.getStudentCode());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setGender(studentDTO.getGender());
        student.setEmail(studentDTO.getEmail());
        student.setDateOfBirth(dateOfBirth);
        student.setParent(parent);
        student.setUser(user);

        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(UUID studentID, StudentDTO studentDTO) throws ParseException {
        Student existingStudent = studentRepository.findById(studentID)
                .orElseThrow(
                        () -> new ParentNotFoundException("Student  " + studentID + " Not Found")
                );
        if (studentDTO.getFirstName() != null)
            existingStudent.setFirstName(studentDTO.getFirstName());
        if (studentDTO.getLastName() != null)
            existingStudent.setLastName(studentDTO.getLastName());
        if (studentDTO.getGender() != null)
            existingStudent.setGender(studentDTO.getGender());
        if (studentDTO.getEmail() != null)
            existingStudent.setEmail(studentDTO.getEmail());
        if (studentDTO.getDateOfBirth() != null) {
            DateParser dateParser = new DateParser();
                Date dateOfBirth = dateParser.convertStringToDate(studentDTO.getDateOfBirth());
                existingStudent.setDateOfBirth(dateOfBirth);

        }
        if (studentDTO.getStudentStatus() != null)
            existingStudent.setStudentStatus(StudentStatus.valueOf(studentDTO.getStudentStatus()));
        if (studentDTO.getStudentCode() != null)
            existingStudent.setStudentCode(studentDTO.getStudentCode());
        if (studentDTO.getParentID() != null) {
            Parent parent = parentRepository.findById(studentDTO.getParentID())
                    .orElseThrow(
                            () -> new ParentNotFoundException("Parent  " + studentDTO.getParentID() + " Not Found")
                    );
            existingStudent.setParent(parent);
        }
        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(UUID studentID) {
        Student student = studentRepository.findById(studentID)
                .orElseThrow(
                        () -> new ParentNotFoundException("Student  " + studentID + " Not Found")
                );
        studentRepository.delete(student);
    }
}
