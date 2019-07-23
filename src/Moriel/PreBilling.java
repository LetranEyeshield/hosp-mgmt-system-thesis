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
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import Moriel.Admission.Moriel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class PreBilling extends JFrame implements ActionListener, Printable, MouseListener, KeyListener{
	
	JLabel labels,title;
	
	
	JTextField PID, FN, MN, LN, ward, wardCharge, age, numDays, docCharge, otherCharge, bill;
	
	JButton compute, clearFields, print, update, exit;

	
	String G[] = {"", "Male", "Female"};
	String setGender, getGender;
	JComboBox gender;
	
	String W[] = {"", "Private", "Pay Ward", "Charity Ward"};
	JComboBox wardType;
	
	//MGA VARS PARA SA CHECKING NG MGA BAYAD MAMAYA
	String WC, ND, DC, OC;
	int nd;
	double wc, dc, oc, totalBill;
	
	
	JTable table;

	DefaultTableModel model;
	
	
	//COLORS PARA SA CHECKING INPUTS
		Color inputFailed = Color.RED;
		Color changeInput = Color.WHITE;
		Color inputPass = Color.WHITE;
		Color returnInput = Color.BLACK;
		
		
		String getWardCharge;
		
		
		//PREPARE THE CLASS PARA SA LAST NAME SEARCHING
		Moriel Kim = new Moriel();
		
		
	public PreBilling(){
		super("HOSPITAL MANAGEMENT SYSTEM");
		setLayout(null);
		
		
		tableModel();
		setTitle();
		setLabels();
		setFields();
		setButtons();
		setPanels();
		setLine();
		setBackground();
		
		
		
	}
	
	
public void setTitle(){
		
		title = new JLabel("                   " +
				"                      " +
				"               PARTIAL BILLING");
		title.setBounds(0,0,860,25);
		title.setOpaque(true);
		title.setBackground(Color.blue);
		title.setFont(new Font("OLD ENGLISH MT",Font.BOLD,20));
		add(title);
	}



public void setBackground(){

	JLabel background = new JLabel(new ImageIcon("res/pre_billing.png"));
	 background.setBounds(0,0,860,670);
	 background.setBackground(new Color(0,0,255));
	// background.setBackground(Color.LIGHT_GRAY);
	 background.setOpaque(true);
	 add(background);
	 
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

public void setLabels(){
	

	prepareLabels(" Patient ID:",30,80,90,18);
	prepareLabels(" First Name:",30,110,90,18);
	prepareLabels(" Middle Name:",30,140,90,18);
	prepareLabels(" Last Name:",30,170,90,18);
	prepareLabels(" Age:",30,200,90,18);
	prepareLabels(" Gender:",30,230,90,18);
	
	prepareLabels(" Ward/Room No:",30,300,140,18);
	prepareLabels(" Ward/Room Charge:",30,330,140,18);
	prepareLabels(" Ward/Room Type:",30,360,140,18);
	prepareLabels(" Number Of Days:",30,390,140,18);
	prepareLabels(" Doctor Charge:",30,420,140,18);
	prepareLabels(" Other Charge:",30,450,140,18);
	prepareLabels(" Partial Bill:",30,480,140,18);
	

	
}


public void setPanels(){
	
	JLabel panel1 = new JLabel();
	panel1.setBounds(20,70,280,190);
	panel1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel1);
	
	JLabel panel2 = new JLabel();
	panel2.setBounds(320,70,500,440);
	panel2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel2);
	
	JLabel panelLabel1 = new JLabel("  Personal Information ");
	panelLabel1.setBounds(20,50,130,20);
	panelLabel1.setOpaque(true);
	panelLabel1.setBackground(Color.CYAN);
	add(panelLabel1);
	

	JLabel panelLabel2 = new JLabel("  Charges Information ");
	panelLabel2.setBounds(20,270,130,20);
	panelLabel2.setOpaque(true);
	panelLabel2.setBackground(Color.CYAN);
	add(panelLabel2);
	
	JLabel panel3 = new JLabel();
	panel3.setBounds(20,290,280,220);
	panel3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel3);
	
	
	
	
	
}


