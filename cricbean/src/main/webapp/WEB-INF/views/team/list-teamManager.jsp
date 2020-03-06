<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show team</title>
</head>
<body>


	<jsp:include page="/WEB-INF/views/common/sidebar.jsp" />
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<div class="container">
		<div class="row">
			<div class="col col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="ui-block">
					<div class="top-header top-header-favorit">
						<div class="top-header-thumb">
							<img src="${pageContext.request.contextPath }/img/${team.logo}"
								alt="nature">
							<div class="top-header-author">
								<div class="author-thumb">
									<img src="img/author-main2.jpg" alt="author">
								</div>
								<div class="author-content">
									<a href="#" class="h3 author-name">${team.name}</a>
									<div class="country">${team.teamDescription}</div>
								</div>
							</div>
						</div>
						<div class="profile-section">
							<div class="row">
								<div class="col col-xl-8 m-auto col-lg-8 col-md-12">
									<ul class="profile-menu">
																				
										<li><a  class="active" href="${pageContext.request.contextPath }/team/${team.id}/teamManager">Team Manager</a></li>
										</li>
										
										<li><a
											href="${pageContext.request.contextPath }/team/detail/${team.id}/players"
											>Players</a></li>
									</ul>
								</div>
							</div>

							<div class="control-block-button">
								<a href="#" class="btn btn-control bg-primary"> <svg
										class="olymp-star-icon">
										<use xlink:href="svg-icons/sprites/icons.svg#olymp-star-icon"></use></svg>
								</a> <a href="#" class="btn btn-control bg-purple"> <svg
										class="olymp-chat---messages-icon">
										<use
											xlink:href="svg-icons/sprites/icons.svg#olymp-chat---messages-icon"></use></svg>
								</a>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<div class="container">
		<div class="row">
			<div
				class="col col-xl-6 order-xl-2 col-lg-12 order-lg-1 col-sm-12 col-12">
				<div id="newsfeed-items-grid">
					<div class="ui-block"></div>


				</div>

			</div>

			<div
				class="col col-xl-3 order-xl-1 col-lg-6 order-lg-2 col-md-6 col-sm-12 col-12">
				<div class="ui-block">
					<div class="ui-block-title">
						<h6 class="title">Page Intro</h6>
						<a href="#" class="more"><svg class="olymp-three-dots-icon">
								<use
									xlink:href="svg-icons/sprites/icons.svg#olymp-three-dots-icon"></use></svg></a>
					</div>
					<div class="ui-block-content">

						<!-- W-Personal-Info -->

						<ul class="widget w-personal-info item-block">
							<li><span class="text">We are Rock Band from Los
									Angeles, now based in San Francisco, come and listen to us
									play!</span></li>
							<li><span class="title">Created:</span> <span class="text">September
									17th, 2013</span></li>
							<li><span class="title">Based in:</span> <span class="text">San
									Francisco, California</span></li>
							<li><span class="title">Contact:</span> <a href="#"
								class="text">greengoo_gigs@youmail.com</a></li>
							<li><span class="title">Website:</span> <a href="#"
								class="text">www.ggrock.com</a></li>
							<li><span class="title">Favourites:</span> <a href="#"
								class="text">5630 </a></li>
						</ul>

						<!-- ... end W-Personal-Info -->
						<!-- W-Socials -->

						<div class="widget w-socials">
							<h6 class="title">Other Social Networks:</h6>
							<a href="#" class="social-item bg-facebook"> <i
								class="fab fa-facebook-f" aria-hidden="true"></i> Facebook
							</a> <a href="#" class="social-item bg-twitter"> <i
								class="fab fa-twitter" aria-hidden="true"></i> Twitter
							</a> <a href="#" class="social-item bg-dribbble"> <i
								class="fab fa-dribbble" aria-hidden="true"></i> Dribbble
							</a>
						</div>


						<!-- ... end W-Socials -->
					</div>
				</div>

			</div>

			<div
				class="col col-xl-9 order-xl-1 col-lg-9 order-lg-2 col-md-6 col-sm-12 col-12">
				<div class="ui-block">
					<div class="ui-block-title">
						<h6 class="title">Team Manager</h6>
						<a href="#" class="more"><svg class="olymp-three-dots-icon">
								<use
									xlink:href="svg-icons/sprites/icons.svg#olymp-three-dots-icon"></use></svg></a>
					</div>
					<div class="ui-block-content">


						<div class="container">
							<div class="row">
								<div class="col col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="ui-block responsive-flex">
										<div class="ui-block-title">
											<div class="w-select">
												<div class="title">Order By:</div>
												<fieldset class="form-group">
													<select class="selectpicker form-control" name=sort
														id="sSort">
														<option value="ND">Name (Des)</option>
														<option value="NA">Name (Asc)</option>
													</select>
												</fieldset>
												<input type="hidden" name="pageIndex" value="${pageIndex}" />
											</div>
											<div class="w-search">
												<div class="form-group with-button">
													<input name=naem id="name" class="form-control" type="text"
														placeholder="Search team...">
													<button style=width: "40px" id="btnSearch">
														<img class="olymp-menu-icon left-menu-icon"
															src="${pageContext.request.contextPath }/img/search.png" />
													</button>
												</div>
											</div>
											<a href="#" class="more"><svg
													class="olymp-three-dots-icon">
								<use
														xlink:href="svg-icons/sprites/icons.svg#olymp-three-dots-icon"></use></svg></a>

											<a class="btn btn-primary"
												href="${pageContext.request.contextPath }/team/${team.id}/add/teamManager">
												<img class="olymp-menu-icon left-menu-icon"
												src="${pageContext.request.contextPath }/img/new-copy-48.png" />


												Add Team Manager
												<div class="ripple-container"></div>
											</a>
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
										<%-- <input type="hidden" name="teamId" id="teamId" value="${team.teamId}"/> --%>
										<c:forEach items="${members}" var="member">


											<div
												class="col col-xl-6 col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="ui-block">

													<!-- Post -->

													<article
														class="hentry post has-post-thumbnail thumb-full-width">

														<div class="post__author author vcard inline-items">
															<%-- <img 
											src="${pageContext.request.contextPath }/img/${member.user.logo}"
											alt="author"> --%>
															<div class="author-date">
																<a class="h6 post__author-name fn"
																	href="detail?id=${ member.id}">${ member.name }
																	<div class="post__date">
																		<time class="published" datetime="2017-03-24T18:18">
																		</time>
																	</div>
															</div>
															<div class="more">
																<svg class="olymp-three-dots-icon">
												<use
																		xlink:href="svg-icons/sprites/icons.svg#olymp-three-dots-icon"></use></svg>
																<ul class="more-dropdown">
																	<li><a
																		href="//${team.id}/delete/player?playerId=${ member.id}">Delete
																			Post</a></li>
																</ul>
															</div>
														</div>
														<div class="post-thumb" style="height: 150px;">
															<img
																src="${pageContext.request.contextPath }/img/${member.logo}"
																alt="photo">
														</div>

														<div class="control-block-button post-control-button">
															<a
																href="${pageContext.request.contextPath}/team/${team.id}/delete/teamManager?teamManagerId=${ member.id}"
																class="btn btn-control"> <img
																class="olymp-heart-icon"
																style="margin-bottom: 10px; margin-left: 2px;"
																src="${pageContext.request.contextPath }/img/close-window-64.png"
																alt="photo">
															</a>
														</div>
													</article>
													<button id='btnAssignCaptain'
														myContextPath="${pageContext.request.contextPath}"
														teamId="${team.id}" playerId="${member.id}"
														class="btn btn-md-2 btn-border-think c-grey btn-transparent custom-color"
														style=width: "40px">
														<img class="olymp-menu-icon left-menu-icon"
															src="${pageContext.request.contextPath }/img/search.png" />
														Assign as captain
													</button>
													<!-- ... end Post -->
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>

			</div>
		</div>
	</div>



</body>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
<script>
	$(function() {

		/*  Submit form using Ajax */
		$('#btnAssignCaptain').click(function(e) {
			//Prevent default submission of form
			e.preventDefault();
			if(confirm("Are you sure to assign this player as captain!")){
				
				var myContextPath = $(this).attr('myContextPath');
				var teamId = $(this).attr('teamId');
				var playerId = $(this).attr('playerId');

				$.ajax({
					url : myContextPath + "/team/"+teamId+"/add/captain?playerId="+playerId,
					success : function(data) {
						
						window.location = myContextPath+"/team/detail?id="+teamId;
					}
				});
				
				
			}else{
				
				debugger;
			}
			

		});
		
	});
</script>
</html>
