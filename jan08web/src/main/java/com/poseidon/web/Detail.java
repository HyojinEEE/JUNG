package com.poseidon.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dao.LogDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

@WebServlet("/detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public Detail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//오는 no 잡기
		
		//int no = Integer.parseInt(request.getParameter("no"));
		int no = Util.str2Int(request.getParameter("no"));
		System.out.println(no);
		//log
		LogDAO log = new LogDAO();
		log.write(Util.getIP(request), "./detail", "no="+no);
		
		
		//데이터베이스에 질의하기
		BoardDAO dao = new BoardDAO();
		//log
		//LogDAO log = new LogDAO();
		log.write(Util.getIP(request), "./detail", "no="+no);

		//로그인 한 회원이라면 읽음수 올리기 2024-01-19
		HttpSession session = request.getSession();
		if (session.getAttribute("mid") != null) {
			//bno, mno
			dao.countUp(no, (String) session.getAttribute("mid"));
		}
		
		BoardDTO dto = dao.detail(no);
		
		// System.out.println(dto.getTitle());
		// System.out.println(dto.getContent() == null);
		// 해당하는 no=A59번 글이 있으면 홈페이지에 뜨는거임
		// 만약 없다면?
		
		// null이면 에러로
		if ( no == 0 || dto.getContent() == null) {
			// 뒤에 번호가 0이거나 내용이 없는 게시물 번호이면 
			response.sendRedirect("error.jsp");
			// 에러 홈페이지로 이동해
			
		} else {
			//내용가져오기
			request.setAttribute("detail", dto);
			
			//댓글이 있다면 List 뽑아내기
			List<CommentDTO> commentList = dao.commentList(no);
			//Board에 생성해
			
			if(commentList.size() > 0) {
				request.setAttribute("commentList", commentList);
//				댓글수가 있으면 집어넣기
			}
			
			//리퀘스트디스패쳐 호출하기
			RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
			rd.forward(request, response);
			
			// 큰 수를 넣으면 500에러가 난다 -> int 범위를 벗어나서 그런거임
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
