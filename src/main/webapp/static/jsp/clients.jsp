<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 18.04.2015
  Time: 17:42
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
        <p>&nbsp;</p>
        <p><input type="button" value="Добавить нового клиента" onclick="location.href='new_client'"></p>

    </div>
</div>
</body>
</html>
