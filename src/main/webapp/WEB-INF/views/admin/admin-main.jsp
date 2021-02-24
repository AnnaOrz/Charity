<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>ADMIN MENU</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>

</head>
<body>
<a href='<c:url value="/admin/users" />'>Użytkownicy</a>
<a href='<c:url value="/admin/admins" />'>Admini</a>
<a href='<c:url value="/admin/institutions" />'>Fundacje</a>
<a href='<c:url value="/admin/donations" />'>Dary</a>
</body>
</html>


