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

<div class="d-flex align-items-center min-vh-100">
	<!-- container -->
	<div class="container-xxl col-lg-10 col-xl-8 col-xxl-6">
		<!-- card -->
		<div class="card">
			<!-- list-group -->
			<ul class="list-group list-group-flush">
				<li class="list-group-item">
					<!-- 게시글 목록 -->
					<table id="postList" class="table table-hover">
						<thead>
							<tr>
								<th class="col-1" scope="col"># ${result}</th>
								<th class="col-6" scope="col">제목</th>
								<th class="col-2" scope="col">작성자</th>
								<th class="col-3" scope="col">작성일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="post">
								<tr>
									<th scope="row">${post.num}</th>
									<td><a href="bbs/read?num=${post.num}&pageNum=${page.pageNum}&pageSize=${page.pageSize}">${post.title}</a></td>
									<td>${post.writer}</td>
									<td><fmt:formatDate value="${post.writeDate}" pattern="yyyy.MM.dd HH:mm:ss" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table> <!-- /게시글 목록 -->
				</li>

				<li class="list-group-item">
					<div class="row justify-content-end">
						<div class="col-auto">
							<a class="btn btn-primary" href="bbs/write">작성</a>
						</div>
					</div>
				</li>

				<li class="list-group-item">
					<!-- 페이지 버튼 -->
					<ul class="pagination justify-content-center">
						<c:set var="pageSize" value="pageSize=${page.pageSize}" />

						<c:if test="${page.prev}">
							<li class="page-item"><a class="page-link" href="?pageNum=1&${pageSize}"><c:out value="${'<<'}" /></a></li>
							<li class="page-item"><a class="page-link" href="?pageNum=${page.startPage - 1}&${pageSize}"><c:out value="${'<'}" /></a></li>
						</c:if>

						<c:forEach var="var" begin="${page.startPage}" end="${page.endPage}">
							<c:set var="active" value="" />
							<c:if test="${var == page.pageNum}">
								<c:set var="active" value="active" />
							</c:if>
							<li class="page-item ${active}"><a class="page-link" href="?pageNum=${var}&${pageSize}">${var}</a></li>
						</c:forEach>

						<c:if test="${page.next}">
							<li class="page-item"><a class="page-link" href="?pageNum=${page.endPage + 1}&${pageSize}"><c:out value="${'>'}" /></a></li>
							<li class="page-item"><a class="page-link" href="?pageNum=${page.totalPage}&${pageSize}"><c:out value="${'>>'}" /></a></li>
						</c:if>
					</ul> <!-- /페이지 버튼 -->
				</li>
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
	alert('작성 되었습니다.')
	break
case 'failure':
	alert('오류가 있습니다.')
	history.back()
	break
}
</script>

<%@ include file="../include/footer.jsp"%>