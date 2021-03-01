<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anda
  Date: 25.02.2021
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" type="text/css">

<body>
<ul class="nav--bar">
    <li><a href='<c:url value="/" />'>Powrót do strony głównej</a></li>
    <li><a href='<c:url value="/admin/users" />'>Użytkownicy</a></li>
    <li><a href='<c:url value="/admin/admins" />'>Admini</a></li>
    <li><a href='<c:url value="/admin/institutions" />'>Fundacje</a></li>
    <li><a href='<c:url value="/admin/donations" />'>Dary</a></li>
</ul>
</body>
</html>
