package net.mv.FirstBank.transaction.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.mv.FirstBank.util.ConnUtil;
import net.mv.FirstBank.account.domain.Account;
import net.mv.FirstBank.transaction.domain.Transaction;

public class TransactionDao {
	private ConnUtil util = ConnUtil.getUtil();
	
	public ArrayList<Transaction> getWithdraws(int User_id){
		ArrayList<Transaction>transactionList = new ArrayList<Transaction>();
		
		String query = "SELECT * FROM B_TRANSACTION WHERE U_ID=? ORDER BY T_DATE";
		
		try (Connection conn = util.createConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			
			pstmt.setInt(1, User_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Transaction transaction = new Transaction();
				
				transaction.setDateT(rs.getString("T_DATE"));
				transaction.setAmount(rs.getDouble("T_AMOUNT"));
				transaction.setId_user(rs.getInt("U_ID"));
				transaction.setID_Account(rs.getInt("A_ID"));
				transaction.setTttype(rs.getBoolean("TTYPE"));
				transaction.setId_receipt(rs.getInt("T_ID"));
				transactionList.add(transaction);
			} 
		}catch (SQLException e) {
				e.printStackTrace();
			}
		
		return transactionList;
		}

	public void insertTransaction(Transaction transaction){
		
		String query = "INSERT INTO B_TRANSACTION (T_DATE,T_AMOUNT,U_ID,A_ID,TTYPE) VALUES (to_char( sysdate, 'DD/MM/YY HH24:MI:SS' ),?,?,?,?)";
		
		try (Connection conn = util.createConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);) {
			
				pstmt.setDouble(1,transaction.getAmount());
			    pstmt.setInt(2,transaction.getId_user());
			    pstmt.setInt(3,transaction.getID_Account());
			    pstmt.setBoolean(4,transaction.isTttype());
				
				pstmt.executeUpdate();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
}
