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
                    <c:if test="${username != null}">
                        <script>
                            var currentURL = window.location.href;
                            var baseURL = currentURL.substring(0, currentURL.lastIndexOf('?'));
                            window.location.href = baseURL;
                        </script>
                    </c:if>
                    <div class="form">
                        <div class="tab-content">
                            <div id="login" style="display: block">
                                <form method="post" action="/warehouse?event=login">
                                    <div class="field-wrap">
                                        <label>
                                            Имя пользователя
                                            <span class="req">*</span>
                                        </label>
                                        <input maxlength="32" autocomplete="off" type="text" name="user">
                                    </div>
                                    <div class="field-wrap">
                                        <label>
                                            Ключ доступа
                                            <span class="req">*</span>
                                        </label>
                                        <input maxlength="32" autocomplete="off" type="password" name="key">
                                    </div>
                                    <button type="submit" class="button button-block">Войти</button>
                                </form>
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
