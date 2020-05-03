<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://fonts.googleapis.com/css?family=Black+Han+Sans&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="\resources\grid.css" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<script src="\resources\jquery.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>COMNET</title>
</head>
<body id="view">
	<header>
		<button type="button" id="left" class="btn btn-default"
			onclick="location.href='${redirectUrl}'" aria-label="Left Align">
			<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
		</button>
		<h1>COMNET</h1>
	</header>
	<sec:authentication var="uid" property='principal.userVO.uid' />
	<input id="uid" type="hidden" value="${uid}">
	<input id="bid" type="hidden" value="${board.boardVO.bid}">
	<div id="project">
		<h1>${board.boardVO.title}</h1>
		<div class="project-head">
			<h4 class="leader">${owner.name}-${board.boardVO.contact}</h4>
			<h4 class="deadline">
				<c:choose>
					<c:when test="${board.boardVO.deadline.year==8099}">
					상시모집
				</c:when>
					<c:otherwise>
						<fmt:formatDate pattern="yyyy-MM-dd"
							value="${board.boardVO.deadline}" />
					</c:otherwise>
				</c:choose>
			</h4>
		</div>
		<div class="project-body">
			<div class="field-language">
				<c:forEach var="field" items="${board.boardField}">
					<label>${field.fname}</label>
				</c:forEach>
				<c:forEach var="language" items="${board.boardLanguage}">
					<label>${language.lname}</label>
				</c:forEach>
			</div>
			<div class="form-group">
				<textarea class="form-control" readonly rows="5">${board.boardVO.content}</textarea>
			</div>
			<table id="partner-list" class="table">
				<!--팀원 목록-->
				<thead>
					<tr>
						<th scope="col">학번</th>
						<th scope="col">이름</th>
						<th scope="col">영역/언어</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="partner" items="${board.partnerList}">
						<tr>
							<th scope="row">${partner.userVO.email}</th>
							<td><c:choose>
								<c:when test="${uid==owner.uid}">
									<a onclick="volunteerDetail(${partner.userVO.uid})">
								</c:when>
								<c:otherwise>
									<a>
								</c:otherwise>
							</c:choose>
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
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="3" scope="col">${partnerTotal}/${board.boardVO.partner_limit}</th>
					</tr>
				</tfoot>
			</table>
				<!--팀원 페이징 처리 시작-->
					<nav id="partner-pagination">
						<ul class="pagination">


						</ul>
					</nav>
			<c:if test="${uid==owner.uid}">
				<!--나의 글이면 지원자 목록 조회-->
				<div class="volunteer-list">
					<h1>지원자</h1>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">학번</th>
								<th scope="col">이름</th>
								<th scope="col">영역/언어</th>
								<th scope="col"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="volunteer" items="${board.volunteerList}">
								<c:if test="${uid==volunteer.userVO.uid}">
									<c:set var="applied" value="1" />
								</c:if>
								<tr>
									<th scope="row">${volunteer.userVO.email}</th>
									<td><a onclick="volunteerDetail(${volunteer.userVO.uid})">${volunteer.userVO.name}</a></td>
									<td class="ability"><c:set var="cnt" value="-1" /> <c:forEach
											begin="0" end="5" var="field" items="${volunteer.userField}"
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
											items="${volunteer.userLanguage}" varStatus="status">
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
										<button type="button" onclick="approval(${volunteer.userVO.uid})" class="approval">승인</button>
										<button type="button" class="reject">거부</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!--지원자 페이징 처리 시작-->
					<nav id="volunteer-pagination">
						<ul class="pagination">


						</ul>
					</nav>
					<!--페이징 처리 끝-->
				</div>
			</c:if>
		</div>
		<div class="project-foot">
			<c:choose>
				<c:when test="${uid==owner.uid}">
					<!--나의 글이면 수정,삭제 버튼 조회-->
					<div class="btn-group" role="group">
						<button type="button"
							onclick="location.href='/board/update?bid=${board.boardVO.bid}'"
							class="btn btn-default apply">수정</button>
						<button type="button" id="delete" class="btn btn-default apply">삭제</button>
					</div>
				</c:when>
				<c:when test="${board.applied}">
					<button id="apply-cancel" type="button" class="apply">지원취소</button>
				</c:when>
				<c:when test="${!board.partner}">
					<button id="apply" type="button" class="apply">지원</button>
				</c:when>
			</c:choose>
		</div>
	</div>
	<!--지원자 조회 모달-->
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
    	volunteerPagination(1);
    	partnerPagination(1);
    	$("#delete").click(function(){//글 삭제
    		var check=confirm("정말로 삭제하시겠습니까?");
    		if(check){
    			$.ajax({
        			type:"DELETE",
        			url:"/board/delete?bid="+$("#bid").val(),
        			success: function(data){
        					location.href=data;
        			}
        		});
    		}
    		else
    			return;
    	});
    	$("#apply").click(function(){//프로젝트 지원
    		$.ajax({
    			type:"POST",
    			url:"/board/apply",
    			data:{bid: $("#bid").val(), vid: $("#uid").val()},
    			success: function(){
    				location.href="/board/view?bid="+$("#bid").val();
    			}
    		});
    	});
    	function approval(uid){//프로젝트 지원
    		$.ajax({
    			type:"POST",
    			url:"/board/approval",
    			data:{bid: $("#bid").val(), vid: uid},
    			success: function(){
    				location.href="/board/view?bid="+$("#bid").val();
    			}
    		});
    	};
    	$("#apply-cancel").click(function(){//프로젝트 지원
    		$.ajax({
    			type:"DELETE",
    			url:"/board/applyCancel?bid="+$("#bid").val(),
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
				$("#partner-pagination .pagination").append("<li><a aria-label='Previous' onclick='partnerPagination("+ (realStartPage - 1) +")'><span aria-hidden='true'>&laquo;</span></a></li>")
			for(var i=realStartPage; i<=realEndPage; i++){
				if(i == page)
					$("#partner-pagination .pagination").append("<li class='active'><a onclick='partnerPagination("+ i +")'>" + i + "</li></a>")
				else
					$("#partner-pagination .pagination").append("<li><a onclick='partnerPagination("+ i +")'>" + i + "</li></a>")
			}
			if(realEndPage != endPage)
				$("#partner-pagination .pagination").append("<li><a aria-label='Next' onclick='partnerPagination("+ (realEndPage + 1) +")'><span aria-hidden='true'>&raquo;</span></a></li>")
		}
    	function volunteerPagination(page){//페이징 처리
			$(".volunteer-list tbody > tr").css("display","none");
    		var i=0;
			$(".volunteer-list tbody > tr").filter(function(){
					if(i>=(page-1)*15 && i<page*15){
						i++;
						return $(this);
					}
					else
						i++;
			}).css("display","table-row");
			$("#volunteer-pagination .pagination").html("");
			volunteerTotal=${volunteerTotal};
			var endPage = Math.ceil(volunteerTotal / 15.0);
			var realEndPage=Math.ceil(page / 10.0) * 10;
			var realStartPage= realEndPage-9;
			if(realEndPage > endPage)
				realEndPage=endPage;
			//페이지 버튼생성
			if(realEndPage > 10)
				$("#volunteer-pagination .pagination").append("<li><a aria-label='Previous' onclick='volunteerPagination("+ (realStartPage - 1) +")'><span aria-hidden='true'>&laquo;</span></a></li>")
			for(var i=realStartPage; i<=realEndPage; i++){
				if(i == page)
					$("#volunteer-pagination .pagination").append("<li class='active'><a onclick='volunteerPagination("+ i +")'>" + i + "</li></a>")
				else
					$("#volunteer-pagination .pagination").append("<li><a onclick='volunteerPagination("+ i +")'>" + i + "</li></a>")
			}
			if(realEndPage != endPage)
				$(".pagination").append("<li><a aria-label='Next' onclick='volunteerPagination("+ (realEndPage + 1) +")'><span aria-hidden='true'>&raquo;</span></a></li>")
		}
    </script>
</body>
</html>
