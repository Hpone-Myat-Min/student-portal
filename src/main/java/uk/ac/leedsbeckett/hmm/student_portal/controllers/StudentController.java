package uk.ac.leedsbeckett.hmm.student_portal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Invoice;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Student;
import uk.ac.leedsbeckett.hmm.student_portal.repositories.UserRepository;
import uk.ac.leedsbeckett.hmm.student_portal.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    public final StudentService studentService;
    private final UserRepository userRepository;

    public StudentController(StudentService studentService, UserRepository userRepository) {   // dependency injection of Student Service
        this.studentService = studentService;
        this.userRepository = userRepository;
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

    @PutMapping("/enroll/{userId}/{courseId}")
    public ResponseEntity<Student> enrollStudentInCourse(@PathVariable Long userId, @PathVariable Long courseId) {

        Student student = studentService.enrollStudentInCourse(userId, courseId);
        return new ResponseEntity<>(student, HttpStatus.OK);

    }
//
//    @PutMapping("/invoices/{reference}/pay")
//    public ResponseEntity<String> payInvoice(@PathVariable String reference) {
//        studentService.payInvoice(reference);
//        return ResponseEntity.ok("Invoice paid successfully.");
//    }

    @GetMapping("/invoices/{reference}/get")
    public ResponseEntity<Invoice> getInvoice(@PathVariable String reference) {
        Invoice invoice = studentService.getInvoice(reference);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @GetMapping("/graduation/{studentId}")
    public ResponseEntity<Boolean> viewGraduation(@PathVariable String studentId) {
        Boolean isGraduating = studentService.viewGradEligibility(studentId);
        return new ResponseEntity<>(isGraduating, HttpStatus.OK);
    }

}