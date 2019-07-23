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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import Moriel.Consultation.Moriel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class PatientInfo extends JFrame implements ActionListener, Printable, MouseListener, KeyListener{
	
	JLabel labels,title, patient_count;
	
	Random rand_id = new Random();
	int checkID;
	
	JTextField PID, FN, MN, LN, age, bplace, address, occu, nation, rel, bloodPress, dad, mom, parentsAddr, parentsNo, spouse, SAddr, SNo, insurance;
	
	JButton add, edit, print, exit;
	
	JTable table;
	
	DefaultTableModel model;
	
	//VARS FOR BIRTH DATE
	String BM[] = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	JComboBox BMonth;
	
	String BD[] = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	JComboBox BDay;
	

	JTextField BYr;
	
	String W[] = {"Pay Ward", "Private", "Charity Ward"};
	JComboBox wardType;
	
	
	JComboBox ward, bed_no;
	
	//KELANGAN KO TO PARA SA TECHNIQUE MAMAYA SA PAGKUHA NG VALUES NG WARD, TYPE, BED NO
	JTextField getWard, getType, getBed;
	
	String B[] = {"", "O", "A", "B", "AB"};
	JComboBox bloodType;
	
	
	String G[] = {"", "Male", "Female"};
	JComboBox gender;
	
	String S[] = {"", "Single", "Married", "Widowed","Divorced","Separated"};
	JComboBox status;
	
	
	String D[] = {"", "SSS Dependent", "GSIS Dependent", "PhilHealth Dependent"};
	JComboBox dependency;
	
	
	JTextArea diag;
	JScrollPane scroll;
	
	JTextArea HPlan;
	JScrollPane scrollHPlan;
	
	
	//VARS PARA SA PRINTING
	Graphics2D g2;
	Image logo;
	Font font1, font2, font3, font4, font5;
	
	
	//COLORS PARA SA CHECKING INPUTS
	Color inputFailed = Color.RED;
	Color changeInput = Color.WHITE;
	Color returnInput = Color.BLACK;
	Color inputPass = Color.WHITE;
	
	
	//kailangan para sa rendering ng data sa database mamaya
		ResultSet rs;
	
		//PREPARE THE CLASS PARA SA LAST NAME SEARCHING
		Moriel Kim = new Moriel();
		
	public PatientInfo(){
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
		getPatientCount();
		
		
	}
	
	
public void setTitle(){
		
		title = new JLabel("                   " +
				"                      " +
				"                         " +
				"              PATIENT INFORMATION");
		title.setBounds(0,0,1250,25);
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

public void setPanels(){
	
	JLabel panel1 = new JLabel();
	panel1.setBounds(20,70,430,290);
	panel1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel1);
	
	JLabel panel2 = new JLabel();
	panel2.setBounds(460,70,770,290);
	panel2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel2);
	
	JLabel panelLabel1 = new JLabel("  Personal Information ");
	panelLabel1.setBounds(20,50,130,20);
	panelLabel1.setOpaque(true);
	panelLabel1.setBackground(Color.CYAN);
	add(panelLabel1);
	

	JLabel panelLabel2 = new JLabel("  Medical Information ");
	panelLabel2.setBounds(20,360,130,20);
	panelLabel2.setOpaque(true);
	panelLabel2.setBackground(Color.CYAN);
	add(panelLabel2);
	
	JLabel panel3 = new JLabel();
	panel3.setBounds(20,380,550,230);
	panel3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel3);
	
	
	JLabel panelLabel3 = new JLabel("  Family Information ");
	panelLabel3.setBounds(590,360,130,20);
	panelLabel3.setOpaque(true);
	panelLabel3.setBackground(Color.CYAN);
	add(panelLabel3);
	
	JLabel panel4 = new JLabel();
	panel4.setBounds(590,380,640,230);
	panel4.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel4);
	
	
}


public void setBackground(){

	JLabel background = new JLabel(new ImageIcon("res/patient_info.png"));
	 background.setBounds(0,0,1250,700);
	 background.setBackground(new Color(65,105,255));
	// background.setBackground(Color.LIGHT_GRAY);
	 background.setOpaque(true);
	 add(background);
	 
}

public void setLabels(){
	
	prepareLabels(" Patient ID:",30,80,90,18);
	prepareLabels(" First Name:",30,105,90,18);
	prepareLabels(" Middle Name:",30,130,90,18);
	prepareLabels(" Last Name:",30,155,90,18);
	prepareLabels(" Address:",30,180,90,18);
	prepareLabels(" Birthday:",30,205,90,18);
	prepareLabels(" Birthplace:",30,230,90,18);
	prepareLabels(" Age:",30,255,90,18);
	prepareLabels(" Gender:",250,255,80,20);
	prepareLabels(" Civil Status:",30,280,90,18);
	prepareLabels(" Nationality:",250,280,80,18);
	prepareLabels(" Occupation:",30,305,90,18);
	prepareLabels(" Religion:",30,330,90,18);
	
	prepareLabels(" Ward/Room:",30,390,90,18);
	prepareLabels(" Ward Type:",210,390,100,18);
	prepareLabels(" Bed No:",440,390,60,18);
	prepareLabels(" Blood Type:",30,415,90,18);
	prepareLabels(" Blood Pressure:",210,415,100,18);
	prepareLabels(" Health Insurance:",30,440,170,18);
	prepareLabels(" Type Of Dependency:",30,465,170,18);
	
	prepareLabels(" Patient Problem/Diagnosis:",30,490,170,18);
	
	prepareLabels("      Hospitalization Plan ",30,550,170,20);
	prepareLabels("(Company/Industrial Name)",30,565,170,20);
	
	
	prepareLabels(" Father's Name:",600,390,150,18);
	prepareLabels(" Mother's Name:",600,415,150,18);
	prepareLabels(" Parent's Address:",600,440,150,18);
	prepareLabels(" Parent's Contact No:",600,465,150,18);
	prepareLabels(" Spouse:",600,490,150,18);
	prepareLabels(" Spouse's Contact No:",600,515,150,18);
	prepareLabels(" Spouse's Address:",600,540,150,18);
	
	
	// label para  kunin ung number of patients mamaya obvious diba? haha!
	patient_count = new JLabel(" No. Of Patients ");
	patient_count.setBounds(750,330,150,20);
	patient_count.setOpaque(true);
	patient_count.setBackground(new Color(151,255,255));
	add(patient_count);
	
}


