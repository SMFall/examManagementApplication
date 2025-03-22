package org.amu.examManagement.repositories;

import org.amu.examManagement.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT u.courseList FROM Users u WHERE u.id = ?1")
    List<Course> findAllByStudentId(Long id);

}
