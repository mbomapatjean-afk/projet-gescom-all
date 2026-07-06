package com.gescom.commande.repository;

import com.gescom.commande.modele.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Object getNotificationByIdNotif(Long idNotif);

}