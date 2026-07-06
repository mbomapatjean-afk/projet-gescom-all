package com.gescom.document.controller;

import com.gescom.document.modele.Document;
import com.gescom.document.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;

@Controller
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("")
    public ResponseEntity<byte[]> getDocument(
            @RequestParam("numCommande") String numCommande,
            @RequestParam("nomClient") Long idClient, // nomClient dans le formulaire est ${commande.client.idClient}
            @RequestParam("montant") Double montant,
            @RequestParam("numPaiement") String numPaiement,
            @RequestParam("datePaiement") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePaiement,
            @RequestParam("messageNotif") String messageNotif) throws IOException {

        // Persister les informations en base
        Document document = new Document();
        document.setNumCommande(numCommande);
        document.setIdClient(idClient);
        document.setMontantPaiement(montant);
        document.setNumPaiement(numPaiement);
        document.setDatePaiement(datePaiement);
        document.setMessageNotif(messageNotif);
        
        documentService.saveDocument(document);

        // Générer le PDF
        byte[] pdfBytes = documentService.generateInvoicePdf(document, messageNotif);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=facture_" + numCommande + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
