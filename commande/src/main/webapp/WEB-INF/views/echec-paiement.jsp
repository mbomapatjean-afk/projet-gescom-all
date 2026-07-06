<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Résultat paiement">
    <h2> Paiement Réfusé</h2>

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

        <p>
            Message :
                ${message}
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

    <form action="${pageContext.request.contextPath}/paiement" method="get">
        <input type="hidden" name="numCommande" value="${commande.numCommande}">
        <input type="submit" value="Rééssayez" style="width: 100%;">
    </form>

    <form action="./" method="get">
        <input type="hidden" name="idClient" value="${idClient}">
        <input type="hidden" name="nomClient" value="${nomClient}">
        <input type="hidden" name="emailClient" value="${emailClient}">
        <input type="submit" value="Retour Accueil"  style="background-color: var(--orange); margin-top: 20px; width: 100%;">
    </form>
</t:layout>