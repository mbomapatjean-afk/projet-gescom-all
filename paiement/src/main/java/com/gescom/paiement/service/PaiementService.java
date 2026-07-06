package com.gescom.paiement.service;

import com.gescom.paiement.dto.PaiementRequest;
import com.gescom.paiement.dto.PaiementResponse;
import com.gescom.paiement.dto.SoldeRequest;
import com.gescom.paiement.dto.SoldeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaiementService {

    @Value("${app.services.banque.url}")
    private String banqueUrl;

    private Long compteurPaiement = 1L;

    private final RestTemplate restTemplate = new RestTemplate();

    public PaiementResponse effectuerPaiement(PaiementRequest request) {

        SoldeRequest soldeRequest =
            new SoldeRequest(
                request.getNumeroCarte(),
                request.getMontant()
            );

        SoldeResponse soldeResponse =
            restTemplate.postForObject(
                banqueUrl,
                soldeRequest,
                SoldeResponse.class
            );

        if (soldeResponse != null && "ACCEPTE".equals(soldeResponse.getStatut())) {

            String numeroPaiement = "PAY-" + compteurPaiement;
            compteurPaiement++;

            return new PaiementResponse(
                numeroPaiement,
                "ACCEPTE",
                soldeResponse.getMessage()
            );
        }

        return new PaiementResponse(
            null,
            "REJETE",
            soldeResponse.getMessage()
        );
    }
}