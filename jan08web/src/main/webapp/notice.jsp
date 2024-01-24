<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>notice</title>
<link href="./css/index.css" rel="stylesheet"/>
<link href="./css/menu.css" rel="stylesheet"/>
<script type="text/javascript" src="./js/menu.js"></script>

</head>
<body>
	<div class="container">
		<header>
			<%@ include file="menu.jsp"%>
			<!--  jsp:은 출력 결과만 화면에 나옵니다. -->
		</header>
		<div class="main"></div>
			<div>
				<article>
					<h1>휴일 공지사항</h1>
					<ul>
						<li>2월 : 설날 (2024.02.09~02.12)</li>
						<li>3월 : 삼일절 (2024.03.01)</li>
						<li>4월 : 22대 국회의원 선거(2024.04.10)</li>
						<li>5월 : 어린이날 대체 휴일 (2024.05.06)</li>
						<li>5월 : 스승의 날 (2024.05.15)</li>
					</ul>
				</article>
		</div>
		
	
</body>
</html>