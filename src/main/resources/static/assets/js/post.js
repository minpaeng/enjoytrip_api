// 목록 버튼 클릭 이벤트
document.querySelector(".post-cancel").addEventListener("click", function () {
  location.href = "${root}/information";
});

// 글작성 버튼 클릭 이벤트
document.querySelector("#btn-create-post").addEventListener("click", function () {
        if (!document.querySelector("#title").value) {
          alert("제목 입력!!");
          return;
        } else if (!document.querySelector("#content").value) {
          alert("내용 입력!!");
          return;
        } else {
          document.querySelector("#form-write").submit();
        }
});