public void setFields(){
	
	PID = new JTextField();
	PID.setBounds(130,80,200,18);
	//PID.setEditable(false);
	PID.addKeyListener(this);
	add(PID);
	
	FN = new JTextField();
	FN.setBounds(130,105,200,18);
	add(FN);
	
	MN = new JTextField();
	MN.setBounds(130,130,200,18);
	add(MN);
	
	LN = new JTextField();
	LN.setBounds(130,155,200,18);
	LN.addKeyListener(Kim);
	add(LN);
	
	
	
		//MGA FIELDS PARA SA BIRTHDAY
		BMonth = new JComboBox(BM);
		BMonth.setBounds(130,205,90,18);
		BMonth.setSelectedIndex(0);
		add(BMonth);


		BDay = new JComboBox(BD);
		BDay.setBounds(230,205,40,18);
		BDay.setSelectedIndex(0);
		add(BDay);


		BYr = new JTextField("2017");
		BYr.setBounds(280,205,50,18);
		add(BYr);
			
		gender = new JComboBox(G);
		gender.setBounds(340,255,100,18);
		gender.setSelectedIndex(0);
		add(gender);
			
			
			
		status = new JComboBox(S);
		status.setBounds(130,280,90,18);
		status.setSelectedIndex(0);
		add(status);
		
		
		wardType = new JComboBox(W);
		wardType.setBounds(320,390,100,18);
		wardType.setSelectedIndex(0);
		wardType.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent moriel) {
				// TODO Auto-generated method stub
				CheckAvailableRoom();
			}
			
		});
		add(wardType);
		
		
		ward = new JComboBox();
		ward.setBounds(130,390,50,18);
		ward.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent moriel) {
				// TODO Auto-generated method stub
				CheckAvailableBed();
			}
			
		});
		add(ward);
		
		bed_no = new JComboBox();
		bed_no.setBounds(510,390,50,18);
		add(bed_no);
		
		
		bloodType = new JComboBox(B);
		bloodType.setBounds(130,415,50,18);
		bloodType.setSelectedIndex(0);
		add(bloodType);
			
			
		dependency = new JComboBox(D);
		dependency.setBounds(210,465,210,18);
		dependency.setSelectedIndex(0);
		add(dependency);
			
			
			age = new JTextField();
			age.setBounds(130,255,90,18);
			add(age);
			
			bplace = new JTextField();
			bplace.setBounds(130,230,200,18);
			add(bplace);
			
			occu = new JTextField();
			occu.setBounds(130,305,200,18);
			add(occu);
			
			address = new JTextField();
		    address.setBounds(130,180,200,18);
			add(address);
			
	
			nation = new JTextField();
			nation.setBounds(340,280,100,18);
			add(nation);
			
			rel = new JTextField();
			rel.setBounds(130,330,200,18);
			add(rel);
			
			
			bloodPress = new JTextField("120/80");
			bloodPress.setBounds(320,415,100,18);
			add(bloodPress);
			
			
			dad = new JTextField();
			dad.setBounds(760,390,200,18);
			add(dad);
			
			mom = new JTextField();
			mom.setBounds(760,415,200,18);
			add(mom);
		
			
			diag=new JTextArea();
			//Remarks.setBounds(100,505,250,240);
			diag.setToolTipText("Please use 'Enter Key' to use line break!");
			diag.setLineWrap(true);
			diag.setWrapStyleWord(true);
			//add(Remarks);
			scroll=new JScrollPane(diag);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			//scrollRemarks.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll.setBounds(210,490,210,50);
			add(scroll);
			
			
			parentsAddr = new JTextField();
			parentsAddr.setBounds(760,440,200,18);
			add(parentsAddr);
			
			parentsNo = new JTextField();
			parentsNo.setBounds(760,465,200,18);
			add(parentsNo);
			
			spouse = new JTextField();
			spouse.setBounds(760,490,200,18);
			add(spouse);
			
			SAddr = new JTextField();
			SAddr.setBounds(760,540,200,18);
			add(SAddr);
			
			SNo = new JTextField();
			SNo.setBounds(760,515,200,18);
			add(SNo);
			
			insurance = new JTextField();
			insurance.setBounds(210,440,210,18);
			add(insurance);
			

			HPlan=new JTextArea();
			//Remarks.setBounds(100,505,250,240);
			HPlan.setToolTipText("Please use 'Enter Key' to use line break!");
			HPlan.setLineWrap(true);
			HPlan.setWrapStyleWord(true);
			//add(Remarks);
			scrollHPlan=new JScrollPane(HPlan);
			scrollHPlan.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			//scrollRemarks.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollHPlan.setBounds(210,550,210,50);
			add(scrollHPlan);
			
			
			
			getWard = new JTextField();
			getWard.setBounds(130,390,50,18);
			getWard.setVisible(false);
			add(getWard);
			
			
			getType= new JTextField();
			getType.setBounds(320,390,100,18);
			getType.setVisible(false);
			add(getType);
			
			getBed= new JTextField();
			getBed.setBounds(510,390,50,18);
			getBed.setVisible(false);
			add(getBed);
	
}





//CHECKING PARA SA AVAILABLE NA ROOMS OR WARDS PAG NA-CLICK UNG TYPE
public void CheckAvailableRoom(){
	
	 ward.removeAllItems();
	 bed_no.removeAllItems();

	
	String sql = "SELECT * FROM ward WHERE availability = 'Available' AND type LIKE "+"\""+wardType.getSelectedItem()+"%\""+"";
	
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

		ResultSet rs = stmt.executeQuery(sql);
		 
		 int counter = 0;
		 
		 

			 while(rs.next()){
				 
				 String getWard =rs.getString("number");
				 
				 String getBed =rs.getString("bed_no");
				 
				 	
					ward.addItem(getWard);
					
					
				
		counter++;
			 }
			 

			 if(counter<1){
				 
				// JOptionPane.showMessageDialog(null,"Ward Not Available!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }

		conn.close();

		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 

			} 
}




//CHECK AVAILABLE BED PAG NA-CLICK UNG ROOM
public void CheckAvailableBed(){
	
	bed_no.removeAllItems();
	
	//String sql = "SELECT * FROM ward WHERE availability = 'Available' AND type LIKE "+"\""+wardType.getSelectedItem()+"%\""+"";
	
	String sql = "SELECT * FROM ward WHERE availability = 'Available' AND type = "+"\""+wardType.getSelectedItem()+"\""
			+" AND number = "+"\""+ward.getSelectedItem()+"\"";
	 
	
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

		ResultSet rs = stmt.executeQuery(sql);
		 
		 int counter = 0;
		 
		 

			 while(rs.next()){
				 
				 String getWard =rs.getString("number");
				 
				 String getBed =rs.getString("bed_no");

				 	
					//ward.addItem(getWard);
					bed_no.addItem(getBed);
					
				
		counter++;
			 }
			 

			 if(counter<1){
				 
				// JOptionPane.showMessageDialog(null,"Ward Not Available!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }

		conn.close();

		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 

			} 
}


public void setButtons(){
	

	
	add = new JButton("ADD");
	add.setBounds(350,640,90,25);
	add.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			checkInputs();
			
			
		}
		
	});
	add(add);
	
	
	
	edit = new JButton("EDIT");
	edit.setBounds(460,640,70,25);
	edit.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			EditSQL();
			
		}
		
	});
	add(edit);
	
	
	
	print = new JButton("PRINT");
	print.setBounds(550,640,90,25);
	print.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			printMe();
		}
		
	});
	add(print);
	
	
	exit = new JButton("EXIT");
	exit.setBounds(660,640,90,25);
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
	line.setBounds(0,615,1250,1);
	line.setOpaque(true);
	line.setBackground(Color.RED);
	add(line);
}


