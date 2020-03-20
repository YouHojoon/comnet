<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
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
<title>COMNET</title>
</head>
<body id="info">
    <header>
      <h1>COMNET</h1>
    </header>
    <!--사용자 선택 영역-->
		<div id="select-field">
			<c:forEach items="${userField}" var="fid">
				<input type="hidden" value="${fid.fid}">
			</c:forEach>
		</div>
		<!--사용자 선택 언어-->
		<div id="select-language">
			<c:forEach items="${userLanguage}" var="lid">
				<input type="hidden" value="${lid.lid}">
			</c:forEach>
		</div>
    <input type="hidden" id="uid" value="${user.uid}">
    <form class="register-form">
      <div class="input-group mb-3">
        <input type="text" readonly="readonly" name="email" class="form-control" placeholder="학번" value="${user.email}">
        <div class="input-group-append">
          <span class="input-group-text">@sangmyung.kr</span>
        </div>
      </div>
      <div class="form-group">
        <input type="text" name="name" class="form-control" placeholder="홍길동" value="${user.name}">
      </div>
      <div class="form-group">
        <input type="text" name="phone" class="form-control" placeholder="01011111111" value="${user.phone}">
      </div>
      <div class="form-group">
        <input id="password" type="password" name="password" class="form-control" placeholder="비밀번호">
  		<button type="button" id="change-password" class="btn btn-primary">비밀번호 변경</button>
      </div>
      <div class="form-group">
        <input type="password" id="password-check" class="form-control" placeholder="비밀번호 확인">
      </div>
    
      <label id="field-label">관심 분야</label>
      <table id="user-field" class="table table-borderless">
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
                	<label class="form-check-label">
                  		${field.fname}
                	</label>
                </div>
        	</th>
        </c:forEach>
        </tr>
      </table>
      <label id="field-label">관심 언어</label>
      <table id="user-language" class="table table-borderless">
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
                	<label class="form-check-label">
                  		${language.lname}
                	</label>
            	</div>
        	</th>
        </c:forEach>
        </tr>
      </table>
      <div class="form-group">
        <label id="memo-label">메모</label>
        <textarea name="memo" class="form-control" rows="3">${user.memo}</textarea>
      </div>
      <button type="button" id="update" class="btn btn-primary btn-lg">수정</button>
      <button type="button" id="back" onclick="location.href='/mypage'" class="btn btn-primary btn-sm">Back</button>
    </form>
    <script type="text/javascript">
	    $("#select-field input").each(function(){//선택 영역 표시
			$("#user-field input[value="+$(this).val()+"]").attr("checked","checked");
		});
		$("#select-language input").each(function(){//선택 언어 표시
			$("#user-language input[value="+$(this).val()+"]").attr("checked","checked");
		});
    	var phoneForm=/^01(0|1)\d{8}$/;
    	$("#update").click(function(){
    		//입력 확인
    		if($("input[name=name]").val()==""){
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
    			alert("전화번호를 올바르게 입력해주세요.\n예:01011111111");
    			$("input[name=email]").focus();
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
    		var userField=new Array();
    		var userLanguage=new Array();
    		$("#user-field input").each(function(){
    			if($(this).is(":checked")==true){
    				userField.push(Number($(this).val()));
    			}
    		});
    		$("#user-language input").each(function(){
    			if($(this).is(":checked")==true){
    				userLanguage.push(Number($(this).val()));
    			}
    		});
    		var data={email:$("input[name=email]").val(), name:$("input[name=name]").val(),
                  phone:$("input[name=phone]").val(), memo:$("textarea[name=memo]").val(),
                  userField:userField, userLanguage:userLanguage};
    		$.ajax({
    			type:"PUT",
    			url:"/mypage/info?uid="+$("#uid").val(),
    			data:JSON.stringify(data),
    			contentType:"application/json",
    			success:function(){
    				location.href="/mypage";
    			}
    		});
    	});
    	$("#change-password").click(function(){
    		if($("input[name=password]").val()==""){
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
    		var data={eamil:$("input[name=email]").val(), password:$("input[name=password]").val()};
    		$.ajax({
    			type:"PATCH",
    			url:"/findpw",
    			data:JSON.stringify(data),
    			contentType:"application/json",
    			success:function(){
    				location.href="/mypage";
    			}
    		});
    	});
    </script>
  </body>
</html>