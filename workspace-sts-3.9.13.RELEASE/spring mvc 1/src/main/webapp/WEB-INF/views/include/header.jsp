<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="ko">

<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link href="${basePath}/resources/bootstrap-5.0.2-dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>

<style>
@media (max-width: 576px) {
	body {
		font-size: 0.8rem
	}
}
@media (min-width: 576px) and (max-width: 768px) {
	body {
		font-size: 0.9rem
	}
}
</style>

<script type="text/javascript">
const basePath = '${basePath}'
</script>
