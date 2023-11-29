package rw.ac.rca.bootrca.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO extends PersonDTO{
    String studentCode;
    String parentFirstName;
    String parentLastName;
    String courseCode;
}
