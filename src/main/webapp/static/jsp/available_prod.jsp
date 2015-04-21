<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 18.04.2015
  Time: 17:29
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
        <h3>Hi <%=username %>, Login successful.</h3>
        <br>
        <h2><b>Товары в наличии на складе &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</b>
            &nbsp; &nbsp; &nbsp;<input type="button" value="Текущий заказ" onclick="location.href='order'"></h2>
        <table align="left" border="1" cellpadding="1" cellspacing="1" id="products" style="width: 850px;">
            <thead>
            <tr>
                <th scope="col">Штрих-код</th>
                <th scope="col">Категория</th>
                <th scope="col">Наименование</th>
                <th scope="col">Количество в наличии</th>
                <th scope="col">Цена</th>
                <th scope="col">Количество</th>
                <th scope="col">Добавить в заказ</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <p>&nbsp;</p>
        <hr>
        <p>Сортировать по: &nbsp; &nbsp; &nbsp;<select size="1"><option value="Наименованию">Наименованию</option><option value="Цене">Цене</option></select>&nbsp; &nbsp; &nbsp;&nbsp;<input type="button" value="ОК"></p>
        <hr>
        <p>Поиск по: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<select size="1"><option value="Наименованию">Наименованию</option><option value="Штрих-коду">Штрих-коду</option></select>&nbsp; &nbsp; &nbsp; <input placeholder="Введите" type="text">&nbsp; &nbsp;<input type="button" value="ОК"></p>
        <hr>
        <p><input type="button" value="Добавить товары в БД">&nbsp; &nbsp;<input type="button" value="Просмотр заказов">&nbsp; &nbsp;<input type="button" value="Просмотр клиентов"></p>
        <p>&nbsp;</p>

    </div>
</div>
</body>
</html>
