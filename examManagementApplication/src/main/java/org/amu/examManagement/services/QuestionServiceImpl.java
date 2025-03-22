package org.amu.examManagement.services;

import jakarta.transaction.Transactional;
import org.amu.examManagement.model.Question;
import org.amu.examManagement.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Optional<Question> getQuestion(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    @Transactional
    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    @Override
    @Transactional
    public void saveAllQuestions(List<Question> questions) {
        questionRepository.saveAll(questions);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
