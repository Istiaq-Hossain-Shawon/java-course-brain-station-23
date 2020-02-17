<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Spring By Practical Examples</title>
    <link rel="stylesheet"	href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<link rel="stylesheet"	href="${pageContext.request.contextPath }/css/style.css" />
<link rel="stylesheet"	href="${pageContext.request.contextPath }/css/jquery-ui.css" />
<link rel="stylesheet"	href="${pageContext.request.contextPath }/css/font-awesome.min.css" />

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">CricBean</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/team/show-all" >Teams</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/country/show-all">Countries</a>
      </li>      
    </ul>
    <form class="form-inline my-2 my-lg-0">
     
     
      <a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-success my-2 my-sm-0">Logout</a>
    </form>
  </div>
</nav>
</body>