package org.amu.examManagement.controllers;

import org.amu.examManagement.model.Exam;
import org.amu.examManagement.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    // Affiche la page d'accueil
    @GetMapping("/")
    public String showHome(Model model) {
        return "homepage";
    }

    // Affiche la page 404
    @GetMapping("/error")
    public String showError() {
        return "404";
    }

}
