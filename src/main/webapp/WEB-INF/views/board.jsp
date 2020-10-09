<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://fonts.googleapis.com/css?family=Black+Han+Sans&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
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
		<button type="button" id="right" onclick="location.href='/mypage'"
			class="btn btn-default" aria-label="Left Align">
			<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
		</button>
		<button type="button" id="message" class="btn btn-default"
			aria-label="Left Align">
			<c:if test="${messageTotal!=0}">
				<div class="alarm">
					<c:choose>
						<c:when test="${messageTotal>99 }">
						99
					</c:when>
						<c:otherwise>
						${messageTotal }
					</c:otherwise>
					</c:choose>
				</div>
			</c:if>
			<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
		</button>
	</header>
	<sec:authentication var="uid" property="principal.userVO.uid" />

	<div class="project-list">
		<!--사이드 메뉴 시작-->
		<!--사용자 선택 영역-->
		<div id="select-field">
			<c:if test="${selectFieldList!=null}">
				<c:forEach items="${selectFieldList}" var="selectField">
					<input type="hidden" value="${selectField}">
				</c:forEach>
			</c:if>
		</div>
		<!--사용자 선택 언어-->
		<div id="select-language">
			<c:if test="${selectLanguageList!=null}">
				<c:forEach items="${selectLanguageList}" var="selectLanguage">
					<input type="hidden" value="${selectLanguage}">
				</c:forEach>
			</c:if>
		</div>
		<div class="sidemenu">
			<div class="language">
				<label class="menu-label">분야</label>
				<div id="field-list" class="list-group">
					<c:forEach var="field" items="${fieldList}">
						<a class="list-group-item" id="${field.fid}">${field.fname}</a>
					</c:forEach>
				</div>
			</div>
			<div class="language">
				<label class="menu-label">언어</label>
				<div id="language-list" class="list-group">
					<c:forEach var="language" items="${languageList}">
						<a class="list-group-item" id="${language.lid}">${language.lname}</a>
					</c:forEach>
				</div>
			</div>
			<input type="text" id="search-input" class="form-control"
				placeholder="조회 조건 검색">
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
					<th scope="col">팀원제한</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boardList}">
					<tr id="board">
						<th id="rowNum" scope="row">${board.boardVO.rowNum}</th>
						<td><a onclick="keepPage()"
							href="/board/view?bid=${board.boardVO.bid}&uid=${uid}">${board.boardVO.title}</a></td>
						<td class="requirement"><c:set var="cnt" value="-1" /> <c:forEach
								begin="0" end="5" var="field" items="${board.boardField}"
								varStatus="status">
								<c:set var="cnt" value="${cnt+1}" />
								<c:choose>
									<c:when test="${status.index==4}">
										<label>...</label>
									</c:when>
									<c:otherwise>
										<label>${field.fname}</label>
									</c:otherwise>
								</c:choose>
							</c:forEach> <c:forEach begin="${cnt}" end="5" var="language"
								items="${board.boardLanguage}" varStatus="status">
								<c:set var="cnt" value="${cnt+1}" />
								<c:choose>
									<c:when test="${status.index==4}">
										<label>...</label>
									</c:when>
									<c:otherwise>
										<label>${language.lname}</label>
									</c:otherwise>
								</c:choose>
							</c:forEach></td>
						<td><c:choose>
								<c:when test="${board.boardVO.deadline.year==8099}">
									상시모집
								</c:when>
								<c:otherwise>
									<fmt:formatDate pattern="yyyy-MM-dd"
										value="${board.boardVO.deadline}" />
								</c:otherwise>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${board.boardVO.partner_limit==999}">
									제한없음
								</c:when>
								<c:otherwise>
									${board.boardVO.partner_limit}
								</c:otherwise>
							</c:choose></td>
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
						<c:forEach var="message" items="${messageList }">
							<li class="list-group-item">${message.msg }
								<button type="button" onclick="deleteMessage(${message.mid})"
									class="close">
									<span aria-hidden="true">&times;</span>
								</button>
							</li>
						</c:forEach>
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
		var total=${total}
		var startPage=sessionStorage.getItem("page");
		if(startPage==null)
			startPage=1;
		sessionStorage.removeItem("page");
		pagination(startPage);
		$("#select-field input").each(function(){//선택 영역 표시
			$("#field-list > a[id="+$(this).val()+"]").addClass("active");
		});
		$("#select-language input").each(function(){//선택 언어 표시
			$("#language-list > a[id="+$(this).val()+"]").addClass("active");
		});
		$(".list-group > a").click(function(){
			$(this).toggleClass("active");
		});
		
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
		$("#save").click(function(){//원하는 요건의 프로젝트 조회
			var selectFieldList=new Array();
			var selectLanguageList=new Array();
			$("#field-list a").each(function(){
				if($(this).hasClass("active"))
					selectFieldList.push($(this).attr("id"));
			});
			$("#language-list a").each(function(){
				if($(this).hasClass("active"))
					selectLanguageList.push($(this).attr("id"));
			});
			var form = document.createElement("form");
			form.setAttribute("method","POST");
			form.setAttribute("action","/board");
			var fieldInput= document.createElement("input");
			fieldInput.setAttribute("name","selectFieldList");
			fieldInput.setAttribute("value",selectFieldList);
			fieldInput.setAttribute("type","hidden");
			form.appendChild(fieldInput);
			var languageInput= document.createElement("input");
			languageInput.setAttribute("name","selectLanguageList");
			languageInput.setAttribute("value",selectLanguageList);
			languageInput.setAttribute("type","hidden");
			form.appendChild(languageInput);
			document.body.appendChild(form);
			form.submit();
		});
		$("#message").click(function() {
			$("#message-modal").modal("show");
		});
		$("#search-input").keyup(function() {//언어 검색
			var search = $("#search-input").val();
			$(".list-group > a").css('display', 'none');
			$(".list-group > a:contains(" + search + ")").css('display', 'block');
		});
		function deleteMessage(mid){//메세지 삭제
		  		$.ajax({
					type:"DELETE",
					url:"/message?mid="+mid,
					success: function(){
						$("button[onclick='deleteMessage("+mid+")']").parent().remove();
					}
				});
		};
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