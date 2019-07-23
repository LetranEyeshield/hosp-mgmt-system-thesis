package Moriel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Consultation extends JFrame implements ActionListener, Printable, MouseListener, KeyListener{
	
	JLabel labels,title;
	
	
	JTextField CID, date, FN, MN, LN, age, address, doctor, checkFee;
	
	JButton clear, edit, exit, print;
	
	JTable table;

	DefaultTableModel model;
	
	//VARS FOR BIRTH DATE
	String BM[] = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	String getBMonth;

	String BD[] = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	JComboBox BMonth, BDay;
	String getBDay;

	JTextField BYr;
	
	
	String G[] = {"", "Male", "Female"};
	String setGender, getGender;
	JComboBox gender;
	
	String S[] = {"", "Single", "Married", "Widowed","Divorced","Separated"};
	String getStatus;
	JComboBox status;
	
	String O[] = {"", "Old", "New", "Senior"};
	String getOrient;
	JComboBox orient;
	
	
	JTextArea reason;
	JScrollPane scroll;
	
	
	//VARS PARA SA PRINTING
	Graphics2D g2;
	Image logo;
	
	
	//COLORS PARA SA CHECKING INPUTS
		Color inputFailed = Color.RED;
		Color changeInput = Color.WHITE;
		Color inputPass = Color.WHITE;
		Color returnInput = Color.BLACK;
	
		//PREPARE THE CLASS PARA SA LAST NAME SEARCHING
		Moriel Kim = new Moriel();
		
	public Consultation(){
		super("HOSPITAL MANAGEMENT SYSTEM");
		setLayout(null);
		
		setTitle();
		setLabels();
		setFields();
		setButtons();
		setPanels();
		setLine();
		tableModel();
		setBackground();
		
		
	}
	
	
public void setTitle(){
		
		title = new JLabel("                   " +
				"                      " +
				"                     " +
				"     CONSULTATION");
		title.setBounds(0,0,1000,25);
		title.setOpaque(true);
		title.setBackground(Color.green);
		title.setFont(new Font("OLD ENGLISH MT",Font.BOLD,20));
		add(title);
	}


public void prepareLabels(String jammi, int xpos, int ypos, int width, int height){
	
	labels = new JLabel(jammi);
	labels.setBounds(xpos,ypos,width,height);
	labels.setOpaque(true);
	labels.setBackground(Color.BLUE);
	labels.setForeground(Color.WHITE);
	labels.setFont(new Font("Tahoma",Font.PLAIN,14));
	add(labels);
	
	
}


public void setBackground(){

	JLabel background = new JLabel(new ImageIcon("res/consultation.png"));
	 background.setBounds(0,0,1000,700);
	 background.setBackground(new Color(0,0,255));
	// background.setBackground(Color.LIGHT_GRAY);
	 background.setOpaque(true);
	 add(background);
	 
}


public void setLabels(){
	
	
	prepareLabels(" Patient ID:",30,80,90,18);
	prepareLabels(" First Name:",30,110,90,18);
	prepareLabels(" Middle Name:",30,140,90,18);
	prepareLabels(" Last Name:",30,170,90,18);
	prepareLabels(" Birthday:",30,200,90,18);
	prepareLabels(" Age:",30,230,90,18);
	prepareLabels(" Gender:",30,260,90,20);
	prepareLabels(" Address:",30,290,90,18);
	prepareLabels(" Civil Status:",30,320,90,18);
	
	prepareLabels(" Date:",30,390,150,18);
	prepareLabels(" Doctor's Name:",30,420,150,18);
	prepareLabels(" Orientation:",30,450,150,18);
	prepareLabels(" Check-Up Fee :",30,480,150,18);
	prepareLabels(" Reason For Check-Up:",30,510,150,18);
	
	
}

public void setPanels(){
	
	JLabel panel1 = new JLabel();
	panel1.setBounds(20,70,400,280);
	panel1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel1);
	
	JLabel panel2 = new JLabel();
	panel2.setBounds(440,70,550,500);
	panel2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel2);
	
	JLabel panelLabel1 = new JLabel("  Personal Information ");
	panelLabel1.setBounds(20,50,130,20);
	panelLabel1.setOpaque(true);
	panelLabel1.setBackground(Color.CYAN);
	add(panelLabel1);
	

	JLabel panelLabel2 = new JLabel("  Other Information ");
	panelLabel2.setBounds(20,360,130,20);
	panelLabel2.setOpaque(true);
	panelLabel2.setBackground(Color.CYAN);
	add(panelLabel2);
	
	JLabel panel3 = new JLabel();
	panel3.setBounds(20,380,400,190);
	panel3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel3);
	
	
}


