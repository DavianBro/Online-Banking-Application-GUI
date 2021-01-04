package onlineBankingApplication;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;


public class ATMGUI implements ActionListener {
	private static ArrayList<ATMUser> Userarraylist = new ArrayList<ATMUser>(); 
	public static JFrame frame = new JFrame();
	public static JPanel panel = new JPanel();
	public static JLabel BanklogoONE = new JLabel("ONE AMERICA BANK");
	public static Date Time = new java.util.Date();
	public static double UpdatedCheckingBalance;
	public static double UpdatedSavingsBalance;
	public static JLabel Error = new JLabel(" Error! Insufficient Funds"); 
    static JLabel LoginError = new JLabel("  **Invalid Username or Password**  "); 
    static JLabel WrongInfo = new JLabel();
   public static JButton LoginButton = new JButton(" Login ");
  public static JButton NoAccountButton = new JButton("No Account? Create One Here");
	public static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	public static void main(String[]args) {
	Userarraylist.add(new ATMUser("Admin", "Account", "OABSystemAdmin", "4356789", "5467", "OneAmericaBankAdmin@OAB.com",
			"12 One America Bank Way, Orlano, Florida 32789", "1-800-800-9000", 100000.00)); 
	// Adds Admin User Account or Create another Class with this Method
		GUI();
		
	}
	
	public static void bankLogoMethod(JPanel PanelMethod, int a, int b,int c, int d ) { // Sets Bank Logo Header 
		BanklogoONE.setFont(new Font("CALABRI",Font.ITALIC,45)); // Sets Bank Logo On Top 
        BanklogoONE.setForeground(Color.BLUE);
        BanklogoONE.setBounds(a,b,c,d); 
      PanelMethod.add(BanklogoONE);

	}
	
	public static void clearScreen(JPanel PanelMethod) { // This method clears the screen
		frame.getContentPane().removeAll();
		frame.getContentPane().add((PanelMethod));
        frame.revalidate();
        PanelMethod.setLayout(null); // Very Important or else it wont move
        PanelMethod.setBackground(Color.WHITE);
	}
	
