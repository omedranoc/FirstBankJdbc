package net.mv.FirstBank.transaction.domain;



import javax.print.DocFlavor.STRING;

public class Transaction {
	private int Id_receipt;
	private int ID_Account;
	private String dateT;

	private Double amount;
	private int Id_user;
	private boolean tttype;
	
	public String getDateT() {
		return dateT;
	}
	public void setDateT(String string) {
		this.dateT = string;
	}
	public boolean isTttype() {
		return tttype;
	}
	public void setTttype(boolean tttype) {
		this.tttype = tttype;
	}
	public int getId_receipt() {
		return Id_receipt;
	}
	public void setId_receipt(int id_receipt) {
		Id_receipt = id_receipt;
	}
	public int getID_Account() {
		return ID_Account;
	}
	public void setID_Account(int iD_Account) {
		ID_Account = iD_Account;
	}
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public int getId_user() {
		return Id_user;
	}
	public void setId_user(int id_user) {
		Id_user = id_user;
	}
	

}
