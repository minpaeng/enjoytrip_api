// 목록 버튼 클릭 이벤트
document.querySelector(".post-cancel").addEventListener("click", function () {
  location.href = "../board?action=information";
});

// 글작성 버튼 클릭 이벤트
document.querySelector("#btn-create-post").addEventListener("click", function () {
        if (!document.querySelector("#title").value) {
          alert("제목 입력!!");
          return;
        } else if (!document.querySelector("#context").value) {
          alert("내용 입력!!");
          return;
        } else {
          let form = document.querySelector("#form-write");
          form.setAttribute("action", "../board");
          form.submit();
        }
});

