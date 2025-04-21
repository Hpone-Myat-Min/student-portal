package uk.ac.leedsbeckett.hmm.student_portal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Course;
import uk.ac.leedsbeckett.hmm.student_portal.services.CourseService;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    public final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/addCourse")
    public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        Course course = courseService.getCourse(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<Course> getCourseByName(@PathVariable String title) {
        Course course = courseService.getCourseByName(title);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourseById(@PathVariable Long id, @RequestBody Course updatedCourse) {
        Course course = courseService.updateCourseById(id, updatedCourse);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> coursesList = courseService.getAllCourses();
        return new ResponseEntity<>(coursesList, HttpStatus.OK);
    }

//    @PutMapping("/{userId}/enroll/{courseId}")
//    public ResponseEntity<Course> enrollStudentInCourse(@PathVariable Long userId, @PathVariable Long courseId) {
//        Student student = studentService.enrollStudentInCourse(studentId, courseId);
//        return new ResponseEntity<>(student, HttpStatus.OK);
//    }
}