package com.gescom.commande.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LigneCommandeDTO {

    private Long idCommande;
    private Long idProduit;
    private Integer quantite;
    private Double prixUnitProduit;

    public LigneCommandeDTO(Long idProduit, Integer quantite, Double prixUnitProduit) {
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.prixUnitProduit = prixUnitProduit;
    }

}
