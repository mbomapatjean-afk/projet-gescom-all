package com.gescom.banque.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BANQUE")
public class CarteCredit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BANQUE")
    private Long idBanque;

    @Column(name = "ID_CLIENT")
    private String idClient;

    @Column(name = "NUM_CARTE")
    private String numCarte;

    @Column(name = "DATE_EXPIRATION")
    private Date dateExpiration;

    @Column(name = "SOLDE_CARTE")
    private Double soldeCarte;
}
