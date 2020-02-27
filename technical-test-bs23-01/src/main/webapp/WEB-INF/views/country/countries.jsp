<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/views/common/header.jsp" />


<body>


	<div class="container">
		<div class="row">
			<div class="col col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="ui-block responsive-flex">
					<form class="ui-block-title"
						action="${pageContext.request.contextPath }/country/search"
						modelAttribute="country"  method="post">
						<div class="h6 title">Country List</div>
						<div class="w-select">
							<div class="title">Order By:</div>
							<fieldset class="form-group">
								<select class="selectpicker form-control" name=sort>
									<option value="ND">Name (Des)</option>
									<option value="NA">Name (Asc)</option>
								</select>
							</fieldset>
							
							<input type="hidden" name="pageIndex" value="${pageIndex}"/>						
							
							
						</div>
						<div class="w-search">
							<div class="form-group with-button">
								<input name=countryName class="form-control" type="text"
									placeholder="Search Country...">
								<button>
									<svg class="olymp-magnifying-glass-icon">
										<use
											xlink:href="svg-icons/sprites/icons.svg#olymp-magnifying-glass-icon"></use></svg>
								</button>
							</div>
						</div>
						<a href="#" class="more"><svg class="olymp-three-dots-icon">
								<use
									xlink:href="svg-icons/sprites/icons.svg#olymp-three-dots-icon"></use></svg></a>
					</form>
				</div>
			</div>
		</div>
	</div>




	<div class="container">
		<div class="row">

			<div class="col col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="ui-block">




					<table class="table">
						<tr>
							<th>ID</th>
							<th>Country Name</th>
							<th>Country Name</th>
							<th>Action</th>
						</tr>
						<c:forEach items="${countries }" var="country">
							<tr>
								<th>${ country.id }</th>

								<th><img class="img-responsive" style="width: 100px;"
									src="${pageContext.request.contextPath }/img/${country.logo}"></th>
								<th>${ country.countryName }</th>

								<th><a class="btn btn-primary"
									href="edit?id=${country.id }">Edit</a></th>
								<th><a class="btn btn-danger"
									href="delete?id=${country.id }">Delete</a></th>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			pageIndex: ${pageIndex}
			 <ul class="pagination pagination-sm">
                <li class="page-item"><a class="page-link" href="/SpringMvcPagination/init/1">1</a></li>
                <li class="page-item"><a class="page-link" href="/SpringMvcPagination/init/2">2</a></li>
                <li class="page-item"><a class="page-link" href="/SpringMvcPagination/init/3">3</a></li>
              </ul>
		</div>

	</div>

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>