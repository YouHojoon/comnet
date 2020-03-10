<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="\resources\grid.css" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Black+Han+Sans&display=swap" rel="stylesheet">
    <script src="\resources\jquery.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>COMNET</title>
</head>
  <body>
    <header>
      <button type="button" id="left" class="btn btn-default" onclick="location.href='/board'" aria-label="Left Align">
        <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
      </button>
      <h1>COMNET</h1>
    </header>
    <sec:authentication var="uid" property="principal.userVO.uid"/>
    <div class="mypage">
      <div class="menu">
        <div class="name">
          <h1 class="display-4">정보 수정</h1>
        </div>
      </div>
      <div class="menu">
        <div class="name" onclick="location.href='/mypage/myproject?uid=${uid}'">
          <h1 class="display-4">나의 프로젝트</h1>
        </div>
      </div>
      <div class="menu">
        <div class="name">
          <h1 class="display-4">내가 지원한 프로젝트</h1>
        </div>
      </div>
      <div class="menu">
        <div class="name" id="logout">
          <h1 class="display-4">로그아웃</h1>
        </div>
      </div>
    </div>
    <script type="text/javascript">
    	$("#logout").click(function(){
    		$.ajax({
    			type: "POST",
    			url: "/logout",
    			success: function(){
    				location.href="/";
    			}
    		});
    	});
    </script>
  </body>
</html>