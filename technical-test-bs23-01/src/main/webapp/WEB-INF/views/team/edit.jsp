<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<body>
	<div class="container">
		<div class="row" style="margin-top: 10px;">
			<div
				class="col col-xl-12 order-xl-2 col-lg-12 order-lg-1 col-md-12 col-sm-12 col-12">
				<div class="page-description">
					<div class="icon">
						<img class="olymp-star-icon left-menu-icon"
							src="~/Content/img/icons8-online-money-transfer-64.png" />
					</div>
					<span>Here you ll update a team</span>
				</div>
			</div>
		</div>
		<div class="row">
			<div
				class="col col-xl-12 order-xl-2 col-lg-12 order-lg-1 col-md-12 col-sm-12 col-12">
				<div class="ui-block">
					<div class="ui-block-title">
						<h6 class="title">Create Team</h6>
					</div>
					<div class="ui-block-content">

						<form:form
							action="${pageContext.request.contextPath }/team/editTeam"
							modelAttribute="team" enctype="multipart/form-data">
							<div class="form-row">
								<form:input path="id" value="${id}" hidden="hidden" />

								<div class="col col-lg-6 col-md-6 col-sm-12 col-12">
									<div class="form-group">
										<label for="teamName">Team</label>
										<form:input path="name" class="form-control" id="teamName"
											placeholder="team Name..." />
									</div>
								</div>


							</div>

							<div class="form-row">


								<div class="form-group col-md-12">
									<label for="teamDescription">Description</label>
									<form:input path="teamDescription" class="form-control"
										id="teamDescription" placeholder="team Description..." />
								</div>

							</div>



							<div class="form-row">


								<div class="col col-lg-4 col-md-4 col-sm-12 col-12">
									<div class="form-group label-floating is-select">
										<label class="control-label">Status</label>
										<form:select path="status" class="selectpicker form-control">
											<form:option value="Y" label="ACTIVE" />
											<form:option value="N" label="DEACTIVE" />
										</form:select>
									</div>
								</div>
								<div class="col col-lg-4 col-md-4 col-sm-12 col-12">
									<div class="form-group label-floating is-select">
										<label class="control-label">Type</label>
										<form:select path="type" class="selectpicker form-control">
											<form:option value="LEAGUE" label="LEAGUE" />
											<form:option value="INTERNATIONAL" label="INTERNATIONAL" />
										</form:select>
									</div>
								</div>

								<div class="col col-lg-4 col-md-4 col-sm-12 col-12">
									<div class="form-group label-floating is-select">
										<label class="control-label">Country</label>
										<form:select path="countryId"
											class="selectpicker form-control">
											<form:options items="${countryList}" />
										</form:select>
										<!-- <input type="file"	name="file"  style="display:none;"/> -->
									<form:input path="logo" value="${logo}" hidden="hidden" />
									</div>
								</div>
							</div>
							<div class="form-row">
								<c:if test="${ empty team.logo}">
									<div class="form-group col-md-4">
										<label for="IMage">Image</label>
										<input type="file"	name="file" />
										<%-- <form:input type="file" name="file" path="file" /> --%>
									</div>
								</c:if>
							</div>
							<div class="form-row">
							<div class="form-group col-md-10">
								<c:if test="${not empty team.logo}">
									<div class="form-group col-md-4">
										<table class="table">
											<tr>
												<th>Images</th>
												<th>Action</th>
											</tr>
											<tr>
												<th><img class="card-img-top"
													src="${pageContext.request.contextPath }/img/${team.logo}"
													alt=""></th>
												<th><a class="badge badge-primary"
													href="team/deleteImage?logo=${logo}}&id=${id }">Delete</a></th>
											</tr>
										</table>
									</div>
								</c:if>
							</div>
						</div>
							<input type="submit" class="btn btn-primary" value="Update Team">
						</form:form>

						

					</div>
				</div>


			</div>


		</div>
	</div>











</body>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</html>