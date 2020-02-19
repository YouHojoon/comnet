<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<body>
<header>
      <h1>COMNET</h1>
    </header>
    <form class="project-register" method="post">
      <div class="form-group">
        <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="프로젝트 제목">
      </div>
      <label>마감기한</label>
      <div class="row">
        <div class="col">
          <select class="form-control" id="year">
          </select>
        </div>
        <div class="col">
          <select class="form-control" id="month">
            <option value="1">1월</option>
            <option value="2">2월</option>
            <option value="3">3월</option>
            <option value="4">4월</option>
            <option value="5">5월</option>
            <option value="6">6월</option>
            <option value="7">7월</option>
            <option value="8">8월</option>
            <option value="9">9월</option>
            <option value="10">10월</option>
            <option value="11">11월</option>
            <option value="12">12월</option>
          </select>
        </div>
        <div class="col">
          <select class="form-control" id="day">
          </select>
        </div>
        <div class="col">
          <div class="recruit">
            <input class="form-check-input" type="checkbox" value="1" id="always">
              <label class="form-check-label" for="defaultCheck1">
                상시모집
              </label>
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
          <label>프로젝트 설명</label>
          <textarea class="form-control" rows="3"></textarea>
        </div>
        <div class="form-group">
          <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="팀원제한(0입력시 무제한)">
        </div>
        <div class="form-group">
          <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="연락처">
        </div>
        <div>
          <button type="button" class="btn btn-primary btn-lg">작성</button>
        </div>
        <button type="button" id="back" onclick="location.href='project.html'" class="btn btn-primary btn-sm">Back</button>
    </form>
    <script type="text/javascript">
      var Calander=new Date();
      $("#year").append("<option value="+Calander.getFullYear()+" selected>"+Calander.getFullYear()+"년"+"</option>");
      var nextYear=Calander.getFullYear()+1;
      $("#year").append("<option value="+nextYear+">"+nextYear+"년"+"</option>");
      $("#month").click(function(){
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
      });
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
    </script>
</body>
</html>