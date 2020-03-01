<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/views/common/sidebar.jsp" />
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
					<span>Here you’ll create a country 
						</span>
				</div>
			</div>
		</div>
		<div class="row">
			<div
				class="col col-xl-12 order-xl-2 col-lg-12 order-lg-1 col-md-12 col-sm-12 col-12">
				<div class="ui-block">
					<div class="ui-block-title">
						<h6 class="title">Create Country</h6>
					</div>
					<div class="ui-block-content">

						<form:form
							action="${pageContext.request.contextPath }/country/add" enctype="multipart/form-data"
							modelAttribute="country">


							<div class="form-row">



								<div class="form-group col-md-6">
									<label for="countryName">country Name</label>
									<form:input path="countryName" />
								</div>


							</div>

							<div class="form-row">
							

							<div class="form-group col-md-6">
								<label for="IMage">Image</label> 
								<input type="file" name="file" />
								
								<%-- <form:input type="file" name="file" path="file" /> --%>
							</div>

						</div>

							<input type="submit" name="submit" class="btn btn-primary"
								value="Add Country">
						</form:form>


					</div>
				</div>


			</div>


		</div>
	</div>






</body>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</html>