<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../include/header.jsp"%>

<style>
table#postList {
	table-layout: fixed;
}

table#postList th, table#postList td {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>

<c:set var="searchParams" value="${
	page.searchKeyword != null ? String.format(\"&searchType=%s&searchKeyword=%s\", param.searchType, param.searchKeyword) : ''}"></c:set>

<div class="d-flex align-items-center min-vh-100">
	<!-- container -->
	<div class="container-xxl col-lg-10 col-xl-8 col-xxl-6">
		<!-- card -->
		<div class="card">
			<!-- list-group -->
			<ul class="list-group list-group-flush">
				<!-- 게시글 목록 -->
				<li class="list-group-item">
					<table id="postList" class="table table-hover">
						<thead>
							<tr>
								<th class="col-1" scope="col">#</th>
								<th class="col-6" scope="col">제목</th>
								<th class="col-2" scope="col">작성자</th>
								<th class="col-3" scope="col">작성일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${postList}" var="post">
								<tr>
									<th scope="row">${post.num}</th>
									<td><a href="bbs/read?num=${post.num}&pageNum=${page.pageNum}&pageSize=${page.pageSize}${searchParams}">${post.title}</a></td>
									<td>${post.writer}</td>
									<td><fmt:formatDate value="${post.writeDate}" pattern="yyyy.MM.dd HH:mm:ss" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</li>
				<!-- /게시글 목록 -->

				<li class="list-group-item">
					<div class="row justify-content-end">
						<div class="col-auto">
							<a class="btn btn-primary" href="bbs/write">작성</a>
						</div>
					</div>
				</li>

				<!-- 페이지 버튼 -->
				<li class="list-group-item">
					<ul class="pagination justify-content-center">
						<c:set var="pageSizeParam" value="&pageSize=${page.pageSize}"></c:set>

						<c:if test="${page.prev}">
							<li class="page-item"><a class="page-link" href="?pageNum=1&${pageSizeParam}${searchParams}"><c:out value="${'<<'}" /></a></li>
							<li class="page-item"><a class="page-link" href="?pageNum=${page.startPage - 1}${pageSizeParam}${searchParams}"><c:out value="${'<'}" /></a></li>
						</c:if>

						<c:forEach var="var" begin="${page.startPage}" end="${page.endPage}">
							<c:set var="active" value=""></c:set>
							<c:if test="${var == page.pageNum}">
								<c:set var="active" value="active"></c:set>
							</c:if>
							<li class="page-item ${active}"><a class="page-link" href="?pageNum=${var}${pageSizeParam}${searchParams}">${var}</a></li>
						</c:forEach>

						<c:if test="${page.next}">
							<li class="page-item"><a class="page-link" href="?pageNum=${page.endPage + 1}${pageSizeParam}${searchParams}"><c:out value="${'>'}" /></a></li>
							<li class="page-item"><a class="page-link" href="?pageNum=${page.totalPage}${pageSizeParam}${searchParams}"><c:out value="${'>>'}" /></a></li>
						</c:if>
					</ul>
				</li>
				<!-- /페이지 버튼 -->
				
				<!-- 검색 -->
				<li class="list-group-item">
					<form class="row justify-content-center" method="get">
						<input type="hidden" name="pageNum" value="1">
						<input type="hidden" name="pageSize" value="${page.pageSize}">
						<div class="col-auto">
							<select class="form-select" name="searchType">
								<option value="TC" ${page.searchType == "TC" ? "selected" : ""}>제목 + 내용</option>
								<option value="T" ${page.searchType == "T" ? "selected" : ""}>제목</option>
								<option value="C" ${page.searchType == "C" ? "selected" : ""}>내용</option>
								<option value="W" ${page.searchType == "W" ? "selected" : ""}>작성자</option>
							</select>
						</div>
						<div class="col-auto">
							<input class="form-control" type="text" name="searchKeyword" value="${page.searchKeyword}" required>
						</div>
						<div class="col-auto">
							<button class="btn btn-primary" type="submit">검색</button>
						</div>
					</form>
				</li>
				<!-- /검색 -->
			</ul>
			<!-- /list-group -->
		</div>
		<!-- /card -->
	</div>
	<!-- /container -->
</div>

<script type="text/javascript">
switch ('${result}') {
case 'success':
	break
case 'failure':
	alert('오류가 있습니다.')
	history.back()
	break
}
</script>

<%@ include file="../include/footer.jsp"%>