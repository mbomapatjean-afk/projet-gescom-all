package com.gescom.client.repository;

import com.gescom.client.modele.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByEmailClient(String emailClient);
    java.util.Optional<Client> findByEmailClient(String emailClient);
}
