package org.amu.examManagement.services;

import org.amu.examManagement.model.Exam;
import org.springframework.stereotype.Service;

@Service
public interface ExamService {

    public void saveExam(Exam exam);

}
