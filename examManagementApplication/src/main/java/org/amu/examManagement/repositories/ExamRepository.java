package org.amu.examManagement.repositories;

import org.amu.examManagement.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Query(value = "SELECT * FROM EXAM JOIN USERS ON EXAM.USERS_ID = USERS.ID WHERE USERS.ID = ?1 AND USERS.ROLE = ?2 LIMIT 1", nativeQuery = true)
    Exam findFirstByTeacherIdOrderByExamDateAsc(Long teacherId, String role);

}
