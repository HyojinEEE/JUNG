<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<link href="./css/board.css" rel="stylesheet" />
<script type="text/javascript" src="./js/menu.js"></script>


</head>
<body>
	<div class="container">
		<header>
			<%@ include file="menu.jsp"%>
<!-- 		menu.jsp를 가져오고 자바를 실행시킬거야 -->
		</header>
		<div class="main">
			<div class="mainStyle">
			
				<article>
					<%-- for문 연습해보기<br>
					<c:forEach items="${list }" var="e" varStatus="s">
						${e.no } / ${s.first } / ${s.last } / ${s.index } / ${s.count } / ${s.step }<br>
					</c:forEach> --%>
				</article>

				<article>
					<c:choose>
						<c:when test="${fn:length(list) gt 0 }">
							<table>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>날짜</th>
									<th>읽음</th>
								</tr><c:forEach items="${list }" var="row">
									<tr>
										<td class="w1" >${row.no }</td>
										<td class="title"><a href="./detail?page=${page }&no=${row.no }">${row.title }
										&ensp;<c:if test="${row.comment ne 0 }">
										<span>[${row.comment }]</span></c:if>
										</a></td>
										<td class="w2">${row.write }</td>
										<td class="w1">${row.date }</td>
										<td class="w1">${row.count }</td>
									</tr></c:forEach>
							</table>
							
							<c:set var="totalPage" value="${totalCount / 10 }"/>
							<fmt:parseNumber integerOnly="true" value="${totalPage }" var="totalPage"/>
							<c:if test="${totalCount % 10 gt 0 }">
								<c:set var="totalPage" value="${totalPage + 1 }"/>
							</c:if>
							
							<c:set var="startPage" value="1"/> 
							<c:if test="${page gt 5 }">
								<c:set var="startPage" value="${page - 5 }"/>
							</c:if>
							<c:set var="endPage" value="${startPage + 9 }"/> 
							<c:if test="${page + 4 gt totalPage }">
								<c:set var="startPage" value="${totalPage - 9 }"/>
								<c:set var="endPage" value="${totalPage }"/>
							</c:if>
							
							
						<div class="paging">
								<button onclick="paging(1)">⏮️</button>
								<button 
								<c:if test="${page - 10 lt 1 }">disabled="disabled"</c:if> 
								onclick="paging(${page - 10 })">◀️</button>
								<!-- 버튼 안에다가 if문을 넣어준거야 10페이지 이상이면 ◀️버튼이 활성화(반대도 동일) -->
								<c:forEach begin="${startPage }" end="${endPage }" var="p">
									<button
									<c:if test="${page eq p}">class="currentBtn" </c:if> 
									onclick="paging(${p})">${p }</button>
								</c:forEach>
								<button 
								<c:if test="${page + 10 gt totalPage }">disabled="disabled"</c:if> 
								onclick="paging(${page + 10 })">▶️</button>
								<button onclick="paging(${totalPage })">⏭️</button>
							</div>
							
						</c:when>
						<c:otherwise>
							<h1>출력할 값이 없습니다.</h1>
						</c:otherwise>
					</c:choose>
					
					<c:if test="${sessionScope.mname ne null }">
						<button onclick="url('./write')">글쓰기</button>
						<!-- 로그인을 해야 게시판에서 글쓰기 버튼이 보이는거 -->
					</c:if>

				</article>
				<article>
					<%-- <fmt:requestEncoding value="UTF-8" />
					<fmt:setLocale value="ko_kr" />
					<fmt:formatNumber value="3.14" type="number" />
					<fmt:parseNumber value="3.14" integerOnly="true" />

					<c:set var="nowDate" value="<%=new Date()%>" />
					${nowDate } <br>
					<fmt:formatDate type="time" value="${nowDate }" />
					<br>
					<fmt:formatDate type="date" value="${nowDate }" />
					<br>
					<fmt:formatDate type="both" value="${nowDate }" />
					<br>
					<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
						value="${nowDate }" />
					<br>
					<fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"
						value="${nowDate }" />
					<br>
					<fmt:formatDate type="both" dateStyle="long" timeStyle="long"
						value="${nowDate }" />
					<br>
					<fmt:formatDate pattern="yyyy-MM-dd" value="${nowDate }" />
 --%>
				</article>
				<article>
				<%-- 	fn이용해서 자료형 데이터 길이 뽑아내기 ${fn:length(list) }<br>
 --%>


				</article>
			</div>
		</div>
		<footer>  ※ 너무너무너무 집에 가고싶다 전기장판에 누워서 귤 까먹고 싶어요※ </footer>
	</div>
	<script type="text/javascript">
		function paging(no) {
			location.href="board?page="+no;
		}
	</script>	
</body>
</html>

