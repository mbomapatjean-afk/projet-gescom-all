package com.gescom.commande.dto;

public class PaiementRequest {

    private String numeroCarte;
    private String nomTitulaire;
    private double montant;
    private String dateExpiration;

    public PaiementRequest() {
    }

    public PaiementRequest(String numeroCarte, String nomTitulaire, double montant, String dateExpiration) {
        this.numeroCarte = numeroCarte;
        this.nomTitulaire = nomTitulaire;
        this.montant = montant;
        this.dateExpiration = dateExpiration;
    }

    public String getNumeroCarte() {
        return numeroCarte;
    }

    public void setNumeroCarte(String numeroCarte) {
        this.numeroCarte = numeroCarte;
    }

    public String getNomTitulaire() {
        return nomTitulaire;
    }

    public void setNomTitulaire(String nomTitulaire) {
        this.nomTitulaire = nomTitulaire;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
}
