<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Rejestracja</title>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>" type="text/css">
</head>
<body>
<%@ include file="header.jsp" %>

<section class="login-page">
    <h2>Załóż konto</h2>
<form:form method="post" modelAttribute="user">
        <div class="form-group">
            <form:input path="email" value="${user.email}"  name="email" placeholder="Email" />
            <form:errors path="email"/>
    </div>
        <div class="form-group">
            <form:input path="password" type="password" name="password" placeholder="Hasło" minlength="8"/>
            <form:errors path="password"/>
        </div>
        <div class="form-group">
            <form:input path="passwordConfirm" type="password" name="password2" placeholder="Powtórz hasło" minlength="8"/>
        </div>
    <div class="form-group">
        <form:input path="firstName"  name="firstName" placeholder="Imię" />
    </div>
    <div class="form-group">
        <form:input path="lastName" name="lastName" placeholder="Nazwisko" />
    </div>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/login"/>" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>

</form:form>>
</section>

<%@ include file="footer.jsp" %>


<script src="<c:url value="resources/js/app.js"/>"></script>
</body>
</html>
