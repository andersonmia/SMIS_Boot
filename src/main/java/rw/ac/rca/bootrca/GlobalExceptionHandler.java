package rw.ac.rca.bootrca;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rw.ac.rca.bootrca.exceptions.CourseNotFoundException;
import rw.ac.rca.bootrca.exceptions.InstructorNotFoundException;
import rw.ac.rca.bootrca.exceptions.StudentNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<?> handleCourseNotFoundException(CourseNotFoundException exception) {
        return new ResponseEntity<>("Course not found: " + exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InstructorNotFoundException.class)
    public ResponseEntity<?> handleInstructorNotFoundException(InstructorNotFoundException exception) {
        return new ResponseEntity<>("Instructor not found: " + exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<?> handleGlobalException(StudentNotFoundException exception) {
        return new ResponseEntity<>("Student not found: " + exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception) {
        return new ResponseEntity<>("An error occurred: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
