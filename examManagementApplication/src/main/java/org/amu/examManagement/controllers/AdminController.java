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
            // On récupère les utilisateurs
            List<Exam> exams = examService.getAllExams();
            model.addAttribute("exams", exams);

            // On récupère l'enseignant dont l'identifiant correspond au paramètre
            model.addAttribute("users", loggedUser);

            return "exams";
        } else {
            return "redirect:/error";
        }
    }

    // Afficher le formulaire pour ajouter un nouvel utilisateur
    @GetMapping("/users/add")
    public String showAddExamForm(Model model, HttpSession session) {

        model.addAttribute("exam", new Exam());

        List<Course> courses = courseService.getAllCourses();
        List<Users> teachers = usersService.getAllUsersWithRole("teacher");
        model.addAttribute("courses", courses);
        model.addAttribute("teachers", teachers);

        // On récupère l'enseignant dont l'identifiant correspond au paramètre
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        model.addAttribute("users", loggedUser);

        return "exam-form";
    }

    // Afficher le formulaire d'édition d'un utilisateurs
    @GetMapping("/users/edit/{id}")
    public String showEditExamForm(@PathVariable("id") Long id, Model model, HttpSession session) {
        Optional<Exam> examOpt = examService.getExamById(id);
        if(examOpt.isPresent()) {
            Exam exam = examOpt.get();
            model.addAttribute("exam", exam);

            List<Course> courses = courseService.getAllCourses();
            List<Users> teachers = usersService.getAllUsersWithRole("teacher");
            model.addAttribute("courses", courses);
            model.addAttribute("teachers", teachers);

            // On récupère l'enseignant dont l'identifiant correspond au paramètre
            Users loggedUser = (Users) session.getAttribute("loggedUser");
            model.addAttribute("users", loggedUser);

            return "exam-form";
        } else {
            return "redirect:/error";
        }
    }

    // Supprimer un examen
    @GetMapping("/users/delete/{id}")
    public String deleteExam(@PathVariable("id") Long id, HttpSession session, Model model) {
        examService.deleteExam(id);

        // On récupère l'enseignant dont l'identifiant correspond au paramètre
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        model.addAttribute("users", loggedUser);

        return "redirect:/exams";
    }

    // Enregistrer un nouvel examen
    @PostMapping("/users")
    public String createExam(@ModelAttribute("exam") Exam exam,
                             @RequestParam(name = "courseId", required = false) Long courseId,
                             HttpSession session) {

        // On récupère l'utilisateur connecté
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            // Personne n'est connecté -> rediriger vers la page de login
            return "redirect:/login";
        }

        // On assigne cet enseignant à l'examen
        exam.setUsers(loggedUser);

        // On vérifie si un cours a été sélectionné
        if (courseId != null) {
            Optional<Course> courseOpt = courseService.getCourseById(courseId);
            courseOpt.ifPresent(exam::setCourse);
        }

        // On enregistre l'examen
        examService.saveExam(exam);
        return "redirect:/users";
    }

    // Mettre à jour un examen
    @PostMapping("/users/update/{id}")
    public String updateExam(@PathVariable("id") Long id,
                             @ModelAttribute("exam") Exam examForm,
                             @RequestParam(name = "courseId", required = false) Long courseId,
                             @RequestParam(name = "teacherId", required = false) Long teacherId) {
        // On va chercher l'examen existant pour le mettre à jour
        Optional<Exam> examOpt = examService.getExamById(id);
        if(examOpt.isPresent()) {
            Exam examToUpdate = examOpt.get();
            examToUpdate.setExamTitle(examForm.getExamTitle());
            examToUpdate.setExamDate(examForm.getExamDate());

            // On set le course
            if(courseId != null) {
                Optional<Course> courseOpt = courseService.getCourseById(courseId);
                courseOpt.ifPresent(examToUpdate::setCourse);
            }

            // On set l'enseignant
            if(teacherId != null) {
                Optional<Users> teacherOpt = usersService.getUsers(teacherId);
                teacherOpt.ifPresent(examToUpdate::setUsers);
            }

            examService.saveExam(examToUpdate);

            return "redirect:/users";
        } else {
            return "redirect:/error";
        }
    }

}
