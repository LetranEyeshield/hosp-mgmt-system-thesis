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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class Pharmacy_Patient_Billing extends JFrame implements ActionListener, MouseListener, Printable, KeyListener{
	
	JLabel labels,title;
	
	
	JTextField pid, fn, mn, ln, age, address, occupation;
	JButton compute, print, save, exit;
	
	
	String meds;
	
	
	JComboBox medicine1,
			  medicine2,
			  medicine3,
			  medicine4,
			  medicine5,
			  medicine6,
			  medicine7,
			  medicine8,
			  medicine9,
			  medicine10;
	
	JTextField quantity1, 
			   quantity2,
			   quantity3,
			   quantity4,
			   quantity5,
			   quantity6,
			   quantity7,
			   quantity8,
			   quantity9,
			   quantity10;
	
	
	JTextField price1, 
	   			price2,
	   			price3,
	   			price4,
	   			price5,
	   			price6,
	   			price7,
	   			price8,
	   			price9,
	   			price10;
	
	JTextField Total1,
	   		   Total2, 
	   		   Total3, 
	   		   Total4, 
	   		   Total5, 
	   		   Total6, 
	   		   Total7, 
	   		   Total8, 
	   		   Total9, 
	   		   Total10; 
	
	
	int getQuantity1, 
		getQuantity2,
		getQuantity3,
		getQuantity4,
		getQuantity5,
		getQuantity6,
		getQuantity7,
		getQuantity8,
		getQuantity9,
		getQuantity10;
		
		double getPrice1,
			getPrice2,
			getPrice3,
			getPrice4,
			getPrice5,
			getPrice6,
			getPrice7,
			getPrice8,
			getPrice9,
			getPrice10;
		
		double getTotal1,
			   getTotal2,
			   getTotal3,
			   getTotal4,
			   getTotal5,
			   getTotal6,
			   getTotal7,
			   getTotal8,
			   getTotal9,
			   getTotal10;
		
		
		double subTotal1,
		   subTotal2,
		   subTotal3,
		   subTotal4,
		   subTotal5,
		   subTotal6,
		   subTotal7,
		   subTotal8,
		   subTotal9,
		   subTotal10;
		
		
		double totalBill, cash, change, balance;
	
		JTextField TotalBill, Cash, Change, Balance;
		
	
	JTable table;

	DefaultTableModel model;

	
	String G[] = {"","Male","Female"};
	JComboBox gender;
	
	String S[] = {"", "Single", "Married", "Widowed","Divorced","Separated"};
	JComboBox status;
	
	
	//PREPARE THE CLASS PARA SA LAST NAME SEARCHING
	Moriel Kim = new Moriel();
		
	
	public Pharmacy_Patient_Billing(){
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
		
		setMedsCombo();
		
		
	}
	
	
	
public void setTitle(){
		
		title = new JLabel("                   " +
				"                 "
				+ "                  " +
				"         PHARMACY PATIENT BILLING");
		title.setBounds(0,0,1060,25);
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

public void setLabels(){
	
	prepareLabels(" Patient ID:",30,80,90,18);
	prepareLabels(" First Name:",30,105,90,18);
	prepareLabels(" Middle Name:",30,130,90,18);
	prepareLabels(" Last Name:",30,155,90,18);
	prepareLabels(" Age:",30,180,90,18);
	prepareLabels(" Gender:",30,205,90,20);
	prepareLabels(" Occupation:",30,230,90,18);
	prepareLabels(" Address:",30,255,90,18);
	prepareLabels(" Civil Status:",30,280,90,18);
	
	prepareLabels(" 1. Medicine:",30,350,90,18);
	prepareLabels(" Quantity:",500,350,90,18);
	prepareLabels(" Price:",680,350,50,18);
	prepareLabels(" Total:",830,350,50,18);
	
	
	
	prepareLabels(" 2. Medicine:",30,375,90,18);
	prepareLabels(" Quantity:",500,375,90,18);
	prepareLabels(" Price:",680,375,50,18);
	prepareLabels(" Total:",830,375,50,18);
	
	
	prepareLabels(" 3. Medicine:",30,400,90,18);
	prepareLabels(" Quantity:",500,400,90,18);
	prepareLabels(" Price:",680,400,50,18);
	prepareLabels(" Total:",830,400,50,18);
	
	
	prepareLabels(" 4. Medicine:",30,425,90,18);
	prepareLabels(" Quantity:",500,425,90,18);
	prepareLabels(" Price:",680,425,50,18);
	prepareLabels(" Total:",830,425,50,18);
	
	
	prepareLabels(" 5. Medicine:",30,450,90,18);
	prepareLabels(" Quantity:",500,450,90,18);
	prepareLabels(" Price:",680,450,50,18);
	prepareLabels(" Total:",830,450,50,18);
	
	prepareLabels(" 6. Medicine:",30,475,90,18);
	prepareLabels(" Quantity:",500,475,90,18);
	prepareLabels(" Price:",680,475,50,18);
	prepareLabels(" Total:",830,475,50,18);
	
	prepareLabels(" 7. Medicine:",30,500,90,18);
	prepareLabels(" Quantity:",500,500,90,18);
	prepareLabels(" Price:",680,500,50,18);
	prepareLabels(" Total:",830,500,50,18);
	
	
	prepareLabels(" 8. Medicine:",30,525,90,18);
	prepareLabels(" Quantity:",500,525,90,18);
	prepareLabels(" Price:",680,525,50,18);
	prepareLabels(" Total:",830,525,50,18);
	
	
	prepareLabels(" 9. Medicine:",30,550,90,18);
	prepareLabels(" Quantity:",500,550,90,18);
	prepareLabels(" Price:",680,550,50,18);
	prepareLabels(" Total:",830,550,50,18);
	
	
	prepareLabels("10. Medicine:",30,575,90,18);
	prepareLabels(" Quantity:",500,575,90,18);
	prepareLabels(" Price:",680,575,50,18);
	prepareLabels(" Total:",830,575,50,18);
	
	prepareLabels(" Total Bill:",820,600,60,18);
	prepareLabels(" Cash:",320,600,50,18);
	prepareLabels(" Change:",670,600,60,18);
	prepareLabels(" Balance:",500,600,90,18);
	
	
	
}


public void setBackground(){

	JLabel background = new JLabel(new ImageIcon("res/pharmacy_patient_billing.png"));
	//JLabel background = new JLabel();
	 background.setBounds(0,0,1060,720);
	// background.setBackground(new Color(0,0,255));
	// background.setBackground(Color.LIGHT_GRAY);
	 background.setOpaque(true);
	 add(background);
	 
}


public void setPanels(){
	
	JLabel panel1 = new JLabel();
	panel1.setBounds(20,70,325,240);
	panel1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel1);
	
	JLabel panel2 = new JLabel();
	panel2.setBounds(360,70,670,240);
	panel2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel2);
	
	JLabel panelLabel1 = new JLabel("  Personal Information ");
	panelLabel1.setBounds(20,50,130,20);
	panelLabel1.setOpaque(true);
	panelLabel1.setBackground(Color.CYAN);
	add(panelLabel1);
	

	JLabel panelLabel2 = new JLabel("  Medicine Charges ");
	panelLabel2.setBounds(20,320,130,20);
	panelLabel2.setOpaque(true);
	panelLabel2.setBackground(Color.CYAN);
	add(panelLabel2);
	
	JLabel panel3 = new JLabel();
	panel3.setBounds(20,340,1010,285);
	panel3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel3);
	
	
	
	
	
}


