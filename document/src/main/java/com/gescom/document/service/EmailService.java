package com.gescom.document.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void envoyerNotificationCommande(String destinataire, String numCommande, String messageNotif) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("mbomapatjean@gmail.com");
        message.setTo(destinataire);
        message.setSubject("Confirmation de votre commande N° " + numCommande);
        message.setText(messageNotif);

        mailSender.send(message);
    }
}