package uk.ac.leedsbeckett.hmm.student_portal.services;

import uk.ac.leedsbeckett.hmm.student_portal.entities.Course;

import java.util.List;

public interface CourseService {

    Course saveCourse( Course course );
    Course getCourse( Long id );
    List<Course> getCourseByName( String name );
    Course updateCourseById( Long courseId, Course updatedCourse );
    void deleteCourse( Long id );
    List<Course> getAllCourses();

}
