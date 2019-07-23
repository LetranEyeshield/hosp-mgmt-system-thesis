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
import javax.swing.JCheckBox;
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

public class Admission extends JFrame implements ActionListener, Printable, MouseListener, KeyListener{
	
	JLabel labels,title;
	
	Random rand_id = new Random();
	int checkID;
	
	JTextField PID, FN, MN, LN, age, bplace, address, occu, nation, rel, ward, psy, dad, mom;
	
	JButton  clear, edit, exit, print;
	
	JTable table;

	DefaultTableModel model;
	
	//VARS FOR BIRTH DATE
	String BM[] = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	String getBMonth;

	String BD[] = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	JComboBox BMonth, BDay;
	String getBDay;

	JTextField BYr;
	
	//VARS FOR TIME
	String Hrs[] = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
	String getHour;

	String min[] = {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
	JComboBox Hour, Min;
	String getMin;
	
	String AMPM[] = {"AM/PM", "am", "pm"};
	JComboBox ampm;
	
	
	//VARS FOR DATE
	String M[] = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	String getMonth;

	String D[] = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	JComboBox Month, Day;
	String getDay;

	JTextField Yr;
	
	
	String G[] = {"", "Male", "Female"};
	String setGender, getGender;
	JComboBox gender;
	
	String S[] = {"", "Single", "Married", "Widowed","Divorced","Separated"};
	String getStatus;
	JComboBox status;
	
	
	JTextArea diag;
	JScrollPane scroll;
	//wala akong maisip na magandang variable para sa philhealth eh!
	//Ano ka ba, Akong programmer dito ano! Sayaw kana lang! Haha!
	JCheckBox ph;
	String ppap, pineapplePen;
	boolean applePen;
	
	
	//VARS PARA SA PRINTING
	Graphics2D g2;
	Image logo;
	
	//COLORS PARA SA CHECKING INPUTS
	Color inputFailed = Color.RED;
	Color changeInput = Color.WHITE;
	Color returnInput = Color.BLACK;
	Color inputPass = Color.WHITE;
	
	
	//kailangan para sa rendering ng data sa database mamaya
		ResultSet rs;
		
		//PREPARE THE CLASS PARA SA LAST NAME SEARCHING
		Moriel Kim = new Moriel();
	
	public Admission(){
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
				"                    PATIENT ADMISSION");
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
	labels.setFont(new Font("Tahoma",Font.PLAIN,14));
	labels.setForeground(Color.WHITE);
	add(labels);
	
}


public void setBackground(){

	JLabel background = new JLabel(new ImageIcon("res/admission.png"));
	 background.setBounds(0,0,1250,660);
	 background.setBackground(new Color(0,0,255));
	// background.setBackground(Color.LIGHT_GRAY);
	 background.setOpaque(true);
	 add(background);
	 
}

public void setLabels(){
	
	prepareLabels(" Patient ID:",30,90,90,18);
	prepareLabels(" Date:",360,90,80,18);
	prepareLabels(" Time:",360,120,80,18);
	prepareLabels(" First Name:",30,120,90,18);
	prepareLabels(" Middle Name:",30,150,90,18);
	prepareLabels(" Last Name:",30,180,90,18);
	prepareLabels(" Birthday:",30,210,90,18);
	prepareLabels(" Age:",360,150,80,18);
	prepareLabels(" Gender:",360,180,80,20);
	prepareLabels(" Birthplace:",30,240,90,18);
	prepareLabels(" Civil Status:",360,240,80,18);
	prepareLabels(" Address:",360,210,80,18);
	prepareLabels(" Occupation:",360,270,80,18);
	prepareLabels(" Nationality:",30,270,90,18);
	prepareLabels(" Religion:",30,300,90,18);
	
	prepareLabels(" Ward/Room:",30,380,130,18);
	prepareLabels(" Attending Physician:",30,410,130,18);
	prepareLabels(" Father's Name:",390,380,170,18);
	prepareLabels(" Mother's Name:",390,410,170,18);
	prepareLabels(" Patient Problem/Diagnosis:",390,440,170,18);
	

	
}


public void setPanels(){
	
	JLabel panel1 = new JLabel();
	panel1.setBounds(20,80,760,250);
	panel1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel1);
	
