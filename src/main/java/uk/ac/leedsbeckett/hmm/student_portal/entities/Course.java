package uk.ac.leedsbeckett.hmm.student_portal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private double fee;
    @ManyToMany(mappedBy = "courses") // Bidirectional mapping
    @ToString.Exclude
    @JsonIgnore
    private Set<Student> students = new HashSet<>();
}
