package com.gescom.paiement.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SoldeResponse {

    private String numeroCarte;
    private String statut;
    private String message;
    private Double soldeDisponible;
}