public void setFields(){
	
	CID = new JTextField();
	CID.setBounds(130,80,200,18);
	//CID.setEditable(false);
	CID.addKeyListener(this);
	add(CID);
	
	FN = new JTextField();
	FN.setBounds(130,110,200,18);
	add(FN);
	
	MN = new JTextField();
	MN.setBounds(130,140,200,18);
	add(MN);
	
	LN = new JTextField();
	LN.setBounds(130,170,200,18);
	LN.addKeyListener(Kim);
	add(LN);
	

		//MGA FIELDS PARA SA BIRTHDAY
		BMonth = new JComboBox(BM);
		BMonth.setBounds(130,200,90,18);
		BMonth.setSelectedIndex(0);
		BMonth.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent jammi){

		if(jammi.getSource()==BMonth){
		JComboBox cb = (JComboBox)jammi.getSource();
		getBMonth = (String)cb.getSelectedItem();

	

		}

			}
			}
			); 
		add(BMonth);



		BDay = new JComboBox(BD);
		BDay.setBounds(230,200,40,18);
		BDay.setSelectedIndex(0);
		BDay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent moriel){

		if(moriel.getSource()==BDay){
		JComboBox cb = (JComboBox)moriel.getSource();
		getBDay = (String)cb.getSelectedItem();

		

		}

			}
			}
			); 
		add(BDay);


			
			BYr = new JTextField("2017");
			BYr.setBounds(280,200,50,18);
			add(BYr);
			
			
			
			gender = new JComboBox(G);
			gender.setBounds(130,260,200,18);
			gender.setSelectedIndex(0);
			gender.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent moriel){

			if(moriel.getSource()==gender){
			JComboBox cb = (JComboBox)moriel.getSource();
			getGender = (String)cb.getSelectedItem();

		

			}

				}
				}
				); 
			add(gender);
			
			
			
			status = new JComboBox(S);
			status.setBounds(130,320,200,18);
			status.setSelectedIndex(0);
			status.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent moriel){

			if(moriel.getSource()==status){
			JComboBox cb = (JComboBox)moriel.getSource();
			getStatus = (String)cb.getSelectedItem();

			

			}

				}
				}
				); 
			add(status);
			
			
			date = new JTextField("Jamuary 22, 2017");
			date.setBounds(190,390,220,18);
			add(date);
			
			
			orient = new JComboBox(O);
			orient.setBounds(190,450,220,18);
			orient.setSelectedIndex(0);
			orient.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent moriel){

			if(moriel.getSource()==orient){
			JComboBox cb = (JComboBox)moriel.getSource();
			getOrient = (String)cb.getSelectedItem();

			

			}

				}
				}
				); 
			add(orient);
			
			
			//END OF COMBO STUFFS NANDE KUSO
			
			age = new JTextField();
			age.setBounds(130,230,200,18);
			add(age);
			
			address = new JTextField();
			address.setBounds(130,290,200,18);
			add(address);
			
			doctor = new JTextField();
			doctor.setBounds(190,420,220,18);
			add(doctor);
			
			checkFee = new JTextField("500");
			checkFee.setBounds(190,480,220,18);
			add(checkFee);
			
			
			reason=new JTextArea();
			//Remarks.setBounds(100,505,250,240);
			reason.setToolTipText("Please use 'Enter Key' to use line break!");
			reason.setLineWrap(true);
			reason.setWrapStyleWord(true);
			//add(Remarks);
			scroll=new JScrollPane(reason);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			//scrollRemarks.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll.setBounds(190,510,220,50);
			add(scroll);
			
			
	
}


