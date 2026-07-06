package com.gescom.notification.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "NOTIFICATION")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NOTIFICATION")
    private Long idNotif;

    @Column(name = "DATE_NOTIFICATION")
    private LocalDate dateNotif;

    @Column(name = "MESSAGE_NOTIFICATION")
    private String messageNotif;

    private String idClient;

}