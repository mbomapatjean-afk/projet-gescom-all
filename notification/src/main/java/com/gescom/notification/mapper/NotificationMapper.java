package com.gescom.notification.mapper;

import com.gescom.notification.dto.NotificationDTO;
import com.gescom.notification.modele.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationDTO toDTO(Notification notification) {
        if (notification == null) {
            return null;
        }
        NotificationDTO dto = new NotificationDTO();
        dto.setIdNotif(notification.getIdNotif());
        dto.setDateNotif(notification.getDateNotif());
        dto.setMessageNotif(notification.getMessageNotif());
        dto.setIdClient(Long.valueOf(notification.getIdClient()));
        return dto;
    }

    public Notification toEntity(NotificationDTO dto) {
        if (dto == null) {
            return null;
        }
        Notification notification = new Notification();
        notification.setIdNotif(dto.getIdNotif());
        notification.setDateNotif(dto.getDateNotif());
        notification.setMessageNotif(dto.getMessageNotif());
        notification.setIdClient(String.valueOf(dto.getIdClient()));
        return notification;
    }
}
