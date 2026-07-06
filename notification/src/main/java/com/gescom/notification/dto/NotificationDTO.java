package com.gescom.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
    private Long idNotif;
    private LocalDate dateNotif;
    private String messageNotif;
    private Long idClient;
}
