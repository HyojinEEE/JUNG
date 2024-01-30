package com.poseidon.util;

import javax.servlet.http.HttpServletRequest;

public class Util {
	// String값이 들어가면 int타입인지 확인해보는 메소드
	// 127 -> true
	// 1A2A5 -> false

	// A59 -> 59 로 나오게 해줘
	// 5A9 -> 59
	// 이 메소드는 문자 제외하고 숫자만 나오게 해주라는거임

	// 방법1
	public static int str2Int(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				sb.append(str.charAt(i));
			}
		}
		int number = 0;
		if (sb.length() > 0) {
			number = Integer.parseInt(sb.toString());
		}
		System.out.println("변환된 숫자 " + number);
		return number;

	}

	// 방법2
	public static int str2Int2(String str) {
		String numberOnly = str.replaceAll("[^0-9]", "");
		return Integer.parseInt(numberOnly);
	}

	public static boolean intCheck(String str) {
		try {
			// 예외가 발생될 것 같을 문장을 여기다 넣어준거야
			// str을 숫자로 바꾸는거
			Integer.parseInt(str);
			// 들어오는 str녀석을 int로 바꾸는거야
			return true;
			// 위에가 된다면 true 값이 출력

		} catch (Exception e) {
			return false;

		}
	}

	public static boolean intCheck2(String str) {
		boolean result = true;
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				result = false;
				break;
			} else {
				result = true;
			}
		}
		return result;
	}

	// ip 가져오기
	public static String getIP(HttpServletRequest request) {

		String ip = request.getHeader("X-FORWARDED-FOR");

		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	// HTML태그를 특수기호로 변경하기 
	public static String removeTag(String str) {
		str = str.replace("<", "&lt");
		str = str.replace(">", "&gt");

		return str;

	}

	// 엔터키 처리 (br로 바꿔준다) - Comment.java랑 같이 봐
	public static String addBR(String str) {
		return str.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
	}

	// 아이피 중간을 ♡로 가리기 172.30.1.27 -> 172.♡.1.63
	public static String ipMasking(String ip) {
		if (ip.indexOf('.') != -1) {
			StringBuffer sb = new StringBuffer(); // 멀티스레드 환경에서도 동기화 지원
			sb.append(ip.substring(0, ip.indexOf('.')));
			sb.append(".😒.");
			sb.append(ip.substring(ip.indexOf('.', 6) + 1));
			return sb.toString();
		} else {
			return ip;
		}

	}

}
