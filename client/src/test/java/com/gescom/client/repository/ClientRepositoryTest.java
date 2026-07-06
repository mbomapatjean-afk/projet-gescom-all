package com.gescom.client.repository;

import com.gescom.client.modele.Client;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ClientRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(ClientRepositoryTest.class);

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testSaveClient() {
        // Given
        Client client = new Client();
        client.setNomClient("Jean Dupont");
        client.setEmailClient("jean.dupont@example.com");
        client.setNumTelClient("0123456789");

        // When
        Client savedClient = clientRepository.save(client);

        // Then

        assertThat(savedClient).isNotNull();
        assertThat(savedClient.getIdClient()).isNotNull();
        assertThat(savedClient.getNomClient()).isEqualTo("Jean Dupont");

        boolean isNotNull = savedClient != null;
        boolean idNotNull = savedClient != null && savedClient.getIdClient() != null;
        boolean nameMatches = savedClient != null && "Jean Dupont".equals(savedClient.getNomClient());

        log.info("[TEST STATUS] \n"+
                        "\n" +
                        "        assertThat(savedClient).isNotNull() == {}\n" +
                        "        assertThat(savedClient.getIdClient()).isNotNull() == {}\n" +
                        "        assertThat(savedClient.getNomClient()).isEqualTo(\"Jean Dupont\") == {}",
                isNotNull,
                idNotNull,
                nameMatches);

    }
}
