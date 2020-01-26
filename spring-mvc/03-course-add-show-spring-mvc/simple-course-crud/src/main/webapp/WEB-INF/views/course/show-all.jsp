<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New Course</title>
</head>
<body>
<h1>hi</h1>
	<table>
		<tr>
			<th>ID</th>			
			<th>Country Name</th>
		</tr>
		<c:forEach items="${courses}" var="course">
			<tr>
				<th>${ course.courseId }</th>				
				<th>${ course.courseName }</th>
			</tr>
		</c:forEach>
	</table>
</body>
</html>