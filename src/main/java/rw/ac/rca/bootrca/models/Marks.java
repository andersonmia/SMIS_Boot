package rw.ac.rca.bootrca.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Marks {
    @Id
    @GeneratedValue
    UUID id;
    double score;
    @ManyToOne
    @JoinColumn(name = "student_id" , referencedColumnName ="id" )
    Student student;
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    Course course;
}