public void setFields(){
	
	
	pid = new JTextField();
	pid.setBounds(130,80,200,18);
	pid.addKeyListener(this);
	//pid.setEditable(false);
	add(pid);
	
	fn = new JTextField();
	fn.setBounds(130,105,200,18);
	add(fn);
	
	mn = new JTextField();
	mn.setBounds(130,130,200,18);
	add(mn);
	
	ln = new JTextField();
	ln.setBounds(130,155,200,18);
	ln.addKeyListener(Kim);
	add(ln);
	
	age = new JTextField();
	age.setBounds(130,180,200,18);
	add(age);
	
	gender = new JComboBox(G);
	gender.setBounds(130,205,200,18);
	gender.setSelectedIndex(0);
	add(gender);
	
	occupation = new JTextField();
	occupation.setBounds(130,230,200,18);
	add(occupation);
	
	address = new JTextField();
	address.setBounds(130,255,200,18);
	add(address);
	
	
	status = new JComboBox(S);
	status.setBounds(130,280,200,18);
	status.setSelectedIndex(0);
	add(status);
	
	
	medicine1 = new JComboBox();
 	medicine1.setBounds(130,350,350,18);
 	medicine1.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			getMed1();
		}
 		
 	});
	add(medicine1);
	
	quantity1 = new JTextField("0");
 	quantity1.setBounds(600,350,50,18);
	add(quantity1);
	
	price1 = new JTextField("0");
	price1.setEditable(false);
 	price1.setBounds(740,350,70,18);
	add(price1);
	
	
	Total1 = new JTextField("0");
	Total1.setEditable(false);
 	Total1.setBounds(890,350,70,18);
	add(Total1);
	
	
	
	medicine2 = new JComboBox();
 	medicine2.setBounds(130,375,350,18);
 	medicine2.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent jammi) {
			// TODO Auto-generated method stub
			getMed2();
			
		}
 		
 	});
	add(medicine2);
	
	
	quantity2 = new JTextField("0");
 	quantity2.setBounds(600,375,50,18);
	add(quantity2);
	
	price2 = new JTextField("0");
	price2.setEditable(false);
 	price2.setBounds(740,375,70,18);
	add(price2);
	
	
	Total2 = new JTextField("0");
	Total2.setEditable(false);
 	Total2.setBounds(890,375,70,18);
	add(Total2);
	
	
	medicine3 = new JComboBox();
 	medicine3.setBounds(130,400,350,18);
 	medicine3.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent jammi) {
			// TODO Auto-generated method stub
			getMed3();
			
		}
 		
 	});
	add(medicine3);
	
	
	quantity3 = new JTextField("0");
 	quantity3.setBounds(600,400,50,18);
	add(quantity3);
	
	price3 = new JTextField("0");
	price3.setEditable(false);
 	price3.setBounds(740,400,70,18);
	add(price3);
	
	
	Total3 = new JTextField("0");
	Total3.setEditable(false);
 	Total3.setBounds(890,400,70,18);
	add(Total3);
	
	
	medicine4 = new JComboBox();
 	medicine4.setBounds(130,425,350,18);
 	medicine4.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent jammi) {
			// TODO Auto-generated method stub
			getMed4();
			
		}
 		
 	});
	add(medicine4);
	
	
	quantity4 = new JTextField("0");
 	quantity4.setBounds(600,425,50,18);
	add(quantity4);
	
	price4 = new JTextField("0");
	price4.setEditable(false);
 	price4.setBounds(740,425,70,18);
	add(price4);
	
	
	Total4 = new JTextField("0");
	Total4.setEditable(false);
 	Total4.setBounds(890,425,70,18);
	add(Total4);
	
	
	medicine5 = new JComboBox();
 	medicine5.setBounds(130,450,350,18);
 	medicine5.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent jammi) {
			// TODO Auto-generated method stub
			getMed5();
			
		}
 		
 	});
	add(medicine5);
	
	
	quantity5 = new JTextField("0");
 	quantity5.setBounds(600,450,50,18);
	add(quantity5);
	
	price5 = new JTextField("0");
	price5.setEditable(false);
 	price5.setBounds(740,450,70,18);
	add(price5);
	
	
	Total5 = new JTextField("0");
	Total5.setEditable(false);
 	Total5.setBounds(890,450,70,18);
	add(Total5);
	
	
	medicine6 = new JComboBox();
 	medicine6.setBounds(130,475,350,18);
 	medicine6.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent jammi) {
			// TODO Auto-generated method stub
			getMed6();
			
		}
 		
 	});
	add(medicine6);
	
	
	quantity6 = new JTextField("0");
 	quantity6.setBounds(600,475,50,18);
	add(quantity6);
	
	price6 = new JTextField("0");
	price6.setEditable(false);
 	price6.setBounds(740,475,70,18);
	add(price6);
	
	
	Total6 = new JTextField("0");
	Total6.setEditable(false);
 	Total6.setBounds(890,475,70,18);
	add(Total6);
	
	
	medicine7 = new JComboBox();
 	medicine7.setBounds(130,500,350,18);
 	medicine7.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent jammi) {
			// TODO Auto-generated method stub
			getMed7();
			
		}
 		
 	});
	add(medicine7);
	
	
	quantity7 = new JTextField("0");
 	quantity7.setBounds(600,500,50,18);
	add(quantity7);
	
	price7 = new JTextField("0");
	price7.setEditable(false);
 	price7.setBounds(740,500,70,18);
	add(price7);
	
	
	Total7 = new JTextField("0");
	Total7.setEditable(false);
 	Total7.setBounds(890,500,70,18);
	add(Total7);
	
	
	medicine8 = new JComboBox();
 	medicine8.setBounds(130,525,350,18);
 	medicine8.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent jammi) {
			// TODO Auto-generated method stub
			getMed8();
			
		}
 		
 	});
	add(medicine8);
	
	
	quantity8 = new JTextField("0");
 	quantity8.setBounds(600,525,50,18);
	add(quantity8);
	
	price8 = new JTextField("0");
	price8.setEditable(false);
 	price8.setBounds(740,525,70,18);
	add(price8);
	
	
	Total8 = new JTextField("0");
	Total8.setEditable(false);
 	Total8.setBounds(890,525,70,18);
	add(Total8);
	
	
	medicine9 = new JComboBox();
 	medicine9.setBounds(130,550,350,18);
 	medicine9.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent jammi) {
			// TODO Auto-generated method stub
			getMed9();
			
		}
 		
 	});
	add(medicine9);
	
	
	quantity9 = new JTextField("0");
 	quantity9.setBounds(600,550,50,18);
	add(quantity9);
	
	price9 = new JTextField("0");
	price9.setEditable(false);
 	price9.setBounds(740,550,70,18);
	add(price9);
	
	
	Total9 = new JTextField("0");
	Total9.setEditable(false);
 	Total9.setBounds(890,550,70,18);
	add(Total9);
	
	
	medicine10 = new JComboBox();
 	medicine10.setBounds(130,575,350,18);
 	medicine10.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent jammi) {
			// TODO Auto-generated method stub
			getMed10();
			
		}
 		
 	});
	add(medicine10);
	
	
	quantity10 = new JTextField("0");
 	quantity10.setBounds(600,575,50,18);
	add(quantity10);
	
	price10 = new JTextField("0");
	price10.setEditable(false);
 	price10.setBounds(740,575,70,18);
	add(price10);
	
	
	Total10 = new JTextField("0");
	Total10.setEditable(false);
 	Total10.setBounds(890,575,70,18);
	add(Total10);
	
	
	TotalBill = new JTextField("0");
	TotalBill.setEditable(false);
 	TotalBill.setBounds(890,600,120,18);
	add(TotalBill);
	
	Cash = new JTextField("0");
 	Cash.setBounds(380,600,100,18);
	add(Cash);
	
	Change = new JTextField("0");
 	Change.setBounds(740,600,70,18);
 	Change.setEditable(false);
	add(Change);
	
	Balance = new JTextField("0");
 	Balance.setBounds(600,600,50,18);
 	Balance.setEditable(false);
	add(Balance);

	
			
	
}