//CHECKING USER INPUTS
public void checkInputs(){
	
	
	
	
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
	
if(bplace.getText().toString().equals("")){
	bplace.setBackground(inputFailed);
	bplace.setForeground(changeInput);
	}else{
		bplace.setBackground(inputPass);
		bplace.setForeground(returnInput);
	}

if(address.getText().toString().equals("")){
	address.setBackground(inputFailed);
	address.setForeground(changeInput);
	}else{
		address.setBackground(inputPass);
		address.setForeground(returnInput);
	}

if(occu.getText().toString().equals("")){
	occu.setBackground(inputFailed);
	occu.setForeground(changeInput);
	}else{
		occu.setBackground(inputPass);
		occu.setForeground(returnInput);
	}

if(nation.getText().toString().equals("")){
	nation.setBackground(inputFailed);
	nation.setForeground(changeInput);
	}else{
		nation.setBackground(inputPass);
		nation.setForeground(returnInput);
	}

if(rel.getText().toString().equals("")){
	rel.setBackground(inputFailed);
	rel.setForeground(changeInput);
	}else{
		rel.setBackground(inputPass);
		rel.setForeground(returnInput);
	}

if(ward.getSelectedItem().toString().equals("")){
	ward.setBackground(inputFailed);
	ward.setForeground(changeInput);
	}else{
		ward.setBackground(inputPass);
		ward.setForeground(returnInput);
	}

if(wardType.getSelectedItem().toString().equals("")){
	wardType.setBackground(inputFailed);
	wardType.setForeground(changeInput);
	}else{
		wardType.setBackground(inputPass);
		wardType.setForeground(returnInput);
	}

if(ward.getSelectedItem().toString().equals("")){
	ward.setBackground(inputFailed);
	ward.setForeground(changeInput);
	}else{
		ward.setBackground(inputPass);
		ward.setForeground(returnInput);
	}

if(bed_no.getSelectedItem().toString().equals("")){
	bed_no.setBackground(inputFailed);
	bed_no.setForeground(changeInput);
	}else{
		bed_no.setBackground(inputPass);
		bed_no.setForeground(returnInput);
	}



if(bloodType.getSelectedItem().toString().equals("")){
	bloodType.setBackground(inputFailed);
	bloodType.setForeground(changeInput);
	}else{
		bloodType.setBackground(inputPass);
		bloodType.setForeground(returnInput);
	}


if(bloodPress.getText().toString().equals("")){
	bloodPress.setBackground(inputFailed);
	bloodPress.setForeground(changeInput);
	}else{
		bloodPress.setBackground(inputPass);
		bloodPress.setForeground(returnInput);
	}

if(insurance.getText().toString().equals("")){
	insurance.setBackground(inputFailed);
	insurance.setForeground(changeInput);
	}else{
		insurance.setBackground(inputPass);
		insurance.setForeground(returnInput);
	}

if(BMonth.getSelectedItem().equals("")){
	BMonth.setBackground(inputFailed);
	BMonth.setForeground(changeInput);
	}else{
		BMonth.setBackground(inputPass);
		BMonth.setForeground(returnInput);
	}

if(BDay.getSelectedItem().equals("")){
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

if(gender.getSelectedItem().equals("")){
	gender.setBackground(inputFailed);
	gender.setForeground(changeInput);
	}else{
		gender.setBackground(inputPass);
		gender.setForeground(returnInput);
	}

if(status.getSelectedItem().equals("")){
	status.setBackground(inputFailed);
	status.setForeground(changeInput);
	}else{
		status.setBackground(inputPass);
		status.setForeground(returnInput);
	}

if(diag.getText().toString().equals("")){
	diag.setBackground(inputFailed);
	diag.setForeground(changeInput);
	}else{
		diag.setBackground(inputPass);
		diag.setForeground(returnInput);
	}

if(diag.getText().toString().equals("")){
	diag.setBackground(inputFailed);
	diag.setForeground(changeInput);
	}else{
		diag.setBackground(inputPass);
		diag.setForeground(returnInput);
	}

	
	if(FN.getText().toString().equals("") ||  MN.getText().toString().equals("") || LN.getText().toString().equals("") ||
			age.getText().toString().equals("") ||  bplace.getText().toString().equals("") || address.getText().toString().equals("") || occu.getText().toString().equals("") || 
			nation.getText().toString().equals("") || rel.getText().toString().equals("") || ward.getSelectedItem().toString().equals("")
			|| wardType.getSelectedItem().equals("") || bloodType.getSelectedItem().equals("") || bloodPress.getText().toString().equals("")
			|| insurance.getText().toString().equals("")
			|| BMonth.getSelectedItem().equals("") || BDay.getSelectedItem().equals("") || BYr.getText().toString().equals("")
			|| gender.getSelectedItem().equals("") || status.getSelectedItem().equals("")
			|| diag.getText().toString().equals("") || ward.getSelectedItem().equals("") || bed_no.getSelectedItem().equals("")
			){
		
		JOptionPane.showMessageDialog(null,"Please Fill All Fields Required In Red Color!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
		
	}
	
	else{
		
		
		InsertSQL();
		
	}
	
}


//METHOD FOR ADD KONOYAROU!
public void InsertSQL(){
	
	Random rand_id = new Random();
	int checkID;
	
	checkID = rand_id.nextInt(9999999);
	
	PID.setText(checkID+"");
	

	  
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
String query = "INSERT INTO patient_info (patient_id,first_name,middle_name,last_name,birth_month,birth_day,birth_year,age,gender,birthplace,civil_status,occupation,address,nationality,religion,ward,ward_type,blood_type,blood_pressure,diagnosis,father,mother,parents_address,parents_contact,spouse,spouse_address,spouse_contact,h_plan,insurance,dependency,bed_no)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	
	
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);

	 	ps.setString(1, PID.getText().toString());

	 	ps.setString(2, FN.getText().toString()); 

	 	ps.setString(3, MN.getText().toString()); 

	 	ps.setString(4, LN.getText().toString()); 
	  
	 	ps.setString(5, (String) BMonth.getSelectedItem());

	 	ps.setString(6, (String) BDay.getSelectedItem()); 

	 	ps.setString(7, BYr.getText().toString()); 

	 	ps.setString(8, age.getText().toString());
	  
	 	ps.setString(9, (String) gender.getSelectedItem());

	 	ps.setString(10, bplace.getText().toString()); 

	 	ps.setString(11, (String) status.getSelectedItem()); 

	 	ps.setString(12, occu.getText().toString());

	 	ps.setString(13, address.getText().toString()); 

	 	ps.setString(14, nation.getText().toString()); 

	 	ps.setString(15, rel.getText().toString());
	 	
	 	ps.setString(16, ward.getSelectedItem().toString());
	  
	 	ps.setString(17, (String)wardType.getSelectedItem());

	 	ps.setString(18, (String)bloodType.getSelectedItem()); 
	 	
	 	ps.setString(19, bloodPress.getText().toString());
		  
	 	ps.setString(20, diag.getText().toString());

	 	ps.setString(21, dad.getText().toString()); 

	 	ps.setString(22, mom.getText().toString()); 

	 	ps.setString(23, parentsAddr.getText().toString());
	  
	 	ps.setString(24, parentsNo.getText().toString());

	 	ps.setString(25, spouse.getText().toString()); 

	 	ps.setString(26, SAddr.getText().toString()); 

	 	ps.setString(27, SNo.getText().toString());
	  
	 	ps.setString(28, HPlan.getText().toString());

	 	ps.setString(29, insurance.getText().toString()); 
	 	
	 	ps.setString(30, (String) dependency.getSelectedItem());
	 	
	 	ps.setString(31, (String) bed_no.getSelectedItem());


	 ps.execute();

	  //JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
	 
	  

conn.close();

}catch (Exception moriel){

 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

	} 


//INSERT CONSULTATION

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

	java.sql.Statement stmt = conn.createStatement();
		 
		 
	String query2 = "INSERT INTO consultation (patient_id, date, first_name,middle_name,last_name,birth_month,birth_day,birth_year,age,gender,address, civil_status, doctor,reason,orientation,fee)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query2);

	ps.setString(1, PID.getText().toString());

	ps.setString(2, "");

	ps.setString(3, FN.getText().toString()); 

	ps.setString(4, MN.getText().toString()); 

	ps.setString(5, LN.getText().toString()); 
	 
	ps.setString(6, BMonth.getSelectedItem().toString());

	ps.setString(7, BDay.getSelectedItem().toString()); 

	 ps.setString(8, BYr.getText().toString()); 

	 ps.setString(9, age.getText().toString());
	 
	 ps.setString(10, gender.getSelectedItem().toString());

	 ps.setString(11, address.getText().toString()); 

	 ps.setString(12, status.getSelectedItem().toString()); 

	 ps.setString(13, "");
	 
	 ps.setString(14, "");

	 ps.setString(15, ""); 

	 ps.setString(16, ""); 

		 

		 ps.execute();
		 
		// JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
		 
		 
		 conn.close();



		 }catch (Exception moriel){

		  		JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

		 	} 
		 


