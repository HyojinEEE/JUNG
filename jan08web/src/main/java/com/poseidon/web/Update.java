package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.util.Util;

@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Update() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// 화면에 보여주는거
		// no잡기
		// if(Util.intCheck(request.getParameter("no"))) {
		// int no = Util.str2Int(request.getParameter("no"));
		HttpSession session = request.getSession();
		// 세션이 있을 때 = 정상작업하기
		if (session.getAttribute("mid") != null) {
			int no = Util.str2Int(request.getParameter("no"));
			// DAO에 질의하기
			BoardDAO dao = new BoardDAO();
			BoardDTO dto = dao.detail(no);
			// jsp에 보내기
			// System.out.println(dto.getMid().equals(session.getAttribute("mid")));
			// System.out.println(session.getAttribute("mid").equals(dto.getMid()));
			// System.out.println(((String)session.getAttribute("mid")).equals(dto.getMid()));
			// 위 3 개는 동일한 내용
			// System.out.println(session.getAttribute("mid") == dto.getMid());
			// 세션에서 로그인 한 mid랑 dto에서 ..db에서 가져온 mid랑 같은지 나타내는 if문

			if (session.getAttribute("mid").equals(dto.getMid())) {
				request.setAttribute("update", dto);
				RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("./error.jsp");
			}

		} else {
			response.sendRedirect("./login?login=nologin");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		HttpSession session =request.getSession();
		if (request.getParameter("title") != null && request.getParameter("content") != null
				&& Util.intCheck(request.getParameter("no")) && session.getAttribute("mid") != null) {
			// 진짜 수정
			BoardDTO dto = new BoardDTO();
			dto.setContent(request.getParameter("content"));
			dto.setTitle(request.getParameter("title"));
			dto.setNo(Util.str2Int(request.getParameter("no")));
			dto.setMid((String) session.getAttribute("mid"));

			BoardDAO dao = new BoardDAO();
			int result = dao.update(dto);

			// System.out.println("수정 결과 : " + result);

			if (result == 1) {
				response.sendRedirect("./detail?no=" + request.getParameter("no"));
  
				
				
				 
			} else {
				// error
				response.sendRedirect("./error.jsp");
			}

		} else {

		}

	}
}
