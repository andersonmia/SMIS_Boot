package rw.ac.rca.bootrca.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    String courseName;
    String courseCode;
    Double totalMarks;
    String courseDuration;
    UUID instructorID;
}
