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
    @NotEmpty
    @NotNull
    String studentCode;

    @NotEmpty
    @NotNull
    String parentFirstName;

    @NotEmpty
    @NotNull
    String parentLastName;

    @NotEmpty
    @NotNull
    String studentStatus;
}
