package com.gescom.commande.controller;

import com.gescom.commande.dto.ClientDTO;
import com.gescom.commande.dto.LigneCommandeDTO;
import com.gescom.commande.modele.Commande;
import com.gescom.commande.modele.Notification;
import com.gescom.commande.modele.Produit;
import com.gescom.commande.repository.NotificationRepository;
import com.gescom.commande.service.CommandeService;
import com.gescom.commande.service.EmailService;
import com.gescom.commande.service.NotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommandeController {

    private final CommandeService commandeService;
    private final EmailService emailService;
    private final NotificationService notificationService;
    private final NotificationRepository notificationRepository;

    @Value("${app.services.loginPage.url}")
    private String loginPage;


    public CommandeController(CommandeService commandeService, EmailService emailService, NotificationService notificationService, NotificationRepository notificationRepository) {
        this.commandeService = commandeService;
        this.emailService = emailService;
        this.notificationService = notificationService;
        this.notificationRepository = notificationRepository;
    }

    @ModelAttribute
    public void addClientAttributes(@ModelAttribute ClientDTO clientDTO, Model model) {
        if (clientDTO != null) {
            model.addAttribute("idClient", clientDTO.getIdClient());
            model.addAttribute("nomClient", clientDTO.getNomClient());
            model.addAttribute("emailClient", clientDTO.getEmailClient());
        }
    }

    @GetMapping("/")
    public String index(@ModelAttribute ClientDTO clientDTO, RedirectAttributes redirectAttributes) {
        if (clientDTO == null || clientDTO.getIdClient() == null) {
            return "redirect:" + loginPage;
        }
        redirectAttributes.addFlashAttribute("clientDTO", clientDTO);
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String afficherHome(@ModelAttribute ClientDTO clientDTO) {
        if (clientDTO == null || clientDTO.getIdClient() == null) {
            return "redirect:" + loginPage;
        }
        return "home";
    }

    @RequestMapping(value = "/commande", method = {RequestMethod.GET, RequestMethod.POST})
    public String afficherSaisieCommande(@ModelAttribute ClientDTO clientDTO, Model model) {
        if (clientDTO == null || clientDTO.getIdClient() == null) {
            return "redirect:" + loginPage;
        }
        List<Produit> produits = commandeService.obtenirCatalogueProduits();
        model.addAttribute("retourAccueil", loginPage);
        model.addAttribute("produits", produits);
        return "saisie-commande";
    }

    @RequestMapping(value = "/saveCommande", method = {RequestMethod.GET, RequestMethod.POST})
    @PostMapping("/saveCommande")
    public String enregistrerCommande(
            @RequestParam("idProduit[]") List<Long> idsProduits,
            @RequestParam("quantiteCommande[]") List<Integer> quantites,
            @RequestParam("prixUnitProduit[]") List<Double> prixUnit,
            @RequestParam("montantTotal") Double montantTotal,
            @ModelAttribute ClientDTO clientDTO,
            RedirectAttributes redirectAttributes,
            Model model) {

        model.addAttribute("retourAccueil", loginPage);
        Commande commande = new Commande();
        commande.setIdClient(clientDTO.getIdClient());
        commande.setNumCommande("CMD-" + System.currentTimeMillis());
        commande.setDateCommande(LocalDate.now());

        List<LigneCommandeDTO> panier = new ArrayList<>();
        for (int i = 0; i < idsProduits.size(); i++) {
            panier.add(new LigneCommandeDTO(idsProduits.get(i), quantites.get(i), prixUnit.get(i)));
        }
        commande.setMontantCommande(montantTotal);
        commande = commandeService.enregistrerCommande(panier, commande);

        try {
            String targetEmail = clientDTO.getEmailClient();
            String numCmd = commande.getNumCommande();
//            String messageMail = commande.getNotif().getMessageNotif();
            String messageMail = "";
            // Cet appel sera instantané si tu actives l'asynchrone
//            emailService.envoyerNotificationCommande(targetEmail, numCmd, messageMail);

        } catch (Exception e) {
            if (e instanceof ResourceAccessException) {
                redirectAttributes.addFlashAttribute("error", "Le service de notification est injoignable (Down).");
            }
        }

        redirectAttributes.addFlashAttribute("commande", commande);
        redirectAttributes.addFlashAttribute("clientDTO", clientDTO);

        return "redirect:/resultat-commande";
    }

    @GetMapping("/resultat-commande")
    public String afficherResultatCommande(@ModelAttribute("commande") Commande commande,
                                          @ModelAttribute("clientDTO") ClientDTO clientDTO) {
        if (clientDTO == null) {
            // Si on arrive ici sans commande (ex: accès direct à l'URL), on redirige vers l'accueil
            return "redirect:/home";
        }
        return "resultat-commande";
    }

    @GetMapping("/login")
    public String annuler() {
        return "redirect:"+loginPage;
    }

    private String handleException(Exception e, String viewName, String serviceName,
                                   @ModelAttribute ClientDTO clientDTO,Model model) {
        if (e instanceof ResourceAccessException) {
            model.addAttribute("error", "Le " + serviceName + " est injoignable (Down).");
        }

        model.addAttribute("nomClient", clientDTO.getNomClient());
        model.addAttribute("emailClient", clientDTO.getEmailClient());
        return viewName;
    }
}
