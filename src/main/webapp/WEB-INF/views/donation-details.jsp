<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Szczegóły darowizny</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" type="text/css">
</head>
<body>
<h1>Szczegóły darowizny</h1>
<div>
<a href="<c:url value="/logged"/>">Powrót do: moje konto</a>
</div>

    <div class="grid-container">
            <div class="institution"> Darowizna dla:  ${donation.institution.name} </div>
            <div class="bags"> Ilość worków: ${donation.quantity}
                <c:forEach items="${donation.categories}" var="category">
                    <p class="category"> ${category.name} </p> </c:forEach>
            </div>

            <div class="pickUpDateTime"> Odbiór: ${donation.pickUpDate} o ${donation.pickUpTime}</div>
            <div class="pickUpAdress"> ${donation.street} ${donation.zipCode} ${donation.city} </div>
            <div class="collected"> Data potwierdzenia odbioru: ${donation.collectionSubmitted.toLocalDate()}
        <form method="post">
            <label>
                <input type="hidden" value="${donation.id}" />
            </label>
            <button type="submit">Potwierdź odbiór</button>
        </form>
            </div>
    </div>


    <script src="<c:url value="/resources/js/app.js" />" type="text/javascript"></script>
</body>
</html>
