package uk.ac.leedsbeckett.hmm.student_portal.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Course;
import uk.ac.leedsbeckett.hmm.student_portal.repositories.CourseRepository;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Override
    public Course saveCourse( Course course ) {
        return courseRepository.save( course );
    }

    @Override
    public Course getCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(()-> new RuntimeException("Course not found with ID: " + id));
    }

    @Override
    public List<Course> getCourseByName(String name) {
//        return courseRepository.findByDescriptionContainingIgnoreCase(name).orElseThrow(()-> new RuntimeException("Course not found with name: " + name));
        List<Course> courses = courseRepository.findByDescriptionContainingIgnoreCase(name);
        if (courses.isEmpty()) {
            throw new RuntimeException("No matching courses found.");
        }
        return courses;

    }

    @Override
    public Course updateCourseById( Long courseId, Course updatedCourse ) {
        Course currentCourse = courseRepository.findById(courseId).orElse(null);
        if ( currentCourse == null ) {
            throw new RuntimeException("Course not found with ID: " + courseId);
        }
        currentCourse.setTitle( updatedCourse.getTitle());
        currentCourse.setDescription( updatedCourse.getDescription());
        currentCourse.setFee( updatedCourse.getFee());
        return courseRepository.save( currentCourse );
    }

    @Transactional
    @Override
    public void deleteCourse(Long id) {
        Course currentCourse = courseRepository.findById(id).orElse(null);
        if ( currentCourse == null ) {
            throw new RuntimeException("Course not found with ID: " + id);
        }
        courseRepository.deleteById( id );
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
