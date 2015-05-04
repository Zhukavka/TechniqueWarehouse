<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 27.04.2015
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список работников</title>
</head>
<body>
<h3>Все работники:</h3>(<a href="add">добавить</a>)
<ol>
    <c:forEach items="${employees}" var="employee">
        <li>
                ${employee.name} ${employee.surname} - ${employee.salary} - ${employee.address}
            <a href="add?edit=${employee.id}">редактировать</a> | <a href="delete?id=${employee.id}">удалить</a>
        </li>
    </c:forEach>
</ol>

</body>
</html>
