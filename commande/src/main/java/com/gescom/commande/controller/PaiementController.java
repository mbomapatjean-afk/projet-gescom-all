package com.gescom.commande.controller;

import com.gescom.commande.dto.ClientDTO;
import com.gescom.commande.dto.PaiementResponse;
import com.gescom.commande.modele.Commande;
import com.gescom.commande.modele.Paiement;
import com.gescom.commande.service.CommandeService;
import com.gescom.commande.service.PaiementService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PaiementController {

    private final CommandeService commandeService;
    private final PaiementService paiementService;


    @Value("${app.services.loginPage.url}")
    private String loginPage;


    public PaiementController(
            CommandeService commandeService,
            PaiementService paiementService) {

        this.commandeService = commandeService;
        this.paiementService = paiementService;
    }

    @ModelAttribute
    public void addClientAttributes(@ModelAttribute ClientDTO clientDTO, Model model) {
        if (clientDTO != null) {
            model.addAttribute("idClient", clientDTO.getIdClient());
            model.addAttribute("nomClient", clientDTO.getNomClient());
            model.addAttribute("emailClient", clientDTO.getEmailClient());
        }
    }

//    @GetMapping("/rechercher")
//    public String index(
//            @RequestParam(value = "numCommande", required = false) String numCommande,
//            @ModelAttribute ClientDTO clientDTO,
//            RedirectAttributes redirectAttributes ) {
//        if (clientDTO == null || clientDTO.getIdClient() == null) {
//            return "redirect:" + loginPage;
//        }
//        redirectAttributes.addFlashAttribute("numCommande", numCommande);
//        redirectAttributes.addFlashAttribute("clientDTO", clientDTO);
//        return "redirect:/rechercher-commande";
//    }


    @RequestMapping(value = "/rechercher", method = {RequestMethod.GET, RequestMethod.POST})
    public String rechercherPagePaiement(
            @RequestParam(value = "numCommande", required = false) String numCommande,
            @ModelAttribute ClientDTO clientDTO,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (numCommande != null && !numCommande.isEmpty()) {
            Commande commande = commandeService.rechercherCommandeParNumero(numCommande);
            if (commande == null) {
                model.addAttribute("error", "Commande non trouvée pour le numéro : " + numCommande);
            } else {
                model.addAttribute("commande", commande);
            }
        } else {
            model.addAttribute("message", "empty");
        }


        redirectAttributes.addFlashAttribute("numCommande", numCommande);
        redirectAttributes.addFlashAttribute("clientDTO", clientDTO);

        return "rechercher-commande";
    }


    @GetMapping("/paiement")
    public String afficherPagePaiement(
            @RequestParam(value = "numCommande", required = false) String numCommande,
            @RequestParam(value = "idCommande", required = false) Long idCommande,
            Model model) {

//        model.addAttribute("retourAccueil", retourAccueil);
        Commande commande = null;
        if (numCommande != null && !numCommande.isEmpty()) {
            commande = commandeService.rechercherCommandeParNumero(numCommande);
        } else if (idCommande != null) {
            commande = commandeService.rechercherCommandeParId(idCommande);
        }

        if (commande != null) {
            model.addAttribute("commande", commande);
        } else {
            model.addAttribute("error", "Commande non trouvée.");
        }

        return "effectuer-paiement";
    }

    @PostMapping("/paiement/valider")
    public String validerPaiement(
            @RequestParam("idCommande") Long idCommande,
            @RequestParam("numeroCarte") String numeroCarte,
            @RequestParam("nomTitulaire") String nomTitulaire,
            @RequestParam("dateExpiration") String dateExpiration,
            Model model) {

        Commande commande = commandeService.rechercherCommandeParId(idCommande);

        Paiement paiement = paiementService.effectuerPaiement(
                commande,
                numeroCarte,
                nomTitulaire,
                dateExpiration
        );

        PaiementResponse response = paiementService.getLastPaiementResponse();

//        model.addAttribute("retourAccueil", retourAccueil);
        model.addAttribute("commande", commande);
        model.addAttribute("paiement", paiement);
        com.gescom.commande.modele.Notification notif = new com.gescom.commande.modele.Notification();

        if (paiement == null) {
            Paiement dummyPaiement = new Paiement();
            dummyPaiement.setNumPaiement("ERREUR");
            dummyPaiement.setDatePaiement(java.time.LocalDate.now());
            notif.setMessageNotif((response != null ? response.getMessage() : "Erreur inconnue")+" - Paiement refusé par la plateforme de paiement.");
            dummyPaiement.setNotif(notif);
            model.addAttribute("paiement", dummyPaiement);

            model.addAttribute("message", (response != null ? response.getMessage() : "Erreur inconnue")+" - Paiement refusé par la plateforme de paiement.");
            return "echec-paiement";
        }

        model.addAttribute("message",  response != null ? response.getMessage() : "Paiement effectué avec succès");

        return "resultat-paiement";
    }
}