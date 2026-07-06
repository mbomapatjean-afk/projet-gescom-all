<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Saisie de commande">
    <h2>Saisie de commande</h2>
    <div class="produit-selection"><!-- Section Ajout Produit -->
        <div class="flex-grow">
            <label>Produit :</label>
            <select id="selectProduit">
                <option value="" data-prix="0">-- Choisir un produit --</option>
                <c:forEach items="${produits}" var="produit">
                    <option value="${produit.idProduit}" data-prix="${produit.prixUnitProduit}" data-nom="${produit.nomProduit}">
                            ${produit.nomProduit} (${produit.prixUnitProduit != null ? produit.prixUnitProduit : '0.00'})
                    </option>
                </c:forEach>
            </select>
        </div>
        <div>
            <label>Quantité :</label>
            <input type="number" id="inputQuantite" value="1" min="1" style="width: 80px;"/>
        </div>
        <button type="button" id="btnAjouter" class="btn-add">
            <i class="fa-solid fa-plus"></i>
        </button>
    </div>

    <form action="saveCommande" method="post" id="formCommande"> <!-- Panier -->
        <div class="panier-container">
            <h3>Votre Panier</h3>
            <table class="panier-table">
                <thead>
                <tr>
                    <th>Produit</th>
                    <th>Prix</th>
                    <th>Qté</th>
                    <th>Total</th>
                    <th></th>
                </tr>
                </thead>
                <tbody id="panierBody"><!-- Lignes ajoutées dynamiquement -->             
                </tbody>
            </table>
            <div class="total-container">
                Total : <span id="montantTotal">0.00</span>
            </div>
        </div>

        <input type="hidden" name="idClient" value="${idClient}">
        <input type="hidden" name="nomClient" value="${nomClient}">
        <input type="hidden" name="emailClient" value="${emailClient}">
        <input type="hidden" name="montantTotal" id="hiddenMontantTotal" value="${montantTotal}">
        <input type="submit" value="Valider la commande" style="margin-top: 20px;"/>
    </form>

    <form action="./" method="get">
        <input type="hidden" name="idClient" value="${idClient}">
        <input type="hidden" name="nomClient" value="${nomClient}">
        <input type="hidden" name="emailClient" value="${emailClient}">
        <button type="submit" style="background-color: var(--orange); margin-top: 20px; width: 100%;">
            Retour Accueil
        </button>
    </form>

    <script src="${pageContext.request.contextPath}/static/js/commande.js"></script>
</t:layout>