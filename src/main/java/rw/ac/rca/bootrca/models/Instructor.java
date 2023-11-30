package rw.ac.rca.bootrca.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instructor extends Person{
    @Id
    @GeneratedValue
    UUID id;
    String phoneNumber;
}
