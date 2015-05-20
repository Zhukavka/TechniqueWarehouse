<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.dashyl.entity.Client"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="utf-8" />
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<head>
    <style>
        <%@include file="/static/css/style.css"%>
        <%@include file="/static/css/table.css"%>
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
                    <c:set var="clients" value="${clients}"/>
                    <p> <h2>Клиенты<input style="float: right" type="button" value="Добавить нового клиента" onclick="newClient()"></h2></p>
                    <c:choose>
                        <c:when test="${not empty clients}">
                            <div class="CSSTableGenerator" >
                                <table >
                                    <tr>
                                        <td scope="col">Имя</td>
                                        <td scope="col">E-mail</td>
                                        <td scope="col">Телефон</td>
                                    </tr>
                                    <c:forEach var="client" items="${clients}">
                                        <tr>
                                            <td><c:out value="${client.name}"/></td>
                                            <td><c:out value="${client.email}"/></td>
                                            <td><c:out value="${client.phoneNumber}"/></td>
                                        </tr>

                                    </c:forEach>
                                </table>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="info">В БД нет клиентов</div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    function newClient() {
        var currentURL = window.location.href;
        var baseURL = currentURL.substring(0, currentURL.lastIndexOf('?'));
        window.location.href = baseURL + '?event=new_client';
    }
</script>
</body>
</html>
