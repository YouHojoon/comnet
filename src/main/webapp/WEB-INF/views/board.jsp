<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<button type="button" id="left" class="btn btn-default"
			onclick="showMenu()" aria-label="Left Align">
			<span class="glyphicon glyphicon-align-justify" aria-hidden="true"></span>
		</button>
		<h1>COMNET</h1>
		<button type="button" id="right" onclick="location.href='mypage.html'"
			class="btn btn-default" aria-label="Left Align">
			<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
		</button>
		<button type="button" id="message" class="btn btn-default"
			aria-label="Left Align">
			<div class="alarm">99</div>
			<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
		</button>
	</header>

	<div class="project-list">
	<!--사이드 메뉴 시작-->
		<div class="sidemenu">
			<div class="language">
				<label class="menu-label">분야</label>
				<div class="list-group">
					<c:forEach var="field" items="${fieldList}" >
						<a class="list-group-item">${field.fname}</a>
					</c:forEach>
				</div>
			</div>
			<div class="language">
				<label class="menu-label">언어</label>
				<div class="list-group">
					<c:forEach var="language" items="${languageList}">
						<a class="list-group-item">${language.lname}</a>
					</c:forEach>
				</div>
			</div>
			<input type="text" id="search-input" class="form-control" placeholder="조회 조건 검색">
			<button type="button" id="save" class="btn btn-default">저장</button>
		</div>
		<!--사이드 메뉴 끝-->
		<!--프로젝트 조회 시작-->
		<input type="hidden" id="total" value="${total}">
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
						<td>${board.boardVO.title}</td>
						<td class="requirement">
							<c:forEach var="field" items="${board.board_field}">
								<label><c:out value="${field.fname}"/></label>
							</c:forEach>
							<c:forEach var="language" items="${board.board_language}">
								<label><c:out value="${language.lname}"/></label>
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
								<c:when test="${board.boardVO.partner_limit==0}">
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
		<button type="button" id="register" class="btn btn-default"
		onclick="location.href='/board/new'" aria-label="Left Align">register</button>
	<!--페이징 처리 시작-->
		<nav>
			<ul class="pagination">
				
				
			</ul>
		</nav>
	<!--페이징 처리 끝-->
	</div>
	
	<!--알림 조회 모달-->
	<div class="modal" id="message-modal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLongTitle" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="exampleModalLongTitle">Message</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<ul class="list-group">
						<li class="list-group-item">Lookie 합격
							<button type="button" class="close">
								<span aria-hidden="true">&times;</span>
							</button>
						</li>
						<li class="list-group-item">뭐뭐 불합격
							<button type="button" class="close">
								<span aria-hidden="true">&times;</span>
							</button>
						</li>
						<li class="list-group-item">이것저것 합격
							<button type="button" class="close">
								<span aria-hidden="true">&times;</span>
							</button>
						</li>
						<li class="list-group-item">저건 불합격
							<button type="button" class="close">
								<span aria-hidden="true">&times;</span>
							</button>
						</li>
						<li class="list-group-item">저건 합격
							<button type="button" class="close">
								<span aria-hidden="true">&times;</span>
							</button>
						</li>
					</ul>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<footer id="test"> Create by YouHoJoon </footer>
	<script type="text/javascript">
		var total=$("#total").val();
		var startPage=sessionStorage.getItem("page");
		if(startPage==null)
			startPage=1;
		pagination();
		
			$(window).resize(function(){//창 크기를 줄이거나 늘릴 때
				if($(".sidemenu").width() > 0){
					if($(window).width() > 960){
						$(".sidemenu").css("width","15%");
					}
					else if($(window).width() > 768){
						$(".sidemenu").css("width","20%");
					}
					else{
						$(".sidemenu").css("width","30%");
					}
				}
			});
		function pageMove(page){//페이지 옮길 때 실행
			startPage=page;
			pagination();
		}
		$("#message").click(function() {
			$("#message-modal").modal("show");
		});
		$("#search-input").keyup(function() {
			var search = $("#search-input").val();
			$(".list-group > a").css('display', 'none');
			$(".list-group > a:contains(" + search + ")").css('display', 'block');
		});
		window.onbeforeunload = function() {
			sessionStorage.setItem("page",$(".active > a").text());
		}
		function pagination(){//페이징 처리
			$("th[id=rowNum]").parent().css("display","none");
			$("th[id=rowNum]").filter(function(){
					return $(this).text() <= 15 * startPage * startPage && $(this).text() >= (startPage-1) * 15 + 1;
					//페이지 범위 사이에 있는 것만 보이게
			}).parent().css("display","table-row");
			
			$(".pagination").html("");
			var endPage = Math.ceil(total / 15.0);
			var realStartPage= Math.ceil(startPage / 10.0);
			var realEndPage=Math.ceil(startPage / 10.0) * 10;
			if(realEndPage > endPage)
				realEndPage=endPage;
			//페이지 버튼생성
			if(realEndPage > 10)
				$(".pagination").append("<li><a aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>")
			for(var i=realStartPage; i<=realEndPage; i++){
				if(i == startPage)
					$(".pagination").append("<li class='active'><a onclick='pageMove("+ i +")'>" + i + "</li></a>")
				else
					$(".pagination").append("<li><a onclick='pageMove("+ i +")'>" + i + "</li></a>")
			}
			if(realEndPage != endPage)
				$(".pagination").append("<li><a aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>")
		}
		function showMenu() {//사이드 메뉴 크기 결정
			if($(window).width() > 960){
				$(".sidemenu").animate({
					width: "15%"
				}, 1000);
			}
			else if($(window).width() > 768){
				$(".sidemenu").animate({
					width: "20%"
				}, 1000);
			}
			else{
				$(".sidemenu").animate({
					width : "30%"
				}, 1000);
			}
			$("#search-input").css("display", "inline-flex");
			$(".menu-label").css("display", "inline-flex");
			$("#save").css("display", "inline-flex");
			$("#left").attr("onclick", "hideMenu()");
		}
		function hideMenu() {
			$(".sidemenu").animate({
				width : "0%"
			}, 1000);
			setTimeout(function() {
				$("#search-input").css("display", "none");
				$(".menu-label").css("display", "none");
				$("#save").css("display", "none");
			}, 999);
			$("#left").attr("onclick", "showMenu()");
		}
	</script>
</body>
</html>