	JLabel panel2 = new JLabel();
	panel2.setBounds(800,80,420,430);
	panel2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel2);
	
	JLabel panel3 = new JLabel();
	panel3.setBounds(20,370,760,140);
	panel3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel3);
	
	JLabel panelLabel1 = new JLabel("  Personal Information ");
	panelLabel1.setBounds(20,50,130,20);
	panelLabel1.setOpaque(true);
	panelLabel1.setBackground(Color.CYAN);
	add(panelLabel1);
	

	JLabel panelLabel2 = new JLabel("  Other Information ");
	panelLabel2.setBounds(20,340,130,20);
	panelLabel2.setOpaque(true);
	panelLabel2.setBackground(Color.CYAN);
	add(panelLabel2);
	
}


public void setFields(){
	
	PID = new JTextField();
	PID.setBounds(130,90,200,18);
	//PID.setEditable(false);
	PID.addKeyListener(this);
	add(PID);
	
	FN = new JTextField();
	FN.setBounds(130,120,200,18);
	add(FN);
	
	MN = new JTextField();
	MN.setBounds(130,150,200,18);
	add(MN);
	
	LN = new JTextField();
	LN.setBounds(130,180,200,18);
	LN.addKeyListener(Kim);
	add(LN);
	
	
	Month = new JComboBox(M);
	Month.setBounds(450,90,90,18);
	Month.setSelectedIndex(0);
	Month.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent jammi){

	if(jammi.getSource()==Month){
	JComboBox cb = (JComboBox)jammi.getSource();
	getMonth = (String)cb.getSelectedItem();

	}
}
}); 
	add(Month);



	Day = new JComboBox(D);
	Day.setBounds(550,90,40,18);
	Day.setSelectedIndex(0);
	Day.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent moriel){

	if(moriel.getSource()==Day){
	JComboBox cb = (JComboBox)moriel.getSource();
	getDay = (String)cb.getSelectedItem();

	

	}

		}
		}
		); 
	add(Day);


		
		Yr = new JTextField("2017");
		Yr.setBounds(600,90,50,18);
		add(Yr);
		
		
		//MGA FIELDS PARA SA TIME
		//WALANGYA ANG DAMI NG CASE DITO NAKAKATAMAD BAKAYAROU!
		Min = new JComboBox(min);
		Min.setBounds(560,120,40,18);
		prepareLabels(" Minutes:",610,120,60,18);
		Min.setSelectedIndex(0);
		Min.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent moriel){

		if(moriel.getSource()==Min){
		JComboBox cb = (JComboBox)moriel.getSource();
		getMin = (String)cb.getSelectedItem();

		
		}

			}
			}
			); 
		add(Min);
		
		
		Hour = new JComboBox(Hrs);
		Hour.setBounds(450,120,40,18);
		prepareLabels(" Hours:",500,120,50,18);
		Hour.setSelectedIndex(0);
		Hour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent moriel){

		if(moriel.getSource()==Hour){
		JComboBox cb = (JComboBox)moriel.getSource();
		getHour = (String)cb.getSelectedItem();

		
		}

			}
			}
			); 
		add(Hour);
		
		ampm = new JComboBox(AMPM);
		ampm.setBounds(680,120,80,18);
		add(ampm);

	

		//MGA FIELDS PARA SA BIRTHDAY
		BMonth = new JComboBox(BM);
		BMonth.setBounds(130,210,90,18);
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
		BDay.setBounds(230,210,40,18);
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
			BYr.setBounds(280,210,50,18);
			add(BYr);
			
			
			
			gender = new JComboBox(G);
			gender.setBounds(450,180,200,18);
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
			status.setBounds(450,240,200,18);
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
			
			
			//END OF COMBO STUFFS NANDE KUSO
			
			age = new JTextField();
			age.setBounds(450,150,200,18);
			add(age);
			
			bplace = new JTextField();
			bplace.setBounds(130,240,200,18);
			add(bplace);
			
			address = new JTextField();
			address.setBounds(450,210,200,18);
			add(address);
			
			occu = new JTextField();
			occu.setBounds(450,270,200,18);
			add(occu);
	
			nation = new JTextField();
			nation.setBounds(130,270,200,18);
			add(nation);
			
			rel = new JTextField();
			rel.setBounds(130,300,200,18);
			add(rel);
			
			ward = new JTextField();
			ward.setBounds(170,380,190,18);
			add(ward);
			
			psy = new JTextField();
			psy.setBounds(170,410,190,18);
			add(psy);
			
			dad = new JTextField();
			dad.setBounds(570,380,200,18);
			add(dad);
			
			mom = new JTextField();
			mom.setBounds(570,410,200,18);
			add(mom);
			
			ph = new JCheckBox("  PhilHealth  ");
			ph.setBounds(30,440,130,18);
			ph.setSelected(true);
			ph.setBackground(Color.LIGHT_GRAY);
			add(ph);
			
			
			diag=new JTextArea();
			//Remarks.setBounds(100,505,250,240);
			diag.setToolTipText("Please use 'Enter Key' to use line break!");
			diag.setLineWrap(true);
			diag.setWrapStyleWord(true);
			//add(Remarks);
			scroll=new JScrollPane(diag);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			//scrollRemarks.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll.setBounds(570,440,200,50);
			add(scroll);
			
			
			
	
}


