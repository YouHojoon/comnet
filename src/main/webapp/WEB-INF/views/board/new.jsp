<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Black+Han+Sans&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<script src="\resources\jquery.min.js"></script>
<link rel="stylesheet" href="\resources\grid.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<title>COMNET</title>
</head>
<body class="new">
<header>
      <h1>COMNET</h1>
    </header>
    <form class="register-form">
      	<input type="hidden" id="uid" value="<sec:authentication property='principal.userVO.uid'/>">
      <div class="form-group">
        <input type="text" id="title" class="form-control" placeholder="프로젝트 제목">
      </div>
      <label>마감기한</label>
      <div class="row">
        <div class="col">
          <select class="form-control" onchange="makeMonth()" id="year">
          </select>
        </div>
        <div class="col">
          <select class="form-control" onchange="makeDay()" id="month">
          </select>
        </div>
        <div class="col">
          <select class="form-control" id="day">
          </select>
        </div>
        <div class="col">
          <div class="recruit">
            <input class="form-check-input" type="checkbox" value="1" id="always">
              <label class="form-check-label">
                	상시모집
              </label>
          </div>

        </div>
      </div>
      <label id="field-label">모집 분야</label>
      <table id="board-field" class="table table-borderless">
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
      <label id="field-label">모집 언어</label>
      <table id="board-language" class="table table-borderless">
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
          <label>프로젝트 설명</label>
          <textarea id="content" class="form-control" rows="3"></textarea>
        </div>
        <div class="partner-limit">
	    	<div class="form-group">
          		<input type="text" class="form-control" placeholder="팀원제한(최대 999명)" id="partner-limit-input">
        	</div>
	        <div class="partner-limit-check">
		        <div class="recruit">
		            <input class="form-check-input" type="checkbox" value="1" id="unlimit">
		              <label class="form-check-label">
		                	제한없음
		              </label>
		        </div>
	        </div>
        </div>
        <div class="form-group">
	    	<input type="text" id="contact" class="form-control" placeholder="연락처">
	    </div>
        <div>
          <button type="button" id="new" class="btn btn-primary btn-lg">작성</button>
        </div>
        <button type="button" id="back" onclick="location.href='/board'" class="btn btn-primary btn-sm">Back</button>
    </form>
    <script type="text/javascript">
      var Calander=new Date();
      var current= new Date();
      $("#year").append("<option selected value="+Calander.getFullYear()+">"+Calander.getFullYear()+"년"+"</option>");
      var nextYear=Calander.getFullYear()+1;
      $("#year").append("<option value="+nextYear+">"+nextYear+"년"+"</option>");
      makeMonth();
      makeDay();
      //마감기한 달력 생성
      function makeMonth(){//달 생성
    	 var str="";
    	 Calander.setFullYear($("#year").val());
    	 if($("#year").val()!=current.getFullYear())
    		 Calander.setMonth(0);
    	 else
    		 Calander.setMonth(current.getMonth());
    	 for(var i=Calander.getMonth()+1; i<=12; i++)
       	 	str+="<option value="+i+">"+i+"월"+"</option>";
         $("#month").html(str);
         makeDay();
      }
      function makeDay(){//일 생성
    	  var str="";
          Calander.setMonth($("#month").val()-1);
          if($("#year").val()==current.getFullYear() && $("#month").val()==current.getMonth()+1)
          	Calander.setDate(current.getDate());
          else
        	Calander.setDate(1);
          do {
            var date=Calander.getDate();
            str+="<option value="+date+">"+date+"일"+"</option>";
            Calander.setDate(date+1);
          } while(Calander.getDate()!=1)
          $("#day").html(str);
      }
      $("#always").click(function(){
        if($("#always").prop("checked")){
          $("#year").attr("disabled","true");
          $("#month").attr("disabled","true");
          $("#day").attr("disabled","true");
        }
        else{
          $("#year").removeAttr("disabled");
          $("#month").removeAttr("disabled");
          $("#day").removeAttr("disabled");
        }
      });
      $("#unlimit").click(function(){
          if($("#unlimit").prop("checked")){
            $("#partner-limit-input").attr("readonly","readonly");
          }
          else{
        	  $("#partner-limit-input").removeAttr("readonly");
          }
        });
      $("#new").click(function(){
    	 
    	var partner_limit_form=/^[1-9]{1,3}$/;
    	if($("#title").val()==""){
    		alert("제목을 입력해주세요.");
    		$("#title").focus();
    		return;
    	}
    	else if($("input:checkbox[name=field-check]:checked").length==0){
  			alert("모집 분야를 하나 이상 선택해주세요.");
  			$("#user-field").focus();
  			return;
  		}
  		else if($("input:checkbox[name=language-check]:checked").length==0){
  			alert("모집 언어를 하나 이상 선택해주세요.");
  			$("#user-language").focus();
  			return;
  		}
    	else if($("#content").val()==""){
    		alert("내용을 입력해주세요.");
    		$("#content").focus();
    		return;
    	}
    	else if(!$("#unlimit").prop("checked") && !partner_limit_form.test($("#partner-limit-input").val())){
    		alert("팀원 제한을 입력하거나 제한없음을 체크해주세요.");
    		$("#partner-limit").focus();
    		return;
    	}
    	else if($("#contact").val()==""){
    		alert("연락처를 입력해주세요.");
    		$("#contact").focus();
    		return;
    	}
  		
    	var boardField=new Array();
  		var boardLanguage=new Array();
  		$("#board-field input").each(function(){
  			if($(this).is(":checked")==true){
  				boardField.push($(this).val());
  			}
  		});
  		$("#board-language input").each(function(){
  			if($(this).is(":checked")==true){
  				boardLanguage.push($(this).val());
  			}
  		});
  		
  		var partner_limit;
  		if($("#unlimit").prop("checked")){
  			partner_limit=999;
  		}
  		else{
  			partner_limit=$("#partner-limit-input").val();
  		}
  		var deadline=new Date();
  		if($("#always").prop("checked")){
  			deadline.setFullYear(9999);
  		}
  		else{
  			deadline.setFullYear($("#year").val());
  	  		deadline.setMonth($("#month").val()-1);
  	  		deadline.setDate($("#day").val());
  		}
  		
  		$.ajax({
  			type: "POST",
  			url: "/board/new",
  			traditional:true,
  			data:{title: $("#title").val(), content: $("#content").val(), deadline: deadline.getFullYear()+"-"+(deadline.getMonth()+1)+"-"+deadline.getDate(), partner_limit: partner_limit, 
  				uid: $("#uid").val(), contact: $("#contact").val(), boardField: boardField, boardLanguage: boardLanguage},
  			success: function(){
  				location.href="/board";
  			}
  		});
      });
    </script>
</body>
</html>