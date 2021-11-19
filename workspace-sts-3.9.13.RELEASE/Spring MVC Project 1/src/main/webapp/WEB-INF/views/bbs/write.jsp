<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="../include/header.jsp"%>

<div class="d-flex align-items-center min-vh-100">
	<!-- container -->
	<div class="container-fluid" style="max-width: 1000px">
		<!-- card -->
		<div class="card">
			<!-- list-group -->
			<ul class="list-group list-group-flush">
				<li class="list-group-item">
					<div>
						<form:form commandName="postForm">
							<div class="row mb-3">
								<div class="col">
									<input type="text" class="form-control" placeholder="제목" name="title" required="required">
								</div>
							</div>
							<div class="row mb-3">
								<div class="col">
									<!-- CKEditor -->
									<textarea id="editor" name="content"></textarea>
									<!-- /CKEditor -->
								</div>
							</div>
							<div class="row">
								<div class="col-2">
									<input type="text" class="form-control" placeholder="이름" name="writer" required="required">
								</div>
								<div class="col-2">
									<input type="password" class="form-control" placeholder="암호" name="password" required="required">
								</div>
							</div>
							<div class="row justify-content-end">
								<div class="col-auto">
									<button class="btn btn-warning" type="submit">제출</button>
								</div>
							</div>
						</form:form>
					</div>
				</li>
				<li class="list-group-item">
					<div class="row justify-content-end">
						<div class="col-auto">
							<a id="gotoListButton" class="btn btn-primary" role="button" href="/bbs">목록</a>
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

<script type="text/javascript" src="/resources/bbs/write.js"></script>

<!-- CKEditor -->
<script type="text/javascript" src="/resources/ckeditor5-build-classic/ckeditor.js"></script>
<script type="text/javascript" src="/resources/ckeditor5-build-classic/translations/ko.js"></script>
<script>
ClassicEditor
.create( document.querySelector( '#editor' ), {
    language: 'ko'
} )
.then( editor => {
    window.editor = editor;
} )
.catch( err => {
    console.error( err.stack );
} );
</script>
<!-- /CKEditor -->

<%@ include file="../include/footer.jsp"%>