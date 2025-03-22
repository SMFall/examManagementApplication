package org.amu.examManagement.services;

import org.amu.examManagement.model.Exam;
import org.amu.examManagement.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {

    public List<Question> getAllQuestions();

    public void saveQuestion(Question question);
}
