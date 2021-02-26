<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Logowanie</title>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>" type="text/css">
</head>
<body>
<%@ include file="header.jsp" %>

<section class="login-page">
    <h2>Zaloguj się</h2>
    <form method="post" >
        <div class="form-group">
            <input type="text" name="username" id="username" placeholder="Email" />
                </div>
        <div class="form-group">
            <input type="password" name="password" id="password" placeholder="Hasło" />
            <a href=href="<c:url value="/resetPassword"/>" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
        </div>


        <div class="form-group form-group--buttons">
            <a href="<c:url value="/register"/>" class="btn btn--without-border">Załóż konto</a>
            <button class="btn" type="submit">Zaloguj się</button>
        </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</section>
<%@ include file="footer.jsp" %>


<script src="<c:url value="resources/js/app.js"/>"></script>

</body>
</html>