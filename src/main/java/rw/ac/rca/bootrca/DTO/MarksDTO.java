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
public class MarksDTO {

    @NotEmpty
    double score;

    @NotEmpty
    @NotNull
    String courseCode;

    @NotEmpty
    @NotNull
    String studentCode;
}
