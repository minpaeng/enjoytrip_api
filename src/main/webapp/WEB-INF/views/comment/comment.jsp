<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
	
<div class="card mb-2">
	<div class="card-header bg-light">
		<i class="fa fa-comment fa"></i> 댓글
	</div>
	<div class="card-body">
		<ul class="list-group list-group-flush">
			<li class="list-group-item">
				<form class="row" method="post" action="${root}/information/${post.id}/comment" id="form-comment">
					<div class="col-10" align="center"><textarea class="mt-2 mb-2 form-control" id="content" name="content" rows="1"></textarea></div>
					<div class="col-2" align="center"><button type="button" class="mt-2 mb-2 btn btn-dark" id="btn-comment">등록</button></div>
				</form>
			</li>
		</ul>
		<div class="card border-0 mt-1 mb-1">
			<ul class="list-group">
				<c:forEach var="comment" items="${comments}">
					<li class="list-group-item border-0 border-bottom">
						<div class="row mt-1">
							<div class="col-1">${comment.userId}</div>
							<div class="col-8">${comment.content}</div>
							<div class="col-2">${comment.registerDate}</div>
							<c:if test="${userInfo.userId eq comment.userId}">
							<a class="col-1" href="${root}/information/${post.id}/comment/${comment.id}">삭제</a>
							</c:if>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>

<script>
document.querySelector("#btn-comment").addEventListener("click", function() {
		document.querySelector("#form-comment").submit();
	});
</script>