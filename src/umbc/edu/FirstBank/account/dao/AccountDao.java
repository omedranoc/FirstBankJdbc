package net.mv.FirstBank.account.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import net.mv.FirstBank.util.ConnUtil;

import net.mv.FirstBank.account.domain.Account;;
public class AccountDao {
private ConnUtil util = ConnUtil.getUtil();
	
	public void deposit(Account account, long userId){
		
		String query = "INSERT INTO ACCOUNT (AMOUNT) VALUES (?)";
		
		try (Connection conn = util.createConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);) {
			
				pstmt.setDouble(1,account.getAmount());
				
				pstmt.executeUpdate();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public int createcuenta(){
		
		int cuenta=0;
		String query = "INSERT INTO B_ACCOUNT (A_BALANCE) VALUES (0)";
		String query1 = "select A_ID from B_ACCOUNT  where A_ID=(select max(A_ID) from B_ACCOUNT)";
		try (Connection conn = util.createConnection();
				Statement pstmt = conn.createStatement();) {
							
				pstmt.executeUpdate(query);
				
				ResultSet rs=pstmt.executeQuery(query1);
				while(rs.next()){
				cuenta= rs.getInt("A_ID");
				System.out.println("cuenta"+ cuenta);
				}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return cuenta;
	}

   public void updateBalance(Account account,Double amount){
		
		String query = "UPDATE B_account set A_BALANCE=? where A_ID=?";
		
		try (Connection conn = util.createConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);) {
			
				pstmt.setDouble(1,amount);
				pstmt.setInt(2, account.getA_id());
				
				
				pstmt.executeUpdate();
				
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
   public void updateAccount(Account account){
		
		String query = "select * from  B_account  where A_ID=?";
		
		try (Connection conn = util.createConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);) {
			
				
				pstmt.setInt(1, account.getA_id());
				
				
				ResultSet rs= pstmt.executeQuery();
				while(rs.next()){
					
					account.setAmount(rs.getDouble("A_BALANCE"));
				
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
  
	
	public List<Account> withdraw(long userId){
		List<Account> cardList = new ArrayList<Account>();
		
		String query = "SELECT * FROM B_ACCOUNT WHERE A_ID = ?";
		
		try (Connection conn = util.createConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			
			pstmt.setLong(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Account account = new Account();
				account.setA_id(rs.getInt("A_ID"));
				account.setAmount(rs.getDouble("A_BALANCE"));
				cardList.add(account);
			} 
		}catch (SQLException e) {
				e.printStackTrace();
			}
		
		return cardList;
		}
		
		
	

}
