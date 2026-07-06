<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<t:layout title="Accueil">
    <c:if test="${not empty notif}">
        <div style="background-color: #d4edda; color: #155724; padding: 10px; border-radius: 5px; margin-bottom: 20px; text-align: center;">
            ${notif}
        </div>
    </c:if>
    <h2>Bienvenue dans l'application GESCOM</h2>
    <p class="welcome-msg">Veuillez choisir une action :</p>

    <div style="display: flex; flex-direction: column;">
        <form action="commande" method="post">
            <input type="hidden" name="idClient" value="${idClient}">
            <input type="hidden" name="nomClient" value="${nomClient}">
            <input type="hidden" name="emailClient" value="${emailClient}">
            <input type="submit" value="Nouvelle commande" style="width: 100%;">
        </form>

        <form action="rechercher" method="get">
            <input type="hidden" name="idClient" value="${idClient}">
            <input type="hidden" name="nomClient" value="${nomClient}">
            <input type="hidden" name="emailClient" value="${emailClient}">
            <input type="submit" value="Rechercher commande"  style="background-color: var(--orange); margin-top: 20px; width: 100%;">
        </form>
    </div>
</t:layout>
