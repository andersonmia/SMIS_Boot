package rw.ac.rca.bootrca.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
abstract class Person implements Serializable {
    String firstName;
    String lastName;
    String gender;
    String email;
    Date dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    Address address;
}
