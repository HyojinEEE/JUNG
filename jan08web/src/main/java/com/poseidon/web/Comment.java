package com.poseidon.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.CommentDAO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

@WebServlet("/comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Comment() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		// 오는 값 받기
		String commentcontent = request.getParameter("commentcontent");// 댓글내용
		
		
		
	
		
		//HTML에서 태그를 특수기호로 변경하기
		commentcontent = Util.removeTag(commentcontent);
		
		
		// 2024-01.23
		// 엔터처리(댓글에서 엔터를 쳐도 옆으로만 나오는거 잡아줌)  /r  /n  /nr 이거를 엔터로 변경하자
		// Util에 추가하고 -> Comment에 저장

		commentcontent = Util.addBR(commentcontent);
		
		
		
		
		
		String bno = request.getParameter("bno"); // 글번호

		// 컨텐츠 값이 null이 아닐때
		if (session.getAttribute("mid") != null && commentcontent != null && bno != null) {

			// System.out.println(commentcontent + " : " + bno);

			// 저장해주세요.
			CommentDTO dto = new CommentDTO();
			dto.setCcomment(commentcontent);
			dto.setBoard_no(Util.str2Int(bno));
			//util이라는 클래스 안에 있는 str2Int 메소드 사용해서bno를 String -> int로 바꿔준거 
			dto.setMid((String) session.getAttribute("mid"));
			dto.setCip(Util.getIP(request));
			
			// dao 연결해
			CommentDAO dao = new CommentDAO();
			int result = dao.commentWrite(dto);
			// 이 결과값이 0 아니면 1이야 (dao에서 받아왔으니까)

			// 이동해주세요.
			if (result == 1) {
				response.sendRedirect("./detail?no=" + bno);

			} else {
				response.sendRedirect("./error.jsp");

			}

		} else {
			response.sendRedirect("./error.jsp");
		}
	}

}
