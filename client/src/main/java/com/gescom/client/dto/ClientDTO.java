package com.gescom.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Long idClient;
    private String nomClient;
    private String emailClient;
    private String numTelClient;
}
