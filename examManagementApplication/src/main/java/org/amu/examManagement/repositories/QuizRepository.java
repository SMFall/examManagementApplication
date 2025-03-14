package org.amu.examManagement.repositories;

import org.amu.examManagement.model.Exam;
import org.amu.examManagement.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface QuizRepository extends JpaRepository<Quiz, Long> {

}
