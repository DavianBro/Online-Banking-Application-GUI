package onlineBankingApplication;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*; 

public class ATMUser extends Account {
	
private String firstName; // Users First Name

private String lastName; // Users Last Name

private  String Username; // Username for loging in for verification

private String PersonalID; // User's ID Number 

private String pincode; // User Pin Code

private String UserEmailAddress; // User's Email Address

private String UserHomeAddress; // user's Home address

private String UserPhoneNumber; // User Number

private double initialdeposit; // changed to public set back to private

public ATMUser() {
	// Default
}

public ATMUser(String firstName, String lastName, String username, String personalID, String pincode,
		String userEmailAddress, String userHomeAddress, String userPhoneNumber, double initialdeposit) {
	super();
	this.firstName = firstName;
	
	this.lastName = lastName;
	Username = username;
	PersonalID = personalID;
	this.pincode = pincode;
	UserEmailAddress = userEmailAddress;
	UserHomeAddress = userHomeAddress;
	UserPhoneNumber = userPhoneNumber;
	this.initialdeposit = initialdeposit;
}


public  String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getUsername() {
	return Username;
}

public void setUsername(String username) {
	Username = username;
}

public String getPersonalID() {
	return PersonalID;
}

public void setPersonalID(String personalID) {
	PersonalID = personalID;
}

public String getPincode() {
	return pincode;
}

public void setPincode(String pincode) {
	this.pincode = pincode;
}

public String getUserEmailAddress() {
	return UserEmailAddress;
}

public void setUserEmailAddress(String userEmailAddress) {
	UserEmailAddress = userEmailAddress;
}

public String getUserHomeAddress() {
	return UserHomeAddress;
}

public void setUserHomeAddress(String userHomeAddress) {
	UserHomeAddress = userHomeAddress;
}

public String getUserPhoneNumber() {
	return UserPhoneNumber;
}

public void setUserPhoneNumber(String userPhoneNumber) {
	UserPhoneNumber = userPhoneNumber;
}

public double getInitialdeposit() {
	return initialdeposit;
}

public void setInitialdeposit(double initialdeposit) {
	this.initialdeposit = initialdeposit;
}


public double getcheckingsAccountBalance() {
	return super.getUserAccountcheckingsAccount(initialdeposit);
}

public double getsavingsAccountBalance() {
	return super.getUserAccountSavingsAccount(initialdeposit); 
}


}
















	


