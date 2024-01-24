package com.poseidon.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.LogDAO;
import com.poseidon.dao.MemberDAO;
import com.poseidon.dto.MemberDTO;
import com.poseidon.util.Util;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 이 사람이 로그인 했는지 알아봐야함 
		HttpSession session = request.getSession();
		
		String url = null;
		if(session.getAttribute("mname") != null) {
			url = "already.jsp";
		} else {
			url = "login.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 2024-01-12 웹 서버프로그램 구현
		if (request.getParameter("id") != null && request.getParameter("pw") != null) {
			MemberDTO dto = new MemberDTO();
			dto.setMid(request.getParameter("id"));
			dto.setMpw(request.getParameter("pw"));
			
			MemberDAO dao = new MemberDAO();
			dto = dao.login(dto);
			
			// 아이피도 저장하기
			Map<String, Object> log = new HashMap<String, Object>();
			log.put("ip", Util.getIP(request));
			log.put("url", "./login");
			log.put("data", "id:"+dto.getMid() + ",pw:" + dto.getMpw() + " 결과 : " + dto.getCount());
			
			LogDAO logDAO = new LogDAO();
			logDAO.write(log);
			
			
			// 아이디랑 비번이 정상이면 : 정상로그인 
			// 아이디랑 비번이 틀리면 : 헬로우 사만다 =안녕하세요 시어도르님이 콘솔창에 출력된다
			if(dto.getCount() == 1) {
				System.out.println("정상 로그인");
				// 세션만들기(암기하기)
				HttpSession session = request.getSession();
				session.setAttribute("mname", dto.getMname());//mname이라는 이름으로 세션 만듬
				session.setAttribute("mid", dto.getMid());//mid라는 이름으로 세션 만듬
				
				
				// 페이지 이동 =board
				response.sendRedirect("./board");
			} else {
				//System.out.println("헬로우 사만다 = 안녕하세요 시어도르님");
				// 페이지 이동 = login?error=4567
				response.sendRedirect("./login?error=4567");
			}
			
		}	else {
			
		}
	}

}
