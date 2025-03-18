package uk.ac.leedsbeckett.hmm.student_portal.entities;

import jakarta.persistence.*;
import lombok.Data;

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
}
