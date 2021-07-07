<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../include/header.jsp"%>

<div class="d-flex align-items-center min-vh-100">
	<!-- container -->
	<div class="container-xxl col-lg-10 col-xl-8 col-xxl-6">
		<!-- card -->
		<div class="card">
			<!-- list-group -->
			<ul class="list-group list-group-flush">
				<li class="list-group-item">
					<div class="row">
						<div class="col">#${post.num}</div>
					</div>
				</li>
				<li class="list-group-item">
					<div class="row">
						<div class="col">${post.title}</div>
					</div>
				</li>
				<li class="list-group-item">
					<div class="row justify-content-end">
						<div class="col">${post.writer}</div>
						<div class="col-auto">
							<fmt:formatDate value="${post.updateDate}" pattern="'수정: 'yyyy.MM.dd HH:mm:ss" />
						</div>
						<div class="col-auto">
							<fmt:formatDate value="${post.writeDate}" pattern="'작성: 'yyyy.MM.dd HH:mm:ss" />
						</div>
					</div>
				</li>
				<li class="list-group-item">
					<div class="row" style="min-height: 20vh">
						<div class="col">${post.content}</div>
					</div>
				</li>
				<li class="list-group-item">
					<div class="row justify-content-end">
						<div class="col-2">
							<input id="postPassword" class="form-control" type="password" placeholder="암호">
						</div>
						<div class="col-auto">
							<button class="btn btn-danger" type="button" onclick="deletePost(${post.num})">삭제</button>
							<a class="btn btn-warning" role="button" href="/bbs/edit?num=${post.num}">수정</a>
						</div>
					</div>
				</li>
				<li class="list-group-item">
					<div class="row justify-content-end">
						<div class="col-auto">
							<a id="gotoListButton" class="btn btn-primary" role="button" href="/bbs?pageNum=${page.pageNum}&pageSize=${page.pageSize}">목록</a>
						</div>
					</div>
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
	alert('수정 되었습니다.')
	break
case 'failure':
	alert('암호가 틀렸습니다.')
	history.back()
	break
}
</script>

<script type="text/javascript" src="/resources/bbs/read.js"></script>

<%@ include file="../include/footer.jsp"%>