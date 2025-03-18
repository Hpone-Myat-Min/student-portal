package uk.ac.leedsbeckett.hmm.student_portal.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String qualification;
    private String university;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "student_course", // Join table name
            joinColumns = @JoinColumn(name = "student_id"), // Student foreign key
            inverseJoinColumns = @JoinColumn(name = "course_id") // Course foreign key
    )
    @ToString.Exclude
    private Set<Course> courses = new HashSet<>();

}
