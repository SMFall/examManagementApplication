package org.amu.examManagement.services;

import jakarta.transaction.Transactional;
import org.amu.examManagement.model.Question;
import org.amu.examManagement.repositories.QuestionRepository;
import org.amu.examManagement.services.QuestionService;
import org.amu.examManagement.model.Question;
import org.amu.examManagement.model.Question;
import org.amu.examManagement.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    @Transactional
    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }
}
