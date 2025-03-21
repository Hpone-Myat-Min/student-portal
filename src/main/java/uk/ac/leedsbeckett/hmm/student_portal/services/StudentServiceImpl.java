package uk.ac.leedsbeckett.hmm.student_portal.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Course;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Student;
import uk.ac.leedsbeckett.hmm.student_portal.entities.User;
import uk.ac.leedsbeckett.hmm.student_portal.repositories.CourseRepository;
import uk.ac.leedsbeckett.hmm.student_portal.repositories.StudentRepository;
import uk.ac.leedsbeckett.hmm.student_portal.repositories.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    private static final String PREFIX = "c";

//    List<Student> students = new ArrayList<Student>();
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository; // dependency
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Student saveStudent( Student student ) {        // register new student
        return studentRepository.save(student);
    }

    @Override
    public Student getStudent( String id ) {// find student by Id
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

        String firstName = updatedStudent.getFirstName();
        String lastName = updatedStudent.getLastName();
        String qualification = updatedStudent.getQualification();
        String university = updatedStudent.getUniversity();

        if (firstName!=null){
            currentStudent.setFirstName(firstName);
        }
        if (lastName!=null){
            currentStudent.setLastName(lastName);
        }
        if (qualification!=null){
            currentStudent.setQualification(qualification);
        }
        if (university!=null){
            currentStudent.setUniversity(university);
        }

        return studentRepository.save(currentStudent);
    }

    @Override
    public List<Student> getAllStudents() { // retrieve a list of all students
        return studentRepository.findAll();
    }

    @Override
    public Student enrollStudentInCourse(Long userId, Long courseId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found with ID: " + userId));
        if (Objects.equals(user.getRole(), "normal")){
            Student newStudent = new Student();
            newStudent.setStudentId(createStudentId());

            user.setRole("student");
            user.setStudent(newStudent);
            newStudent.setUser(user);
            userRepository.save(user);
        } else if (Objects.equals(user.getRole(), "admin")) {
            user.setRole("admin");
        }
        Course newCourse = courseRepository.findById(courseId).orElseThrow(()-> new RuntimeException("Course not found with ID: " + courseId));

        Student student = user.getStudent();
        student.getCourses().add(newCourse);
        studentRepository.save(student);

        return student;
    }

    public String createStudentId(){
        Optional<Student> lastStudentOpt = studentRepository.findTopByOrderByIdDesc();
        int nextNumber = 1;
        if (lastStudentOpt.isPresent()) {
            String lastStudentNumber = lastStudentOpt.get().getStudentId();
            String numericPart = lastStudentNumber.substring(PREFIX.length());
            nextNumber = Integer.parseInt(numericPart) + 1;
        }

        String newStudentNumber = PREFIX + String.format("%07d", nextNumber);
        return newStudentNumber;
    }

//
//    @Override
//    public void updateStudentRole(Long studentId, String role) {
//        Student currentStudent = studentRepository.findById(studentId).orElse(null);
//
//        if(currentStudent == null) {
//            throw new RuntimeException("Student not found with ID: " + studentId);
//        }
//        currentStudent.setRole(role);
//
//        studentRepository.save(currentStudent);
//    }


}
