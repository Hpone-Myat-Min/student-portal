package uk.ac.leedsbeckett.hmm.student_portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentId( String studentId );
    void deleteByStudentId(String studentId);
}
