<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<body>

	<h1>Edit team</h1>


	<div class="container padding-t80">
		<div class="row">
			<div class="col-lg-10">
				<div class="row">

					<form:form
						action="${pageContext.request.contextPath }/team/editTeam"
						modelAttribute="team" enctype="multipart/form-data">
						<form:input path="id" value="${id}" hidden="hidden" />
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="teamName">team Name</label>
								<form:input path="name" class="form-control" id="teamName"
									placeholder="team Name..." />
							</div>
						</div>
						<div class="form-group">
							<label for="teamDescription">Description</label>
							<form:input path="teamDescription" class="form-control"
								id="teamDescription" placeholder="team Description..." />
						</div>

						<div class="form-row">
							<div class="form-group col-md-4">
								<label for="teamStatus">Status</label>
								<form:select path="status" class="form-control">
									<form:option value="Y" label="ACTIVE" />
									<form:option value="N" label="DEACTIVE" />
								</form:select>
							</div>
							<div class="form-group col-md-4">
								<label for="teamLevel">Type</label>
								<form:select path="type" class="form-control">
									<form:option value="T20" label="T20" />
									<form:option value="TEST" label="TEST" />
									<form:option value="ODI" label="ODI" />
								</form:select>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-4">
								<label for="teamLevel">Country</label>
								<form:select path="countryId" class="form-control">
									<form:options items="${countryList}" />
								</form:select>
							</div>
							<c:if test="${logo==''}">
								<div class="form-group col-md-4">
									<label for="IMage">Image</label> <input type="file" name="file" />
									<%-- <form:input type="file" name="file" path="file" /> --%>
								</div>
							</c:if>
							
							<c:if test="${logo!=''}">
								<div class="form-group col-md-4">
								<table class="table">
									<tr>
										<th>Images</th>
										<th>Action</th>
									</tr>
									<tr>
										<th><img class="card-img-top"
											src="${pageContext.request.contextPath }/images/${logo}"
											alt=""></th>
										<th><a class="badge badge-primary"
											href="team/deleteImage?logo=${logo}}&id=${id }">Delete</a></th>
									</tr>
								</table>
							</div>
							</c:if>
							
							
						</div>

						<input type="submit" class="btn btn-primary" value="Edit Team">
					</form:form>


				</div>
			</div>
		</div>
	</div>


</body>
</html>