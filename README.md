# PROJET_GESCOM - Système de Gestion Commerciale

Ce projet est un ensemble de microservices Spring Boot conçus pour gérer un système de gestion commerciale complet. Chaque module remplit une fonction spécifique, de l'authentification à la gestion des commandes, des produits et des paiements.

## Architecture du Projet

Le projet est divisé en plusieurs microservices autonomes :

- **gescom.auth** : Gère l'authentification des utilisateurs et la gestion des comptes clients.
- **accueil** : Module frontal servant de point d'entrée après connexion.
- **produit** : Gère le catalogue des produits, les stocks et les catégories.
- **commande** : Responsable du cycle de vie des commandes clients.
- **paiement** : Gère les transactions de paiement des commandes.
- **banque** : Un service simulateur pour la vérification des soldes et les transactions bancaires.
- **notification** : Service centralisé pour l'envoi de notifications (notamment par e-mail via SMTP).
- **document** : Gestion des documents liés aux transactions (factures, bons de commande).

## Technologies utilisées

- **Langage** : Java 21
- **Framework** : Spring Boot 3.x / 4.x
- **Gestion de dépendances** : Gradle
- **Persistance** : Spring Data JPA
- **Base de données** : MySQL / Microsoft SQL Server
- **Vues** : JSP (JavaServer Pages) avec JSTL
- **Documentation API** : SpringDoc OpenAPI (Swagger) sur certains modules

## Configuration Requise

- **Java JDK 21** installé.
- **MySQL** tournant localement (ou sur un serveur accessible).
- Une base de données nommée `gescom_db` doit être créée.

## Installation et Lancement

Chaque module est indépendant et possède son propre fichier de configuration `application.yml` ou `application.yaml`.

### 1. Configuration de la base de données
Assurez-vous que MySQL est actif et créez la base de données :
```sql
CREATE DATABASE gescom_db;
```

### 2. Lancement des services
Vous pouvez lancer chaque microservice individuellement à l'aide de Gradle. Par exemple, pour lancer le service d'authentification :

```bash
cd gescom.auth
./gradlew bootRun
```

Répétez l'opération pour les autres modules nécessaires.

### 3. Ports par défaut
Voici les ports configurés par défaut pour les différents services :
- **gescom.auth** : 8079
- **commande** : 9080
- **accueil** : 9081
- **paiement** : 8082
- **produit** : 9083
- **banque** : 9084
- **notification** : 9088

## Points de Terminaison (API)
Certains services exposent une documentation Swagger. Vous pouvez y accéder via :
`http://localhost:[PORT]/[CONTEXT-PATH]/swagger-ui/index.html`

## Auteurs
Ce projet a été réalisé dans le cadre d'une formation Spring Boot avec M. MOLIERE 
