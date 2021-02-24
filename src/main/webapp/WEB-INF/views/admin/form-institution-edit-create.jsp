<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Dane fundacji</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" type="text/css">
</head>
<body>

<section class="login-page">
    <h2>Dane instytucji</h2>
    <form:form method="post" modelAttribute="institution">
        <div class="form-group">
            <form:input path="name" value="${institution.name}"  placeholder="Nazwa" />
            <form:errors path="name"/>
        </div>
        <div class="form-group">
            <form:input path="description" placeholder="Opis" />
            <form:errors path="description"/>
        </div>
        <div class="form-group">
            <form:input path="id" value="${institution.id}" type="hidden" />
        </div>


        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Zatwierdź</button>
        </div>

    </form:form>
</section>
</body>
</html>
