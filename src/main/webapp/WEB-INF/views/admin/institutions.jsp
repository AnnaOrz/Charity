<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Institutions</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>

</head>
<body>

<%@ include file="admin-header.jsp" %>
<div> ${info}</div>
<div> <a href='<c:url value="/admin/addInstitution"/>'>Dodaj nową instytucję</a> </div>
<table class="table table-light table-striped">
    <thead>
    <tr>
        <th scope="col"></th>
        <th scope="col"></th>
        <th scope="col">Id</th>
        <th scope="col">Nazwa</th>
        <th scope="col">Opis</th>

    </tr>
    </thead>

    <c:forEach items="${institutions}" var="institution">
    <tr>
        <td><a href='<c:url value="/admin/editInstitution/${institution.id}"/>'>Edytuj</a></td>
        <td><a href='<c:url value="/admin/deleteInstitution/${institution.id}"/>'>Usuń</a></td>
        <th scope="row">${institution.id}</th>
        <td>${institution.name}</td>
        <td>${institution.description}</td>
    </tr>
    </c:forEach>

</body>
</html>

