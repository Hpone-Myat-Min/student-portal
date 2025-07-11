package uk.ac.leedsbeckett.hmm.student_portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByDescriptionContainingIgnoreCase(String description);

}
