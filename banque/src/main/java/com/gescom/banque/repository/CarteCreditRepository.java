package com.gescom.banque.repository;

import com.gescom.banque.modele.CarteCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarteCreditRepository extends JpaRepository<CarteCredit, Long> {
    Optional<CarteCredit> findByNumCarte(String numCarte);
}
