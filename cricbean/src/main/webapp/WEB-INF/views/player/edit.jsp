<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Edit  team</title>
</head>
<body>

<h1>Edit  team</h1>

<form:form action="${pageContext.request.contextPath }/item/edit"
           modelAttribute="item">
    
    <form:input path="name"/> <br>
<form:select path="countryId" items="${countryList}"   selected="true" value="${country.setId()}"/>
    <input type="submit" name="submit" value="edit Item">
</form:form>

</body>
</html>