<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Résultat paiement">
    <h2>Paiement Réussi</h2>

    <div class="recap">
        <p>
            Commande :
            ${commande.numCommande}
        </p>

        <p>
            Client :
            ${commande.client.nomClient}
        </p>

        <p>
            Montant :
            ${commande.montantCommande}
        </p>

    </div>

    <c:if test="${paiement != null}">
        <div class="recap" style="border-top: 1px solid var(--glass-border); padding-top: 10px;">
            <p>
                Numéro paiement :
                    ${paiement.numPaiement}
            </p>

            <p>
                Date paiement :
                    ${paiement.datePaiement}
            </p>

            <p>
                Notification :
                    ${paiement.notif.messageNotif}
            </p>
        </div>
    </c:if>

    <form action="http://localhost:8086/gescom/document" method="get">
        <input type="hidden" name="numCommande" value="${commande.numCommande}">
        <input type="hidden" name="nomClient" value="${commande.client.idClient}">
        <input type="hidden" name="montant" value="${paiement.montantPaiement}">
        <input type="hidden" name="numPaiement" value="${paiement.numPaiement}">
        <input type="hidden" name="datePaiement" value="${paiement.datePaiement}">
        <input type="hidden" name="messageNotif" value="${paiement.notif.messageNotif}">
        <input type="submit" value="Imprimer Facture"  style="background-color: #999;; margin-top: 20px; width: 100%;">
    </form>
    <form action="${pageContext.request.contextPath}/commande" method="get">
        <input type="submit" value="Nouvelle commande" style="width: 100%;">
    </form>

    <form action="http://localhost:8079/gescom/home" method="get">
        <input type="submit" value="Retour Accueil"  style="background-color: var(--orange); margin-top: 20px; width: 100%;">
    </form>
</t:layout>