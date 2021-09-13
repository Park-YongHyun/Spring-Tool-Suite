<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/header.jsp"%>

<c:set var="errorType" value="${requestScope['javax.servlet.error.status_code']}"></c:set>
<c:choose>
	<c:when test="${errorType == 400}">
		<c:set var="errorMessage" value="이 응답은 잘못된 문법으로 인하여 서버가 요청을 이해할 수 없음을 의미합니다."></c:set>
	</c:when>

	<c:when test="${errorType == 403}">
		<c:set var="errorMessage" value="클라이언트는 콘텐츠에 접근할 권리를 가지고 있지 않습니다."></c:set>
	</c:when>

	<c:when test="${errorType == 404}">
		<c:set var="errorMessage" value="서버는 요청받은 리소스를 찾을 수 없습니다."></c:set>
	</c:when>

	<c:when test="${errorType == 405}">
		<c:set var="errorMessage" value="요청한 메소드는 서버에서 알고 있지만, 제거되었고 사용할 수 없습니다."></c:set>
	</c:when>

	<c:when test="${errorType == 500}">
		<c:set var="errorMessage" value="서버가 처리 방법을 모르는 상황이 발생했습니다."></c:set>
	</c:when>

	<c:when test="${errorType == 503}">
		<c:set var="errorMessage" value="서버가 요청을 처리할 준비가 되지 않았습니다."></c:set>
	</c:when>
</c:choose>

<div>
	<div>${errorMessage}</div>
	<button class="btn btn-outline-primary" onclick="history.back()">뒤로가기</button>
</div>

<%@ include file="../include/footer.jsp"%>