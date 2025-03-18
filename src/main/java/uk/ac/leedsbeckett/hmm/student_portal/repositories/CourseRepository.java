package uk.ac.leedsbeckett.hmm.student_portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
