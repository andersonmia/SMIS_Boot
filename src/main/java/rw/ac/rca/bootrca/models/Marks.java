package rw.ac.rca.bootrca.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    double score;

    @ManyToOne
    @JoinColumn(name = "student_id" , referencedColumnName ="id" )
    Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    Course course;

    public Marks(double score, Student student, Course course) {
        this.score = score;
        this.student = student;
        this.course = course;
    }
}
