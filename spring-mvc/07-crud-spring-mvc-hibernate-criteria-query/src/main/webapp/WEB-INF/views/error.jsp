<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<body>
	<div class="container padding-t80">
		<div class="row">
			<div class="col-lg-10">
				<div class="row">
					<div class="col-lg-10 col-md-10 mb-10">
						<div class="h-100">
							<div class="card-body">
								<h4 class="card-title"></h4>
								<div class="alert alert-danger" role="alert">${message}!</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>