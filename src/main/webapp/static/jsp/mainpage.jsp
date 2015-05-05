<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 18.04.2015
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.dashyl.entity.AvailableProduct"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dashyl.entity.Product"%>
<%@ page import="com.dashyl.entity.Category"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="/static/css/style.css"%>
    </style>
    <title></title>
</head>
<body>
<div id="bgc">
    <div class="wrapper">
        <%@include file="menu.jsp"%>
        <c:set var="products" value="${products}"/>
        <br>
        <h2>
            <b>Товары в наличии на складе</b>
            <c:if test="${username != null}">
                <input type="button" value="Текущий заказ" onclick="currentOrder()">
            </c:if>
        </h2>
        <form method="post" action="warehouse?event=sortProduct">
            <label>Сортировать по:
                <select size="1" name="criteria">
                    <option value="name">Наименованию</option>
                    <option value="price">Цене</option>
                </select>
            </label>
            <input type="submit" value="Сортировать">
        </form>
        <hr>
        <form method="post" action="warehouse?event=findProduct">
            <label>Поиск по:
                <select size="1" name="criteria">
                    <option value="category">Категории</option>
                    <option value="barcode">Штрих-коду</option>
                </select>
            </label>
            <input placeholder="Введите" name="findValue" type="text">
            <input type="submit" value="Поиск">
        </form>
        <hr>
        <c:if test="${username != null}">
            <p>
                <input type="button" value="Добавить товары в БД" onclick="addProducts()">
                <input type="button" value="Просмотр заказов">
                <input type="button" value="Просмотр клиентов">
            </p>
        </c:if>
        <table align="left" border="1" cellpadding="1" cellspacing="1" id="products" style="width: 850px;">
            <thead>
            <tr>
                <th scope="col">Штрих-код</th>
                <th scope="col">Категория</th>
                <th scope="col">Наименование</th>
                <th scope="col">Количество в наличии</th>
                <th scope="col">Цена</th>
                <c:if test="${username != null}">
                    <th scope="col">Заказ</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty products}">
                <c:forEach var="product" items="${products}">

                    <tr>
                        <td><c:out value="${product.product.barcode}"/></td>
                        <td><c:out value="${product.product.category.name}"/></td>
                        <td><c:out value="${product.product.name}"/></td>
                        <td><c:out value="${product.amount}"/></td>
                        <td><c:out value="${product.price}"/></td>
                        <c:if test="${username != null}">
                            <td>
                                <form method="post" action="warehouse?event=addToOrder&id=${product.id}">
                                    <input type="text" name="amount" required autocomplete="off" placeholder="Кол-во для заказа">
                                    <input type="submit" value="Добавить в заказ">
                                </form>
                            </td>
                        </c:if>
                    </tr>

                </c:forEach>
            </c:if>
            </tbody>
        </table>

    </div>
</div>

<script>
    function currentOrder() {
        var currentURL = window.location.href;
        var baseURL = currentURL.substring(0, currentURL.lastIndexOf('?'));
        window.location.href = baseURL + "?event=order";
    }
    function addProducts() {
        var currentURL = window.location.href;
        var baseURL = currentURL.substring(0, currentURL.lastIndexOf('?'));
        window.location.href = baseURL + "?event=add_products";
    }
</script>
</body>
</html>
