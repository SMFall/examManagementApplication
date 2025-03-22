package org.amu.examManagement.controllers;

import jakarta.servlet.http.HttpSession;
import org.amu.examManagement.model.Course;
import org.amu.examManagement.model.Exam;
import org.amu.examManagement.model.Quiz;
import org.amu.examManagement.model.Users;
import org.amu.examManagement.repositories.QuizRepository;
import org.amu.examManagement.services.CourseService;
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
    @Autowired
    private ExamService examService;
    @Autowired
    private CourseService courseService;

    // ==================================
    //     VITRINE DE L'APPLICATION
    // ==================================

    // Affiche la page d'accueil
    @GetMapping("/")
    public String showHome(Model model) {
        return "homepage";
    }

    // Affiche la page d'accueil
    @GetMapping("/features")
    public String showFeatures(Model model) {
        return "features";
    }

    // Affiche la page d'accueil
    @GetMapping("/contact")
    public String showContact(Model model) {
        return "contact";
    }

    // Affiche la page tableau de bord
    @GetMapping("/users/{id}/dashboard")
    public String showDashboard(@PathVariable Long id,
                                HttpSession session,
                                Model model) {
        // Vérifier si un user est en session
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            // Personne n'est connecté, on redirige vers login
            return "redirect:/login";
        }

        // Vérifier si c'est bien le user qui a le droit de voir ce dashboard
        if (!loggedUser.getUser_id().equals(id)) {
            return "redirect:/error";
        }

        // On récupère l'enseignant dont l'identifiant correspond au paramètre
        Optional<Users> usersOpt = usersService.getUsers(id);
        List<Quiz> quizList = quizService.getAllQuiz();
        List<Users> usersList = usersService.getAllUsersWithRole("teacher");

        int nbCourses = courseService.getAllCourses().size();
        int nbQuiz = quizList.size();
        int nbStudents = usersService.getAllUsersWithRole("student").size();

        model.addAttribute("nbCourses", nbCourses);
        model.addAttribute("nbQuiz", nbQuiz);
        model.addAttribute("nbStudents", nbStudents);

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

    // ==================================
    //         ERREUR 404
    // ==================================

    // Affiche la page 404
    @GetMapping("/error")
    public String showError() {
        return "404";
    }

}