	public static void GUI() {
		
	frame.setSize(430,320);// original 500/400
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setTitle("ONE AMERICA BANK ONLINE BANKING"); 
	frame.setVisible(true);
	frame.setResizable(false);
	panel.setBackground(Color.white);
	 // This Created Panel for GUI
	frame.add(panel);
	panel.setLayout(null);
	

	BanklogoONE.setFont(new Font("CALABRI",Font.ITALIC,37)); // CALABRI
	 BanklogoONE.setForeground(Color.BLUE);
	BanklogoONE.setBounds(10,20,500,45); 
	panel.add(BanklogoONE);
	
	JLabel label = new JLabel("Username ");
	label.setBounds(10,70,80,25); 
	panel.add(label);
	
	JTextField userText = new JTextField(20);
	userText.setBounds(100,70,165, 25);
	userText.setBorder(new LineBorder(Color.black,1));
	panel.add(userText);
	
	JLabel passwordlabel = new JLabel("Password ");
	passwordlabel.setBounds(10,100,80,25); 
	panel.add(passwordlabel);
	
	JPasswordField  passwordText = new JPasswordField();
	passwordText.setBorder(new LineBorder(Color.black,1));
	passwordText.setBounds(100,100,165,25);
	panel.add(passwordText);
	
	// Login Button
	LoginButton.setBounds(140,150,100, 25);
	panel.add(LoginButton);
	
	LoginButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
			String LoginUsername = userText.getText(); // Login Username to text
			 String LoginPassword = passwordText.getText(); // Login Password to text
		
			 boolean Elementfound = false;
			 int x = 0; // variable has to be initialized 
			 for(int i = 0; i<Userarraylist.size(); i++) {
				 if(Userarraylist.get(i).getUsername().equals(LoginUsername) && Userarraylist.get(i).getPincode().equals(LoginPassword)) {
					 Elementfound = true;
					 x = i;
					 // Set userarraylist.get(i) to a certain variable so you can use that user bank balance and etc 
				 }
				
			 }
			 
			 if(Elementfound) { // If User is Found 
				 JPanel UserOptionsScreen = new JPanel(); 
				 UserOptionsScreen.revalidate();
				 UserOptionsScreen.repaint();
				 frame.repaint();
				 clearScreen(UserOptionsScreen); // This Method allows for a clear screen transition to Options Menu 
				 frame.setSize(900,500); // Set's Size of Option Menu Screen
				 LoginError.setText("");
				 transactions.clear();
				 final int z = x; // this allows to use in other methods, declared it final so it wont change possibly change back 
				 
				 UpdatedCheckingBalance = Userarraylist.get(x).getcheckingsAccountBalance(); // Sets User Object Balance to Checking
				 UpdatedSavingsBalance = Userarraylist.get(x).getsavingsAccountBalance(); // Sets User Object Balance to Saving
	          
		        bankLogoMethod(UserOptionsScreen,200,20,500,45); // Adds Bank Logo on top
		        
				JLabel dateFormat = new JLabel(); // This Label Puts the Time at the bottom of the code on Options Menu
				dateFormat.setText( Userarraylist.get(x).getFirstName() + "'s " + " Last Login: " + Time);// This is printing same name 
				dateFormat.setBounds(15,420,500,45);  
				UserOptionsScreen.add(dateFormat);
			
		        JLabel WelcomeBack = new JLabel(); // Welcomes User to Front Screen 
		        WelcomeBack.setText("WELCOME BACK, " + Userarraylist.get(x).getFirstName()+"!");
		        WelcomeBack.setFont(new Font("Playfair ", Font.ITALIC,23));
		        WelcomeBack.setForeground(Color.RED);
		        WelcomeBack.setBounds(300,50,500,45);
		        UserOptionsScreen.add(WelcomeBack);
		        
		        // Get User Personal ID At Bottom 
		        JLabel UserID = new JLabel(); // This Label Puts the USERID at the bottom of the code on Options Menu
		        UserID.setText(" USER ID: #" + Userarraylist.get(x).getPersonalID());
		        UserID.setBounds(700,420,500,45);  
		        UserID.setFont(new Font( "null", Font.ITALIC,15));
				UserOptionsScreen.add(UserID);
		        // Change Up This Whole Thing
		        
			JButton Withdraw = new JButton(" Withdraw "); // WithDraw Method
			Withdraw.setBounds(45,130,240, 25);
			Withdraw.setForeground(Color.BLUE); 
			Withdraw.setFont(new Font("Open Sans", Font.BOLD,13));
			UserOptionsScreen.add(Withdraw);
			Withdraw.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					// Method is the Screen User Sees to Withdraw and enter in input
					JPanel WithdrawScreen = new JPanel(); 
					clearScreen(WithdrawScreen);
					frame.setSize(550,280);
					bankLogoMethod(WithdrawScreen, 50,20,500,45);
			
					JLabel UserAccountNumber = new JLabel(); // puts account# on screen 
					UserAccountNumber.setText(" Account #" + Userarraylist.get(z).getPersonalID()); // Displays Account Number
					Font font = new Font("Verdana", Font.ITALIC|Font.BOLD, 12);
					UserAccountNumber.setFont(font);
					UserAccountNumber.setBounds(335,200,500,45); // Changed from 375
					WithdrawScreen.add(UserAccountNumber);
					
					JLabel WithDrawAmount = new JLabel();
					WithDrawAmount.setText(" WITHDRAW AMOUNT: $"); // Label For How Much to be withdraw 
					WithDrawAmount.setBounds(90,90,300,30);
					WithDrawAmount.setForeground(Color.RED);
					WithdrawScreen.add(WithDrawAmount);

					
					JTextField WithDrawinput = new JTextField(); // Takes in User input 
					WithDrawinput.setBorder(new LineBorder(Color.black,1));
					WithDrawinput.setBounds(265,92,165,25);
					WithdrawScreen.add(WithDrawinput);
					
					Bankmethods.returnToMainPage(frame,UserOptionsScreen,WithdrawScreen,40,200, 145, 45); // Puts Back Button Screen 
					JButton WithdrawButton = new JButton();
					WithdrawButton.setText(" Withdraw Funds");
					WithdrawButton.setFont(new Font("Open Sans", Font.BOLD,13));
					WithdrawButton.setForeground(Color.BLUE);
					WithdrawButton.setBounds(265, 130, 145, 45); // Probably center more 
					WithdrawScreen.add(WithdrawButton);
					WithdrawButton.addActionListener(new ActionListener() { // When User Clicks Withdraw from Amount this code will run

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//double WithdrawAmount = 0;
				double WithdrawAmount = 0; // sets Withdraw Amount 
				boolean WithCase = true; // Boolean statement for Exception if user enter something other than a number 
				UIManager.put("OptionPane.background", Color.WHITE); 
				UIManager.getLookAndFeelDefaults().put("Panel.background", Color.WHITE);			
							
					try { // Exception in case User enters $ or Alphabet
					 WithdrawAmount =  Double.parseDouble(WithDrawinput.getText());
				
					}catch(NumberFormatException e1) {
					WithCase = false;
								
						}
							
					if (WithCase) { // If no errors in the Withdrawal amount, this will run 

					int display = JOptionPane.showOptionDialog(frame, " CHOOSE ACCOUNT: "," One America Bank", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, Bankmethods.options, null);
					 if( display == 1) {// If Display  == Checkings 
							if (WithdrawAmount<= UpdatedCheckingBalance && WithdrawAmount != 0) {	// Check to see if Balance is actually there
										
						int selection = JOptionPane.showOptionDialog(frame, " CONFIRM WITHDRAWAL FOR " + Bankmethods.Formatter(WithdrawAmount)," One America Bank" , 
							JOptionPane.OK_CANCEL_OPTION,  JOptionPane.DEFAULT_OPTION, null, null, null); // Confirm Message 
									
							if (selection == 0) { // If selection is " Checkings "
								UpdatedCheckingBalance = UpdatedCheckingBalance - WithdrawAmount;
							 Bankmethods.successfulTransaction(frame);
							transactions.add(new Transaction(WithdrawAmount, Time, " Withdrawal", " Checkings")); // consider changing how it's Added 
						
							 WithDrawinput.setText(""); // Sets Textfield to EMPTY This could stop array from getting input !!!! MAY HAVE TO DO IT OUT OF IF
							}
							 }
						 else if (WithdrawAmount > UpdatedCheckingBalance || WithdrawAmount == 0) { // THis code will run if User has insufficient funds or entered 0
									Error.setForeground(Color.RED); // Declared Static Above
									Error.setFont(new Font("Open Sans", Font.ITALIC, 14));
							JOptionPane.showMessageDialog(frame, Error, " One America Bank ", JOptionPane.DEFAULT_OPTION); // Sets Pop Up Screen
		
								 }
						}
					 if (display == 0) { // For Savings
						 if (WithdrawAmount<= UpdatedSavingsBalance && WithdrawAmount != 0) {
							int selection2 = JOptionPane.showOptionDialog(frame, " Confirm Withdrawal For " + Bankmethods.Formatter(WithdrawAmount)," One America Bank" , 
										JOptionPane.OK_CANCEL_OPTION,  JOptionPane.DEFAULT_OPTION, null, null, null); // Confirm Message 
							if (selection2 == 0) { 
								UpdatedSavingsBalance = UpdatedSavingsBalance - WithdrawAmount;
								 WithDrawinput.setText("");
								 Bankmethods.successfulTransaction(frame);
								 transactions.add(new Transaction(WithdrawAmount, Time, " Withdrawal", " Savings"));
								
							}

						 }
						 else if (WithdrawAmount > UpdatedSavingsBalance || WithdrawAmount == 0) { // If User Withdraws from Savings 
						
						 Error.setForeground(Color.RED);
						Error.setFont(new Font("Open Sans", Font.ITALIC, 14));
						JOptionPane.showMessageDialog(frame, Error, " One America Bank ", JOptionPane.DEFAULT_OPTION);
	
							 
						 }

					 }
					}
					else { // Displays if User enters something else in Dialog, this is from try & Catch 
						
						JLabel NoSign = new JLabel("ERROR! ENTER ONLY DIGITS-NO LETTERS OR CHARACTERS");
						UIManager.put("OptionPane.background", Color.WHITE); 
						UIManager.getLookAndFeelDefaults().put("Panel.background", Color.WHITE);
						NoSign.setFont(new Font("Open Sans", Font.BOLD, 14));
						NoSign.setForeground(Color.RED);
						JOptionPane.showMessageDialog(frame, NoSign
								, "One America Bank Online Banking",JOptionPane.DEFAULT_OPTION);
					}
					
						}
			
					}

				);

				}
				
			});

			JButton Deposit = new JButton(" Deposit ");
			Deposit.setBounds(335,130,240, 25);  
			Deposit.setForeground(Color.BLUE); 
			Deposit.setFont(new Font("Open Sans", Font.BOLD,13));
			UserOptionsScreen.add(Deposit);
			Deposit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JPanel DepositScreen = new JPanel(); 
					clearScreen(DepositScreen);
					frame.setSize(550,280);
					bankLogoMethod(DepositScreen, 50,20,500,45);
					
					JLabel DepositUserAccountNumber = new JLabel(); // puts account# on screen 
					DepositUserAccountNumber.setText(" Account #" + Userarraylist.get(z).getPersonalID()); // Displays Account Number
					Font font = new Font("Verdana", Font.ITALIC|Font.BOLD, 12);
					DepositUserAccountNumber.setFont(font);
					DepositUserAccountNumber.setBounds(335,200,500,45);
					DepositScreen.add(DepositUserAccountNumber);
	
					JLabel DepositAmount = new JLabel();
					DepositAmount.setText(" DEPOSIT AMOUNT: $"); // Label For How Much to be withdraw 
					DepositAmount.setBounds(90,90,300,30);
					DepositAmount.setForeground(Color.RED);
					DepositScreen.add(DepositAmount);
					JTextField Depositinput = new JTextField(); // Takes in User input 
					Depositinput.setBorder(new LineBorder(Color.black,1));
					Depositinput.setBounds(265,92,165,25);
					DepositScreen.add(Depositinput);
					
					Bankmethods.returnToMainPage(frame,UserOptionsScreen,DepositScreen,40,200, 145, 45); // Puts Back Button Screen 
					JButton DepositButton = new JButton();
					DepositButton.setText(" Deposit Funds");
					DepositButton.setFont(new Font("Open Sans", Font.BOLD,13));
					DepositButton.setForeground(Color.BLUE); // Bolden Text?
					DepositButton.setBounds(265, 130, 145, 45); // Probably center more 
					DepositScreen.add(DepositButton);
					
				DepositButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						double DepositAmount = 0; // sets Withdraw Amount 
						boolean DepositCase = true; // Boolean statement for Exception if user enter something other than a number 
						UIManager.put("OptionPane.background", Color.WHITE); 
						UIManager.getLookAndFeelDefaults().put("Panel.background", Color.WHITE);			
									
							try { // Exception in case User enters $ or Alphabet
							DepositAmount =  Double.parseDouble(Depositinput.getText());
						
							}catch(NumberFormatException e1) {
							DepositCase = false;
										
								}
						
			if(DepositCase){
				int Depositdisplay = JOptionPane.showOptionDialog(frame, " CHOOSE ACCOUNT: "," One America Bank", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, Bankmethods.options, null);
							
				if(Depositdisplay==1) { // Checkings 
				int DepositConfirmation = JOptionPane.showOptionDialog(frame, " CONFIRM DEPOSIT FOR " + 
				Bankmethods.Formatter(DepositAmount)," One America Bank" , JOptionPane.OK_CANCEL_OPTION,  JOptionPane.DEFAULT_OPTION, null, null, null); // Confirm Message 
				if (DepositConfirmation == 0) {
				UpdatedCheckingBalance = UpdatedCheckingBalance + DepositAmount;
				 Bankmethods.successfulTransaction(frame);
				 transactions.add(new Transaction(DepositAmount, Time, "Deposit", " Checkings")); // consider changing how it's Added 
					
				Depositinput.setText(""); // Sets Textfield to EMPTY This could stop array from getting input !!!!					
					}
					
						}
					if(Depositdisplay==0) { // Savings
					int DepositConfirmation2 = JOptionPane.showOptionDialog(frame, " CONFIRM DEPOSIT FOR " + 
					Bankmethods.Formatter(DepositAmount)," One America Bank" , JOptionPane.OK_CANCEL_OPTION,  JOptionPane.DEFAULT_OPTION, null, null, null); // Confirm Message 
					if (DepositConfirmation2 == 0) {
					UpdatedSavingsBalance = UpdatedSavingsBalance + DepositAmount;
				 Bankmethods.successfulTransaction(frame);
			transactions.add(new Transaction(DepositAmount, Time, "Deposit", " Savings")); // consider changing how it's Added 
				Depositinput.setText(""); // Sets Textfield to EMPTY This could stop array from get

						}
						
						}
													
						}
						else {
							JLabel NoSign1 = new JLabel("ERROR! ENTER ONLY DIGITS-NO LETTERS OR CHARACTERS");
							UIManager.put("OptionPane.background", Color.WHITE); 
							UIManager.getLookAndFeelDefaults().put("Panel.background", Color.WHITE);
							NoSign1.setFont(new Font("Open Sans", Font.BOLD, 14));
							NoSign1.setForeground(Color.RED);
							JOptionPane.showMessageDialog(frame, NoSign1
									, "One America Bank Online Banking",JOptionPane.DEFAULT_OPTION);

						}
					}
					
					
				});
					
				}
			}
			
		);
			
			JButton TransferFunds = new JButton(" Transfer Funds ");
			TransferFunds.setBounds(625,130,240,25);  // Enlarge the button
			TransferFunds.setForeground(Color.BLUE); 
			TransferFunds.setFont(new Font("Open Sans", Font.BOLD,13));
			UserOptionsScreen.add(TransferFunds);
			TransferFunds.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JPanel TransferFundsPanel = new JPanel();
					clearScreen(TransferFundsPanel);
					frame.setSize(500,340);
					bankLogoMethod(TransferFundsPanel,10,20,500,45);
					
					JLabel RecipientName = new JLabel(" Enter Recipient's Name "); // User enters Desired Recipient
					RecipientName .setBounds(5,70,300,25); 
					RecipientName.setFont(new Font("Open Sans", Font.ITALIC,16));
					RecipientName.setForeground(Color.RED);
					TransferFundsPanel.add(RecipientName);
					JTextField RecipientNameText = new JTextField(20);
					RecipientNameText.setBorder(new LineBorder(Color.black,1));
					RecipientNameText.setBounds(220,70,200, 25);
					TransferFundsPanel.add(RecipientNameText); 
				
					JLabel BankRoutingNum = new JLabel(" Enter Recipients Routing #"); // Enters Routing Number
					BankRoutingNum .setBounds(5,100,300,25); 
					BankRoutingNum.setFont(new Font("Open Sans", Font.ITALIC,16));
					BankRoutingNum.setForeground(Color.RED);
					TransferFundsPanel.add(BankRoutingNum);
					JTextField BankRoutingNumText = new JTextField(20);
					BankRoutingNumText.setBorder(new LineBorder(Color.black,1));
					BankRoutingNumText.setBounds(220,100,200,25);
					TransferFundsPanel.add(BankRoutingNumText); 
					
					JLabel RecBankName = new JLabel("Enter Recipient's Bank "); // // Enter Recipients Bank 
					RecBankName.setBounds(5,130,300,25); 
					RecBankName.setFont(new Font("Open Sans", Font.ITALIC,16));
					RecBankName.setForeground(Color.RED);
					TransferFundsPanel.add(RecBankName);
					JTextField RecBanknameText = new JTextField(20);
					RecBanknameText.setBorder(new LineBorder(Color.black,1));
					RecBanknameText.setBounds(220,130,200,25);
					TransferFundsPanel.add(RecBanknameText); 
					
					JLabel TransAmount = new JLabel("Enter Transfer Amount $"); // Enter Recipient Transfer Amount
					TransAmount.setBounds(5,160,300,25); 
				    TransAmount.setFont(new Font("Open Sans", Font.ITALIC,16));
				    TransAmount.setForeground(Color.RED);
					TransferFundsPanel.add( TransAmount);
					JTextField TransAmountText = new JTextField(20);
					TransAmountText.setBorder(new LineBorder(Color.black,1));
					TransAmountText.setBounds(220,160,200,25);
					TransferFundsPanel.add(TransAmountText); 
					
					JLabel WhichAccount = new JLabel ("Choose Transfer Account "); // Consider Changing 
					WhichAccount.setBounds(5,190,300,25); 
					WhichAccount.setForeground(Color.RED);
					WhichAccount.setFont(new Font("Open Sans", Font.ITALIC,16));
					TransferFundsPanel.add( WhichAccount);
					
					
					final JComboBox<String>  AccountList = new JComboBox<String>(Bankmethods.options); // Drop Down Menu For Account To Transfer Funds
				    AccountList.setBackground(Color.WHITE);
				    AccountList.setOpaque(true);
				    AccountList.setForeground(Color.BLACK);
				    AccountList.setVisible(true);
				    AccountList.setBounds(220,190,200,25);
				    TransferFundsPanel.add(AccountList);
				Bankmethods.returnToMainPage(frame, UserOptionsScreen,TransferFundsPanel, 5, 250 ,145, 45);
					
				
				JButton TransferFundsButton = new JButton("Transfer Funds!");
				TransferFundsButton.setFont(new Font("Open Sans", Font.BOLD,13));
				TransferFundsButton.setForeground(Color.BLUE); // Bolden Text?
				TransferFundsButton.setBounds(235, 215, 145, 45); // Probably center more 
				TransferFundsPanel.add(TransferFundsButton);
				
				TransferFundsButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						// Set All to Strings then to a Try and Catch for Transfer Amount 
						 // Then If it catches it will give a JOPTIONPane Alert
						 // Going to be withdraw Method same wat
						// Record All Transactions 
					UIManager.put("OptionPane.background", Color.WHITE); 
					 UIManager.getLookAndFeelDefaults().put("Panel.background", Color.WHITE);
					 
						String RecipientName =  RecipientNameText.getText(); // Converts TextField  Name TextField To String 
						String RecRoutingNumber = BankRoutingNumText.getText(); // Converts TextField  Routing # TextField To String 
						String BankName = RecBanknameText.getText();
						
						double TransferAmount = 0;
					    boolean TransferAmountBoolean = true;
					  
						try {
							TransferAmount =  Double.parseDouble(TransAmountText.getText());
						}catch(NumberFormatException e3) {
							TransferAmountBoolean = false;
						}
						
						if(TransferAmountBoolean && TransferAmount > 0 && RecRoutingNumber.matches("[0-9]+") && BankName.matches("^[a-zA-Z\\s]*$") && 
					 RecipientName.matches("^[a-zA-Z\\s]*$") && RecRoutingNumber.length() == 9 && BankName.length()>=7) {
							if (Bankmethods.options[0].equals(AccountList.getSelectedItem())) { // If equals Savings 
								JLabel UserSelection = new JLabel();
								UserSelection.setText(" Confirm  Transfer to " + RecipientName.toUpperCase() + 
									 " For " + Bankmethods.Formatter(TransferAmount) + " From Savings Account ");
							UserSelection.setForeground(Color.BLUE);

							int UserConfirm = JOptionPane.showOptionDialog(frame,UserSelection," One America Bank" , JOptionPane.OK_CANCEL_OPTION, 
							JOptionPane.DEFAULT_OPTION, null, null, null);
							if(UserConfirm == 0 && TransferAmount<= UpdatedSavingsBalance) { // Another Nested IF 
								UpdatedSavingsBalance = UpdatedSavingsBalance - TransferAmount;
								transactions.add(new Transaction(TransferAmount, Time, " Transfer", " Savings")); //Record Transaction
								Bankmethods.transferReceipt(frame, UserOptionsScreen, RecipientName, BankName, RecRoutingNumber, TransferAmount, "Savings");

							}
							else if (TransferAmount > UpdatedSavingsBalance ) { // THis code will run if User has insufficient funds or entered 0
								Error.setForeground(Color.RED); // Declared Static Above
								Error.setFont(new Font("Open Sans", Font.ITALIC, 14));
								UIManager.put("OptionPane.background", Color.WHITE); 
								UIManager.getLookAndFeelDefaults().put("Panel.background", Color.WHITE);
						JOptionPane.showMessageDialog(frame, Error, " One America Bank ", JOptionPane.DEFAULT_OPTION); // Sets Pop Up Screen
							}
							
					}
							 if ((Bankmethods.options[1].equals(AccountList.getSelectedItem()))) { // Checkings 
								 JLabel UserSelectionCheckings = new JLabel();
								 UserSelectionCheckings.setText(" Confirm  Transfer to " + RecipientName.toUpperCase() + 
								 " For " + Bankmethods.Formatter(TransferAmount) + " From Checkings Account ");
								 UserSelectionCheckings.setForeground(Color.BLUE);
								 int UserConfirm1 = JOptionPane.showOptionDialog(frame,UserSelectionCheckings," One America Bank" , JOptionPane.OK_CANCEL_OPTION, 
								 JOptionPane.DEFAULT_OPTION, null, null, null);
							 if(UserConfirm1 == 0 && TransferAmount<= UpdatedCheckingBalance) {
								 	UpdatedCheckingBalance = UpdatedCheckingBalance - TransferAmount;
									transactions.add(new Transaction(TransferAmount, Time, " Transfer", " Checkings")); //Record Transaction
									Bankmethods.transferReceipt(frame,UserOptionsScreen, RecipientName, BankName, RecRoutingNumber, TransferAmount, "Checkings");
									
									
							 }
							 else if(TransferAmount > UpdatedCheckingBalance){
							Error.setForeground(Color.RED);
							Error.setFont(new Font("Open Sans", Font.ITALIC, 14));
							JOptionPane.showMessageDialog(frame, Error, " One America Bank ", JOptionPane.DEFAULT_OPTION);
								 
							 }
						
							 }
						}
							else {

							JLabel Rejection = new JLabel("TRY AGAIN! CHECK INFO PROVIDED ");
							UIManager.put("OptionPane.background", Color.WHITE); 
							UIManager.getLookAndFeelDefaults().put("Panel.background", Color.WHITE);
							Rejection.setFont(new Font("Open Sans", Font.ITALIC, 14));
							Rejection.setForeground(Color.RED);
							JOptionPane.showMessageDialog(frame, Rejection
									, "One America Bank Online Banking",JOptionPane.DEFAULT_OPTION);
							}
					}
					
				});
				 
				 
		
				}

			});

			JButton CheckBalance = new JButton(" Check Balance "); // This Puts Button on Screen for User to check balance
			CheckBalance.setBounds(45,250,240,25);
			CheckBalance.setForeground(Color.BLUE); 
			CheckBalance.setFont(new Font("Open Sans", Font.BOLD,13));
			UserOptionsScreen.add(CheckBalance);
			CheckBalance.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) { // Method When User Clicks Check Balance 
					// TODO Auto-generated method stub
					JPanel CheckBalanceScreen = new JPanel();
					clearScreen(CheckBalanceScreen);
					frame.setSize(600,300); //500
					
					bankLogoMethod(CheckBalanceScreen,80,20,500,45); 

					JLabel UserCheckBalanceScreenWelcome = new JLabel(); // Account Statement name 
					UserCheckBalanceScreenWelcome.setText(Userarraylist.get(z).getFirstName() + "'s " + " Account Balance As Of: " + Time);
					UserCheckBalanceScreenWelcome.setFont(new Font("Calabri",  Font.ITALIC,14)); // fix up font
					UserCheckBalanceScreenWelcome.setForeground(Color.RED);
					UserCheckBalanceScreenWelcome.setBounds(90,60,500,45);
					CheckBalanceScreen.add(UserCheckBalanceScreenWelcome);
					
					JLabel UserAccountNumber = new JLabel(); // puts account# on screen 
					UserAccountNumber.setText(" Account #" + Userarraylist.get(z).getPersonalID());
					Font font = new Font("Verdana", Font.ITALIC|Font.BOLD, 12);
					UserAccountNumber.setFont(font);
					UserAccountNumber.setBounds(450, 220,500,45);
					CheckBalanceScreen.add(UserAccountNumber);
			 
					JLabel CheckingsDisplay = new JLabel(); // This Displays Checking Amount Balance
					CheckingsDisplay.setText(" CHECKINGS: " + Bankmethods.Formatter(UpdatedCheckingBalance));  // needs to be updated 
					CheckingsDisplay.setFont(new Font ("Open Sans",  Font.ITALIC,18));
					CheckingsDisplay.setForeground(Color.BLACK);
					CheckingsDisplay.setBounds(190, 100,500,45); 
					CheckBalanceScreen.add(CheckingsDisplay);
					
					JLabel SavingsDisplay = new JLabel(); // Displays Savings
					SavingsDisplay.setText(" SAVINGS: " + Bankmethods.Formatter(UpdatedSavingsBalance));  
					SavingsDisplay.setFont(new Font ("Open Sans",  Font.ITALIC,18));
					SavingsDisplay.setForeground(Color.BLACK);
					SavingsDisplay.setBounds(190,135,500,45);
					CheckBalanceScreen.add(SavingsDisplay);
					Bankmethods.returnToMainPage(frame,UserOptionsScreen, CheckBalanceScreen, 25, 220, 145, 45 ); // Return to User Options Screen  
	
				}

			});

			JButton TransactionHistory = new JButton (" Previous Transaction ");
			TransactionHistory.setBounds(335,250,240,25);
			TransactionHistory.setForeground(Color.BLUE); 
			TransactionHistory.setFont(new Font("Open Sans", Font.BOLD,13));
			UserOptionsScreen.add(TransactionHistory);
			TransactionHistory.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				
			JPanel TransactionHistoryScreen = new JPanel();
			clearScreen(TransactionHistoryScreen);
			frame.setSize(630,265);// was 290
			bankLogoMethod(TransactionHistoryScreen,100,20,500,45);

			JLabel Welcomes = new JLabel();
			Welcomes.setText(Userarraylist.get(z).getFirstName() + "'s" + " Previous Transaction Since Last Login " + Time);
			Welcomes.setFont(new Font(" monaco ", Font.ITALIC,12));
			Welcomes.setForeground(Color.RED);
			Welcomes.setBounds(55,55,500,45);
			TransactionHistoryScreen.add(Welcomes);
			Bankmethods.returnToMainPage(frame, UserOptionsScreen,TransactionHistoryScreen ,25,185, 145, 45);
			
			if( transactions.isEmpty()) {
				JLabel None = new JLabel();
				None.setText(" NO PREVIOUS TRANSACTIONS AVAILABLE");
				None.setFont(new Font(" monaco ", Font.BOLD,19));
				None.setBounds(75,115,500,45); // Consider Changing over 
				TransactionHistoryScreen.add(None);
			}
			else {
				
				JLabel Dashes = new JLabel();
				Dashes.setText("--------------------------------------------------");
				Dashes.setBounds(75,75,500,45);
				TransactionHistoryScreen.add(Dashes);
			
				JLabel header = new JLabel();
				header.setFont(new Font(" monaco ", Font.BOLD,15));
				String headers = String.format("%-15s%-10s%25s%15s",  "AMOUNT", "DATE", "TRANSACTION", "ACCOUNT");
				header.setText(headers);
				header.setBounds(75,110,500,45);
				TransactionHistoryScreen.add(header);
				
				int D = 0;
				for(int i = 0; i<transactions.size(); i++){
							D = i;
				}
				JLabel trans = new JLabel();
				trans.setText(transactions.get(D).toString());
				trans.setBounds(75,135,500,45);
				trans.setFont(new Font(" monaco ", Font.BOLD,12));
				TransactionHistoryScreen.add(trans);
				
				
			}

				}
			});
	
			JButton Signout = new JButton(" Sign Out "); // Or Exit 
			Signout.setBounds(625,250,240,25);
			Signout.setForeground(Color.BLUE); 
			Signout.setFont(new Font("Open Sans", Font.BOLD,13));
			UserOptionsScreen.add(Signout);
			Signout.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent SignOut) {
					// TODO Auto-generated method stub
					JLabel SignoutMessage = new JLabel("Thank You For Banking With One America Bank!"); // This is the message that Pops up When User Signs Out 
				SignoutMessage.setFont(new Font("Open Sans", Font.ITALIC, 13));
		SignoutMessage.setForeground(Color.BLUE);

		UIManager.put("OptionPane.background", Color.WHITE); 
		UIManager.getLookAndFeelDefaults().put("Panel.background", Color.WHITE);
		JOptionPane.showMessageDialog(frame, SignoutMessage
				, "One America Bank Online Banking",JOptionPane.DEFAULT_OPTION);

	            	panel.revalidate();
	            	panel.repaint();
	            	clearScreen(panel);
	            	passwordText.setText(""); // Sets Login Screen TextFields Back to blank
	            	userText.setText("");// Sets Login Screen TextFields Back to blank
	            	GUI();
	            	
				}
				
			}
					
					);
			 }
			 
			 else { // If User enters wrong User ID and Password this message is printed 
				//JLabel LoginError = new JLabel("  **Invalid Username or Password**  "); 
				 LoginError.setText("  **Invalid Username or Password**  ");
					 LoginError.setForeground(Color.RED);
					 LoginError.setFont(new Font("Arial",Font.ITALIC, 12));
					 LoginError.setBounds(90,210,350,45);
					panel.revalidate(); // Allows Panel to Update  W/ Message 
					panel.repaint(); // / Allows Panel to Update W/ Message
					panel.add(LoginError);
			 }

		}
		
	});
	
	// No Account Button Here
	NoAccountButton.setBounds(60,200,250,25); 
	NoAccountButton.addActionListener(new ATMGUI());
	panel.add (NoAccountButton);
	frame.setVisible(true); 
	}

	
	@Override
	public void actionPerformed(ActionEvent e) { // Method of No Account Create One Here No Account? Create One Here
		// This Method Brings User to registration Page to create Account
		/* This method name is action Performed 
		 * This Method will take user input and store it into Private Array 
		 * Also generate random pin code to be displayed to user after page is clicked
		 * Page has to Be a bit bigger due to it's taking input 
		 * This method should also have some stuff like to take in UserName and Password 
		 */
		JPanel CustomerSignup = new JPanel(); // This Created Panel for GUI
		clearScreen(CustomerSignup);
		CustomerSignup.setBackground(Color.WHITE); // Consider removing and going back to original color?
     	frame.setSize(430,520); // This set a new size for 

		// Moved This to the Top of the code 
		BanklogoONE.setFont(new Font("CALABRI",Font.ITALIC,37)); 
		 BanklogoONE.setForeground(Color.BLUE);
		BanklogoONE.setBounds(10,20,500,45); 
		CustomerSignup.add(BanklogoONE);
		
		JLabel CreateUsername = new JLabel("Create Username "); // User enters Desired Username 
		CreateUsername.setBounds(10,70,300,25); 
		CustomerSignup.add(CreateUsername);
		JTextField CreateUsernameText = new JTextField(20);
		CreateUsernameText.setBorder(new LineBorder(Color.black,1));
		CreateUsernameText.setBounds(150,70,200, 25);
		CustomerSignup.add(CreateUsernameText); 
	
		JLabel CreateFirstName = new JLabel("Enter First Name "); // User enters First Name
		 CreateFirstName.setBounds(10,100,300,25); 
		CustomerSignup.add( CreateFirstName);
		JTextField CreateFirstNameText = new JTextField(20);
		CreateFirstNameText.setBorder(new LineBorder(Color.black,1));
		CreateFirstNameText.setBounds(150,100,200, 25);
		CustomerSignup.add(CreateFirstNameText);

		
		JLabel CreateLastName = new JLabel("Enter Last Name "); // User enters Last Name Name
		CreateLastName.setBounds(10,130,300,25);
		CustomerSignup.add(CreateLastName);
		JTextField CreateLastNameText = new JTextField(20);
		CreateLastNameText.setBorder(new LineBorder(Color.black,1));
		CreateLastNameText.setBounds(150,130,200, 25);
		CustomerSignup.add(CreateLastNameText);
		
		JLabel CreateEmailAddress = new JLabel("Enter Email Address"); // Enter Email 
		CreateEmailAddress.setBounds(10,160,300,25);
		CustomerSignup.add(CreateEmailAddress);
		JTextField CreateEmailAddressText = new JTextField(20);
		CreateEmailAddressText.setBorder(new LineBorder(Color.black,1));
		CreateEmailAddressText.setBounds(150,160,200, 25);
		CustomerSignup.add(CreateEmailAddressText);
	
		JLabel CreateConfirmEmailAddress = new JLabel("Confirm Email "); // Confirm Email
		CreateConfirmEmailAddress.setBounds(10,190,300,25);
		CustomerSignup.add(CreateConfirmEmailAddress);
		JTextField CreateConfirmEmailAddressText = new JTextField(20);
		CreateConfirmEmailAddressText.setBorder(new LineBorder(Color.black,1));
		CreateConfirmEmailAddressText.setBounds(150,190,200, 25);
		CustomerSignup.add(CreateConfirmEmailAddressText);
		
		JLabel CreatePhoneNumber = new JLabel("Enter Mobile Number"); // Enter Phone
		CreatePhoneNumber.setBounds(10,220,300,25);
		CustomerSignup.add(CreatePhoneNumber);
		JTextField  CreatePhoneNumberText = new JTextField(20); 
		CreatePhoneNumberText.setBorder(new LineBorder(Color.black,1));
		CreatePhoneNumberText.setBounds(150,220,200, 25);
		CustomerSignup.add(CreatePhoneNumberText);
		
		
		JLabel CreateHomeAddress = new JLabel("Enter Home Address"); // Enter Home Address
		CreateHomeAddress.setBounds(10,250,300,25);
		CustomerSignup.add(CreateHomeAddress);
		JTextField  CreateHomeAddressText = new JTextField(20); 
		CreateHomeAddressText.setBorder(new LineBorder(Color.black,1));
		CreateHomeAddressText.setBounds(150,250,200, 25);
		CustomerSignup.add(CreateHomeAddressText);
		
		JLabel CreateHomeAddressState = new JLabel("Enter State"); // Enter State
		CreateHomeAddressState.setBounds(10,280,300,25);
		CustomerSignup.add(CreateHomeAddressState);
		JTextField  CreateHomeAddressStateText = new JTextField(20); 
		CreateHomeAddressStateText.setBorder(new LineBorder(Color.black,1));
		CreateHomeAddressStateText.setBounds(150,280,200, 25);
		CustomerSignup.add(CreateHomeAddressStateText);
		
		
		JLabel  CreateHomeAddressCity = new JLabel("Enter City"); // Enter User City
		CreateHomeAddressCity.setBounds(10,310,300,25);
		CustomerSignup.add(CreateHomeAddressCity);
		JTextField  CreateHomeAddressCityText = new JTextField(20);
		CreateHomeAddressCityText.setBorder(new LineBorder(Color.black,1));
		CreateHomeAddressCityText.setBounds(150,310,200, 25);
		CustomerSignup.add(CreateHomeAddressCityText);

		
		JLabel CreateHomeAddressZipCode = new JLabel("Enter Zip Code"); // Enter Zip Code
		CreateHomeAddressZipCode.setBounds(10,340,300,25);
		CustomerSignup.add(CreateHomeAddressZipCode);
		JTextField  CreateHomeAddressZipCodeText = new JTextField(20); 
		 CreateHomeAddressZipCodeText.setBorder(new LineBorder(Color.black,1));
		CreateHomeAddressZipCodeText.setBounds(150,340,200, 25);
		CustomerSignup.add(CreateHomeAddressZipCodeText);
	
	 
		JLabel CreateInitialDeposit = new JLabel("Enter Initial Deposit $"); // Enter Initial Deposit
		CreateInitialDeposit.setBounds(10,370,300,25);
		CustomerSignup.add(CreateInitialDeposit);
		JTextField  CreateInitialDepositText = new JTextField(20);
		 CreateInitialDepositText.setBorder(new LineBorder(Color.black,1));
		CreateInitialDepositText.setBounds(150,370,200, 25);
		CustomerSignup.add(CreateInitialDepositText);
		
		JButton SubmitApplicationButton = new JButton("Create Account"); // Create Account Button
		SubmitApplicationButton.setBounds(60,400,250,25); 
		SubmitApplicationButton.setBackground(Color.BLUE); 
		SubmitApplicationButton.setOpaque(true); 
		SubmitApplicationButton.setForeground(Color.WHITE);
		SubmitApplicationButton.setBorderPainted(false);
		CustomerSignup.add(SubmitApplicationButton);
		// ADD the listener
		
		JButton GoBack1 = new JButton (" < Go Back "); // Creates Button
		GoBack1.setForeground(Color.BLUE);
		GoBack1.setBounds(5, 445,  145, 45);
		GoBack1.setFont(new Font("Open Sans", Font.BOLD,15));
		CustomerSignup.add(GoBack1);     // Adds Button
		
		SubmitApplicationButton.addActionListener(new ActionListener() {

	         @Override
	         public void actionPerformed(ActionEvent arg0) {
	           //Bankmethods.GetText(Hi, CreateUsernameText);
	         String Username = CreateUsernameText.getText(); // Username String
	            String UserFirstname = CreateFirstNameText.getText(); // UserFirstname String
	            String UserLastname = CreateLastNameText.getText(); // User Last Name
	            String UserEmailAddress = CreateEmailAddressText.getText(); // User Email Address 
	            String UserConfirmEmailAddress = CreateConfirmEmailAddressText.getText(); // Confirmed User Email Address
	            String UserPhonenumber = CreatePhoneNumberText.getText(); // User Phone Number CONSIDER CHANGING THE NUMBER TO AN INT
	            String UserHomeAddress = CreateHomeAddressText.getText(); // User Home Address
	            String UserState = CreateHomeAddressStateText.getText(); // user State
	            String UserCity = CreateHomeAddressCityText.getText();
	            String UserZipCode =  CreateHomeAddressZipCodeText.getText(); // User Zip Code
	
	       double UserInitialDeposit = 0;
	       Boolean Deposit = true;
	      
	    try {  // Try & Catch for Number Format Exception
	          UserInitialDeposit =  Double.parseDouble(CreateInitialDepositText.getText()); // User
	     }catch(NumberFormatException e) {
	    	Deposit = false;
	     }
	       
	     String UserHomeAddressInfo =  (UserHomeAddress + " " + UserCity  + " " + UserState + " " + UserZipCode); 
	            // Users Home Address Info all combined into One String 

	    boolean UsernameValid = true; // This method ensures that the name is not already in the array list/ User cannot have the same username 
	     for( int d = 0; d < Userarraylist.size(); d++){
	       if (Userarraylist.get(d).getUsername().equals(Username)){
	       UsernameValid = false;
	           }
	            	
	            }
	            
	   double InitialDepositminimumvalue = 0;
	   if (UserConfirmEmailAddress.matches(UserEmailAddress) && UserConfirmEmailAddress.contains("@") &&
	       UserConfirmEmailAddress.contains (".com") && UserInitialDeposit > InitialDepositminimumvalue && UserPhonenumber.length() >= 10 
	       && UsernameValid && Deposit) { // If statement that checks parameters for Customer signup 	   	// No numbers in name

	         int UserPersonalNum = 254678219 + (int)(Math.random() * 98653219); // User Personal Number
	         final String User_Personal_Number = Integer.toString (UserPersonalNum); // User Personal Number
	            	
	          int  Pin =  1280 + (int)(Math.random() * 9980); // Assigns Random Value for User PineCode
	          final String UserPinCode = Integer.toString(Pin);
	
	          Userarraylist.add(new ATMUser(UserFirstname, UserLastname, Username, User_Personal_Number, UserPinCode, UserConfirmEmailAddress,
	          UserHomeAddressInfo,UserPhonenumber,UserInitialDeposit ));// Enter's User input  into User Array

	            // Successful Account Creation Panel
	            JPanel SuccessfulAccountCreation = new JPanel();
	            clearScreen(SuccessfulAccountCreation);
	            SuccessfulAccountCreation.setBackground(Color.WHITE);
	            frame.setSize(800,350); 

	           	 // Adds Bank Logo To The Tab
	           	 BanklogoONE.setFont(new Font("CALABRI",Font.ITALIC,37));
	           	 BanklogoONE.setForeground(Color.BLUE);
	           	 BanklogoONE.setBounds(240,20,500,45); 
	           	 SuccessfulAccountCreation.add(BanklogoONE);

	            JLabel AccountSuccessCreation = new JLabel ("ACCOUNT SUCCESSFULLY CREATED!"); // Displays Message that Account Successfully Created
	        	AccountSuccessCreation.setFont(new Font ("CALABRI", Font.ITALIC,18));
	        	AccountSuccessCreation.setForeground(Color.GREEN);
	        	AccountSuccessCreation.setBounds(250,55,500,45); 
	        	SuccessfulAccountCreation.add(AccountSuccessCreation); 
	        
	        	 JLabel AccountPINProvidedMessage = new JLabel() ; // Adds Pincode to display message on Successful Account Creation
	        	 AccountPINProvidedMessage.setFont(new Font ("Open Sans", Font.PLAIN,17));
	        	 AccountPINProvidedMessage.setBounds(240,85,500,45); 
	        	 AccountPINProvidedMessage.setText(UserFirstname.toUpperCase() + "," +  " YOUR USER PINCODE IS: " + UserPinCode);
	        	 SuccessfulAccountCreation.add(AccountPINProvidedMessage);
	        		 	
	           JLabel AccountPersonalIDProvidedMessage = new JLabel ();
	           AccountPersonalIDProvidedMessage.setFont(new Font ("Open Sans", Font.PLAIN,17));
	           AccountPersonalIDProvidedMessage.setBounds(250,104,500,45); 
	           AccountPersonalIDProvidedMessage.setText(" USER PERSONAL BANK ID: " + User_Personal_Number);
	           SuccessfulAccountCreation.add(AccountPersonalIDProvidedMessage);
	        		
	           JLabel InfoOnAccount = new JLabel ();// Tells User Message On WhatTo Do
	           InfoOnAccount.setFont(new Font ("ARIAL", Font.PLAIN,15));
	           InfoOnAccount.setForeground(Color.RED);
	           InfoOnAccount.setBounds(200,160,500,45); // Put 90 possibly to 200 
	           InfoOnAccount.setText(" **Please Use Pincode As Password And Login With Username**");
	           SuccessfulAccountCreation.add(InfoOnAccount);
	  
	        	JButton GoLogin = new JButton("Login"); // This Button Brings you to the Login Page from success account creation
	        	GoLogin.setBounds(300,200,250,25); 
	        	GoLogin.setBackground(Color.BLUE); 
	        	GoLogin.setOpaque(true); 
	        	GoLogin.setForeground(Color.WHITE);
	        	GoLogin.setBorderPainted(false);
	        	SuccessfulAccountCreation.add(GoLogin);
	
	        	GoLogin.addActionListener(new ActionListener() {

					@Override
				public void actionPerformed(ActionEvent e) { // Calls GUI Method Bringing User Back to Main Screen
				// TODO Auto-generated method stub
				clearScreen(panel);
				GUI(); 
					}
	        		
	        	});
	
	            }
	            else { // If User Inputs Exception or Wrong Information This Will Run
	            	WrongInfo.setBounds(10,425,460,25); 
	        		WrongInfo.setText(" Error! Please Check Username, Email Address, Deposit, & Phone Number "); 
	        		WrongInfo.setForeground(Color.RED);
	        		WrongInfo.setFont(new Font("Arial",Font.ITALIC, 12));
	        		CustomerSignup.revalidate(); // Allows Panel to Update 
	        		CustomerSignup.repaint();
	        		CustomerSignup.add(WrongInfo);
	            }
	      };
	
		});
	}
}


