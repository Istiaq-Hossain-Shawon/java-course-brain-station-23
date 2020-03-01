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
		<div class="row">
			<div class="col col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="ui-block responsive-flex">
					<div class="ui-block-title">
						<div class="h6 title">Country List</div>
						<div class="w-select">
							<div class="title">Order By:</div>
							<fieldset class="form-group">
								<select class="selectpicker form-control" name=sort id="sSort">
									<option value="ND">Name (Des)</option>
									<option value="NA">Name (Asc)</option>
								</select>
							</fieldset>
							<input type="hidden" name="pageIndex" value="${pageIndex}"/>	
						</div>
						<div class="w-search">
							<div class="form-group with-button">
								<input name=countryName  id="countryName" class="form-control" type="text"
									placeholder="Search Country...">
								<button style=width:"40px" id="btnSearch" myContextPath="${pageContext.request.contextPath}">
									<img  class="olymp-menu-icon left-menu-icon"
                                  src="${pageContext.request.contextPath }/img/search.png" />									
								</button>
							</div>
						</div>
						<a href="#" class="more"><svg class="olymp-three-dots-icon">
								<use
									xlink:href="svg-icons/sprites/icons.svg#olymp-three-dots-icon"></use></svg></a>
									
						<a class="btn btn-primary" href="${pageContext.request.contextPath }/country/add"> 
						 <img  class="olymp-menu-icon left-menu-icon"
                                  src="${pageContext.request.contextPath }/img/new-copy-48.png" />
						
						
						Add Country<div class="ripple-container"></div></a>
					</form>
				</div>
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


<c:if test="${totalPages>0}">

			<div class="pagination">
				<c:forEach var="i" begin="0" end="${totalPages-1}" step="1">
				<c:if test="${i == 0}"><a href="${pageContext.request.contextPath }/country/show-all?_search=&_pageIndex=${i}&_rows=5&_sort=NA">&laquo;</a>
				</c:if>				
				
				<c:if test="${i == pageIndex}"><a class="active" href="${pageContext.request.contextPath }/country/show-all?_search=&_pageIndex=${i}&_rows=5&_sort=NA">${i+1}</a>
				</c:if>
				
				<c:if test="${i != pageIndex}"><a  href="${pageContext.request.contextPath }/country/show-all?_search=&_pageIndex=${i}&_rows=5&_sort=NA">${i+1}</a>
				</c:if>
				
				
				
				
				<c:if test="${i == totalPages}"><a href="${pageContext.request.contextPath }/country/show-all?_search=&_pageIndex=${i}&_rows=5&_sort=NA">&raquo;</a>
				</c:if>
				
				</c:forEach>
			</div>

				</c:if>




<!-- 
			<div class="pagination">
				<a href="#">&laquo;</a> <a href="#">1</a> <a href="#" class="active">2</a>
				<a href="#">3</a> <a href="#">4</a> <a href="#">5</a> <a href="#">6</a>
				<a href="#">&raquo;</a>
			</div> -->

		</div>

	</div>

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	
	   <script>

   $(function() {
	   /*  Submit form using Ajax */
	   $('#btnSearch').click(function (e) { 	   
	      //Prevent default submission of form
	      e.preventDefault();
	      debugger;
	      var myContextPath = $(this).attr('myContextPath');
	      
	      window.location = myContextPath+"/country/show-all?_search="+$('#countryName').val()+"&_pageIndex=0&_rows=5&_sort="+$('#sSort').val()+"";	      
	   });
	});

           
        </script>
</body>
</html>