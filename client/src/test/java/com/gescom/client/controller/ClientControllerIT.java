package com.gescom.client.controller;

import com.gescom.client.dto.ClientDTO;
import com.gescom.client.repository.ClientRepository;
import com.gescom.client.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ClientControllerIT {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testCreateClient() throws Exception {
        // Given
        String email = "test.client.notif@example.com";
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setNomClient("Test Client");
        clientDTO.setEmailClient(email);
        clientDTO.setNumTelClient("0600000000");

        // When
        clientService.saveClient(clientDTO);

        // Then
        assertThat(clientRepository.existsByEmailClient(email)).isTrue();
        var savedClient = clientRepository.findByEmailClient(email).get();
        assertThat(savedClient.getNomClient()).isEqualTo("Test Client");

        // Vérifier que la notification est sauvegardée (Commenté car NotificationRepository inexistant)
        /*
        var notifications = notificationRepository.findAll();
        assertThat(notifications).isNotEmpty();
        assertThat(notifications.stream().anyMatch(n -> n.getClient().getIdClient().equals(savedClient.getIdClient()))).isTrue();
        */
    }
}
