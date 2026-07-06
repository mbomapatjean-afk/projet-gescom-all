<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Commande enregistrée">
    <h2>Commande enregistrée avec succès</h2>

    <c:if test="${not empty error}">
        <div style="color: red; margin-bottom: 20px;">
            ${error}
        </div>
    </c:if>

    <div class="recap">
        <p>
            Date :
            ${commande.dateCommande}
        </p>
        <p>
            Numéro :
            ${commande.numCommande}
        </p>

        <p>
            Montant à payer :
            ${commande.montantCommande} Fr CFA
        </p>
    </div>

    <form action="paiement" method="get">
        <input type="hidden" name="numCommande" value="${commande.numCommande}" />

        <button type="submit">
            Effectuer le paiement
        </button>
    </form>
    <form action="./" method="get">
        <input type="hidden" name="idClient" value="${idClient}">
        <input type="hidden" name="nomClient" value="${nomClient}">
        <input type="hidden" name="emailClient" value="${emailClient}">
        <button type="submit" style="background-color: var(--orange);">Payer plus tard (Retour Accueil)</button>
    </form>
</t:layout>
