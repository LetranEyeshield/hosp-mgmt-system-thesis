package Moriel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PharmacyBilling extends JFrame implements ActionListener, Printable{
	
	JLabel labels,title;
	
	
	JComboBox fields[];
	
	JTextField Quantity[];
	
	JTextField Price1,
			   Price2, 
			   Price3, 
			   Price4, 
			   Price5, 
			   Price6, 
			   Price7, 
			   Price8, 
			   Price9, 
			   Price10;
	
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
	
	JButton compute, print, clear, exit, patient_billing;
	
	
	//VARS PARA SA COMPUTATION LATER
	int quantity1,
		quantity2,
		quantity3,
		quantity4,
		quantity5,
		quantity6,
		quantity7, 
		quantity8,
		quantity9,
		quantity10;
	
	double price1, 
		   price2, 
		   price3, 
		   price4, 
		   price5,
		   price6,
		   price7,
		   price8,
		   price9,
		   price10;
	
	
	double totalPrice1, 
		   totalPrice2, 
		   totalPrice3, 
		   totalPrice4, 
		   totalPrice5, 
		   totalPrice6, 
		   totalPrice7, 
		   totalPrice8, 
		   totalPrice9, 
		   totalPrice10;
	
	
	JTextField TotalBill, cash, change;
	
	double totalBill, Cash, Change;
	
	JTable table;
	
	//VARS PARA SA PRINTING
	Graphics2D g2;
	Image logo;
	Font font1, font2, font3, font4, font5;
	
	
	public PharmacyBilling(){
		super("HOSPITAL MANAGEMENT SYSTEM");
		setLayout(null);
		
		setTitle();
		setLabels();
		setFields();
		setButtons();
		setLine();
		setBackground();
		
		setMedsCombo();
		
		
	}
	
	
public void setTitle(){
		
		title = new JLabel("                   " +
				"                      " +
				"           " +
				"               PHARMACY BILLING");
		title.setBounds(0,0,1040,25);
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

	JLabel background = new JLabel(new ImageIcon("res/pharmacy_billing.png"));
	 background.setBounds(0,0,1040,600);
	// background.setBackground(new Color(0,0,255));
	 background.setOpaque(true);
	 add(background);
	 
}


public void setLabels(){
	
	
	prepareLabels(" Medicine Name:",30,70,120,18);
	prepareLabels(" Quantity:",430,70,80,18);
	prepareLabels(" Price:",590,70,60,18);
	prepareLabels(" Total:",730,70,60,18);

	
	prepareLabels(" Medicine Name:",30,100,120,18);
	prepareLabels(" Quantity:",430,100,80,18);
	prepareLabels(" Price:",590,100,60,18);
	prepareLabels(" Total:",730,100,60,18);
	
	prepareLabels(" Medicine Name:",30,130,120,18);
	prepareLabels(" Quantity:",430,130,80,18);
	prepareLabels(" Price:",590,130,60,18);
	prepareLabels(" Total:",730,130,60,18);
	
	prepareLabels(" Medicine Name:",30,160,120,18);
	prepareLabels(" Quantity:",430,160,80,18);
	prepareLabels(" Price:",590,160,60,18);
	prepareLabels(" Total:",730,160,60,18);
	
	prepareLabels(" Medicine Name:",30,190,120,18);
	prepareLabels(" Quantity:",430,190,80,18);
	prepareLabels(" Price:",590,190,60,18);
	prepareLabels(" Total:",730,190,60,18);
	
	
	prepareLabels(" Medicine Name:",30,220,120,18);
	prepareLabels(" Quantity:",430,220,80,18);
	prepareLabels(" Price:",590,220,60,18);
	prepareLabels(" Total:",730,220,60,18);
	
	prepareLabels(" Medicine Name:",30,250,120,18);
	prepareLabels(" Quantity:",430,250,80,18);
	prepareLabels(" Price:",590,250,60,18);
	prepareLabels(" Total:",730,250,60,18);
	
	prepareLabels(" Medicine Name:",30,280,120,18);
	prepareLabels(" Quantity:",430,280,80,18);
	prepareLabels(" Price:",590,280,60,18);
	prepareLabels(" Total:",730,280,60,18);
	
	
	prepareLabels(" Medicine Name:",30,310,120,18);
	prepareLabels(" Quantity:",430,310,80,18);
	prepareLabels(" Price:",590,310,60,18);
	prepareLabels(" Total:",730,310,60,18);
	
	
	prepareLabels(" Medicine Name:",30,340,120,18);
	prepareLabels(" Quantity:",430,340,80,18);
	prepareLabels(" Price:",590,340,60,18);
	prepareLabels(" Total:",730,340,60,18);
	
	
	prepareLabels(" Total Bill:",180,400,80,18);
	
	prepareLabels(" Cash:",400,400,50,18);
	
	prepareLabels(" Change:",560,400,60,18);
	
}


public void setFields(){
	
	fields = new JComboBox[10];
	
	Quantity = new JTextField[10];
	
	fields[0] = new JComboBox();
	fields[0].setBounds(160,70,250,18);
	fields[0].addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent Satella) {
			// TODO Auto-generated method stub
			getMed1();
		}
		
	});
	add(fields[0]);
	
	Quantity[0] = new JTextField("0");
	Quantity[0].setBounds(520,70,50,18);
	add(Quantity[0]);
	
	
	Price1 = new JTextField("0");
	Price1.setBounds(660,70,50,18);
	Price1.setEditable(false);
	add(Price1);
	
	Total1 = new JTextField();
	Total1.setBounds(800,70,70,18);
	Total1.setEditable(false);
	add(Total1);
	
	
	fields[1] = new JComboBox();
	fields[1].setBounds(160,100,250,18);
	fields[1].addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent Satella) {
			// TODO Auto-generated method stub
			getMed2();
		}
		
	});
	add(fields[1]);
	
	Quantity[1] = new JTextField("0");
	Quantity[1].setBounds(520,100,50,18);
	add(Quantity[1]);
	
	
	Price2 = new JTextField("0");
	Price2.setBounds(660,100,50,18);
	Price2.setEditable(false);
	add(Price2);
	
	Total2 = new JTextField();
	Total2.setBounds(800,100,70,18);
	Total2.setEditable(false);
	add(Total2);
	
	
	fields[2] = new JComboBox();
	fields[2].setBounds(160,130,250,18);
	fields[2].addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent Satella) {
			// TODO Auto-generated method stub
			getMed3();
		}
		
	});
	add(fields[2]);
	
	Quantity[2] = new JTextField("0");
	Quantity[2].setBounds(520,130,50,18);
	add(Quantity[2]);
	
	
	Price3 = new JTextField("0");
	Price3.setBounds(660,130,50,18);
	Price3.setEditable(false);
	add(Price3);
	
	Total3 = new JTextField();
	Total3.setBounds(800,130,70,18);
	Total3.setEditable(false);
	add(Total3);
	
	
	fields[3] = new JComboBox();
	fields[3].setBounds(160,160,250,18);
	fields[3].addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent Satella) {
			// TODO Auto-generated method stub
			getMed4();
		}
		
	});
	add(fields[3]);
	
	Quantity[3] = new JTextField("0");
	Quantity[3].setBounds(520,160,50,18);
	add(Quantity[3]);
	
	
	Price4 = new JTextField("0");
	Price4.setBounds(660,160,50,18);
	Price4.setEditable(false);
	add(Price4);
	
	Total4 = new JTextField();
	Total4.setBounds(800,160,70,18);
	Total4.setEditable(false);
	add(Total4);
	
	
	fields[4] = new JComboBox();
	fields[4].setBounds(160,190,250,18);
	fields[4].addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent Satella) {
			// TODO Auto-generated method stub
			getMed5();
		}
		
	});
	add(fields[4]);
	
	Quantity[4] = new JTextField("0");
	Quantity[4].setBounds(520,190,50,18);
	add(Quantity[4]);
	
	
	Price5 = new JTextField("0");
	Price5.setBounds(660,190,50,18);
	Price5.setEditable(false);
	add(Price5);
	
	Total5 = new JTextField();
	Total5.setBounds(800,190,70,18);
	Total5.setEditable(false);
	add(Total5);
	
	
	fields[5] = new JComboBox();
	fields[5].setBounds(160,220,250,18);
	fields[5].addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent Satella) {
			// TODO Auto-generated method stub
			getMed6();
		}
		
	});
	add(fields[5]);
	
	Quantity[5] = new JTextField("0");
	Quantity[5].setBounds(520,220,50,18);
	add(Quantity[5]);
	
	
	Price6 = new JTextField("0");
	Price6.setBounds(660,220,50,18);
	Price6.setEditable(false);
	add(Price6);
	
	Total6 = new JTextField();
	Total6.setBounds(800,220,70,18);
	Total6.setEditable(false);
	add(Total6);
	
	
	fields[6] = new JComboBox();
	fields[6].setBounds(160,250,250,18);
	fields[6].addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent Satella) {
			// TODO Auto-generated method stub
			getMed7();
		}
		
	});
	add(fields[6]);
	
	Quantity[6] = new JTextField("0");
	Quantity[6].setBounds(520,250,50,18);
	add(Quantity[6]);
	

	Price7 = new JTextField("0");
	Price7.setBounds(660,250,50,18);
	Price7.setEditable(false);
	add(Price7);
	
	Total7 = new JTextField();
	Total7.setBounds(800,250,70,18);
	Total7.setEditable(false);
	add(Total7);
	
	
	fields[7] = new JComboBox();
	fields[7].setBounds(160,280,250,18);
	fields[7].addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent Satella) {
			// TODO Auto-generated method stub
			getMed8();
		}
		
	});
	add(fields[7]);
	
	Quantity[7] = new JTextField("0");
	Quantity[7].setBounds(520,280,50,18);
	add(Quantity[7]);
	
	
	Price8 = new JTextField("0");
	Price8.setBounds(660,280,50,18);
	Price8.setEditable(false);
	add(Price8);
	
	Total8 = new JTextField();
	Total8.setBounds(800,280,70,18);
	Total8.setEditable(false);
	add(Total8);
	
	
	fields[8] = new JComboBox();
	fields[8].setBounds(160,310,250,18);
	fields[8].addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent Satella) {
			// TODO Auto-generated method stub
			getMed9();
		}
		
	});
	add(fields[8]);
	
	Quantity[8] = new JTextField("0");
	Quantity[8].setBounds(520,310,50,18);
	add(Quantity[8]);
	
	
	Price9 = new JTextField("0");
	Price9.setBounds(660,310,50,18);
	Price9.setEditable(false);
	add(Price9);
	
	Total9 = new JTextField();
	Total9.setBounds(800,310,70,18);
	Total9.setEditable(false);
	add(Total9);
	
	
	fields[9] = new JComboBox();
	fields[9].setBounds(160,340,250,18);
	fields[9].addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent Satella) {
			// TODO Auto-generated method stub
			getMed10();
		}
		
	});
	add(fields[9]);
	
	Quantity[9] = new JTextField("0");
	Quantity[9].setBounds(520,340,50,18);
	add(Quantity[9]);
	
	
	Price10 = new JTextField("0");
	Price10.setBounds(660,340,50,18);
	Price10.setEditable(false);
	add(Price10);
	
	Total10 = new JTextField();
	Total10.setBounds(800,340,70,18);
	Total10.setEditable(false);
	add(Total10);
	
	
	TotalBill = new JTextField();
	TotalBill.setBounds(270,400,100,18);
	TotalBill.setEditable(false);
	add(TotalBill);
	
	cash = new JTextField("0");
	cash.setBounds(460,400,70,18);
	add(cash);
	
	
	change = new JTextField();
	change.setBounds(630,400,100,18);
	change.setEditable(false);
	add(change);
				
	
}