public void setButtons(){
	

	
	clear = new JButton("CLEAR FIELDS");
	clear.setBounds(140,550,120,25);
	clear.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			ClearFields();
		}
		
	});
	add(clear);
	
	
	edit = new JButton("EDIT");
	edit.setBounds(280,550,70,25);
	edit.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			

			int ans = JOptionPane.showConfirmDialog(null,"Save Changes?");
			
			
			if(ans == JOptionPane.YES_OPTION){
			checkInputs();
			
		}else if(ans==JOptionPane.NO_OPTION){
			
			
		}else if(ans ==JOptionPane.CANCEL_OPTION){
			
		}else if(ans ==JOptionPane.CLOSED_OPTION){
			
		}
		}
		
	});
	add(edit);
	
	
	print = new JButton("PRINT");
	print.setBounds(370,550,90,25);
	print.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			printMe();
		}
		
	});
	add(print);
	
	exit = new JButton("EXIT");
	exit.setBounds(480,550,90,25);
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
	line.setBounds(0,520,1250,1);
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
	
	if(ward.getText().toString().equals("")){
		ward.setBackground(inputFailed);
		ward.setForeground(changeInput);
	}else{
		ward.setBackground(inputPass);
		ward.setForeground(returnInput);
	}
	
	if(psy.getText().toString().equals("")){
		psy.setBackground(inputFailed);
		psy.setForeground(changeInput);
	}else{
		psy.setBackground(inputPass);
		psy.setForeground(returnInput);
	}
	
	if(dad.getText().toString().equals("")){
		dad.setBackground(inputFailed);
		dad.setForeground(changeInput);
	}else{
		dad.setBackground(inputPass);
		dad.setForeground(returnInput);
	}
	
	if(mom.getText().toString().equals("")){
		mom.setBackground(inputFailed);
		mom.setForeground(changeInput);
	}else{
		mom.setBackground(inputPass);
		mom.setForeground(returnInput);
	}
	
	if(Month.getSelectedItem().toString().equals("")){
		Month.setBackground(inputFailed);
		Month.setForeground(changeInput);
	}else{
		Month.setBackground(inputPass);
		Month.setForeground(returnInput);
	}
	
	if(Day.getSelectedItem().toString().equals("")){
		Day.setBackground(inputFailed);
		Day.setForeground(changeInput);
	}else{
		Day.setBackground(inputPass);
		Day.setForeground(returnInput);
	}
	
	if(Yr.getText().toString().equals("")){
		Yr.setBackground(inputFailed);
		Yr.setForeground(changeInput);
	}else{
		Yr.setBackground(inputPass);
		Yr.setForeground(returnInput);
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
	
	if(Hour.getSelectedItem().toString().equals("")){
		Hour.setBackground(inputFailed);
		Hour.setForeground(changeInput);
	}else{
		Hour.setBackground(inputPass);
		Hour.setForeground(returnInput);
	}
	
	if(Min.getSelectedItem().toString().equals("")){
		Min.setBackground(inputFailed);
		Min.setForeground(changeInput);
	}else{
		Min.setBackground(inputPass);
		Min.setForeground(returnInput);
	}
	
	if(ampm.getSelectedItem().toString().equals("") || ampm.getSelectedItem().toString().equals("AM/PM")){
		ampm.setBackground(inputFailed);
		ampm.setForeground(changeInput);
	}else{
		ampm.setBackground(inputPass);
		ampm.setForeground(returnInput);
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
	
	if(diag.getText().toString().equals("")){
		diag.setBackground(inputFailed);
		diag.setForeground(changeInput);
	}else{
		diag.setBackground(inputPass);
		diag.setForeground(returnInput);
	}
	
	
	
	if(FN.getText().toString().equals("") ||  MN.getText().toString().equals("") || LN.getText().toString().equals("") ||
			age.getText().toString().equals("") || bplace.getText().equals("") ||
			address.getText().toString().equals("") ||  occu.getText().toString().equals("") || nation.getText().toString().equals("") 
			|| rel.getText().toString().equals("") || 
			ward.getText().toString().equals("") || psy.getText().toString().equals("") || dad.getText().toString().equals("")
			|| mom.getText().toString().equals("")
			|| Month.getSelectedItem().equals("") || Day.getSelectedItem().equals("") || Yr.getText().toString().equals("")
			|| BMonth.getSelectedItem().equals("") || BDay.getSelectedItem().equals("") || BYr.getText().toString().equals("")
			|| Hour.getSelectedItem().equals("") || Min.getSelectedItem().equals("")
			|| ampm.getSelectedItem().equals("") ||  ampm.getSelectedItem().equals("AM/PM") || gender.getSelectedItem().equals("")
			|| status.getSelectedItem().equals("") || diag.getText().toString().equals("")
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

	 
	 if(ph.isSelected()){
		 
		 ppap = "with philhealth";
		 
	 }else{
		 
		 ppap = "without philhealth";
	 }
	 
	 
	 String query = "INSERT INTO admission_history (patient_id, date_month, date_day, date_year,date_hour,date_minutes,am_pm,first_name,middle_name,last_name,birth_month,birth_day,birth_year,birthplace,age,gender,civil_status,address,occupation,nationality,religion,ward,physician,father,mother,philhealth,diagnosis)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);

	 ps.setInt(1, checkID);

	  ps.setString(2, getMonth); 

	  ps.setString(3, getDay); 

	  ps.setString(4, Yr.getText().toString()); 
	  
	  ps.setString(5, getHour);

	  ps.setString(6, getMin); 
	  
	  ps.setString(7, (String) ampm.getSelectedItem());

	  ps.setString(8, FN.getText().toString()); 

	  ps.setString(9, MN.getText().toString());
	  
	  ps.setString(10, LN.getText().toString());

	  ps.setString(11, getBMonth); 

	  ps.setString(12, getBDay); 

	  ps.setString(13, BYr.getText().toString());
	  
	  ps.setString(14, bplace.getText().toString());

	  ps.setString(15, age.getText().toString()); 

	  ps.setString(16, getGender); 

	  ps.setString(17, getStatus);
	  
	  ps.setString(18, address.getText().toString());

	  ps.setString(19, occu.getText().toString()); 

	  ps.setString(20, nation.getText().toString()); 

	  ps.setString(21, rel.getText().toString());
	  
	  ps.setString(22, ward.getText().toString());

	  ps.setString(23, psy.getText().toString()); 

	  ps.setString(24, dad.getText().toString()); 

	  ps.setString(25, mom.getText().toString());
	  
	  ps.setString(26, ppap);

	  ps.setString(27, diag.getText().toString()); 


	 ps.execute();

	 // JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
	 


conn.close();

}catch (Exception moriel){

 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

	} 





//CODES PARA SA DISCHARGE PARA MERON NG ADMITTING INFO
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
	String query = "UPDATE discharge SET admit_date=?, physician=?, admit_diagnosis=? WHERE patient_id = "+"\""+this.PID.getText().toString()+"\"";

	
	PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
	

	 ps.setString(1, Month.getSelectedItem().toString()+ " "+Day.getSelectedItem().toString()+" "+Yr.getText().toString());
	 
	 ps.setString(2, psy.getText().toString()); 

	 ps.setString(3, diag.getText().toString()); 

	

	ps.execute();

	 //JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 

	conn.close();
	
	//this.user.enable(true);

	}catch (Exception moriel){

	 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

		} 




} 




