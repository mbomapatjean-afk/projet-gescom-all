package com.gescom.commande.modele;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "PAIEMENT")
public class Paiement {

    @Id
    @Column(name = "ID_PAIEMENT")
    private Long idPaiement;

    @Column(name = "DATE_PAIEMENT")
    private LocalDate datePaiement;

    @Column(name = "NUM_PAIEMENT")
    private String numPaiement;

    @Column(name = "MONTANT_PAIEMENT")
    private Double montantPaiement;

    @OneToOne
    @JoinColumn(name = "ID_COMMANDE")
    private Commande commande;

    @OneToOne
    @JoinColumn(name = "ID_NOTIFICATION")
    private Notification notif;

    public Paiement() {}

    public Paiement(Long idPaiement, LocalDate datePaiement, String numPaiement,
                    Double montantPaiement, Commande commande) {
        this.idPaiement = idPaiement;
        this.datePaiement = datePaiement;
        this.numPaiement = numPaiement;
        this.montantPaiement = montantPaiement;
        this.commande = commande;
    }

    public boolean montantConforme() {
        return commande != null
                && Double.compare(montantPaiement, commande.getMontantCommande()) == 0;
    }

    public Long getIdPaiement() { return idPaiement; }
    public void setIdPaiement(Long idPaiement) { this.idPaiement = idPaiement; }

    public LocalDate getDatePaiement() { return datePaiement; }
    public void setDatePaiement(LocalDate datePaiement) { this.datePaiement = datePaiement; }

    public String getNumPaiement() { return numPaiement; }
    public void setNumPaiement(String numPaiement) { this.numPaiement = numPaiement; }

    public Double getMontantPaiement() { return montantPaiement; }
    public void setMontantPaiement(Double montantPaiement) { this.montantPaiement = montantPaiement; }

    public Commande getCommande() { return commande; }
    public void setCommande(Commande commande) { this.commande = commande; }

    public Notification getNotif() { return notif; }
    public void setNotif(Notification notif) { this.notif = notif; }
}