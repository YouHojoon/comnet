<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Black+Han+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="\resources\grid.css" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <script src="\resources\jquery.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>COMNET</title>
  </head>
  <body id="view">
    <header>
      <button type="button" id="left" class="btn btn-default" onclick="location.href='/board'" aria-label="Left Align">
        <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
      </button>
      <h1>COMNET</h1>
    </header>
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
					<fmt:formatDate pattern="yyyy-MM-dd" value="${board.boardVO.deadline}"/>
				</c:otherwise>
			</c:choose>
		</h4>
      </div>
      <div class="project-body">
        <div class="field-language">
          <c:forEach var="field" items="${board.boardField}">
			<label><c:out value="${field.fname}"/></label>
		  </c:forEach>
		  <c:forEach var="language" items="${board.boardLanguage}">
			<label><c:out value="${language.lname}"/></label>
		  </c:forEach>
        </div>
        <div class="form-group">
          <textarea class="form-control" readonly rows="5" >${board.boardVO.content}</textarea>
        </div>
        <table class="table">
          <thead>
            <tr>
              <th scope="col">학번</th>
              <th scope="col">이름</th>
              <th scope="col">영역/언어</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">2016****</th>
              <td>유호준</td>
              <td class="ability">
                <label>서버</label>
	            <label>자바</label>
	            <label>서버</label>
	            <label>자바</label>
                <label>서버</label>
                <label>자바</label>
              </td>
            </tr>
            <tr>
              <th scope="row">2017****</th>
              <td>박도원</td>
              <td>
                <label>서버</label>
                <label>자바</label>
              </td>
            </tr>
            <tr>
              <th scope="row">2016****</th>
              <td>양유림</td>
              <td>
                <label>서버</label>
                <label>자바</label>
              </td>
            </tr>
            <tr>
              <th scope="row">2017****</th>
              <td>박희지</td>
              <td>
                <label>안드로이드</label>
                <label>자바</label>
              </td>
            </tr>
            <tr>
              <th scope="row">2016****</th>
              <td>천윤한</td>
              <td>
                <label>안드로이드</label>
                <label>자바</label>
              </td>
            </tr>
            <tr>
              <th scope="row">2018****</th>
              <td>이정인</td>
              <td>
                <label>안드로이드</label>
                <label>자바</label>
              </td>
            </tr>
          </tbody>
          <tfoot>
            <tr>
              <th colspan="3"scope="col">6/6</th>
            </tr>
          </tfoot>
        </table>
        <sec:authentication var="uid" property='principal.userVO.uid'/>
        <c:if test="${uid==owner.uid}">
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
	              <tr>
	                <th scope="row">2016****</th>
	                <td>유호준</td>
	                <td class="ability">
	                  <label>서버</label>
	                  <label>자바</label>
	                  <label>서버</label>
	                  <label>자바</label>
	                  <label>서버</label>
	                  <label>자바</label>
	                </td>
	                <td>
	                  <button type="button" class="approval">승인</button>
	                  <button type="button" class="reject">거부</button>
	                </td>
	              </tr>
	              <tr>
	                <th scope="row">2017****</th>
	                <td>박도원</td>
	                <td>
	                  <label>서버</label>
	                  <label>자바</label>
	                </td>
	                <td>
	                  <button type="button" class="approval">승인</button>
	                  <button type="button" class="reject">거부</button>
	                </td>
	              </tr>
	              <tr>
	                <th scope="row">2016****</th>
	                <td>양유림</td>
	                <td>
	                  <label>서버</label>
	                  <label>자바</label>
	                </td>
	                <td>
	                  <button type="button" class="approval">승인</button>
	                  <button type="button" class="reject">거부</button>
	                </td>
	              </tr>
	              <tr>
	                <th scope="row">2017****</th>
	                <td>박희지</td>
	                <td>
	                  <label>안드로이드</label>
	                  <label>자바</label>
	                </td>
	                <td>
	                  <button type="button" class="approval">승인</button>
	                  <button type="button" class="reject">거부</button>
	                </td>
	              </tr>
	              <tr>
	                <th scope="row">2016****</th>
	                <td>천윤한</td>
	                <td>
	                  <label>안드로이드</label>
	                  <label>자바</label>
	                </td>
	                <td>
	                  <button type="button" class="approval">승인</button>
	                  <button type="button" class="reject">거부</button>
	                </td>
	              </tr>
	              <tr>
	                <th scope="row">2018****</th>
	                <td>이정인</td>
	                <td>
	                  <label>안드로이드</label>
	                  <label>자바</label>
	                </td>
	                <td>
	                  <button type="button" class="approval">승인</button>
	                  <button type="button" class="reject">거부</button>
	                </td>
	              </tr>
	            </tbody>
	          </table>
        	</div>
        </c:if>
        
      </div>
      
      <div class="project-foot">
	      <c:choose>
	      	<c:when test="${uid==owner.uid}">
	      		<button type="button" class="volunteer">수정</button>
          		<button type="button" class="volunteer">삭제</button>
	      	</c:when>
	      	<c:otherwise>
	      		<button type="button" class="volunteer">지원</button>
	      	</c:otherwise>
	      </c:choose>
      </div>
      
    </div>
  </body>
</html>