//CODE PARA SA INSERTING SA ADMISSION

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


		 
		 String query = "INSERT INTO admission (patient_id, date_month, date_day, date_year,date_hour,date_minutes,am_pm,first_name,middle_name,last_name,birth_month,birth_day,birth_year,birthplace,age,gender,civil_status,address,occupation,nationality,religion,ward,physician,father,mother,philhealth,diagnosis)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);

		 ps.setString(1, PID.getText().toString());

		  ps.setString(2, ""); 

		  ps.setString(3, ""); 

		  ps.setString(4, ""); 
		  
		  ps.setString(5, "");

		  ps.setString(6, ""); 
		  
		  ps.setString(7, "");

		  ps.setString(8, FN.getText().toString()); 

		  ps.setString(9, MN.getText().toString());
		  
		  ps.setString(10, LN.getText().toString());

		  ps.setString(11, BMonth.getSelectedItem().toString()); 

		  ps.setString(12, BDay.getSelectedItem().toString()); 

		  ps.setString(13, BYr.getText().toString());
		  
		  ps.setString(14, bplace.getText().toString());

		  ps.setString(15, age.getText().toString()); 

		  ps.setString(16, gender.getSelectedItem().toString()); 

		  ps.setString(17, status.getSelectedItem().toString());
		  
		  ps.setString(18, address.getText().toString());

		  ps.setString(19, occu.getText().toString()); 

		  ps.setString(20, nation.getText().toString()); 

		  ps.setString(21, rel.getText().toString());
		  
		  ps.setString(22, ward.getSelectedItem().toString());

		  ps.setString(23, ""); 

		  ps.setString(24, dad.getText().toString()); 

		  ps.setString(25, mom.getText().toString());
		  
		  ps.setString(26, "");

		  ps.setString(27, diag.getText().toString()); 


		 ps.execute();

		  //JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
		



	conn.close();

	}catch (Exception moriel){

	 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

		} 


//CODE PARA SA EDITING WARD FOR ADMISSION NG PATIENT
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
	String query = "UPDATE ward SET availability=? WHERE number = "+"\""+ward.getSelectedItem().toString()+"\""+"AND type = "+"\""+wardType.getSelectedItem()+"\""+"AND bed_no = "+"\""+bed_no.getSelectedItem()+"\"";;

	
	PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
	
	
	ps.setString(1, "Not Available");

	
    ps.execute();

	 //JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 

	conn.close();

	
	//this.user.enable(true);

	}catch (Exception moriel){

	 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

		} 






//CODES PARA SA INSERT BILLING

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

		 
		String query = "INSERT INTO billing (patient_id, first_name, middle_name, last_name, ward, ward_charge, ward_type, age, address, gender, phic, month, day, year, patient_case, no_days, doctor_charge, other_charge, bill,surgery,surgery_charge,laboratory,laboratory_charge)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		 
		 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
		 
		 

		  ps.setString(1, PID.getText().toString());

		  ps.setString(2, FN.getText().toString()); 

		  ps.setString(3, MN.getText().toString()); 

		  ps.setString(4, LN.getText().toString()); 
		  
		  ps.setString(5, ward.getSelectedItem().toString());
		  
		  ps.setDouble(6, 0); 
		  
		  ps.setString(7, (String) wardType.getSelectedItem()); 

		  ps.setString(8, age.getText().toString()); 

		  ps.setString(9, address.getText().toString());
		  
		  ps.setString(10, (String) gender.getSelectedItem());

		  ps.setString(11, ""); 
	 
		  ps.setString(12, ""); 

		  ps.setString(13, "");
		  
		  ps.setString(14, "");

		  ps.setString(15, ""); 

		  ps.setDouble(16, 0); 

		  ps.setDouble(17, 0);
		  
		  ps.setDouble(18, 0);

		  ps.setDouble(19, 0); 
		  
		  ps.setString(20, ""); 

		  ps.setString(21, "0"); 
		  
		  ps.setString(22, ""); 

		  ps.setString(23, "0");  
		  

		
		 ps.execute();

		//  JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
		 


	conn.close();

	}catch (Exception moriel){

	 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

		} 


//CODES PARA SA INSERTING SA PARTIAL BILLING
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

		 
		String query = "INSERT INTO partial_billing (patient_id, first_name, middle_name, last_name, ward_no, ward_charge, ward_type, age, gender, num_days, doctor_charge, other_charge, partial_bill)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		 
		 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
		 
		 

		  ps.setString(1, PID.getText().toString());

		  ps.setString(2, FN.getText().toString()); 

		  ps.setString(3, MN.getText().toString()); 

		  ps.setString(4, LN.getText().toString()); 
		  
		  ps.setString(5, ward.getSelectedItem().toString());
		  
		  ps.setString(6, ""); 
		  
		  ps.setString(7, (String) wardType.getSelectedItem()); 

		  ps.setString(8, age.getText().toString()); 
		  
		  ps.setString(9, (String) gender.getSelectedItem());

		  ps.setString(10, ""); 

		  ps.setString(11, "");
		  
		  ps.setString(12, "");

		  ps.setString(13, ""); 
		  
		
		 ps.execute();

		  //JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
		 
		 


	conn.close();

	}catch (Exception moriel){

	 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

		} 



//CODES PARA SA INSERTING SA DISCHARGE
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

String query = "INSERT INTO discharge (patient_id, case_no, first_name, middle_name, last_name, address, ward, ward_type, admit_date, discharge_date, days, disposition, result, physician, admit_diagnosis, final_diagnosis, other_diagnosis,bed_no)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	 
	 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
	 

	 ps.setString(1, PID.getText().toString());
	 
	 ps.setString(2, ""); 

	 ps.setString(3, FN.getText().toString()); 

	ps.setString(4, MN.getText().toString()); 
	  
	ps.setString(5,  LN.getText().toString());
	
	 ps.setString(6, address.getText().toString()); 

	 ps.setString(7, ward.getSelectedItem().toString());
	 
	 ps.setString(8, (String) wardType.getSelectedItem());

	ps.setString(9,  ""); 

	ps.setString(10, ""); 

	ps.setString(11, "");
	  
	ps.setString(12, "");

	ps.setString(13, ""); 

	 ps.setString(14, ""); 

	  ps.setString(15, "");
	  
	  ps.setString(16, "");

	  ps.setString(17, ""); 
	  
	  ps.setString(18, bed_no.getSelectedItem().toString()); 
	  


	 ps.execute();

	  //JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 



conn.close();

}catch (Exception moriel){

		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 




} 



