package org.amu.examManagement.services;

import jakarta.transaction.Transactional;
import org.amu.examManagement.model.Quiz;
import org.amu.examManagement.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    @Transactional
    public void saveQuiz(Quiz quiz) {
        quizRepository.save(quiz);
    }
}
