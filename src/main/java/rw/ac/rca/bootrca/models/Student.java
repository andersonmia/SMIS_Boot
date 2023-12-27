package rw.ac.rca.bootrca.models;

import jakarta.persistence.*;
import lombok.*;
import rw.ac.rca.bootrca.utils.StudentStatus;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Person {
    @Id
    @GeneratedValue
    UUID id;
    String studentCode;
    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    Parent parent;
    @Enumerated(EnumType.STRING)
    StudentStatus studentStatus;
    @ManyToMany(mappedBy = "students")
    List<Course> courses;
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    User user;
}
