package org.amu.examManagement.controllers;

import jakarta.servlet.http.HttpSession;
import org.amu.examManagement.model.Course;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class CourseController {

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
    //         COURS
    // ==================================

    @GetMapping("/courses")
    public String showCoursesList(Model model, HttpSession session) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);

        // On récupère l'enseignant dont l'identifiant correspond au paramètre
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        model.addAttribute("users", loggedUser);

        return "courses";
    }

    // Afficher le formulaire pour ajouter un nouveau cours
    @GetMapping("/courses/add")
    public String showAddCourseForm(Model model, HttpSession session) {
        model.addAttribute("course", new Course());

        // On récupère l'enseignant dont l'identifiant correspond au paramètre
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        model.addAttribute("users", loggedUser);

        List<Users> allUsers = usersService.getAllUsersWithRole("student");
        model.addAttribute("allUsers", allUsers);

        return "course-form";
    }

    // Afficher le formulaire d'édition d'un cours
    @GetMapping("/courses/edit/{id}")
    public String showEditCourseForm(@PathVariable("id") Long id, Model model, HttpSession session) {
        Optional<Course> courseOpt = courseService.getCourseById(id);
        if(courseOpt.isPresent()) {
            model.addAttribute("course", courseOpt.get());

            // On récupère l'enseignant dont l'identifiant correspond au paramètre
            Users loggedUser = (Users) session.getAttribute("loggedUser");
            model.addAttribute("users", loggedUser);

            List<Users> allUsers = usersService.getAllUsersWithRole("student");
            model.addAttribute("allUsers", allUsers);

            return "course-form";
        } else {
            return "redirect:/error";
        }
    }

    // Supprimer un cours
    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id, HttpSession session, Model model) {
        courseService.deleteCourse(id);

        // On récupère l'enseignant dont l'identifiant correspond au paramètre
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        model.addAttribute("users", loggedUser);

        return "redirect:/courses";
    }

    // Enregistrer un nouveau cours
    @PostMapping("/courses")
    public String createCourse(@ModelAttribute("course") Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    // Mettre à jour un cours
    @PostMapping("/courses/update/{id}")
    public String updateCourse(@PathVariable("id") Long id,
                               @ModelAttribute("course") Course courseForm) {
        courseService.updateCourse(id, courseForm);
        return "redirect:/courses";
    }

}
