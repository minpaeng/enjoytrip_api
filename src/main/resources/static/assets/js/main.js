window.onload = init;

function init() {
//	document.getElementById("btn-signup").addEventListener("click", signup);
	document.getElementById("btn-signin").addEventListener("click", signin);
	document.getElementById("password-check").addEventListener("keyup", password_check);
}

// 로그인 버튼 클릭 이벤트
function signin() {
	if (!document.querySelector("#login-user-id").value) {
		alert("아이디 입력!!");
		return;
	} else if (!document.querySelector("#login-password").value) {
		alert("비밀번호 입력!!");
		return;
	} else {
		let form = document.querySelector(".login-form");

		form.submit();
	}
}

// 회원가입 모달 닫을 때 form 초기화
//$("#signup-modal").on("hidden.bs.modal", function() {
//	document.getElementsByClassName("sign-form")[0].reset();
//	document.getElementsByClassName("info-form")[0].reset();
//	document.getElementById("password").style.border = "1px solid #dee2e6";
//	document.getElementById("password-check").style.border = "1px solid #dee2e6";
//});

// 로그인 모달 닫을 때 form 초기화
$("#signin-modal").on("hidden.bs.modal", function() {
	$(this).find("form")[0].reset();
});

// 회원가입 버튼 클릭 이벤트
//function signup() {
//	if (!document.getElementById("name").value) {
//		alert("이름을 입력해주세요.");
//		return;
//	} else if (!document.getElementById("user-id").value) {
//		alert("아이디를 입력해주세요.");
//		return;
//	} else if (!document.getElementById("password").value || !document.getElementById("password-check").value) {
//		alert("비밀번호를 입력해주세요.");
//		return;
//	} else if (
//		!document.getElementById("email").value ||
//		document.getElementById("domain").options[document.getElementById("domain").selectedIndex].text == "도메인 선택"
//	) {
//		alert("이메일을 입력해주세요.");
//		return;
//	} else if (document.getElementById("password").value != document.getElementById("password-check").value) {
//		alert("비밀번호가 일치하지 않습니다.");
//		return;
//	} else {
//		let form = document.querySelector(".sign-form");
//		form.submit();
//	}
//}

// 회원가입 - 비밀번호, 비밀번호 확인 값 일치여부 체크 이벤트
function password_check() {
	var pw = document.getElementById("password");
	var pw_check = document.getElementById("password-check");

	if (pw.value != "" && pw_check.value != "") {
		if (pw.value == pw_check.value) {
			pw.style.border = "1px solid green";
			pw_check.style.border = "1px solid green";
		} else {
			pw.style.border = "1px solid red";
			pw_check.style.border = "1px solid red";
		}
	}
}
