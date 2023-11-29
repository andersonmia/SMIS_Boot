package rw.ac.rca.bootrca.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    @NotNull
    @NotEmpty
    String courseName;

    @NotEmpty
    @NotNull
    String courseCode;

    @NotEmpty
    @NotNull
    Double totalMarks;

    @NotEmpty
    @NotNull
    String courseDuration;

    @NotEmpty
    @NotNull
    String instructorFirstName;

    @NotEmpty
    @NotNull
    String instructorLastName;
}
