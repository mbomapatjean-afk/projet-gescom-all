/**
package com.gescom.commande.service;

import com.formation.modele.com.gescom.commande.Commande;
import com.formation.modele.com.gescom.commande.Notification;
import com.formation.modele.com.gescom.commande.Paiement;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaiementService {

    private final NotificationService notificationService;

    private Long compteurPaiement = 1L;

    public PaiementService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public boolean verifierMontant(Commande commande, double montantPaiement) {
        return Double.compare(commande.getMontantCommande(), montantPaiement) == 0;
    }

    public Paiement effectuerPaiement(Commande commande, double montantPaiement) {

        if (!verifierMontant(commande, montantPaiement)) {
            return null;
        }

        Paiement paiement = new Paiement(compteurPaiement, LocalDate.now(), "PAY-" + compteurPaiement, montantPaiement, commande);

        compteurPaiement++;

        String message = "Paiement de la commande " + commande.getNumCommande() + " effectué avec succès.";

        Notification notif = notificationService.envoyerNotification(message);

        paiement.setNotif(notif);

        return paiement;
    }
}
 */

package com.gescom.commande.service;

import com.gescom.commande.dto.PaiementRequest;
import com.gescom.commande.dto.PaiementResponse;
import com.gescom.commande.modele.Commande;
import com.gescom.commande.modele.Notification;
import com.gescom.commande.modele.Paiement;
import com.gescom.commande.repository.PaiementRepository;
import com.gescom.commande.restclient.PaiementRestClient;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaiementService {

    private final NotificationService notificationService;
    private final PaiementRestClient paiementRestClient;
    private final PaiementRepository paiementRepository;

    @Getter
    private PaiementResponse lastPaiementResponse;

    private Long compteurPaiement = 1L;

    public PaiementService(
            NotificationService notificationService,
            PaiementRestClient paiementRestClient,
            PaiementRepository paiementRepository) {

        this.notificationService = notificationService;
        this.paiementRestClient = paiementRestClient;
        this.paiementRepository = paiementRepository;
    }

    public Paiement effectuerPaiement(
            Commande commande,
            String numeroCarte,
            String nomTitulaire,
            String dateExpiration) {

        PaiementRequest request = new PaiementRequest(
                numeroCarte,
                nomTitulaire,
                commande.getMontantCommande(),
                dateExpiration
        );

        PaiementResponse response = paiementRestClient.effectuerPaiement(request);
        this.lastPaiementResponse = response;

        if (response == null) {
            this.lastPaiementResponse = new PaiementResponse();
            this.lastPaiementResponse.setStatut("ERREUR");
            this.lastPaiementResponse.setMessage("Service de paiement indisponible.");
            return null;
        }

        // Pour diagnostiquer
        System.out.println("Numero paiement : " + response.getNumeroPaiement());
        System.out.println("Statut : " + response.getStatut());
        System.out.println("Message : " + response.getMessage());
        // Fin message diagnostick

        if (!"ACCEPTE".equals(response.getStatut())) {
            return null;
        }

        Paiement paiement = new Paiement(
                compteurPaiement,
                LocalDate.now(),
                response.getNumeroPaiement(),
                commande.getMontantCommande(),
                commande
        );

        compteurPaiement++;

        String message = "Paiement de la commande " + commande.getNumCommande() + " effectué avec succès.";

        Notification notif = notificationService.envoyerNotification(message);

        paiement.setNotif(notif);

        return paiementRepository.save(paiement);
    }

}

