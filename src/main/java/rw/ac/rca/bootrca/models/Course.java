package rw.ac.rca.bootrca.models;

import jakarta.persistence.*;
import lombok.*;
import rw.ac.rca.bootrca.utils.CourseDuration;

import java.util.UUID;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue
    UUID id;
    String courseName;
    String courseCode;
    Double totalMarks;
    @Enumerated(EnumType.STRING)
    CourseDuration courseDuration;
    @ManyToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    Instructor instructor;
}
