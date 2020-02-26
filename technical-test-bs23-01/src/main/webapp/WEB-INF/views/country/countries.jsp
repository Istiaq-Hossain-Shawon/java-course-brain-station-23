<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<body>
<table class="table">
    <tr>
        <th>ID</th>
       
        <th>Country Name</th>
             <th>Action</th>
    </tr>
    <c:forEach items="${country_list}" var="country">
        <tr>
            <th>${ country.id }</th>
            
            <th>${ country.countryName }</th>
            
             <th><a class="badge badge-primary" href="edit?id=${country.id }">Edit</a></th>
             <th><a class="badge badge-primary" href="delete?id=${country.id }">Delete</a></th>
        </tr>
    </c:forEach>
</table>
</body>
</html>