package com.gescom.commande.restclient;

import com.gescom.commande.dto.PaiementRequest;
import com.gescom.commande.dto.PaiementResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class PaiementRestClient {

    private final RestClient restClient;

    public PaiementRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public PaiementResponse effectuerPaiement(PaiementRequest request) {

        try {
            return restClient.post()
                    .uri("http://localhost:8082/paiement/api/paiements")
                    .body(request)
                    .retrieve()
                    .body(PaiementResponse.class);
        } catch (RestClientException e) {
            PaiementResponse errorResponse = new PaiementResponse();
            errorResponse.setStatut("ERREUR");
            errorResponse.setMessage("Le service de paiement est indisponible (404 ou erreur de connexion).");
            return errorResponse;
        }
    }
}
