package com.gescom.banque.service;


import com.gescom.banque.dto.SoldeRequest;
import com.gescom.banque.dto.SoldeResponse;
import com.gescom.banque.modele.CarteCredit;
import com.gescom.banque.repository.CarteCreditRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CarteCreditService {

    private CarteCreditRepository carteCreditRepository = null;

    public CarteCreditService(CarteCreditRepository carteCreditRepository) {
        this.carteCreditRepository = carteCreditRepository;
    }

    public void create(CarteCredit carteCredit){
        carteCreditRepository.save(carteCredit);
    }

    public CarteCredit findById(Long id){
        return carteCreditRepository.findById(id).orElse(null);
    }

    public void update(CarteCredit carteCredit){
        carteCreditRepository.save(carteCredit);
    }

    public SoldeResponse verifierSolde(SoldeRequest request) {

        CarteCredit carte = carteCreditRepository.findByNumCarte(request.getNumeroCarte()).orElse(null);

        if (carte == null) {
            return new SoldeResponse(
                    request.getNumeroCarte(),
                    "REJETE",
                    "Carte inconnue",
                    0.0
            );
        }

        Double solde = carte.getSoldeCarte();

        if (solde >= request.getMontant()) {
            return new SoldeResponse(
                    request.getNumeroCarte(),
                    "ACCEPTE",
                    "Solde correcte",
                    solde
            );
        }

        return new SoldeResponse(
                request.getNumeroCarte(),
                "REJETE",
                "Solde insuffisant",
                solde
        );
    }
}
