<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="utf-8" />
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<head>
    <style>
        <%@include file="/static/css/style.css"%>
        <%@include file="/static/css/table.css"%>
    </style>
    <title></title>
</head>
<body>
<section class="container">
    <div id="bgc">
        <div class="wrapper">
            <%@include file="menu.jsp"%>
            <div id="holder">
                <div id="content" class="homepage">
                    <c:set var="orders" value="${orders}"/>
                    <c:set var="clients" value="${clients}"/>
                    <c:if test="${username == null}">
                        <script>
                            var currentURL = window.location.href;
                            var baseURL = currentURL.substring(0, currentURL.lastIndexOf('?'));
                            window.location.href = baseURL + "?event=auth&message=forbid";
                        </script>
                    </c:if>
                    <h2>Заказы</h2>
                    <div class="CSSTableGenerator" >
                        <table>

                            <tr>
                                <td scope="col">Клиент</td>
                                <td scope="col">Дата Заказа</td>
                                <td scope="col">Сумма</td>
                                <td scope="col">Оформил заказ</td>
                                <td scope="col">Сохранить отчет</td>
                            </tr>

                            <c:if test="${not empty orders}">
                                <c:forEach var="order" items="${orders}">
                                    <tr>
                                        <td><c:out value="${order.client.name}"/></td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${order.date}"/></td>
                                        <td><c:out value="${String.format('%.0f',order.cost)}"/></td>
                                        <td><c:out value="${order.user.name}"/></td>
                                        <td>
                                            <form method="post" action="warehouse?event=saveReport">
                                                <input type="submit" value="Сохранить отчёт">
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>

                        </table>
                    </div>
                    <hr>
                    <form method="post" action="warehouse?event=ordersBetweenDates">
                        Вывод статистики: за период от
                        <label>
                            <input maxlength="10" type="date" name="fromDate" value="Дата">до<input maxlength="10" type="date" name="toDate" value="Дата">
                        </label>
                        <input type="submit" value="Показать">
                    </form>
                    <form method="post" action="warehouse?event=orderByClient">
                        <label>по клиенту
                            <select size="1" name="criteria">
                                <c:if test="${ not empty clients}">
                                    <c:forEach var="client" items="${clients}">
                                        <option value="${client.id}"><c:out value="${client.name}"/></option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </label>
                        <input type="submit" value="Показать">
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
