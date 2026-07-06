<%@ tag description="Main Layout Template" pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title} - GESCOM</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
<div class="container">
    <div class="header-container">
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/static/images/logo.jpeg" alt="Logo GESCOM" class="logo">
        </div>

        <div class="info-client">
            <form action="login" method="get">
                <button type="submit" class="btn-user" title="Se déconnecter">
                    <i class="fa-solid fa-user"></i>
                    <strong>${nomClient != null ? nomClient : ""}</strong>
                </button>
            </form>
        </div>
    </div>

    <jsp:doBody/>
</div>
</body>
</html>
