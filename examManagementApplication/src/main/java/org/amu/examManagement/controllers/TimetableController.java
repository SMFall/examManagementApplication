package org.amu.examManagement.controllers;

import jakarta.servlet.http.HttpSession;
import org.amu.examManagement.model.Course;
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
public class TimetableController {

    @Autowired
    private CourseService courseService;

    // ==================================
    //         EMPLOI DU TEMPS
    // ==================================

    @GetMapping("/timetable")
    public String showCoursesList(Model model, HttpSession session) {

        // On récupère l'enseignant dont l'identifiant correspond au paramètre
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        if (loggedUser != null && loggedUser.getRole().equals("student")) {

            List<Course> courses = courseService.getAllCoursesForStudent(loggedUser.getUser_id());
            model.addAttribute("courses", courses);

            model.addAttribute("users", loggedUser);

            return "timetable";
        } else {
            return "redirect:/error";
        }
    }

}
