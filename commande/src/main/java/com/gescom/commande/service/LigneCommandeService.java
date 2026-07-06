package com.gescom.commande.service;

import com.gescom.commande.dto.LigneCommandeDTO;
import com.gescom.commande.modele.LigneCommande;
import com.gescom.commande.repository.LigneCommandeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LigneCommandeService {

    private final LigneCommandeRepository ligneCommandeRepository;

    public LigneCommandeService(LigneCommandeRepository ligneCommandeRepository) {
        this.ligneCommandeRepository = ligneCommandeRepository;
    }

    @Transactional
    public List<LigneCommande> enregistrerToutesLesLignes(List<LigneCommandeDTO> panier, Long idCommande) {
        List<LigneCommande> lignes = panier.stream().map(dto -> {
            LigneCommande ligne = new LigneCommande();
            ligne.setIdCommande(idCommande);
            ligne.setIdProduit(dto.getIdProduit());
            ligne.setQuantiteCommande(dto.getQuantite());
            ligne.setPrixUnitCommande(dto.getPrixUnitProduit());
            
            return ligne;
        }).collect(Collectors.toList());

        return ligneCommandeRepository.saveAll(lignes);
    }
}
