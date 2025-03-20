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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

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
    //     COMPTES DES UTILISATEURS
    // ==================================

    @GetMapping("/users")
    public String showUsersList(Model model,
                                HttpSession session) {
        // Récupérer l'utilisateur connecté
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            // Pas connecté -> redirige vers login
            return "redirect:/login";
        }

        if ("admin".equals(loggedUser.getRole())) {
            // On récupère tous les utilisateurs de l'application
            List<Users> allUsers = usersService.getAllUsers();
            model.addAttribute("allUsers", allUsers);
            model.addAttribute("users", loggedUser);

            return "users";
        } else {
            return "redirect:/error";
        }
    }

    // Afficher le formulaire pour ajouter un nouvel utilisateur
    @GetMapping("/users/add")
    public String showAddUsersForm(Model model, HttpSession session) {

        // On récupère l'utilisateur dont l'identifiant correspond au paramètre
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        model.addAttribute("users", loggedUser);

        Users editedUser = new Users();
        model.addAttribute("editedUser", editedUser);

        return "users-form";
    }

    // Afficher le formulaire d'édition d'un utilisateur
    @GetMapping("/users/edit/{id}")
    public String showEditUsersForm(@PathVariable("id") Long id, Model model, HttpSession session) {
        Optional<Users> usersOpt = usersService.getUsers(id);
        if(usersOpt.isPresent()) {
            Users users = usersOpt.get();
            model.addAttribute("editedUser", users);

            // On récupère l'utilisateur dont l'identifiant correspond au paramètre
            Users loggedUser = (Users) session.getAttribute("loggedUser");
            model.addAttribute("users", loggedUser);

            return "users-form";
        } else {
            return "redirect:/error";
        }
    }

    // Supprimer un examen
    @GetMapping("/users/delete/{id}")
    public String deleteUsers(@PathVariable("id") Long id, HttpSession session, Model model) {

        usersService.deleteUsers(id);

        // On récupère l'utilisateur dont l'identifiant correspond au paramètre
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        model.addAttribute("users", loggedUser);

        return "redirect:/users";
    }

    // Enregistrer un nouvel examen
    @PostMapping("/users")
    public String createUsers(@ModelAttribute("editedUsers") Users editedUsers,
                             @RequestParam(name = "courseId", required = false) Long courseId,
                             HttpSession session) {

        // On récupère l'utilisateur connecté
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            // Personne n'est connecté -> rediriger vers la page de login
            return "redirect:/login";
        }

        // On enregistre l'utilisateur
        usersService.saveUsers(editedUsers);
        return "redirect:/users";
    }

    // Mettre à jour un utilisateur
    @PostMapping("/users/update/{id}")
    public String updateUsers(@PathVariable("id") Long id,
                             @ModelAttribute("editedUsers") Users editedUsers) {

        // On va chercher l'examen existant pour le mettre à jour
        Optional<Users> usersOpt = usersService.getUsers(id);
        if(usersOpt.isPresent()) {
            Users usersToUpdate = usersOpt.get();
            usersToUpdate.setFirstName(editedUsers.getFirstName());
            usersToUpdate.setLastName(editedUsers.getLastName());
            usersToUpdate.setEmail(editedUsers.getEmail());
            usersToUpdate.setUsername(editedUsers.getUsername());
            usersToUpdate.setPassword(editedUsers.getPassword());
            usersToUpdate.setRole(editedUsers.getRole());

            usersService.saveUsers(usersToUpdate);

            return "redirect:/users";
        } else {
            return "redirect:/error";
        }
    }

}
