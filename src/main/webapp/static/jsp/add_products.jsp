<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 02.05.2015
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <style><%@include file="/static/css/style.css"%></style>
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

    <form id="fileImport" method="post" enctype="multipart/form-data">
      <p>Выберите файл</p>
      <p><input id="fileImportData" type="file" multiple accept="application/json" name="file">
        <input type="submit" value="Отправить"></p>
    </form>
  </div>
</div>


<script>
  $(document).ready(function(){

    if(!window.FormData) {
      alert("Ваш браузер не поддерживает объект FormData");
      return;
    }

    $('#fileImport').submit(function(e) {
      e.preventDefault();
      if(import_errors) {
        return false;
      }
      import_data();
      return false;
    });

    function import_errors() {
      if($('fileImportData').val() == '') {
        alert('No file(s) selected. Please choose a JSON file to upload');
        return true;
      } else {
        var ext = $('#fileImportData').val().split('.').pop().toLowerCase();
        if($.inArray(ext, ['json']) == -1) {
          alert('Invalid file type. Please choose a JSON file(s) to upload.');
          return true;
        }
        return false;
      }
    }

    function import_data() {
      var formData = new FormData();
      $.each($('#fileImportData')[0].files, function(i, file) {
        formData.append('uploadFile-' + i, file);
      });

      formData.append('action', '');
      formData.append('',);

      $.ajax( {
        url: '/warehouse?event=add_products',
        type: 'POST',
        cache: false,
        contentType: false,
        processData: false,

        data: formData,
        beforeSend: function(jqXHR, settings) {
          console.log("Haven't entered server side yet.");
        },
        dataFilter: function(data, type) {
          console.log("JSON string echoed back from server side.");
        },
        success: function(data, textStatus, jqXHR) {
          console.log("Back from server-side!");
          if(data.msg != 'success') {
            alert(data.error);
          }
        },
        error: function(jqXHR, textStatus, errorThrown) {
          console.log("A JS error has ocurred");
        },
        complete: function(jqXHR, textStatus) {
          console.log("Ajax is finished");
        }
      });
  }
  });
</script>
</body>
</html>
