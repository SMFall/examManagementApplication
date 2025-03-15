package org.amu.examManagement.repositories;

import org.amu.examManagement.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