public void setButtons(){
	
	
	
	clear = new JButton("CLEAR FIELDS");
	clear.setBounds(140,600,120,25);
	clear.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			ClearFields();
			
		}
		
	});
	add(clear);
	
	
	edit = new JButton("UPDATE");
	edit.setBounds(280,600,80,25);
	edit.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			checkInputs();
			
		}
		
	});
	add(edit);
	
	
	
	print = new JButton("PRINT");
	print.setBounds(380,600,90,25);
	print.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			printMe();
		}
		
	});
	add(print);
	
	
	exit = new JButton("EXIT");
	exit.setBounds(490,600,90,25);
	exit.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			CloseMe();
		}
		
	});
	add(exit);
	
	
	
	
}


public void setLine(){
	
	JLabel line = new JLabel("");
	line.setBounds(0,580,1000,1);
	line.setOpaque(true);
	line.setBackground(Color.RED);
	add(line);
}



//CHECKING USER INPUTS
public void checkInputs(){
	
	if(date.getText().toString().equals("")){
		date.setBackground(inputFailed);
		date.setForeground(changeInput);
	}else{
		 
		date.setBackground(inputPass);
		date.setForeground(returnInput);
	}
	
	
	if(FN.getText().toString().equals("")){
		FN.setBackground(inputFailed);
		FN.setForeground(changeInput);
	}else{
		 
		FN.setBackground(inputPass);
		FN.setForeground(returnInput);
	}
	
	if(MN.getText().toString().equals("")){
		MN.setBackground(inputFailed);
		MN.setForeground(changeInput);
	}else{
		 
		MN.setBackground(inputPass);
		MN.setForeground(returnInput);
	}
	
	if(LN.getText().toString().equals("")){
		LN.setBackground(inputFailed);
		LN.setForeground(changeInput);
	}else{
		 
		LN.setBackground(inputPass);
		LN.setForeground(returnInput);
	}
	
	if(age.getText().toString().equals("")){
		age.setBackground(inputFailed);
		age.setForeground(changeInput);
	}else{
		 
		age.setBackground(inputPass);
		age.setForeground(returnInput);
	}
	
	if(address.getText().toString().equals("")){
		address.setBackground(inputFailed);
		address.setForeground(changeInput);
	}else{
		 
		address.setBackground(inputPass);
		address.setForeground(returnInput);
	}
	
	if(doctor.getText().toString().equals("")){
		doctor.setBackground(inputFailed);
		doctor.setForeground(changeInput);
	}else{
		 
		doctor.setBackground(inputPass);
		doctor.setForeground(returnInput);
	}
	
	if(checkFee.getText().toString().equals("")){
		checkFee.setBackground(inputFailed);
		checkFee.setForeground(changeInput);
	}else{
		 
		checkFee.setBackground(inputPass);
		checkFee.setForeground(returnInput);
	}
	
	if(reason.getText().toString().equals("")){
		reason.setBackground(inputFailed);
		reason.setForeground(changeInput);
	}else{
		 
		reason.setBackground(inputPass);
		reason.setForeground(returnInput);
	}
	
	if(BMonth.getSelectedItem().toString().equals("")){
		BMonth.setBackground(inputFailed);
		BMonth.setForeground(changeInput);
	}else{
		 
		BMonth.setBackground(inputPass);
		BMonth.setForeground(returnInput);
	}
	
	if(BDay.getSelectedItem().toString().equals("")){
		BDay.setBackground(inputFailed);
		BDay.setForeground(changeInput);
	}else{
		 
		BDay.setBackground(inputPass);
		BDay.setForeground(returnInput);
	}
	
	if(BYr.getText().toString().equals("")){
		BYr.setBackground(inputFailed);
		BYr.setForeground(changeInput);
	}else{
		 
		BYr.setBackground(inputPass);
		BYr.setForeground(returnInput);
	}
	
	if(gender.getSelectedItem().toString().equals("")){
		gender.setBackground(inputFailed);
		gender.setForeground(changeInput);
	}else{
		 
		gender.setBackground(inputPass);
		gender.setForeground(returnInput);
	}
	
	if(status.getSelectedItem().toString().equals("")){
		status.setBackground(inputFailed);
		status.setForeground(changeInput);
	}else{
		 
		status.setBackground(inputPass);
		status.setForeground(returnInput);
	}
	
	if(orient.getSelectedItem().toString().equals("")){
		orient.setBackground(inputFailed);
		orient.setForeground(changeInput);
	}else{
		 
		orient.setBackground(inputPass);
		orient.setForeground(returnInput);
	}
	
	if(date.getText().toString().equals("") || FN.getText().toString().equals("") ||  MN.getText().toString().equals("") || LN.getText().toString().equals("") 
			|| age.getText().toString().equals("") || address.getText().equals("") ||
			doctor.getText().toString().equals("") ||  checkFee.getText().toString().equals("") || reason.getText().toString().equals("")
			|| BMonth.getSelectedItem().equals("") || BDay.getSelectedItem().equals("") || BYr.getText().toString().equals("")
			|| gender.getSelectedItem().equals("") || status.getSelectedItem().equals("") || orient.getSelectedItem().equals("")
			){
		
		JOptionPane.showMessageDialog(null,"Please Fill All Fields Required In Red Color!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		
	}
	
	else{
		
		EditSQL();
		InsertSQL();
		
		
	}
	
}



//METHOD FOR ADD KONOYAROU!
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
	
	 
	 
	 String query = "INSERT INTO consultation_history (patient_id, date, first_name,middle_name,last_name,birth_month,birth_day,birth_year,age,gender,address, civil_status, doctor,reason,orientation,fee)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);

	 ps.setString(1, CID.getText().toString());

	 ps.setString(2, date.getText().toString());
	 
	 ps.setString(3, FN.getText().toString()); 

	 ps.setString(4, MN.getText().toString()); 

	 ps.setString(5, LN.getText().toString()); 
	  
	 ps.setString(6, getBMonth);

	 ps.setString(7, getBDay); 

	  ps.setString(8, BYr.getText().toString()); 

	  ps.setString(9, age.getText().toString());
	  
	  ps.setString(10, getGender);

	  ps.setString(11, address.getText().toString()); 

	  ps.setString(12, getStatus); 

	  ps.setString(13, doctor.getText().toString());
	  
	  ps.setString(14, reason.getText().toString());

	  ps.setString(15, getOrient); 

	  ps.setString(16, checkFee.getText().toString()); 

	 

	  ps.execute();

	//  JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
	 



conn.close();

}catch (Exception moriel){

 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

	} 




} 




