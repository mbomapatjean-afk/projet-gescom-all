package com.gescom.accueil.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilController {

    @Value("${app.services.commande.url}")
    private String commandeUrl;

    @Value("${app.services.rechercheCommande.url}")
    private String rechercheCommandeUrl;


    @GetMapping("/")
    public String afficherHome(Model model) {
        model.addAttribute("commandeUrl", commandeUrl);
        model.addAttribute("rechercheCommandeUrl", rechercheCommandeUrl);
        return "home";
    }
}
