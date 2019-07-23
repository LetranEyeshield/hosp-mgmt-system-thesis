package Moriel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Users extends JFrame implements ActionListener, MouseListener, KeyListener{
	
	JLabel labels,title;
	
	JTextField user, pass, question, answer;
	
	JButton add, search, edit, delete, clear, exit;
	
	String User,Pass, Q, A;
	
	String UT[] = {"", "Admin", "Pharmacist","Doctor","Nurse","Treasurer","Data Encoder","Cashier","Info Officer"};
	JComboBox userType;
	
	JTable table;

	DefaultTableModel model;
	
	public Users(){
		super("HOSPITAL MANAGEMENT SYSTEM");
		setLayout(null);
		
		setTitle();
		setLabels();
		setFields();
		setLine();
		setButtons();
		setPanels();
		tableModel();
		setBackground();
		
	     
	}
	
	
public void setTitle(){
		
		title = new JLabel("                             " +
				"                       USER ACCOUNTS");
		title.setBounds(0,0,800,25);
		title.setOpaque(true);
		title.setBackground(Color.blue);
		title.setFont(new Font("OLD ENGLISH MT",Font.BOLD,20));
		add(title);
	}


public void prepareLabels(String jammi, int xpos, int ypos, int width, int height,Color color){
	
	labels = new JLabel(jammi);
	labels.setBounds(xpos,ypos,width,height);
	labels.setOpaque(true);
	labels.setBackground(color);
	labels.setForeground(Color.WHITE);
	labels.setFont(new Font("Tahoma",Font.PLAIN,14));
	add(labels);
	
}

public void setLabels(){
	
	prepareLabels(" Username:",30,100,80,18,Color.BLUE);
	prepareLabels(" Password:",30,130,80,18,Color.BLUE);
	prepareLabels(" User Type:",30,160,80,18,Color.BLUE);
	prepareLabels(" Enter Your Security Question:",30,190,210,18,Color.BLUE);
	prepareLabels(" Enter Your Security Answer:",30,220,210,18,Color.BLUE);
	
	
	
}

public void setPanels(){
	
	JLabel panel1 = new JLabel();
	panel1.setBounds(20,80,500,190);
	panel1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel1);
	
	JLabel panelLabel1 = new JLabel("  Users Information ");
	panelLabel1.setBounds(20,60,130,20);
	panelLabel1.setOpaque(true);
	panelLabel1.setBackground(Color.CYAN);
	add(panelLabel1);
	
	JLabel panel2 = new JLabel();
	panel2.setBounds(540,80,220,190);
	panel2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel2);
	
	
}


public void setBackground(){

	JLabel background = new JLabel(new ImageIcon("res/users.png"));
	 background.setBounds(0,0,800,470);
	// background.setBackground(new Color(0,0,255));
	// background.setBackground(Color.LIGHT_GRAY);
	 background.setOpaque(true);
	 add(background);
	 
}



public void setFields(){
	
	user = new JTextField();
	user.setBounds(120,100,120,18);
	user.addKeyListener(this);
	add(user);
	
	pass = new JTextField();
	pass.setBounds(120,130,120,18);
	add(pass);
	
	userType = new JComboBox(UT);
	userType.setBounds(150,160,120,18);
	userType.setSelectedIndex(0);
	userType.addActionListener(this);
	add(userType);
	
	question = new JTextField();
	question.setBounds(250,190,250,18);
	add(question);
	
	answer = new JTextField();
	answer.setBounds(250,220,250,18);
	add(answer);
	
	
}
	
	
public void setLine(){
	
	JLabel line = new JLabel("");
	line.setBounds(0,290,800,1);
	line.setOpaque(true);
	line.setBackground(Color.RED);
	add(line);
}


public void setButtons(){
	

	
	add = new JButton("ADD");
	add.setBounds(60,340,90,25);
	add.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			checkInputs();
		}
		
	});
	add(add);
	
	
	search = new JButton("SEARCH");
	search.setBounds(170,340,90,25);
	search.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			SearchSQL();
			
		}
		
	});
	add(search);
	
	
	edit = new JButton("EDIT");
	edit.setBounds(280,340,70,25);
	edit.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			EditSQL();
			
		}
		
	});
	add(edit);
	
	
	delete = new JButton("DELETE");
	delete.setBounds(370,340,90,25);
	delete.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			DeleteSQL();
		}
		
	});
	add(delete);
	
	
	
	exit = new JButton("EXIT");
	exit.setBounds(480,340,90,25);
	exit.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			CloseMe();
		}
		
	});
	add(exit);
	
	
}


