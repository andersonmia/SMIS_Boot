package rw.ac.rca.bootrca.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import rw.ac.rca.bootrca.utils.UserRole;

import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends Person{
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    Long id;

    String username;
    String password;
    UserRole userRole;

    public User(String firstName, String lastName, String gender, String email, Date dateOfBirth, Address address, String username, String password, UserRole userRole) {
        super(firstName, lastName, gender, email, dateOfBirth, address);
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }
}
