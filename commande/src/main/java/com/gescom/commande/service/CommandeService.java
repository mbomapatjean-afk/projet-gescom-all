package com.gescom.commande.service;

import com.gescom.commande.modele.Commande;
import com.gescom.commande.modele.Notification;
import com.gescom.commande.modele.Produit;
import com.gescom.commande.modele.LigneCommande;
import com.gescom.commande.repository.CommandeRepository;
import com.gescom.commande.repository.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {

    private final ProduitService produitService;
    private final NotificationService notificationService;
    private final LigneCommandeService ligneCommandeService;

    private final NotificationRepository notificationRepository;
    private final CommandeRepository commandeRepository;


    public CommandeService(
            ProduitService produitService,
            NotificationService notificationService,
            LigneCommandeService ligneCommandeService,
            NotificationRepository notificationRepository,
            CommandeRepository commandeRepository) {

        this.produitService = produitService;
        this.notificationService = notificationService;
        this.ligneCommandeService = ligneCommandeService;
        this.notificationRepository = notificationRepository;
        this.commandeRepository = commandeRepository;
    }

    public List<Produit> obtenirCatalogueProduits() {
        return produitService.obtenirCatalogueProduits();
    }

    @Transactional
    public Commande enregistrerCommande(List<com.gescom.commande.dto.LigneCommandeDTO> panier, Commande commande ) {
        return enregistrerCommandeAvecClient(panier, commande);
    }

    private Commande enregistrerCommandeAvecClient(List<com.gescom.commande.dto.LigneCommandeDTO> panier, Commande commande) {
        if (panier == null || panier.isEmpty()) {
            throw new IllegalArgumentException("Le panier ne peut pas être vide.");
        }
        commande = CommandeService.this.commandeRepository.save(commande);

        Notification notif =
                notificationService.envoyerNotification(
                        "Commande "
                                + commande.getNumCommande()
                                + " enregistrée avec succès."
                );
        notif.setIdClient(commande.getIdClient());
        notificationRepository.save(notif);

        ligneCommandeService.enregistrerToutesLesLignes(panier, commande.getIdCommande());

        return commande;
    }

    public Commande rechercherCommandeParId(Long idCommande) {
        return commandeRepository
                .findById(idCommande)
                .orElseThrow(() -> new RuntimeException(
                        "Commande introuvable : " + idCommande
                ));
    }

    public Commande rechercherCommandeParNumero(String numCommande) {
        return commandeRepository
                .findByNumCommande(numCommande)
                .orElse(null);
    }

}
