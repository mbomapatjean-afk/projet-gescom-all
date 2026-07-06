package com.gescom.client.mapper;

import com.gescom.client.dto.ClientDTO;
import com.gescom.client.modele.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDTO toDTO(Client client) {
        if (client == null) {
            return null;
        }
        ClientDTO dto = new ClientDTO();
        dto.setIdClient(client.getIdClient());
        dto.setNomClient(client.getNomClient());
        dto.setEmailClient(client.getEmailClient());
        dto.setNumTelClient(client.getNumTelClient());
        return dto;
    }

    public Client toEntity(ClientDTO dto) {
        if (dto == null) {
            return null;
        }
        Client client = new Client();
        client.setIdClient(dto.getIdClient());
        client.setNomClient(dto.getNomClient());
        client.setEmailClient(dto.getEmailClient());
        client.setNumTelClient(dto.getNumTelClient());
        return client;
    }
}
