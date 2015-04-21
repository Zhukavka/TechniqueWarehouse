<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 20.04.2015
  Time: 15:34
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
    <%if(username == null) response.sendRedirect("auth");
      if (username != null) {
        if(!username.equals("admin")) response.sendRedirect("available_prod");
      } else {
    %>
    <h3>We are not admin</h3>
    <%}
    %>
    <form id="new_user" method="post">
      <p><input maxlength="32" placeholder="Имя пользователя" type="text" name="user"><input type="submit" value="Добавить"></p>
    </form>
  </div>
</div>
</body>
</html>
