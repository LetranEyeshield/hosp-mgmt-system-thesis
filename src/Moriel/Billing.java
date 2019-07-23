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

public class Billing extends JFrame implements ActionListener, Printable, MouseListener, KeyListener{
	
	JLabel labels,title;
	
	
	JTextField PID, FN, MN, LN, ward, wardCharge, age, address, phic, PCase, numDays, docCharge, otherCharge, bill;
	
	JButton add, clear, edit, alterSearch, print, preBill, pharmacyBilling, reports, exit;
	
	JTextField labPrice, surgeryPrice;
	
	JTable table;

	DefaultTableModel model;
	
	
	//VARS FOR BDATE
	String M[] = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	String getMonth;

	String D[] = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	JComboBox Month, Day;
	String getDay;

	JTextField Yr;
	
	
	String G[] = {"", "Male", "Female"};
	String setGender, getGender;
	JComboBox gender;
	
	String W[] = {"", "Private", "Pay Ward", "Charity Ward"};
	JComboBox wardType;
	
	//VARS PARA SA LAB AND SURGERY PRICES
	String L[] = {"","X-RAY","Ultrasound","HCG","HIV Antibody", "Drug Test", "Flu Test", "Blood Test", "CBC", "Glucose","Pap Smear","Cholesterol",
					"Uric Acid","Urine Test","Stool Analysis",};
	JComboBox Labs;
	
	String S[] = {"","Breast Augmentation Surgery","Colon Surgery", "Rectal Surgery", "Gynecological Surgery", "Hand Surgery", "Head and Neck Surgery", 
			"Neurosurgery","Orthopedic Surgery","Ophthalmological Surgery",
			"Pediatric Surgery","Plastic and Reconstructive Surgery","Urologic Surgery"};
	JComboBox Surgery;
	
	//MGA VARS PARA SA CHECKING NG MGA BAYAD MAMAYA
	String WC, ND, DC, OC, SC, LC;
	int nd;
	double wc, dc, oc, sc=0, lc=0, totalBill;
	
	
	//COLORS PARA SA CHECKING INPUTS
		Color inputFailed = Color.RED;
		Color changeInput = Color.WHITE;
		Color inputPass = Color.WHITE;
		Color returnInput = Color.BLACK;
		
		
		String getWardCharge;
		
		//PARA SA LAST NAME SEARCHING
		Moriel Kim = new Moriel();
		
	public Billing(){
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
				"                                 " +
				"                             " +
				"       BILLING");
		title.setBounds(0,0,1250,25);
		title.setOpaque(true);
		title.setBackground(Color.blue);
		title.setFont(new Font("OLD ENGLISH MT",Font.BOLD,20));
		add(title);
	}



public void setBackground(){

	JLabel background = new JLabel(new ImageIcon("res/billing.png"));
	 background.setBounds(0,0,1250,670);
	// background.setBackground(new Color(0,0,255));
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
	

	prepareLabels(" Patient ID:",30,90,90,18);
	prepareLabels(" First Name:",30,120,90,18);
	prepareLabels(" Middle Name:",30,150,90,18);
	prepareLabels(" Last Name:",30,180,90,18);
	prepareLabels(" Date:",280,90,70,18);
	prepareLabels(" Age:",280,120,70,18);
	prepareLabels(" Gender:",280,150,70,18);
	prepareLabels(" Address:",280,180,70,18);
	
	
	prepareLabels(" Type of PHIC:",30,250,140,18);
	prepareLabels(" Patient Case:",30,280,140,18);
	prepareLabels(" Ward/Room No:",30,310,140,18);
	prepareLabels(" Ward/Room Type:",30,340,140,18);
	prepareLabels(" Ward/Room Charge:",30,370,140,18);
	prepareLabels(" Number Of Days:",30,400,140,18);
	
	prepareLabels(" Lab Test:",410,250,100,18);
	prepareLabels(" Lab Price:",410,280,100,18);
	prepareLabels(" Surgery:",410,310,100,20);
	prepareLabels(" Surgery Price:",410,340,100,18);
	prepareLabels(" Doctor Charge:",410,370,100,18);
	prepareLabels(" Other Charge:",410,400,100,18);
	prepareLabels(" Total Bill:",410,430,100,18);
	
	
	
}

