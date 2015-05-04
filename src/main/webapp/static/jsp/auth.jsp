<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 17.04.2015
  Time: 13:03
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
        <c:if test="${username != null}">
            <script>
                var currentURL = window.location.href;
                var baseURL = currentURL.substring(0, currentURL.lastIndexOf('?'));
                window.location.href = baseURL;
            </script>
        </c:if>
        <form id="login" method="post" action="/warehouse?event=login">
            <p><input maxlength="32" placeholder="Имя пользователя" type="text" name="user"></p>
            <p><input maxlength="32" placeholder="Ключ доступа" type="password" name="key"></p>
            <p><input type="submit" value="Войти"></p>
        </form>
    </div>
</div>
</body>
</html>
