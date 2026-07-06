package com.gescom.commande.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COMMANDE")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COMMANDE")
    private Long idCommande;

    @Column(name = "DATE_COMMANDE")
    private LocalDate dateCommande;

    @Column(name = "NUM_COMMANDE")
    private String numCommande;

    @Column(name = "MONTANT_COMMANDE")
    private Double montantCommande;

    @Column(name = "ID_CLIENT")
    private Long idClient;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_COMMANDE")
    private List<LigneCommande> lignesCommande = new ArrayList<>();

    public void ajouterLigne(Produit produit, Integer quantiteCommande) {

        LigneCommande ligne = new LigneCommande();
        ligne.setIdProduit(produit.getIdProduit());
        ligne.setQuantiteCommande(quantiteCommande);
        ligne.setPrixUnitCommande(produit.getPrixUnitProduit());
        lignesCommande.add(ligne);

        recalculerMontant();
    }

    public void recalculerMontant() {
        montantCommande = lignesCommande.stream()
                .mapToDouble(LigneCommande::calculerMontantLigne)
                .sum();
    }
}
