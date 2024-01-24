package com.poseidon.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dao.LogDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.util.Util;

@WebServlet("/board")
//web에 있는 주석방법을 이렇게 간단하게 한것
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	      //location.href="./board?page="+no; 여기서 이름이 page로 설정된것
	      
	      int page = request.getParameter("page") == null || request.getParameter("page").equals("")? 1 : Util.str2Int(request.getParameter("page"));
	      
	      // 페이지버튼 말고 게시판을 누르면 500에러 뜨는걸 잡아주는 구문
	      
	      
	      //log
	      Map<String, Object> log = new HashMap<String, Object>();
	      log.put("ip", Util.getIP(request));
	      log.put("url", "./board");
	      log.put("data", "page=" + page);
	      LogDAO logDAO = new LogDAO();
	      logDAO.write(log);
	      
	      
	      BoardDAO dao = new BoardDAO();
	      List<BoardDTO> list = dao.boardList(page);
	      int totalCount = dao.totalCount();
	      
	      request.setAttribute("list", list); // "list"라는 변수명으로 보낸다
	      request.setAttribute("totalCount", totalCount);
	      request.setAttribute("page", page);
	      
	      RequestDispatcher rd = request.getRequestDispatcher("board.jsp"); //board.jsp페이지로
	      rd.forward(request, response);
	   }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