public void ClearFields(){
	
	
	fn.setText("");
	
	mn.setText("");
	
	ln.setText("");
	
	age.setText("");
	
	gender.setSelectedIndex(0);
	
	occupation.setText("");
	
	address.setText("");
	
	status.setSelectedIndex(0);
	
 	medicine1.setSelectedIndex(0);
 	medicine2.setSelectedIndex(0);
 	medicine3.setSelectedIndex(0);
 	medicine4.setSelectedIndex(0);
 	medicine5.setSelectedIndex(0);
 	medicine6.setSelectedIndex(0);
 	medicine7.setSelectedIndex(0);
 	medicine8.setSelectedIndex(0);
 	medicine9.setSelectedIndex(0);
 	medicine10.setSelectedIndex(0);
 	
 	
 	quantity1.setText("0");
 	quantity2.setText("0");
 	quantity3.setText("0");
 	quantity4.setText("0");
 	quantity5.setText("0");
 	quantity6.setText("0");
 	quantity7.setText("0");
 	quantity8.setText("0");
 	quantity9.setText("0");
 	quantity10.setText("0");
	
 	price1.setText("0");
 	price2.setText("0");
 	price3.setText("0");
 	price4.setText("0");
 	price5.setText("0");
 	price6.setText("0");
 	price7.setText("0");
 	price8.setText("0");
 	price9.setText("0");
 	price10.setText("0");
 	
 	Total1.setText("0");
 	Total2.setText("0");
 	Total3.setText("0");
 	Total4.setText("0");
 	Total5.setText("0");
 	Total6.setText("0");
 	Total7.setText("0");
 	Total8.setText("0");
 	Total9.setText("0");
 	Total10.setText("0");
	
 	TotalBill.setText("0");
	
 	Cash.setText("0");
 	
 	Change.setText("0");
 	
 	Balance.setText("P ");
			

			
	
}


public void setMedsCombo(){
	
	
String query = "SELECT * FROM pharmacy ORDER BY brand_name ASC";

	
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
				 
				 	meds = rs.getString("brand_name");
				 
				 	
				 	medicine1.addItem(meds);
				 	medicine2.addItem(meds);
				 	medicine3.addItem(meds);
				 	medicine4.addItem(meds);
				 	medicine5.addItem(meds);
				 	medicine6.addItem(meds);
				 	medicine7.addItem(meds);
				 	medicine8.addItem(meds);
				 	medicine9.addItem(meds);
				 	medicine10.addItem(meds);
				 	
				 	

				
		counter++;
			 }
			 

			 if(counter<1){
				 
				 JOptionPane.showMessageDialog(null,"Ward Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }

		conn.close();

		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 

			} 

	
	
	
}


public void setButtons(){
	

	compute = new JButton("COMPUTE");
	compute.setBounds(200,650,100,25);
	compute.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			
			Compute();
		}
		
	});
	add(compute);
	
	
	save = new JButton("SAVE");
	save.setBounds(330,650,80,25);
	save.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			
			EditSQL();
			InsertHistorySQL();
			
		}
		
	});
	add(save);
	
	
	
	print = new JButton("PRINT");
	print.setBounds(440,650,100,25);
	print.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			
			printMe();
		}
		
	});
	add(print);
	
	
	exit = new JButton("EXIT");
	exit.setBounds(570,650,100,25);
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
	line.setBounds(0,630,1060,1);
	line.setOpaque(true
			);
	line.setBackground(Color.RED);
	add(line);
}



//METHOD PARA SA COMPUTATION KONOYAROU!
public void Compute(){
	
	getQuantity1 = Integer.parseInt(quantity1.getText().toString());
	getQuantity2 = Integer.parseInt(quantity2.getText().toString());
	getQuantity3 = Integer.parseInt(quantity3.getText().toString());
	getQuantity4 = Integer.parseInt(quantity4.getText().toString());
	getQuantity5 = Integer.parseInt(quantity5.getText().toString());
	getQuantity6 = Integer.parseInt(quantity6.getText().toString());
	getQuantity7 = Integer.parseInt(quantity7.getText().toString());
	getQuantity8 = Integer.parseInt(quantity8.getText().toString());
	getQuantity9 = Integer.parseInt(quantity9.getText().toString());
	getQuantity10 = Integer.parseInt(quantity10.getText().toString());
	
	getPrice1 = Double.parseDouble(price1.getText().toString());
	getPrice2 = Double.parseDouble(price2.getText().toString());
	getPrice3 = Double.parseDouble(price3.getText().toString());
	getPrice4 = Double.parseDouble(price4.getText().toString());
	getPrice5 = Double.parseDouble(price5.getText().toString());
	getPrice6 = Double.parseDouble(price6.getText().toString());
	getPrice7 = Double.parseDouble(price7.getText().toString());
	getPrice8 = Double.parseDouble(price8.getText().toString());
	getPrice8 = Double.parseDouble(price9.getText().toString());
	getPrice10 = Double.parseDouble(price10.getText().toString());
	
	getTotal1 = Double.parseDouble(Total1.getText().toString());
	getTotal2 = Double.parseDouble(Total2.getText().toString());
	getTotal3 = Double.parseDouble(Total3.getText().toString());
	getTotal4 = Double.parseDouble(Total4.getText().toString());
	getTotal5 = Double.parseDouble(Total5.getText().toString());
	getTotal6 = Double.parseDouble(Total6.getText().toString());
	getTotal7 = Double.parseDouble(Total7.getText().toString());
	getTotal8 = Double.parseDouble(Total8.getText().toString());
	getTotal9 = Double.parseDouble(Total9.getText().toString());
	getTotal10 = Double.parseDouble(Total10.getText().toString());
	
	
	
	subTotal1 = getPrice1 * getQuantity1;
	subTotal2 = getPrice2 * getQuantity2;
	subTotal3 = getPrice3 * getQuantity3;
	subTotal4 = getPrice4 * getQuantity4;
	subTotal5 = getPrice5 * getQuantity5;
	subTotal6 = getPrice6 * getQuantity6;
	subTotal7 = getPrice7 * getQuantity7;
	subTotal8 = getPrice8 * getQuantity8;
	subTotal9 = getPrice9 * getQuantity9;
	subTotal10 = getPrice10 * getQuantity10;
	
	
	totalBill = subTotal1 + subTotal2 + subTotal3 + subTotal4 +
			subTotal5 + subTotal6 +subTotal7 + subTotal8 +
			subTotal9 + subTotal10;
	
	cash = Double.parseDouble(Cash.getText().toString());
	
	change = cash-totalBill;
	
	balance = totalBill-cash;
	
	if(totalBill<cash){
		
		Balance.setText("P ");
		
	}else{
		Balance.setText("P "+balance);
	}
	
	
	if(cash<totalBill){
		Change.setText("P ");
		
	}else{
		Change.setText("P "+change);
		
	}
	
	
	
	
	Total1.setText(subTotal1+"");
	Total2.setText(subTotal2+"");
	Total3.setText(subTotal3+"");
	Total4.setText(subTotal4+"");
	Total5.setText(subTotal5+"");
	Total6.setText(subTotal6+"");
	Total7.setText(subTotal7+"");
	Total8.setText(subTotal8+"");
	Total9.setText(subTotal9+"");
	Total10.setText(subTotal10+"");
	
	TotalBill.setText("P "+totalBill);
	
	
	
	
}


