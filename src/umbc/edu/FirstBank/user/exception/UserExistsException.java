package net.mv.FirstBank.user.exception;

public class UserExistsException extends Exception{
	
	public UserExistsException (String message, Throwable t){
		super(message, t);
	}
	
	

}
