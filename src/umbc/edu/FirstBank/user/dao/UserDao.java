package net.mv.FirstBank.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.mv.FirstBank.util.ConnUtil;
import net.mv.FirstBank.user.domain.BankUser;

import net.mv.FirstBank.user.exception.UserExistsException;

public class UserDao {
	
	private ConnUtil util = ConnUtil.getUtil();
	
	public void insertUser(BankUser user) throws UserExistsException{
		
		String query = "INSERT INTO B_USER (U_NAME,U_PASSWORD,A_ID) VALUES(?,?,?)";
		
		try (Connection conn = util.createConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);){
			
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2,user.getPassword());
			pstmt.setInt(3,user.getAccountid());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			throw new UserExistsException("The user already exists.", e);
			// Add custom exception
		} 
	}
	public BankUser retriveUser(String username) {
		String query = "SELECT * FROM B_USER WHERE U_NAME = ?";
		BankUser bUser = null;
		try (Connection conn = util.createConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);)
				{
			pstmt.setString(1,username);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bUser = new BankUser();
				//fUser.setId(rs.getLong("F_ID"));
				bUser.setUsername(rs.getString("U_NAME"));
				bUser.setPassword(rs.getString("U_PASSWORD"));
				bUser.setAccountid(rs.getInt("A_ID"));
				bUser.setId(rs.getInt("U_ID"));
				
			}
			
			
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
		return bUser;
	}
}
