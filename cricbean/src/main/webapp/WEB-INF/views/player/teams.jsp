<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
   
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>Team Name</th>
        <th>Country Name</th>
    </tr>
    <c:forEach items="${team_list}" var="team">
        <tr>
            <th>${ team.id }</th>
            <th>${ team.name }</th>
            <th>${ team.country.countryName }</th>
        </tr>
    </c:forEach>
</table>
</body>
</html>