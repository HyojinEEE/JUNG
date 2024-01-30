<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coffee</title>
<link href="./css/menu.css" rel="stylesheet" />
<script type="text/javascript" src="./js/menu.js"></script>
<script>

 	function 
    function order(menu) {
       // alert("당신이 선택한 메뉴는? " + menu);
        // 여기에 선택한 메뉴에 대한 추가 로직을 작성할 수 있습니다.
    	if (selectedMenu) {
    		 order(selectedMenu.value);
		} else(name="선택")
    }	alert("반가워요. 커피 고르세요");

    function confirmOrder() {
        var selectedMenu = document.querySelector('input[name="menu"]:checked');

        if (selectedMenu) {
            order(selectedMenu.value);
        } else {
            alert("진짜 안드신다구요?");
        }
    }
</script>
</head>
<body>
	<div class="container">
		<header>
			<%@ include file="menu.jsp"%>
		</header>
		<div class="main">
			<article>
				<h1>Cafe Menu</h1>
				<ul>
					<h4>1. 아이스 아메리카노<input type="radio" name="menu" value="1" checked onclick="order('아이스 아메리카노')"></h4>
					<h4>2. 뜨거운 아메리카노 <input type="radio" name="menu" value="2" checked onclick="order('뜨거운 아메리카노')"></h4>
					<h4>3. 아이스티 <input type="radio" name="menu" value="3" checked  onclick="order('아이스티')"></h4>
					<h4>4. 페퍼민트차 <input type="radio" name="menu" value="4" checked  onclick="order('페퍼민트차')"></h4>
					<h4>5. 캐모마일티<input type="radio" name="menu" value="5" checked  onclick="order('캐모마일티')"></h4>
					<h4>6. 로즈마리티<input type="radio" name="menu" value="6" checked  onclick="order('로즈마리티')"></h4>
					<h4>7. 유자차 <input type="radio" name="menu" value="7" checked  onclick="order('유자차')"></h4>
					<h4>8. 자몽티 <input type="radio" name="menu" value="8" checked  onclick="order('자몽티')"></h4>
					<h4>9. 레몬티 <input type="radio" name="menu" value="9" checked  onclick="order('레몬티')"></h4>
					<h4>10. 유자자몽허니블랙티차 <input type="radio" name="menu" value="10" checked  onclick="order('유자자몽허니블랙티차')"></h4>
					<h4>11. 안마셔!!!!! <input type="radio" name="menu" value="11" checked  onclick="order('안마셔!!!!!')"></h4>
				 	<!-- 선택 버튼 추가 -->
				</ul>
                    <h4>
                        <input type="button" name="선택" value="선택" onclick="confirmOrder()">
                    </h4>
			</article>
		</div>




	</div>


</body>
</html>