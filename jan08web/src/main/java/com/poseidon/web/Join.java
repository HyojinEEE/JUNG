package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.MemberDAO;
import com.poseidon.dto.MemberDTO;

@WebServlet("/join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Join() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("join.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 회원가입 하는거
		// 값 잡기 id  name  pw1 
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw1");
		// 얘네는 dao로 보낼거야
		
		// db에 보내기
		MemberDTO dto = new MemberDTO();
		dto.setMid(id);
		dto.setMname(name);
		dto.setMpw(pw);
		MemberDAO dao = new MemberDAO();
		int result = dao.join(dto);
		// dao에서 조인 메소드에 dto값을 넣겠다
				
		// 정상적으로 데이터입력을 완료했다면 로그인 페이지로,
		if(result == 1) {
			response.sendRedirect("./login");
//			login 서블릿으로 보낸거야
		} else {
			response.sendRedirect("./error.jsp");
//			error는 서블릿 안만들어서 jsp로 보내기
		}
		// 비정상이라면 에러로 보내기
	} 

}
