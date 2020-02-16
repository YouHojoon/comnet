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
    <form class="register-form" method="post" action="/register">
      <div class="input-group mb-3">
        <input type="text" name="email" class="form-control" placeholder="학번">
        <div class="input-group-append">
          <span class="input-group-text" id="basic-addon2">@sangmyung.kr</span>
        </div>
      </div>
      <div class="form-group">
        <input type="text" name="name" class="form-control" placeholder="홍길동">
      </div>
      <div class="form-group">
        <input type="text" name="phone" class="form-control" placeholder="01011111111">
      </div>
      <div class="form-group">
        <input type="password" name="password" class="form-control" placeholder="비밀번호">
      </div>
      <div class="form-group">
        <input type="password" id="password-check" class="form-control" placeholder="비밀번호 확인">
      </div>
      <div class="auth">
        <div class="form-group">
          <input type="text" id="auth-input" class="form-control" placeholder="인증문자">
          <button type="button" onclick="auth()" id="auth-button" class="btn btn-primary">인증</button>
          <div class="spinner-border text-primary" role="status">
  			<span class="sr-only">Loading...</span>
		  </div>
        </div>
      </div>
      <label id="field-label">관심 분야</label>
      <table id="user_field" class="table table-borderless">
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
              		<input class="form-check-input" name="field-check" type="checkbox" value="${field.fid}">
                	<label class="form-check-label" for="defaultCheck1">
                  		${field.fname}
                	</label>
                </div>
        	</th>
        </c:forEach>
        </tr>
      </table>
      <label id="field-label">관심 언어</label>
      <table id="user_language" class="table table-borderless">
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
              		<input class="form-check-input"  name="language-check" type="checkbox" value="${language.lid}">
                	<label class="form-check-label" for="defaultCheck1">
                  		${language.lname}
                	</label>
            	</div>
        	</th>
        </c:forEach>
        </tr>
      </table>
      <div class="form-group">
        <label id="memo-label">메모</label>
        <textarea name="memo" class="form-control" rows="3"></textarea>
      </div>
      <button type="button" id="register" class="btn btn-primary btn-lg">회원가입</button>
      <button type="button" id="back" onclick="location.href='login.html'" class="btn btn-primary btn-sm">Back</button>
    </form>
    <script type="text/javascript">
    	var authString;
    	var emailForm=/^20(1|2)\d{6}$/;
    	var phoneForm=/^01(0|1)\d{8}$/;
    	$("#register").click(function(event){
    		//입력 확인
    		if($("input[name=email]").val()==""){
    			alert("학번을 입력해주세요.");
    			$("input[name=email]").focus();
    			return;
    		}
    		else if(emailForm.test($("input[name=email]").val())==false){
    			alert("학번을 올바르게 입력해주세요.");
    			$("input[name=email]").focus();
    			return;
    		}
    		else if($("input[name=name]").val()==""){
    			alert("이름을 입력해주세요.");
    			$("input[name=name]").focus();
    			return;
    		}
    		else if($("input[name=phone]").val()==""){
    			alert("전화번호를 입력해주세요.");
    			$("input[name=phone]").focus();
    			return;
    		}
    		else if(phoneForm.test($("input[name=phone]").val())==false){
    			alert("전화번호를 올바르게 입력해주세요.\n01011111111");
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
    		else if($("input:checkbox[name=field-check]:checked").length==0){
    			alert("관심 분야를 하나 이상 선택해주세요.");
    			$("#user-field").focus();
    			return;
    		}
    		else if($("input:checkbox[name=language-check]:checked").length==0){
    			alert("관심 언어를 하나 이상 선택해주세요.");
    			$("#user-language").focus();
    			return;
    		}
    		else if(!authString){
    			//인증 확인
    			alert("이메일 인증을 해주세요.");
    			$("#auth-button").focus();
    			return;
    		}
    		var user_field=new Array();
    		var user_language=new Array();
    		$("#user_field input").each(function(){
    			if($(this).is(":checked")==true){
    				user_field.push($(this).val());
    			}
    		});
    		$("#user_language input").each(function(){
    			if($(this).is(":checked")==true){
    				user_language.push($(this).val());
    			}
    		});
    		$.ajax({
    			type:"post",
    			url:"/register",
    			traditional:true,
    			data:{email:$("input[name=email]").val(),password:$("input[name=password]").val()
    				,name:$("input[name=name]").val(),phone:$("input[name=phone]").val(),
    				memo:$("textarea[name=memo]").val(),user_field:user_field, user_language:user_language},
    			success:function(){
    				location.href="/loginPage";
    			}
    		});
    	});
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
    			authString=true;
    		}
    		else{
    			alert("인증 실패");
    			$("#auth-button").attr("onclick","auth()");
    		}
    	}
    </script>
  </body>
</html>