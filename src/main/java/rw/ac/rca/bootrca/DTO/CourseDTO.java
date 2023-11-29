package rw.ac.rca.bootrca.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.bootrca.utils.CourseDuration;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    String courseName;
    String courseCode;
    Double totalMarks;
    String courseDuration;
    String instructorFirstName;
    String instructorLastName;
}
