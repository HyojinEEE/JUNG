package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poseidon.db.DBConnection;

// 부모 DAO(상속) = DBConn, close만 가지고있어
// 이걸로 하나 만들어서 상속해서 쓰는거
public class AbstractDAO {
	DBConnection db = DBConnection.getInstance();
	
	void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
	// 얘는 접근제한자 DEFAULT 값
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
}
