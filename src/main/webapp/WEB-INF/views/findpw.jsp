<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="\resources\grid.css" type="text/css">
    <script src="\resources\jquery.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>COMNET</title>
</head>
<body>
    <div class="logo" >
      <img width="200" src="\resources\logo.jpg">
    </div>
    <div class="login">
      <div class="input-group mb-3">
        <input type="text" name="email" class="form-control" placeholder="학번" pattern="20(1|2)[0-9]\d{5}" required>
        <div class="input-group-append">
          <span class="input-group-text">@sangmyung.kr</span>
        </div>
      </div>
      <div class="password-check-auth">
        <div class="input-group mb-3">
          <input type="text" id="auth-input" class="form-control" placeholder="인증번호">
          <div class="input-group-append">
            <button class="btn btn-primary" type="button" id="auth-button" onclick="auth()">인증</button>
          </div>
          <div class="spinner-border text-primary" role="status">
  			<span class="sr-only">Loading...</span>
		  </div>
        </div>
      </div>
      <div class="input-group mb-3">
      	<input type="password" id="password" name="password" class="form-control" placeholder="변경할 비밀번호">
      </div>
      <div>
      	<input type="password" id="password-check" class="form-control" placeholder="비밀번호 확인">
      </div>
      <div class="button">
        <button type="button" id="change" class="btn btn-primary">변경</button>
      </div>
      <div class="button">
        <button type="button" class="btn btn-primary btn-sm">Back</button>
      </div>
    </div>
	<script type="text/javascript">
		var authString;
		var emailForm=/^20(1|2)\d{6}$/;
		$("#change").click(function(){
			if($("input[name=email]").val() == "") {
				alert("Email을 입력해주세요.");
				return;
			}
			else if(emailForm.test($("input[name=email]").val())==false){
    			alert("학번을 올바르게 입력해주세요.");
    			$("input[name=email]").focus();
    			return;
    		}
			else if($("input[name=password]").val()==""){
    			alert("비밀번호를 입력해주세요.");
    			$("input[name=password]").focus();
    			return;
    		}
    		else if($("input[name=password]").val().length < 8){
    			alert("비밀번호를 8자 이상 입력해주세요.");
    			$("input[name=password]").focus();
    			return;
    		}
    		else if($("input[name=password]").val()!=$("#password-check").val()){
    			alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
    			$("#password-check").focus();
    			return;
    		}
    		else if(authString!=true){
    			//인증 확인
    			alert("이메일 인증을 해주세요.");
    			$("#auth-button").focus();
    			return;
    		}
			var data={email: $("input[name=email]").val(), password: $("input[name=password]").val()}
			$.ajax({
				type: "PATCH",
				url: "/findpw",
				data: JSON.stringify(data),
				contentType: "application/json",
				success: function(){
					location.href="/";
				}
			});
		});
		function auth() {//인증메일 전송
			if($("input[name=email]").val() == "") {
				alert("Email을 입력해주세요.");
				return;
			}
			$("#auth-button").css("display", "none");
			$(".spinner-border").css("display", "inline-block");
			$.ajax({
				type : "GET",
				url : "/auth?email=" + $("input[name=email]").val(),
				success : function(data) {
					if (data === "no") {
						alert("아이디가 존재하지 않습니다.");
						$("input[name=email]").focus();
					} else {
						authString = data;
						alert("인증 메일이 전송되었습니다.");
						$("#auth-button").attr("onclick", "check()");
					}
					$(".spinner-border").css("display", "none");
					$("#auth-button").css("display", "inline-block");
				}
			});
		}
		function check() {//인증메일 확인
			if (authString === $("#auth-input").val()) {
				alert("인증 성공");
				$("input[name=email]").attr("readonly", "readonly");
				$("#auth-input").attr("readonly", "readonly");
				$("#auth-button").attr("disabled","disabled");
				authString = true;
			} else {
				alert("인증 실패");
				$("#auth-button").attr("onclick", "auth()");
			}
		}
	</script>
</body>
</html>