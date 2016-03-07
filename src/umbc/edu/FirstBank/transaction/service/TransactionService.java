package net.mv.FirstBank.transaction.service;


import java.util.ArrayList;
import java.util.List;

import net.mv.FirstBank.account.dao.AccountDao;
import net.mv.FirstBank.account.domain.Account;
import net.mv.FirstBank.transaction.dao.TransactionDao;
import net.mv.FirstBank.transaction.domain.Transaction;


public class TransactionService {
	
private TransactionDao td = new TransactionDao();
	
	public ArrayList<Transaction> getTransactions(int c_id){
		
		
		ArrayList<Transaction>transactions= td.getWithdraws(c_id);
	  
		
		System.out.println("transactions");
        return transactions;
	
		
		
	}

    public void createTransaction (double amount,int id_account,int id_user, Boolean ttype)
    {
    	Transaction t= new Transaction();
	      
	      t.setAmount(amount);
	      t.setID_Account(id_account);
	      t.setTttype(ttype);
	      t.setId_user(id_user);
    	 td.insertTransaction(t);
    }
}