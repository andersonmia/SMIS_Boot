package rw.ac.rca.bootrca.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
abstract public class PersonDTO {
    String firstName;
    String lastName;
    String gender;
    String email;
    String dateOfBirth;
    String villageName;
}