//METHOD FOR ADD KONOYAROU!
public void checkInputs(){
	
	User = user.getText().toString();
	Pass = pass.getText().toString();
	Q = question.getText().toString();
	A = answer.getText().toString();
	
	
	if(User.equals("")||Pass.equals("")||Q.equals("")||A.equals("")){
		
		JOptionPane.showMessageDialog(null,"Please Fill All Fields!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

		
	}else{
		
		
		
		int ans = JOptionPane.showConfirmDialog(null,"Add Account?");
		
		
		
		if(ans == JOptionPane.YES_OPTION){
			
			InsertSQL();
		}
	}
		
		
}


public void InsertSQL(){
	  
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

String check = "SELECT username FROM users WHERE username="+"\""+User+"\"";

java.sql.Statement stmt = conn.createStatement();

ResultSet rs = stmt.executeQuery(check);
 
	
	if(rs.first()){
		 
		 JOptionPane.showMessageDialog(null,"Username Already Exist!"+"\n"+"Please Choose Different One!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		
	 
 }else{
	 
	 String query = "INSERT INTO users (username, password, question, answer, block, user_type)" + "values(?,?,?,?,?,?)";

	 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);

	 ps.setString(1, User);

	  ps.setString(2, Pass); 

	  ps.setString(3, Q.toLowerCase()); 

	  ps.setString(4, A.toLowerCase()); 
	  
	  ps.setInt(5, 0); 
	  
	  ps.setString(6, userType.getSelectedItem().toString()); 


	 ps.execute();

	  JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
	 
	  ClearFields();
	
}



conn.close();

}catch (Exception moriel){

 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

	} 




} 



public void ClearFields(){
	 user.setText("");
	  pass.setText("");
	  question.setText("");
	  userType.setSelectedItem("");
	  answer.setText("");
}



//METHOD FOR SEARCHING ACCOUNT BAKAYAROU!
public void SearchSQL(){
	  
	String User = JOptionPane.showInputDialog(null,"Enter Your Username"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.QUESTION_MESSAGE);
	
	String Pass = JOptionPane.showInputDialog(null,"Enter Your Password"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.QUESTION_MESSAGE);
	
	
	String check = "SELECT * FROM users WHERE username="+"\""+User+"\""+"AND password="+"\""+Pass+"\"";

	
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


		 java.sql.Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery(check);
		 
		 int counter = 0;

			 while(rs.next()){

				this.user.setText(rs.getString("username"));
				this.pass.setText(rs.getString("password"));
				this.userType.setSelectedItem(rs.getString("user_type"));
				this.question.setText(rs.getString("question"));
				this.answer.setText(rs.getString("answer"));
				
				this.user.enable(false);
				
		counter++;
			 }
			 

			 if(counter<1){
				 
				 JOptionPane.showMessageDialog(null,"Account Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }

		conn.close();

		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 

			} 

} 


//METHOD FOR EDITING NANDE KUSO!
public void EditSQL(){

	

	int ans = JOptionPane.showConfirmDialog(null,"Save Changes?");
	
	
	if(ans == JOptionPane.YES_OPTION){
		
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

			String query = "UPDATE users SET password=?, question=?,answer=?, user_type=? WHERE username= "+"\""+this.user.getText().toString()+"\"";

			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			
			
			ps.setString(1, this.pass.getText().toString());
			ps.setString(2, this.question.getText().toString().toLowerCase());
			ps.setString(3, this.answer.getText().toString().toLowerCase());
			ps.setString(4, this.userType.getSelectedItem().toString());
			
			ps.execute();

			 JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 

			conn.close();
			
			ClearFields();
			this.user.enable(true);

			}catch (Exception moriel){

			 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

				} 
		
		
	}else if(ans==JOptionPane.NO_OPTION){
		
		
	}else if(ans ==JOptionPane.CANCEL_OPTION){
		
	}else if(ans ==JOptionPane.CLOSED_OPTION){
		
	}
	  
	
} 


//METHOD FOR DELETING CHIKUSHO!
public void DeleteSQL(){
	
	int ans = JOptionPane.showConfirmDialog(null,"Are You Sure To Delete This Account?");
	
if(ans == JOptionPane.YES_OPTION){
	
	
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

		String query = "DELETE FROM users WHERE username ="+"\""+this.user.getText().toString()+"\""+" AND password="+"\""+this.pass.getText().toString()+"\"";

		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
		
		
		ps.execute();

		 JOptionPane.showMessageDialog(null,"Account Deleted!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 

		conn.close();
		
		ClearFields();
		this.user.enable(true);

		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

			} 
		
		
	}else if(ans==JOptionPane.NO_OPTION){
		
		
	}else if(ans ==JOptionPane.CANCEL_OPTION){
		
	}else if(ans ==JOptionPane.CLOSED_OPTION){
		
	}
	
	
}


