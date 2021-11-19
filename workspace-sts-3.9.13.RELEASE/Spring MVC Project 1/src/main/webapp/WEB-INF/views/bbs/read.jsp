<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../include/header.jsp"%>

<c:set var="searchParams" value="${
	page.searchKeyword != null ? String.format(\"&searchType=%s&searchKeyword=%s\", param.searchType, param.searchKeyword) : ''}"></c:set>

<div class="d-flex align-items-center min-vh-100">
	<!-- container -->
	<div class="container-fluid" style="max-width: 1000px">
		<!-- card -->
		<div class="card">
			<div class="card-header">
				<div class="row">
					<div class="col">#${post.num}</div>
				</div>
			</div>
			<div class="card-header">
				<div class="row">
					<div class="col">${post.title}</div>
				</div>
			</div>
			<div class="card-header">
				<div class="row justify-content-end">
					<div class="col">${post.writer}</div>
					<div class="col-auto">
						<c:choose>
							<c:when test="${post.writeDate.getTime() > System.currentTimeMillis() - (1000*3600*24*30)}">
								<fmt:formatDate value="${post.updateDate}" pattern="'수정: 'yyyy.MM.dd HH:mm:ss" />
							</c:when>
							<c:otherwise>
								<fmt:formatDate value="${post.updateDate}" pattern="'수정: 'HH:mm:ss" />
							</c:otherwise>
						</c:choose>
					</div>
					<div class="col-auto">
						<c:choose>
							<c:when test="${post.writeDate.getTime() > System.currentTimeMillis() - (1000*3600*24*30)}">
								<fmt:formatDate value="${post.writeDate}" pattern="'작성: 'yyyy.MM.dd HH:mm:ss" />
							</c:when>
							<c:otherwise>
								<fmt:formatDate value="${post.writeDate}" pattern="'작성: 'HH:mm:ss" />
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<!-- list-group -->
			<ul class="list-group list-group-flush">
				<li class="list-group-item">
					<div class="row" style="min-height: 20vh">
						<div class="col">${post.content}</div>
					</div>
				</li>
				<li class="list-group-item">
					<div class="row justify-content-end">
						<div class="col-auto">
							<a class="btn btn-warning" role="button" href="/bbs/edit?num=${post.num}">수정</a>
						</div>
						<div class="col-auto">
							<button class="btn btn-danger comment-delete-button" type="button" data-bs-toggle="modal" data-bs-target="#postDeleteModal">삭제</button>
						</div>
					</div>
				</li>
				<!-- 댓글 목록 -->
				<li class="list-group-item">
					<div class="row">
						<div class="col">댓글</div>
					</div>
					<div id="comment-list"></div>
					<template id="comment-template">
						<div class="card my-3 comment-card">
							<div class="card-header">
								<div class="row justify-content-end">
									<div class="col comment-writer">작성자</div>
									<div class="col-auto comment-update-date">수정: 0000.00.00 00:00:00</div>
									<div class="col-auto comment-write-date">작성: 0000.00.00 00:00:00</div>
									<div class="col-auto btn-group">
										<button type="button" class="btn btn-sm btn-outline-primary comment-reply-button">답글</button>
										<button type="button" class="btn btn-sm btn-outline-primary dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown"></button>
										<ul class="dropdown-menu">
											<li><button class="dropdown-item comment-edit-button" type="button">수정</button></li>
											<li><button class="dropdown-item comment-delete-button" type="button" data-bs-toggle="modal" data-bs-target="#commentDeleteModal">삭제</button></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="card-body">
								<div class="row">
									<div class="col comment-content">내용</div>
								</div>
							</div>
						</div>
					</template>
					<!-- 댓글 작성 카드 -->
					<div id="comment-write-card" class="card my-3">
						<div class="card-header">
							<div class="row">
								<div class="col-3">
									<input class="form-control data-writer" type="text" placeholder="이름">
								</div>
								<div class="col-3">
									<input class="form-control data-password" type="password" placeholder="암호">
								</div>
								<div class="col-auto ms-auto">
									<button class="btn-close" type="button" style="display: none;"></button>
								</div>
							</div>
						</div>
						<ul class="list-group list-group-flush">
							<li class="list-group-item">
								<div class="row">
									<div class="col">
										<textarea class="form-control data-content" rows="" cols="" placeholder="내용"></textarea>
									</div>
								</div>
							</li>
							<li class="list-group-item">
								<div class="row justify-content-end">
									<div class="col-auto">
										<button class="btn btn-warning submit-button" type="button" onclick="writeComment()">제출</button>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<!-- /댓글 작성 카드 -->
				</li>
				<!-- /댓글 목록 -->
				<!-- 댓글 페이지 버튼 -->
				<li class="list-group-item">
					<ul id="page-button-group" class="pagination justify-content-center">
						<li class="page-item page-button-arrow"><a class="page-link" href="#comment-list"><c:out value="${'<<'}" /></a></li>
						<li class="page-item page-button-arrow"><a class="page-link" href="#comment-list"><c:out value="${'<'}" /></a></li>

						<c:forEach var="var" begin="1" end="10">
							<li class="page-item page-button-number"><a class="page-link" href="#comment-list">${var}</a></li>
						</c:forEach>

						<li class="page-item page-button-arrow"><a class="page-link" href="#comment-list"><c:out value="${'>'}" /></a></li>
						<li class="page-item page-button-arrow"><a class="page-link" href="#comment-list"><c:out value="${'>>'}" /></a></li>
					</ul>
				</li>
				<li class="list-group-item">
					<div class="row justify-content-end">
						<div class="col-auto">
							<a id="goto-list-button" class="btn btn-primary" role="button" href="/bbs?pageNum=${page.pageNum}&pageSize=${page.pageSize}${searchParams}">목록</a>
						</div>
					</div>
				</li>
				<!-- /댓글 페이지 버튼 -->
			</ul>
			<!-- /list-group -->
		</div>
		<!-- /card -->
	</div>
	<!-- /container -->
</div>

<!-- Modal -->
<div class="modal fade" id="postDeleteModal" tabindex="-1">
	<div class="modal-dialog modal-sm modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				게시글 삭제
				<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>
			<div class="modal-body">
				<input class="form-control data-password" type="password" placeholder="암호">
			</div>
			<div class="modal-footer">
				<button class="btn btn-warning submit-button" type="button" onclick="deletePost(${post.num})">제출</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="commentDeleteModal" tabindex="-1">
	<div class="modal-dialog modal-sm modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				댓글 삭제
				<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>
			<div class="modal-body">
				<input class="form-control data-password" type="password" placeholder="암호">
			</div>
			<div class="modal-footer">
				<button class="btn btn-warning submit-button" type="button">제출</button>
			</div>
		</div>
	</div>
</div>
<!-- /Modal -->

<script type="text/javascript">
switch ('${result}') {
case 'success':
	break
case 'failure':
	alert('암호가 틀렸습니다.')
	history.back()
	break
}
</script>

<script type="text/javascript" src="/resources/bbs/read.js"></script>
<script type="text/javascript" src="/resources/bbs/commentService.js"></script>

<%@ include file="../include/footer.jsp"%>