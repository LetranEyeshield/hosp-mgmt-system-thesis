package Moriel;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import com.mysql.jdbc.PreparedStatement;




public class Login extends JFrame implements ActionListener{


	
JTextField username;

JPasswordField password;

JLabel labelUser, labelPass;

String user, pass, userType;

JButton login, forgot;

JPanel panel;




public Login() {
super(" HOSPITAL MANAGEMENT SYSTEM");
setLayout(null);


setObjects();
log_in();
ForgotPass();





setBackground();




}



public void setObjects(){
	
	
	
	

	labelUser = new JLabel("     Username ");
	labelUser.setBounds(50,80,100,20);
	labelUser.setOpaque(true);
	labelUser.setBackground(Color.green);
	labelUser.setForeground(Color.black);
	add(labelUser);


	labelPass = new JLabel("     Password ");
	labelPass.setBounds(50,110,100,20);
	labelPass.setOpaque(true);
	labelPass.setBackground(Color.green);
	labelPass.setForeground(Color.black);
	add(labelPass);


	username = new JTextField();
	username.setBounds(160,80,100,20);
	add(username);
	
	
	password = new JPasswordField();
	password.setBounds(160,110,100,20);
	password.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent morielKim) {
			// TODO Auto-generated method stub
			Validate();
			
		}
		
	});
	 add(password);


}

public void log_in(){
	
	login = new JButton("Log In");
	login.setBounds(160,140,80,30);
	login.addActionListener(this);
	add(login);


}