public void setFields(){
	
	PID = new JTextField();
	PID.setBounds(130,80,150,18);
	//PID.setEditable(false);
	PID.addKeyListener(this);
	add(PID);
	
	FN = new JTextField();
	FN.setBounds(130,110,150,18);
	add(FN);
	
	MN = new JTextField();
	MN.setBounds(130,140,150,18);
	add(MN);
	
	LN = new JTextField();
	LN.setBounds(130,170,150,18);
	LN.addKeyListener(Kim);
	add(LN);
	
	
	age = new JTextField();
	age.setBounds(130,200,150,18);
	add(age);
	
	
	gender = new JComboBox(G);
	gender.setBounds(130,230,150,18);
	gender.setSelectedIndex(0);
	gender.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent jammi){

	if(jammi.getSource()==gender){
	JComboBox cb = (JComboBox)jammi.getSource();
	getGender = (String)cb.getSelectedItem();

	}
}
}); 
	add(gender);
	
	ward = new JTextField();
	ward.setBounds(180,300,100,18);
	add(ward);
	
	wardCharge = new JTextField("500");
	wardCharge.setBounds(180,330,100,18);
	add(wardCharge);
	
	
	wardType = new JComboBox(W);
	wardType.setBounds(180,360,100,18);
	wardType.setSelectedIndex(0);
	wardType.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent jammi) {
			// TODO Auto-generated method stub
			SearchWardSQL();
		}
		
	});
	add(wardType);
	
		
		numDays = new JTextField("0");
		numDays.setBounds(180,390,100,18);
		add(numDays);
		
		docCharge = new JTextField("0");
		docCharge.setBounds(180,420,100,18);
		add(docCharge);
		
		otherCharge = new JTextField("0");
		otherCharge.setBounds(180,450,100,18);
		add(otherCharge);
		
		bill = new JTextField();
		bill.setBounds(180,480,100,18);
		bill.setEditable(false);
		add(bill);
		
	
			
			
	
}


public void setButtons(){
	

	
	compute = new JButton("COMPUTE");
	compute.setBounds(100,570,120,25);
	compute.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			
			checkInputs();
			
		}
		
	});
	add(compute);
	
	
	print = new JButton("PRINT");
	print.setBounds(240,570,120,25);
	print.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			printMe();
		}
		
	});
	add(print);
	
	
	clearFields = new JButton("CLEAR FIELDS");
	clearFields.setBounds(520,570,120,25);
	clearFields.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			ClearFields();
		}
		
	});
	add(clearFields);
	
	
	
	update = new JButton("UPDATE");
	update.setBounds(380,570,120,25);
	update.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			checkEditInputs();
		}
		
	});
	add(update);
	
	
	exit = new JButton("EXIT");
	exit.setBounds(660,570,70,25);
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
	line.setBounds(0,530,860,1);
	line.setOpaque(true);
	line.setBackground(Color.RED);
	add(line);
}





