<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spring By Practical Examples</title>
<link rel="stylesheet"	href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<link rel="stylesheet"	href="${pageContext.request.contextPath }/css/style.css" />
<link rel="stylesheet"	href="${pageContext.request.contextPath }/css/jquery-ui.css" />
<link rel="stylesheet"	href="${pageContext.request.contextPath }/css/font-awesome.min.css" />
<script src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery-ui.js"></script>
</head>



  <nav class="navbar navbar-expand-lg fixed-top header-background" >
    <div class="container">
      <a class="navbar-brand" href="<c:url value="/course/show-all" />">Simple CRUD</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
            <a class="nav-link" href="<c:url value="/course/add" />"> <i class="fa fa-car"></i>Add Courses
              <span class="sr-only">(current)</span>
            </a>
          </li>          
        </ul>
      </div>
    </div>
  </nav>
<body>