<%--
  Created by IntelliJ IDEA.
  User: duyli
  Date: 4/17/2020
  Time: 9:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/common/taglib.jsp"%>
<%--<c:redirect url = "/homepage"/>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  </head>
  <body>
  <div class="container-fluid px-0" style="height: 100vh;">
    <nav class="navbar navbar-expand-sm navbar-dark rounded" style="height: 10%; background-color: #ff3335">
      <!-- Brand -->
      <a class="navbar-brand" href="#">CYBER GAME</a>

      <!-- Links -->
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/homepage?action=index">Trang chủ</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Liên hệ</a>
        </li>

        <!-- Dropdown -->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
            Tuyển dụng
          </a>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="#">Link 1</a>
            <a class="dropdown-item" href="#">Link 2</a>
            <a class="dropdown-item" href="#">Link 3</a>
          </div>
        </li>
      </ul>
    </nav>
    <div class="row px-0 mx-0" style="height: 90%;">
      <div class="col-2 bg-info px-0 rounded">
        <div class="list-group border border-primary rounded h-100">
          <a href="#" class="list-group-item list-group-item-action active">Home</a>
          <a href="${pageContext.request.contextPath}/employees?action=list" class="list-group-item list-group-item-action">Employee Management</a>
          <a href="#" class="list-group-item list-group-item-action">Customer Management</a>
          <a href="#" class="list-group-item list-group-item-action">Service Management</a>
        </div>
      </div>
      <div class="col-10 p-5">
        <router-outlet></router-outlet>
      </div>
    </div>
  </div>












  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
          integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
          crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
          integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
          crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
          integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
          crossorigin="anonymous"></script>
  </body>
</html>
