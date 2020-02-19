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
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body id="new">
<header>
      <h1>COMNET</h1>
    </header>
    <form class="register-form" method="post">
      <div class="form-group">
        <input type="email" class="form-control" placeholder="프로젝트 제목">
      </div>
      <label>마감기한</label>
      <div class="row">
        <div class="col">
          <select class="form-control" onchange="makeDay()" id="year">
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
      <label id="field-label">모집 언어</label>
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
          <label>프로젝트 설명</label>
          <textarea class="form-control" rows="3"></textarea>
        </div>
        <div class="partner-limit">
	    	<div class="form-group">
          		<input type="email" class="form-control" placeholder="팀원제한(0입력시 무제한)">
        	</div>
	        <div class="partner-limit-check">
		        <div class="recruit">
		            <input class="form-check-input" type="checkbox" value="1" id="always">
		              <label class="form-check-label">
		                	팀원 무제한
		              </label>
		        </div>
	        </div>
        </div>
        <div class="form-group">
	    	<input type="email" class="form-control" placeholder="연락처">
	    </div>
        <div>
          <button type="button" class="btn btn-primary btn-lg">작성</button>
        </div>
        <button type="button" id="back" onclick="location.href='project.html'" class="btn btn-primary btn-sm">Back</button>
    </form>
    <script type="text/javascript">
      var Calander=new Date();
      $("#year").append("<option selected value="+Calander.getFullYear()+">"+Calander.getFullYear()+"년"+"</option>");
      var nextYear=Calander.getFullYear()+1;
      $("#year").append("<option value="+nextYear+">"+nextYear+"년"+"</option>");
      for(var i=Calander.getMonth()+1; i<=12; i++){
    	  $("#month").append("<option value="+i+">"+i+"월"+"</option>");
      }
     $("#month option[value="+(new Date().getMonth()+1)+"]").attr("selected","selected");
     makeDay();
     $("#day option[value="+new Date().getDate()+"]").attr("selected","selected");
     //마감기한 달력 생성
      function makeDay(){//일 생성
    	  var str="";
          Calander.setYear($("#year").val());
          Calander.setMonth($("#month").val()-1);
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
      $(".new").click(function(){
    	  
      })
    </script>
</body>
</html>