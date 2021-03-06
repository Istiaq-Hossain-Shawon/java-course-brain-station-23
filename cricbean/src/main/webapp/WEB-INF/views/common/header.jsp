<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Cricbean</title>


</head>
<body>

<header class="header" id="site-header">
	<div class="page-title">
		
		
	</div>
	
	<div class="header-content-wrapper">
		<img src="${pageContext.request.contextPath }/img/cricbean_logo.png" alt="Cricbean" style='    height: 70px;'>
			
		
		<div class="control-block">
			<div class="author-page author vcard inline-items more">
				<div class="author-thumb">
					<img alt="author" src="${pageContext.request.contextPath }/img/user.png" class="avatar" style='width: 50px;'>
					<span class="icon-status online"></span>
					<div class="more-dropdown more-with-triangle">
						<div class="mCustomScrollbar" data-mcs-theme="dark">
							<div class="ui-block-title ui-block-title-small">
								<h6 class="title">Your Account</h6>
							</div>
							<ul class="account-settings">								
								<li>
									<a href="${pageContext.request.contextPath}/team/add">
										<svg class="olymp-star-icon left-menu-icon"  data-toggle="tooltip" data-placement="right"   data-original-title="FAV PAGE"><use xlink:href="svg-icons/sprites/icons.svg#olymp-star-icon"></use></svg>

										<span>Create Team</span>
									</a>
								</li>
								<li>								 
									<a href="${pageContext.request.contextPath}/logout">
										<svg class="olymp-logout-icon"><use xlink:href="svg-icons/sprites/icons.svg#olymp-logout-icon"></use></svg>

										<span>Log Out</span>
									</a>
								</li>
							</ul>
						</div>

					</div>
				</div>
				<a href="02-ProfilePage.html" class="author-name fn">
					<div class="author-title">
					<sec:authentication var="principal" property="principal" />
					${principal.username}
						<svg class="olymp-dropdown-arrow-icon">
						<use xlink:href="svg-icons/sprites/icons.svg#olymp-dropdown-arrow-icon"></use></svg>
					</div>
					<span class="author-subtitle"></span>
				</a>
			</div>

		</div>
	</div>

</header>

<!-- ... end Header-BP -->




<!-- ... end Responsive Header-BP -->
<div class="header-spacer"></div>





<!-- Header-BP -->

</body>
