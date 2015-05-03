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
    <title>Dashyl.com</title>
</head>
<body>
<div id="bgc">
    <div class="wrapper">
        <%@include file="menu.jsp"%>

        <h2><b>Товары в наличии на складе </b></h2>
        <table align="left" border="1" cellpadding="1" cellspacing="1" id="products" style="width: 500px;">
            <thead>
            <tr>
                <th scope="col">Штрих-код</th>
                <th scope="col">Категория</th>
                <th scope="col">Наименование</th>
                <th scope="col">Количество</th>
                <th scope="col">Цена</th>
            </tr>
            <c:if test="${not empty products}">

            </c:if>
            </thead>
            <tbody>
            </tbody>
        </table>
        <hr>
        <p>Сортировать по:<select size="1"><option value="Наименованию">Наименованию</option><option value="Цене">Цене</option></select>
            <input type="button" value="ОК"></p>
        <hr>
        <p>Поиск по:<select size="1"><option value="Наименованию">Наименованию</option><option value="Штрих-коду">Штрих-коду</option></select>
            <input placeholder="Введите" type="text">
            <input type="button" value="ОК"></p>
    </div>
</div>
</body>
</html>


