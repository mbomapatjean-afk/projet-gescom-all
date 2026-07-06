package com.gescom.client.service;

import com.gescom.client.dto.ClientDTO;
import com.gescom.client.exception.ClientAlreadyExistsException;
import com.gescom.client.exception.NotClientException;
import com.gescom.client.mapper.ClientMapper;
import com.gescom.client.modele.Client;
import com.gescom.client.repository.ClientRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;


    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public ClientDTO create(ClientDTO clientDTO) {
        checkIfClientExistsByEmail(clientDTO.getEmailClient());
        Client client = clientMapper.toEntity(clientDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toDTO(savedClient);
    }

    @Transactional
    public Client saveClient(ClientDTO clientDTO) {
        Client client = createEntity(clientDTO);
        //envoyer un email de confirmation
        return client;
    }

    private Client createEntity(ClientDTO clientDTO) {
        checkIfClientExistsByEmail(clientDTO.getEmailClient());
        Client client = clientMapper.toEntity(clientDTO);
        return clientRepository.save(client);
    }

    public ClientDTO findById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDTO)
                .orElseThrow(() -> new NotClientException("Aucun client trouvé avec l'ID " + id));
    }

    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }
    public Optional<ClientDTO> findByEmailClient(String emailClient) {
        return clientRepository.findByEmailClient(emailClient)
                .map(clientMapper::toDTO);
    }
    
    private void checkIfClientExistsByEmail(String email) {
        if (clientRepository.existsByEmailClient(email)) {
            throw new ClientAlreadyExistsException("Un client avec l'email " + email + " existe déjà.");
        }
    }
}
