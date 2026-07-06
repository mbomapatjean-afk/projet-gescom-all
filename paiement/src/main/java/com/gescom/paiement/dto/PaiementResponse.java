package com.gescom.paiement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaiementResponse {

    private String numeroPaiement;
    private String statut;
    private String message;
}
