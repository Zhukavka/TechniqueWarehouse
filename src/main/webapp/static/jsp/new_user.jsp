<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 20.04.2015
  Time: 15:34
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
    <c:choose>
      <c:when test="${username == null}">
        <script>
          var currentURL = window.location.href;
          var baseURL = currentURL.substring(0, currentURL.lastIndexOf('?'));
          window.location.href = baseURL + "?event=auth&message=forbid";
        </script>
      </c:when>
      <c:when test="${username != 'admin'}">
        <script>
          var currentURL = window.location.href;
          var baseURL = currentURL.substring(0, currentURL.lastIndexOf('?'));
          window.location.href = baseURL + "?event=available_prod&message=need_admin";
        </script>
      </c:when>
    </c:choose>
    <form id="new_user" method="post">
      <p><input maxlength="32" placeholder="Имя пользователя" type="text" name="user"><input type="submit" value="Добавить"></p>
    </form>
  </div>
</div>
</body>
</html>
