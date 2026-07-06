package com.gescom.produit.service;

import com.gescom.produit.modele.Produit;
import com.gescom.produit.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {

    private final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public List<Produit> obtenirCatalogueProduits() {
        return produitRepository.findAll();
    }

    public Produit rechercherProduitParId(Long idProduit) {
        return produitRepository.findById(idProduit).orElse(null);
    }
}