public void setBackground(){

	 JLabel background = new JLabel(new ImageIcon("res/login.png"));
	 background.setBounds(0,0,370,220);
	 background.setOpaque(true);
	 add(background);
	 
}

	
	@SuppressWarnings("deprecation")
	public void Validate(){
		
		String User = username.getText().toString();
		String Pass = password.getText().toString();
		String query;
		
		if (User.equals("") || Pass.equals("")){
			
			 JOptionPane.showMessageDialog(null,"Fill All Fields!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
			 
			
		}else{
			
			
			/*ETO SANA UNG CHECKING KUNG NILAGAY KO UNG BLOCKING OF ACCOUNT
			query = "SELECT * FROM users WHERE username = "+"\""+username.getText().toString()+"\""
					+" AND password = "+"\""+password.getText().toString()+"\""+" AND block = 0 OR block= 1 OR block= 2";*/
			
			query = "SELECT * FROM users WHERE username = "+"\""+username.getText().toString()+"\""
					+" AND password = "+"\""+password.getText().toString()+"\"";
			 

			try{

				//set natin ang pag access sa mysql driver
				String driver = "org.gjt.mm.mysql.Driver";

				//set natin ang host, port, at database natin

				String url = "jdbc:mysql://localhost:3306/h_m_s";

				//access natin ang java mysql driver library na ating inimport para magamit
				Class.forName(driver);

				//set natin ung user at pass ng sql appz, server, host or kung anong tawag jan natin, mine is xammp 

				String user = "root";
				String pass = "";

				//create na natin ung connection para sating database 
				Connection conn = DriverManager.getConnection(url,user,pass);


				 java.sql.Statement stmt = conn.createStatement();

				ResultSet rs = stmt.executeQuery(query);
				 
					
					if(rs.first()){
						
						userType = rs.getString("user_type");
						
						
						switch(userType){
						
						case "Doctor":
							
							DoctorMenu DM = new DoctorMenu();
							DM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							DM.setSize(670,520);
							DM.setVisible(true);
							DM.setResizable(false);
							
						break;
						
						case "Pharmacist":
							
							PharmacistMenu PM = new PharmacistMenu();
							PM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							PM.setSize(670,520);
							PM.setVisible(true);
							PM.setResizable(false);
							
						break;
						
						
						case "Nurse":
							
							NurseMenu NM = new NurseMenu();
							NM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							NM.setSize(670,520);
							NM.setVisible(true);
							NM.setResizable(false);
							
						break;
						
						
						case "Data Encoder":
							
							DataEncoderMenu DEM = new DataEncoderMenu();
							DEM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							DEM.setSize(670,520);
							DEM.setVisible(true);
							DEM.setResizable(false);
							
							
						break;
						
						
						case "Treasurer":
							
							TreasurerMenu TM = new TreasurerMenu();
							TM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							TM.setSize(670,520);
							TM.setVisible(true);
							TM.setResizable(false);
							 
							
						break;
						
						case "Cashier":
							
							CashierMenu CM = new CashierMenu();
							CM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							CM.setSize(670,520);
							CM.setVisible(true);
							CM.setResizable(false);
							 
							
						break;
						
						
						default:
							
							Menu M = new Menu();
							M.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							M.setSize(670,520);
							M.setVisible(true);
							M.setResizable(false);
								
							
						break;
						
						
						}
						
						InsertUserLogSQL();
						
						this.dispose();
					 
				 }else{
					 
					 JOptionPane.showMessageDialog(null,"Invalid Username/Password Combination!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
					 
					 
					 /*
					 ETO SANA UNG LALABAS PAG MAY BLOCK OF ACCOUNT
					 JOptionPane.showMessageDialog(null,"Invalid Username/Password Combination!"+"\n"+"OR ACCOUNT IS BLOCKED!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
					 */
					 
					
				}
				 
						
				conn.close();

				}catch (Exception moriel){

				 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 

					} 

			
		}


}

	
	public void InsertUserLogSQL(){
		
		//DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:m:s");
		 Date date = new Date();
		  
		try{

		//set natin ang pag access sa mysql driver
		String driver = "org.gjt.mm.mysql.Driver";

		//set natin ang host, port, at database natin

		String url = "jdbc:mysql://localhost:3306/h_m_s";

		//access natin ang java mysql driver library na ating inimport para magamit
		Class.forName(driver);

		//set natin ung user at pass ng sql appz, server, host or kung anong tawag jan natin, mine is xammp 

		String user = "root";
		String pass = "";

		//create na natin ung connection para sating database 
		Connection conn = (Connection) DriverManager.getConnection(url,user,pass);

		//set the sql query

			 String query = "INSERT INTO users_log (username, user_type, date)" + "values(?,?,?)";

			 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);

			  ps.setString(1, username.getText().toString());

			  ps.setString(2, userType); 

			  ps.setString(3, dateFormat.format(date)); 
			
			 ps.execute();

			  //JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
			 



		conn.close();

		}catch (Exception moriel){

		 		// JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

			} 




		} 
	
	
	@Override
	public void actionPerformed(ActionEvent moriel) {
		// TODO Auto-generated method stub
		Validate();
		
	}
	
	
	//METHOD PARA SA FORGOT PASS MEDYO NAHIRAPAN AKO DITO KESA DON SA VB.NET EWAN KO BA!
	public void ForgotPass(){
		
		forgot = new JButton("Forgot Password?");
		forgot.setBounds(135,180,140,20);
		forgot.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent moriel) {
				// TODO Auto-generated method stub
				
				String getUsername = JOptionPane.showInputDialog(null,"Enter Your Username"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.QUESTION_MESSAGE);
				
				
				String query1 = "SELECT * FROM users WHERE username = "+"\""+getUsername+"\"";
				 
				try{

					//set natin ang pag access sa mysql driver
					String driver = "org.gjt.mm.mysql.Driver";

					//set natin ang host, port, at database natin

					String url = "jdbc:mysql://localhost:3306/h_m_s";

					//access natin ang java mysql driver library na ating inimport para magamit
					Class.forName(driver);

					//set natin ung user at pass ng sql appz, server, host or kung anong tawag jan natin, mine is xammp 

					String user = "root";
					String pass = "";

					//create na natin ung connection para sating database 
					Connection conn = DriverManager.getConnection(url,user,pass);


					 java.sql.Statement stmt = conn.createStatement();

					ResultSet rs = stmt.executeQuery(query1);
					 
						int counter = 0;

						 while(rs.next()){
							
							
							 String getQ = JOptionPane.showInputDialog(null,"Answer Your Security Question"+
									 			"\n\n"+rs.getString("question")+"?"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.QUESTION_MESSAGE);
							
								 
							String query2 = "SELECT * FROM users WHERE username = "+"\""+getUsername+"\" AND answer = "+"\""+getQ.toLowerCase()+"\"";
							
							ResultSet RS = stmt.executeQuery(query2);
							
							
							int counter2 = 0;
							
							
							while(RS.next()){
								
								String getPass = RS.getString("password");
								
								JOptionPane.showMessageDialog(null,"Your Password Is "+getPass," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE);		 
								 
								
								counter2++;
								
								
							}
							
							
							if(counter2<1){
								
								
								JOptionPane.showMessageDialog(null,"Invalid Answer!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE);		 
								 
							}
							
							counter++;
							
						 }
						
						 
					if(counter<1){
						
						
						JOptionPane.showMessageDialog(null,"The Username "+getUsername+" Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE);		 
						 
					}
					
							
					conn.close();

					}catch (Exception morielKim){
						
							//COMMENT OUT NYO LANG KUNG GAGAMITIN SA DEBUGGING PURPOSES
						//KASI LUMALABAS UNG RESULSET NOT ALLOWED EWAN KO KUNG BAKIT
					 		 //JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+morielKim.getMessage(),"HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
							 

						} 

				
			}
				
			
			
		});
		add(forgot);


	}
	
	
	
	
	
	//FOR FUTURE USE SAKALI NEED I-LIMIT ANG LOG-IN ATTEMPT
public void FirstTry(){
		
		String User = username.getText().toString();
		String Pass = password.getText().toString();
		String query;
		
		if (User.equals("") || Pass.equals("")){
			
			 JOptionPane.showMessageDialog(null,"Fill All Fields!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
			 
			
		}else{
			
			query = "SELECT * FROM users WHERE username = "+"\""+username.getText().toString()+"\""
					+" AND password = "+"\""+password.getText().toString()+"\"";
			 

			try{

				//set natin ang pag access sa mysql driver
				String driver = "org.gjt.mm.mysql.Driver";

				//set natin ang host, port, at database natin

				String url = "jdbc:mysql://localhost:3306/h_m_s";

				//access natin ang java mysql driver library na ating inimport para magamit
				Class.forName(driver);

				//set natin ung user at pass ng sql appz, server, host or kung anong tawag jan natin, mine is xammp 

				String user = "root";
				String pass = "";

				//create na natin ung connection para sating database 
				Connection conn = DriverManager.getConnection(url,user,pass);


				 java.sql.Statement stmt = conn.createStatement();

				ResultSet rs = stmt.executeQuery(query);
				 
					
					if(rs.first()){
						 

						Menu M = new Menu();
						M.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						M.setSize(670,520);
						M.setVisible(true);
						M.setResizable(false);
						
						this.dispose();
					 
				 }else{
					 
					 JOptionPane.showMessageDialog(null,"Invalid Username/Password Combination!"+"\n"+"You have 2 log-in attempts left!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
					 
					 String sql = "UPDATE users SET block = 1 WHERE username= "+"\""+username.getText().toString()+"\"";

					 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

					 ps.setString(1, User);


					 ps.execute();
					 
					 SecondTry();
					
				}
				 
						
				conn.close();

				}catch (Exception moriel){

				 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 

					} 

			
		}


}





//ETO UNG 2ND TRY
//NOTE: THIS CODE IS NOT IMPLEMENTED DAHIL AYOKO HEHE...
public void SecondTry(){
	
	String User = username.getText().toString();
	String Pass = password.getText().toString();
	String query;
	
	if (User.equals("") || Pass.equals("")){
		
		 JOptionPane.showMessageDialog(null,"Fill All Fields!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
		
	}else{
		
		query = "SELECT * FROM users WHERE username = "+"\""+username.getText().toString()+"\""
				+" AND password = "+"\""+password.getText().toString()+"\"";
		 

		try{

			//set natin ang pag access sa mysql driver
			String driver = "org.gjt.mm.mysql.Driver";

			//set natin ang host, port, at database natin

			String url = "jdbc:mysql://localhost:3306/h_m_s";

			//access natin ang java mysql driver library na ating inimport para magamit
			Class.forName(driver);

			//set natin ung user at pass ng sql appz, server, host or kung anong tawag jan natin, mine is xammp 

			String user = "root";
			String pass = "";

			//create na natin ung connection para sating database 
			Connection conn = DriverManager.getConnection(url,user,pass);


			 java.sql.Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			
			 
				
				if(rs.first()){
					 

					Menu M = new Menu();
					M.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					M.setSize(670,520);
					M.setVisible(true);
					M.setResizable(false);
					
					this.dispose();
				 
			 }else{
				 
				 //MAY ILALAGAY SANA AKONG MADUGONG CODING DITO PARA I-CHECK KUNG NAKAILANG LOGIN ATTEMPTS NA CYA
				 //PARANG GANITO UNG METHOD 
				 //checkLoginAttemps();
				 
				 JOptionPane.showMessageDialog(null,"Invalid Username/Password Combination!"+"\n"+"You have 1 log-in attempt left!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
				 String sql = "UPDATE users SET block = 2 WHERE username= "+"\""+username.getText().toString()+"\"";

				 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

				 ps.setString(1, User);


				 ps.execute();
				 
				 ThirdTry();
				
			}
			 
					
			conn.close();

			}catch (Exception moriel){

			 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
					 

				} 

		
	}


}



//ETO UNG 3RD TRY NG LOG-IN ATTEMPT
//NOTE: THIS CODE IS NOT IMPLEMENTED DAHIL AYOKO HEHE...
public void ThirdTry(){
	
	String User = username.getText().toString();
	String Pass = password.getText().toString();
	String query;
	
	if (User.equals("") || Pass.equals("")){
		
		 JOptionPane.showMessageDialog(null,"Fill All Fields!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
		
	}else{
		
		query = "SELECT * FROM users WHERE username = "+"\""+username.getText().toString()+"\""
				+" AND password = "+"\""+password.getText().toString()+"\"";
		 

		try{

			//set natin ang pag access sa mysql driver
			String driver = "org.gjt.mm.mysql.Driver";

			//set natin ang host, port, at database natin

			String url = "jdbc:mysql://localhost:3306/h_m_s";

			//access natin ang java mysql driver library na ating inimport para magamit
			Class.forName(driver);

			//set natin ung user at pass ng sql appz, server, host or kung anong tawag jan natin, mine is xammp 

			String user = "root";
			String pass = "";

			//create na natin ung connection para sating database 
			Connection conn = DriverManager.getConnection(url,user,pass);


			 java.sql.Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			 
				
				if(rs.first()){
					 

					Menu M = new Menu();
					M.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					M.setSize(670,520);
					M.setVisible(true);
					M.setResizable(false);
					
					this.dispose();
				 
			 }else{
				 
				 JOptionPane.showMessageDialog(null,"Invalid Username/Password Combination!"+"\n"+"YoUr Account Is Blocked!"+
						 							"Please Contact Eyeshield Or The System Admisnistrator"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
				 String sql = "UPDATE users SET block = 3 WHERE username= "+"\""+username.getText().toString()+"\"";

				 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

				 ps.setString(1, User);


				 ps.execute();
				 
				 System.exit(0);
				
			}
			 
					
			conn.close();

			}catch (Exception moriel){

			 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
					 

				} 

		
	}


}




//ETO UNG CHECKING SANA KUNG ADMIN ANG USER OR ORDINARY USER LANG
//WALA KASI AKONG IBANG MAISIP NA IBANG ADMIN PRIVILEDGES KUNDI ANG MANG-BLOCK OR UNBLOCK NG USER ACCOUNT EH!
//HINDI RIN ITO NAIMPLEMENT
public void checkAdmin(){
	
	String User = username.getText().toString();
	String Pass = password.getText().toString();
	String query;
	
	if (User.equals("") || Pass.equals("")){
		
		 JOptionPane.showMessageDialog(null,"Fill All Fields!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
		
	}else{
		
		query = "SELECT * FROM users WHERE username = "+"\""+username.getText().toString()+"\""
				+" AND password = "+"\""+password.getText().toString()+"\" AND user_type = 'admin'";
		 

		try{

			//set natin ang pag access sa mysql driver
			String driver = "org.gjt.mm.mysql.Driver";

			//set natin ang host, port, at database natin

			String url = "jdbc:mysql://localhost:3306/h_m_s";

			//access natin ang java mysql driver library na ating inimport para magamit
			Class.forName(driver);

			//set natin ung user at pass ng sql appz, server, host or kung anong tawag jan natin, mine is xammp 

			String user = "root";
			String pass = "";

			//create na natin ung connection para sating database 
			Connection conn = DriverManager.getConnection(url,user,pass);


			 java.sql.Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			 
				
			//BAGO ANG LINE OF CODE NA ITO, CHECKING MUNA SANA NG USER TYPE KUNG NAIMPLEMENT
			//MADUGONG CODING ULIT PARANG GANITO
			
				if(rs.first()){
					 

					//ADMIN PRIVILEDGES PANEL HEHE...
					
				 
			 }else{
				 
				 //ETO MAGIGING QUERY PAG HINDI CYA ADMIN BUT HINDI KO PA NA-TRY KC SHORTCUT MODE LANG ITO HEHE...
				 //HIRAP KAYANG MAG LONG-CUT MODE KAU KAYA GUMAWA KONOYAROU!!!
				 query = "SELECT * FROM users WHERE username = "+"\""+username.getText().toString()+"\""
							+" AND password = "+"\""+password.getText().toString()+"\" AND user_type = 'user'";
				 
				 
				 
				 	Menu M = new Menu();
					M.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					M.setSize(670,520);
					M.setVisible(true);
					M.setResizable(false);
					
					this.dispose();
			
				
			}
			 
					
			conn.close();

			}catch (Exception moriel){

			 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
					 

				} 

		
	}


}







	
	
}





