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
        <c:if test="${username == null}">
            <script>
                var currentURL = window.location.href;
                var baseURL = currentURL.substring(0, currentURL.lastIndexOf('?'));
                window.location.href = baseURL + "?event=auth&message=forbid";
            </script>
        </c:if>
        <br>
        <h2>
            <b>Товары в наличии на складе</b>
            <input type="button" value="Текущий заказ" onclick="currentOrder()">
        </h2>
        <p>Сортировать по: <select size="1"><option value="Наименованию">Наименованию</option><option value="Цене">Цене</option></select>
            <input type="button" value="ОК"></p>
        <hr>
        <p>Поиск по:<select size="1"><option value="Наименованию">Наименованию</option><option value="Штрих-коду">Штрих-коду</option></select>
            <input placeholder="Введите" type="text">
            <input type="button" value="ОК"></p>
        <hr>
        <p>
            <input type="button" value="Добавить товары в БД" onclick="addProducts()">
            <input type="button" value="Просмотр заказов">
            <input type="button" value="Просмотр клиентов">
        </p>
        <table align="left" border="1" cellpadding="1" cellspacing="1" id="products" style="width: 850px;">
            <thead>
            <tr>
                <th scope="col">Штрих-код</th>
                <th scope="col">Категория</th>
                <th scope="col">Наименование</th>
                <th scope="col">Количество в наличии</th>
                <th scope="col">Цена</th>
                <th scope="col">Заказ</th>
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
                        <td><input type="text" placeholder="Кол-во для заказа"><input type="button" value="Добавить в заказ"></td>
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
