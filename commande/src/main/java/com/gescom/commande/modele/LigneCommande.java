package com.gescom.commande.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LIGNE_COMMANDE")
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LIGNE_COMMANDE")
    private Long idLigneCommande;

    @Column(name = "ID_COMMANDE")
    private Long idCommande;

    @Column(name = "ID_PRODUIT")
    private Long idProduit;

    @Column(name = "QUANTITE_PRODUIT")
    private Integer quantiteCommande;

    @Column(name = "PRIX_UNIT_PRODUIT")
    private Double prixUnitCommande;

    public Double calculerMontantLigne() {
        if (quantiteCommande == null || prixUnitCommande == null) return 0.0;
        return quantiteCommande * prixUnitCommande;
    }
}