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
        <%@include file="/static/css/auth.css"%>

    </style>


    <title></title>
</head>
<body>
<section class="container">
    <div id="bgc">
        <div class="wrapper">
            <%@include file="menu.jsp"%>
            <div id="holder">
                <div id="content" class="homepage">
                    <c:if test="${username == null}">
                        <script>
                            var currentURL = window.location.href;
                            var baseURL = currentURL.substring(0, currentURL.lastIndexOf('?'));
                            window.location.href = baseURL + "?event=auth&message=forbid";
                        </script>
                    </c:if>
                    <div class="form">
                        <div class="tab-content">
                            <div id="login" style="display: block">
                                <form method="post" action="warehouse?event=add_client">
                                    <div class="field-wrap">
                                        <label>
                                            Имя клиента
                                            <span class="req">*</span>
                                        </label>
                                        <input maxlength="32" required autocomplete="off" name="name"type="text">
                                    </div>
                                    <div class="field-wrap">
                                        <label>
                                            E-mail
                                            <span class="req">*</span>
                                        </label>
                                        <input maxlength="32" required autocomplete="off" name="email" type="email">
                                    </div>
                                    <div class="field-wrap">
                                        <label>
                                            Телефон
                                            <span class="req">*</span>
                                        </label>
                                        <input maxlength="32" required autocomplete="off" name="phone" type="text">
                                    </div>
                                    <button type="submit" class="button button-block">Добавить</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</section>
<script>
    <%@include file="/static/javascript/input_form.js"%>
</script>
</body>
</html>