//MUNTIK KO NG HINDI MAKITA ANG METHOD NA ITO SA DAMI NG CODES
//MORIEL!!!! HUHU!
public void ClearFields(){
	
	
	  //CID.setText("");
	  date.setText("");
	  FN.setText("");
	  MN.setText("");
	  LN.setText("");
	  BMonth.setSelectedItem(""); 
	  BDay.setSelectedItem("");
	  BYr.setText("");
	  age.setText("");
	  gender.setSelectedItem("");
	  address.setText("");
	  status.setSelectedItem("");
	  doctor.setText("");
	  reason.setText("");
	  orient.setSelectedItem("");
	  checkFee.setText("");
	
	
}



//METHOD FOR SEARCHING ACCOUNT BAKAYAROU!
public void SearchSQL(){
	  
	ClearFields();
	
	String check = "SELECT * FROM consultation WHERE patient_id="+"\""+CID.getText().toString()+"\"";

	
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

				 
				 CID.setText(rs.getString("patient_id"));
				 
				 date.setText(rs.getString("date"));

				  FN.setText(rs.getString("first_name"));
				  
				  MN.setText(rs.getString("middle_name"));
				  
				  LN.setText(rs.getString("last_name"));

				  BMonth.setSelectedItem(rs.getString("birth_month")); 

				  BDay.setSelectedItem(rs.getString("birth_day"));

				  BYr.setText(rs.getString("birth_year"));
				  
				  age.setText(rs.getString("age"));

				  gender.setSelectedItem(rs.getString("gender"));
				  
				  address.setText(rs.getString("address"));

				  status.setSelectedItem(rs.getString("civil_status"));
				  
				  doctor.setText(rs.getString("doctor"));
				 
				 reason.setText(rs.getString("reason"));
				 
				 orient.setSelectedItem(rs.getString("orientation"));
				 
				 checkFee.setText(rs.getString("fee"));
				
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


//METHOD FOR SEARCHING TABLE ROW CLICKING!
public void tableClickSearch(){
	  
	
	//String cid = JOptionPane.showInputDialog(null,"Enter Patient ID"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.QUESTION_MESSAGE);
	
	
	String check = "SELECT * FROM consultation WHERE patient_id="+"\""+table.getValueAt(table.getSelectedRow(), 0)+"\"";

	
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

				 
				 CID.setText(rs.getString("patient_id"));
				 
				 date.setText(rs.getString("date"));

				  FN.setText(rs.getString("first_name"));
				  
				  MN.setText(rs.getString("middle_name"));
				  
				  LN.setText(rs.getString("last_name"));

				  BMonth.setSelectedItem(rs.getString("birth_month")); 

				  BDay.setSelectedItem(rs.getString("birth_day"));

				  BYr.setText(rs.getString("birth_year"));
				  
				  age.setText(rs.getString("age"));

				  gender.setSelectedItem(rs.getString("gender"));
				  
				  address.setText(rs.getString("address"));

				  status.setSelectedItem(rs.getString("civil_status"));
				  
				  doctor.setText(rs.getString("doctor"));
				 
				 reason.setText(rs.getString("reason"));
				 
				 orient.setSelectedItem(rs.getString("orientation"));
				 
				 checkFee.setText(rs.getString("fee"));
				
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
			String query = "UPDATE consultation SET date=?, first_name=?, middle_name=?, last_name=?, birth_month=?, birth_day=?, birth_year=?, age=?, gender=?, address=?, civil_status=?, doctor=?, reason=?, orientation=?, fee=? WHERE patient_id = "+"\""+this.CID.getText().toString()+"\"";

			
			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			
			ps.setString(1, date.getText().toString()); 
			
			 ps.setString(2, FN.getText().toString()); 

			 ps.setString(3, MN.getText().toString()); 

			 ps.setString(4, LN.getText().toString()); 
			  
			 ps.setString(5, (String) BMonth.getSelectedItem());

			 ps.setString(6, (String) BDay.getSelectedItem()); 

			  ps.setString(7, BYr.getText().toString()); 

			  ps.setString(8, age.getText().toString());
			  
			  ps.setString(9, (String) gender.getSelectedItem());

			  ps.setString(10, address.getText().toString()); 

			  ps.setString(11, (String) status.getSelectedItem()); 

			  ps.setString(12, doctor.getText().toString());
			  
			  ps.setString(13, reason.getText().toString());

			  ps.setString(14, (String) orient.getSelectedItem()); 

			  ps.setString(15, checkFee.getText().toString()); 

			  
			
		
			
			ps.execute();

			 JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 

			conn.close();
			
			//this.user.enable(true);

			}catch (Exception moriel){

			 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

				} 
		
		
	}else if(ans==JOptionPane.NO_OPTION){
		
		
	}else if(ans ==JOptionPane.CANCEL_OPTION){
		
	}else if(ans ==JOptionPane.CLOSED_OPTION){
		
	}
	
	
	String URL = "jdbc:mysql://localhost:3306/h_m_s";
	String userid = "root";
	String password = "";
	String sql;

	try  {
		
		Connection connection = (Connection) DriverManager.getConnection( URL, userid, password );
	    Statement stmt = connection.createStatement();
	   	  
	    sql = "SELECT * FROM consultation";
	    ResultSet rs = stmt.executeQuery(sql);
			
			 table.setModel(DbUtils.resultSetToTableModel(rs));

	}catch (Exception moriel){

			 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

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
		String query = "DELETE FROM consultation WHERE patient_id ="+"\""+this.CID.getText().toString()+"\"";

		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
		
		
	    ps.execute();

		 JOptionPane.showMessageDialog(null,"Record Deleted!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 

		conn.close();
		
		
	
		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

			} 
		
		
	}else if(ans==JOptionPane.NO_OPTION){
		
		
	}else if(ans ==JOptionPane.CANCEL_OPTION){
		
	}else if(ans ==JOptionPane.CLOSED_OPTION){
		
	}