//CODES PARA SA INSERTING SA PHARMACY BILLING INFO NAHIRAPAN TALAGA AKO SA CLASS NA UN CHIKUSHO!
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
	String query = "INSERT INTO pharmacy_patient_billing (patient_id,first_name,middle_name,last_name,age,gender,address,occupation,status,bill,payment,balance," +
			"		meds1,meds2,meds3,meds4,meds5,meds6,meds7,meds8,meds9,meds10," +
			"quantity1,quantity2,quantity3,quantity4,quantity5,quantity6,quantity7,quantity8,quantity9,quantity10," +
			"price1,price2,price3,price4,price5,price6,price7,price8,price9,price10)" 
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
					"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


	java.sql.Statement stmt = conn.createStatement();


		 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);

		 
		 	ps.setString(1, PID.getText().toString());
		 	ps.setString(2, FN.getText().toString());
		 	ps.setString(3, MN.getText().toString());
		 	ps.setString(4, LN.getText().toString());
		 	ps.setString(5, age.getText().toString());
		 	ps.setString(6, gender.getSelectedItem().toString());
		 	ps.setString(7, address.getText().toString());
		 	ps.setString(8, occu.getText().toString());
		 	ps.setString(9, status.getSelectedItem().toString());
		 	ps.setString(10, "");
		 	ps.setString(11, "");
		 	ps.setString(12, "");
		 	ps.setString(13, "");
		 	ps.setString(14, "");
		 	ps.setString(15, "");
		 	ps.setString(16, "");
		 	ps.setString(17, "");
		 	ps.setString(18, "");
		 	ps.setString(19, "");
		 	ps.setString(20, "");
		 	ps.setString(21, "");
		 	ps.setString(22, "");
		 	ps.setString(23, "0");
		 	ps.setString(24, "0");
		 	ps.setString(25, "0");
		 	ps.setString(26, "0");
		 	ps.setString(27, "0");
		 	ps.setString(28, "0");
		 	ps.setString(29, "0");
		 	ps.setString(30, "0");
		 	ps.setString(31, "0");
		 	ps.setString(32, "0");
		 	ps.setString(33, "0");
		 	ps.setString(34, "0");
		 	ps.setString(35, "0");
		 	ps.setString(36, "0");
		 	ps.setString(37, "0");
		 	ps.setString(38, "0");
		 	ps.setString(39, "0");
		 	ps.setString(40, "0");
		 	ps.setString(41, "0");
		 	ps.setString(42, "0");

		   

		    ps.execute();

		  JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
		 
		

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
   	  
    sql = "SELECT * FROM patient_info";
    ResultSet rs = stmt.executeQuery(sql);
		
		 table.setModel(DbUtils.resultSetToTableModel(rs));

}catch (Exception moriel){

		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

}



getPatientCount();

ClearFields();
  


} 




//MUNTIK KO NG HINDI MAKITA ANG METHOD NA ITO SA DAMI NG CODES
//MORIEL!!!! HUHU!
public void ClearFields(){
	
	
	  //PID.setText("");
	  	FN.setText(""); 
	 	MN.setText(""); 
	 	LN.setText(""); 
	 	BMonth.setSelectedItem("");
	 	BDay.setSelectedItem(""); 
	 	BYr.setText("");  
	 	age.setText(""); 
	 	gender.setSelectedItem("");
	 	bplace.setText(""); 
	 	status.setSelectedItem("");  
	 	occu.setText(""); 
	 	address.setText(""); 
	 	nation.setText(""); 
	 	rel.setText(""); 
	 	bloodType.setSelectedItem(""); 
	 	bloodPress.setText(""); 
	 	diag.setText(""); 
	 	dad.setText(""); 
	 	mom.setText(""); 
	 	parentsAddr.setText(""); 
	 	parentsNo.setText(""); 
	 	spouse.setText(""); 
	 	SAddr.setText("");  
	 	SNo.setText(""); 
	 	HPlan.setText(""); 
	 	insurance.setText(""); 
	 	dependency.setSelectedItem(""); 
	 
	 	
	 	
	
	
}



//METHOD FOR SEARCHING ACCOUNT BAKAYAROU!
public void SearchSQL(){
	
	ClearFields();
	  
	//ETO UNG SINASABI KONG TECHNIQUE PARA MAKUHA ANG VALUE NG WARD, TYPE, AT BED NO
	//MAMAYA SA EDITSQL ISESET ULIT NATIN ANG VISIBILITY
	ward.setVisible(false);
	wardType.setVisible(false);
	bed_no.setVisible(false);
	
	getWard.setVisible(true);
	getType.setVisible(true);
	getBed.setVisible(true);
	
	
if(PID.getText().toString().equals("")){
		
		ward.setVisible(true);
		wardType.setVisible(true);
		bed_no.setVisible(true);
		
		getWard.setVisible(false);
		getType.setVisible(false);
		getBed.setVisible(false);
		
	}
	
	
	
	String check = "SELECT * FROM patient_info WHERE patient_id="+"\""+PID.getText().toString()+"\"";

	
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
				 BMonth.setSelectedItem(rs.getString("birth_month"));
				 BDay.setSelectedItem(rs.getString("birth_day")); 
				 BYr.setText(rs.getString("birth_year"));  
				 age.setText(rs.getString("age")); 
				 gender.setSelectedItem(rs.getString("gender"));
				 bplace.setText(rs.getString("birthplace")); 
				 status.setSelectedItem(rs.getString("civil_status")); 
				 occu.setText(rs.getString("occupation")); 
				 address.setText(rs.getString("address")); 
				 nation.setText(rs.getString("nationality")); 
				 rel.setText(rs.getString("religion")); 
				 getWard.setText(rs.getString("ward"));
				 getType.setText(rs.getString("ward_type"));
				 bloodType.setSelectedItem(rs.getString("blood_type"));
				 bloodPress.setText(rs.getString("blood_pressure")); 
				 diag.setText(rs.getString("diagnosis")); 
				 dad.setText(rs.getString("father")); 
				 mom.setText(rs.getString("mother")); 
				 parentsAddr.setText(rs.getString("parents_address")); 
				 parentsNo.setText(rs.getString("parents_contact")); 
				 spouse.setText(rs.getString("spouse")); 
				 SAddr.setText(rs.getString("spouse_address"));  
				 SNo.setText(rs.getString("spouse_contact")); 
				 HPlan.setText(rs.getString("h_plan")); 
				 insurance.setText(rs.getString("insurance")); 
				 dependency.setSelectedItem(rs.getString("dependency")); 
				 getBed.setText(rs.getString("bed_no")); 

				
				
		counter++;
			 }
			 

			 if(counter<1){
				 
				 //JOptionPane.showMessageDialog(null,"Record Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }

		conn.close();

		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 

			} 

} 


