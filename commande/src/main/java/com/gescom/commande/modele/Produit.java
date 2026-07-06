package com.gescom.commande.modele;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Produit {

    private Long idProduit;
    private String codeProduit;
    private String nomProduit;
    private Double prixUnitProduit;

}
