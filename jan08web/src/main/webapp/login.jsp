<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<script type="text/javascript" src="./js/menu.js"></script>
<style type="text/css">
.login {
	margin: 0 auto;
	width: 300px;
	min-height: 300px;
	background-color: #E6E6FA;
	padding: 10px;
	box-sizing: border-box;
	text-align: center;
}

.login input {
	width: 100%;
	height: 30px;
	text-align: center;
	color: green;
	margin-bottom: 10px;
	box-sizing: border-box;
}

.login button {
	width: 45%;
	height: 30px;
	color: rgb(102, 47, 142);
	font-size: large;
}
</style>
<script type="text/javascript">
	function err() {
		let errBox = document.querySelector("#errorMSG");
		//errBox.innerText = "올바른 id랑 pw 입력해야지?";
		errBox.innerHTML = "<marquee>샤샤샤샤샤샤샤샤샥 틀렸네 푸하하하하핳 샤샤샤샤샤샤샤샤샤샤샥</marquee>";
		//이렇게 HTML 사용할수도 있음 : 글자 옆으로 휘리릭
		//https://velog.io/@warmwhiten/%EB%B8%8C%EB%9D%BC%EC%9A%B0%EC%A0%80%EC%99%80-%EB%B8%8C%EB%9D%BC%EC%9A%B0%EC%A0%80-%EC%97%94%EC%A7%84
		errBox.style.color = 'red';
	}
</script>


</head>
<body>
	<div class="container">
		<header>
			<%@ include file="menu.jsp"%>
			<!-- 			<jsp:include page="menu.jsp"></jsp:include> --!>
			<!--  jsp:은 출력 결과만 화면에 나옵니다. -->
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<h1>Login</h1>

					<c:if test="${param.error ne null }">
						<!-- ne는 not equal이라는 뜻 -->
						<!-- 에러가 들어오면 이 스크립트를 띄워주는거야 -->
						<!-- 파라미터에 error라는 값을 적어줘 이렇게 하면 에러 잡을 수있어-->
						<script type="text/javascript">
							alert("올바른 암호와 아이디를 입력하세요.")
							var errorBox = document.getElementById("errorMSG");
							errorBox.innerHTML = "올바른 암호와 아이디를 입력하세요.";
						</script>
					</c:if>

					<div class="login">
						<form action="./login" method="post">
							<img alt="login" src="./image/img.jpg" width="100%;"> <input
								type="text" name="id" placeholder="아이디를 입력하세요">
							<!-- 자바를 쓸때 name에 써 -->
							<input type="password" name="pw" placeholder="암호를 입력하세요">
							<button type="reset">지우기</button>
							<button type="submit">로그인</button>
							<div id="errorMSG"></div>
						</form>
						<a href="./join">회원가입</a>
					</div>

				</article>
			</div>

			<footer>
				<c:import url="footer.jsp" />
			</footer>

		</div>

		<c:if test="${param.error ne null }">
			<script type="text/javascript">
				err();
			</script>
		</c:if>
	</div>
</body>
</html>