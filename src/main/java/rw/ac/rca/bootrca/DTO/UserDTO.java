package rw.ac.rca.bootrca.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO extends PersonDTO{
    String username;
    String password;
    String userRole;
}