//MUNTIK KO NG HINDI MAKITA ANG METHOD NA ITO SA DAMI NG CODES
//MORIEL!!!! HUHU!
public void ClearFields(){
	
	 // PID.setText("");
	  Month.setSelectedItem("");
	  Day.setSelectedItem("");
	  Yr.setText("");
	  Hour.setSelectedItem("");
	  Min.setSelectedItem("");
	  ampm.setSelectedItem("AM/PM");
	  FN.setText("");
	  MN.setText("");
	  LN.setText("");
	  BMonth.setSelectedItem(""); 
	  BDay.setSelectedItem("");
	  BYr.setText("");
	  bplace.setText("");
	  age.setText("");
	  gender.setSelectedItem("");
	  status.setSelectedItem("");
	  address.setText("");
	  occu.setText("");
	  nation.setText(""); 
	  rel.setText("");
	  ward.setText("");
	  psy.setText("");
	  dad.setText("");
	  mom.setText("");
	  pineapplePen = "";
	  ph.setSelected(false);
	  diag.setText("");
	
	
}



//METHOD FOR SEARCHING ACCOUNT BAKAYAROU!
public void SearchSQL(){
	  
	ClearFields();
	
	
	//String check = "SELECT * FROM admission WHERE patient_id="+"\""+table.getValueAt(table.getSelectedRow(), 0)+"\"";

	String check = "SELECT * FROM admission WHERE patient_id="+"\""+PID.getText().toString()+"\"";

	
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

				  Month.setSelectedItem(rs.getString("date_month"));

				  Day.setSelectedItem(rs.getString("date_day"));

				  Yr.setText(rs.getString("date_year"));
				  
				  Hour.setSelectedItem(rs.getString("date_hour"));
				  
				  Min.setSelectedItem(rs.getString("date_minutes"));
				  
				  ampm.setSelectedItem(rs.getString("am_pm"));

				  FN.setText(rs.getString("first_name"));
				  
				  MN.setText(rs.getString("middle_name"));
				  
				  LN.setText(rs.getString("last_name"));

				  BMonth.setSelectedItem(rs.getString("birth_month")); 

				  BDay.setSelectedItem(rs.getString("birth_day"));

				  BYr.setText(rs.getString("birth_year"));
				  
				  bplace.setText(rs.getString("birthplace"));

				  age.setText(rs.getString("age"));

				  gender.setSelectedItem(rs.getString("gender"));

				  status.setSelectedItem(rs.getString("civil_status"));
				  
				  address.setText(rs.getString("address"));

				  occu.setText(rs.getString("occupation"));

				  nation.setText(rs.getString("nationality")); 

				  rel.setText(rs.getString("religion"));
				  
				  ward.setText(rs.getString("ward"));

				  psy.setText(rs.getString("physician"));

				  dad.setText(rs.getString("father"));

				  mom.setText(rs.getString("mother"));
				  
				  
				  pineapplePen = "with philhealth";
				  
				  if(rs.getString("philhealth").equals(pineapplePen)){
					  
					  applePen = true;
					  
				  }else{
					  
					  applePen = false;
					  
				  }
				  
				  ph.setSelected(applePen);
				  

				 diag.setText(rs.getString("diagnosis"));
				
		counter++;
			 }
			 

			 if(counter<1){
				 
				// JOptionPane.showMessageDialog(null,"Account Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }

		conn.close();

		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 

			} 

} 


