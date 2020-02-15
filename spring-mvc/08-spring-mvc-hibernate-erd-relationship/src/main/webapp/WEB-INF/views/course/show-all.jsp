<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

  <div class="container padding-t80">
    <div class="row">
      <div class="col-lg-10">       
        <div class="row">
			<c:forEach items="${courses}" var="course">
					   <div class="col-lg-4 col-md-4 mb-4">
		            <div class="card h-100">
		               <a href="#">
		               <img class="card-img-top" src="${pageContext.request.contextPath }/images/${course.courseImage}" alt=""></a> 
		              <div class="card-body">
		                <h4 class="card-title">
		                  <a href="<c:url value="/course/detail/${ course.courseId}" />" class="name">${ course.courseId }${ course.courseName }</a>
		                </h4>
		                <h5>$${course.coursePrice}</h5>
		                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>
		              </div>
		              <div class="card-footer">
		              
		              </div>
		            </div>
		          </div>				
			</c:forEach>
        </div>
      </div>
    </div>
 </div>
