<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://fonts.googleapis.com/css?family=Black+Han+Sans&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<script src="\resources\jquery.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="\resources\grid.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<title>COMNET</title>
</head>
<body class="new">
	<header>
		<h1>COMNET</h1>
	</header>
	<input type="hidden" id="uid"
		value="<sec:authentication property='principal.userVO.uid'/>">
	<!--수정하려는 글의 현재 영역과 언어 선택을 위해 설정 -->
	<div id="fid">
		<c:forEach var="field" items="${board.boardField }">
			<input type="hidden" value="${field.fid}">
		</c:forEach>
	</div>
	<div id=lid>
		<c:forEach var="language" items="${board.boardLanguage}">
			<input type="hidden" value="${language.lid}">
		</c:forEach>
	</div>
	<input id="bid" type="hidden" value="${board.boardVO.bid}">
	<!--수정하려는 글의 현재 마감기한 선택을 위해 설정 -->
	<input id="selected-year" type="hidden"
		value="${board.boardVO.deadline.year}">
	<input id="selected-month" type="hidden"
		value="${board.boardVO.deadline.month}">
	<input id="selected-date" type="hidden"
		value="${board.boardVO.deadline.date}">
	<form class="register-form">
		<div class="form-group">
			<input type="text" id="title" class="form-control"
				placeholder="프로젝트 제목" value="${board.boardVO.title}">
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
					<input class="form-check-input" type="checkbox" value="1"
						id="always"> <label class="form-check-label"> 상시모집
					</label>
				</div>
			</div>
		</div>
		<label id="field-label">모집 분야</label>
		<table id="board-field" class="table table-borderless">
			<tr>
				<c:set var="cnt" value="0" />
				<c:forEach var="field" items="${fieldList}">
					<c:set var="cnt" value="${cnt+1}" />
					<c:if test="${cnt > 6}">
						<!--한 줄당 6개씩 출력-->
			</tr>
			<tr>
				<c:set var="cnt" value="1" />
				</c:if>
				<th scope="col">
					<div class="form-check">
						<input class="form-check-input" name="field-check" type="checkbox"
							value="${field.fid}"> <label class="form-check-label">
							${field.fname} </label>
					</div>
				</th>
				</c:forEach>
			</tr>
		</table>
		<label id="field-label">모집 언어</label>
		<table id="board-language" class="table table-borderless">
			<tr>
				<c:set var="cnt" value="0" />
				<c:forEach var="language" items="${languageList}">
					<c:set var="cnt" value="${cnt+1}" />
					<c:if test="${cnt > 6}">
						<!--한 줄당 6개씩 출력-->
			</tr>
			<tr>
				<c:set var="cnt" value="1" />
				</c:if>
				<th scope="col">
					<div class="form-check">
						<input class="form-check-input" name="language-check"
							type="checkbox" value="${language.lid}"> <label
							class="form-check-label"> ${language.lname} </label>
					</div>
				</th>
				</c:forEach>
			</tr>
		</table>
		<div class="form-group">
			<label>프로젝트 설명</label>
			<textarea id="content" class="form-control" rows="3">${board.boardVO.content}</textarea>
		</div>
		<div class="partner-limit">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="팀원제한(최대 999명)"
					id="partner-limit-input" value="${board.boardVO.partner_limit}">
			</div>
			<div class="partner-limit-check">
				<div class="recruit">
					<input class="form-check-input" type="checkbox" value="1"
						id="unlimit"> <label class="form-check-label">
						제한없음 </label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<input type="text" id="contact" class="form-control"
				placeholder="연락처" value="${board.boardVO.contact}">
		</div>
		<table id="partner-list" class="table">
			<!--팀원 목록-->
			<thead>
				<tr>
					<th scope="col">학번</th>
					<th scope="col">이름</th>
					<th scope="col">영역/언어</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="partner" items="${board.partnerList}">
					<tr>
						<th scope="row">${partner.userVO.email}</th>
						<td><a onclick="volunteerDetail(${partner.userVO.uid})">
								${partner.userVO.name}</a></td>
						<td class="ability"><c:set var="cnt" value="-1" /> <c:forEach
								begin="0" end="5" var="field" items="${partner.userField}"
								varStatus="status">
								<c:set var="cnt" value="${cnt+1}" />
								<c:choose>
									<c:when test="${status.index==4}">
				                  			...				    				             			             
				                  		</c:when>
									<c:otherwise>
										<label>${field.fname}</label>
									</c:otherwise>
								</c:choose>
							</c:forEach> <c:forEach begin="${cnt}" end="5" var="language"
								items="${partner.userLanguage}" varStatus="status">
								<c:choose>
									<c:when test="${status.index==4}">
				                  			...
				                  		</c:when>
									<c:otherwise>
										<label>${language.lname}</label>
									</c:otherwise>
								</c:choose>
							</c:forEach></td>
						<td>
							<button type="button" class="reject"
								onclick="eliminate(${partner.userVO.uid})">추방</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th colspan="4" scope="col">${partnerTotal}/<c:choose>
							<c:when test="${board.boardVO.partner_limit==999}">
							제한없음
						</c:when>
							<c:otherwise>
							${board.boardVO.partner_limit}
						</c:otherwise>
						</c:choose>
					</th>
				</tr>
			</tfoot>
		</table>
		<!--팀원 페이징 처리 시작-->
		<nav id="partner-pagination">
			<ul class="pagination">


			</ul>
		</nav>

		<div>
			<button type="button" id="update" class="btn btn-primary btn-lg">수정</button>
		</div>
		<button type="button" id="back"
			onclick="location.href='/board/view?bid=${board.boardVO.bid}'"
			class="btn btn-primary btn-sm">Back</button>
	</form>

	<!--팀원 조회 모달-->
	<div class="modal" id="volunteer-modal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title"></h1>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="field-language"></div>
					<div class="form-group">
						<input type="text" name="name" class="form-control"
							readonly="readonly">
					</div>
					<div class="form-group">
						<input type="text" name="phone" class="form-control"
							readonly="readonly">
					</div>
					<div class="form-group">
						<label id="memo-label">자기소개</label>
						<textarea name="memo" class="form-control" rows="3"
							readonly="readonly"></textarea>
					</div>
				</div>
				<div class="modal-footer">
					<button id="modal-close" type="button" class="btn btn-default"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
      var Calander=new Date();
      var current= new Date();
      partnerPagination(1);
      
      $("#year").append("<option selected value="+Calander.getFullYear()+">"+Calander.getFullYear()+"년"+"</option>");
      var nextYear=Calander.getFullYear()+1;
      $("#year").append("<option value="+nextYear+">"+nextYear+"년"+"</option>");
      makeMonth();
      makeDay();
      if($("#selected-year").val()==8099){
    	  $("#year").attr("disabled","true");
          $("#month").attr("disabled","true");
          $("#day").attr("disabled","true");
          $("#always").attr("checked","checked");
      }
      else
      	$("#year option[value="+($("#selected-year").val()+1900)+"]").attr("selected","selected");
      $("#month option[value="+($("#selected-month").val()+1)+"]").attr("selected","selected");
      if($("#month option[selected='selected']").val()==($("#selected-month").val()+1) && 
    		  $("#year option[selected='selected']").val()==($("#selected-year").val()+1900))
      	$("#day option[value="+$("#selected-date").val()+"]").attr("selected","selected"); 
      //마감기한 달력 생성
      if($("#partner-limit-input").val()==999){
    	  $("#partner-limit-input").attr("readonly","readonly");
    	  $("#unlimit").attr("checked","checked");
      }
 
      $("#fid input").each(function(){
    	  $("#board-field input[value="+$(this).val()+"]").attr("checked","checked");
      });
      $("#lid input").each(function(){
    	  $("#board-language input[value="+$(this).val()+"]").attr("checked","checked");
      });
      
      function partnerPagination(page){//페이징 처리
			$("#partner-list tbody > tr").css("display","none");
  		var i=0;
			$("#partner-list tbody > tr").filter(function(){
					if(i>=(page-1)*15 && i<page*15){
						i++;
						return $(this);
					}
					else
						i++;
			}).css("display","table-row");
			$("#partner-pagination .pagination").html("");
			partnerTotal=${partnerTotal};
			var endPage = Math.ceil(partnerTotal / 15.0);
			var realEndPage=Math.ceil(page / 10.0) * 10;
			var realStartPage= realEndPage-9;
			if(realEndPage > endPage)
				realEndPage=endPage;
			//페이지 버튼생성
			if(realEndPage > 10)
				$("#partner-pagination .pagination").append("<li class='page-item'><a class='page-link' aria-label='Previous' onclick='partnerPagination("+ (realStartPage - 1) +")'><span aria-hidden='true'>&laquo;</span></a></li>")
			for(var i=realStartPage; i<=realEndPage; i++){
				if(i == page)
					$("#partner-pagination .pagination").append("<li class='page-item active'><a class='page-link' onclick='partnerPagination("+ i +")'>" + i + "</li></a>")
				else
					$("#partner-pagination .pagination").append("<li class='page-item'><a class='page-link' onclick='partnerPagination("+ i +")'>" + i + "</li></a>")
			}
			if(realEndPage != endPage)
				$("#partner-pagination .pagination").append("<li class='page-item'><a class='page-link' aria-label='Next' onclick='partnerPagination("+ (realEndPage + 1) +")'><span aria-hidden='true'>&raquo;</span></a></li>")
		}
      
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
          } while(Calander.getDate()!=1);
          
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
      $("#update").click(function(){
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
  				boardField.push(Number($(this).val()));
  			}
  		});
  		$("#board-language input").each(function(){
  			if($(this).is(":checked")==true){
  				boardLanguage.push(Number($(this).val()));
  			}
  		});
  		var partner_limit;
  		if($("#unlimit").prop("checked")){
  			partner_limit=999;
  		}
  		else{
  			partner_limit=Number($("#partner-limit-input").val());
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
		var data={title: $("#title").val(), content: $("#content").val(), deadline: deadline.getFullYear()+"-"+(deadline.getMonth()+1)+"-"+deadline.getDate(), 
  				partner_limit: partner_limit, uid: Number($("#uid").val()), contact: $("#contact").val(), boardField: boardField, boardLanguage: boardLanguage};
  		$.ajax({
  			type: "PUT",
  			url: "/board/update?bid="+$("#bid").val(),
  			data:JSON.stringify(data),
  			contentType:"application/json",
  			success: function(){
  				location.href="/board/view?bid="+$("#bid").val();
  			}
  		});
      });
      function volunteerDetail(vid){//지원자 정보 상세 조회
  		$.ajax({
  			type:"GET",
  			url:"/volunteerDetail?vid="+vid,
  			success: function(data){
  				$(".modal-title").html(data.userVO.name);
  				$("input[name='name']").val(data.userVO.email);
  				$("input[name='phone']").val(data.userVO.phone);
  				$("textarea[name='memo']").html(data.userVO.memo);
  				var str="";
  				for(var i in data.userField)
  					str+="<label>"+data.userField[i].fname+"</label>";
  				for(var i in data.userLanguage)
						str+="<label>"+data.userLanguage[i].lname+"</label>";
					$(".modal-body > .field-language").html(str);
  				$("#volunteer-modal").modal("show");
  			}
  		});
  	}
  	function eliminate(pid){//프로젝트 지원 거부
		var check=confirm("정말 추방하시겠습니까?");
  		if(check){
	  		$.ajax({
				type:"DELETE",
				url:"/board/eliminate?bid="+$("#bid").val()+"&pid="+pid,
				success: function(){
					$("button[onclick='eliminate("+pid+")']").parent().parent().remove();
				}
			});
  		}
  		else
  			return;
	}
    </script>
</body>
</html>