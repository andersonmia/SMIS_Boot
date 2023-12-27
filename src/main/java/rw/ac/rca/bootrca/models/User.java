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
public class User{
    @Id
    @GeneratedValue
    UUID id;
    String username;
    String password;
    @Enumerated(EnumType.STRING)
    UserRole role;

    @OneToOne
    @JoinColumn(name = "instructor_id")
    Instructor instructor;

    @OneToOne
    @JoinColumn(name = "parent_id")
    Parent parent;

    @OneToOne
    @JoinColumn(name = "student_id")
    Student student;

}
