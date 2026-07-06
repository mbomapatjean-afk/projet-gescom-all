package com.gescom.produit.controller;

import com.gescom.produit.modele.Produit;
import com.gescom.produit.service.ProduitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/catalogue")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping
    public List<Produit> obtenirCatalogueProduits() {
        return produitService.obtenirCatalogueProduits();
    }
}
