package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.poseidon.dto.CommentDTO;

public class CommentDAO extends AbstractDAO {

	public int commentWrite(CommentDTO dto) { //select가 아니면 다 int라고 생각해 ( 이게 맞다면, 0이나 1로 출력할거니까)
		
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "INSERT INTO comment (ccomment, board_no, mno, cip) "
				+ "VALUES (?, ?, (SELECT mno FROM member WHERE mid=?), ?)";
//		id 같은거만 출력되도록 한거야
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getCcomment());
			pstmt.setInt(2, dto.getBoard_no());
			pstmt.setString(3, dto.getMid());
			pstmt.setString(4, dto.getCip());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
			
		}
		
		return result;
	}

	public int commentDel(CommentDTO dto) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE comment SET cdel='0' "
				+ "WHERE cno=? AND mno=(SELECT mno FROM member WHERE mid=?)";
		int result = 0;
		
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getCno());
			pstmt.setString(2, dto.getMid());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
		return result;
	}

}