public void setPanels(){
	
	JLabel panel1 = new JLabel();
	panel1.setBounds(20,80,740,130);
	panel1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel1);
	
	JLabel panel2 = new JLabel();
	panel2.setBounds(20,240,740,220);
	panel2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel2);
	
	JLabel labelPanel1 = new JLabel(" Personal Information");
	labelPanel1.setBounds(20,60,130,18);
	labelPanel1.setOpaque(true);
	labelPanel1.setBackground(Color.CYAN);
	add(labelPanel1);
	
	JLabel labelPanel2 = new JLabel(" Charges Information");
	labelPanel2.setBounds(20,220,130,18);
	labelPanel2.setOpaque(true);
	labelPanel2.setBackground(Color.CYAN);
	add(labelPanel2);
	
	JLabel panel3 = new JLabel();
	panel3.setBounds(770,80,460,380);
	panel3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel3);
	
}


public void setFields(){
	
	PID = new JTextField();
	PID.setBounds(130,90,120,18);
	//PID.setEditable(false);
	PID.addKeyListener(this);
	add(PID);
	
	FN = new JTextField();
	FN.setBounds(130,120,120,18);
	add(FN);
	
	MN = new JTextField();
	MN.setBounds(130,150,120,18);
	add(MN);
	
	LN = new JTextField();
	LN.setBounds(130,180,120,18);
	LN.addKeyListener(Kim);
	add(LN);
	
	age = new JTextField();
	age.setBounds(360,120,200,18);
	add(age);
	
	address = new JTextField();
	address.setBounds(360,180,200,18);
	add(address);
	
	ward = new JTextField();
	ward.setBounds(180,310,200,18);
	add(ward);
	
	wardCharge = new JTextField("0");
	wardCharge.setBounds(180,370,200,18);
	add(wardCharge);
	
	
	wardType = new JComboBox(W);
	wardType.setBounds(180,340,200,18);
	wardType.setSelectedIndex(0);
	wardType.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent jammi) {
			// TODO Auto-generated method stub
			SearchWardSQL();
		}
		
	});
	add(wardType);
	
	gender = new JComboBox(G);
	gender.setBounds(360,150,200,18);
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
	
	phic = new JTextField();
	phic.setBounds(180,250,200,18);
	add(phic);
	
	
	Month = new JComboBox(M);
	Month.setBounds(360,90,90,18);
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
	Day.setBounds(460,90,40,18);
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
		Yr.setBounds(510,90,50,18);
		add(Yr);
		
		
		PCase = new JTextField();
		PCase.setBounds(180,280,200,18);
		add(PCase);
		
		numDays = new JTextField("0");
		numDays.setBounds(180,400,200,18);
		add(numDays);
		
		docCharge = new JTextField("0");
		docCharge.setBounds(520,370,230,18);
		add(docCharge);
		
		otherCharge = new JTextField("500");
		otherCharge.setBounds(520,400,230,18);
		add(otherCharge);
		
		bill = new JTextField();
		bill.setBounds(520,430,230,18);
		bill.setEditable(false);
		add(bill);
			
		
		Labs = new JComboBox(L);
		Labs.setBounds(520,250,230,18);
		Labs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent saber) {
				// TODO Auto-generated method stub
					switch(Labs.getSelectedItem().toString()){
					
					case "X-RAY":
						labPrice.setText("500");
						break;
						
					case "Ultrasound":
						labPrice.setText("750");
						break;
					
					case "HCG":
						labPrice.setText("1500");
						break;
						
					case "HIV Antibody":
						labPrice.setText("3500");
						break;
						
					case "Drug Test":
						labPrice.setText("1000");
						break;
						
					case "Flu Test":
						labPrice.setText("650");
						break;
						
					case "Blood Test":
						labPrice.setText("500");
						break;
						
					case "CBC":
						labPrice.setText("450");
						break;
						
					case "Glucose":
						labPrice.setText("500");
						break;
						
					case "Pap Smear":
						labPrice.setText("500");
						break;
						
					case "Cholesterol":
						labPrice.setText("650");
						break;
						
					case "Uric Acid":
						labPrice.setText("450");
						break;
						
					case "Urine Test":
						labPrice.setText("350");
						break;
						
					case "Stool Analysis":
						labPrice.setText("750");
						break;
						
						default:
							labPrice.setText("0");
							break;
					
					
				}
			}
			
		});
		add(Labs);
		
		labPrice = new JTextField("0");
		labPrice.setBounds(520,280,230,18);
		labPrice.setEditable(false);
		add(labPrice);
		
		Surgery = new JComboBox(S);
		Surgery.setBounds(520,310,230,18);
		Surgery.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent saber) {
				// TODO Auto-generated method stub
				
				switch(Surgery.getSelectedItem().toString()){
				
				case "Breast Augmentation Surgery":
					surgeryPrice.setText("10000");
					break;
					
				case "Colon Surgery":
					surgeryPrice.setText("25000");
					break;
					
				case "Rectal Surgery":
					surgeryPrice.setText("15000");
					break;
					
				case "Gynecological Surgery":
					surgeryPrice.setText("18000");
					break;
					
				case "Hand Surgery":
					surgeryPrice.setText("4500");
					break;
					
				case "Head and Neck Surgery":
					surgeryPrice.setText("23000");
					break;
					
				case "Neurosurgery":
					surgeryPrice.setText("50000");
					break;
					
				case "Orthopedic Surgery":
					surgeryPrice.setText("30000");
					break;
					
				case "Ophthalmological Surgery":
					surgeryPrice.setText("35000");
					break;
					
				case "Pediatric Surgery":
					surgeryPrice.setText("25000");
					break;
					
				case "Plastic and Reconstructive Surgery":
					surgeryPrice.setText("65000");
					break;
					
				case "Urologic Surgery":
					surgeryPrice.setText("21000");
					break;
					
					default:
						surgeryPrice.setText("0");
						break;
				
				}
				
			}
			
		});
		add(Surgery);
		
		surgeryPrice = new JTextField("0");
		surgeryPrice.setBounds(520,340,230,18);
		surgeryPrice.setEditable(false);
		add(surgeryPrice);
			
			
	
}


