package com.gescom.commande.repository;

import com.gescom.commande.modele.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    Optional<Commande> findByNumCommande(String numCommande);
}
