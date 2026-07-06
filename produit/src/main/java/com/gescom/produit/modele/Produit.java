package com.gescom.produit.modele;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUIT")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUIT")
    private Long idProduit;

    @Column(name = "CODE_PRODUIT")
    private String codeProduit;

    @Column(name = "NOM_PRODUIT")
    private String nomProduit;

    @Column(name = "PRIX_UNIT_PRODUIT")
    private Double prixUnitProduit;
}
