package rw.ac.rca.bootrca.models;

import jakarta.persistence.*;
import lombok.*;
import rw.ac.rca.bootrca.utils.UserRole;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Person{
    @Id
    @GeneratedValue
    UUID id;
    String username;
    String password;
    @Enumerated(EnumType.STRING)
    UserRole userRole;
}
