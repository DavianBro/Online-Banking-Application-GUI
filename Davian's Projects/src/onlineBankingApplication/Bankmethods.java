package onlineBankingApplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import java.util.Locale;
import javax.swing.*;
import javax.swing.UIManager;


public class Bankmethods{
	
	public static String [] options = { "Savings ", "Checkings" };
	public static JLabel Error = new JLabel(" Error! Insufficient Funds"); 
	
	public static String Formatter(double Account) { // This Method formats the Output 
		
		DecimalFormat Formatters = new DecimalFormat("$ #,###.00"); // This Formats Output  w/ Formula
		Formatters.format(Account); // Formats input 
		return Formatters.format(Account);
	}

								           // Frame 	// UserScreenMenu  // CheckBalance Screen
	public static void returnToMainPage (JFrame frames,JPanel Addin, JPanel Panels, int a, int b, int c, int d) {
		// Method Returns User To The User Options Screen
		JButton GoBack = new JButton (" < Go Back "); // Creates Button
		GoBack.setForeground(Color.BLUE);
		GoBack.setBounds( a,  b,  c, d);
		GoBack.setFont(new Font("Open Sans", Font.BOLD,15));
		Panels.add(GoBack);     // Adds Button
		
		GoBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { //clears screen and brings User to UserOptionScreen
				// TODO Auto-generated method stub
				ATMGUI.clearScreen(Addin);
				frames.setSize(900,500);
				ATMGUI.bankLogoMethod(Addin,200,20,500,45);
			}
			});
	}
	
	
	

public static void successfulTransaction(JFrame frames) {

	JLabel Success = new JLabel("SUCCESSFUL TRANSACTION!"); // This is the message that Pops up When User Signs Out 
	Success.setFont(new Font("Open Sans", Font.ITALIC|Font.BOLD, 14));
	Success.setForeground(Color.BLUE);

JOptionPane.showMessageDialog(frames, Success
		, "One America Bank Online Banking",JOptionPane.DEFAULT_OPTION);


	}


public static void transferReceipt(JFrame frames,  JPanel Option, String Name, String Bank, String RoutingNumber, double Amount, String Account) {
	
	JPanel Receipt = new JPanel();
	ATMGUI.clearScreen(Receipt);
	frames.setSize(500,330); // 340
	JLabel SuccessfulTrans = new JLabel("<html>ONE AMERICA BANK SUCCESSFUL TRANSFER RECEIPT<br/> -----------------------------------------------"
			+ " </html>", SwingConstants.CENTER);

	SuccessfulTrans.setFont(new Font(" monaco ", Font.ITALIC,15));
	SuccessfulTrans.setBounds(10,5,500,45);
	Receipt.add(SuccessfulTrans);


	JLabel Username = new JLabel();
	Username.setFont(new Font(" monaco ", Font.ITALIC,14));
	Username.setBounds(5,40,300,25); 
	Username.setText("RECIPIENT NAME:    "  + Name.toUpperCase() );
	Receipt.add(Username); 
	
	JLabel Userbank = new JLabel();
	Userbank.setFont(new Font(" monaco ", Font.ITALIC,14));
	Userbank.setBounds(5,65,300,25); 
	Userbank.setText("RECIPIENT BANK:    "  + Bank.toUpperCase() );
	Receipt.add(Userbank); 

	JLabel  UserRoutingNumber = new JLabel();
	UserRoutingNumber.setFont(new Font(" monaco ", Font.ITALIC,14));
	UserRoutingNumber.setBounds(5,90,800,25); 
	UserRoutingNumber.setText("ROUTING NUMBER:    "  + RoutingNumber );
	Receipt.add(UserRoutingNumber); 
	
	JLabel  UserAmount = new JLabel();
	UserAmount.setFont(new Font(" monaco ", Font.ITALIC,14));
	UserAmount.setBounds(5,115,800,25); 
	UserAmount.setText("TRANSFER AMOUNT:    "  + Formatter(Amount) );
	Receipt.add(UserAmount); 
	
	
	JLabel  UserAccount = new JLabel();
	UserAccount.setFont(new Font(" monaco ", Font.ITALIC,14));
	UserAccount.setBounds(5,140,800,25); 
	UserAccount.setText("   ACCOUNT:    " + Account.toUpperCase());
	Receipt.add(UserAccount); 
	
	
	JLabel  UserTimeStamp = new JLabel();  // DATE 
	 UserTimeStamp.setFont(new Font(" monaco ", Font.ITALIC,14));
	 UserTimeStamp.setBounds(125,165,800,25); 
	 UserTimeStamp.setText("" + ATMGUI.Time);
	 Receipt.add( UserTimeStamp); 
	
	 returnToMainPage(ATMGUI.frame,Option, Receipt, 5, 250 ,145, 45);

	
}
		
	
	}


	


