package uk.ac.leedsbeckett.hmm.student_portal.services;

import org.springframework.stereotype.Service;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Invoice;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    Student getStudent(String studentId);
    Student updateStudent(String studentId, Student updatedStudent);
    void deleteStudent(String studentId);
    List<Student> getAllStudents();
    Student enrollStudentInCourse(Long userId, Long courseId);
//    void updateStudentRole(Long studentId, String role);

//    void payInvoice(String reference);
    Invoice getInvoice(String reference);
    Boolean viewGradEligibility(String studentId);
}