String URL = "jdbc:mysql://localhost:3306/h_m_s";
String userid = "root";
String password = "";
String sql;

try  {
	
	Connection connection = (Connection) DriverManager.getConnection( URL, userid, password );
    Statement stmt = connection.createStatement();
   	  
    sql = "SELECT * FROM consultation";
    ResultSet rs = stmt.executeQuery(sql);
		
		 table.setModel(DbUtils.resultSetToTableModel(rs));

}catch (Exception moriel){

		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

}


ClearFields();

	
	
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
        	  
         sql = "SELECT * FROM consultation";
         ResultSet rs = stmt.executeQuery(sql);
         table = new JTable(model);
   		 JScrollPane scrollPane = new JScrollPane(table);
   		 table.setPreferredScrollableViewportSize(new Dimension(500, 70));
   		 table.setFillsViewportHeight(true);
   		 table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
   		 table.setBackground(Color.LIGHT_GRAY);
   		 scrollPane.setBounds(450,80,530,480);
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
        	  
        	sql = "SELECT * FROM consultation WHERE patient_id LIKE "+"\""+CID.getText().toString()+"%\"";
        	ResultSet rs = stmt.executeQuery(sql);
        	table.setModel(DbUtils.resultSetToTableModel(rs));
         
    
	 }catch(Exception moriel){
		 
		 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
	 }
	 
	
}


