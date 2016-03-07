package net.mv.FirstBank.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import net.mv.FirstBank.account.Exception.accountoverdrawnexception;
import net.mv.FirstBank.account.domain.Account;
import net.mv.FirstBank.account.service.AccountService;
import net.mv.FirstBank.transaction.domain.Transaction;
import net.mv.FirstBank.transaction.service.TransactionService;
import net.mv.FirstBank.user.domain.BankUser;
import net.mv.FirstBank.user.service.UserService;

public class FisrtBankName {
  public static void main(String[] args) {

		
		//UserService us = new UserService();
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		/*
	      Account account= as.CreateCuenta();
	      as.deposit(account, 200);
	      as.deposit(account, 100);
	      as.whithdraw(account, 150);
	      Transaction t= new Transaction();
	      double a = 100;
	      t.setAmount(a);
	      t.setID_Account(23);
	      t.setTttype(true);
	      t.setId_user(1);
		  ts.createTransaction(t);
	      ArrayList<Transaction> transactions= ts.getTransactions(1);
	      if (transactions.isEmpty())
	      {
	    	  System.out.println("You have not made any withdraws yet");
	      }
	      for (Transaction transaction : transactions) {
	    	boolean typetransaction=transaction.isTttype();
	    	if (typetransaction==false)
	    	{
    	
	    	System.out.println("saul");
			System.out.println("withdraw receipt number: "+transaction.getId_receipt());
			System.out.println(" this amount was    witthdraw   on: "+transaction.getDateT());
	    	}
  }*/
	  UserService us = new UserService();
	
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("*****");
		System.out.println("Welcome to my bank");
		System.out.println("*****");
		
		while (true) {
			System.out.println("Please select an option number: ");
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Exit");
			
			String option = scan.nextLine();
			
			if (option.contains("1")){
				
				System.out.println("Welcome to user login ...");
				System.out.println("Please enter a username: ");
				String username = scan.nextLine();
	
				System.out.println("Please enter a password:...");
				String password = scan.nextLine();
				
				BankUser user = us.authenticateUser(username, password);
				if (user != null && user.isAuthenticated()){
					System.out.println();
					System.out.println("Welcome, " + user.getUsername() );
					Account account= new Account();
					account.setA_id(user.getAccountid());
					while (true) {
						System.out.println("******");
						System.out.println("Please select an option: ");
						System.out.println("1. Deposit.");
						System.out.println("2. withdraw.");
						System.out.println("3. Balance.");
						System.out.println("4. Exit");
						
						option = scan.nextLine();
						
						if (option.contains("1")) {
							System.out.println("Please enter te amount you want to deposit: ");
							    Double scanamount=scan.nextDouble();					    
							    
							    as.deposit(account, scanamount);
							    ts.createTransaction(scanamount,user.getAccountid(),user.getId(),false);
						        
						} else if (option.contains("2")) {
							System.out.println("Please enter te amount you want to withdraw: ");
							Double scanamount=scan.nextDouble();
							try {
								 as.whithdraw(account,  scanamount);
								 ts.createTransaction(scanamount,user.getAccountid(),user.getId(),true);
								
							} catch (accountoverdrawnexception e) {
								System.out.println("you don't have enough money");
								
							}
						   						    
						     //TRUE MEANS WITHDRAW
						   
							System.out.println("*****");
						    while (true) {
								
								System.out.println("Please select an option: ");
								System.out.println(" ");
								System.out.println("1. View your withdraw's history.");
								System.out.println("2. Go back to the main menu.");
								
								option = scan.nextLine();
								if (option.contains("1")) {
									 ArrayList<Transaction> transactions= ts.getTransactions(user.getId());
								      if (transactions.isEmpty())
								      {
								    	  System.out.println("You have not made any withdraws yet");
								      }
								      for (Transaction transaction : transactions) {
								    	boolean typetransaction=transaction.isTttype();
								    	if (typetransaction==true)
								    	{
							    	
								    	System.out.println("Receipt");
										System.out.println("withdraw receipt number: "+transaction.getId_receipt());
										System.out.println("The amount: "+transaction.getAmount()+ " was    withdraw   on: "+transaction.getDateT());
								    	}
								      }
								}
								else if (option.contains("2"))
								{
									break;
								}
								}
						} else if (option.contains("3")) {
							as.getbalance(account);
							
						} 
						else if (option.contains("4")) {
							break;
						} 
						
						else {
							System.out.println("Please select a valid option  ...1");
						}
						
						
					}
				} else
				{
					System.out.println("Invalid credentials.");
				}
				
			} else if (option.contains("2")) {
				
				System.out.println("Welcome to user registration . ..");
				System.out.println();
				System.out.println("Please enter a username: ");
				String username = scan.nextLine();
				
				System.out.println("Please enter a password: ");
				String password = scan.nextLine();
				Account account= as.CreateCuenta();
				boolean exists = us.registerUser(username, password,account.getA_id());
				
				if (exists) {
					System.out.println("That user already exists!!!");
					
				} else{
					
					
					System.out.println("Sucess! Noww you may log in . . .");
					
				}
				
			} else if (option.contains("3")) {
				
			} else {
				System.out.println("Please select a valid option ...");
			}
		}	   
		
		
		
}
}