public void Compute(){
	
	
	quantity1 = Integer.parseInt(Quantity[0].getText().toString());
	quantity2 = Integer.parseInt(Quantity[1].getText().toString());
	quantity3 = Integer.parseInt(Quantity[2].getText().toString());
	quantity4 = Integer.parseInt(Quantity[3].getText().toString());
	quantity5 = Integer.parseInt(Quantity[4].getText().toString());
	quantity6 = Integer.parseInt(Quantity[5].getText().toString());
	quantity7 = Integer.parseInt(Quantity[6].getText().toString());
	quantity8 = Integer.parseInt(Quantity[7].getText().toString());
	quantity9 = Integer.parseInt(Quantity[8].getText().toString());
	quantity10 = Integer.parseInt(Quantity[9].getText().toString());
	
	
	price1 = Double.parseDouble(Price1.getText().toString());
	price2 = Double.parseDouble(Price2.getText().toString());
	price3 = Double.parseDouble(Price3.getText().toString());
	price4 = Double.parseDouble(Price4.getText().toString());
	price5 = Double.parseDouble(Price5.getText().toString());
	price6 = Double.parseDouble(Price6.getText().toString());
	price7 = Double.parseDouble(Price7.getText().toString());
	price8 = Double.parseDouble(Price8.getText().toString());
	price9 = Double.parseDouble(Price9.getText().toString());
	price10 = Double.parseDouble(Price10.getText().toString());
	
	
	
	totalPrice1 = quantity1 * price1;
	totalPrice2 = quantity2 * price2;
	totalPrice3 = quantity3 * price3;
	totalPrice4 = quantity4 * price4;
	totalPrice5 = quantity5 * price5;
	totalPrice6 = quantity6 * price6;
	totalPrice7 = quantity7 * price7;
	totalPrice8 = quantity8 * price8;
	totalPrice9 = quantity9 * price9;
	totalPrice10 = quantity10 * price10;
	
	
	Cash = Double.parseDouble(cash.getText().toString());
	
	Total1.setText(" P"+totalPrice1);
	Total2.setText(" P"+totalPrice2);
	Total3.setText(" P"+totalPrice3);
	Total4.setText(" P"+totalPrice4);
	Total5.setText(" P"+totalPrice5);
	Total6.setText(" P"+totalPrice6);
	Total7.setText(" P"+totalPrice7);
	Total8.setText(" P"+totalPrice8);
	Total9.setText(" P"+totalPrice9);
	Total10.setText(" P"+totalPrice10);
	
	
	totalBill = totalPrice1 +
				totalPrice2 +
				totalPrice3 + 
				totalPrice4 + 
				totalPrice5 + 
				totalPrice6 + 
				totalPrice7 + 
				totalPrice8 +
				totalPrice9 +
				totalPrice10;
	
	
	Change = Cash-totalBill;
	
	
	TotalBill.setText(" P "+totalBill);
	
	if(totalBill>Cash){
		
		change.setText(" P ");
		
	}else{
	
	change.setText(" P "+Change);
	
	
	}
	
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
				 
				 String	meds = rs.getString("brand_name");
				 
				 	
				 	fields[0].addItem(meds);
				 	fields[1].addItem(meds);
				 	fields[2].addItem(meds);
				 	fields[3].addItem(meds);
				 	fields[4].addItem(meds);
				 	fields[5].addItem(meds);
				 	fields[6].addItem(meds);
				 	fields[7].addItem(meds);
				 	fields[8].addItem(meds);
				 	fields[9].addItem(meds);
				 	
				 	

				
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
	compute.setBounds(180,480,110,25);
	compute.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			
			Compute();
			
		}
		
	});
	add(compute);
	
	
	
	print = new JButton("PRINT");
	print.setBounds(310,480,110,25);
	print.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			
			printMe();
		}
		
	});
	add(print);
	
	
	clear = new JButton("CLEAR FIELDS");
	clear.setBounds(440,480,130,25);
	clear.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			
			ClearFields();
		}
		
	});
	add(clear);
	
	
	exit = new JButton("EXIT");
	exit.setBounds(590,480,90,25);
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
	line.setBounds(0,380,1040,2);
	line.setOpaque(true);
	line.setBackground(Color.RED);
	add(line);
}



