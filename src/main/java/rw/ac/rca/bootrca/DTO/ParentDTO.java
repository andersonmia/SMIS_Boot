package rw.ac.rca.bootrca.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParentDTO extends PersonDTO{
    int countryCode;
    int phoneNumber;
}
