package rw.ac.rca.bootrca.models;

import jakarta.persistence.*;
import lombok.*;
import rw.ac.rca.bootrca.utils.StudentStatus;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String studentCode;

    @ManyToOne
    Parent parent;

    @OneToMany(mappedBy = "student")
    List<Marks> marks;

    StudentStatus studentStatus;

    public Student(String firstName, String lastName, String gender, String email, Date dateOfBirth, Address address, String studentCode, Parent parent, StudentStatus studentStatus) {
        super(firstName, lastName, gender, email, dateOfBirth, address);
        this.studentCode = studentCode;
        this.parent = parent;
        this.studentStatus = studentStatus;
    }
}