//CODE PARA SA JTABLE NATIN NAHIRAPAN TALAGA AKO DITO CHIKUSHO!
public void tableModel(){
	
	 String url = "jdbc:mysql://localhost:3306/h_m_s";
   String userid = "root";
   String password = "";
   String sql;
  
	 try  {
   	
   	Connection connection = (Connection) DriverManager.getConnection( url, userid, password );
       Statement stmt = connection.createStatement();
      	  
       sql = "SELECT username, user_type FROM users";
       ResultSet rs = stmt.executeQuery(sql);
       table = new JTable(model);
 		 JScrollPane scrollPane = new JScrollPane(table);
 		 table.setPreferredScrollableViewportSize(new Dimension(500, 70));
 		 table.setFillsViewportHeight(true);
 		 table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 		 table.setBackground(Color.LIGHT_GRAY);
 		 scrollPane.setBounds(565,90,170,170);
 		 scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
 		 scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
 		 add(scrollPane);
 		 table.setModel(DbUtils.resultSetToTableModel(rs));
 		 table.addMouseListener(this);
       
  
	 }catch(Exception moriel){
		 
		 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
	 }
		
		
  
}

//METHOD FOR ALTERNETE SEARCHING NANDE KUSO!
public void AlternateSearchSQL(){
	
	 String url = "jdbc:mysql://localhost:3306/h_m_s";
     String userid = "root";
     String password = "";
     String sql;
 	
	 try  {
     	
     	Connection connection = (Connection) DriverManager.getConnection( url, userid, password );
         Statement stmt = connection.createStatement();
        	  
        	  sql = "SELECT username, user_type FROM users WHERE username LIKE "+"\""+user.getText().toString()+"%\"";
        	  ResultSet rs = stmt.executeQuery(sql);
        	   table.setModel(DbUtils.resultSetToTableModel(rs));
         
    
	 }catch(Exception moriel){
		 
		 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
	 }
	 
	
}


//METHOD PARA SA TABLE ROW CLICKING
public void tableClickSearch(){
	
String check = "SELECT username, user_type FROM users WHERE username="+"\""+table.getValueAt(table.getSelectedRow(), 0)+"\"";

	
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


		 java.sql.Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery(check);
		 
		 int counter = 0;

			 while(rs.next()){

				this.user.setText(rs.getString("username"));
				this.userType.setSelectedItem(rs.getString("user_type"));
				
				
		counter++;
			 }
			 

			 if(counter<1){
				 
				 JOptionPane.showMessageDialog(null,"Account Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }

		conn.close();

		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 

			} 
	 
	
}



//METHOD PARA SA USERNAME SEARCHING WHEN TYPING
public void userSearch(){
	
String check = "SELECT username, user_type FROM users WHERE username = "+"\""+user.getText().toString()+"\"";


	userType.setSelectedItem("");
	
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


		 java.sql.Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery(check);
		 
		 int counter = 0;

			 while(rs.next()){

				this.user.setText(rs.getString("username"));
				this.userType.setSelectedItem(rs.getString("user_type"));
				
				
		counter++;
			 }
			 

			 if(counter<1){
				 
				 //JOptionPane.showMessageDialog(null,"Account Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }

		conn.close();

		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 

			} 
	 
	
}



//METHOD PARA SA SEARCHING TRU USER TYPE SELECTION
public void userTypeSearch(){
	
	 String url = "jdbc:mysql://localhost:3306/h_m_s";
     String userid = "root";
     String password = "";
     String sql;
 	
	 try  {
     	
     	Connection connection = (Connection) DriverManager.getConnection( url, userid, password );
         Statement stmt = connection.createStatement();
        	  
        	  sql = "SELECT username, user_type FROM users WHERE user_type LIKE "+"\""+userType.getSelectedItem().toString()+"%\"";
        	  ResultSet rs = stmt.executeQuery(sql);
        	   table.setModel(DbUtils.resultSetToTableModel(rs));
         
    
	 }catch(Exception moriel){
		 
		 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
	 }
	 
	
}



//METHOD FOR EXIT BUTTON
public void CloseMe(){
	this.dispose();
}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		userTypeSearch();
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		tableClickSearch();
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		userSearch();
		AlternateSearchSQL();
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		userSearch();
		AlternateSearchSQL();
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		userSearch();
		AlternateSearchSQL();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
