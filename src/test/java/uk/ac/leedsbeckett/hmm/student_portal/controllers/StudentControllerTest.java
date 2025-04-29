package uk.ac.leedsbeckett.hmm.student_portal.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Student;
import uk.ac.leedsbeckett.hmm.student_portal.services.StudentService;

import static org.junit.jupiter.api.Assertions.*;

class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student("Jason", "Min", "hmm@gmail.com", "Bachelor", "James Cook");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void registerStudent() {
    }

    @Test
    void getStudentbyID() {
    }

    @Test
    void updateStudentbyId() {
    }

    @Test
    void deleteStudentbyId() {
    }

    @Test
    void getStudents() {
    }

    @Test
    void enrollStudentInCourse() {
    }
}