package com.gescom.document.service;

import com.gescom.document.modele.Document;
import com.gescom.document.repository.DocumentRepository;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document saveDocument(Document document) {
        return documentRepository.save(document);
    }

    public byte[] generateInvoicePdf(com.gescom.document.modele.Document documentData, String messageNotif) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            com.lowagie.text.Document pdfDoc = new com.lowagie.text.Document(PageSize.A4);
            PdfWriter.getInstance(pdfDoc, baos);

            pdfDoc.open();

            // Ajouter le logo
            try {
                Path path = Paths.get("src/main/webapp/static/images/logo.jpeg");
                Image logo = Image.getInstance(path.toAbsolutePath().toString());
                logo.setAlignment(Element.ALIGN_CENTER);
                logo.scaleToFit(100, 100); // Ajuster la taille du logo
                pdfDoc.add(logo);
            } catch (Exception e) {
                System.err.println("Logo non trouvé : " + e.getMessage());
            }

            // Style
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, Font.BOLD);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            // Titre
            Paragraph title = new Paragraph("FACTURE DE PAIEMENT", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(30);
            pdfDoc.add(title);

            // Section Réussi
            Paragraph success = new Paragraph("Paiement Réussi", headerFont);
            success.setSpacingAfter(10);
            pdfDoc.add(success);

            // Informations Commande
            pdfDoc.add(new Paragraph("Informations Commande", headerFont));
            pdfDoc.add(new Paragraph("Numéro Commande : " + documentData.getNumCommande(), normalFont));
            pdfDoc.add(new Paragraph("ID Client : " + documentData.getIdClient(), normalFont));
            pdfDoc.add(new Paragraph("Montant : " + documentData.getMontantPaiement() + " CFA", normalFont));

            pdfDoc.add(new Paragraph(" ", normalFont)); // Espacement

            // Détails du Paiement
            pdfDoc.add(new Paragraph("Détails du Paiement", headerFont));
            pdfDoc.add(new Paragraph("Numéro Paiement : " + documentData.getNumPaiement(), normalFont));
            pdfDoc.add(new Paragraph("Date Paiement : " + documentData.getDatePaiement(), normalFont));
            pdfDoc.add(new Paragraph("Notification : " + messageNotif, normalFont));

            pdfDoc.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new IOException("Erreur lors de la génération du PDF", e);
        }
    }
}
