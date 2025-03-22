package org.amu.examManagement.services;

import org.amu.examManagement.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface QuestionService {

    public Optional<Question> getQuestion(Long id);

    public List<Question> getAllQuestions();

    public void saveQuestion(Question question);

    public void saveAllQuestions(List<Question> questions);

    public void deleteQuestion(Long id);
}
