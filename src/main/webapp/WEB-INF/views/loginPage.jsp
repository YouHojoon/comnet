<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="\resources\grid.css" type="text/css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script src="\resources\jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>COMNET</title>
</head>
<body>
	<div class="logo">
		<img width="200" src="\resources\logo.jpg">
	</div>

	<form action="/login" method="POST" class="login">
		<div class="input-group mb-3">
			<input name="username" type="text" class="form-control" placeholder="학번">
			<div class="input-group-append">
				<span class="input-group-text">@sangmyung.kr</span>
			</div>
		</div>
		<div>
			<input type="password" id="password" name="password" class="form-control"
				placeholder="비밀번호" required>
		</div>
		<div class="button">
			<button id="login" type="submit" class="btn btn-primary">Login</button>
		</div>
		<div class="button">
			<a href="/register">회원가입</a>
		</div>
		<div>
			<a href="/findpw">비밀번호 찾기</a>
		</div>
		<input type="hidden" name="remember-me" value="true">
	</form>

</body>
</html>