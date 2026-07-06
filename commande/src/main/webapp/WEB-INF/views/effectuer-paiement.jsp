<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Effectuer paiement">
    <h2>Effectuer le paiement</h2>

    <c:if test="${not empty error}">
        <p class="welcome-msg" style="color: red;">${error}</p>
    </c:if>

    <c:if test="${not empty commande}">
        <div class="recap" style="margin-top: 20px;">
            <h3>Détails de la commande</h3>

            <p>
                <strong>Numéro :</strong>
                ${commande.numCommande}
            </p>

            <p>
                <strong>Client :</strong>
                ${commande.client.nomClient}
            </p>

            <p>
                <strong>Montant :</strong>
                ${commande.montantCommande}
            </p>
        </div>

        <h3>Informations de paiement</h3>

        <form action="${pageContext.request.contextPath}/paiement/valider" method="post">

            <input type="hidden" name="idCommande" value="${commande.idCommande}" />

            <label>Numéro de carte :</label>
            <input type="text" name="numeroCarte" placeholder="XXXX XXXX XXXX XXXX" required />

            <label>Nom du titulaire :</label>
            <input type="text" name="nomTitulaire"
                   value="${commande.client.nomClient}" placeholder="Nom complet" required />

            <label>Date d'expiration :</label>
            <input type="text" name="dateExpiration"
                   placeholder="JJ/MM/YYYY" required />

            <button type="submit">
                Valider le paiement
            </button>

        </form>
    </c:if>

    <div style="display: flex; gap: 10px;">
        <form action="${pageContext.request.contextPath}/rechercher" method="get" style="flex: 1;">
            <button type="submit" style="background-color: var(--orange); margin-top: 20px; width: 100%;">
                Rechercher un paiement
            </button>
        </form>

        <form action="./" method="get" style="flex: 1;">
            <input type="hidden" name="idClient" value="${idClient}">
            <input type="hidden" name="nomClient" value="${nomClient}">
            <input type="hidden" name="emailClient" value="${emailClient}">
            <button type="submit" style="background-color: var(--orange); margin-top: 20px; width: 100%;">
                Retour Accueil
            </button>
        </form>
    </div>
</t:layout>
