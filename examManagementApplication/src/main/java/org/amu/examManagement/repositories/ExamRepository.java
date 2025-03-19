package org.amu.examManagement.repositories;

import org.amu.examManagement.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Query(value = "SELECT e.* FROM EXAM e JOIN USERS u ON e.USERS_ID = u.ID WHERE u.ID = ?1 AND u.ROLE = ?2 LIMIT 1", nativeQuery = true)
    Exam findFirstByTeacherIdOrderByExamDateAsc(Long teacherId, String role);

    @Query("SELECT e FROM Exam e WHERE e.users.id = :teacherId")
    List<Exam> findByTeacherId(@Param("teacherId") Long teacherId);
}
