package org.amu.examManagement.services;

import jakarta.transaction.Transactional;
import org.amu.examManagement.model.Exam;
import org.amu.examManagement.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    @Transactional
    public void saveExam(Exam exam) {
        examRepository.save(exam);
    }
}
