package com.poseidon.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.CommentDAO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

@WebServlet("/recomment")
public class ReComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReComment() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int result = 0;
		if (session.getAttribute("mid") != null && Util.intCheck2(request.getParameter("cno"))
				&& request.getParameter("comment") != null) {

			CommentDTO dto = new CommentDTO();
			dto.setCno(Util.str2Int(request.getParameter("cno")));
			dto.setCcomment(request.getParameter("comment"));

			CommentDAO dao = new CommentDAO();
			result = dao.commentUpdate(dto);

		}

		PrintWriter pw = response.getWriter();
		pw.print(result);

	}

}
