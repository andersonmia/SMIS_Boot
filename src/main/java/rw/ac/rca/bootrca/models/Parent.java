package rw.ac.rca.bootrca.models;

import jakarta.persistence.*;
import lombok.*;
import rw.ac.rca.bootrca.utils.PhoneNumber;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Parent extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @OneToMany
    List<PhoneNumber> phoneNumbers;
    @OneToMany
    List<Student> children;

    public Parent(String firstName, String lastName, String gender, String email, Date dateOfBirth, Address address, List<PhoneNumber> phoneNumbers) {
        super(firstName, lastName, gender, email, dateOfBirth, address);
        this.phoneNumbers = phoneNumbers;
    }
}
