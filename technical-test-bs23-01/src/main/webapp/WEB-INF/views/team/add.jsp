<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<body>

<h1>Add New Team</h1>

<form:form action="${pageContext.request.contextPath }/team/add"
           modelAttribute="team">
    
    <form:input path="name"/> <br>



<form:select path="countryId">
    <form:options items="${countryList}" />
</form:select>

    <input type="submit" name="submit" value="Add Team">
</form:form>

</body>
</html>