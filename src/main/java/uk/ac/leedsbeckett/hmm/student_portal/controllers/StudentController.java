package uk.ac.leedsbeckett.hmm.student_portal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Student;
import uk.ac.leedsbeckett.hmm.student_portal.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    public final StudentService studentService;

    public StudentController(StudentService studentService) {   // dependency injection of Student Service
        this.studentService = studentService;
    }

    @PostMapping("/registerStudent")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student ) {
        Student newStudent = studentService.saveStudent( student );
        return new ResponseEntity<>( newStudent,HttpStatus.CREATED );
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentbyID(@PathVariable String studentId){
        Student student = studentService.getStudent(studentId);
        return new ResponseEntity<>( student,HttpStatus.OK);
    }

    @PutMapping ("/{studentId}")
    public ResponseEntity<Student> updateStudentbyId(@PathVariable String studentId, @RequestBody Student updatedStudent) {
        Student student = studentService.updateStudent(studentId, updatedStudent);
        return new ResponseEntity<>( student,HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudentbyId(@PathVariable String studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> studentList = studentService.getAllStudents();
        return new ResponseEntity<>( studentList,HttpStatus.OK);
    }

    @PutMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<Student> enrollStudentInCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        Student student = studentService.enrollStudentInCourse(studentId, courseId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}