//METHOD FOR SEARCHING ACCOUNT THROUGH TABLE ROW CLICK
//METHOD FOR SEARCHING ACCOUNT BAKAYAROU!
public void clickTableSearch(){
	
	
	//String check = "SELECT * FROM admission WHERE patient_id="+"\""+table.getValueAt(table.getSelectedRow(), 0)+"\"";

	String check = "SELECT * FROM admission WHERE patient_id="+"\""+table.getValueAt(table.getSelectedRow(), 0)+"\"";

	
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

				  Month.setSelectedItem(rs.getString("date_month"));

				  Day.setSelectedItem(rs.getString("date_day"));

				  Yr.setText(rs.getString("date_year"));
				  
				  Hour.setSelectedItem(rs.getString("date_hour"));
				  
				  Min.setSelectedItem(rs.getString("date_minutes"));
				  
				  ampm.setSelectedItem(rs.getString("am_pm"));

				  FN.setText(rs.getString("first_name"));
				  
				  MN.setText(rs.getString("middle_name"));
				  
				  LN.setText(rs.getString("last_name"));

				  BMonth.setSelectedItem(rs.getString("birth_month")); 

				  BDay.setSelectedItem(rs.getString("birth_day"));

				  BYr.setText(rs.getString("birth_year"));
				  
				  bplace.setText(rs.getString("birthplace"));

				  age.setText(rs.getString("age"));

				  gender.setSelectedItem(rs.getString("gender"));

				  status.setSelectedItem(rs.getString("civil_status"));
				  
				  address.setText(rs.getString("address"));

				  occu.setText(rs.getString("occupation"));

				  nation.setText(rs.getString("nationality")); 

				  rel.setText(rs.getString("religion"));
				  
				  ward.setText(rs.getString("ward"));

				  psy.setText(rs.getString("physician"));

				  dad.setText(rs.getString("father"));

				  mom.setText(rs.getString("mother"));
				  
				  
				  pineapplePen = "with philhealth";
				  
				  if(rs.getString("philhealth").equals(pineapplePen)){
					  
					  applePen = true;
					  
				  }else{
					  
					  applePen = false;
					  
				  }
				  
				  ph.setSelected(applePen);
				  

				 diag.setText(rs.getString("diagnosis"));
				
		counter++;
			 }
			 

			 if(counter<1){
				 
				// JOptionPane.showMessageDialog(null,"Account Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }

		conn.close();

		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 

			} 

} 