public void setButtons(){
	
	
	clear = new JButton("CLEAR FIELDS");
	clear.setBounds(30,500,120,25);
	clear.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			ClearFields();
			
		}
		
	});
	add(clear);
	
	
	
	edit = new JButton("UPDATE");
	edit.setBounds(170,500,90,25);
	edit.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			checkInputs();
			
			
		}
		
	});
	add(edit);
	
	
	
	print = new JButton("PRINT");
	print.setBounds(280,500,90,25);
	print.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			printMe();
		}
		
	});
	add(print);
	
	
	preBill = new JButton("Pre-Billing");
	preBill.setBounds(390,500,100,25);
	preBill.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			PreBilling P = new PreBilling();
			P.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			P.setSize(860,700);
			P.setVisible(true);
			P.setResizable(false);
			
		}
		
	});
	add(preBill);
	
	
	pharmacyBilling = new JButton("PHARMACY BILLING");
	pharmacyBilling.setBounds(510,500,150,25);
	pharmacyBilling.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			Pharmacy_Patient_Billing PPB = new Pharmacy_Patient_Billing();
			PPB.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			PPB.setSize(1060,720);
			PPB.setVisible(true);
			PPB.setResizable(false);
			
		}
		
	});
	add(pharmacyBilling);
	
	
	reports = new JButton("REPORTS");
	reports.setBounds(680,500,90,25);
	reports.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			Reports R = new Reports();
			R.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			R.setSize(860,600);
			R.setVisible(true);
			R.setResizable(false);
		}
		
	});
	add(reports);
	
	
	exit = new JButton("EXIT");
	exit.setBounds(790,500,70,25);
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
	line.setBounds(0,470,1250,1);
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
	
	if(address.getText().toString().equals("")){
		address.setBackground(inputFailed);
		address.setForeground(changeInput);
	}else{
		address.setBackground(inputPass);
		address.setForeground(returnInput);
	}
	
	if(phic.getText().toString().equals("")){
		phic.setBackground(inputFailed);
		phic.setForeground(changeInput);
	}else{
		phic.setBackground(inputPass);
		phic.setForeground(returnInput);
	}
	
	if(PCase.getText().toString().equals("")){
		PCase.setBackground(inputFailed);
		PCase.setForeground(changeInput);
	}else{
		PCase.setBackground(inputPass);
		PCase.setForeground(returnInput);
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
			age.getText().toString().equals("") ||  address.getText().toString().equals("") || phic.getText().toString().equals("") || 
			PCase.getText().toString().equals("") || 
			numDays.getText().toString().equals("") || docCharge.getText().toString().equals("")
			|| Month.getSelectedItem().equals("") || Day.getSelectedItem().equals("") || Yr.getText().toString().equals("")
			|| gender.getSelectedItem().equals("") || wardType.getSelectedItem().equals("")
			){
		
		JOptionPane.showMessageDialog(null,"Please Fill All Fields Required In Red Color!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
		
	}
	
	else{
	
		try {
			checkBayadInput();
			EditSQL();
			InsertSQL();
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
			
			SC = surgeryPrice.getText().toString();
			
			LC = labPrice.getText().toString();
		
			sc = Double.parseDouble(SC);
			
			
			lc = Double.parseDouble(LC);
			
			totalBill = (dc+wc)*nd + oc + lc + sc;
			
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

				 
				String query = "INSERT INTO billing_history (patient_id, first_name, middle_name, last_name, ward, ward_charge, ward_type, age, address, gender, phic, month, day, year, patient_case, no_days, doctor_charge, other_charge, bill, surgery, surgery_charge, laboratory, laboratory_charge)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				 
				 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
				 
				 

				  ps.setString(1, PID.getText().toString());

				  ps.setString(2, FN.getText().toString()); 

				  ps.setString(3, MN.getText().toString()); 

				  ps.setString(4, LN.getText().toString()); 
				  
				  ps.setString(5, ward.getText().toString());
				  
				  ps.setDouble(6, wc); 
				  
				  ps.setString(7, (String) wardType.getSelectedItem()); 

				  ps.setString(8, age.getText().toString()); 

				  ps.setString(9, address.getText().toString());
				  
				  ps.setString(10, (String) gender.getSelectedItem());

				  ps.setString(11, phic.getText().toString()); 
			 
				  ps.setString(12, (String) Month.getSelectedItem()); 

				  ps.setString(13, (String) Day.getSelectedItem());
				  
				  ps.setString(14, Yr.getText().toString());

				  ps.setString(15, PCase.getText().toString()); 

				  ps.setDouble(16, nd); 

				  ps.setDouble(17, dc);
				  
				  ps.setDouble(18, oc);
				  
				  ps.setDouble(19, totalBill); 
				  
				  ps.setString(20, Surgery.getSelectedItem().toString());
				  
				  ps.setDouble(21, sc);

				  ps.setString(22, Labs.getSelectedItem().toString());
				  
				  ps.setDouble(23, lc);

				
				 ps.execute();

				 // JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
				 
				 


			conn.close();

			}catch (Exception moriel){

			 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

				} 
		

	  

} 




//MUNTIK KO NG HINDI MAKITA ANG METHOD NA ITO SA DAMI NG CODES
//MORIEL!!!! HUHU!
public void ClearFields(){
	
	  //PID.setText("");
	  FN.setText("");
	  MN.setText("");
	  LN.setText("");
	  ward.setText("");
	  wardCharge.setText("0");
	  wardType.setSelectedItem("");
	  age.setText("");
	  address.setText("");
	  gender.setSelectedItem("");
	  Month.setSelectedItem("");
	  Day.setSelectedItem("");
	  Yr.setText("");
	  phic.setText("");
	  PCase.setText(""); 
	  numDays.setText("0"); 
	  docCharge.setText("0"); 
	  otherCharge.setText("0");
	  labPrice.setText("0");
	  surgeryPrice.setText("0");
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
	String check = "SELECT * FROM billing WHERE patient_id="+"\""+PID.getText().toString()+"\"";

	
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
				  ward.setText(rs.getString("ward"));
				  wardType.setSelectedItem(rs.getString("ward_type"));
				  age.setText(rs.getString("age"));
				  address.setText(rs.getString("address"));
				  gender.setSelectedItem(rs.getString("gender"));
				  Month.setSelectedItem(rs.getString("month"));
				  Day.setSelectedItem(rs.getString("day"));
				  Yr.setText(rs.getString("year"));
				  phic.setText(rs.getString("phic"));
				  PCase.setText(rs.getString("patient_case")); 
				  numDays.setText(rs.getString("no_days")); 
				  docCharge.setText(rs.getString("doctor_Charge")); 
				  otherCharge.setText(rs.getString("other_charge"));
				  Surgery.setSelectedItem(rs.getString("surgery"));
				  surgeryPrice.setText(rs.getString("surgery_charge"));
				  Labs.setSelectedItem(rs.getString("laboratory"));
				  labPrice.setText(rs.getString("laboratory_charge"));
				  bill.setText("P "+rs.getString("bill"));

				
				
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


//METHOD PARA SA TABLE ROW CLICKING SEARCH
public void tableClickSearch(){
	 
	
	// RENDERING NA NG RESULTS
	String check = "SELECT * FROM billing WHERE patient_id="+"\""+table.getValueAt(table.getSelectedRow(), 0)+"\"";

	
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
				  ward.setText(rs.getString("ward"));
				  wardType.setSelectedItem(rs.getString("ward_type"));
				  age.setText(rs.getString("age"));
				  address.setText(rs.getString("address"));
				  gender.setSelectedItem(rs.getString("gender"));
				  Month.setSelectedItem(rs.getString("month"));
				  Day.setSelectedItem(rs.getString("day"));
				  Yr.setText(rs.getString("year"));
				  phic.setText(rs.getString("phic"));
				  PCase.setText(rs.getString("patient_case")); 
				  numDays.setText(rs.getString("no_days")); 
				  docCharge.setText(rs.getString("doctor_Charge")); 
				  otherCharge.setText(rs.getString("other_charge"));
				  Surgery.setSelectedItem(rs.getString("surgery"));
				  surgeryPrice.setText(rs.getString("surgery_charge"));
				  Labs.setSelectedItem(rs.getString("laboratory"));
				  labPrice.setText(rs.getString("laboratory_charge"));
				  bill.setText("P "+rs.getString("bill"));

				
				
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
			String query = "UPDATE billing SET first_name=?, middle_name=?, last_name=?, ward=?, ward_charge=?, ward_type=?, age=?, address=?, gender=?, phic=?, month=?, day=?, year=?, patient_case=?, no_days=?, doctor_charge=?, other_charge=?, bill=?, surgery=?, surgery_charge=?, laboratory=?, laboratory_charge=? WHERE patient_id = "+"\""+this.PID.getText().toString()+"\"";
			
			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			

			  ps.setString(1, FN.getText().toString()); 

			  ps.setString(2, MN.getText().toString()); 

			  ps.setString(3, LN.getText().toString()); 
			  
			  ps.setString(4, ward.getText().toString());

			  ps.setDouble(5, wc); 
			  
			  ps.setString(6, (String) wardType.getSelectedItem());

			  ps.setString(7, age.getText().toString()); 

			  ps.setString(8, address.getText().toString());
			  
			  ps.setString(9, (String) gender.getSelectedItem());

			  ps.setString(10, phic.getText().toString()); 
		 
			  ps.setString(11, (String) Month.getSelectedItem()); 

			  ps.setString(12, (String) Day.getSelectedItem());
			  
			  ps.setString(13, Yr.getText().toString());

			  ps.setString(14, PCase.getText().toString()); 

			  ps.setDouble(15, nd); 

			  ps.setDouble(16, dc);
			  
			  ps.setDouble(17, oc);

			  ps.setDouble(18, totalBill); 
			  
			  ps.setString(19, Surgery.getSelectedItem().toString());
			  
			  ps.setDouble(20, sc);

			  ps.setString(21, Labs.getSelectedItem().toString());
			  
			  ps.setDouble(22, lc);



			
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
	   	  
	    sql = "SELECT * FROM billing";
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
        	  
         sql = "SELECT * FROM billing";
         ResultSet rs = stmt.executeQuery(sql);
         table = new JTable(model);
   		 JScrollPane scrollPane = new JScrollPane(table);
   		 table.setPreferredScrollableViewportSize(new Dimension(500, 70));
   		 table.setFillsViewportHeight(true);
   		 table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
   		 table.setBackground(Color.LIGHT_GRAY);
   		 scrollPane.setBounds(780,90,440,360);
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
        	  
        	sql = "SELECT * FROM billing WHERE patient_id LIKE "+"\""+PID.getText().toString()+"%\"";
        	
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
	
	Image logo = Toolkit.getDefaultToolkit().getImage("res/logo.jpg");
	
	
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
      g.drawString("Billing", 270,135);
      
      
      
      g.setFont(new Font("Tahoma",Font.PLAIN,8));
      g.setColor(Color.BLACK);

      g.drawString("Patient ID: "+PID.getText().toString(), 100,160);

      g.drawString("Name: "+FN.getText().toString()+" "+MN.getText().toString()+" "+LN.getText().toString(), 100,170);
      
      g.drawString("Date: "+ Month.getSelectedItem()+" "+Day.getSelectedItem()+", "+Yr.getText().toString(), 100,180);
      
     
      g.drawString("Address: "+address.getText().toString(), 100,190);

      
      g.drawString("Age: "+age.getText().toString(), 100,200);
      
      g.drawString("Gender : "+ gender.getSelectedItem(), 100,210);
     
      g.drawString("Ward/Room No: "+ward.getText().toString(), 100,220);

      
      g.drawString("Ward/Room Charge: "+wardCharge.getText().toString(), 100,230);
      
      
      g.drawString("Ward/Room Type: "+wardType.getSelectedItem(), 100,240);

     
      g.drawString("Address: "+address.getText().toString(), 100,250);

     
      g.drawString("Type Of PHIC: "+phic.getText().toString(), 100,260);

    
     
      g.drawString("Patient Case: "+PCase.getText().toString(), 100,270);


      g.drawString("Number Of Days: "+numDays.getText().toString(), 100,280);

     
      g.drawString("Doctor Charge: "+docCharge.getText().toString(), 100,290);
      g.drawString("Laboratory Charge: "+labPrice.getText().toString(), 240,290);

    
      g.drawString("Other Charges: "+otherCharge.getText().toString(), 100,300);
      g.drawString("Surgery Charge: "+surgeryPrice.getText().toString(), 240,300);
   
      g.drawString("Total Bill: "+bill.getText().toString(), 100,310);

      
      
      g.setFont(new Font("Tahoma",Font.ITALIC,10));
      g.setColor(Color.BLACK);
      //draw line separator
      g.drawString("_______________________________________" +
      		"_________________________________", 70, 330);
    

      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.drawString("Email Address: region1mc2003@yahoo.com", 100,345);
      
      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.drawString("Telephone Numbers: (075) 515-89-16 ; (075)515-89-18 ; (075)515-89-01", 100,355);

      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.drawString("Fax: (075) 523-41-03", 100,365);


    //LINE SEPARATOR FOR CUTTING NANDE KUSO
      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.setColor(Color.BLACK);
      //draw line separator
      g.drawString("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ " +
      		"_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ " +
      		"_ _ _ _ _ _ _ _ _ _ _ _", 70, 375);
    
    
      
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
      g.drawString("Billing", 270,510);
      
      
      
      g.setFont(new Font("Tahoma",Font.PLAIN,8));
      g.setColor(Color.BLACK);

      g.drawString("Patient ID: "+PID.getText().toString(), 100,530);

      g.drawString("Name: "+FN.getText().toString()+" "+MN.getText().toString()+" "+LN.getText().toString(), 100,540);
      
      g.drawString("Date: "+ Month.getSelectedItem()+" "+Day.getSelectedItem()+", "+Yr.getText().toString(), 100,550);
      
     
      g.drawString("Address: "+address.getText().toString(), 100,560);

      
      g.drawString("Age: "+age.getText().toString(), 100,570);
      
      g.drawString("Gender : "+ gender.getSelectedItem(), 100,580);
     
      g.drawString("Ward/Room No: "+ward.getText().toString(), 100,590);

      
      g.drawString("Ward/Room Charge: "+wardCharge.getText().toString(), 100,600);
      
      
      g.drawString("Ward/Room Type: "+wardType.getSelectedItem(), 100,610);

     
      g.drawString("Address: "+address.getText().toString(), 100,620);

     
      g.drawString("Type Of PHIC: "+phic.getText().toString(), 100,630);

    
     
      g.drawString("Patient Case: "+PCase.getText().toString(), 100,640);


      g.drawString("Number Of Days: "+numDays.getText().toString(), 100,650);

     
      g.drawString("Doctor Charge: "+docCharge.getText().toString(), 100,660);
      g.drawString("Laboratory Charge: "+labPrice.getText().toString(), 240,660);

    
      g.drawString("Other Charges: "+otherCharge.getText().toString(), 100,670);
      g.drawString("Surgery Charge: "+surgeryPrice.getText().toString(), 240,670);
   
      g.drawString("Total Bill: "+bill.getText().toString(), 100,680);

      
      
      g.setFont(new Font("Tahoma",Font.ITALIC,10));
      g.setColor(Color.BLACK);
      //draw line separator
      g.drawString("_______________________________________" +
      		"_________________________________", 70, 695);
    

      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.drawString("Email Address: region1mc2003@yahoo.com", 100,705);
      
      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.drawString("Telephone Numbers: (075) 515-89-16 ; (075)515-89-18 ; (075)515-89-01", 100,715);

      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.drawString("Fax: (075) 523-41-03", 100,725);



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
		public void keyPressed(KeyEvent MK) {
			// TODO Auto-generated method stub
			AlternateSearchSQL();
		}

		@Override
		public void keyReleased(KeyEvent MK) {
			// TODO Auto-generated method stub
			AlternateSearchSQL();
		}

		@Override
		public void keyTyped(KeyEvent MK) {
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
		        	  
		        	sql = "SELECT * FROM billing WHERE last_name LIKE "+"\""+LN.getText().toString()+"%\"";
		        	
		        	ResultSet rs = stmt.executeQuery(sql);
		        	table.setModel(DbUtils.resultSetToTableModel(rs));
		         
		    
			 }catch(Exception moriel){
				 
				 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }
			 
			
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
