package rw.ac.rca.bootrca.controllers;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import rw.ac.rca.bootrca.models.PhoneNumber;
import rw.ac.rca.bootrca.models.Student;
import rw.ac.rca.bootrca.repositories.StudentRepository;
import rw.ac.rca.bootrca.utils.CourseDuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public abstract class BaseController {

    final StudentRepository studentRepository;

    public BaseController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    protected List<Student> processStudents(List<String> studentCodes) {
        List<Student> validStudents = new ArrayList<>();
        for (String studentCode : studentCodes) {
            Optional<Student> student = Optional.ofNullable(studentRepository.searchStudentByStudentCode(studentCode));
            student.ifPresent(validStudents::add);
        }
        return validStudents;
    }

    protected List<PhoneNumber> processPhoneNumbers(List<Long> phoneNumbers) {
        List<PhoneNumber> validPhoneNumbers = new ArrayList<>();
        for (Long phoneNumber : phoneNumbers) {
            PhoneNumber validPhoneNumber = new PhoneNumber(250, phoneNumber);
            validPhoneNumbers.add(validPhoneNumber);
        }
        return validPhoneNumbers;
    }

    protected Date processStringDate(String stringDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.parse(stringDate);
    }

    protected CourseDuration processStringDuration(String stringDuration){
        return CourseDuration.valueOf(stringDuration.toUpperCase());
    }
}