//METHOD PARA SA EXIT BUTTON
public void CloseMe(){
	this.dispose();
}



//ITO UNG PAG DRAWING NG MGA STUFFS
//MADUGONG LABANAN TALAGA TO! Moriel!!!
@Override
public int print(Graphics g, PageFormat pf, int page)
		throws PrinterException {
	// TODO Auto-generated method stub
	
	 logo = Toolkit.getDefaultToolkit().getImage("res/logo.png");
	
	 if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

       
        /* Now we perform our rendering */
     
        Image logo = Toolkit.getDefaultToolkit().getImage("res/logo.jpg");
    	
   	 if (page > 0) { /* We have only one page, and 'page' is zero-based */
             return NO_SUCH_PAGE;
         }

         /* User (0,0) is typically outside the imageable area, so we must
          * translate by the X and Y values in the PageFormat to avoid clipping
          */
 
        
         /* Now we perform our rendering */
   	 g.drawImage(logo, 270, 10, 60, 60, this);

     g.setFont(new Font("Tahoma",Font.BOLD,20));
     g.setColor(Color.RED);
     g.drawString(" HOSPITAL MANAGEMENT SYSTEM", 140, 100);
    
     
     g.setFont(new Font("Tahoma",Font.ITALIC,8));
     g.setColor(Color.BLACK);
     g.drawString("Arellano Street, Dagupan City 2400, Pangasinan, Philippines", 180,110);

     //draw line separator
     g.drawString("_______________________________________" +
     		"________________________________________________", 70, 120);
     
     g.setFont(new Font("Tahoma",Font.ITALIC,10));
     g.setColor(Color.BLACK);
     g.drawString("Consultation", 230,135);
     
     
         g.setFont(new Font("Tahoma",Font.PLAIN,8));
         g.setColor(Color.BLACK);
         g.drawString("Patient ID: "+CID.getText().toString(), 100,160);

         g.drawString("Name: "+FN.getText().toString()+" "+MN.getText().toString()+" "+LN.getText().toString(), 100,170);
         
         g.drawString("Date: "+ date.getText().toString() , 100,180);
         
        
         g.drawString("Birthday: "+address.getText().toString(), 100,190);

         
         g.drawString("Age: "+age.getText().toString(), 100,200);
         
         g.drawString("Gender : "+ gender.getSelectedItem(), 100,210);
        
         g.drawString("Address: "+address.getText().toString(), 100,220);

         
         g.drawString("Civil Status: "+status.getSelectedItem(), 100,230);

        
         g.drawString("Doctor's Name: "+doctor.getText().toString(), 100,240);

        
         g.drawString("Orientation: "+orient.getSelectedItem(), 100,250);

       
        
         g.drawString("Check-Up Fee: "+checkFee.getText().toString(), 100,260);


         g.drawString("Reason For Check-Up: "+reason.getText().toString(), 100,270);

        
         
         
         g.setFont(new Font("Tahoma",Font.ITALIC,10));
         g.setColor(Color.BLACK);
         //draw line separator
         g.drawString("_______________________________________" +
         		"_________________________________", 70, 290);
         
       

         g.setFont(new Font("Tahoma",Font.ITALIC,8));
         g.drawString("Email Address: region1mc2003@yahoo.com", 100,305);
         
         g.setFont(new Font("Tahoma",Font.ITALIC,8));
         g.drawString("Telephone Numbers: (075) 515-89-16 ; (075)515-89-18 ; (075)515-89-01", 100,315);

         g.setFont(new Font("Tahoma",Font.ITALIC,8));
         g.drawString("Fax: (075) 523-41-03", 100,325);

      
         
         
         //LINE SEPARATOR FOR CUTTING NANDE KUSO
         g.setFont(new Font("Tahoma",Font.ITALIC,8));
         g.setColor(Color.BLACK);
         //draw line separator
         g.drawString("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ " +
         		"_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ " +
         		"_ _ _ _ _ _ _ _ _ _ _ _", 70, 355);


         
         
         g.drawImage(logo, 270, 375, 60, 60, this);

         g.setFont(new Font("Tahoma",Font.BOLD,20));
         g.setColor(Color.RED);
         g.drawString(" HOSPITAL MANAGEMENT SYSTEM", 140, 455);
        
         
         g.setFont(new Font("Tahoma",Font.ITALIC,8));
         g.setColor(Color.BLACK);
         g.drawString("Arellano Street, Dagupan City 2400, Pangasinan, Philippines", 180,465);

         //draw line separator
         g.drawString("_______________________________________" +
         		"________________________________________________", 70, 475);
         
         g.setFont(new Font("Tahoma",Font.ITALIC,10));
         g.setColor(Color.BLACK);
         g.drawString("Consultation", 230,495);
         
         
             g.setFont(new Font("Tahoma",Font.PLAIN,8));
             g.setColor(Color.BLACK);
             g.drawString("Patient ID: "+CID.getText().toString(), 100,515);

             g.drawString("Name: "+FN.getText().toString()+" "+MN.getText().toString()+" "+LN.getText().toString(), 100,525);
             
             g.drawString("Date: "+ date.getText().toString() , 100,535);
             
            
             g.drawString("Birthday: "+address.getText().toString(), 100,545);

             
             g.drawString("Age: "+age.getText().toString(), 100,555);
             
             g.drawString("Gender : "+ gender.getSelectedItem(), 100,565);
            
             g.drawString("Address: "+address.getText().toString(), 100,575);

             
             g.drawString("Civil Status: "+status.getSelectedItem(), 100,585);

            
             g.drawString("Doctor's Name: "+doctor.getText().toString(), 100,595);

            
             g.drawString("Orientation: "+orient.getSelectedItem(), 100,605);

           
            
             g.drawString("Check-Up Fee: "+checkFee.getText().toString(), 100,615);


             g.drawString("Reason For Check-Up: "+reason.getText().toString(), 100,625);

            
             
             
             g.setFont(new Font("Tahoma",Font.ITALIC,10));
             g.setColor(Color.BLACK);
             //draw line separator
             g.drawString("_______________________________________" +
             		"_________________________________", 70, 640);
           

             g.setFont(new Font("Tahoma",Font.ITALIC,8));
             g.drawString("Email Address: region1mc2003@yahoo.com", 100,655);
             
             g.setFont(new Font("Tahoma",Font.ITALIC,8));
             g.drawString("Telephone Numbers: (075) 515-89-16 ; (075)515-89-18 ; (075)515-89-01", 100,665);

             g.setFont(new Font("Tahoma",Font.ITALIC,8));
             g.drawString("Fax: (075) 523-41-03", 100,675);

         
         
         
         
        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;

}

