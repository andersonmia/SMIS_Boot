package rw.ac.rca.bootrca.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO extends PersonDTO{
    String studentCode;
    UUID parentID;
    String studentStatus;
}
