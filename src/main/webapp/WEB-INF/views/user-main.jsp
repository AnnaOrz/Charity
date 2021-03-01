<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <title>UserMenu</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" type="text/css">
</head>

<body>
<section class="user">
<ul class="nav--bar">
    <li><a href='<c:url value="/" />'>Powrót do strony głównej</a></li>
    <li><a href='<c:url value="/user/edit"/>'>Edytuj swoje dane</a> </li>
    <li> <a href='<c:url value="/donation"/>'>Dodaj nową darowiznę</a></li>
</ul>

<div> ${info}</div>
<h2>Twoje darowizny</h2>
<table>
    <thead>
    <tr>
        <th scope="col">Instytucja </th>
        <th scope="col">Data odbioru</th>
        <th scope="col"> Odebrane </th>
    </tr>
    </thead>
    <c:forEach items="${donations}" var="donation">
    <tr>
        <td>${donation.institution.name}</td>
        <td>${donation.pickUpDate}</td>
        <td>${donation.collected}</td>
        <td> <a href ='<c:url value="/user/donationDetails/${donation.id}"/>'> Wyświetl szczegóły </a> </td>

    </tr>
    </c:forEach>
</section>
</body>
</html>
