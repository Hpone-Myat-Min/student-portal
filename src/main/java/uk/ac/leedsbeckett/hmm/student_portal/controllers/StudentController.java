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

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentbyID(@PathVariable String id){
        Student student = studentService.getStudent(id);
        return new ResponseEntity<>( student,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> studentList = studentService.getAllStudents();
        return new ResponseEntity<>( studentList,HttpStatus.OK);
    }

}
