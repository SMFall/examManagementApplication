package org.amu.examManagement.services;

import jakarta.transaction.Transactional;
import org.amu.examManagement.model.Quiz;
import org.amu.examManagement.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Optional<Quiz> getQuiz(Long id) {
        return quizRepository.findById(id);
    }

    @Override
    public List<Quiz> getAllQuiz() {
        return quizRepository.findAll();
    }

    @Override
    @Transactional
    public void saveQuiz(Quiz quiz) {
        quizRepository.save(quiz);
    }

    @Override
    @Transactional
    public void saveAllQuiz(List<Quiz> quizList) {
        quizRepository.saveAll(quizList);
    }

    @Override
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }
}
