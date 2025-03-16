package org.amu.examManagement.controllers;

import org.amu.examManagement.model.Exam;
import org.amu.examManagement.model.Teacher;
import org.amu.examManagement.services.ExamService;
import org.amu.examManagement.services.TeacherService;
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
    private TeacherService teacherService;

    // Affiche la page d'accueil
    @GetMapping("/")
    public String showHome(Model model) {
        return "homepage";
    }

    // Affiche la page tableau de bord
    @GetMapping("/teacher/{id}/dashboard")
    public String showDashboard(@PathVariable Long id, Model model) {
        // On récupère l'enseignant dont l'identifiant correspond au paramètre
        Optional<Teacher> teacherOpt = teacherService.getTeacher(id);
        // Si on trouve le teacher correspondant, on envoie l'objet dans le model
        if (teacherOpt.isPresent()) {
            model.addAttribute("teacher", teacherOpt.get());
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
