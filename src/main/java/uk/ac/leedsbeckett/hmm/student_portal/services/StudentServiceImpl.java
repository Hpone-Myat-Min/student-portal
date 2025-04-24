package uk.ac.leedsbeckett.hmm.student_portal.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.leedsbeckett.hmm.student_portal.entities.*;
import uk.ac.leedsbeckett.hmm.student_portal.repositories.CourseRepository;
import uk.ac.leedsbeckett.hmm.student_portal.repositories.StudentRepository;
import uk.ac.leedsbeckett.hmm.student_portal.repositories.UserRepository;

import java.time.LocalDate;
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
    private final IntegrationService integrationService;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, UserRepository userRepository, IntegrationService integrationService) {
        this.studentRepository = studentRepository; // dependency
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.integrationService = integrationService;
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
    public Invoice enrollStudentInCourse(Long userId, Long courseId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found with ID: " + userId));
        if (Objects.equals(user.getRole(), "normal")){
            Student newStudent = new Student();         // create student account for the user
            String newStudentId = createStudentId();    // create student id e.g. c0000001
            newStudent.setStudentId(newStudentId);

            user.setRole("student");                    // update user role to "normal" to "student"
            user.setStudent(newStudent);                // mapped student to the user
            newStudent.setUser(user);                   // mapped user to the new student account
            userRepository.save(user);

            FinanceAccount financeAccount = new FinanceAccount();
            financeAccount.setStudentId(newStudentId);  // create Finance account for the student

            FinanceAccount createdAccount = integrationService.createFinanceAccount(financeAccount);
            if (createdAccount == null || createdAccount.getId() == null) {
                throw new RuntimeException("Finance account creation failed for studentId: " + newStudentId);
            }

            LibraryAccount libraryAccount = integrationService.createLibraryAccount(newStudentId);
            System.out.print("STUDENT LIBRARY CREATED: " + libraryAccount);
        }

//        else if (Objects.equals(user.getRole(), "admin")) {
//            user.setRole("admin");
//        }
        Course newCourse = courseRepository.findById(courseId).orElseThrow(()-> new RuntimeException("Course not found with ID: " + courseId));

        Student student = user.getStudent();

//        if (student.getCourses().contains(newCourse)){    // informing if the student already enrolled in the course
//            System.out.println("Student already enrolled in this course");
//            return null;
//        }

        student.getCourses().add(newCourse);
        studentRepository.save(student);

        FinanceAccount financeAccount = integrationService.getFinanceAccount(student.getStudentId());

        Invoice newInvoice = createInvoice(newCourse, financeAccount, Invoice.Type.TUITION_FEES);

//        Invoice newInvoice = Invoice.createInvoice(student, newCourse, financeAccount);
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            System.out.println(mapper.writeValueAsString(newInvoice));
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        newInvoice = integrationService.createCourseFeeInvoice(newInvoice);

        return newInvoice;
    }

    public Invoice createInvoice(Course newCourse, FinanceAccount financeAccount, Invoice.Type invoiceType) {
        Invoice newInvoice = new Invoice();

//        newInvoice.setReference("");
        newInvoice.setAmount(newCourse.getFee());           // creating new invoice for course enroll
        newInvoice.setType(invoiceType);
//        newInvoice.setStatus(Invoice.Status.OUTSTANDING);
//        newInvoice.setStudentId("");
        newInvoice.setDueDate(LocalDate.now().plusDays(14));
        newInvoice.setAccount(financeAccount);

        return newInvoice;
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

//    public void payInvoice(String reference){
//        integrationService.payInvoice(reference);
//    }

    public Boolean viewGradEligibility(String studentId){

        FinanceAccount financeAccount = integrationService.getFinanceAccount(studentId);
        if (financeAccount == null) {
            throw new RuntimeException("Finance account not found with ID: " + studentId);
        } else if (financeAccount.isHasOutstandingBalance()) {
            return false;
        } else{
            return true;
        }

//        List<Invoice> invoicesList = integrationService.getAllInvoices();
//        System.out.print(invoicesList);
//        boolean hasLibrary = false;
//        boolean hasTuition = false;
//
//        for (Invoice invoice : invoicesList) {
//            if (!studentId.equals(invoice.getStudentId())) continue;
//
//            if (invoice.getStatus() == Invoice.Status.OUTSTANDING) {
//                if (invoice.getType() == Invoice.Type.LIBRARY_FINE) hasLibrary = true;
//                if (invoice.getType() == Invoice.Type.TUITION_FEES) hasTuition = true;
//
//                if (hasLibrary && hasTuition) break; // ✅ optimization: exit early
//            }
//        }
//
//        if (hasLibrary && hasTuition){
//            return "both";
//        }
//
//        else if (hasLibrary){
//            return "library";
//        }
//
//        else if (hasTuition){
//            return "tuition";
//        }
//        else{
//            return "none";
//        }

    }

    public Invoice getInvoice(String reference){
        return integrationService.getInvoice(reference);
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

    @Override
    public Boolean isStudentEnrolledInCourse(String studentId, Long courseId) {

        Student student = studentRepository.findByStudentId(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);
        if (student.getCourses().contains(course)) {
            return true;
        }
        return false;

//        User user = userRepository.findById(studentId).orElseThrow(()-> new RuntimeException("User not found with ID: " + studentId));
//        if (Objects.equals(user.getRole(), "student")){
//            Student student = user.getStudent();
//            if(student.getCourses().contains(courseId)){
//                return true;
//            }
//            else{
//                return false;
//            }
//        }
//        else{
//            return false;
//        }

    }


}
