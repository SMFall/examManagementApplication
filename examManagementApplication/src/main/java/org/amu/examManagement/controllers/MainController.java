package org.amu.examManagement.controllers;

import org.amu.examManagement.model.Exam;
import org.amu.examManagement.model.Quiz;
import org.amu.examManagement.model.Users;
import org.amu.examManagement.repositories.QuizRepository;
import org.amu.examManagement.services.ExamService;
import org.amu.examManagement.services.QuizService;
import org.amu.examManagement.services.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuizService quizService;

    // Affiche la page d'accueil
    @GetMapping("/")
    public String showHome(Model model) {
        return "homepage";
    }

    // Affiche la page tableau de bord
    @GetMapping("/users/{id}/dashboard")
    public String showDashboard(@PathVariable Long id, Model model) {

        // On récupère l'enseignant dont l'identifiant correspond au paramètre
        Optional<Users> usersOpt = usersService.getUsers(id);
        List<Quiz> quizList = quizService.getAllQuiz();
        List<Users> usersList = usersService.getAllUsersWithRole("teacher");

        // Si on trouve le users correspondant, on envoie l'objet dans le model
        if (usersOpt.isPresent()) {
            model.addAttribute("users", usersOpt.get());
            model.addAttribute("quizList", quizList);
            model.addAttribute("usersList", usersList);
            return "dashboard";

        } else {
            // Sinon on redirige vers la page 404
            return "redirect:/error";
        }
    }

    // Affiche la page 404
    @GetMapping("/error")
    public String showError() {
        return "404";
    }

}
