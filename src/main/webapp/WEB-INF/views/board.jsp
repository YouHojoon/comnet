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

	<div>
	<!--사이드 메뉴 시작-->
		<div class="sidemenu">
			<div class="language">
				<label class="menuLabel">분야</label>
				<div class="list-group">
					<c:forEach var="field" items="${fieldList}">
						<a class="list-group-item">${field.fname}</a>
					</c:forEach>
				</div>
			</div>
			<div class="language">
				<label class="menuLabel">언어</label>
				<div class="list-group">
					<c:forEach var="language" items="${languageList}">
						<a class="list-group-item">${language.lname}</a>
					</c:forEach>
				</div>
			</div>
			<div class="search">
				<div class="input-group">
					<input type="text" id="searchInput" class="form-control"
						placeholder="조회 조건 검색"> <span class="input-group-btn">
						<button id="search" class="btn btn-default" type="button" disabled>
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
						</button>
					</span>
				</div>
			</div>
			<button type="button" id="save" class="btn btn-default">저장</button>
		</div>
		<!--사이드 메뉴 끝-->
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
					<tr>
						<th scope="row">${board.boardVO.rowNum}</th>
						<td>${board.boardVO.title}</td>
						<td>
							<c:forEach var="field" items="${board.board_field}">
								<label><c:out value="${field.fname}"/></label>
							</c:forEach>
							<c:forEach var="language" items="${board.board_language}">
								<label><c:out value="${language.lname}"/></label>
							</c:forEach>
						</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.boardVO.deadline}"/></td>
						<td>${board.boardVO.partner_limit}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!--프로젝트 조회 끝-->
	</div>
	<button type="button" id="register" class="btn btn-default"
		aria-label="Left Align">register</button>
	<!--페이징 처리 시작-->
	<nav>
		<ul class="pagination">
			<li><a href="#" aria-label="Previous"> <span
					aria-hidden="true">&laquo;</span>
			</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>
	</nav>
	<!--페이징 처리 끝-->
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
		
			$(window).resize(function(){
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
		
		$("#message").click(function() {
			$("#message-modal").modal("show");
		});
		$("#searchInput").keyup(function() {
			var search = $("#searchInput").val();
			$("a").css('display', 'none');
			$("a:contains(" + search + ")").css('display', 'block');
		});
		function showMenu() {
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
			$(".search").css("display", "flex");
			$(".menuLabel").css("display", "inline-flex");
			$("#save").css("display", "inline-flex");
			$("#left").attr("onclick", "hideMenu()");
		}
		function hideMenu() {
			$(".sidemenu").animate({
				width : "0%"
			}, 1000);
			setTimeout(function() {
				$(".search").css("display", "none");
				$(".menuLabel").css("display", "none");
				$("#save").css("display", "none");
			}, 999);
			$("#left").attr("onclick", "showMenu()");
		}
	</script>
</body>
</html>