//CHECKING USER INPUTS
public void checkInputs(){
	
	if(PID.getText().toString().equals("")){
		PID.setBackground(inputFailed);
		PID.setForeground(changeInput);
	}else{
		PID.setBackground(inputPass);
		PID.setForeground(returnInput);
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
	
	
	
	if(numDays.getText().toString().equals("")){
		numDays.setBackground(inputFailed);
		numDays.setForeground(changeInput);
	}else{
		numDays.setBackground(inputPass);
		numDays.setForeground(returnInput);
	}
	
	if(docCharge.getText().toString().equals("")){
		docCharge.setBackground(inputFailed);
		docCharge.setForeground(changeInput);
	}else{
		docCharge.setBackground(inputPass);
		docCharge.setForeground(returnInput);
	}
	
	
	
	if(gender.getSelectedItem().toString().equals("")){
		gender.setBackground(inputFailed);
		gender.setForeground(changeInput);
	}else{
		gender.setBackground(inputPass);
		gender.setForeground(returnInput);
	}
	
	if(wardType.getSelectedItem().toString().equals("")){
		wardType.setBackground(inputFailed);
		wardType.setForeground(changeInput);
	}else{
		wardType.setBackground(inputPass);
		wardType.setForeground(returnInput);
	}
	
	if(ward.getText().toString().equals("")){
		ward.setBackground(inputFailed);
		ward.setForeground(changeInput);
	}else{
		ward.setBackground(inputPass);
		ward.setForeground(returnInput);
	}
	
	if(wardCharge.getText().toString().equals("")){
		wardCharge.setBackground(inputFailed);
		wardCharge.setForeground(changeInput);
	}else{
		wardCharge.setBackground(inputPass);
		wardCharge.setForeground(returnInput);
	}
	
	if(PID.getText().toString().equals("") || FN.getText().toString().equals("") ||  MN.getText().toString().equals("") || LN.getText().toString().equals("") 
			|| ward.getText().toString().equals("") || wardCharge.getText().equals("") ||
			age.getText().toString().equals("") || 
			numDays.getText().toString().equals("") || docCharge.getText().toString().equals("")
			|| gender.getSelectedItem().equals("") || wardType.getSelectedItem().equals("")
			){
		
		JOptionPane.showMessageDialog(null,"Please Fill All Fields Required In Red Color!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
		
	}
	
	else{
	
		try {
			checkBayadInput();
			InsertSQL();
			
		} catch (Exception morielKim) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"Some Fields Required Numbers Only"+"\n"+"Lalo Na Sa Bayad Konoyarou!"+"\n"+morielKim.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

		}
	}
	
}




//CHECKING USER INPUTS FOR UPDATING
public void checkEditInputs(){
	
	if(PID.getText().toString().equals("")){
		PID.setBackground(inputFailed);
		PID.setForeground(changeInput);
	}else{
		PID.setBackground(inputPass);
		PID.setForeground(returnInput);
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
	
	
	
	if(numDays.getText().toString().equals("")){
		numDays.setBackground(inputFailed);
		numDays.setForeground(changeInput);
	}else{
		numDays.setBackground(inputPass);
		numDays.setForeground(returnInput);
	}
	
	if(docCharge.getText().toString().equals("")){
		docCharge.setBackground(inputFailed);
		docCharge.setForeground(changeInput);
	}else{
		docCharge.setBackground(inputPass);
		docCharge.setForeground(returnInput);
	}
	
	
	
	if(gender.getSelectedItem().toString().equals("")){
		gender.setBackground(inputFailed);
		gender.setForeground(changeInput);
	}else{
		gender.setBackground(inputPass);
		gender.setForeground(returnInput);
	}
	
	if(wardType.getSelectedItem().toString().equals("")){
		wardType.setBackground(inputFailed);
		wardType.setForeground(changeInput);
	}else{
		wardType.setBackground(inputPass);
		wardType.setForeground(returnInput);
	}
	
	if(ward.getText().toString().equals("")){
		ward.setBackground(inputFailed);
		ward.setForeground(changeInput);
	}else{
		ward.setBackground(inputPass);
		ward.setForeground(returnInput);
	}
	
	if(wardCharge.getText().toString().equals("")){
		wardCharge.setBackground(inputFailed);
		wardCharge.setForeground(changeInput);
	}else{
		wardCharge.setBackground(inputPass);
		wardCharge.setForeground(returnInput);
	}
	
	if(PID.getText().toString().equals("") || FN.getText().toString().equals("") ||  MN.getText().toString().equals("") || LN.getText().toString().equals("") 
			|| ward.getText().toString().equals("") || wardCharge.getText().equals("") ||
			age.getText().toString().equals("") || 
			numDays.getText().toString().equals("") || docCharge.getText().toString().equals("")
			|| gender.getSelectedItem().equals("") || wardType.getSelectedItem().equals("")
			){
		
		JOptionPane.showMessageDialog(null,"Please Fill All Fields Required In Red Color!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
		
	}
	
	else{
	
		try {
			checkBayadInput();
			EditSQL();
			
		} catch (Exception morielKim) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"Some Fields Required Numbers Only"+"\n"+"Lalo Na Sa Bayad Konoyarou!"+"\n"+morielKim.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

		}
	}
	
}


public void checkBayadInput() throws Exception{
	
	
	//CONVERTING THE MONEY TO NUMBERS PARA WALANG MANDAYA HAHA!	
			ND = numDays.getText().toString();
			nd = Integer.parseInt(ND);
			
			WC = wardCharge.getText().toString();
			wc = Double.parseDouble(WC);
			
			DC = docCharge.getText().toString();
			dc = Double.parseDouble(DC);
			
			OC = otherCharge.getText().toString();
			oc = Double.parseDouble(OC);
			
			totalBill = (nd*wc) +dc + oc;
			
			bill.setText("P "+totalBill);
			
			
			
	
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

			java.sql.Statement stmt = conn.createStatement();

				 
				String query = "INSERT INTO partial_billing_history (patient_id, first_name, middle_name, last_name, ward_no, ward_charge, ward_type, age, gender, num_days, doctor_charge, other_charge, partial_bill)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

				 
				 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
				 
				 

				  ps.setString(1, PID.getText().toString());

				  ps.setString(2, FN.getText().toString()); 

				  ps.setString(3, MN.getText().toString()); 

				  ps.setString(4, LN.getText().toString()); 
				  
				  ps.setString(5, ward.getText().toString());
				  
				  ps.setDouble(6, wc); 
				  
				  ps.setString(7, (String) wardType.getSelectedItem()); 

				  ps.setString(8, age.getText().toString()); 
				  
				  ps.setString(9, (String) gender.getSelectedItem());

				  ps.setDouble(10, nd); 

				  ps.setDouble(11, dc);
				  
				  ps.setDouble(12, oc);

				  ps.setDouble(13, totalBill); 

				
				 ps.execute();

				 // JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
				 
				 


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
		   	  
		    sql = "SELECT * FROM partial_billing";
		    ResultSet rs = stmt.executeQuery(sql);
				
				 table.setModel(DbUtils.resultSetToTableModel(rs));

		}catch (Exception moriel){

				 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

		}
		

	  

} 




//MUNTIK KO NG HINDI MAKITA ANG METHOD NA ITO SA DAMI NG CODES
//MORIEL!!!! HUHU!
public void ClearFields(){
	
	 // PID.setText("");
	  FN.setText("");
	  MN.setText("");
	  LN.setText("");
	  ward.setText("");
	  wardCharge.setText("0");
	  wardType.setSelectedItem("");
	  age.setText("");
	  gender.setSelectedItem("0");
	  numDays.setText("0"); 
	  docCharge.setText("0"); 
	  otherCharge.setText("0"); 
	  bill.setText("");
	
	
}


//METHOD FOR SEARCHING WARD PRICE!
public void SearchWardSQL(){
	  
	
	
	String query = "SELECT * FROM ward WHERE type="+"\""+wardType.getSelectedItem().toString()+"\"";

	
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
				 
				 wardCharge.setText(rs.getString("price_per_day"));

				
		counter++;
			 }
			 

			 if(counter<1){
				 
				// JOptionPane.showMessageDialog(null,"Ward Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }

		conn.close();

		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 

			} 

} 



