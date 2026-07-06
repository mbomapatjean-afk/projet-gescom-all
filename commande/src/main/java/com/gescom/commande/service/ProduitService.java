package com.gescom.commande.service;

import com.gescom.commande.modele.Produit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.Collections;
import java.util.List;

@Service
public class ProduitService {

    private final RestClient restClient;
    private final String catalogueProduitsUrl;

    public ProduitService(
            RestClient restClient,
            @Value("${app.services.produit.listUrl}") String catalogueProduitsUrl) {
        this.restClient = restClient;
        this.catalogueProduitsUrl = catalogueProduitsUrl;
    }

    public List<Produit> obtenirCatalogueProduits() {
        try {
            List<Produit> produits = restClient.get()
                    .uri(catalogueProduitsUrl)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<Produit>>() {});

            return produits != null ? produits : Collections.emptyList();

        } catch (RestClientResponseException e) {
            throw new IllegalStateException(
                    "Impossible de récupérer le catalogue des produits. " +
                            "Vérifie l'URL appelée : " + catalogueProduitsUrl +
                            " | Statut HTTP : " + e.getStatusCode(),
                    e
            );
        }
    }

    public Produit rechercherProduitParId(Long idProduit) {
        List<Produit> produits = obtenirCatalogueProduits();

        return produits.stream()
                .filter(p -> p.getIdProduit().equals(idProduit))
                .findFirst()
                .orElse(null);
    }
}