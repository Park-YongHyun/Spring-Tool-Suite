<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp" %>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Read</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Read Page</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="form-group">
					<label>Bno</label> <input class="form-control" name="bno"
						value='<c:out value="${board.bno}" />' readonly="readonly">
				</div>
				<div class="form-group">
					<label>Title</label> <input class="form-control" name="title"
						value='<c:out value="${board.title}" />' readonly="readonly">
				</div>
				<div class="form-group">
					<label>Text area</label> <textarea class="form-control" rows="3" name="content"
						readonly="readonly"><c:out value="${board.content}" /></textarea>
				</div>
				<div class="form-group">
					<label>Writer</label> <input class="form-control" name="writer"
						value='<c:out value="${board.writer}" />' readonly="readonly">
				</div>
				<div class="form-group">
					<label>RegDate</label> <input class="form-control" name="regDate"
						value='<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${board.regdate}"/>' readonly="readonly">
				</div>
				<div class="form-group">
					<label>update Date</label> <input class="form-control" name="updateDate"
						value='<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${board.updateDate}"/>' readonly="readonly">
				</div>
				<button data-oper="modify" class="btn btn-default">Modify</button>
				<button data-oper="list" class="btn btn-info">List</button>
				<form id="operForm" action="/board/modify" method="get">
					<input type="hidden" id="bno" name="bno" value="<c:out value='${board.bno}' />">
					<input type="hidden" name="pageNum" value="<c:out value='${cri.pageNum}' />">
					<input type="hidden" name="amount" value="<c:out value='${cri.amount}' />">
					<input type="hidden" name="type" value="<c:out value='${cri.type}' />">
					<input type="hidden" name="keyword" value="<c:out value='${cri.keyword}' />">
				</form>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-comments fa-fw"></i> Reply
				<button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">New Reply</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<!-- start reply -->
				<template id="template-reply">
					<li class="left clearfix" data-rno="">
						<div>
							<div class="header">
								<strong class="primary-font">replyer</strong>
								<small class="pull-right text-muted">0000.00.00 00:00:00</small>
							</div>
							<p>reply content</p>
						</div>
					</li>
				</template>
				<ul class="chat"></ul>
				<!-- end reply -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<!-- 댓글 -->
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>Reply</label>
					<input class="form-control" name="reply" value="New Reply!!!">
				</div>
				<div class="form-group">
					<label>Replyer</label>
					<input class="form-control" name="replyer" value="replyer">
				</div>
				<div class="form-group">
					<label>Reply Date</label>
					<input class="form-control" name="replyDate" value="">
				</div>
			</div>
			<div class="modal-footer">
				<button id="modalModifyBtn" type="button" class="btn btn-warning">Modify</button>
				<button id="modalRemoveBtn" type="button" class="btn btn-danger">Remove</button>
				<button id="modalRegisterBtn" type="button" class="btn btn-primary">Register</button>
				<button id="modalCloseBtn" type="button" class="btn btn-default">Close</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- /댓글 -->

<!-- 게시글 -->
<script type="text/javascript">
document.addEventListener("DOMContentLoaded", () => {
	let operForm = document.querySelector('#operForm')

	// 수정 페이지로 이동
	document.querySelector('button[data-oper="modify"]').addEventListener("click", () => {
		operForm.setAttribute('action', '/board/modify')
		operForm.submit()
	})

	// 목록 페이지로 이동
	document.querySelector('button[data-oper="list"]').addEventListener("click", () => {
		operForm.querySelector('#bno').remove()
		operForm.setAttribute('action', '/board/list')
		operForm.submit()
	})
})
</script>
<!-- /게시글 -->

<!-- 댓글 -->
<script type="text/javascript" src="/resources/js/reply.js"></script>

<script type="text/javascript">
console.log("===========")
console.log("JS TEST")

let bnoValue = '<c:out value="${board.bno}"/>'
let replyUL = document.querySelector('.chat')

// 댓글 등록 테스트
// replyService.add({ reply: "js test", replyer: "tester", bno: bnoValue }, (result) => {
// 	console.log(result)
// })

// // 댓글 조회 테스트
// replyService.get(2, (data) => {
// 	console.log(data)
// })

// // 게시물의 댓글 조회 테스트
// replyService.getList({ bno: bnoValue, page: 1 }, (list) => {
// 	for(let i = 0, len = list.length; i < len; i++) {
// 		console.log(list[i])
// 	}
// })

// // 댓글 수정 테스트
// replyService.update({ rno: 7, reply: "댓글 수정 테스트" }, (result) => {
// 	console.log(result)
// })

// // 댓글 삭제 테스트
// replyService.remove(5, (result) => {
// 	console.log(result)
// })

showList(1)

// 댓글 표시
function showList(page) {
	replyService.getList({bno: bnoValue, page: page || 1}, (replyList) => {
		replyList.forEach((replyData) => {
			let replyClone = document.querySelector("#template-reply").content.querySelector("li").cloneNode(true)
			replyClone.attributes["data-rno"] = replyData.rno
			replyClone.querySelector("strong").innerText = replyData.replyer
			let date = new Date(replyData.replyDate)
			date = {
				year: date.getFullYear(),
				month: `\${date.getMonth()}`.padStart(2, '0'),
				date: `\${date.getDate()}`.padStart(2, '0'),
				hours: `\${date.getHours()}`.padStart(2, '0'),
				minutes: `\${date.getMinutes()}`.padStart(2, '0'),
				seconds: `\${date.getSeconds()}`.padStart(2, '0')
			}
			replyClone.querySelector("small").innerText = `\${date.year}.\${date.month}.\${date.date} \${date.hours}:\${date.minutes}:\${date.seconds}`
			replyClone.querySelector("p").innerText = replyData.reply
			replyUL.appendChild(replyClone)
		})
	})
}
</script>
<!-- /댓글 -->

<%@ include file="../includes/footer.jsp" %>