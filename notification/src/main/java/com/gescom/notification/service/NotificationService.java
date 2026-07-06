package com.gescom.notification.service;


import com.gescom.notification.dto.NotificationDTO;
import com.gescom.notification.mapper.NotificationMapper;
import com.gescom.notification.modele.Notification;
import com.gescom.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;
import java.time.LocalDate;
import java.util.Map;

@Service
public class NotificationService {

    private static final String EMAIL_SUJET = "Bienvenue chez GESCOM";
    private static final String LOGO_PATH = "src/main/webapp/static/images/logo.jpeg";

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final EmailService emailService;
    private final RestTemplate restTemplate;

    @Value("${app.services.getIdClient.url}")
    private String getIdClientUrl;


    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper, EmailService emailService, RestTemplate restTemplate) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.emailService = emailService;
        this.restTemplate = restTemplate;
    }

    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        return notificationMapper.toDTO(notificationRepository.save(notificationMapper.toEntity(notificationDTO)));
    }

    public void createNotification(Map<String, String> clientData) {
        String nomClient = clientData.get("nomClient");
        String emailClient = clientData.get("emailClient");

        try {   // Récupération de l'ID client auprès du service createClient
            String finalUrl = getIdClientUrl.replace("{email}", emailClient);
            System.out.println("Appel du service createClient à l'URL : " + finalUrl);
            String idClient = restTemplate.getForObject(finalUrl, String.class);
            if (idClient != null) {
                System.out.println("ID client récupéré : " + idClient);
                clientData.put("idClient", idClient);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération de l'ID client : " + e.getMessage());
        }

        if (saveNotification("Création du compte - " + nomClient, clientData)){
            sendMailToClient(nomClient, emailClient);
        }
    }

    private boolean saveNotification(String message, Map<String, String> clientData) {
        try {
            Object idClientObj = clientData.get("idClient");
            String idClient = (idClientObj != null) ? idClientObj.toString() : "Inconnu";

            Notification notification = new Notification();
            notification.setDateNotif(LocalDate.now());
            notification.setMessageNotif(message);
            notification.setIdClient(idClient);

            Notification savedNotif = notificationRepository.save(notification);
            System.out.println("Notification sauvegardée en base avec l'ID : " + savedNotif.getIdNotif());
            return true;
        } catch (Exception e) {
            System.err.println("Erreur dans NotificationService lors de la sauvegarde : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private void sendMailToClient(String nomClient, String emailClient) {
        String contenuHtml = String.format(
                "<html>" +
                "<body>" +
                "<div style='text-align: center; margin-bottom: 20px;'>" +
                "   <img src='cid:logo' alt='Logo GESCOM' style='max-width: 150px;'>" +
                "</div>" +
                "<h3>Bienvenue chez GESCOM</h3>" +
                "<p>Bonjour <strong>%s</strong>,</p>" +
                "<p>Votre compte a été créé avec succès.</p>" +
                "<p>Vos identifiants de connexion sont :</p>" +
                "<ul>" +
                "   <li><strong>Identifiant :</strong> %s</li>" +
                "   <li><strong>Mot de passe :</strong> %s</li>" +
                "</ul>" +
                "<p>Cordialement,<br>L'équipe GESCOM</p>" +
                "</body>" +
                "</html>",
                nomClient, nomClient, emailClient
        );

//        emailService.envoyerEmail(emailClient, EMAIL_SUJET, contenuHtml, LOGO_PATH);

    }
}