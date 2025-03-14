package org.amu.examManagement.services;

import org.amu.examManagement.model.Exam;
import org.amu.examManagement.model.Quiz;
import org.springframework.stereotype.Service;

@Service
public interface QuizService {

    public void saveQuiz(Quiz quiz);

}
