<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Users</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <%--<link rel="stylesheet" href="<c:url value="resources/css/style.css"/>" type="text/css">--%>
</head>
<body>

<%@ include file="admin-header.jsp" %>
<div> ${info}</div>
<table class="table table-light table-striped">
    <thead>
    <tr>
        <th scope="col">Edycja użytkownika</th>

        <th scope="col"> Usuń</th>
        <th scope="col">Id w bazie</th>
        <th scope="col">Email</th>
        <th scope="col">Imię</th>
        <th scope="col">Nazwisko</th>
        <th scope="col">Role</th>
        <th scope="col">Dostęp</th>
        <th scope="col"></th>
    </tr>
    </thead>

    <c:forEach items="${users}" var="user">
    <tr>
        <td><a href='<c:url value="/admin/editUser/${user.id}"/>'>Edytuj</a></td>
        <td><a href='<c:url value="/admin/deleteUser/${user.id}"/>'>Usuń</a></td>
        <th scope="row">${user.id}</th>
        <td>${user.email}</td>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.roles}</td>
        <td>${user.enabled}</td>
        <td><a href='<c:url value="/admin/disable/${user.id}"/>'>Zablokuj</a></td>
    </tr>
    </c:forEach>

</body>
</html>

