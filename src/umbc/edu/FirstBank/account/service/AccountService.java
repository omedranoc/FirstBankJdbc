package net.mv.FirstBank.account.service;
import net.mv.FirstBank.account.Exception.accountoverdrawnexception;
import java.util.List;

import net.mv.FirstBank.account.dao.AccountDao;
import net.mv.FirstBank.account.domain.Account;


public class AccountService {
	
private AccountDao fcd = new AccountDao();
	
	public Account CreateCuenta(){
		
	
		
		Account account = new Account();
		account.setAmount(0);
		int NumberAccount=fcd.createcuenta();
		System.out.println("the account was created");
		account.setA_id(NumberAccount);
		return account;
	
		
		
	}
public void deposit(Account account,double amount){
	    fcd.updateAccount(account);
	    double balance = account.getAmount();
	    
	    balance=balance+amount;
		fcd.updateBalance(account,balance);	
		fcd.updateAccount(account);
		System.out.println("You deposited : "+amount+" your balance is: "+balance);
		
	
		
		
	}
public void whithdraw(Account account,double amount)throws accountoverdrawnexception {
	fcd.updateAccount(account);
    double balance = account.getAmount();
    if(amount>balance)
    {
    	throw new accountoverdrawnexception("count overdrawn");
    }
    else{
    balance=balance-amount;
	fcd.updateBalance(account,balance);	
	fcd.updateAccount(account);
	
	System.out.println("This amount was succesfully withdraw: "+amount+" your balance is: "+balance);
	
    }
	
	
}


	
	public void getbalance(Account account){
		fcd.updateAccount(account);
		System.out.println("your balance is: "+ account.getAmount());
	}


}
