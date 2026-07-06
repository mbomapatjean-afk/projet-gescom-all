package com.gescom.document.modele;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "DOCUMENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DOCUMENT")
    private Long idDocument;

    @Column(name = "ID_CLIENT")
    private Long idClient;

    @Column(name = "DATE_PAIEMENT")
    private LocalDate datePaiement;

    @Column(name = "NUM_PAIEMENT")
    private String numPaiement;

    @Column(name = "MONTANT_PAIEMENT")
    private Double montantPaiement;

    @Column(name = "num_COMMANDE")
    private String numCommande;

    @Column(name = "MESSAGE_NOTIFICATION")
    private String messageNotif;
}
