package org.amu.examManagement.services;

import org.amu.examManagement.model.Exam;
import org.amu.examManagement.model.Quiz;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizService {

    public void saveAllQuiz(List<Quiz> quizList);

    public List<Quiz> getAllQuiz();

}
