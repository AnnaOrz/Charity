<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Edycja użytkownika</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" type="text/css">
</head>
<body>
<section class="login-page">
    <h2>Edycja użytkownika</h2>
<form:form method="post" modelAttribute="user">
        <div class="form-group">
            <form:input path="email" value="${user.email}"  name="email" placeholder="Email" />
            <form:errors path="email"/>
    </div>
        <div class="form-group">
            <form:input path="password" type="password" value="${user.password}" name="password" placeholder="Hasło" />
            <form:errors path="password"/>
        </div>
        <div class="form-group">
            <form:input path="passwordConfirm" type="password" value="${user.password}" name="password2" placeholder="Powtórz hasło" />
        </div>
    <div class="form-group">
        <form:input path="firstName"  name="firstName" value="${user.firstName}" placeholder="Imię" />
    </div>
    <div class="form-group">
        <form:input path="lastName" name="lastName" value="${user.lastName}"  placeholder="Nazwisko" />
    </div>
    <form:input path="id" value="${user.id}" type="hidden" />
    <form:input path="enabled" value="${user.enabled}"  type="hidden"  />
    <form:input path="tokenExpired" value="${user.tokenExpired}" type="hidden"/>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Potwierdź edycję</button>
        </div>
</form:form>
</section>
</body>
</html>
