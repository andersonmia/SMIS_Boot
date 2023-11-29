package rw.ac.rca.bootrca.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDTO extends PersonDTO {
    List<Long> phoneNumbers;
}
