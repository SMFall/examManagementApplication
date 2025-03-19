package org.amu.examManagement.controllers;

import jakarta.servlet.http.HttpSession;
import org.amu.examManagement.model.Users;
import org.amu.examManagement.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UsersService usersService;

    // Afficher le formulaire de login
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login";
    }

    // Traiter la soumission du formulaire
    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpSession session,
                               Model model) {

        // On recherche l'utilisateur par username
        Optional<Users> userOpt = usersService.findByUsername(username);
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            // Vérification du mot de passe
            if (user.getPassword().equals(password)) {
                // Connexion OK -> on stocke l'utilisateur en session
                session.setAttribute("loggedUser", user);

                // Rediriger vers le dashboard ou la page d'accueil...
                return "redirect:/users/" + user.getUser_id() + "/dashboard";
            } else {
                // Mauvais mot de passe
                model.addAttribute("error", "Utilisateur inconnu ou Mot de passe incorrect");
                return "login";
            }
        } else {
            // Utilisateur non trouvé
            model.addAttribute("error", "Utilisateur inconnu ou Mot de passe incorrect");
            return "login";
        }
    }

    // Déconnexion
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // On invalide la session
        session.invalidate();
        // Retour page login ou home
        return "redirect:/login";
    }
}