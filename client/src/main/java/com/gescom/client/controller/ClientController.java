package com.gescom.client.controller;

import com.gescom.client.dto.ClientDTO;
import com.gescom.client.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/managment")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO create(@RequestBody ClientDTO clientDTO) {
        return clientService.create(clientDTO);
    }

    @PostMapping(value = "/saveClient", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveClient(@ModelAttribute ClientDTO clientDTO) {
        clientService.saveClient(clientDTO);
    }

    @GetMapping("/{id}")
    public ClientDTO findById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @GetMapping
    public List<ClientDTO> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/getIdClient")
    public ClientDTO getIdClient(@RequestParam String email) {
        return clientService.findByEmailClient(email)
                .orElse(null);
    }
}
