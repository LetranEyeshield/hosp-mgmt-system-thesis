package Moriel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import Moriel.Consultation.Moriel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Doctor extends JFrame implements ActionListener, Printable, MouseListener, KeyListener{
	
	JLabel labels,title, pixBox, doctor_count;
	
	Random rand_id = new Random();
	int checkID;
	
	JTextField DID, name, special, age, religion, nationality, email, addr, SCharge;
	
	JButton add, clear, edit, uploadPic, medicalCert, sched, exit;
	
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
	String getGender;
	JComboBox gender;
	
	
	//VARs PARA SA PAGKUHA NG PIX AT PATH NITO
	String photo;
	ImageIcon img;
	JFileChooser chooser;
	FileNameExtensionFilter filter;
	
	
	//COLORS PARA SA CHECKING INPUTS
	Color inputFailed = Color.RED;
	Color changeInput = Color.WHITE;
	Color inputPass = Color.WHITE;
	Color returnInput = Color.BLACK;
		
	

	//VARS PARA SA PRINTING
	Graphics2D g2;
	Image logo;
	Font font1, font2, font3, font4, font5;
	
	
	//PREPARE THE CLASS PARA SA DOCTOR NAME SEARCHING
	Moriel Kim = new Moriel();
	
	public Doctor(){
		super("HOSPITAL MANAGEMENT SYSTEM");
		setLayout(null);
		
		setTitle();
		setLabels();
		setFields();
		setButtons();
		setPanels();
		setLine();
		tableModel();
		setPixBox();
		setBackground();
		getDoctorCount();
		
	}
	
	
public void setTitle(){
		
		title = new JLabel("                     " +
				"                                " +
				"DOCTOR INFORMATION");
		title.setBounds(0,0,1200,25);
		title.setOpaque(true);
		title.setBackground(Color.blue);
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

	JLabel background = new JLabel(new ImageIcon("res/doctor.png"));
	 background.setBounds(0,0,1200,600);
	 background.setBackground(new Color(0,0,255));
	// background.setBackground(Color.LIGHT_GRAY);
	 background.setOpaque(true);
	 add(background);
	 
}

public void setLabels(){
	
	prepareLabels(" Doctor ID:",30,70,160,18);
	prepareLabels(" Doctor's Name:",30,100,160,18);
	prepareLabels(" Doctor's Specialization:",30,130,160,18);
	prepareLabels(" Birthday:",30,160,160,18);
	prepareLabels(" Age:",30,190,160,18);
	prepareLabels(" Gender:",30,220,160,18);
	prepareLabels(" Religion:",30,250,160,18);
	prepareLabels(" Nationality:",30,280,160,18);
	prepareLabels(" Address:",30,310,160,18);
	prepareLabels(" E-Mail:",30,340,160,18);
	prepareLabels(" Service Charge Per Day:",30,370,160,18);
	
	
	// label para  kunin ung number of doctors mamaya obvious diba? haha!
		doctor_count = new JLabel("No. Of Doctors ");
		doctor_count.setBounds(610,370,170,20);
		doctor_count.setOpaque(true);
		doctor_count.setBackground(Color.CYAN);
		add(doctor_count);
	
}


public void setPanels(){
	
	JLabel panel1 = new JLabel();
	panel1.setBounds(20,60,570,350);
	panel1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel1);
	
	JLabel panel2 = new JLabel();
	panel2.setBounds(600,60,580,350);
	panel2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel2);
	
	
}


public void setFields(){
	
	DID = new JTextField();
	DID.setBounds(200,70,200,18);
	//DID.setEditable(false);
	DID.addKeyListener(this);
	add(DID);
	
	name = new JTextField();
	name.setBounds(200,100,200,18);
	name.addKeyListener(Kim);
	add(name);
	
	special = new JTextField();
	special.setBounds(200,130,200,18);
	add(special);
	
	religion = new JTextField();
	religion.setBounds(200,250,200,18);
	add(religion);
	
	nationality = new JTextField();
	nationality.setBounds(200,280,200,18);
	add(nationality);
	
	addr = new JTextField();
	addr.setBounds(200,310,200,18);
	add(addr);
	
	email = new JTextField();
	email.setBounds(200,340,200,18);
	add(email);
	
	SCharge = new JTextField("500");
	SCharge.setBounds(200,370,200,18);
	add(SCharge);
	
		//MGA FIELDS PARA SA BIRTHDAY
		BMonth = new JComboBox(BM);
		BMonth.setBounds(200,160,90,18);
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
		BDay.setBounds(300,160,40,18);
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
			BYr.setBounds(350,160,50,18);
			add(BYr);
			
			
			
			gender = new JComboBox(G);
			gender.setBounds(200,220,200,18);
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
			
			
			
			age = new JTextField();
			age.setBounds(200,190,200,18);
			add(age);
			
			
	
}