//LONG CODE PERO DI KO GINAMIT, KEEP KO LANG BAKA MAGAMIT IN THE FUTURE
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

//set the sql query
String query = "INSERT INTO pharmacy_patient_billing (patient_id,first_name,middle_name,last_name,age,gender,address,occupation,status,bill,payment,balance," +
		"		meds1,meds2,meds3,meds4,meds5,meds6,meds7,meds8,meds9,meds10," +
		"quantity1,quantity2,quantity3,quantity4,quantity5,quantity6,quantity7,quantity8,quantity9,quantity10," +
		"price1,price2,price3,price4,price5,price6,price7,price8,price9,price10)" 
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
				"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


java.sql.Statement stmt = conn.createStatement();


	 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);

	 
	 	ps.setString(1, pid.getText().toString());
	 	ps.setString(2, fn.getText().toString());
	 	ps.setString(3, mn.getText().toString());
	 	ps.setString(4, ln.getText().toString());
	 	ps.setString(5, age.getText().toString());
	 	ps.setString(6, gender.getSelectedItem().toString());
	 	ps.setString(7, address.getText().toString());
	 	ps.setString(8, occupation.getText().toString());
	 	ps.setString(9, status.getSelectedItem().toString());
	 	ps.setString(10, TotalBill.getText().toString());
	 	ps.setString(11, Cash.getText().toString());
	 	ps.setString(12, Balance.getText().toString());
	 	ps.setString(13, medicine1.getSelectedItem().toString());
	 	ps.setString(14, medicine2.getSelectedItem().toString());
	 	ps.setString(15, medicine3.getSelectedItem().toString());
	 	ps.setString(16, medicine4.getSelectedItem().toString());
	 	ps.setString(17, medicine5.getSelectedItem().toString());
	 	ps.setString(18, medicine6.getSelectedItem().toString());
	 	ps.setString(19, medicine7.getSelectedItem().toString());
	 	ps.setString(20, medicine8.getSelectedItem().toString());
	 	ps.setString(21, medicine9.getSelectedItem().toString());
	 	ps.setString(22, medicine10.getSelectedItem().toString());
	 	ps.setString(23, quantity1.getText().toString());
	 	ps.setString(24, quantity2.getText().toString());
	 	ps.setString(25, quantity3.getText().toString());
	 	ps.setString(26, quantity4.getText().toString());
	 	ps.setString(27, quantity5.getText().toString());
	 	ps.setString(28, quantity6.getText().toString());
	 	ps.setString(29, quantity7.getText().toString());
	 	ps.setString(30, quantity8.getText().toString());
	 	ps.setString(31, quantity9.getText().toString());
	 	ps.setString(32, quantity10.getText().toString());
	 	ps.setString(33, price1.getText().toString());
	 	ps.setString(34, price2.getText().toString());
	 	ps.setString(35, price3.getText().toString());
	 	ps.setString(36, price4.getText().toString());
	 	ps.setString(37, price5.getText().toString());
	 	ps.setString(38, price6.getText().toString());
	 	ps.setString(39, price7.getText().toString());
	 	ps.setString(40, price8.getText().toString());
	 	ps.setString(41, price9.getText().toString());
	 	ps.setString(42, price10.getText().toString());

	   

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
   	  
    sql = "SELECT * FROM pharmacy_patient_billing";
    ResultSet rs = stmt.executeQuery(sql);
		
		 table.setModel(DbUtils.resultSetToTableModel(rs));

}catch (Exception moriel){

		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

}




} 



//METHOD PARA INSERT SA PHARMACY PATIENT BILLING HISTORY
//METHOD FOR ADD KONOYAROU!
public void InsertHistorySQL(){
	
	  
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
String query = "INSERT INTO pharmacy_patient_billing_history (patient_id,first_name,middle_name,last_name,age,gender,address,occupation,status,bill,payment,balance," +
		"		meds1,meds2,meds3,meds4,meds5,meds6,meds7,meds8,meds9,meds10," +
		"quantity1,quantity2,quantity3,quantity4,quantity5,quantity6,quantity7,quantity8,quantity9,quantity10," +
		"price1,price2,price3,price4,price5,price6,price7,price8,price9,price10)" 
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
				"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


java.sql.Statement stmt = conn.createStatement();


	 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);

	 
	 	ps.setString(1, pid.getText().toString());
	 	ps.setString(2, fn.getText().toString());
	 	ps.setString(3, mn.getText().toString());
	 	ps.setString(4, ln.getText().toString());
	 	ps.setString(5, age.getText().toString());
	 	ps.setString(6, gender.getSelectedItem().toString());
	 	ps.setString(7, address.getText().toString());
	 	ps.setString(8, occupation.getText().toString());
	 	ps.setString(9, status.getSelectedItem().toString());
	 	ps.setString(10, TotalBill.getText().toString());
	 	ps.setString(11, Cash.getText().toString());
	 	ps.setString(12, Balance.getText().toString());
	 	ps.setString(13, medicine1.getSelectedItem().toString());
	 	ps.setString(14, medicine2.getSelectedItem().toString());
	 	ps.setString(15, medicine3.getSelectedItem().toString());
	 	ps.setString(16, medicine4.getSelectedItem().toString());
	 	ps.setString(17, medicine5.getSelectedItem().toString());
	 	ps.setString(18, medicine6.getSelectedItem().toString());
	 	ps.setString(19, medicine7.getSelectedItem().toString());
	 	ps.setString(20, medicine8.getSelectedItem().toString());
	 	ps.setString(21, medicine9.getSelectedItem().toString());
	 	ps.setString(22, medicine10.getSelectedItem().toString());
	 	ps.setString(23, quantity1.getText().toString());
	 	ps.setString(24, quantity2.getText().toString());
	 	ps.setString(25, quantity3.getText().toString());
	 	ps.setString(26, quantity4.getText().toString());
	 	ps.setString(27, quantity5.getText().toString());
	 	ps.setString(28, quantity6.getText().toString());
	 	ps.setString(29, quantity7.getText().toString());
	 	ps.setString(30, quantity8.getText().toString());
	 	ps.setString(31, quantity9.getText().toString());
	 	ps.setString(32, quantity10.getText().toString());
	 	ps.setString(33, price1.getText().toString());
	 	ps.setString(34, price2.getText().toString());
	 	ps.setString(35, price3.getText().toString());
	 	ps.setString(36, price4.getText().toString());
	 	ps.setString(37, price5.getText().toString());
	 	ps.setString(38, price6.getText().toString());
	 	ps.setString(39, price7.getText().toString());
	 	ps.setString(40, price8.getText().toString());
	 	ps.setString(41, price9.getText().toString());
	 	ps.setString(42, price10.getText().toString());

	   

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
 	  
  sql = "SELECT * FROM pharmacy_patient_billing";
  ResultSet rs = stmt.executeQuery(sql);
		
		 table.setModel(DbUtils.resultSetToTableModel(rs));

}catch (Exception moriel){

		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

}




} 




