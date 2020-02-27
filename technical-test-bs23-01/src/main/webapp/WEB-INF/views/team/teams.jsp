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
		<div class="row"></div>

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
											<a class="h6 post__author-name fn" href="02-ProfilePage.html">${ team.name }
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
												<li><a href="edit?id=${team.id }">Edit Post</a></li>
												<li><a href="delete?id=${team.id }">Delete Post</a></li>
											</ul>
										</div>

									</div>

									<div class="post-thumb">
										<img
											src="${pageContext.request.contextPath }/images/${team.logo}"
											alt="photo">
									</div>

									<a href="/team/detail/${ team.id}" data-toggle="modal"
										data-target="#blog-post-popup" class="h2 post-title">${ team.name }</a>
									<p>${ team.teamDescription }...</p>
									<a href="#" data-toggle="modal" data-target="#blog-post-popup"
										class="btn btn-md-2 btn-border-think c-grey btn-transparent custom-color">Read
										More</a>
									<div class="post-additional-info inline-items">

										<a href="#" class="post-add-icon inline-items"> <svg
												class="olymp-heart-icon">
												<use
													xlink:href="svg-icons/sprites/icons.svg#olymp-heart-icon"></use></svg>
											<span>8</span>
										</a>
									</div>
									<div class="control-block-button post-control-button">
										<a href="#" class="btn btn-control"> <svg
												class="olymp-like-post-icon">
												<use
													xlink:href="svg-icons/sprites/icons.svg#olymp-like-post-icon"></use></svg>
										</a> <a href="#" class="btn btn-control"> <svg
												class="olymp-comments-post-icon">
												<use
													xlink:href="svg-icons/sprites/icons.svg#olymp-comments-post-icon"></use></svg>
										</a> <a href="#" class="btn btn-control"> <svg
												class="olymp-share-icon">
												<use
													xlink:href="svg-icons/sprites/icons.svg#olymp-share-icon"></use></svg>
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
	</div>
</body>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</html>