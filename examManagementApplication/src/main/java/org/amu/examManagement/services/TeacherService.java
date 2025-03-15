package org.amu.examManagement.services;

import org.amu.examManagement.model.Teacher;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {

    public void saveTeacher(Teacher teacher);

}