//METHOD FOR SEARCHING ACCOUNT BAKAYAROU!
public void SearchSQL(){
	  
	ClearFields();
	
	String query = "SELECT * FROM pharmacy_patient_billing WHERE patient_id="+"\""+pid.getText().toString()+"\"";

	
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

				 pid.setText(rs.getString("patient_id"));
				 fn.setText(rs.getString("first_name"));
				 mn.setText(rs.getString("middle_name"));
				 ln.setText(rs.getString("last_name"));
				 age.setText(rs.getString("age"));
				 gender.setSelectedItem(rs.getString("gender"));
				 address.setText(rs.getString("address"));
				 occupation.setText(rs.getString("occupation"));
				 status.setSelectedItem(rs.getString("status"));
				 TotalBill.setText(rs.getString("bill"));
				 Cash.setText(rs.getString("payment"));
				 Balance.setText(rs.getString("balance"));
				 medicine1.setSelectedItem(rs.getString("meds1"));
				 medicine2.setSelectedItem(rs.getString("meds2"));
				 medicine3.setSelectedItem(rs.getString("meds3"));
				 medicine4.setSelectedItem(rs.getString("meds4"));
				 medicine5.setSelectedItem(rs.getString("meds5"));
				 medicine6.setSelectedItem(rs.getString("meds6"));
				 medicine7.setSelectedItem(rs.getString("meds7"));
				 medicine8.setSelectedItem(rs.getString("meds8"));
				 medicine9.setSelectedItem(rs.getString("meds9"));
				 medicine10.setSelectedItem(rs.getString("meds10"));
				 quantity1.setText(rs.getString("quantity1"));
				 quantity2.setText(rs.getString("quantity2"));
				 quantity3.setText(rs.getString("quantity3"));
				 quantity4.setText(rs.getString("quantity4"));
				 quantity5.setText(rs.getString("quantity5"));
				 quantity6.setText(rs.getString("quantity6"));
				 quantity7.setText(rs.getString("quantity7"));
				 quantity8.setText(rs.getString("quantity8"));
				 quantity9.setText(rs.getString("quantity9"));
				 quantity10.setText(rs.getString("quantity10"));
				 Total1.setText(rs.getString("price1"));
				 Total2.setText(rs.getString("price2"));
				 Total3.setText(rs.getString("price3"));
				 Total4.setText(rs.getString("price4"));
				 Total5.setText(rs.getString("price5"));
				 Total6.setText(rs.getString("price6"));
				 Total7.setText(rs.getString("price7"));
				 Total8.setText(rs.getString("price8"));
				 Total9.setText(rs.getString("price9"));
				 Total10.setText(rs.getString("price10"));

				 	
				 	
				
		counter++;
			 }
			 

			 if(counter<1){
				 
				 //JOptionPane.showMessageDialog(null,"Patient Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }

		conn.close();

		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 

			} 

} 