//METHODS BELOW ALIKE THIS ARE FOR GETTING THE PRICES KUNG HIT NILA UNG COMBO MEDS
public void getMed1(){
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+fields[0].getSelectedItem().toString()+"\"";
	
			
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
						 
						 
						 Price1.setText(rs.getString("price"));
						

						
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
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+fields[1].getSelectedItem().toString()+"\"";
	
			
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
						 
						 
						 Price2.setText(rs.getString("price"));
						

						
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
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+fields[2].getSelectedItem().toString()+"\"";
	
			
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
						 
						 
						 Price3.setText(rs.getString("price"));
						

						
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
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+fields[3].getSelectedItem().toString()+"\"";
	
			
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
						 
						 
						 Price4.setText(rs.getString("price"));
						

						
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
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+fields[4].getSelectedItem().toString()+"\"";
	
			
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
						 
						 
						 Price5.setText(rs.getString("price"));
						

						
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
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+fields[5].getSelectedItem().toString()+"\"";
	
			
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
						 
						 
						 Price6.setText(rs.getString("price"));
						

						
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
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+fields[6].getSelectedItem().toString()+"\"";
	
			
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
						 
						 
						 Price7.setText(rs.getString("price"));
						

						
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
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+fields[7].getSelectedItem().toString()+"\"";
	
			
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
						 
						 
						 Price8.setText(rs.getString("price"));
						

						
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
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+fields[8].getSelectedItem().toString()+"\"";
	
			
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
						 
						 
						 Price9.setText(rs.getString("price"));
						

						
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
	
	
	String query = "SELECT * FROM pharmacy WHERE brand_name="+"\""+fields[9].getSelectedItem().toString()+"\"";
	
			
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
						 
						 
						 Price10.setText(rs.getString("price"));
						

						
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
		








//ITO UNG PAG DRAWING NG MGA STUFFS
//MADUGONG LABANAN TALAGA TO! Moriel!!!

//NOTE: AYUSIN NYO NA LANG ITO MGA SUSUNOD NA RESEARCHERS
@Override
public int print(Graphics g, PageFormat pf, int page)
		throws PrinterException {
	// TODO Auto-generated method stub
	
	 logo = Toolkit.getDefaultToolkit().getImage("res/logo.jpg");
	 
	 //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
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
      g.drawString("Pharmacy Billing", 230,135);
      
      
      g.setFont(new Font("Tahoma",Font.ITALIC,7));
      g.setColor(Color.BLACK);
      g.drawString(dateFormat.format(date), 420,135);
     // g.drawString(date.toString(), 330,135);
      
      
    
      g.setFont(new Font("Tahoma",Font.PLAIN,10));
      g.setColor(Color.BLACK);
      g.drawString("Medicine Name: ", 80,160);
      g.drawString("Quantity: ", 240,160);
      g.drawString("Price: ", 320,160);
      g.drawString("Total: ", 410,160);

     
      g.setFont(new Font("Tahoma",Font.PLAIN,8));
      g.setColor(Color.BLACK);
      
      g.drawString(fields[0].getSelectedItem().toString(), 80,190);
      g.drawString(Quantity[0].getText().toString(), 20,190);
      g.drawString(Price1.getText().toString(), 330,190);
      g.drawString(Total1.getText().toString(), 410,190);
     
      
      g.drawString(fields[1].getSelectedItem().toString(), 80,200);
      g.drawString(Quantity[1].getText().toString(), 250,200);
      g.drawString(Price2.getText().toString(), 330,200);
      g.drawString(Total2.getText().toString(), 410,200);
      
      
      g.drawString(fields[2].getSelectedItem().toString(), 80,210);
      g.drawString(Quantity[2].getText().toString(), 250,210);
      g.drawString(Price3.getText().toString(), 330,210);
      g.drawString(Total3.getText().toString(), 410,210);
      
      
      g.drawString(fields[3].getSelectedItem().toString(), 80,220);
      g.drawString(Quantity[3].getText().toString(), 250,220);
      g.drawString(Price4.getText().toString(), 330,220);
      g.drawString(Total4.getText().toString(), 410,220);
      
      
      g.drawString(fields[4].getSelectedItem().toString(), 80,230);
      g.drawString(Quantity[4].getText().toString(), 250,230);
      g.drawString(Price5.getText().toString(), 330,230);
      g.drawString(Total5.getText().toString(), 410,230);
      
      
      g.drawString(fields[5].getSelectedItem().toString(), 80,240);
      g.drawString(Quantity[5].getText().toString(), 250,240);
      g.drawString(Price6.getText().toString(), 330,240);
      g.drawString(Total6.getText().toString(), 410,240);
      
      
      g.drawString(fields[6].getSelectedItem().toString(), 80,250);
      g.drawString(Quantity[6].getText().toString(), 250,250);
      g.drawString(Price7.getText().toString(), 330,250);
      g.drawString(Total7.getText().toString(), 410,250);
      
      
      g.drawString(fields[7].getSelectedItem().toString(), 80,260);
      g.drawString(Quantity[7].getText().toString(), 250,260);
      g.drawString(Price8.getText().toString(), 330,260);
      g.drawString(Total8.getText().toString(), 410,260);
      
      
      g.drawString(fields[8].getSelectedItem().toString(), 80,270);
      g.drawString(Quantity[8].getText().toString(), 250,270);
      g.drawString(Price9.getText().toString(), 330,270);
      g.drawString(Total9.getText().toString(), 410,270);
      
      
      g.drawString(fields[9].getSelectedItem().toString(), 80,280);
      g.drawString(Quantity[9].getText().toString(), 250,280);
      g.drawString(Price10.getText().toString(), 330,280);
      g.drawString(Total10.getText().toString(), 410,280);
      
      
      g.drawString("__________", 410,285);
      
      g.drawString("Total Bill"+TotalBill.getText().toString(), 380,300);
      
     
      
      
      
      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.setColor(Color.BLACK);
      //draw line separator
      g.drawString("_______________________________________" +
      		"________________________________________________", 70, 320);
    

     
      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.drawString("Email Address: region1mc2003@yahoo.com", 100,335);
      
      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.drawString("Telephone Numbers: (075) 515-89-16 ; (075)515-89-18 ; (075)515-89-01", 100,345);

      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.drawString("Fax: (075) 523-41-03", 100,355);

    
      //LINE SEPARATOR FOR CUTTING NANDE KUSO
      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.setColor(Color.BLACK);
      //draw line separator
      g.drawString("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ " +
      		"_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ " +
      		"_ _ _ _ _ _ _ _ _ _ _ _", 70, 370);
    
      
      //2ND COPY
      
      
      g.drawImage(logo, 270, 380, 60, 60, this);

      g.setFont(new Font("Tahoma",Font.BOLD,20));
      g.setColor(Color.RED);
      g.drawString(" HOSPITAL MANAGEMENT SYSTEM", 140, 470);
     
      
      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.setColor(Color.BLACK);
      g.drawString("Poblacion, Manaoag, Pangasinan, Philippines", 180,480);

      //draw line separator
      g.drawString("_______________________________________" +
      		"________________________________________________", 70, 490);
      
      g.setFont(new Font("Tahoma",Font.ITALIC,10));
      g.setColor(Color.BLACK);
      g.drawString("Pharmacy Billing", 230,515);
      
      
      g.setFont(new Font("Tahoma",Font.ITALIC,7));
      g.setColor(Color.BLACK);
      g.drawString(dateFormat.format(date), 420,515);
     // g.drawString(date.toString(), 330,135);
      
      
    
      g.setFont(new Font("Tahoma",Font.PLAIN,10));
      g.setColor(Color.BLACK);
      g.drawString("Medicine Name: ", 80,540);
      g.drawString("Quantity: ", 240,540);
      g.drawString("Price: ", 320,540);
      g.drawString("Total: ", 410,540);

     
      g.setFont(new Font("Tahoma",Font.PLAIN,8));
      g.setColor(Color.BLACK);
      
      g.drawString(fields[0].getSelectedItem().toString(), 80,560);
      g.drawString(Quantity[0].getText().toString(), 250,560);
      g.drawString(Price1.getText().toString(), 330,560);
      g.drawString(Total1.getText().toString(), 410,560);
     
      
      g.drawString(fields[1].getSelectedItem().toString(), 80,570);
      g.drawString(Quantity[1].getText().toString(), 250,570);
      g.drawString(Price2.getText().toString(), 330,570);
      g.drawString(Total2.getText().toString(), 410,570);
      
      
      g.drawString(fields[2].getSelectedItem().toString(), 80,580);
      g.drawString(Quantity[2].getText().toString(), 250,580);
      g.drawString(Price3.getText().toString(), 330,580);
      g.drawString(Total3.getText().toString(), 410,580);
      
      
      g.drawString(fields[3].getSelectedItem().toString(), 80,590);
      g.drawString(Quantity[3].getText().toString(), 250,590);
      g.drawString(Price4.getText().toString(), 330,590);
      g.drawString(Total4.getText().toString(), 410,590);
      
      
      g.drawString(fields[4].getSelectedItem().toString(), 80,600);
      g.drawString(Quantity[4].getText().toString(), 250,600);
      g.drawString(Price5.getText().toString(), 330,600);
      g.drawString(Total5.getText().toString(), 410,600);
      
      
      g.drawString(fields[5].getSelectedItem().toString(), 80,610);
      g.drawString(Quantity[5].getText().toString(), 250,610);
      g.drawString(Price6.getText().toString(), 330,610);
      g.drawString(Total6.getText().toString(), 410,610);
      
      
      g.drawString(fields[6].getSelectedItem().toString(), 80,620);
      g.drawString(Quantity[6].getText().toString(), 250,620);
      g.drawString(Price7.getText().toString(), 330,620);
      g.drawString(Total7.getText().toString(), 410,620);
      
      
      g.drawString(fields[7].getSelectedItem().toString(), 80,630);
      g.drawString(Quantity[7].getText().toString(), 250,630);
      g.drawString(Price8.getText().toString(), 330,630);
      g.drawString(Total8.getText().toString(), 410,630);
      
      
      g.drawString(fields[8].getSelectedItem().toString(), 80,640);
      g.drawString(Quantity[8].getText().toString(), 250,640);
      g.drawString(Price9.getText().toString(), 330,640);
      g.drawString(Total9.getText().toString(), 410,640);
      
      
      g.drawString(fields[9].getSelectedItem().toString(), 80,650);
      g.drawString(Quantity[9].getText().toString(), 250,650);
      g.drawString(Price10.getText().toString(), 330,650);
      g.drawString(Total10.getText().toString(), 410,650);
      
      
      g.drawString("__________", 410,655);
    
      g.drawString("Total Bill"+TotalBill.getText().toString(), 380,670);
      
     
      
      
      
      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.setColor(Color.BLACK);
      //draw line separator
      g.drawString("_______________________________________" +
      		"________________________________________________", 70, 690);
    

     
      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.drawString("Email Address: sampleEmail@gmail.com", 100,705);
      
      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.drawString("Telephone Numbers: (075)  123-45-67; 0000-0000-000", 100,720);

      g.setFont(new Font("Tahoma",Font.ITALIC,8));
      g.drawString("Fax: (075) 523-41-03", 100,735);
      
     

    


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




public void ClearFields(){
	
	
	fields[0].setSelectedItem("");
	fields[1].setSelectedItem("");
	fields[2].setSelectedItem("");
	fields[3].setSelectedItem("");
	fields[4].setSelectedItem("");
	fields[5].setSelectedItem("");
	fields[6].setSelectedItem("");
	fields[7].setSelectedItem("");
	fields[8].setSelectedItem("");
	fields[9].setSelectedItem("");
	
	
	Quantity[0].setText("0");
	Quantity[1].setText("0");
	Quantity[2].setText("0");
	Quantity[3].setText("0");
	Quantity[4].setText("0");
	Quantity[5].setText("0");
	Quantity[6].setText("0");
	Quantity[7].setText("0");
	Quantity[8].setText("0");
	Quantity[9].setText("0");
	
	
	Price1.setText("0");
	Price2.setText("0");
	Price3.setText("0");
	Price4.setText("0");
	Price5.setText("0");
	Price6.setText("0");
	Price7.setText("0");
	Price8.setText("0");
	Price9.setText("0");
	Price10.setText("0");
	
	
	Total1.setText("");
	Total2.setText("");
	Total3.setText("");
	Total4.setText("");
	Total5.setText("");
	Total6.setText("");
	Total7.setText("");
	Total8.setText("");
	Total9.setText("");
	Total10.setText("");
	
	
	TotalBill.setText("");
	cash.setText("0");
	change.setText("");
}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}


//METHOD PARA SA EXIT BUTTON
	public void CloseMe(){
		this.dispose();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
