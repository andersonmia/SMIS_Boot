package rw.ac.rca.bootrca.models;

import jakarta.persistence.*;
import lombok.*;
import rw.ac.rca.bootrca.utils.CourseDuration;

import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String courseName;
    String courseCode;
    Double totalMarks;
    CourseDuration courseDuration;

    @ManyToOne
    Instructor instructor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    List<Marks> marks;

    public Course(String courseName, String courseCode, Double totalMarks, CourseDuration courseDuration, Instructor instructor) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.totalMarks = totalMarks;
        this.courseDuration = courseDuration;
        this.instructor = instructor;
    }
}
