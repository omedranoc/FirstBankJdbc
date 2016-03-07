package net.mv.FirstBank.user.service;

import org.omg.CORBA.INTERNAL;

import net.mv.FirstBank.account.domain.Account;
import net.mv.FirstBank.account.service.AccountService;
import net.mv.FirstBank.user.dao.UserDao;
import net.mv.FirstBank.user.domain.BankUser;
import net.mv.FirstBank.user.exception.UserExistsException;

public class UserService {
		private UserDao userDao = new UserDao();
		
		public boolean registerUser(String username, String password,int C_id) {
			BankUser user = new BankUser();
			
			user.setUsername(username);
			user.setPassword(password);
			user.setAccountid(C_id);
			
			AccountService accounts= new AccountService();
			Account account=accounts.CreateCuenta();
			user.setAccountid(account.getA_id());
			
			boolean exists = false;
			
			try {
				userDao.insertUser(user);
			} catch (UserExistsException e) {
				e.printStackTrace();
				exists= true;
			}
			
			return exists;
		}
		
		public BankUser authenticateUser(String username, String password) {
			BankUser user = userDao.retriveUser(username);
			
			if (user != null && user.getPassword().equals(password)){
				user.setAuthenticated(true);
			} else {
				user = new BankUser();
				user.setAuthenticated(false);
			}
			
			return user;
		}
}