//METHOD PARA SA TABLE ROW CLICKING
public void tableClickSearch(){
	  
	
	String query = "SELECT * FROM pharmacy_patient_billing WHERE patient_id="+"\""+table.getValueAt(table.getSelectedRow(), 0)+"\"";

	
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

				 pid.setText(rs.getString("patient_id"));
				 fn.setText(rs.getString("first_name"));
				 mn.setText(rs.getString("middle_name"));
				 ln.setText(rs.getString("last_name"));
				 age.setText(rs.getString("age"));
				 gender.setSelectedItem(rs.getString("gender"));
				 address.setText(rs.getString("address"));
				 occupation.setText(rs.getString("occupation"));
				 status.setSelectedItem(rs.getString("status"));
				 TotalBill.setText(rs.getString("bill"));
				 Cash.setText(rs.getString("payment"));
				 Balance.setText(rs.getString("balance"));
				 medicine1.setSelectedItem(rs.getString("meds1"));
				 medicine2.setSelectedItem(rs.getString("meds2"));
				 medicine3.setSelectedItem(rs.getString("meds3"));
				 medicine4.setSelectedItem(rs.getString("meds4"));
				 medicine5.setSelectedItem(rs.getString("meds5"));
				 medicine6.setSelectedItem(rs.getString("meds6"));
				 medicine7.setSelectedItem(rs.getString("meds7"));
				 medicine8.setSelectedItem(rs.getString("meds8"));
				 medicine9.setSelectedItem(rs.getString("meds9"));
				 medicine10.setSelectedItem(rs.getString("meds10"));
				 quantity1.setText(rs.getString("quantity1"));
				 quantity2.setText(rs.getString("quantity2"));
				 quantity3.setText(rs.getString("quantity3"));
				 quantity4.setText(rs.getString("quantity4"));
				 quantity5.setText(rs.getString("quantity5"));
				 quantity6.setText(rs.getString("quantity6"));
				 quantity7.setText(rs.getString("quantity7"));
				 quantity8.setText(rs.getString("quantity8"));
				 quantity9.setText(rs.getString("quantity9"));
				 quantity10.setText(rs.getString("quantity10"));
				 Total1.setText(rs.getString("price1"));
				 Total2.setText(rs.getString("price2"));
				 Total3.setText(rs.getString("price3"));
				 Total4.setText(rs.getString("price4"));
				 Total5.setText(rs.getString("price5"));
				 Total6.setText(rs.getString("price6"));
				 Total7.setText(rs.getString("price7"));
				 Total8.setText(rs.getString("price8"));
				 Total9.setText(rs.getString("price9"));
				 Total10.setText(rs.getString("price10"));

				 	
				 	
				
		counter++;
			 }
			 

			 if(counter<1){
				 
				 JOptionPane.showMessageDialog(null,"Patient Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
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
			String query = "UPDATE pharmacy_patient_billing SET patient_id=?,first_name=?,middle_name=?,last_name=?,age=?,gender=?,address=?,occupation=?,status=?,bill=?,payment=?,balance=?," +
					"meds1=?,meds2=?,meds3=?,meds4=?,meds5=?,meds6=?,meds7=?,meds8=?,meds9=?,meds10=?," +
					" quantity1=?,quantity2=?,quantity3=?,quantity4=?,quantity5=?,quantity6=?,quantity7=?,quantity8=?,quantity9=?,quantity10=?," +
					"price1=?,price2=?,price3=?,price4=?,price5=?,price6=?,price7=?,price8=?,price9=?,price10=?" +
					"WHERE patient_id = "+"\""+pid.getText().toString()+"\"";

			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			
			
			ps.setString(1, pid.getText().toString());
		 	ps.setString(2, fn.getText().toString());
		 	ps.setString(3, mn.getText().toString());
		 	ps.setString(4, ln.getText().toString());
		 	ps.setString(5, age.getText().toString());
		 	ps.setString(6, gender.getSelectedItem().toString());
		 	ps.setString(7, address.getText().toString());
		 	ps.setString(8, occupation.getText().toString());
		 	ps.setString(9, status.getSelectedItem().toString());
		 	ps.setString(10, TotalBill.getText().toString());
		 	ps.setString(11, Cash.getText().toString());
		 	ps.setString(12, Balance.getText().toString());
		 	ps.setString(13, medicine1.getSelectedItem().toString());
		 	ps.setString(14, medicine2.getSelectedItem().toString());
		 	ps.setString(15, medicine3.getSelectedItem().toString());
		 	ps.setString(16, medicine4.getSelectedItem().toString());
		 	ps.setString(17, medicine5.getSelectedItem().toString());
		 	ps.setString(18, medicine6.getSelectedItem().toString());
		 	ps.setString(19, medicine7.getSelectedItem().toString());
		 	ps.setString(20, medicine8.getSelectedItem().toString());
		 	ps.setString(21, medicine9.getSelectedItem().toString());
		 	ps.setString(22, medicine10.getSelectedItem().toString());
		 	ps.setString(23, quantity1.getText().toString());
		 	ps.setString(24, quantity2.getText().toString());
		 	ps.setString(25, quantity3.getText().toString());
		 	ps.setString(26, quantity4.getText().toString());
		 	ps.setString(27, quantity5.getText().toString());
		 	ps.setString(28, quantity6.getText().toString());
		 	ps.setString(29, quantity7.getText().toString());
		 	ps.setString(30, quantity8.getText().toString());
		 	ps.setString(31, quantity9.getText().toString());
		 	ps.setString(32, quantity10.getText().toString());
		 	ps.setString(33, price1.getText().toString());
		 	ps.setString(34, price2.getText().toString());
		 	ps.setString(35, price3.getText().toString());
		 	ps.setString(36, price4.getText().toString());
		 	ps.setString(37, price5.getText().toString());
		 	ps.setString(38, price6.getText().toString());
		 	ps.setString(39, price7.getText().toString());
		 	ps.setString(40, price8.getText().toString());
		 	ps.setString(41, price9.getText().toString());
		 	ps.setString(42, price10.getText().toString());

		  
			
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
	   	  
	    sql = "SELECT * FROM pharmacy_patient_billing";
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
        	  
         sql = "SELECT * FROM pharmacy_patient_billing";
         ResultSet rs = stmt.executeQuery(sql);
         table = new JTable(model);
   		 JScrollPane scrollPane = new JScrollPane(table);
   		 table.setPreferredScrollableViewportSize(new Dimension(500, 70));
   		 table.setFillsViewportHeight(true);
   		 table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
   		 table.setBackground(Color.LIGHT_GRAY);
   		 scrollPane.setBounds(370,80,650,220);
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
        	  
        	sql = "SELECT * FROM pharmacy_patient_billing WHERE patient_id LIKE "+"\""+pid.getText().toString()+"%\"";
        	ResultSet rs = stmt.executeQuery(sql);
        	table.setModel(DbUtils.resultSetToTableModel(rs));
         
    
	 }catch(Exception moriel){
		 
		 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
	 }
	 
	
}


//METHODS BELOW ALIKE THIS ARE FOR GETTING THE PRICES KUNG HIT NILA UNG COMBO MEDS
public void getMed1(){
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+medicine1.getSelectedItem().toString()+"\"";
 	
			
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
						 
						 
						 price1.setText(rs.getString("price"));
						

						
				counter++;
					 }
					 

					 if(counter<1){
						 
						 JOptionPane.showMessageDialog(null,"Medicine Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 
					 }

				conn.close();

				}catch (Exception moriel){

				 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 

					} 

			
			
			
		}
	


public void getMed2(){
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+medicine2.getSelectedItem().toString()+"\"";
 	
			
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
						 
						 
						 price2.setText(rs.getString("price"));
						

						
				counter++;
					 }
					 

					 if(counter<1){
						 
						 JOptionPane.showMessageDialog(null,"Medicine Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 
					 }

				conn.close();

				}catch (Exception moriel){

				 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 

					} 

			
			
			
		}




public void getMed3(){
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+medicine3.getSelectedItem().toString()+"\"";
 	
			
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
						 
						 
						 price3.setText(rs.getString("price"));
						

						
				counter++;
					 }
					 

					 if(counter<1){
						 
						 JOptionPane.showMessageDialog(null,"Medicine Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 
					 }

				conn.close();

				}catch (Exception moriel){

				 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 

					} 

			
			
			
		}
	
	

	
public void getMed4(){
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+medicine4.getSelectedItem().toString()+"\"";
 	
			
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
						 
						 
						 price4.setText(rs.getString("price"));
						

						
				counter++;
					 }
					 

					 if(counter<1){
						 
						 JOptionPane.showMessageDialog(null,"Medicine Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 
					 }

				conn.close();

				}catch (Exception moriel){

				 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 

					} 

			
			
			
		}
	


public void getMed5(){
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+medicine5.getSelectedItem().toString()+"\"";
 	
			
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
						 
						 
						 price5.setText(rs.getString("price"));
						

						
				counter++;
					 }
					 

					 if(counter<1){
						 
						 JOptionPane.showMessageDialog(null,"Medicine Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 
					 }

				conn.close();

				}catch (Exception moriel){

				 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 

					} 

			
			
			
		}
	


public void getMed6(){
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+medicine6.getSelectedItem().toString()+"\"";
 	
			
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
						 
						 
						 price6.setText(rs.getString("price"));
						

						
				counter++;
					 }
					 

					 if(counter<1){
						 
						 JOptionPane.showMessageDialog(null,"Medicine Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 
					 }

				conn.close();

				}catch (Exception moriel){

				 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 

					} 

			
			
			
		}



