<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<script type="text/javascript" src="./js/menu.js"></script>

</head>
<body>
	<div class="container">
		<header>
			<%@ include file="menu.jsp"%>

<!-- 내가 파일 추가 시킬때 제일 마지막에 다 가져와서 컴파일 시키는거야(첨부하기) -->
			<!--  jsp:은 출력 결과만 화면에 나옵니다. -->
		</header>
		<div class="main"></div>
		<div>
			<article class="arti">
			<h1>ヽ(✿ﾟ▽ﾟ)ノ ヽ(✿ﾟ▽ﾟ)ノ ヽ(✿ﾟ▽ﾟ)ノ ヽ(✿ﾟ▽ﾟ)ノ ヽ(✿ﾟ▽ﾟ)ノ ヽ(✿ﾟ▽ﾟ)ノ ヽ(✿ﾟ▽ﾟ)ノ ヽ(✿ﾟ▽ﾟ)ノ<br>ヽ(✿ﾟ▽ﾟ)ノヽ(✿ﾟ▽ﾟ)ノヽ(✿ﾟ▽ﾟ)ノ  설날까지 D-day 17 ヽ(✿ﾟ▽ﾟ)ノヽ(✿ﾟ▽ﾟ)ノヽ(✿ﾟ▽ﾟ)ノ  <br>ヽ(✿ﾟ▽ﾟ)ノ ヽ(✿ﾟ▽ﾟ)ノ ヽ(✿ﾟ▽ﾟ)ノヽ(✿ﾟ▽ﾟ)ノ ヽ(✿ﾟ▽ﾟ)ノ ヽ(✿ﾟ▽ﾟ)ノヽ(✿ﾟ▽ﾟ)ノヽ(✿ﾟ▽ﾟ)ノ</h1>
	
			<img alt="GO" src="./image/happy.jpg" width="30%;"><br>
			<img alt="GO" src="./image/GO.jpg" width="30%;">
/				
			</article>
		</div>
		<footer>
		<c:import url="footer.jsp"/>
		</footer>
		</div>
</body>
</html>