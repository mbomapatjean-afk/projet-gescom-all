package com.gescom.commande.dto;

public class PaiementResponse {

    private String numeroPaiement;
    private String statut;
    private String message;

    public PaiementResponse() {
    }

    public PaiementResponse(String numeroPaiement, String statut, String message) {
        this.numeroPaiement = numeroPaiement;
        this.statut = statut;
        this.message = message;
    }

    public String getNumeroPaiement() {
        return numeroPaiement;
    }

    public void setNumeroPaiement(String numeroPaiement) {
        this.numeroPaiement = numeroPaiement;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
