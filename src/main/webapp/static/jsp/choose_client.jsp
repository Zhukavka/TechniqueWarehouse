<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 18.04.2015
  Time: 17:37
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
    <p>Клиент:<select size="1"><option value="Имя клиента">Имя клиента</option></select></p>
    <p><input type="button" value="Новый клиент"></p>
    <p><input type="button" value="Отгрузить"></p>

  </div>
</div>
</body>
</html>
