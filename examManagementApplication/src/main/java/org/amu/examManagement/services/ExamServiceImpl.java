package org.amu.examManagement.services;

import jakarta.transaction.Transactional;
import org.amu.examManagement.model.Exam;
import org.amu.examManagement.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    @Transactional
    public void saveExam(Exam exam) {
        examRepository.save(exam);
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public Optional<Exam> getExamById(Long id) {
        return examRepository.findById(id);
    }

    @Override
    @Transactional
    public Exam updateExam(Long id, Exam examDetails) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found for id : " + id));
        exam.setCourse(examDetails.getCourse());
        exam.setExamTitle(examDetails.getExamTitle());
        exam.setExamDate(examDetails.getExamDate());
        exam.setTeacher(examDetails.getTeacher());
        return examRepository.save(exam);
    }

    @Override
    @Transactional
    public void deleteExam(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found for id : " + id));
        examRepository.delete(exam);
    }

    @Override
    public Exam getFirstExamByTeacherId(Long teacherId) {
        return examRepository.findFirstByTeacher_IdOrderByExamDateAsc(teacherId);
    }
}
