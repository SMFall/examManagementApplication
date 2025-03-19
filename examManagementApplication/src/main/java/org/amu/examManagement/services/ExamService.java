package org.amu.examManagement.services;

import org.amu.examManagement.model.Exam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ExamService {

    public void saveExam(Exam exam);
    List<Exam> getAllExams();
    Optional<Exam> getExamById(Long id);
    public Exam updateExam(Long id, Exam exam);
    public void deleteExam(Long id);
    public Exam getFirstTeacherExamByUsersId(Long teacherId);
    public List<Exam> getExamsByTeacherId(Long teacherId);
    public List<Exam> getAllExamsForStudent(Long studentId);
}