//METHOD FOR SEARCHING ACCOUNT BAKAYAROU!
public void SearchSQL(){
	  
	ClearFields();
	
	// RENDERING NA NG RESULTS
	String check = "SELECT * FROM partial_billing WHERE patient_id="+"\""+PID.getText().toString()+"\"";

	
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


				  PID.setText(rs.getString("patient_id"));
				  FN.setText(rs.getString("first_name"));
				  MN.setText(rs.getString("middle_name"));
				  LN.setText(rs.getString("last_name"));
				  ward.setText(rs.getString("ward_no"));
				  wardType.setSelectedItem(rs.getString("ward_type"));
				  age.setText(rs.getString("age"));
				  gender.setSelectedItem(rs.getString("gender"));
				  numDays.setText(rs.getString("num_days")); 
				  docCharge.setText(rs.getString("doctor_Charge")); 
				  otherCharge.setText(rs.getString("other_charge")); 
				  bill.setText("P "+rs.getString("partial_bill"));

				
				
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


//METHOD PARA SA TABLE ROW CLICKING
public void tableClickSearch(){
	  
	
	// RENDERING NA NG RESULTS
	String check = "SELECT * FROM partial_billing WHERE patient_id="+"\""+table.getValueAt(table.getSelectedRow(), 0)+"\"";

	
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


				  PID.setText(rs.getString("patient_id"));
				  FN.setText(rs.getString("first_name"));
				  MN.setText(rs.getString("middle_name"));
				  LN.setText(rs.getString("last_name"));
				  ward.setText(rs.getString("ward_no"));
				  wardType.setSelectedItem(rs.getString("ward_type"));
				  age.setText(rs.getString("age"));
				  gender.setSelectedItem(rs.getString("gender"));
				  numDays.setText(rs.getString("num_days")); 
				  docCharge.setText(rs.getString("doctor_Charge")); 
				  otherCharge.setText(rs.getString("other_charge")); 
				  bill.setText("P "+rs.getString("partial_bill"));

				
				
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
			String query = "UPDATE partial_billing SET first_name=?, middle_name=?, last_name=?, ward_no=?, ward_charge=?, ward_type=?, age=?, gender=?, num_days=?, doctor_charge=?, other_charge=?, partial_bill=? WHERE patient_id = "+"\""+this.PID.getText().toString()+"\"";
			
			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			

			  ps.setString(1, FN.getText().toString()); 

			  ps.setString(2, MN.getText().toString()); 

			  ps.setString(3, LN.getText().toString()); 
			  
			  ps.setString(4, ward.getText().toString());

			  ps.setDouble(5, wc); 
			  
			  ps.setString(6, (String) wardType.getSelectedItem());

			  ps.setString(7, age.getText().toString()); 

			  ps.setString(8, (String) gender.getSelectedItem());

			  ps.setInt(9, nd); 

			  ps.setDouble(10, dc);
			  
			  ps.setDouble(11, oc);

			  ps.setDouble(12, totalBill); 


			
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
	   	  
	    sql = "SELECT * FROM partial_billing";
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
    	  
     sql = "SELECT * FROM partial_billing";
     ResultSet rs = stmt.executeQuery(sql);
     table = new JTable(model);
		 JScrollPane scrollPane = new JScrollPane(table);
		 table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		 table.setFillsViewportHeight(true);
		 table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 table.setBackground(Color.LIGHT_GRAY);
		 scrollPane.setBounds(330,80,480,420);
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
      	  
      	sql = "SELECT * FROM partial_billing WHERE patient_id LIKE "+"\""+PID.getText().toString()+"%\"";
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
	
	Image logo = Toolkit.getDefaultToolkit().getImage("res/logo.jpg");
	
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	//DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:m:s");
 	 Date date = new Date();
	
	
	 if (page > 0) { /* We have only one page, and 'page' is zero-based */
          return NO_SUCH_PAGE;
      }

      /* User (0,0) is typically outside the imageable area, so we must
       * translate by the X and Y values in the PageFormat to avoid clipping
       */
      Graphics2D g2d = (Graphics2D)g;
      g2d.translate(pf.getImageableX(), pf.getImageableY());

     
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
      g.drawString("Partial Billing", 270,135);
      
      
      
      g.setFont(new Font("Tahoma",Font.PLAIN,8));
      g.setColor(Color.BLACK);

      g.drawString("Patient ID: "+PID.getText().toString(), 100,160);

      g.drawString("Name: "+FN.getText().toString()+" "+MN.getText().toString()+" "+LN.getText().toString(), 100,170);
      
      g.drawString("Date: "+ dateFormat.format(date), 100,180);
      
      g.drawString("Age: "+age.getText().toString(), 100,190);
      
      g.drawString("Gender : "+ gender.getSelectedItem(), 100,200);
     
      g.drawString("Ward/Room No: "+ward.getText().toString(), 100,210);

      
      g.drawString("Ward/Room Charge: "+wardCharge.getText().toString(), 100,220);
      
      
      g.drawString("Ward/Room Type: "+wardType.getSelectedItem(), 100,230);


      g.drawString("Number Of Days: "+numDays.getText().toString(), 100,240);

     
      g.drawString("Doctor Charge: "+docCharge.getText().toString(), 100,250);

    
      g.drawString("Other Charges: "+otherCharge.getText().toString(), 100,260);

   
      g.drawString("Partial Bill: "+bill.getText().toString(), 100,270);

      
      
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
      		"_ _ _ _ _ _ _ _ _ _ _ _", 70, 365);
    
    
      
      g.drawImage(logo, 270, 395, 60, 60, this);

      g.setFont(new Font("Tahoma",Font.BOLD,20));
      g.setColor(Color.RED);
      g.drawString(" HOSPITAL MANAGEMENT SYSTEM", 140, 475);
     
      
      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.setColor(Color.BLACK);
      g.drawString("Arellano Street, Dagupan City 2400, Pangasinan, Philippines", 180,485);

      //draw line separator
      g.drawString("_______________________________________" +
      		"________________________________________________", 70, 495);
      
      g.setFont(new Font("Tahoma",Font.ITALIC,10));
      g.setColor(Color.BLACK);
      g.drawString("Partial Billing", 270,510);
      
      
      
      g.setFont(new Font("Tahoma",Font.PLAIN,8));
      g.setColor(Color.BLACK);

      g.drawString("Patient ID: "+PID.getText().toString(), 100,530);

      g.drawString("Name: "+FN.getText().toString()+" "+MN.getText().toString()+" "+LN.getText().toString(), 100,540);
      
      g.drawString("Age: "+age.getText().toString(), 100,550);
      
      g.drawString("Gender : "+ gender.getSelectedItem(), 100,560);
     
      g.drawString("Ward/Room No: "+ward.getText().toString(), 100,570);

      
      g.drawString("Ward/Room Charge: "+wardCharge.getText().toString(), 100,580);
      
      
      g.drawString("Ward/Room Type: "+wardType.getSelectedItem(), 100,590);

     

      g.drawString("Number Of Days: "+numDays.getText().toString(), 100,600);

     
      g.drawString("Doctor Charge: "+docCharge.getText().toString(), 100,610);

    
      g.drawString("Other Charges: "+otherCharge.getText().toString(), 100,620);

   
      g.drawString("Total Bill: "+bill.getText().toString(), 100,630);

      
      
      g.setFont(new Font("Tahoma",Font.ITALIC,10));
      g.setColor(Color.BLACK);
      //draw line separator
      g.drawString("_______________________________________" +
      		"_________________________________", 70, 650);
    

      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.drawString("Email Address: region1mc2003@yahoo.com", 100,665);
      
      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.drawString("Telephone Numbers: (075) 515-89-16 ; (075)515-89-18 ; (075)515-89-01", 100,675);

      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.drawString("Fax: (075) 523-41-03", 100,685);



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
	
	
	public void AlternateSearchSQL(){
		
		 String url = "jdbc:mysql://localhost:3306/h_m_s";
	   String userid = "root";
	   String password = "";
	   String sql;
	  
	 
		
		 try  {
	   	
	   	Connection connection = (Connection) DriverManager.getConnection( url, userid, password );
	       Statement stmt = connection.createStatement();
	      	  
	      	sql = "SELECT * FROM partial_billing WHERE last_name LIKE "+"\""+LN.getText().toString()+"%\"";
	      	ResultSet rs = stmt.executeQuery(sql);
	      	table.setModel(DbUtils.resultSetToTableModel(rs));
	       
	  
		 }catch(Exception moriel){
			 
			 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
			 
		 }
		 
		
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
