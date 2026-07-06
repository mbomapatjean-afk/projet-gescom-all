package com.gescom.paiement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaiementRequest {

    private String numeroCarte;
    private String nomTitulaire;
    private double montant;
    private String dateExpiration;
}
