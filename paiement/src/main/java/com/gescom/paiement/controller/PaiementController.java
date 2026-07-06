package com.gescom.paiement.controller;

import com.gescom.paiement.dto.PaiementRequest;
import com.gescom.paiement.dto.PaiementResponse;
import com.gescom.paiement.service.PaiementService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    private final PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @PostMapping
    public PaiementResponse creerPaiement(@RequestBody PaiementRequest request) {

        return paiementService.effectuerPaiement(request);
    }
}
