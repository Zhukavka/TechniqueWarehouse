<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 20.04.2015
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<body>
<script src="//code.jquery.com/jquery-2.1.3.min.js"></script>
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/jquery-2.1.3.min.js"></script>-->
<div id="menu">

  <ul>
    <li><a href="/warehouse" name="home">Главная</a></li>
    <c:if test = "${username != null}">
      <li><a href="/warehouse?event=all_orders" name="orders">Все заказы</a></li>
      <li><a href="/warehouse?event=available_prod" name="avail_products">Доступные продукты</a></li>
      <li><a href="/warehouse?event=order" name="order">Заказ</a></li>
      <li><a href="/warehouse?event=clients" name="clients">Клиенты</a></li>
      <li><a href="/warehouse?event=new_client" name="new_client">Добавить клиента</a></li>
    </c:if>
    <c:if test = "${username == 'admin'}">
      <li><a href="/warehouse?event=new_user" name="new_user">Добавить пользователя</a></li>
    </c:if>
    <c:if test="${username != null}">
      <li><a href="javascript:logout()">Выйти</a></li>
    </c:if>
    <c:if test="${username == null}">
      <li><a href="/warehouse?event=auth" name="login">Вход</a></li>
    </c:if>

  </ul>
</div>
<script>
  function logout() {
    $(document).ready(function() {
      $.post('warehouse?event=logout');
    });

  }
</script>
</body>
