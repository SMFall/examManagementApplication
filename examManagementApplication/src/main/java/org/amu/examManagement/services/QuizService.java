package org.amu.examManagement.services;

import org.amu.examManagement.model.Quiz;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface QuizService {

    public Optional<Quiz> getQuiz(Long id);

    public List<Quiz> getAllQuiz();

    public void saveQuiz(Quiz quiz);

    public void saveAllQuiz(List<Quiz> quizList);

    public void deleteQuiz(Long id);

}