//ITO UNG PRINT METHOD HEHE...
public void printMe(){ 
	
	  PrinterJob job = PrinterJob.getPrinterJob();
         job.setPrintable(this);
         boolean ok = job.printDialog();
         if (ok) {
             try {
                  job.print();
             } catch (PrinterException morielKim) {
              JOptionPane.showMessageDialog(null, morielKim.getMessage());
             }
         }
	
}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		AlternateSearchSQL();
		
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
		SearchSQL();
		AlternateSearchSQL();	
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		SearchSQL();
		AlternateSearchSQL();
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		SearchSQL();
		AlternateSearchSQL();
	}


	
	//NEW CLASS PARA SA LAST NAME SEARCHING
	public class Moriel implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			AlternateSearchSQL();
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			AlternateSearchSQL();
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			AlternateSearchSQL();
		}
		
		
			
			//METHOD FOR ALTERNATE SEARCHING NANDE KUSO!
			public void AlternateSearchSQL(){
				
				 String url = "jdbc:mysql://localhost:3306/h_m_s";
			     String userid = "root";
			     String password = "";
			     String sql;
			    
			   
			 	
				 try  {
			     	
			     	Connection connection = (Connection) DriverManager.getConnection( url, userid, password );
			         Statement stmt = connection.createStatement();
			        	  
			        	//sql = "SELECT * FROM admission WHERE first_name LIKE "+"\""+alternateSearch.getText().toString()+"%\""+"OR last_name LIKE "+"\""+alternateSearch.getText().toString()+"%\""+"OR patient_id LIKE "+"\""+alternateSearch.getText().toString()+"%\"";
			        	
			         sql = "SELECT * FROM consultation WHERE last_name LIKE "+"\""+LN.getText().toString()+"%\"";
			     
			        	ResultSet rs = stmt.executeQuery(sql);
			        	table.setModel(DbUtils.resultSetToTableModel(rs));
			         
			    
				 }catch(Exception moriel){
					 
					// JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
					 
				 }
				 
			}
			
	}
		
	}
	
	
	
	
	
	
	
	
	
	

