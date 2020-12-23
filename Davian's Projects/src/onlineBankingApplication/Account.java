package onlineBankingApplication;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class Account extends Transaction {

	String AccountUsername;

	private static double checkingsAccount;
	
	private double savingsAccount;
	
	public ATMUser Holder;
	
	public static NumberFormat myFormat = NumberFormat.getInstance();
	

	Account(){
		//Default Constructor
	}

	public Account(String accountUsername, double checkingsAccount, double savingsAccount) {
		super();
		AccountUsername = accountUsername;
		this.checkingsAccount = checkingsAccount;
		this.savingsAccount = savingsAccount;
	}


public double getUserAccountcheckingsAccount(double balance) {
double Checkings;
Checkings = .8 * balance ;
	return Checkings;
}

public double getUserAccountSavingsAccount(double balance) {
double Savings;
Savings = .2 * balance ;
	return Savings;
}



	
}
	
