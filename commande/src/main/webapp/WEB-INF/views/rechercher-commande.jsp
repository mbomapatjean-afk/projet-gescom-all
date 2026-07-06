<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<t:layout title="Paiement commande">
    <h2>Paiement d'une commande</h2>

    <c:if test="${not empty message}">
        <p class="welcome-msg" style="color: var(--orange);">Veuillez saisir le numéro de votre commande</p>
    </c:if>
    <c:if test="${not empty error}">
        <p class="welcome-msg" style="color: red;">${error}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/rechercher" method="get">
        <label for="numCommande">Numéro commande :</label>
        <input type="text" id="numCommande" name="numCommande" value="${param.numCommande}" placeholder="Saisir le numéro de commande" required>
        <input type="submit" value="Rechercher">
    </form>
    <br>
    <c:if test="${not empty commande}">
        <div class="recap success-recap">
            <h3>Détails de la commande</h3>
            <p>
                <strong>Date :</strong>
                <fmt:parseDate value="${commande.dateCommande}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                <fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy" />
            </p>
            <p>
                <strong>Numéro :</strong>
                    ${commande.numCommande}
            </p>

            <p>
                <strong>Montant à payer :</strong>
                    ${commande.montantCommande} Fr CFA
            </p>
            <form action="${pageContext.request.contextPath}/paiement" method="get">
                <input type="hidden" name="numCommande" value="${commande.numCommande}" />
                <button type="submit" style="width: 100%;">
                    Procéder au paiement
                </button>
            </form>
        </div>
    </c:if>

    <form action="./" method="get">
        <input type="hidden" name="idClient" value="${idClient}">
        <input type="hidden" name="nomClient" value="${nomClient}">
        <input type="hidden" name="emailClient" value="${emailClient}">
        <button type="submit" style="background-color: var(--orange); width: 100%;">
            Retour Accueil
        </button>
    </form>
</t:layout>
