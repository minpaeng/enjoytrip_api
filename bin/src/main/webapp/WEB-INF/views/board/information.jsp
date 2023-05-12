<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>
<c:choose>
	<c:when test="${empty userInfo}">
		<c:set var="loginStatus" value="no" />
	</c:when>
	<c:otherwise>
		<c:set var="loginStatus" value="ok" />
	</c:otherwise>
</c:choose>
<!-- 중앙 content start -->
<div class="container-fluid mt-5">
	<div class="row">
		<div class="row text-center">
			<div class="col m-5">
				<h1
					style="font-weight: bold; text-decoration-line: underline; text-decoration-thickness: 6px; text-decoration-color: rgb(189, 201, 255)">
					좋은 정보 공유해요!!!</h1>
			</div>
		</div>
		<div class="row justify-content-between ps-2 pe-2 ms-2 ms-2">
			<div class="col-auto">
				<a type="button" id="write-button" class="btn btn-outline-primary"
					style="font-weight: bold" href=""> 글쓰기 </a>
			</div>
			<div class="col-auto">
				<form id="form-search">
	                <input type="hidden" name="pgno" value="1"/>
					<div class="row pe-2 me-2">
						<select class="col form-select me-1" name="key" id="key"
							aria-label=".form-select example">
							<option selected value="">검색조건</option>
							<option value="info_board_id">글번호</option>
							<option value="title">제목</option>
							<option value="user_id">작성자</option>
						</select> <input type="text" name="word" id="word" placeholder="검색어입력..."
							class="col form-control me-1" />
						<button type="button" id="btn-search" class="col btn btn-outline-secondary me-2">검색</button>
					</div>
				</form>
			</div>

			<div class="row mt-3">
				<div class="d-flex justify-content-center">
					<table class="table table-striped table-bordered text-center mb-5">
						<thead>
							<tr>
								<th scope="col">글번호</th>
								<th scope="col">제목</th>
								<th scope="col">작성자</th>
								<th scope="col">작성일</th>
								<th scope="col">조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="post" items="${posts}">
								<tr>
									<th scope="row">${post.id}</th>
									<td><a href="#" class="post-title link-dark"
										data-no="${post.id}" style="text-decoration: underline;">${post.title}</a>
									</td>
									<td>${post.userId}</td>
									<td>${post.registerDate}</td>
									<td>${post.hit}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="d-flex justify-content-center">
					<nav aria-label="Page navigation example">
						${navigation.navigator}
					</nav>
				</div>
				<form id="form-param" method="get" action="">
			      <input type="hidden" id="p-action" name="action" value="">
			      <input type="hidden" id="p-pgno" name="pgno" value="">
			      <input type="hidden" id="p-key" name="key" value="">
			      <input type="hidden" id="p-word" name="word" value="">
			    </form>
			</div>
		</div>
	</div>

	<div class="row"></div>
</div>
<!-- 중앙 content end -->
<%@ include file="../common/footer.jsp"%>
<script src="${root}/assets/js/main.js"></script>
<script>
	let titles = document.querySelectorAll(".post-title");
	titles.forEach(function (title) {
	  title.addEventListener("click", function () {
	    console.log(this.getAttribute("data-no"));
	    location.href = "${root}/board?action=mvview&postno=" + this.getAttribute("data-no");
	  });
	});

	document.querySelector("#write-button").addEventListener("click", function() {
		var state = "${loginStatus}";
		if (state == "ok")
			document.querySelector("#write-button").href = "${root}/board/post.jsp";
		else {
			alert("로그인이 필요한 서비스입니다.")
			document.querySelector("#write-button").href = "";
		}
	});
	
	document.querySelector("#btn-search").addEventListener("click", function () {
  	  let form = document.querySelector("#form-search");
        form.setAttribute("action", "${root}/information");
        form.submit();
    });
	
	let pages = document.querySelectorAll(".page-link");
    pages.forEach(function (page) {
      page.addEventListener("click", function () {
        console.log(this.parentNode.getAttribute("data-pg"));
        document.querySelector("#p-action").value = "information";
     	  document.querySelector("#p-pgno").value = this.parentNode.getAttribute("data-pg");
     	  document.querySelector("#p-key").value = "${param.key}";
     	  document.querySelector("#p-word").value = "${param.word}";
        document.querySelector("#form-param").submit();
      });
    });
</script>
</body>
</html>
