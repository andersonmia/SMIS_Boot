package rw.ac.rca.bootrca.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
abstract public class PersonDTO {
    @NotEmpty
    @NotNull
    String firstName;

    @NotEmpty
    @NotNull
    String lastName;

    @NotEmpty
    @NotNull
    String gender;

    @NotEmpty
    @NotNull
    String email;

    @NotEmpty
    @NotNull
    String dateOfBirth;

    @NotEmpty
    @NotNull
    String villageName;
}
