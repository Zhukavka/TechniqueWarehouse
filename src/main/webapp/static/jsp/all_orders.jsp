<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 18.04.2015
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <c:if test="${username == null}">
            <script>
                var currentURL = window.location.href;
                var baseURL = currentURL.substring(0, currentURL.lastIndexOf('?'));
                window.location.href = baseURL + "?event=auth&message=forbid";
            </script>
        </c:if>
        <h2>Заказы</h2>
        <table align="left" border="1" cellpadding="1" cellspacing="1" style="width: 500px;">
            <thead>
            <tr>
                <th scope="col">Клиент</th>
                <th scope="col">Дата Заказа</th>
                <th scope="col">Сумма</th>
                <th scope="col">Сохранить отчет</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <hr>
        <p>Вывод статистики: за период от
            <input maxlength="10" type="text" value="Дата"> до
            <input maxlength="10" type="text" value="Дата">
            <input type="button" value="ОК"></p>
        <p>по клиенту
            <select size="1">
                <option value="Имя">Имя</option>
            </select>
            <input type="button" value="ОК"></p>
    </div>
</div>
</body>
</html>
