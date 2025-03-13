package uk.ac.leedsbeckett.hmm.student_portal.services;

import org.springframework.stereotype.Service;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Student;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    List<Student> students = new ArrayList<Student>();

    @Override
    public Student saveStudent( Student student ) {        // register new student
        students.add( student );
        return student;
    }

    @Override
    public Student getStudent( long id ) {  // find student by Id
        return students.stream().filter(student -> student.getStudentId().equals(id)).findFirst().get();
    }

    public List<Student> getAllStudents() { // retrieve a list of all students
        return students;
    }


}
