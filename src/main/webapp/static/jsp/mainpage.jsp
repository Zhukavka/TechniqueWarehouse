<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.dashyl.entity.AvailableProduct"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dashyl.entity.Product"%>
<%@ page import="com.dashyl.entity.Category"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
                    <c:set var="products" value="${products}"/>
                    <br>
                    <h2>
                        <b>Товары в наличии на складе</b>
                    </h2>
                    <c:if test="${message != null}">
                        <c:if test="${messageType != null}">
                            <div class="${messageType}">${message}</div>
                        </c:if>
                    </c:if>
                    <div>
                        <div style="float: left">
                            <form method="post" action="warehouse?event=sortProduct">
                                <label>Сортировать по:
                                    <select size="1" name="criteria">
                                        <option value="name">Наименованию</option>
                                        <option value="price">Цене</option>
                                    </select>
                                </label>
                                <input type="submit" value="Сортировать">
                            </form>
                        </div>
                        <div style="float: right">
                            <form method="post" action="warehouse?event=findProduct">
                                <label>Поиск по:
                                    <select size="1" name="criteria">
                                        <option value="barcode">Штрих-коду</option>
                                        <option value="category">Категории</option>
                                    </select>
                                </label>
                                <input placeholder="Введите" name="findValue" type="text">
                                <input type="submit" value="Поиск">
                            </form>
                        </div>
                    </div>
                    <br>
                    <c:if test="${username != null}">
                        <div style="padding: 10px">
                            <input type="button" value="Добавить товары в БД" onclick="addProducts()">
                        </div>
                    </c:if>
                    <c:choose>
                        <c:when test="${not empty products}">
                            <div class="CSSTableGenerator" >
                                <table >
                                    <tr>
                                        <td scope="col">Штрих-код</td>
                                        <td scope="col">Категория</td>
                                        <td scope="col">Наименование</td>
                                        <td scope="col">Количество в наличии</td>
                                        <td scope="col">Цена</td>
                                        <c:if test="${username != null}">
                                            <td scope="col">Заказ</td>
                                        </c:if>
                                    </tr>
                                    <c:forEach var="product" items="${products}">
                                        <tr>
                                            <td><c:out value="${product.product.barcode}"/></td>
                                            <td><c:out value="${product.product.category.name}"/></td>
                                            <td><c:out value="${product.product.name}"/></td>
                                            <td><c:out value="${product.amount}"/></td>
                                            <td><c:out value="${String.format('%.0f',product.price)}"/></td>
                                            <c:if test="${username != null}">
                                                <td>
                                                    <form method="post" action="warehouse?event=addToOrder&id=${product.id}">
                                                        <input type="number" name="amount" required autocomplete="off" placeholder="Кол-во для заказа">
                                                        <input type="submit" value="Добавить в заказ">
                                                    </form>
                                                </td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="info">Нет продуктов в наличии</div>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>
    </div>
</section>
<script>
    function addProducts() {
        var currentURL = window.location.href;
        var baseURL = currentURL.substring(0, currentURL.lastIndexOf('?'));
        window.location.href = baseURL + "?event=add_products";
    }
</script>
</body>
</html>
