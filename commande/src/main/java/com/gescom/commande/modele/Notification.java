package com.gescom.commande.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "NOTIFICATION")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NOTIFICATION")
    private Long idNotif;

    @Column(name = "DATE_NOTIFICATION")
    private LocalDate dateNotif;

    @Column(name = "MESSAGE_NOTIFICATION")
    private String messageNotif;

    @Column(name = "ID_CLIENT")
    private Long idClient;

}