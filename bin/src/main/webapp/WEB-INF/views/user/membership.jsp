<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<%-- session에 userInfo 객체 없는 경우(로그인 X) --%>
		<c:when test="${empty userInfo}">
			<!-- 회원가입 모달 start -->
			<div class="modal fade" id="signup-modal" data-bs-backdrop="static"
				data-bs-keyboard="false" tabindex="-1"
				aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">
								<i class="bi bi-chat-left-dots-fill text-info">회원가입</i>
							</h4>
							<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
						</div>

						<div class="modal-body">
							<form class="sign-form" method="POST" action="member/join">
								<div class="row m-1 mb-3">
									<div class="d-flex justify-content-start ps-0">
										<i class="bi bi-patch-question me-1"></i> <label for="user-id"
											class="form-label">아이디</label>
									</div>
									<input type="text" class="form-control" id="user-id"
										name="user-id" placeholder="아이디" />
								</div>
								<div class="row m-1 mb-3">
									<div class="d-flex justify-content-start ps-0">
										<i class="bi bi-patch-question me-1"></i> <label for="name"
											class="form-label">이름</label>
									</div>
									<input type="text" class="form-control" id="name" name="name"
										placeholder="이름" />
								</div>
								<div class="row m-1 mb-3">
									<div class="d-flex justify-content-start ps-0">
										<i class="bi bi-patch-question me-1"></i> <label
											for="password" class="form-label">비밀번호</label>
									</div>
									<input type="password" class="form-control" id="password"
										placeholder="비밀번호" name="password" />
								</div>
								<div class="row m-1 mb-3">
									<div class="d-flex justify-content-start ps-0">
										<i class="bi bi-patch-question me-1"></i> <label
											for="password-check" class="form-label">비밀번호확인</label>
									</div>
									<input type="password" class="form-control" id="password-check"
										placeholder="비밀번호확인" name="password-check" />
								</div>
								<div class="row m-1 mb-3">
									<div class="d-flex justify-content-start ps-0">
										<i class="bi bi-patch-question me-1"></i> <label for="email"
											class="form-label">이메일</label>
									</div>
									<div class="d-flex justify-content-start ps-0">
										<input type="email" class="form-control" id="email"
											placeholder="이메일" name="email" /> <span class="ms-2 me-2">@</span>
										<select id="domain" class="form-select" name="domain"
											aria-label=".form-select domain">
											<option selected>도메인 선택</option>
											<option value="ssafy.com">ssafy.com</option>
											<option value="gmail.com">gmail.com</option>
											<option value="naver.com">naver.com</option>
										</select>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" id="btn-signup"
								class="btn btn-primary btn-sm">회원가입</button>
							<button type="button" class="btn btn-outline-danger btn-sm"
								data-bs-dismiss="modal">취소</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 회원가입 모달 end -->
		</c:when>
		<%-- session에 userInfo 객체 있는 경우(로그인 O) --%>
		<c:otherwise>
			<!-- 회원정보수정 모달 start -->
			<div class="modal fade" id="signup-modal" data-bs-backdrop="static"
				data-bs-keyboard="false" tabindex="-1"
				aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">
								<i class="bi bi-chat-left-dots-fill text-info">회원정보수정</i>
							</h4>
							<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
						</div>

						<div class="modal-body">
							<form class="info-form" method="POST" action="member/update">
								<div class="row m-1 mb-3">
									<div class="d-flex justify-content-start ps-0">
										<i class="bi bi-patch-question me-1"></i> <label for="name"
											class="form-label">이름</label>
									</div>
									<input type="text" class="form-control" id="name" name="name"
										placeholder="이름" value="${userInfo.userName}" />
								</div>
								<div class="row m-1 mb-3">
									<div class="d-flex justify-content-start ps-0">
										<i class="bi bi-patch-question me-1"></i> <label
											for="password" class="form-label">비밀번호</label>
									</div>
									<input type="password" class="form-control" id="password"
										placeholder="비밀번호" name="password" />
								</div>
								<div class="row m-1 mb-3">
									<div class="d-flex justify-content-start ps-0">
										<i class="bi bi-patch-question me-1"></i> <label
											for="password-check" class="form-label">비밀번호확인</label>
									</div>
									<input type="password" class="form-control" id="password-check"
										placeholder="비밀번호확인" name="password-check" />
								</div>
								<div class="row m-1 mb-3">
									<div class="d-flex justify-content-start ps-0">
										<i class="bi bi-patch-question me-1"></i> <label for="email"
											class="form-label">이메일</label>
									</div>
									<div class="d-flex justify-content-start ps-0">
										<input type="email" class="form-control" id="email"
											value="${userInfo.emailId}" placeholder="이메일" name="email" /> <span
											class="ms-2 me-2">@</span> <select id="domain"
											class="form-select" name="domain"
											aria-label=".form-select domain">
											<option selected>도메인 선택</option>
											<option value="ssafy.com">ssafy.com</option>
											<option value="gmail.com">gmail.com</option>
											<option value="naver.com">naver.com</option>
										</select>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" id="btn-infoup" 
								class="btn btn-primary btn-sm">회원정보수정</button>
							<button type="button" class="btn btn-outline-danger btn-sm"
								data-bs-dismiss="modal">취소</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 회원정보수정 모달 end -->
		</c:otherwise>
	</c:choose>
<script>
	//회원정보수정
	document.querySelector("#btn-infoup").addEventListener("click", function() {
		if (!document.getElementById("name").value) {
			alert("이름을 입력해주세요.");
			return;
		} else if (!document.getElementById("user-id").value) {
			alert("아이디를 입력해주세요.");
			return;
		} else if (!document.getElementById("password").value || !document.getElementById("password-check").value) {
			alert("비밀번호를 입력해주세요.");
			return;
		} else if (
			!document.getElementById("email").value ||
			document.getElementById("domain").options[document.getElementById("domain").selectedIndex].text == "도메인 선택"
		) {
			alert("이메일을 입력해주세요.");
			return;
		} else if (document.getElementById("password").value != document.getElementById("password-check").value) {
			alert("비밀번호가 일치하지 않습니다.");
			return;
		} else {
			let form = document.querySelector(".info-form");
			form.submit();
		}
	});
</script>
</body>
</html>