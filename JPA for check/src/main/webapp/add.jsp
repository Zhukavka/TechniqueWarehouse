<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 27.04.2015
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавление | Редактирование</title>
</head>
<body>
<form action="add" method="post">
  <label for="name">Введите имя:
    <input type="text" id="name" value="${employee.name}" name="name" />
  </label>  <br />
  <label for="last-name">Введите фамилию:
    <input type="text" id="last-name" value="${employee.surname}" name="surname" />
  </label>  <br />
  <label for="age">Введите зарплату:
    <input type="text" id="age" value="${employee.salary}" name="salary" />
  </label>  <br />
  <label for="last-name">Введите Город:
    <input type="text" id="city" value="${employee.address.city}" name="city" />
  </label>  <br />
  <label for="last-name">Введите фамилию:
    <input type="text" id="phone" value="${employee.address.phone}" name="phone" />
  </label>  <br />
  <input type="hidden" name="id" value="${employee.id}" />
  <input type="submit" value="Сохранить" />
</form>

</body>
</html>
