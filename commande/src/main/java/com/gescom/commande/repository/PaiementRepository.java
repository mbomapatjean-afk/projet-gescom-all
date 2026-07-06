package com.gescom.commande.repository;

import com.gescom.commande.modele.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
}