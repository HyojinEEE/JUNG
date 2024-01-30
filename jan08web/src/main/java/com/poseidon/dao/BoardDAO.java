package com.poseidon.dao;

// 선생님 코드랑 다른 이유는 이렇게 하나하나 차례로 하면 오류 나는 부분을 쉽게 찾을 수 있기 때문이다.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poseidon.db.DBConnection;
import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

public class BoardDAO extends AbstractDAO {

	public List<BoardDTO> boardList(int page) { // 게시판 첫 화면 나오게함
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		// db정보
		// DBConnection db = DBConnection.getInstance();
		// new는 못쓰고 DBConnection 안에 있는 getInstance 쓸수있어
		// con 객체
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		// statement 쓰면 노출이 되는데 그거 방지한게 위에 있는 prepareStatement

		// rs
		ResultSet rs = null;
		// sql문
		String sql = "SELECT * FROM boardview LIMIT ?, 10";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (page - 1) * 10); // 수정해야해
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO e = new BoardDTO();
				e.setNo(rs.getInt(1));
				e.setTitle(rs.getString(2));
				e.setWrite(rs.getNString(3));
				e.setDate(rs.getNString(4));
				e.setCount(rs.getInt(5));
				e.setComment(rs.getInt(6));
				
				// 이름적을거면 이름 하고, 숫자(몇번째인지)는 숫자만
				list.add(e);
				// list에 더해줘
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
			// close 메소드를 만들어준거야. 안그러면 문장 길어지니까
		}
		return list;
	}

	public BoardDTO detail(int no) {
	      
	      BoardDTO dto = new BoardDTO();
	      
	      //Connection con = DBConnection.getInstance().getConnection();
	      Connection con = db.getConnection();
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql =  "SELECT b.board_no, b.board_title, b.board_content, m.mname as board_write, m.mid, b.board_ip, "
	                 + "b.board_date, (SELECT COUNT(*) FROM visitcount WHERE board_no = b.board_no) AS board_count "
	                 + "FROM board b JOIN member m ON b.mno=m.mno "
	                 + "WHERE b.board_no=?";
	      //view 를 안쓰면 이렇게 길게 써야한다
	         
	      try {
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, no);
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	            dto.setNo(rs.getInt("board_no"));
	            dto.setTitle(rs.getString("board_title"));
	            dto.setContent(rs.getString("board_content"));
	            dto.setWrite(rs.getString("board_write"));
	            dto.setDate(rs.getString("board_date"));
	            dto.setCount(rs.getInt("board_count"));
	            dto.setMid(rs.getString("mid"));
	            dto.setIp(rs.getString("board_ip"));
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      
	      return dto;
	   }

	public int write(BoardDTO dto) {
		int result = 0; // 초기값 0으로 잡은거야

		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO board (board_title, board_content, mno, board_ip) "
				+ "VALUES(?,?,(SELECT mno FROM member WHERE mid=?),?)"; //수정완

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getMid()); // 수정완
			pstmt.setString(4, dto.getIp()); // 아이피 추가한것
			result = pstmt.executeUpdate(); // 영향받은 행을 result에 저장합니다.
			// 여기서 result값이 0에서 바뀜
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}

		return result;
	}

	public int delete(BoardDTO dto) {
		int result = 0;
		// conn
		Connection conn = DBConnection.getInstance().getConnection();
		// pstmt
		PreparedStatement pstmt = null;
		// sql문
		String sql = "UPDATE board SET board_del='0' WHERE board_no=? AND mno=(SELECT mno FROM member WHERE mid=?)";
		// DELETE 다음은 바로 FROM이 와야함 무엇을 삭제할지는 WHERE절...
		// 조립

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			pstmt.setString(2, dto.getMid());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}

		return result;
	}

	public int update(BoardDTO dto) {
		int result = 0;
		Connection conn = DBConnection.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_title=?, board_content=?"
				+ "WHERE board_no=? AND mno=(SELECT mno FROM member WHERE mid=?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNo());
			pstmt.setString(4, dto.getMid());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
		return result;
	}

	public int totalCount() {

		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM boardview";
		int result = 0;

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}

		return result;

	}

	// 조회수 +1 시키는 메소드
	public void countUp(int no, String mid) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) FROM visitcount "
				+ "WHERE board_no=? AND mno=(SELECT mno FROM member WHERE mid=?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, mid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int result = rs.getInt(1);
				if (result == 0) {
					realCountUp(no, mid);
					// 이 게시물을 읽은적이 없다면? 0은 없는거임
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
	}

	// 로그인한 사람이 게시물을 읽은적이 없었을때 하는 메소드
	private void realCountUp(int no, String mid) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO visitcount (board_no, mno)" + "VALUES(?,(SELECT mno  FROM member WHERE mid=?))";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, mid);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}

	}

	public List<CommentDTO> commentList(int no) {
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM commentview WHERE board_no=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setCno(rs.getInt("cno"));
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setCcomment(rs.getString("ccomment"));
				dto.setCdate(rs.getString("cdate"));
				dto.setMno(rs.getInt("mno")); // 이제 뷰를 만들어서 mname, mid를 가져오게 해야한다
				dto.setMid(rs.getString("mid"));
				dto.setMname(rs.getString("mname"));
				dto.setClike(rs.getInt("clike"));
				dto.setCip(Util.ipMasking(rs.getString("cip")));
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}

		return list;

	}

}
