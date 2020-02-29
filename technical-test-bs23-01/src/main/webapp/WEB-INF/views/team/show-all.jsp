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
						<div class="h6 title">Team List</div>
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
								<input name=naem  id="name" class="form-control" type="text"
									placeholder="Search team...">
								<button style=width:"40px" id="btnSearch" myContextPath="${pageContext.request.contextPath}">
									<img  class="olymp-menu-icon left-menu-icon"
                                  src="${pageContext.request.contextPath }/img/search.png" />									
								</button>
							</div>
						</div>
						<a href="#" class="more"><svg class="olymp-three-dots-icon">
								<use
									xlink:href="svg-icons/sprites/icons.svg#olymp-three-dots-icon"></use></svg></a>
									
						<a class="btn btn-primary" href="${pageContext.request.contextPath }/team/add"> 
						 <img  class="olymp-menu-icon left-menu-icon"
                                  src="${pageContext.request.contextPath }/img/new-copy-48.png" />
						
						
						Add Team<div class="ripple-container"></div></a>
					</form>
				</div>
			</div>
		</div>
	</div>


</div>




	<div class="container padding-t80">
		<div class="row">
			<div class="col-lg-10">
				<div class="row">
					<c:forEach items="${teams}" var="team">


						<div class="col col-xl-6 col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="ui-block">

								<!-- Post -->

								<article class="hentry post has-post-thumbnail thumb-full-width">

									<div class="post__author author vcard inline-items">
										<img 
											src="${pageContext.request.contextPath }/img/${team.country.logo}"
											alt="author">
										<div class="author-date">
											<a class="h6 post__author-name fn" 
											href="detail?id=${ team.id}">${ team.name }
												<div class="post__date">
													<time class="published" datetime="2017-03-24T18:18">
														7 hours ago </time>
												</div>
										</div>
										<div class="more">
											<svg class="olymp-three-dots-icon">
												<use
													xlink:href="svg-icons/sprites/icons.svg#olymp-three-dots-icon"></use></svg>
											<ul class="more-dropdown">
												<li><a href="edit?id=${ team.id}">Edit Post</a></li>
												<li><a href="delete?id=${ team.id}">Delete Post</a></li>
											</ul>
										</div>

									</div>

									<div class="post-thumb">
										<img
											src="${pageContext.request.contextPath }/img/${team.logo}"
											alt="photo">
									</div>

									<%-- <a href="/team/detail/${ team.id}" data-toggle="modal"
										data-target="#blog-post-popup" class="h2 post-title">${ team.name }</a> --%>
									<p>${ team.teamDescription }...</p>
									
									
								 	<div class="control-block-button post-control-button">
										<a href="edit?id=${ team.id}" class="btn btn-control"> 
													<img class="olymp-heart-icon" style="margin-bottom: 10px;margin-left: 2px;"
											src="${pageContext.request.contextPath }/img/pencil.png"
											alt="photo">
												
										</a>
										
										
									</div> 
								</article>

								<!-- ... end Post -->
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			
			


		</div>
		<div class="row">
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
		</div>
	</div>
</body>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	   <script>

   $(function() {
	   
	   
	   /*  Submit form using Ajax */
	   $('#btnSearch').click(function (e) { 	   
	      //Prevent default submission of form
	      e.preventDefault();
	      debugger;
	      var myContextPath = $(this).attr('myContextPath');
	      
	      window.location = myContextPath+"/team/show-all?_search="+$('#name').val()+"&_pageIndex=0&_rows=5&_sort="+$('#sSort').val()+"";	      
	   });
	});

           
        </script>
</html>