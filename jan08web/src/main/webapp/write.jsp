<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<script type="text/javascript" src="./js/menu.js"></script>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<style type="text/css">
#title{
	width: 100%;
	height: 30px;
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<div class="container1">
		<header>
			<jsp:include page="menu.jsp"></jsp:include>
			<!--  jsp:은 출력 결과만 화면에 나옵니다. -->
		</header>
		<div class="main">
			<div class="mainStyle">
					<article>
						<h1>글쓰기</h1>
						<div class="writeFORM">
							<form action="./write" method="post">
								<input type="text" id="title" name="title">
<!-- 								자바는 name만 가져갈 수 있어 -->
								<textarea id="summernote" name="content"></textarea>
								<button type="submit">저장하기</button>
							</form>
						</div>
					</article>
				</div>
			</div>
			<footer>
			<c:import url="footer.jsp"/>
			</footer>
		</div>
		<script>
		$(document).ready(function() {
		 $('#summernote').summernote({
			 height:500
		 });
		});
		</script>
</body>
</html>