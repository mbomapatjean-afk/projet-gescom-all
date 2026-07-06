/**
package com.gescom.commande.service;

import com.formation.modele.com.gescom.commande.Notification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NotificationService {

    private Long compteurNotification = 100L;

    public Notification envoyerNotification(String message) {

        Notification notification =
                new Notification(
                        compteurNotification,
                        LocalDate.now(),
                        message
                );

        compteurNotification++;

        return notification;
    }
}
 */

package com.gescom.commande.service;

import com.gescom.commande.modele.Notification;
import com.gescom.commande.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification envoyerNotification(String message) {

        Notification notification = new Notification();
        notification.setDateNotif(LocalDate.now());
        notification.setMessageNotif(message);

        return notificationRepository.save(notification);
    }
}