package com.gescom.banque.controller;


import com.gescom.banque.dto.SoldeRequest;
import com.gescom.banque.dto.SoldeResponse;
import com.gescom.banque.service.CarteCreditService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cartes")
public class CarteCreditController {

    private final CarteCreditService carteCreditService;

    public CarteCreditController(CarteCreditService carteCreditService) {
        this.carteCreditService = carteCreditService;
    }

    @PostMapping("/verifier-solde")
    public SoldeResponse verifierSolde(@RequestBody SoldeRequest request) {
        return carteCreditService.verifierSolde(request);
    }
}
