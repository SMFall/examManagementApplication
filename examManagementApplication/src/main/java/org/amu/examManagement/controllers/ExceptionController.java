package org.amu.examManagement.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController implements ErrorController {

    // Mappe toutes les erreurs sur /error
    @RequestMapping("/error")
    public String handleError() {
        // Retourne la page d'erreur 404
        return "404";
    }
}
