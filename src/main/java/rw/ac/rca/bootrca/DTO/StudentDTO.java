package rw.ac.rca.bootrca.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    String studentStatus;
}
