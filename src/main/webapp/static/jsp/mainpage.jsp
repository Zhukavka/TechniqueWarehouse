<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="/static/css/style.css"%>
    </style>
    <title>Dashyl.com</title>
</head>
<body>
<div id="bgc">
    <div class="wrapper">
        <%@include file="menu.jsp"%>
        <h2><b>Товары в наличии на складе &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</b>
            &nbsp; &nbsp; &nbsp;</h2>
        <table align="left" border="1" cellpadding="1" cellspacing="1" id="products" style="width: 500px;">
            <thead>
            <tr>
                <th scope="col">Штрих-код</th>
                <th scope="col">Категория</th>
                <th scope="col">Наименование</th>
                <th scope="col">Количество</th>
                <th scope="col">Цена</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <p>&nbsp;</p>
        <hr>
        <p>Сортировать по: &nbsp; &nbsp; &nbsp;<select size="1"><option value="Наименованию">Наименованию</option><option value="Цене">Цене</option></select>&nbsp; &nbsp; &nbsp;&nbsp;<input type="button" value="ОК"></p>
        <hr>
        <p>Поиск по: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<select size="1"><option value="Наименованию">Наименованию</option><option value="Штрих-коду">Штрих-коду</option></select>&nbsp; &nbsp; &nbsp; <input placeholder="Введите" type="text">&nbsp; &nbsp;<input type="button" value="ОК"></p>
    </div>
</div>
</body>
</html>


