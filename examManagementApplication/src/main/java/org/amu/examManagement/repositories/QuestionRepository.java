package org.amu.examManagement.repositories;

import org.amu.examManagement.model.Question;
import org.amu.examManagement.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface QuestionRepository extends JpaRepository<Question, Long> {
}
