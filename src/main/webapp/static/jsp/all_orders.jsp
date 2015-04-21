<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 18.04.2015
  Time: 17:47
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
        <p>&nbsp;</p>
        <hr>
        <p>Вывод статистики: &nbsp;за период от&nbsp;<input maxlength="10" type="text" value="Дата">&nbsp; до &nbsp;<input maxlength="10" type="text" value="Дата">&nbsp; &nbsp;<input type="button" value="ОК"></p>
        <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; по клиенту &nbsp;&nbsp;<select size="1"><option value="Имя">Имя</option></select>&nbsp; &nbsp;<input type="button" value="ОК"></p>
    </div>
</div>
</body>
</html>
