package org.amu.examManagement.controllers;

import org.amu.examManagement.model.Exam;
import org.amu.examManagement.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    // Création d'un examen
    @PostMapping
    public Exam createExam(@RequestBody Exam exam) {
        examService.saveExam(exam);
        return exam;
    }

    // Récupérer tous les examens
    @GetMapping
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }

    // Récupérer un examen par id
    @GetMapping("/{id}")
    public Exam getExamById(@PathVariable Long id) {
        return examService.getExamById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found for id : " + id));
    }

    // Mise à jour d'un examen
    @PutMapping("/{id}")
    public Exam updateExam(@PathVariable Long id, @RequestBody Exam exam) {
        return examService.updateExam(id, exam);
    }

    // Suppression d'un examen
    @DeleteMapping("/{id}")
    public String deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return "Exam deleted with id : " + id;
    }

    @GetMapping("/firstExam/{teacherId}")
    public Exam getFirstExamByTeacherId(@PathVariable Long teacherId) {
        return examService.getFirstExamByTeacherId(teacherId);
    }
}
