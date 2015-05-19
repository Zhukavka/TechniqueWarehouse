<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.dashyl.entity.Order"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dashyl.entity.Product"%>
<%@ page import="com.dashyl.entity.OrderedProduct"%>
<%@ page import="com.dashyl.entity.User"%>
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
                    <c:set var="order" value="${order}"/>
                    <c:set var="clients" value="${clients}"/>
                    <c:set var="totalPrice" value="${totalPrice}"/>
                    <c:if test="${username == null}">
                        <script>
                            var currentURL = window.location.href;
                            var baseURL = currentURL.substring(0, currentURL.lastIndexOf('?'));
                            window.location.href = baseURL + "?event=auth&message=forbid";
                        </script>
                    </c:if>
                    <h2>Текущий заказ
                        <c:if test="${totalPrice != null}">
                            <label style="float: right">Итого:
                                <input maxlength="32" type="text" readonly value="${String.format('%.0f',totalPrice)}">
                            </label>
                        </c:if>
                    </h2>
                    <div class="CSSTableGenerator" >
                        <table>
                            <tr>
                                <td scope="col">Штрих-код товара</td>
                                <td scope="col">Наименование</td>
                                <td scope="col">Количество</td>
                                <td scope="col">Сумма</td>
                                <td scope="col">Удалить</td>
                            </tr>

                            <c:if test="${order != null}">
                                <c:forEach var="product" items="${order.products}">
                                    <tr>
                                        <td><c:out value="${product.product.barcode}"/></td>
                                        <td><c:out value="${product.product.name}"/></td>
                                        <td><c:out value="${product.amount}"/></td>
                                        <td><c:out value="${String.format('%.0f',product.price)}"/></td>
                                        <td>
                                            <form method="post" action="warehouse?event=removeFromOrder&id=${product.id}">
                                                <input type="submit" value="Убрать из заказа">
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>

                        </table>
                    </div>

                    <p>
                    <form method="post" action="warehouse?event=cancelOrder">
                        <input type="submit" value="Отменить заказ">
                    </form>
                    <form method="post" action="warehouse?event=applyOrder">
                        <select size="1" name="criteria">
                            <c:if test="${ not empty clients}">
                                <c:forEach var="client" items="${clients}">
                                    <option value="${client.id}"><c:out value="${client.name}"/></option>
                                </c:forEach>
                            </c:if>
                        </select>
                        <input type="submit" value="Оформить заказ">
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
