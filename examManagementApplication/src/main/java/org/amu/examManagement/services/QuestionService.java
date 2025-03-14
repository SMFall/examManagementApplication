package org.amu.examManagement.services;

import org.amu.examManagement.model.Exam;
import org.amu.examManagement.model.Question;
import org.springframework.stereotype.Service;

@Service
public interface QuestionService {

    public void saveQuestion(Question question);

}