public void setButtons(){
	

	
	add = new JButton("ADD");
	add.setBounds(70,460,90,25);
	add.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			checkInputs();
		}
		
	});
	add(add);
	
	
	
	edit = new JButton("EDIT");
	edit.setBounds(180,460,70,25);
	edit.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			EditSQL();
			
		}
		
	});
	add(edit);
	
	
	sched = new JButton("SCHEDULE");
	sched.setBounds(270,460,100,25);
	sched.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			DoctorSchedule DS = new DoctorSchedule();
			DS.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			DS.setSize(860,620);
			DS.setVisible(true);
			DS.setResizable(false);
		}
		
	});
	add(sched);
	
	
	
	medicalCert = new JButton("MEDICAL CERTIFICATE");
	medicalCert.setBounds(390,460,160,25);
	medicalCert.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			MedicalCert MC = new MedicalCert();
			MC.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			MC.setSize(910,600);
			MC.setVisible(true);
			MC.setResizable(false);
			
		}
		
	});
	add(medicalCert);
	
	
	clear = new JButton("CLEAR FIELDS");
	clear.setBounds(570,460,120,25);
	clear.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			ClearFields();
			
		}
		
	});
	add(clear);
	
	
	exit = new JButton("EXIT");
	exit.setBounds(710,460,70,25);
	exit.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			CloseMe();
			
		}
		
	});
	add(exit);
	
	
	
	
}



public void setPixBox(){
	
	pixBox = new JLabel();
	pixBox.setBorder(BorderFactory.createLineBorder(Color.RED));
	pixBox.setBounds(430,70,140,140);
	add(pixBox);
	
	
	uploadPic = new JButton("Upload Photo");
	uploadPic.setBounds(440,220,110,20);
	uploadPic.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent Moriel) {
			// TODO Auto-generated method stub
			

			chooser = new JFileChooser();
			   filter = new FileNameExtensionFilter(
			        "JPG, PNG, & GIF Images", "jpg", "png", "gif");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       
			       img = new ImageIcon( chooser.getSelectedFile().getPath());
			       
			       photo = chooser.getSelectedFile().getPath();
			      
			    }
				
				pixBox.setIcon(img);
		}
		
	});
	add(uploadPic);
	
}



public void setLine(){
	
	JLabel line = new JLabel("");
	line.setBounds(0,430,1200,1);
	line.setOpaque(true);
	line.setBackground(Color.RED);
	add(line);
}



