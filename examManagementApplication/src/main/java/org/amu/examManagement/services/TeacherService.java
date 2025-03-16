package org.amu.examManagement.services;

import org.amu.examManagement.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TeacherService {

    public Optional<Teacher> getTeacher(Long id);

    public void saveTeacher(Teacher teacher);

}
