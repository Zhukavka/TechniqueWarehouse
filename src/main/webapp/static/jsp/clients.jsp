<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 18.04.2015
  Time: 17:42
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
        <h2>Клиенты</h2>
        <table align="left" border="1" cellpadding="1" cellspacing="1" style="width: 500px;">
            <thead>
            <tr>
                <th scope="col">Имя</th>
                <th scope="col">E-mail</th>
                <th scope="col">Телефон</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <p>;</p>
        <p><input type="button" value="Добавить нового клиента" onclick="location.href='new_client'"></p>

    </div>
</div>
</body>
</html>
