let panier = [];

function majPrixUnitaire() {
    const select = document.getElementById('selectProduit');
    const option = select.options[select.selectedIndex];
    const prix = option.getAttribute('data-prix');
    // Note: 'inputPrix' seems to be missing in the JSP but kept for consistency with original script
    const inputPrix = document.getElementById('inputPrix');
    if (inputPrix) {
        inputPrix.value = prix ? parseFloat(prix).toFixed(2) : "0.00";
    }
}

function ajouterAuPanier() {
    const select = document.getElementById('selectProduit');
    if (!select) return;
    const option = select.options[select.selectedIndex];
    const idProduit = select.value;

    if (!idProduit) {
        alert("Veuillez choisir un produit");
        return;
    }

    const nomProduit = option.getAttribute('data-nom');
    const prix = parseFloat(option.getAttribute('data-prix'));
    const quantite = parseInt(document.getElementById('inputQuantite').value);

    // Vérifier si le produit est déjà dans le panier
    const index = panier.findIndex(item => item.idProduit === idProduit);
    if (index > -1) {
        panier[index].quantite += quantite;
    } else {
        panier.push({ idProduit, nomProduit, prix, quantite });
    }

    majAffichage();
}

function supprimerDuPanier(index) {
    panier.splice(index, 1);
    majAffichage();
}

function majAffichage() {
    const body = document.getElementById('panierBody');
    if (!body) return;
    body.innerHTML = '';
    let totalGeneral = 0;

    panier.forEach((item, index) => {
        const totalLigne = item.prix * item.quantite;
        totalGeneral += totalLigne;

        const row = `
            <tr>
                <td>
                    ${item.nomProduit}
                    <input type="hidden" name="idProduit[]" value="${item.idProduit}">
                </td>
                <td>${item.prix.toFixed(2)}</td>
                <td>
                    ${item.quantite}
                    <input type="hidden" name="quantiteCommande[]" value="${item.quantite}">
                    <input type="hidden" name="prixUnitProduit[]" value="${item.prix}">
                </td>
                <td>${totalLigne.toFixed(2)}</td>
                <td>
                    <button type="button" class="btn-remove" onclick="supprimerDuPanier(${index})">
                        <i class="fa-solid fa-trash"></i>
                    </button>
                </td>
            </tr>
        `;
        body.innerHTML += row;
    });

    const montantTotal = document.getElementById('montantTotal');
    const hiddenMontantTotal = document.getElementById('hiddenMontantTotal');
    if (montantTotal) {
        montantTotal.innerText = totalGeneral.toFixed(2);
    }
    if (hiddenMontantTotal) {
        hiddenMontantTotal.value = totalGeneral.toFixed(2);
    }
}

document.addEventListener('DOMContentLoaded', function() {
    const selectProduit = document.getElementById('selectProduit');
    if (selectProduit) {
        selectProduit.addEventListener('change', majPrixUnitaire);
    }

    const btnAjouter = document.getElementById('btnAjouter');
    if (btnAjouter) {
        btnAjouter.addEventListener('click', ajouterAuPanier);
    }

    const formCommande = document.getElementById('formCommande');
    if (formCommande) {
        formCommande.addEventListener('submit', function(e) {
            if (panier.length === 0) {
                alert("Votre panier est vide !");
                e.preventDefault();
            }
        });
    }
});
