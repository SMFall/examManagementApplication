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
    //         EXAMEN (EXAM) CRUD
    // ==================================

    @GetMapping("/exams")
    public String showExamsList(Model model,
                                HttpSession session) {
        // Récupérer l'utilisateur connecté
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            // Pas connecté -> redirige vers login
            return "redirect:/login";
        }

        // S'il s'agit d'un professeur, on affiche uniquement SES examens.
        if ("teacher".equals(loggedUser.getRole())) {
            // On récupère les exams
            List<Exam> exams = examService.getExamsByTeacherId(loggedUser.getUser_id());
            model.addAttribute("exams", exams);
            return "exams";
        } else {
            return "redirect:/error";
        }
    }

    // Afficher le formulaire pour ajouter un nouvel examen
    @GetMapping("/exams/add")
    public String showAddExamForm(Model model) {

        model.addAttribute("exam", new Exam());

        List<Course> courses = courseService.getAllCourses();
        List<Users> teachers = usersService.getAllUsersWithRole("teacher");
        model.addAttribute("courses", courses);
        model.addAttribute("teachers", teachers);

        return "exam-form";
    }

    // Enregistrer un nouvel examen
    @PostMapping("/exams")
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
        return "redirect:/exams";
    }

    // Afficher le formulaire d'édition d'un examen
    @GetMapping("/exams/edit/{id}")
    public String showEditExamForm(@PathVariable("id") Long id, Model model) {
        Optional<Exam> examOpt = examService.getExamById(id);
        if(examOpt.isPresent()) {
            Exam exam = examOpt.get();
            model.addAttribute("exam", exam);

            List<Course> courses = courseService.getAllCourses();
            List<Users> teachers = usersService.getAllUsersWithRole("teacher");
            model.addAttribute("courses", courses);
            model.addAttribute("teachers", teachers);

            return "exam-form";
        } else {
            return "redirect:/error";
        }
    }

    // Mettre à jour un examen
    @PostMapping("/exams/update/{id}")
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

            return "redirect:/exams";
        } else {
            return "redirect:/error";
        }
    }

    // Supprimer un examen
    @GetMapping("/exams/delete/{id}")
    public String deleteExam(@PathVariable("id") Long id) {
        examService.deleteExam(id);
        return "redirect:/exams";
    }

    // ==================================
    //         COURS (COURSE) CRUD
    // ==================================

    @GetMapping("/courses")
    public String showCoursesList(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    // Afficher le formulaire pour ajouter un nouveau cours
    @GetMapping("/courses/add")
    public String showAddCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "course-form";
    }

    // Enregistrer un nouveau cours
    @PostMapping("/courses")
    public String createCourse(@ModelAttribute("course") Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    // Afficher le formulaire d'édition d'un cours
    @GetMapping("/courses/edit/{id}")
    public String showEditCourseForm(@PathVariable("id") Long id, Model model) {
        Optional<Course> courseOpt = courseService.getCourseById(id);
        if(courseOpt.isPresent()) {
            model.addAttribute("course", courseOpt.get());
            return "course-form";
        } else {
            return "redirect:/error";
        }
    }

    // Mettre à jour un cours
    @PostMapping("/courses/update/{id}")
    public String updateCourse(@PathVariable("id") Long id,
                               @ModelAttribute("course") Course courseForm) {
        courseService.updateCourse(id, courseForm);
        return "redirect:/courses";
    }

    // Supprimer un cours
    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }

    // Affiche la page d'accueil
    @GetMapping("/")
    public String showHome(Model model) {
        return "homepage";
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

    // Affiche la page 404
    @GetMapping("/error")
    public String showError() {
        return "404";
    }

}