//CHECKING USER INPUTS
public void checkInputs(){
	
	if(name.getText().toString().equals("")){
		name.setBackground(inputFailed);
		name.setForeground(changeInput);
	}else{
		name.setBackground(inputPass);
		name.setForeground(returnInput);
	}
	
	if(special.getText().toString().equals("")){
		special.setBackground(inputFailed);
		special.setForeground(changeInput);
	}else{
		special.setBackground(inputPass);
		special.setForeground(returnInput);
	}
	
	if(age.getText().toString().equals("")){
		age.setBackground(inputFailed);
		age.setForeground(changeInput);
	}else{
		age.setBackground(inputPass);
		age.setForeground(returnInput);
	}
	
	if(religion.getText().toString().equals("")){
		religion.setBackground(inputFailed);
		religion.setForeground(changeInput);
	}else{
		religion.setBackground(inputPass);
		religion.setForeground(returnInput);
	}
	
	if(nationality.getText().toString().equals("")){
		nationality.setBackground(inputFailed);
		nationality.setForeground(changeInput);
	}else{
		nationality.setBackground(inputPass);
		nationality.setForeground(returnInput);
	}
	
	if(email.getText().toString().equals("")){
		email.setBackground(inputFailed);
		email.setForeground(changeInput);
	}else{
		email.setBackground(inputPass);
		email.setForeground(returnInput);
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
	
	if(name.getText().toString().equals("") || special.getText().toString().equals("") ||  age.getText().toString().equals("") || religion.getText().toString().equals("") 
			|| nationality.getText().toString().equals("") || email.getText().equals("") 
			|| BMonth.getSelectedItem().equals("") || BDay.getSelectedItem().equals("") || BYr.getText().toString().equals("")
			|| gender.getSelectedItem().equals("")){
		
		JOptionPane.showMessageDialog(null,"Please Fill All Fields Required In Red Color!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
		
	}
	
	else{
		
		InsertSQL();
		
		
	}
	
}


//METHOD FOR ADD KONOYAROU!
public void InsertSQL(){
	
	checkID = rand_id.nextInt(9999999);
	
	  
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

String check = "SELECT doctor_id FROM doctors WHERE doctor_id="+"\""+checkID+"\"";

java.sql.Statement stmt = conn.createStatement();

ResultSet rs = stmt.executeQuery(check);
 
	
	if(rs.first()){
		 
		 JOptionPane.showMessageDialog(null,"An Error OccuredPlease Try Again!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
	}else{
		
	 
	 
	 String query = "INSERT INTO doctors (doctor_id, doctor_name,specialization,birth_month,birth_day,birth_year,age,gender,religion,nationality,e_mail,address,charge, photo)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);

	 ps.setInt(1, checkID);

	 ps.setString(2, name.getText().toString()); 

	 ps.setString(3, special.getText().toString()); 

	 ps.setString(4, getBMonth); 
	  
	 ps.setString(5, getBDay);

	 ps.setString(6, BYr.getText().toString()); 

	 ps.setString(7, age.getText().toString()); 

	 ps.setString(8, getGender);
	  
	 ps.setString(9, religion.getText().toString());

	 ps.setString(10, nationality.getText().toString()); 

	 ps.setString(11, email.getText().toString()); 

	 ps.setString(12, addr.getText().toString());
	  
	 ps.setString(13, SCharge.getText().toString());

	 ps.setString(14, photo);

	 ps.execute();

	  JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
	 
	 
	  
	  getDoctorCount();
	
}



conn.close();

}catch (Exception moriel){

 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

	} 


String URL = "jdbc:mysql://localhost:3306/h_m_s";
String userid = "root";
String password = "";
String sql;

try  {
	
	Connection connection = (Connection) DriverManager.getConnection( URL, userid, password );
    Statement stmt = connection.createStatement();
   	  
    sql = "SELECT * FROM doctors";
    ResultSet rs = stmt.executeQuery(sql);
		
		 table.setModel(DbUtils.resultSetToTableModel(rs));

}catch (Exception moriel){

		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

}



} 




//MUNTIK KO NG HINDI MAKITA ANG METHOD NA ITO SA DAMI NG CODES
//MORIEL!!!! HUHU!
public void ClearFields(){
	
	  //DID.setText("");
	  name.setText("");
	  special.setText("");
	  BMonth.setSelectedItem("");
	  BDay.setSelectedItem("");
	  BYr.setText("");
	  age.setText("");
	  gender.setSelectedItem("");
	  religion.setText("");
	  nationality.setText("");
	  email.setText("");
	  addr.setText("");
	  SCharge.setText("");
	  pixBox.setIcon(new ImageIcon(""));

	
}



//METHOD FOR SEARCHING ACCOUNT BAKAYAROU!
public void SearchSQL(){
	
	ClearFields();
	
	String check = "SELECT * FROM doctors WHERE doctor_id ="+"\""+DID.getText().toString()+"\"";

	
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

				 photo = rs.getString("photo");
				 
				  DID.setText(rs.getString("doctor_id"));
				  name.setText(rs.getString("doctor_name"));
				  special.setText(rs.getString("specialization"));
				  BMonth.setSelectedItem(rs.getString("birth_month"));
				  BDay.setSelectedItem(rs.getString("birth_day"));
				  BYr.setText(rs.getString("birth_year"));
				  age.setText(rs.getString("age"));
				  gender.setSelectedItem(rs.getString("gender"));
				  religion.setText(rs.getString("religion"));
				  nationality.setText(rs.getString("nationality"));
				  email.setText(rs.getString("e_mail"));
				  addr.setText(rs.getString("address"));
				  SCharge.setText(rs.getString("charge"));
				  pixBox.setIcon(new ImageIcon(photo));


				
				
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




//METHOD FOR TABLE ROW CLICKING
public void clickTableSearch(){
	
	
	String check = "SELECT * FROM doctors WHERE doctor_id="+"\""+table.getValueAt(table.getSelectedRow(), 1)+"\"";

	
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

				 photo = rs.getString("photo");
				 
				  DID.setText(rs.getString("doctor_id"));
				  name.setText(rs.getString("doctor_name"));
				  special.setText(rs.getString("specialization"));
				  BMonth.setSelectedItem(rs.getString("birth_month"));
				  BDay.setSelectedItem(rs.getString("birth_day"));
				  BYr.setText(rs.getString("birth_year"));
				  age.setText(rs.getString("age"));
				  gender.setSelectedItem(rs.getString("gender"));
				  religion.setText(rs.getString("religion"));
				  nationality.setText(rs.getString("nationality"));
				  email.setText(rs.getString("e_mail"));
				  addr.setText(rs.getString("address"));
				  SCharge.setText(rs.getString("charge"));
				  pixBox.setIcon(new ImageIcon(photo));


				
				
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
			String query = "UPDATE doctors SET doctor_name=?,specialization=?,birth_month=?,birth_day=?,birth_year=?,age=?,gender=?,religion=?,nationality=?,e_mail=?,address=?,charge=?, photo=? WHERE doctor_id = "+"\""+this.DID.getText().toString()+"\"";

			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			
			
			ps.setString(1, name.getText().toString()); 

			 ps.setString(2, special.getText().toString()); 

			 ps.setString(3, (String) BMonth.getSelectedItem()); 
			  
			 ps.setString(4, (String) BDay.getSelectedItem());

			 ps.setString(5, BYr.getText().toString()); 

			 ps.setString(6, age.getText().toString()); 

			 ps.setString(7, (String) gender.getSelectedItem());
			  
			 ps.setString(8, religion.getText().toString());

			 ps.setString(9, nationality.getText().toString()); 

			 ps.setString(10, email.getText().toString()); 

			 ps.setString(11, addr.getText().toString());
			  
			 ps.setString(12, SCharge.getText().toString());
			 
			 ps.setString(13, photo);

			
			ps.execute();

			 JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 

			conn.close();
			
			
			getDoctorCount();
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
	   	  
	    sql = "SELECT * FROM doctors";
	    ResultSet rs = stmt.executeQuery(sql);
			
			 table.setModel(DbUtils.resultSetToTableModel(rs));

	}catch (Exception moriel){

			 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

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
        	  
         sql = "SELECT * FROM doctors";
         ResultSet rs = stmt.executeQuery(sql);
         table = new JTable(model);
   		 JScrollPane scrollPane = new JScrollPane(table);
   		 table.setPreferredScrollableViewportSize(new Dimension(500, 70));
   		 table.setFillsViewportHeight(true);
   		 table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
   		 table.setBackground(Color.LIGHT_GRAY);
   		 scrollPane.setBounds(610,70,560,280);
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
        	  
        	sql = "SELECT * FROM doctors WHERE doctor_id LIKE "+"\""+DID.getText().toString()+"%\"";
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
     
        Color color;

        font1 = new Font("Arial", Font.PLAIN, 10);

        font2 = new Font("Arial", Font.BOLD, 12);

         font3 = new Font("Arial", Font.BOLD+Font.ITALIC, 12); 
         
         font4 = new Font("Arial", Font.BOLD, 14); 
         
         font5 = new Font("Arial", Font.BOLD+Font.ITALIC, 14); 


        g.drawImage(logo, 270, 50, 100, 50, this);

        g.setFont(font2);
        g.setColor(Color.RED);
        g.drawString(" HOSPITAL MANAGEMENT SYSTEM", 260, 70);
       
        g.drawString("_________________", 275, 102);
       



      


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



public void getDoctorCount(){
	
	
String query = "SELECT * FROM doctors ORDER BY doctor_count ASC";

	
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

		ResultSet rs = stmt.executeQuery(query);
		 
		 int counter = 0;

			 while(rs.next()){

				 
				doctor_count.setText("  No. Of Doctors:  "+rs.getInt("doctor_count"));
				
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
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		AlternateSearchSQL();
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		clickTableSearch();
		
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


	
	
	//NEW CLASS PARA SA DOCTOR NAME SEARCHING
	public class Moriel implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			SearchDocNameSQL();
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			SearchDocNameSQL();
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			SearchDocNameSQL();
		}
		
		//METHOD PARA SA NAME SEARCHING
		public void SearchDocNameSQL(){
			
			
			 String url = "jdbc:mysql://localhost:3306/h_m_s";
		     String userid = "root";
		     String password = "";
		     String sql;
		    
		   
		 	
			 try  {
		     	
		     	Connection connection = (Connection) DriverManager.getConnection( url, userid, password );
		         Statement stmt = connection.createStatement();
		        	  
		        	sql = "SELECT * FROM doctors WHERE doctor_name LIKE "+"\""+name.getText().toString()+"%\"";
		        	ResultSet rs = stmt.executeQuery(sql);
		        	table.setModel(DbUtils.resultSetToTableModel(rs));
		         
		    
			 }catch(Exception moriel){
				 
				 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }
		} 

		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
