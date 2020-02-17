<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<body>

<h1>Edit  team</h1>

<form:form action="${pageContext.request.contextPath }/team/editTeam" modelAttribute="team" >
    
    <form:input path="name"/> <br>

<form:input path="id" value="${id}" hidden="hidden"/>

<form:select path="countryId"   >
    <form:options items="${countryList}" />
</form:select>

    <input type="submit" name="submit" value="edit Item">
</form:form>

</body>
</html>