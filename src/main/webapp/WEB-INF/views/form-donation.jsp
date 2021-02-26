<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Formularz darowizny 123</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" type="text/css">
</head>
<body>
<%@ include file="header.jsp" %>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="1" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="2">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="3">
                Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
            <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <form:form method="post" modelAttribute="donation">
        <!-- STEP 1: class .active is switching steps -->
        <div data-step="1" class="active">
            <h3>Zaznacz co chcesz oddać:</h3>

                <div class="form-group form-group--checkbox">
                    <c:forEach items="${categories}" var="category">
                        <label>
                           <input type="checkbox" name="categories" value="${category.id}" data-desc="${category.name}"/>
                            <span class="checkbox"></span>
                        <span class="description"> ${category.name}</span>
                    </label>
                    </c:forEach>
                </div>


            <div class="form-group form-group--buttons">
                <button type="button" class="btn next-step">Dalej</button>
            </div>
        </div>

        <!-- STEP 2 -->
        <div data-step="2">
            <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

            <div class="form-group form-group--inline">
                <label>
                    <form:input path="quantity" type="number" step="1" min="1" name="quantity" required="true"  />
                </label>
            </div>

            <div class="form-group form-group--buttons">
                <button type="button" class="btn prev-step">Wstecz</button>
                <button type="button" class="btn next-step">Dalej</button>
            </div>
        </div>


        <!-- STEP 3-->
        <div data-step="3">
            <h3>Wybierz organizacje, której chcesz pomóc:</h3>
            <div class="form-group form-group--checkbox">
                <c:forEach items="${institutions}" var="institution">
                    <label>
                        <input type="radio" value="${institution.id}" data-desc="${institution.name}" name="institution" />
                        <span class="checkbox radio"> </span>
                        <div class="title"> Fundacja : ${institution.name} </div>
                        <span class="description">
                          <div class="subtitle"> Cel i misja: ${institution.description}</div>
                </span>
                    </label>
                </c:forEach>
            </div>
            <div class="form-group form-group--buttons">
                <button type="button" class="btn prev-step">Wstecz</button>
                <button type="button" class="btn next-step">Dalej</button>
            </div>
        </div>

        <!-- STEP 4 -->
        <div data-step="4">
            <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>
            <div class="form-section form-section--columns">
                <div class="form-section--column">
                    <h4>Adres odbioru</h4>
                    <div class="form-group form-group--inline">
                        <label> Ulica <form:input path="street" name="street" id="street" required="true" /> </label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label> Miasto <form:input path="city" name="city" id="city" required="true" /> </label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label> Kod pocztowy <form:input path="zipCode" name="zipCode" id="zipCode" pattern="[0-9]{2}-[0-9]{3}" required="true" /> </label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label>
                            Numer telefonu w formacie 666-777-888 <form:input path="phone" type="tel" pattern="[0-9]{3}-[0-9]{2,3}-[0-9]{2,4}" name="phone" id="phone"/>
                        </label>
                    </div>
                </div>


                <div class="form-section--column">
                    <h4>Termin odbioru</h4>
                    <div class="form-group form-group--inline">
                        <label> Data <form:input type="date" path="pickUpDate" name="date" id="date"/> </label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label> Godzina <form:input type="time" path="pickUpTime" name="time" id="time" /> </label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label>
                            Uwagi dla kuriera
                            <form:textarea path="pickUpComment" name="pickUpComment" id="pickUpComment" />
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group form-group--buttons">
                <button type="button" class="btn prev-step">Wstecz</button>
                <button type="button" class="btn next-step">Dalej</button>
            </div>
        </div>

        <!-- STEP 5 -->
        <div data-step="5">
            <h3>Podsumowanie Twojej darowizny</h3>

            <div class="summary">
                <div class="form-section">
                    <h4>Oddajesz:</h4>
                    <ul>
                        <li>
                            <span class="icon icon-bag"></span>
                            <span class="summary--text" id="bags"
                            >Ilość worków + kategoria darowizny</span>
                        </li>

                        <li>
                            <span class="icon icon-hand"></span>
                            <span class="summary--text" id="institutionSum">Dla której fundacji</span>
                        </li>
                    </ul>
                </div>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru:</h4>
                        <ul>
                            <li id="streetSum">ulica</li>
                            <li id="citySum">miasto</li>
                            <li id="zipSum"> zip code</li>
                            <li id="phoneSum">nr tel</li>
                        </ul>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru:</h4>
                        <ul>
                            <li id="dateSum">data odbioru</li>
                            <li id="timeSum">godzina odbioru</li>
                            <li id="commentSum">uwagi dla kuriera</li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="form-group form-group--buttons">
                <button type="button" class="btn prev-step">Wstecz</button>
                <button type="submit" class="btn">Potwierdzam</button>
            </div>
        </div>
    </div>
    </form:form>
</section>
<%@ include file="footer.jsp" %>
<script src="<c:url value="/resources/js/app.js" />" type="text/javascript"></script>
</body>
</html>