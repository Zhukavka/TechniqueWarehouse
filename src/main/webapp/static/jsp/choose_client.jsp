<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 18.04.2015
  Time: 17:37
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
    <p>Клиент: &nbsp; &nbsp; &nbsp;&nbsp;<select size="1"><option value="Имя клиента">Имя клиента</option></select></p>
    <p><input type="button" value="Новый клиент"></p>
    <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<input type="button" value="Отгрузить"></p>

  </div>
</div>
</body>
</html>
