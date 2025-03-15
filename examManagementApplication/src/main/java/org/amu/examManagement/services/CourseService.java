package org.amu.examManagement.services;

import org.amu.examManagement.model.Course;
import java.util.List;
import java.util.Optional;

public interface CourseService {

    void saveCourse(Course course);
    List<Course> getAllCourses();
    Optional<Course> getCourseById(Long id);
    Course updateCourse(Long id, Course course);
    void deleteCourse(Long id);
}
