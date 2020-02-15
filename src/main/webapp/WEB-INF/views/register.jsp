<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Black+Han+Sans&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<script src="\resources\jquery.min.js"></script>
<link rel="stylesheet" href="\resources\grid.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Comnet</title>
</head>
<body id="user-register">
    <header>
      <h1>COMNET</h1>
    </header>
    <form class="register-form" method="post">
      <div class="input-group mb-3">
        <input type="text" name="email" class="form-control" placeholder="학번" pattern="20(1|2)[0-9]\d{5}" required>
        <div class="input-group-append">
          <span class="input-group-text" id="basic-addon2">@sangmyung.kr</span>
        </div>
      </div>
      <div class="form-group">
        <input type="password" name="password" class="form-control" placeholder="비밀번호">
      </div>
      <div class="form-group">
        <input type="password" class="form-control" placeholder="비밀번호확인">
      </div>
      <div class="auth">
        <div class="form-group">
          <input type="text" id="auth-input"class="form-control" placeholder="인증문자">
          <button type="button" onclick="auth()" id="auth-button" class="btn btn-primary">인증</button>
          <div class="spinner-border text-primary" role="status">
  			<span class="sr-only">Loading...</span>
		  </div>
        </div>
      </div>
      <label id="field-label">관심 영역</label>
      <table class="table table-borderless">
        <tr>
        <c:set var="cnt" value="0"/>
        <c:forEach var="field" items="${fieldList}">
        	<c:set var="cnt" value="${cnt+1}"/>
        	<c:if test="${cnt > 6}"><!--한 줄당 6개씩 출력-->
        		</tr>
        		<tr>
        		<c:set var="cnt" value="1"/>
        	</c:if>
        	<th scope="col">
        		<div class="form-check">
              		<input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                	<label class="form-check-label" for="defaultCheck1">
                  		${field.fname}
                	</label>
                </div>
        	</th>
        </c:forEach>
        </tr>
      </table>
      <label id="field-label">관심 언어</label>
      <table class="table table-borderless">
        <tr>
        <c:set var="cnt" value="0"/>
        <c:forEach var="language" items="${languageList}">
        	<c:set var="cnt" value="${cnt+1}"/>
        	<c:if test="${cnt > 6}"><!--한 줄당 6개씩 출력-->
        		</tr>
        		<tr>
        		<c:set var="cnt" value="1"/>
        	</c:if>
        	<th scope="col">
        		<div class="form-check">
              		<input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                	<label class="form-check-label" for="defaultCheck1">
                  		${language.lname}
                	</label>
            	</div>
        	</th>
        </c:forEach>
        </tr>
      </table>
      <div class="form-group">
        <label id="memo">메모</label>
        <textarea class="form-control" rows="3"></textarea>
      </div>
      <button type="button" id="register" class="btn btn-primary btn-lg">회원가입</button>
      <button type="button" id="back" onclick="location.href='login.html'" class="btn btn-primary btn-sm">Back</button>
    </form>
    <script type="text/javascript">
    	var authString;
    	function auth(){//인증메일 전송
    		if($("input[name=email]").val()==""){
    			alert("Email을 입력해주세요.");
    			return;
    		}
    		$("#auth-button").css("display","none");
    		$(".spinner-border").css("display","inline-block");
    		$.ajax({
    			type:"GET",
    			url:"/auth?email="+$("input[name=email]").val(),
    			success:function(data){
    				authString=data;
    				$(".spinner-border").css("display","none");
    				$("#auth-button").css("display","inline-block");
    				$("#auth-button").attr("onclick","check()");
    				alert("인증 메일이 전송되었습니다.");
    			}
    		});
    	}
    	function check(){//인증메일 확인
    		if(authString===$("#auth-input").val()){
    			alert("인증 성공");
    			$("#auth-input").attr("readonly","readonly");
    		}
    		else{
    			alert("인증 실패");
    			$("#auth-button").attr("onclick","auth()");
    		}
    	}
    </script>
  </body>
</html>