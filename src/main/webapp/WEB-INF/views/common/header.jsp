<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Enjoy trip</title>
<link rel="stylesheet" href="${root}/assets/css/main.css" />

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous" />

<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap"
	rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>

<%@ include file="../user/membership.jsp"%>

	<!-- 로그인 모달 start -->
	<div class="modal fade" id="signin-modal" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">
						<i class="bi bi-chat-left-dots-fill text-info">로그인</i>
					</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<div class="modal-body">
					<form class="login-form" method="POST" action="${root}/member/login">
						<div class="row m-1 mb-3">
							<div class="d-flex justify-content-start ps-0">
								<i class="bi bi-patch-question me-1"></i> <label
									for="login-user-id" class="form-label">아이디</label>
							</div>
							<input type="text" class="form-control" id="login-user-id"
								name="login-user-id" placeholder="아이디" />
						</div>
						<div class="row m-1 mb-3">
							<div class="d-flex justify-content-start ps-0">
								<i class="bi bi-patch-question me-1"></i> <label
									for="login-password" class="form-label">비밀번호</label>
							</div>
							<input type="password" class="form-control" id="login-password"
								placeholder="비밀번호" name="login-password" />
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" id="btn-signin"
						class="btn btn-primary btn-sm">로그인</button>
					<button type="button" class="btn btn-outline-danger btn-sm"
						data-bs-dismiss="modal">취소</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 로그인 모달 end -->

	<!-- 상단 navbar start -->
	<header style="font-family: 'Black Han Sans', sans-serif">
		<nav class="navbar navbar-light bg-light navbar-expand-lg">
			<div class="section-content p-1 mt-3 container-fluid">
				<a class="navbar-brand text-primary fw-bold"
					href="${root}/board/home"> <img src="${root}/assets/img/trip-logo.png"
					alt="" width="130px" />
				</a>

				<div class="collapse navbar-collapse" style="font-weight: bold"
					id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-lg-0">
						<li class="nav-item d-flex"><a class="nav-link"
							aria-current="page" href="${root}/board/attraction">전국관광지 정보</a> 
							<a class="nav-link information-page" aria-current="page"
							href="${root}/information?pgno=1">공지사항</a></li>
					</ul>

					<c:choose>
						<%-- session에 userInfo 객체 없는 경우(로그인 X) --%>
						<c:when test="${empty userInfo}">
							<ul class="navbar-nav mb-2 me-2 mb-lg-0 before-login "
								style="display: flex">
								<li class="nav-item">
									<button type="button" class="nav-link bg-light"
										aria-current="page" data-bs-toggle="modal"
										data-bs-target="#signup-modal" style="border-width: 0">
										회원가입</button>
								</li>
								<li class="nav-item">
									<button type="button" class="nav-link bg-light"
										aria-current="page" data-bs-toggle="modal"
										data-bs-target="#signin-modal" style="border-width: 0">
										로그인</button>
								</li>
							</ul>
						</c:when>
						<%-- session에 userInfo 객체 있는 경우(로그인 O) --%>
						<c:otherwise>
							<!-- 로그인 후 -->
							<ul class="after-login navbar-nav mb-2 me-2 mb-lg-0"
								style="display: flex">
								<li class="nav-item"><a class="nav-link disabled">${userInfo.userName}님
										반갑습니다.</a></li>
								<li class="nav-item"><a id="logout" class="nav-link"
									aria-current="page" href="${root}/member/logout">로그아웃</a></li>
								<li class="dropdown"><a class="nav-link dropdown">마이페이지</a>
									<div class="dropdown-content">
										<a href="${root}/member/delete">회원탈퇴</a>
									<a aria-current="page" data-bs-toggle="modal"
										data-bs-target="#signup-modal" href="#" >회원정보수정</a> 
									</div></li>
							</ul>
						</c:otherwise>
					</c:choose>

				</div>
			</div>
		</nav>
	</header>
	<!-- 상단 navbar end -->