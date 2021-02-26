<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <title>UserMenu</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
</head>

<body>
<div> <a href='<c:url value="/user/edit"/>'>Edytuj swoje dane</a> </div>
<div> ${info}</div>
<div> <a href='<c:url value="/donation"/>'>Dodaj nową darowiznę</a> </div>

<table class="table table-light table-striped">
    Twoje darowizny
    <thead>
    <tr>
        <th scope="col">Instytucja </th>
        <th scope="col">Ilość worków </th>
        <th scope="col">Data i czas odbioru</th>
        <th scope="col">Adres odbioru </th>
        <th scope="col"> Odebrane </th>

    </tr>
    </thead>

    <c:forEach items="${donations}" var="donation">
    <tr>
        <th scope="row">${donation.institution.name}</th>
        <td>${donation.pickUpDate} o ${donation.pickUpTime}</td>
        <td>${donation.street}, ${donation.city} , ${donation.zipCode}</td>
        <td>${donation.collected}</td>
        <td> <c:forEach items="${donation.categories}" var="category"> ${category.name}, </c:forEach></td>
    </tr>
    </c:forEach>

</body>
</html>
