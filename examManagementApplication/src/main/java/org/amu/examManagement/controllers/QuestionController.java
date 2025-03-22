package org.amu.examManagement.controllers;

import jakarta.servlet.http.HttpSession;
import org.amu.examManagement.model.*;
import org.amu.examManagement.model.Question;
import org.amu.examManagement.repositories.QuestionRepository;
import org.amu.examManagement.services.*;
import org.amu.examManagement.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private ExamService examService;

    // ==================================
    //     QUESTION
    // ==================================

    @GetMapping("/questions")
    public String showQuestionsList(Model model,
                                HttpSession session) {
        // Récupérer l'utilisateur connecté
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            // Pas connecté -> redirige vers login
            return "redirect:/login";
        }

        if (loggedUser.getRole().equals("admin") || loggedUser.getRole().equals("teacher")) {
            // On récupère tous les questions de l'application
            List<Question> allQuestions = questionService.getAllQuestions();
            model.addAttribute("allQuestions", allQuestions);
            model.addAttribute("users", loggedUser);

            return "questions";
        } else {
            return "redirect:/error";
        }
    }

    // Afficher le formulaire pour ajouter une nouvelle question
    @GetMapping("/questions/add")
    public String showAddQuestionsForm(Model model, HttpSession session) {

        // On récupère l'utilisateur dont l'identifiant correspond au paramètre
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        model.addAttribute("users", loggedUser);

        Question question = new Question();
        model.addAttribute("question", question);

        List<Exam> allExams = examService.getAllExams();
        model.addAttribute("allExams", allExams);

        return "question-form";
    }

    // Afficher le formulaire d'édition d'une question
    @GetMapping("/questions/edit/{id}")
    public String showEditQuestionsForm(@PathVariable("id") Long id, Model model, HttpSession session) {
        Optional<Question> questionOpt = questionService.getQuestion(id);
        if(questionOpt.isPresent()) {
            Question question = questionOpt.get();
            model.addAttribute("question", question);

            // On récupère l'utilisateur dont l'identifiant correspond au paramètre
            Users loggedUser = (Users) session.getAttribute("loggedUser");
            model.addAttribute("users", loggedUser);

            List<Exam> allExams = examService.getAllExams();
            model.addAttribute("allExams", allExams);

            return "question-form";
        } else {
            return "redirect:/error";
        }
    }

    // Supprimer une question
    @GetMapping("/questions/delete/{id}")
    public String deleteQuestion(@PathVariable("id") Long id, HttpSession session, Model model) {

        Optional<Question> questionOpt = questionService.getQuestion(id);
        if (questionOpt.isPresent()) {
            Question questionToDelete = questionOpt.get();

            // 1. On dissocie la question de l'examen (si présent)
            Exam exam = questionToDelete.getExam();
            if (exam != null) {
                exam.getQuestions().remove(questionToDelete);
                examService.saveExam(exam);
            }
            questionToDelete.setExam(null);

            // 2. On dissocie la question de tous les quiz auxquels elle appartient
            if (questionToDelete.getQuizList() != null) {
                for (Quiz quiz : new ArrayList<>(questionToDelete.getQuizList())) {
                    quiz.getQuestionList().remove(questionToDelete);
                    quizService.saveQuiz(quiz);
                }
            }

            // 3. On supprime la question
            questionService.deleteQuestion(id);

            // Récupérer l'utilisateur connecté
            Users loggedUser = (Users) session.getAttribute("loggedUser");
            model.addAttribute("users", loggedUser);

            return "redirect:/questions";
        } else {
            return "redirect:/error";
        }
    }

    // Enregistrer une nouvelle question
    @PostMapping("/questions")
    public String createQuestion(@ModelAttribute("question") Question question,
                             @RequestParam(name = "courseId", required = false) Long courseId,
                             HttpSession session) {

        // On récupère l'utilisateur connecté
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            // Personne n'est connecté -> rediriger vers la page de login
            return "redirect:/login";
        }

        // On enregistre le question
        questionService.saveQuestion(question);
        return "redirect:/questions";
    }

    // Mettre à jour une question
    @PostMapping("/questions/update/{id}")
    public String updateQuestion(@PathVariable("id") Long id,
                             @ModelAttribute("question") Question question) {

        // On va chercher la question existante pour la mettre à jour
        Optional<Question> questionOpt = questionService.getQuestion(id);
        if(questionOpt.isPresent()) {
            Question questionToUpdate = questionOpt.get();
            questionToUpdate.setCategory(question.getCategory());
            questionToUpdate.setQuestionTitle(question.getQuestionTitle());
            questionToUpdate.setDifficultyLevel(question.getDifficultyLevel());
            questionToUpdate.setOption1(question.getOption1());
            questionToUpdate.setOption2(question.getOption2());
            questionToUpdate.setOption3(question.getOption3());
            questionToUpdate.setOption4(question.getOption4());
            questionToUpdate.setRightAnswer(question.getRightAnswer());
            questionToUpdate.setExam(question.getExam());
            questionService.saveQuestion(questionToUpdate);

            return "redirect:/questions";
        } else {
            return "redirect:/error";
        }
    }

}
