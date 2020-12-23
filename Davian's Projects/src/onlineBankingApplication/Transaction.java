package onlineBankingApplication;

import java.util.ArrayList;
import java.util.Date;

public class Transaction {
	
	private double amount;
	
	private Date timestamp;

	private String TransactionType;
	
	private String Accounttype;
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Transaction(double amount, Date timestamp, String transactionType, String accounttype) {
		super();
		this.amount = amount;
		this.timestamp = timestamp;
		TransactionType = transactionType;
		Accounttype = accounttype;
	}

	


	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}



	public Date getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}



	public String getTransactionType() {
		return TransactionType;
	}



	public void setTransactionType(String transactionType) {
		TransactionType = transactionType;
	}



	public String getAccounttype() {
		return Accounttype;
	}



	public void setAccounttype(String accounttype) {
		Accounttype = accounttype;
	}



	@Override
	public String toString() {
	return (String.format("%-15s%-10s%15s%25s", Bankmethods.Formatter(amount), timestamp, TransactionType,  Accounttype ));
			
			//"Transaction [amount=" + amount + ", timestamp=" + timestamp + ", TransactionType=" + TransactionType
			//	+ ", Accounttype=" + Accounttype + "]";
		
		// Format 
	}



	
	// To String 


	
	
	
	// There is an Account that owns this transaction
	
	// Use this class to print out a receipt once user logs out
	// Use this class to record Transactions 

	
	
	
}