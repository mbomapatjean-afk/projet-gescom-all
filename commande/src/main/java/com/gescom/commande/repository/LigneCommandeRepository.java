package com.gescom.commande.repository;

import com.gescom.commande.modele.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {

}