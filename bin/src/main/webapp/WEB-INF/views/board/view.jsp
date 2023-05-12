<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
	<c:if test="${post eq null}">
		<script>
		alert("글이 삭제되었거나 부적절한 URL 접근입니다.");
		location.href = "${root}/board?action=information";
		</script>
	</c:if>
      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="sky">글보기</mark>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="row my-2">
            <h2 class="text-secondary px-5">${post.articleNo}. ${post.subject}</h2>
          </div>
          <div class="row">
            <div class="col-md-8">
              <div class="clearfix align-content-center">
                <img
                  class="avatar me-2 float-md-start bg-light p-2"
                  src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
                />
                <p>
                  <span class="fw-bold">${post.userId}</span> <br />
                  <span class="text-secondary fw-light"> ${post.registerTime} 조회 : ${post.hit} </span>
                </p>
              </div>
            </div>
            <div class="divider mb-3"></div>
            <div class="text-secondary">
              ${post.content}
            </div>
            <div class="divider mt-3 mb-3"></div>
            <div class="d-flex justify-content-end">
              <button type="button" id="btn-list" class="btn btn-outline-primary mb-3">
                글목록
              </button>
              <c:if test="${userInfo.id eq post.userId}">
              <button type="button" id="btn-mv-modify" class="btn btn-outline-success mb-3 ms-1">
                글수정
              </button>
              <button type="button" id="btn-delete" class="btn btn-outline-danger mb-3 ms-1">
                글삭제
              </button>
              </c:if>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script>
      document.querySelector("#btn-list").addEventListener("click", function () {
        location.href = "${root}/board?action=information";
      });
      
      document.querySelector("#btn-mv-modify").addEventListener("click", function () {
        location.href = "${root}/board?action=mvmodify&postno=${post.articleNo}";
      });
      
      document.querySelector("#btn-delete").addEventListener("click", function () {
        location.href = "${root}/board?action=delete&postno=${post.articleNo}";
      });
      
      
    </script>
<%@ include file="../common/footer.jsp" %>
<!-- 스크립트 로드 -->
<script type="text/javascript" src="${root}/assets/js/main.js"></script>
</body>
</html>