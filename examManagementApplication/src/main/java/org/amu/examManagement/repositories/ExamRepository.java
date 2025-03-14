package org.amu.examManagement.repositories;

import org.amu.examManagement.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ExamRepository extends JpaRepository<Exam, Long> {

}
