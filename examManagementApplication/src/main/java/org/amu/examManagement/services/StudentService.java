package org.amu.examManagement.services;

import org.amu.examManagement.model.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    void saveStudent(Student student);
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long id);
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);
}
