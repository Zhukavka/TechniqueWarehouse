<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 17.04.2015
  Time: 13:03
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
        <%if(username != null) response.sendRedirect("available_prod");%>
        <form id="login" method="post">
            <p><input maxlength="32" placeholder="Имя пользователя" type="text" name="user"></p>
            <p><input maxlength="32" placeholder="Ключ доступа" type="password" name="key"></p>
            <p><input type="submit" value="Войти"></p>
        </form>
        <p>&nbsp;</p>
    </div>
</div>
</body>
</html>
