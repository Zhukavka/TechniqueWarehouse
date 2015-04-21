<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 18.04.2015
  Time: 17:40
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
        <p>Имя: &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<input maxlength="32" placeholder="Name" type="text"></p>
        <p>E-mail: &nbsp; &nbsp;&nbsp;<input maxlength="32" placeholder="E-mail" type="text"></p>
        <div>Телефон:&nbsp;<input maxlength="32" placeholder="Phone" type="text">
            <div>&nbsp;</div>
        </div>
        <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<input type="button" value="Добавить"></p>

    </div>
</div>
</body>
</html>