//METHOD FOR EDITING NANDE KUSO!
public void EditSQL(){

		
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
			String query = "UPDATE admission SET date_month=?, date_day=?, date_year=?, date_hour=?, date_minutes=?, am_pm = ?, first_name=?, middle_name=?, last_name=?, birth_month=?, birth_day=?, birth_year=?, birthplace=?,age=?,gender=?, civil_status=?, address=?,occupation=?,nationality=?,religion=?,ward=?,physician=?,father=?,mother=?,philhealth=?,diagnosis=? WHERE patient_id = "+"\""+this.PID.getText().toString()+"\"";

			
			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			
		
			
			  ps.setString(1, (String) Month.getSelectedItem()); 

			  ps.setString(2, (String)Day.getSelectedItem()); 

			  ps.setString(3, Yr.getText().toString()); 
			  
			  ps.setString(4, (String) Hour.getSelectedItem());

			  ps.setString(5, (String) Min.getSelectedItem()); 
			  
			  ps.setString(6, (String) ampm.getSelectedItem());

			  ps.setString(7, FN.getText().toString()); 

			  ps.setString(8, MN.getText().toString());
			  
			  ps.setString(9, LN.getText().toString());

			  ps.setString(10, (String) BMonth.getSelectedItem()); 

			  ps.setString(11, (String) BDay.getSelectedItem()); 

			  ps.setString(12, BYr.getText().toString());
			  
			  ps.setString(13, bplace.getText().toString());

			  ps.setString(14, age.getText().toString()); 

			  ps.setString(15, (String) gender.getSelectedItem()); 

			  ps.setString(16, (String) status.getSelectedItem());
			  
			  ps.setString(17, address.getText().toString());

			  ps.setString(18, occu.getText().toString()); 

			  ps.setString(19, nation.getText().toString()); 

			  ps.setString(20, rel.getText().toString());
			  
			  ps.setString(21, ward.getText().toString());

			  ps.setString(22, psy.getText().toString()); 

			  ps.setString(23, dad.getText().toString()); 

			  ps.setString(24, mom.getText().toString());
			  
			  
			  if(ph.isSelected()){
					 
					 ppap = "with philhealth";
					 
				 }else{
					 
					 ppap = "without philhealth";
				 }
			  
			  ps.setString(25, ppap);

			  ps.setString(26, diag.getText().toString()); 
			
		
			
			ps.execute();

			 JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 

			conn.close();
			
			
			//this.user.enable(true);

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
	   	  
	    sql = "SELECT * FROM admission";
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
        	  
         sql = "SELECT * FROM admission";
         ResultSet rs = stmt.executeQuery(sql);
         table = new JTable(model);
   		 JScrollPane scrollPane = new JScrollPane(table);
   		 table.setPreferredScrollableViewportSize(new Dimension(500, 70));
   		 table.setFillsViewportHeight(true);
   		 table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
   		 table.setBackground(Color.LIGHT_GRAY);
   		 scrollPane.setBounds(810,90,400,410);
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
        	  
        	//sql = "SELECT * FROM admission WHERE first_name LIKE "+"\""+alternateSearch.getText().toString()+"%\""+"OR last_name LIKE "+"\""+alternateSearch.getText().toString()+"%\""+"OR patient_id LIKE "+"\""+alternateSearch.getText().toString()+"%\"";
        	
         sql = "SELECT * FROM admission WHERE patient_id LIKE "+"\""+PID.getText().toString()+"%\"";
     
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
	
	 logo = Toolkit.getDefaultToolkit().getImage("res/logo.jpg");
	 
	 String pineapplePen;
	
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
        g.drawString("Patient Admission", 230,135);
        
      
        g.setFont(new Font("Tahoma",Font.PLAIN,8));
        g.setColor(Color.BLACK);
        g.drawString("Patient ID: "+PID.getText().toString(), 100,160);

       
        g.drawString("Date: "+ Month.getSelectedItem()+" "+Day.getSelectedItem()+", "+Yr.getText().toString(), 100,170);
        
       
        g.drawString("Time: "+Hour.getSelectedItem()+":"+Min.getSelectedItem()+" "+ampm.getSelectedItem(), 100,180);
      
       
        g.drawString("Name: "+FN.getText().toString()+" "+MN.getText().toString()+" "+LN.getText().toString(), 100,190);

        
        g.drawString("Birthday: "+BMonth.getSelectedItem()+" "+ BDay.getSelectedItem()+", "+BYr.getText().toString(), 100,200);
        
       
        g.drawString("Age : "+ age.getText().toString(), 320,200);

       
        g.drawString("Birthplace: "+bplace.getText().toString(), 100,210);

        
        g.drawString("Civil Status: "+status.getSelectedItem(), 100,220);
        
        g.drawString("Gender: "+gender.getSelectedItem(), 320,220);
       
        g.drawString("Address: "+address.getText().toString(), 100,230);
      
       
        g.drawString("Occupation: "+occu.getText().toString(), 100,240);
      
       
        g.drawString("Nationality: "+nation.getText().toString(), 320,240);


        g.drawString("Religion: "+rel.getText().toString(), 100,250);

       
        g.drawString("Room/Ward: "+ward.getText().toString(), 320,250);

       
        g.drawString("Attending Physician: "+psy.getText().toString(), 100,260);

      
        g.drawString("Father's Name: "+dad.getText().toString(), 100,270);

     
        g.drawString("Mother's Name: "+mom.getText().toString(), 100,280);

     if(ph.isSelected()){
    	 
    	 pineapplePen = "With Philhealth";
    	 
     }else{
    	 
    	 pineapplePen = "Without Philhealth";
    	 
     }


      
        
        g.drawString("Philhealth: "+pineapplePen, 100,290);


       
        g.drawString("Patient Problem/Diagnosis: "+diag.getText().toString(), 100,300);

        
        
        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.setColor(Color.BLACK);
        //draw line separator
        g.drawString("_______________________________________" +
        		"________________________________________________", 70, 310);
      

       
        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.drawString("Email Address: region1mc2003@yahoo.com", 100,325);
        
        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.drawString("Telephone Numbers: (075) 515-89-16 ; (075)515-89-18 ; (075)515-89-01", 100,335);

        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.drawString("Fax: (075) 523-41-03", 100,345);

      
        //LINE SEPARATOR FOR CUTTING NANDE KUSO
        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.setColor(Color.BLACK);
        //draw line separator
        g.drawString("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ " +
        		"_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ " +
        		"_ _ _ _ _ _ _ _ _ _ _ _", 70, 365);
      
        
        //2ND COPY OF ADMISSION
        
        g.drawImage(logo, 270, 370, 60, 60, this);

        g.setFont(new Font("Tahoma",Font.BOLD,20));
        g.setColor(Color.RED);
        g.drawString(" HOSPITAL MANAGEMENT SYSTEM", 140, 460);
       
        
        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.setColor(Color.BLACK);
        g.drawString("Arellano Street, Dagupan City 2400, Pangasinan, Philippines", 180,470);

        //draw line separator
        g.drawString("_______________________________________" +
        		"________________________________________________", 70, 480);
        
        g.setFont(new Font("Tahoma",Font.ITALIC,10));
        g.setColor(Color.BLACK);
        g.drawString("Patient Admission", 230,495);
      
        g.setFont(new Font("Tahoma",Font.PLAIN,8));
        g.setColor(Color.BLACK);
        g.drawString("Patient ID: "+PID.getText().toString(), 100,510);

       
        g.drawString("Date: "+ Month.getSelectedItem()+" "+Day.getSelectedItem()+", "+Yr.getText().toString(), 100,520);
        
       
        g.drawString("Time: "+Hour.getSelectedItem()+":"+Min.getSelectedItem()+" "+ampm.getSelectedItem(), 100,530);
      
       
        g.drawString("Name: "+FN.getText().toString()+" "+MN.getText().toString()+" "+LN.getText().toString(), 100,540);

        
        g.drawString("Birthday: "+BMonth.getSelectedItem()+" "+ BDay.getSelectedItem()+", "+BYr.getText().toString(), 100,550);
        
       
        g.drawString("Age : "+ age.getText().toString(), 320,550);

       
        g.drawString("Birthplace: "+bplace.getText().toString(), 100,560);

        
        g.drawString("Civil Status: "+status.getSelectedItem(), 100,570);
        
        g.drawString("Gender: "+gender.getSelectedItem(), 320,570);
       
        g.drawString("Address: "+address.getText().toString(), 100,580);
      
       
        g.drawString("Occupation: "+occu.getText().toString(), 100,590);
        
        
        g.drawString("Nationality: "+nation.getText().toString(), 320,590);


        g.drawString("Religion: "+rel.getText().toString(), 100,600);

       
        g.drawString("Room/Ward: "+ward.getText().toString(), 320,600);

       
        g.drawString("Attending Physician: "+psy.getText().toString(), 100,610);

      
        g.drawString("Father's Name: "+dad.getText().toString(), 100,620);

     
        g.drawString("Mother's Name: "+mom.getText().toString(), 100,630);
        
        
        g.drawString("Philhealth: "+pineapplePen, 100,640);


        g.drawString("Patient Problem/Diagnosis: "+diag.getText().toString(), 100,650);

        
        
        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.setColor(Color.BLACK);
        //draw line separator
        g.drawString("_______________________________________" +
        		"________________________________________________", 70, 660);
      

       
        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.drawString("Email Address: region1mc2003@yahoo.com", 100,675);
        
        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.drawString("Telephone Numbers: (075) 515-89-16 ; (075)515-89-18 ; (075)515-89-01", 100,685);

        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.drawString("Fax: (075) 523-41-03", 100,695);


      


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


	
	
	//NEW CLASS PARA SA LAST NAME SEARCHING
	public class Moriel implements KeyListener{
		
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
		        	
		         sql = "SELECT * FROM admission WHERE last_name LIKE "+"\""+LN.getText().toString()+"%\"";
		     
		        	ResultSet rs = stmt.executeQuery(sql);
		        	table.setModel(DbUtils.resultSetToTableModel(rs));
		         
		    
			 }catch(Exception moriel){
				 
				// JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }
			 
			
		}


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
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
