package uk.ac.leedsbeckett.hmm.student_portal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import uk.ac.leedsbeckett.hmm.student_portal.entities.User;

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
    private String qualification;
    private String university;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "student_course", // Join table name
            joinColumns = @JoinColumn(name = "student_id"), // Student foreign key
            inverseJoinColumns = @JoinColumn(name = "course_id") // Course foreign key
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Course> courses = new HashSet<>();

    @OneToOne(mappedBy = "student")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private User user;

    public Student(String studentId, String firstName, String lastName, String qualification, String university) {
    }

    public Student(){}
}