public void getMed7(){
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+medicine7.getSelectedItem().toString()+"\"";
 	
			
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
						 
						 
						 price7.setText(rs.getString("price"));
						

						
				counter++;
					 }
					 

					 if(counter<1){
						 
						 JOptionPane.showMessageDialog(null,"Medicine Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 
					 }

				conn.close();

				}catch (Exception moriel){

				 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 

					} 

			
			
			
		}
	
	


public void getMed8(){
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+medicine8.getSelectedItem().toString()+"\"";
 	
			
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
						 
						 
						 price8.setText(rs.getString("price"));
						

						
				counter++;
					 }
					 

					 if(counter<1){
						 
						 JOptionPane.showMessageDialog(null,"Medicine Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 
					 }

				conn.close();

				}catch (Exception moriel){

				 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 

					} 

			
			
			
		}
	


public void getMed9(){
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+medicine9.getSelectedItem().toString()+"\"";
 	
			
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
						 
						 
						 price9.setText(rs.getString("price"));
						

						
				counter++;
					 }
					 

					 if(counter<1){
						 
						 JOptionPane.showMessageDialog(null,"Medicine Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 
					 }

				conn.close();

				}catch (Exception moriel){

				 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 

					} 

			
			
			
		}
	
		
	
public void getMed10(){
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+medicine10.getSelectedItem().toString()+"\"";
 	
			
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
						 
						 
						 price10.setText(rs.getString("price"));
						

						
				counter++;
					 }
					 

					 if(counter<1){
						 
						 JOptionPane.showMessageDialog(null,"Medicine Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
						 
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
	
	
	//METHOD PARA SA EXIT BUTTON
	public void CloseMe(){
		this.dispose();
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
	        
	        
	        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   	 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	   	 Date date = new Date();

	       
	        /* Now we perform our rendering */
	        g.drawImage(logo, 270, 10, 60, 60, this);

	        g.setFont(new Font("Tahoma",Font.BOLD,20));
	        g.setColor(Color.RED);
	        g.drawString(" HOSPITAL MANAGEMENT SYSTEM", 140, 90);
	       
	        
	        g.setFont(new Font("Tahoma",Font.ITALIC,8));
	        g.setColor(Color.BLACK);
	        g.drawString("Arellano Street, Dagupan City 2400, Pangasinan, Philippines", 180,100);

	        //draw line separator
	        g.drawString("_______________________________________" +
	        		"________________________________________________", 70, 110);
	        
	        g.setFont(new Font("Tahoma",Font.ITALIC,10));
	        g.setColor(Color.BLACK);
	        g.drawString("Patient Pharmacy Billing", 230,125);
	        
	        
	        g.setFont(new Font("Tahoma",Font.ITALIC,7));
	        g.setColor(Color.BLACK);
	        g.drawString(dateFormat.format(date), 410,125);
	        
	        
	      
	        g.setFont(new Font("Tahoma",Font.PLAIN,8));
	        g.setColor(Color.BLACK);
	        g.drawString("Patient ID: "+pid.getText().toString(), 100,140);
	        
	       
	        g.drawString("Name: "+fn.getText().toString()+" "+mn.getText().toString()+" "+ln.getText().toString(), 100,150);
	      
	       
	        g.drawString("Age: "+age.getText().toString(), 100,160);

	        
	        g.drawString("Gender: "+gender.getSelectedItem().toString(), 230,160);
	        
	       
	       g.drawString("Occupation: "+ occupation.getText().toString(), 340,160);

	       
	        g.drawString("Address: "+address.getText().toString(), 100,170);

	       
	        g.drawString("Civil Status: "+status.getSelectedItem().toString(), 340,170);

	        
	        g.drawString("Medicine(s) ", 100,190);
	        
	        
	        g.drawString(medicine1.getSelectedItem().toString(), 100,200);
	        g.drawString(quantity1.getText().toString(), 180,200);
	        g.drawString(price1.getText().toString(), 280,200);
	        g.drawString(Total1.getText().toString(), 380,200);
	        
	        g.drawString(medicine2.getSelectedItem().toString(), 100,210);
	        g.drawString(quantity2.getText().toString(), 180,210);
	        g.drawString(price2.getText().toString(), 280,210);
	        g.drawString(Total2.getText().toString(), 380,210);
	        
	        g.drawString(medicine3.getSelectedItem().toString(), 100,220);
	        g.drawString(quantity3.getText().toString(), 180,220);
	        g.drawString(price3.getText().toString(), 280,220);
	        g.drawString(Total3.getText().toString(), 380,220);
	        
	        g.drawString(medicine4.getSelectedItem().toString(), 100,230);
	        g.drawString(quantity4.getText().toString(), 180,230);
	        g.drawString(price4.getText().toString(), 280,230);
	        g.drawString(Total4.getText().toString(), 380,230);
	        
	        g.drawString(medicine5.getSelectedItem().toString(), 100,240);
	        g.drawString(quantity5.getText().toString(), 180,240);
	        g.drawString(price5.getText().toString(), 280,240);
	        g.drawString(Total5.getText().toString(), 380,240);
	        
	        
	        g.drawString(medicine6.getSelectedItem().toString(), 100,250);
	        g.drawString(quantity6.getText().toString(), 180,250);
	        g.drawString(price6.getText().toString(), 280,250);
	        g.drawString(Total6.getText().toString(), 380,250);
	        
	        
	        g.drawString(medicine7.getSelectedItem().toString(), 100,260);
	        g.drawString(quantity7.getText().toString(), 180,260);
	        g.drawString(price7.getText().toString(), 280,260);
	        g.drawString(Total7.getText().toString(), 380,260);
	        
	        g.drawString(medicine8.getSelectedItem().toString(), 100,270);
	        g.drawString(quantity8.getText().toString(), 180,270);
	        g.drawString(price8.getText().toString(), 280,270);
	        g.drawString(Total8.getText().toString(), 380,270);
	        
	        g.drawString(medicine9.getSelectedItem().toString(), 100,280);
	        g.drawString(quantity9.getText().toString(), 180,280);
	        g.drawString(price9.getText().toString(), 280,280);
	        g.drawString(Total9.getText().toString(), 380,280);
	        
	        g.drawString(medicine10.getSelectedItem().toString(), 100,290);
	        g.drawString(quantity10.getText().toString(), 180,290);
	        g.drawString(price10.getText().toString(), 280,290);
	        g.drawString(Total10.getText().toString(), 380,290);
	        
	        g.drawString("Total Bill: "+TotalBill.getText().toString(), 100,300);
	        g.drawString("Cash: "+Cash.getText().toString(), 100,310);
	        g.drawString("Change: "+Change.getText().toString(), 100,320);
	        g.drawString("Balance: "+Balance.getText().toString(), 100,330);
	       
	       
	       
	        
	        
	        g.setFont(new Font("Tahoma",Font.ITALIC,10));
	        g.setColor(Color.BLACK);
	        //draw line separator
	        g.drawString("_______________________________________" +
	        		"________________________________________________", 70, 345);
	      
	       
	        g.setFont(new Font("Tahoma",Font.ITALIC,8));
	        g.drawString("Email Address: region1mc2003@yahoo.com", 100,360);
	        
	        g.setFont(new Font("Tahoma",Font.ITALIC,8));
	        g.drawString("Telephone Numbers: (075) 515-89-16 ; (075)515-89-18 ; (075)515-89-01", 100,370);

	        g.setFont(new Font("Tahoma",Font.ITALIC,8));
	        g.drawString("Fax: (075) 523-41-03", 100,380);
	      
	        
	        //LINE SEPARATOR FOR CUTTING NANDE KUSO
	        g.setFont(new Font("Tahoma",Font.ITALIC,8));
	        g.setColor(Color.BLACK);
	        //draw line separator
	        g.drawString("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ " +
	        		"_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ " +
	        		"_ _ _ _ _ _ _ _ _ _ _ _", 70, 390);

	        
	        //2ND COPY
	        
	        g.drawImage(logo, 270, 410, 60, 60, this);

	        g.setFont(new Font("Tahoma",Font.BOLD,20));
	        g.setColor(Color.RED);
	        g.drawString(" HOSPITAL MANAGEMENT SYSTEM", 140, 490);
	       
	        
	        g.setFont(new Font("Tahoma",Font.ITALIC,8));
	        g.setColor(Color.BLACK);
	        g.drawString("Arellano Street, Dagupan City 2400, Pangasinan, Philippines", 180,500);

	        //draw line separator
	        g.drawString("_______________________________________" +
	        		"________________________________________________", 70, 510);
	        
	        g.setFont(new Font("Tahoma",Font.ITALIC,10));
	        g.setColor(Color.BLACK);
	        g.drawString("Pharmacy Patient Billing", 230,520);
	        
	        
	        g.setFont(new Font("Tahoma",Font.ITALIC,7));
	        g.setColor(Color.BLACK);
	        g.drawString(dateFormat.format(date), 410,520);
	        
	        
	      
	        g.setFont(new Font("Tahoma",Font.PLAIN,8));
	        g.setColor(Color.BLACK);
	        g.drawString("Patient ID: "+pid.getText().toString(), 100,540);
	        
		       
	        g.drawString("Name: "+fn.getText().toString()+" "+mn.getText().toString()+" "+ln.getText().toString(), 100,550);
	      
	       
	        g.drawString("Age: "+age.getText().toString(), 100,560);

	        
	        g.drawString("Gender: "+gender.getSelectedItem().toString(), 230,560);
	        
	       
	       g.drawString("Occupation: "+ occupation.getText().toString(), 340,560);

	       
	        g.drawString("Address: "+address.getText().toString(), 100,570);

	       
	        g.drawString("Civil Status: "+status.getSelectedItem().toString(), 340,570);

	        
	        g.drawString("Medicine(s) ", 100,590);
	        
	        
	        g.drawString(medicine1.getSelectedItem().toString(), 100,600);
	        g.drawString(quantity1.getText().toString(), 180,600);
	        g.drawString(price1.getText().toString(), 280,600);
	        g.drawString(Total1.getText().toString(), 380,600);
	        
	        g.drawString(medicine2.getSelectedItem().toString(), 100,610);
	        g.drawString(quantity2.getText().toString(), 180,610);
	        g.drawString(price2.getText().toString(), 280,610);
	        g.drawString(Total2.getText().toString(), 380,610);
	        
	        g.drawString(medicine3.getSelectedItem().toString(), 100,620);
	        g.drawString(quantity3.getText().toString(), 180,620);
	        g.drawString(price3.getText().toString(), 280,620);
	        g.drawString(Total3.getText().toString(), 380,620);
	        
	        g.drawString(medicine4.getSelectedItem().toString(), 100,630);
	        g.drawString(quantity4.getText().toString(), 180,630);
	        g.drawString(price4.getText().toString(), 280,630);
	        g.drawString(Total4.getText().toString(), 380,630);
	        
	        g.drawString(medicine5.getSelectedItem().toString(), 100,640);
	        g.drawString(quantity5.getText().toString(), 180,640);
	        g.drawString(price5.getText().toString(), 280,640);
	        g.drawString(Total5.getText().toString(), 380,640);
	        
	        
	        g.drawString(medicine6.getSelectedItem().toString(), 100,650);
	        g.drawString(quantity6.getText().toString(), 180,650);
	        g.drawString(price6.getText().toString(), 280,650);
	        g.drawString(Total6.getText().toString(), 380,650);
	        
	        
	        g.drawString(medicine7.getSelectedItem().toString(), 100,660);
	        g.drawString(quantity7.getText().toString(), 180,660);
	        g.drawString(price7.getText().toString(), 280,660);
	        g.drawString(Total7.getText().toString(), 380,660);
	        
	        g.drawString(medicine8.getSelectedItem().toString(), 100,670);
	        g.drawString(quantity8.getText().toString(), 180,670);
	        g.drawString(price8.getText().toString(), 280,670);
	        g.drawString(Total8.getText().toString(), 380,670);
	        
	        g.drawString(medicine9.getSelectedItem().toString(), 100,680);
	        g.drawString(quantity9.getText().toString(), 180,680);
	        g.drawString(price9.getText().toString(), 280,680);
	        g.drawString(Total9.getText().toString(), 380,680);
	        
	        g.drawString(medicine10.getSelectedItem().toString(), 100,690);
	        g.drawString(quantity10.getText().toString(), 180,690);
	        g.drawString(price10.getText().toString(), 280,690);
	        g.drawString(Total10.getText().toString(), 380,690);
	        
	        g.drawString("Total Bill: "+TotalBill.getText().toString(), 100,700);
	        g.drawString("Cash: "+Cash.getText().toString(), 100,710);
	        g.drawString("Change: "+Change.getText().toString(), 100,720);
	        g.drawString("Balance: "+Balance.getText().toString(), 100,730);
	       
	       
	       
	        
	        
	        g.setFont(new Font("Tahoma",Font.ITALIC,10));
	        g.setColor(Color.BLACK);
	        //draw line separator
	        g.drawString("_______________________________________" +
	        		"________________________________________________", 70, 745);
	      
	       
	        g.setFont(new Font("Tahoma",Font.ITALIC,8));
	        g.drawString("Email Address: region1mc2003@yahoo.com", 100,760);
	        
	        g.setFont(new Font("Tahoma",Font.ITALIC,8));
	        g.drawString("Telephone Numbers: (075) 515-89-16 ; (075)515-89-18 ; (075)515-89-01", 100,770);

	        g.setFont(new Font("Tahoma",Font.ITALIC,8));
	        g.drawString("Fax: (075) 523-41-03", 100,780);
	       
	        
	        

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
		        	  
		        	sql = "SELECT * FROM pharmacy_patient_billing WHERE last_name LIKE "+"\""+ln.getText().toString()+"%\"";
		        	ResultSet rs = stmt.executeQuery(sql);
		        	table.setModel(DbUtils.resultSetToTableModel(rs));
		         
		    
			 }catch(Exception moriel){
				 
				 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }
			 
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