//METHOD PARA SA TABLE ROW CLICKING SEARCH
public void tableClickSearch(){
	  
	//ETO UNG SINASABI KONG TECHNIQUE PARA MAKUHA ANG VALUE NG WARD, TYPE, AT BED NO
	//MAMAYA SA EDITSQL ISESET ULIT NATIN ANG VISIBILITY
	ward.setVisible(false);
	wardType.setVisible(false);
	bed_no.setVisible(false);
	
	getWard.setVisible(true);
	getType.setVisible(true);
	getBed.setVisible(true);
	
	
	
	String check = "SELECT * FROM patient_info WHERE patient_id="+"\""+table.getValueAt(table.getSelectedRow(), 1)+"\"";

	
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
				 BMonth.setSelectedItem(rs.getString("birth_month"));
				 BDay.setSelectedItem(rs.getString("birth_day")); 
				 BYr.setText(rs.getString("birth_year"));  
				 age.setText(rs.getString("age")); 
				 gender.setSelectedItem(rs.getString("gender"));
				 bplace.setText(rs.getString("birthplace")); 
				 status.setSelectedItem(rs.getString("civil_status")); 
				 occu.setText(rs.getString("occupation")); 
				 address.setText(rs.getString("address")); 
				 nation.setText(rs.getString("nationality")); 
				 rel.setText(rs.getString("religion")); 
				 getWard.setText(rs.getString("ward"));
				 getType.setText(rs.getString("ward_type"));
				 bloodType.setSelectedItem(rs.getString("blood_type"));
				 bloodPress.setText(rs.getString("blood_pressure")); 
				 diag.setText(rs.getString("diagnosis")); 
				 dad.setText(rs.getString("father")); 
				 mom.setText(rs.getString("mother")); 
				 parentsAddr.setText(rs.getString("parents_address")); 
				 parentsNo.setText(rs.getString("parents_contact")); 
				 spouse.setText(rs.getString("spouse")); 
				 SAddr.setText(rs.getString("spouse_address"));  
				 SNo.setText(rs.getString("spouse_contact")); 
				 HPlan.setText(rs.getString("h_plan")); 
				 insurance.setText(rs.getString("insurance")); 
				 dependency.setSelectedItem(rs.getString("dependency")); 
				 getBed.setText(rs.getString("bed_no")); 

				
				
		counter++;
			 }
			 

			 if(counter<1){
				 
				 JOptionPane.showMessageDialog(null,"Record Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
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
			String query = "UPDATE patient_info SET first_name=?,middle_name=?,last_name=?,birth_month=?,birth_day=?,birth_year=?,age=?,gender=?,birthplace=?,civil_status=?,occupation=?,address=?,nationality=?,religion=?,ward=?,ward_type=?,blood_type=?,blood_pressure=?,diagnosis=?,father=?,mother=?,parents_address=?,parents_contact=?,spouse=?,spouse_address=?,spouse_contact=?,h_plan=?,insurance=?,dependency=?, bed_no=? WHERE patient_id = "+"\""+this.PID.getText().toString()+"\"";

			
			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			
		
			
			ps.setString(1, FN.getText().toString()); 

		 	ps.setString(2, MN.getText().toString()); 

		 	ps.setString(3, LN.getText().toString()); 
		  
		 	ps.setString(4, (String) BMonth.getSelectedItem());

		 	ps.setString(5, (String) BDay.getSelectedItem()); 

		 	ps.setString(6, BYr.getText().toString()); 

		 	ps.setString(7, age.getText().toString());
		  
		 	ps.setString(8, (String) gender.getSelectedItem());

		 	ps.setString(9, bplace.getText().toString()); 

		 	ps.setString(10, (String) status.getSelectedItem()); 

		 	ps.setString(11, occu.getText().toString());

		 	ps.setString(12, address.getText().toString()); 

		 	ps.setString(13, nation.getText().toString()); 

		 	ps.setString(14, rel.getText().toString());
		 	
		 	ps.setString(15, getWard.getText().toString());
		  
		 	ps.setString(16, getType.getText().toString());

		 	ps.setString(17, (String)bloodType.getSelectedItem()); 
		 	
		 	ps.setString(18, bloodPress.getText().toString());
			  
		 	ps.setString(19, diag.getText().toString());

		 	ps.setString(20, dad.getText().toString()); 

		 	ps.setString(21, mom.getText().toString()); 

		 	ps.setString(22, parentsAddr.getText().toString());
		  
		 	ps.setString(23, parentsNo.getText().toString());

		 	ps.setString(24, spouse.getText().toString()); 

		 	ps.setString(25, SAddr.getText().toString()); 

		 	ps.setString(26, SNo.getText().toString());
		  
		 	ps.setString(27, HPlan.getText().toString());

		 	ps.setString(28, insurance.getText().toString()); 
		 	
		 	ps.setString(29, (String) dependency.getSelectedItem());
		 	
		 	ps.setString(30, getBed.getText().toString());



			ps.execute();

			 JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 

			conn.close();
			

			}catch (Exception moriel){

			 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

				} 
		
		
		
		
		
		//EDIT CONSULTATION

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

			java.sql.Statement stmt = conn.createStatement();
				 
				 
			//set the sql query
			String query2 = "UPDATE consultation SET patient_id=?,first_name=?, middle_name=?, last_name=?, birth_month=?, birth_day=?, birth_year=?, age=?, gender=?, address=?, civil_status=? WHERE patient_id = "+"\""+this.PID.getText().toString()+"\"";

			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query2);

			ps.setString(1, PID.getText().toString());

			ps.setString(2, FN.getText().toString()); 

			ps.setString(3, MN.getText().toString()); 

			ps.setString(4, LN.getText().toString()); 
			 
			ps.setString(5, BMonth.getSelectedItem().toString());

			ps.setString(6, BDay.getSelectedItem().toString()); 

			 ps.setString(7, BYr.getText().toString()); 

			 ps.setString(8, age.getText().toString());
			 
			 ps.setString(9, gender.getSelectedItem().toString());

			 ps.setString(10, address.getText().toString()); 

			 ps.setString(11, status.getSelectedItem().toString()); 
				 

				 ps.execute();
				 
				 //JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
				 
				 
				 conn.close();



				 }catch (Exception moriel){

				  		JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

				 	} 
				 


		//CODE PARA SA EDITING ADMISSION
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


				String query = "UPDATE admission SET patient_id=?, first_name=?, middle_name=?, last_name=?, birth_month=?, birth_day=?, birth_year=?, birthplace=?,age=?,gender=?, civil_status=?, address=?,occupation=?,nationality=?,religion=?,ward=?,father=?,mother=?, diagnosis=? WHERE patient_id = "+"\""+this.PID.getText().toString()+"\"";
	 
				 			
				 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);

				 ps.setString(1, PID.getText().toString());

				  ps.setString(2, FN.getText().toString()); 

				  ps.setString(3, MN.getText().toString());
				  
				  ps.setString(4, LN.getText().toString());

				  ps.setString(5, BMonth.getSelectedItem().toString()); 

				  ps.setString(6, BDay.getSelectedItem().toString()); 

				  ps.setString(7, BYr.getText().toString());
				  
				  ps.setString(8, bplace.getText().toString());

				  ps.setString(9, age.getText().toString()); 

				  ps.setString(10, gender.getSelectedItem().toString()); 

				  ps.setString(11, status.getSelectedItem().toString());
				  
				  ps.setString(12, address.getText().toString());

				  ps.setString(13, occu.getText().toString()); 

				  ps.setString(14, nation.getText().toString()); 

				  ps.setString(15, rel.getText().toString());
				  
				  ps.setString(16, getWard.getText().toString());

				  ps.setString(17, dad.getText().toString()); 

				  ps.setString(18, mom.getText().toString());
				  
				  ps.setString(19, diag.getText().toString()); 


				 ps.execute();

				  //JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
				



			conn.close();

			}catch (Exception moriel){

			 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

				} 


		//CODE PARA SA EDITING WARD FOR ADMISSION NG PATIENT
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
			String query = "UPDATE ward SET availability=? WHERE number = "+"\""+getWard.getText().toString()+"\""+"AND type = "+"\""+getType.getText().toString()+"\""+"AND bed_no = "+"\""+getBed.getText().toString()+"\"";;

			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			
			
			ps.setString(1, "Not Available");

			
		    ps.execute();

			 //JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 

			conn.close();

			
			//this.user.enable(true);

			}catch (Exception moriel){

			 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

				} 






		//CODES PARA SA EDITNG BILLING
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

				 
			//set the sql query
			String query = "UPDATE billing SET patient_id=?, first_name=?, middle_name=?, last_name=?, ward=?, ward_type=?, age=?, address=?, gender=? WHERE patient_id = "+"\""+this.PID.getText().toString()+"\"";
			
				 
				 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
				 
				 

				  ps.setString(1, PID.getText().toString());

				  ps.setString(2, FN.getText().toString()); 

				  ps.setString(3, MN.getText().toString()); 

				  ps.setString(4, LN.getText().toString()); 
				  
				  ps.setString(5, getWard.getText().toString());
				  
				  ps.setString(6, getType.getText().toString()); 

				  ps.setString(7, age.getText().toString()); 

				  ps.setString(8, address.getText().toString());
				  
				  ps.setString(9, (String) gender.getSelectedItem());
				  

				
				 ps.execute();

				//  JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
				 


			conn.close();

			}catch (Exception moriel){

			 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

				} 


		//CODES PARA SA EDIT PARTIAL BILLING
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

			//set the sql query
			String query = "UPDATE partial_billing SET patient_id=?, first_name=?, middle_name=?, last_name=?, ward_no=?, ward_type=?, age=?, gender=? WHERE patient_id = "+"\""+this.PID.getText().toString()+"\"";
			
				 
				 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
				 
				 

				  ps.setString(1, PID.getText().toString());

				  ps.setString(2, FN.getText().toString()); 

				  ps.setString(3, MN.getText().toString()); 

				  ps.setString(4, LN.getText().toString()); 
				  
				  ps.setString(5, getWard.getText().toString());
				  
				  ps.setString(6, getType.getText().toString()); 

				  ps.setString(7, age.getText().toString()); 
				  
				  ps.setString(8, (String) gender.getSelectedItem());

				
				  
				
				 ps.execute();

				  //JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
				 
				 


			conn.close();

			}catch (Exception moriel){

			 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

				} 



		//CODES PARA SA EDITING	 DISCHARGE
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

		//set the sql query
		String query = "UPDATE discharge SET patient_id=?, first_name=?, middle_name=?, last_name=?, address=?, ward=?, ward_type=?, bed_no=? WHERE patient_id = "+"\""+this.PID.getText().toString()+"\"";

		
			 
			 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			 

			 ps.setString(1, PID.getText().toString());

			 ps.setString(2, FN.getText().toString()); 

			ps.setString(3, MN.getText().toString()); 
			  
			ps.setString(4,  LN.getText().toString());
			
			 ps.setString(5, address.getText().toString()); 

			 ps.setString(6, getWard.getText().toString());
			 
			 ps.setString(7, getType.getText().toString());

			  ps.setString(8, getBed.getText().toString()); 
			  


			 ps.execute();

			  //JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 



		conn.close();

		}catch (Exception moriel){

				 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 




		} 

		
		
		//CODES PARA SA EDITING PHARMACY BILLING
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
			String query = "UPDATE pharmacy_patient_billing SET patient_id=?,first_name=?,middle_name=?,last_name=?,age=?,gender=?,address=?,occupation=?,status=?" +
					"WHERE patient_id = "+"\""+PID.getText().toString()+"\"";


			java.sql.Statement stmt = conn.createStatement();


				 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);

				 
				 	ps.setString(1, PID.getText().toString());
				 	ps.setString(2, FN.getText().toString());
				 	ps.setString(3, MN.getText().toString());
				 	ps.setString(4, LN.getText().toString());
				 	ps.setString(5, age.getText().toString());
				 	ps.setString(6, gender.getSelectedItem().toString());
				 	ps.setString(7, address.getText().toString());
				 	ps.setString(8, occu.getText().toString());
				 	ps.setString(9, status.getSelectedItem().toString());
				 	

				    ps.execute();

				//  JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
				 
				

			conn.close();

			}catch (Exception moriel){

			 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

				} 
		
		
	}else if(ans==JOptionPane.NO_OPTION){
		
		
	}else if(ans ==JOptionPane.CANCEL_OPTION){
		
	}else if(ans ==JOptionPane.CLOSED_OPTION){
		
	}
	

	//AFTER EDITING, SETFIELDS SA DEFAULT VISIBILITY
	ward.setVisible(true);
	wardType.setVisible(true);
	bed_no.setVisible(true);
	
	getWard.setVisible(false);
	getType.setVisible(false);
	getBed.setVisible(false);
	
	  
	
	
	String URL = "jdbc:mysql://localhost:3306/h_m_s";
	String userid = "root";
	String password = "";
	String sql;

	try  {
		
		Connection connection = (Connection) DriverManager.getConnection( URL, userid, password );
	    Statement stmt = connection.createStatement();
	   	  
	    sql = "SELECT * FROM patient_info";
	    ResultSet rs = stmt.executeQuery(sql);
			
			 table.setModel(DbUtils.resultSetToTableModel(rs));

	}catch (Exception moriel){

			 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

	}
	
	ClearFields();
	
	getPatientCount();
	  
	
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
        	  
         sql = "SELECT * FROM patient_info";
         ResultSet rs = stmt.executeQuery(sql);
         table = new JTable(model);
   		 JScrollPane scrollPane = new JScrollPane(table);
   		 table.setPreferredScrollableViewportSize(new Dimension(500, 70));
   		 table.setFillsViewportHeight(true);
   		 table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
   		 table.setBackground(Color.LIGHT_GRAY);
   		 scrollPane.setBounds(470,80,750,240);
   		 scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
   		 scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
   		 add(scrollPane);
   		 table.setModel(DbUtils.resultSetToTableModel(rs));
         table.addMouseListener(this);
        
         
    
	 }catch(Exception moriel){
		 
		 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
	 }
    
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
        	  
        	sql = "SELECT * FROM patient_info WHERE patient_id LIKE "+"\""+PID.getText().toString()+"%\"";
        	ResultSet rs = stmt.executeQuery(sql);
        	table.setModel(DbUtils.resultSetToTableModel(rs));
         
    
	 }catch(Exception moriel){
		 
		 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
	 }
	 
	
}



