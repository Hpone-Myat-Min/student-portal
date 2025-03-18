package uk.ac.leedsbeckett.hmm.student_portal.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Student;
import uk.ac.leedsbeckett.hmm.student_portal.repositories.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

//    List<Student> students = new ArrayList<Student>();
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository; // dependency
    }

    @Override
    public Student saveStudent( Student student ) {        // register new student
        return studentRepository.save(student);
    }

    @Override
    public Student getStudent( String id ) {  // find student by Id
        return studentRepository.findByStudentId(id).orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    @Transactional
    @Override
    public void deleteStudent( String id ) {
        Student currentStudent = studentRepository.findByStudentId(id).orElse(null);
        if(currentStudent == null) {
            throw new RuntimeException("Student not found with ID: " + id);
        }
        studentRepository.deleteByStudentId(id);
    }

    @Override
    public Student updateStudent(String id, Student updatedStudent ) {
        Student currentStudent = studentRepository.findByStudentId(id).orElse(null);
        if(currentStudent == null){
            throw new RuntimeException("Student not found with ID: " + id);
        }
        currentStudent.setFirstName(updatedStudent.getFirstName());
        currentStudent.setLastName(updatedStudent.getLastName());
        currentStudent.setEmail(updatedStudent.getEmail());
        currentStudent.setQualification(updatedStudent.getQualification());
        currentStudent.setUniversity(updatedStudent.getUniversity());

        return studentRepository.save(currentStudent);
    }

    public List<Student> getAllStudents() { // retrieve a list of all students
        return studentRepository.findAll();
    }


}
