package com.shop.model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shop.model.entity.User;
import com.shop.util.JDBCUtil;

public class UserDAOJDBC {
	private static final String INSERT_STMT = "INSERT INTO mem(usermail, username, userpsw) VALUES (?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE mem SET usermail = ?, username = ?, userpsw =? WHERE userno =?";
	private static final String DELETE_STMT = "DELETE FROM mem WHERE userno = ?";
	private static final String FIND_BY_PK = "SELECT * FROM mem WHERE userno = ?";
	private static final String FIND_BY_MAIL = "SELECT * FROM mem WHERE usermail = ?";
	private static final String GET_ALL = "SELECT * FROM mem";

	static {
		try {
			Class.forName(JDBCUtil.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	public int add(User user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int primaryKey = 0;

		try {
			//回傳PK for UserAction 放入 session
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, user.getUserMail());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getUserPsw());

			int affectedRows = pstmt.executeUpdate();
		
			if (affectedRows > 0) {
		
				ResultSet generatedKeys = pstmt.getGeneratedKeys();
	
				if (generatedKeys.next()) {
					primaryKey = generatedKeys.getInt(1); 
				}
			}
			return primaryKey;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			closeResources(con, pstmt, null);
		}
	}

	public int update(User user) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, user.getUserMail());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getUserPsw());
			pstmt.setInt(4, user.getUserNo());

			return pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
		return -1;
	}

	public int delete(Integer userNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, userNo);

			return pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
		return -1;
	}

	public User findByPk(Integer userNo) {
		User user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setInt(1, userNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setUserNo(rs.getInt("userno"));
				user.setUserMail(rs.getString("userMail"));
				user.setUserName(rs.getString("userName"));
				user.setUserPsw(rs.getString("userPsw"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs);
		}
		return user;
	}

	public User findByMail(String userMail) {
		User user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MAIL);

			pstmt.setString(1, userMail);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setUserNo(rs.getInt("userno"));
				user.setUserMail(rs.getString("userMail"));
				user.setUserName(rs.getString("userName"));
				user.setUserPsw(rs.getString("userPsw"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs);
		}
		return user;
	}

	public List<User> getAll() {
		List<User> list = new ArrayList<User>();
		User user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setUserNo(rs.getInt("userno"));
				user.setUserMail(rs.getString("userMail"));
				user.setUserName(rs.getString("userName"));
				user.setUserPsw(rs.getString("userPsw"));
				list.add(user);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs);
		}
		return list;
	}

	private static void closeResources(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
}
