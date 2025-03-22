package org.amu.examManagement.controllers;

import jakarta.servlet.http.HttpSession;
import org.amu.examManagement.model.Question;
import org.amu.examManagement.model.Quiz;
import org.amu.examManagement.model.Users;
import org.amu.examManagement.repositories.QuizRepository;
import org.amu.examManagement.services.*;
import org.amu.examManagement.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class QuizController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private ExamService examService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private QuestionService questionService;

    // ==================================
    //     QUIZ
    // ==================================

    @GetMapping("/quiz")
    public String showQuizList(Model model,
                                HttpSession session) {
        // Récupérer l'utilisateur connecté
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            // Pas connecté -> redirige vers login
            return "redirect:/login";
        }

        if (loggedUser.getRole().equals("admin") || loggedUser.getRole().equals("teacher")) {
            // On récupère tous les quiz de l'application
            List<Quiz> allQuiz = quizService.getAllQuiz();
            model.addAttribute("allQuiz", allQuiz);
            model.addAttribute("users", loggedUser);

            return "quiz";
        } else {
            return "redirect:/error";
        }
    }

    // Afficher le formulaire pour ajouter un nouveau quiz
    @GetMapping("/quiz/add")
    public String showAddQuizForm(Model model, HttpSession session) {

        // On récupère l'utilisateur dont l'identifiant correspond au paramètre
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        model.addAttribute("users", loggedUser);

        Quiz quiz = new Quiz();
        model.addAttribute("quiz", quiz);

        List<Question> allQuestions = questionService.getAllQuestions();
        model.addAttribute("allQuestions", allQuestions);

        return "quiz-form";
    }

    // Afficher le formulaire d'édition d'un quiz
    @GetMapping("/quiz/edit/{id}")
    public String showEditQuizForm(@PathVariable("id") Long id, Model model, HttpSession session) {
        Optional<Quiz> quizOpt = quizService.getQuiz(id);
        if(quizOpt.isPresent()) {
            Quiz quiz = quizOpt.get();
            model.addAttribute("quiz", quiz);

            // On récupère l'utilisateur dont l'identifiant correspond au paramètre
            Users loggedUser = (Users) session.getAttribute("loggedUser");
            model.addAttribute("users", loggedUser);

            List<Question> allQuestions = questionService.getAllQuestions();
            model.addAttribute("allQuestions", allQuestions);

            return "quiz-form";
        } else {
            return "redirect:/error";
        }
    }

    // Supprimer un quiz
    @GetMapping("/quiz/delete/{id}")
    public String deleteQuiz(@PathVariable("id") Long id, HttpSession session, Model model) {

        quizService.deleteQuiz(id);

        // On récupère l'utilisateur dont l'identifiant correspond au paramètre
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        model.addAttribute("users", loggedUser);

        return "redirect:/quiz";
    }

    // Enregistrer un nouveau quiz
    @PostMapping("/quiz")
    public String createQuiz(@ModelAttribute("quiz") Quiz quiz,
                             @RequestParam(name = "courseId", required = false) Long courseId,
                             HttpSession session) {

        // On récupère l'utilisateur connecté
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            // Personne n'est connecté -> rediriger vers la page de login
            return "redirect:/login";
        }

        // On enregistre le quiz
        quizService.saveQuiz(quiz);
        return "redirect:/quiz";
    }

    // Mettre à jour un quiz
    @PostMapping("/quiz/update/{id}")
    public String updateQuiz(@PathVariable("id") Long id,
                             @ModelAttribute("quiz") Quiz quiz) {

        // On va chercher le quiz existant pour le mettre à jour
        Optional<Quiz> quizOpt = quizService.getQuiz(id);
        if(quizOpt.isPresent()) {
            Quiz quizToUpdate = quizOpt.get();
            quizToUpdate.setTitle(quiz.getTitle());
            quizToUpdate.setQuestionList(quiz.getQuestionList());
            quizService.saveQuiz(quizToUpdate);

            return "redirect:/quiz";
        } else {
            return "redirect:/error";
        }
    }

}
