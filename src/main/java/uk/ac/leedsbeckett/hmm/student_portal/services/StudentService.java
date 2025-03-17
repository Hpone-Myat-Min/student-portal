package uk.ac.leedsbeckett.hmm.student_portal.services;

import uk.ac.leedsbeckett.hmm.student_portal.entities.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    Student getStudent(String studentId);
    List<Student> getAllStudents();

}
