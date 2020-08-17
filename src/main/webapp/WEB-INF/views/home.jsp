<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="app" value="${pageContext.request.contextPath}" />
<%-- <%@include file="include/header.jsp"%> --%>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">HOME PAGE</h3>
					<h3 class="box-title"><a href="${app}/board/register">글쓰기</a></h3>
					<h3 class="box-title"><a href="${app}/board/listAll">리스트</a></h3>
					<h3 class="box-title"><a href="${app}/board/listPage">페이징 리스트</a></h3>
					<h3 class="box-title"><a href="${app}/sboard/list">검색 기능 리스트</a></h3>
				</div>
			</div>
		</div>
		<!--/.col (left) -->
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->


<%-- <%@include file="include/footer.jsp"%> --%>


