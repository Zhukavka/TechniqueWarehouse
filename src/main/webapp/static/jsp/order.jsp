<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 18.04.2015
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
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
        <%if(username == null) response.sendRedirect("auth");%>
        <h2>Текущий заказ</h2>
        <table align="left" border="1" cellpadding="1" cellspacing="1" id="order" style="width: 500px;">
            <thead>
            <tr>
                <th scope="col">Штрих-код товара</th>
                <th scope="col">Наименование</th>
                <th scope="col">Количество</th>
                <th scope="col">Сумма</th>
                <th scope="col">Удалить</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <p>&nbsp;</p>
        <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Итого:&nbsp;<input maxlength="32" type="text" value="Общая сумма"></p>
        <p><input type="button" value="Просмотр клиентов" onclick="location.href='clients'">
            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<input type="button" value="Оформить заказ"></p>
    </div>
</div>
</body>
</html>