<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 18.04.2015
  Time: 17:40
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
        <form method="post" action="warehouse?event=add_client">
            <p>    Имя:<input maxlength="32" name="name" placeholder="Name" type="text"></p>
            <p> E-mail:<input maxlength="32" name="email" placeholder="E-mail" type="text"></p>
            <p>Телефон:<input maxlength="32" name="phone" placeholder="Phone" type="text"></p>
            <p><input type="submit" value="Добавить"></p>
        </form>
    </div>
</div>
</body>
</html>
