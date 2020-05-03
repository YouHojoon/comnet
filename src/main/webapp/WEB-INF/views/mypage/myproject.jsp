<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Black+Han+Sans&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="\resources\grid.css" type="text/css">
<script src="\resources\jquery.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Comnet</title>
</head>
<body>
	<header>
		<button type="button" id="left" class="btn btn-default" onclick="location.href='/mypage'" aria-label="Left Align">
        	<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
      	</button>
		<h1>COMNET</h1>
	</header>
	
	<div class="project-list">
		<!--프로젝트 조회 시작-->
		<table id="project-list" class="table table-hover">
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">모집분야</th>
					<th scope="col">기한</th>
					<th scope="col">팀원</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boardList}">
					<tr id="board">
						<th id="rowNum" scope="row">${board.boardVO.rowNum}</th>
						<td><a onclick="keepPage()" href="/board/view?bid=${board.boardVO.bid}">${board.boardVO.title}</a></td>
						<td class="requirement">
							<c:set var="cnt" value="-1"/>
							<c:forEach begin="0" end="5" var="field" items="${board.boardField}" varStatus="status">
								<c:set var="cnt" value="${cnt+1}" />
								<c:choose>									
									<c:when test="${status.index==4}">
										<label>...</label>
									</c:when>
									<c:otherwise>
										<label>${field.fname}</label>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:forEach begin="${cnt}" end="5" var="language" items="${board.boardLanguage}" varStatus="status">
								<c:set var="cnt" value="${cnt+1}" />
								<c:choose>
									<c:when test="${status.index==4}">
										<label>...</label>
									</c:when>
									<c:otherwise>
										<label>${language.lname}</label>								
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</td>
						<td>
							<c:choose>
								<c:when test="${board.boardVO.deadline.year==8099}">
									상시모집
								</c:when>
								<c:otherwise>
									<fmt:formatDate pattern="yyyy-MM-dd" value="${board.boardVO.deadline}"/>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${board.boardVO.partner_limit==999}">
									제한없음
								</c:when>
								<c:otherwise>
									${board.boardVO.partner_limit}
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!--프로젝트 조회 끝-->
		
	<!--페이징 처리 시작-->
		<nav>
			<ul class="pagination">
				
				
			</ul>
		</nav>
	<!--페이징 처리 끝-->
	</div>
	
	<footer id="test"> Create by YouHoJoon </footer>
	<script type="text/javascript">
		var total=${total};
		var startPage=sessionStorage.getItem("page");
		if(startPage==null)
			startPage=1;
		sessionStorage.removeItem("page");
		pagination(startPage);
		function keepPage(){//프로젝트 상세 조회 시 페이지 유지를 위해 세션 처리
			sessionStorage.setItem("page",$("li[class=active] > a").text());
		}
		
		function pagination(page){//페이징 처리
			$("th[id=rowNum]").parent().css("display","none");
			$("th[id=rowNum]").filter(function(){
					return $(this).text() <= 15 * page && $(this).text() >= (page-1) * 15 + 1;
					//페이지 범위 사이에 있는 것만 보이게
			}).parent().css("display","table-row");
			$(".pagination").html("");
			var endPage = Math.ceil(total / 15.0);
			var realEndPage=Math.ceil(page / 10.0) * 10;
			var realStartPage= realEndPage-9;
			if(realEndPage > endPage)
				realEndPage=endPage;
			//페이지 버튼생성
			if(realEndPage > 10)
				$(".pagination").append("<li><a aria-label='Previous' onclick='pagination("+ (realStartPage - 1) +")'><span aria-hidden='true'>&laquo;</span></a></li>")
			for(var i=realStartPage; i<=realEndPage; i++){
				if(i == page)
					$(".pagination").append("<li class='active'><a onclick='pagination("+ i +")'>" + i + "</li></a>")
				else
					$(".pagination").append("<li><a onclick='pagination("+ i +")'>" + i + "</li></a>")
			}
			if(realEndPage != endPage)
				$(".pagination").append("<li><a aria-label='Next' onclick='pagination("+ (realEndPage + 1) +")'><span aria-hidden='true'>&raquo;</span></a></li>")
		}
		
	</script>
</body>
</html>