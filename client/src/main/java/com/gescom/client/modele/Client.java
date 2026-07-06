package com.gescom.client.modele;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENT")
    private Long idClient;

    @Column(name = "NOM_CLIENT")
    private String nomClient;

    @Column(name = "EMAIL_CLIENT", unique = true)
    private String emailClient;

    @Column(name = "TELEPHONE_CLIENT")
    private String numTelClient;

}