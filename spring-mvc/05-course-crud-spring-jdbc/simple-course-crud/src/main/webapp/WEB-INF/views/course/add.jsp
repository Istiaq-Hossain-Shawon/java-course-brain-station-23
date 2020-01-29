<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container padding-t80">
	<div class="row">
		<div class="col-lg-10">
			<div class="row">

				<form:form action="${pageContext.request.contextPath }/course/add"
					modelAttribute="course" enctype="multipart/form-data">					
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="courseName">Course Name</label> 
							
							<form:input path="courseName"  class="form-control" id="courseName"  placeholder="Course Name..."/>
						</div>
						<div class="form-group col-md-6">
							<label for="courseCode">Course Code</label> 
							<form:input path="courseCode"  class="form-control" id="courseCode"  placeholder="Course Code..."/>
						</div>
					</div>
					<div class="form-group">
						<label for="courseDescription">Description</label> 
						<form:input path="courseDescription"  class="form-control" id="courseDescription"  placeholder="Course Description..."/>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="courseAuthor">Author</label>
							<form:input path="courseAuthor" class="form-control"
								id="courseAuthor" placeholder="Course Author..." />
						</div>
						<div class="form-group col-md-6">
							<label for="submission_date">Date</label>
							<form:input path="submission_date" class="form-control selector"
								id="submission_date" placeholder="mm/dd/yyyy" />
						</div>
					</div>

					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="courseDuration">Course Duration</label> 
							<form:input path="courseDuration"  class="form-control" id="courseDuration"  placeholder="Duration..."/>
						</div>
						<div class="form-group col-md-4">
							<label for="courseLevel">Level</label>
							<form:select path="courseLevel" class="form-control">							   
							   <form:option value="Beginner" label="Beginner"/>
							   <form:option value="Midium" label="Midium"/>
							   <form:option value="Hard" label="Hard"/>							   
							</form:select>
						</div>
						<div class="form-group col-md-2">
							<label for="coursePrice">Price</label> 
							<form:input path="coursePrice"  class="form-control" id="coursePrice"  placeholder="Price..."/>
						</div>
					</div>
					<div class="form-row">

						<div class="form-group col-md-6">
							<label for="IMage">Image</label> 
							<input type="file" name="file" />
								<%-- <form:input type="file" name="file" path="file" /> --%>
						</div>
						
					</div>


					<input type="submit" class="btn btn-primary" value="Add Course">
				</form:form>


			</div>
		</div>
	</div>
</div>
<script>
  $(function() {
    $("#submission_date" ).datepicker(); // give your date field an id or a date css class    
    $( ".selector" ).datepicker( "setDate", new Date());
  });
</script>