//ITO UNG PAG DRAWING NG MGA STUFFS
//MADUGONG LABANAN TALAGA TO! Moriel!!!
@Override
public int print(Graphics g, PageFormat pf, int page)
		throws PrinterException {
	// TODO Auto-generated method stub
	
	 logo = Toolkit.getDefaultToolkit().getImage("res/logo.jpg");
	
	 if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

       
        /* Now we perform our rendering */
     
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
      	 Date date = new Date();

          
           /* Now we perform our rendering */
           g.drawImage(logo, 270, 10, 40, 40, this);

           g.setFont(new Font("Tahoma",Font.BOLD,20));
           g.setColor(Color.RED);
           g.drawString(" HOSPITAL MANAGEMENT SYSTEM", 160, 70);
          
           
           g.setFont(new Font("Tahoma",Font.ITALIC,8));
           g.setColor(Color.BLACK);
           g.drawString("Arellano Street, Dagupan City 2400, Pangasinan, Philippines", 190,80);

           //draw line separator
           g.drawString("_______________________________________" +
           		"________________________________________________", 70, 90);
           
           g.setFont(new Font("Tahoma",Font.ITALIC,10));
           g.setColor(Color.BLACK);
           g.drawString("Patient Information", 230,105);
           
           
           g.setFont(new Font("Tahoma",Font.ITALIC,7));
           g.setColor(Color.BLACK);
           g.drawString(dateFormat.format(date), 410,105);
           
           
         
           g.setFont(new Font("Tahoma",Font.PLAIN,8));
           g.setColor(Color.BLACK);
           g.drawString("Patient ID: "+PID.getText().toString(), 100,130);
        
        g.drawString("Name: "+FN.getText().toString()+" "+MN.getText().toString()+" "+LN.getText().toString(), 100,140);
        
        g.drawString("Birthday: "+BMonth.getSelectedItem()+" "+BDay.getSelectedItem()+", "+BYr.getText().toString(), 100,150);
        
        g.drawString("Age : "+age.getText().toString(), 230,150);
        
        g.drawString("Gender : "+gender.getSelectedItem(), 280,150);
        
        g.drawString("Civil Status: "+status.getSelectedItem(), 370,150);
        
        g.drawString("Address: "+address.getText().toString(), 100,160);

        g.drawString("Nationality: "+nation.getText().toString(), 100,170);
        
        g.drawString("Religion: "+rel.getText().toString(), 230,170);

        g.drawString("Room/Ward: "+ward.getSelectedItem().toString(), 370,170);
        
        g.drawString("Birthplace: "+bplace.getText().toString(), 100,180);
        
        g.drawString("Occupation: "+occu.getText().toString(), 280,180);

        g.drawString("Diagnosis: "+diag.getText().toString(), 100,190);      
        
        g.drawString("Father's Name: "+dad.getText().toString(), 100,220);
        
        g.drawString("Mother's Name: "+mom.getText().toString(), 310,220);
        
        g.drawString("Parent's Address: "+parentsAddr.getText().toString(), 100,230);
        
        g.drawString("Parent's Contact No: "+parentsNo.getText().toString(), 100,240);
        
        g.drawString("Spouse: "+spouse.getText().toString(), 280,240);
        
        g.drawString("Spouse Contact No: "+SNo.getText().toString(), 320,250);
        
        g.drawString("Spouse Address: "+SAddr.getText().toString(), 100,250);
        
        g.drawString("Type Of Dependency: "+dependency.getSelectedItem(), 100,260);
        
        g.drawString("Health Insurance: "+insurance.getText().toString(), 280,260);
        
        g.drawString("Hospitalization Plan: "+HPlan.getText().toString(), 100,270);


        //draw line separator
        g.drawString("_______________________________________" +
        		"________________________________________________", 70, 290);
      
        
        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.drawString("Email Address: region1mc2003@yahoo.com", 100,305);
        
        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.drawString("Telephone Numbers: (075) 515-89-16 ; (075)515-89-18 ; (075)515-89-01", 100,315);

        g.setFont(new Font("Tahoma",Font.ITALIC,10));
        g.drawString("Fax: (075) 523-41-03", 100,325);


        
        //LINE SEPARATOR FOR CUTTING NANDE KUSO
        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.setColor(Color.BLACK);
        //draw line separator
        g.drawString("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ " +
        		"_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ " +
        		"_ _ _ _ _ _ _ _ _ _ _ _", 70, 340);

          
         
        //2ND COPY
           g.drawImage(logo, 270, 360, 40, 40, this);

           g.setFont(new Font("Tahoma",Font.BOLD,20));
           g.setColor(Color.RED);
           g.drawString(" HOSPITAL MANAGEMENT SYSTEM", 160, 420);
          
           
           g.setFont(new Font("Tahoma",Font.ITALIC,8));
           g.setColor(Color.BLACK);
           g.drawString("Arellano Street, Dagupan City 2400, Pangasinan, Philippines", 190,430);

           //draw line separator
           g.drawString("_______________________________________" +
           		"________________________________________________", 70, 440);
           
           g.setFont(new Font("Tahoma",Font.ITALIC,10));
           g.setColor(Color.BLACK);
           g.drawString("Patient Information", 230,455);
           
           
           g.setFont(new Font("Tahoma",Font.ITALIC,7));
           g.setColor(Color.BLACK);
           g.drawString(dateFormat.format(date), 410,455);
           
           
         
           g.setFont(new Font("Tahoma",Font.PLAIN,8));
           g.setColor(Color.BLACK);
           g.drawString("Patient ID: "+PID.getText().toString(), 100,470);
        
        g.drawString("Name: "+FN.getText().toString()+" "+MN.getText().toString()+" "+LN.getText().toString(), 100,480);
        
        g.drawString("Birthday: "+BMonth.getSelectedItem()+" "+BDay.getSelectedItem()+", "+BYr.getText().toString(), 100,490);
        
        g.drawString("Age : "+age.getText().toString(), 230,490);
        
        g.drawString("Gender : "+gender.getSelectedItem(), 280,490);
        
        g.drawString("Civil Status: "+status.getSelectedItem(), 370,490);
        
        g.drawString("Address: "+address.getText().toString(), 100,500);

        g.drawString("Nationality: "+nation.getText().toString(), 100,510);
        
        g.drawString("Religion: "+rel.getText().toString(), 230,510);

        g.drawString("Room/Ward: "+ward.getSelectedItem().toString(), 370,510);
        
        g.drawString("Birthplace: "+bplace.getText().toString(), 100,520);
        
        g.drawString("Occupation: "+occu.getText().toString(), 280,520);

        g.drawString("Diagnosis: "+diag.getText().toString(), 100,530);      
        
        g.drawString("Father's Name: "+dad.getText().toString(), 100,560);
        
        g.drawString("Mother's Name: "+mom.getText().toString(), 310,560);
        
        g.drawString("Parent's Address: "+parentsAddr.getText().toString(), 100,570);
        
        g.drawString("Parent's Contact No: "+parentsNo.getText().toString(), 100,580);
        
        g.drawString("Spouse: "+spouse.getText().toString(), 280,580);
        
        g.drawString("Spouse Contact No: "+SNo.getText().toString(), 320,590);
        
        g.drawString("Spouse Address: "+SAddr.getText().toString(), 100,590);
        
        g.drawString("Type Of Dependency: "+dependency.getSelectedItem(), 100,600);
        
        g.drawString("Health Insurance: "+insurance.getText().toString(), 280,600);
        
        g.drawString("Hospitalization Plan: "+HPlan.getText().toString(), 100,610);


        //draw line separator
        g.drawString("_______________________________________" +
        		"________________________________________________", 70, 630);
      
        
        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.drawString("Email Address: region1mc2003@yahoo.com", 100,645);
        
        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.drawString("Telephone Numbers: (075) 515-89-16 ; (075)515-89-18 ; (075)515-89-01", 100,655);

        g.setFont(new Font("Tahoma",Font.ITALIC,10));
        g.drawString("Fax: (075) 523-41-03", 100,665);

        
        
        
        
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


public void getPatientCount(){
	
	
String query = "SELECT * FROM patient_info ORDER BY patient_count ASC";

	
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

				 
				patient_count.setText("  No. Of Patients:  "+rs.getInt("patient_count"));
				
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


	
	
	
//METHOD PARA SA EXIT BUTTON
public void CloseMe(){
	this.dispose();
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
		        	  
		        	sql = "SELECT * FROM patient_info WHERE last_name LIKE "+"\""+LN.getText().toString()+"%\"";
		        	ResultSet rs = stmt.executeQuery(sql);
		        	table.setModel(DbUtils.resultSetToTableModel(rs));
		         
		    
			 }catch(Exception moriel){
				 
				 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }
			 
			
		}

	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
