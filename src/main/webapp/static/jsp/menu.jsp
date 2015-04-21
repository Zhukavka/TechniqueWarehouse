<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 20.04.2015
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<body>
<script src="//code.jquery.com/jquery-2.1.3.min.js"></script>
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/jquery-2.1.3.min.js"></script>-->
<div id="menu">
  <%
    String username = null;
    Cookie[] cookies = request.getCookies();
    if(cookies != null){
      for(Cookie cookie : cookies){
        if(cookie.getName().equals("user")) username = cookie.getValue();
      }
    }
  %>
  <ul>
    <li><a href="/" name="home">Главная</a></li>
    <%if(username != null) {%>
    <li><a href="/all_orders" name="orders">Все заказы</a></li>
    <li><a href="/available_prod" name="avail_products">Доступные продукты</a></li>
    <li><a href="/order" name="order">Заказ</a></li>
    <li><a href="/new_client" name="new_client">Добавить клиента</a></li>
    <%if(username.equals("admin")) {%>
    <li><a href="/new_user" name="new_user">Добавить пользователя</a></li>
    <%}%>
    <li><a href="/clients" name="clients">Клиенты</a></li>
    <%} else {%>
    <li><a href="/auth" name="login">Вход</a></li>
    <%}%>
    <%if(username != null) {%>
    <li><a href="javascript:logout()">Выйти</a></li>
    <%}%>
  </ul>
</div>
<script>
  function logout() {
    $(document).ready(function() {
      $.post('logout');
    });

  }
</script>
</body>
