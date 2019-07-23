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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import Moriel.Ward.Moriel;

import net.proteanit.sql.DbUtils;

public class Pharmacy extends JFrame implements ActionListener, MouseListener, KeyListener{
	
	JLabel labels,title;
	
	
	JTextField medsCode, companyName, brandName, genericName, quantity, price;
	JButton add, clear, edit, exit, billing, patientBill;
	
	JTable table;

	DefaultTableModel model;
	
	JTextArea desc;
	JScrollPane scroll;
	
	JComboBox combo;
		
	
	//PREPARE THE CLASS PARA SA BRAND NAME SEARCHING
	Moriel Kim = new Moriel();
	
	//PREPARE THE ANOTHER CLASS PARA SA GENERIC NAME SEARCHING
	MorielKim Manaay = new MorielKim();
	
	public Pharmacy(){
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
		
		title = new JLabel("                          " +
				"                           "
				+ "              PHARMACY");
		title.setBounds(0,0,1000,25);
		title.setOpaque(true);
		title.setBackground(Color.blue);
		title.setFont(new Font("OLD ENGLISH MT",Font.BOLD,20));
		add(title);
	}


public void prepareLabels(String jammi, int xpos, int ypos, int width, int height){
	
	labels = new JLabel(jammi);
	labels.setBounds(xpos,ypos,width,height);
	labels.setOpaque(true);
	labels.setBackground(Color.LIGHT_GRAY);
	labels.setFont(new Font("Tahoma",Font.PLAIN,14));
	add(labels);
	
}

public void setLabels(){
	
	prepareLabels(" Medicine Code:",30,80,110,18);
	prepareLabels(" Company Name:",30,110,110,18);
	prepareLabels(" Brand Name:",30,140,110,18);
	prepareLabels(" Generic Name:",30,170,110,18);
	prepareLabels(" Quantity:",30,200,110,18);
	prepareLabels(" Price:",30,230,110,18);
	prepareLabels(" Description:",30,260,110,18);
	
	
	
}


public void setPanels(){
	
	JLabel panelLabel1 = new JLabel("  Medicine Information ");
	panelLabel1.setBounds(20,50,150,20);
	panelLabel1.setOpaque(true);
	panelLabel1.setBackground(Color.CYAN);
	add(panelLabel1);
	
	JLabel panel1 = new JLabel();
	panel1.setBounds(20,70,390,250);
	panel1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel1);
	
	JLabel panel2 = new JLabel();
	panel2.setBounds(420,70,560,250);
	panel2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel2);
	
}


public void setBackground(){

	JLabel background = new JLabel(new ImageIcon("res/pharmacy.png"));
	 background.setBounds(0,0,1000,550);
	 background.setBackground(new Color(0,0,255));
	// background.setBackground(Color.LIGHT_GRAY);
	 background.setOpaque(true);
	 add(background);
	 
}




public void setFields(){
	
	
	medsCode = new JTextField();
	medsCode.setBounds(150,80,250,18);
	medsCode.addKeyListener(this);
	add(medsCode);
	
	companyName = new JTextField();
	companyName.setBounds(150,110,250,18);
	add(companyName);
	
	brandName = new JTextField();
	brandName .setBounds(150,140,250,18);
	brandName.addKeyListener(Kim);
	add(brandName );
	
	genericName = new JTextField();
	genericName.setBounds(150,170,250,18);
	genericName.addKeyListener(Manaay);
	add(genericName);
			
	quantity = new JTextField();
	quantity.setBounds(150,200,250,18);
	add(quantity);
	
	price = new JTextField();
	price.setBounds(150,230,120,18);
	add(price);
			
	desc=new JTextArea();
	//Remarks.setBounds(100,505,250,240);
	desc.setToolTipText("Please use 'Enter Key' to use line break!");
	desc.setLineWrap(true);
	desc.setWrapStyleWord(true);
	//add(Remarks);
	scroll=new JScrollPane(desc);
	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	//scrollRemarks.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	scroll.setBounds(150,260,250,50);
	add(scroll);
			
	
}


public void setButtons(){
	

	
	add = new JButton("ADD");
	add.setBounds(160,430,90,25);
	add.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			InsertSQL();
		}
		
	});
	add(add);
	
	
	edit = new JButton("EDIT");
	edit.setBounds(260,430,70,25);
	edit.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			EditSQL();
			
		}
		
	});
	add(edit);
	
	
	billing = new JButton("BILLING");
	billing.setBounds(340,430,90,25);
	billing.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			
			PharmacyBilling PB = new PharmacyBilling();
			PB.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			PB.setSize(1040,600);
			PB.setVisible(true);
			PB.setResizable(false);
		
		}
		
	});
	add(billing);
	
	
	patientBill = new JButton("PATIENT BILLING");
	patientBill.setBounds(440,430,130,25);
	patientBill.addActionListener(new ActionListener(){

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
	add(patientBill);
	
	
	
	clear = new JButton("CLEAR FIELDS");
	clear.setBounds(580,430,120,25);
	clear.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			ClearFields();
			
		}
		
	});
	add(clear);
	
	
	exit = new JButton("EXIT");
	exit.setBounds(710,430,70,25);
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
	line.setBounds(0,390,1000,1);
	line.setOpaque(true
			);
	line.setBackground(Color.RED);
	add(line);
}



//CLEAR THE FIELDS
public void ClearFields(){
	
	//medsCode.setText("");
 	
 	companyName.setText("");
 	
 	brandName.setText("");
 	
 	genericName.setText("");
 	
 	quantity.setText("");

    price.setText("");
    
    desc.setText("");

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

//set the sql query
String query = "INSERT INTO pharmacy (medicine_code, company_name, brand_name, generic_name, quantity, price, description)" + "values(?,?,?,?,?,?,?)";


java.sql.Statement stmt = conn.createStatement();


	 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);

	 
	 	ps.setString(1, medsCode.getText().toString());

	    ps.setString(2, companyName.getText().toString()); 

	    ps.setString(3, brandName.getText().toString()); 

	    ps.setString(4, genericName.getText().toString()); 
	  
	    ps.setString(5, quantity.getText().toString());

	    ps.setString(6, price.getText().toString()); 
	    
	    ps.setString(7, desc.getText().toString()); 

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
   	  
    sql = "SELECT medicine_code, company_name, brand_name, generic_name, quantity, price, description FROM pharmacy";
    ResultSet rs = stmt.executeQuery(sql);
		
		 table.setModel(DbUtils.resultSetToTableModel(rs));

}catch (Exception moriel){

		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

}




} 




//METHOD FOR SEARCHING ACCOUNT BAKAYAROU!
public void SearchSQL(){
	  
	ClearFields();
	
	String query = "SELECT medicine_code, company_name, brand_name, generic_name, quantity, price, description FROM pharmacy WHERE medicine_code="+"\""+medsCode.getText().toString()+"\"";

	
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

				 	medsCode.setText(rs.getString("medicine_code"));
				 	
				 	companyName.setText(rs.getString("company_name"));
				 	
				 	brandName.setText(rs.getString("brand_name"));
				 	
				 	genericName.setText(rs.getString("generic_name"));
				 	
				 	quantity.setText(rs.getString("quantity"));

				    price.setText(rs.getString("price"));
				    
				    desc.setText(rs.getString("description"));

				
		counter++;
			 }
			 

			 if(counter<1){
				 
				 //JOptionPane.showMessageDialog(null,"Medicine Code Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }

		conn.close();

		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 

			} 

} 



//METHOD PARA SA TABLE ROW CLICKING
public void tableClickSearch(){
	  
	
	String query = "SELECT medicine_code, company_name, brand_name, generic_name, quantity, price, description FROM pharmacy WHERE medicine_code="+"\""+table.getValueAt(table.getSelectedRow(), 0)+"\"";

	
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

				 	medsCode.setText(rs.getString("medicine_code"));
				 	
				 	companyName.setText(rs.getString("company_name"));
				 	
				 	brandName.setText(rs.getString("brand_name"));
				 	
				 	genericName.setText(rs.getString("generic_name"));
				 	
				 	quantity.setText(rs.getString("quantity"));

				    price.setText(rs.getString("price"));
				    
				    desc.setText(rs.getString("description"));

				
		counter++;
			 }
			 

			 if(counter<1){
				 
				 JOptionPane.showMessageDialog(null,"Medicine Code Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
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
			String query = "UPDATE pharmacy SET medicine_code=?,company_name=?,brand_name=?,generic_name=?,quantity=?,price=?,description=? WHERE medicine_code = "+"\""+medsCode.getText().toString()+"\"";

			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			
			
			ps.setString(1, medsCode.getText().toString());

		    ps.setString(2, companyName.getText().toString()); 

		    ps.setString(3, brandName.getText().toString()); 

		    ps.setString(4, genericName.getText().toString()); 
		  
		    ps.setString(5, quantity.getText().toString());

		    ps.setString(6, price.getText().toString()); 
		    
		    ps.setString(7, desc.getText().toString()); 
			
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
	   	  
	    sql = "SELECT medicine_code, company_name, brand_name, generic_name, quantity, price, description FROM pharmacy";
	    ResultSet rs = stmt.executeQuery(sql);
			
			 table.setModel(DbUtils.resultSetToTableModel(rs));

	}catch (Exception moriel){

			 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

	}
	  
	
} 


//METHOD PARA SA EXIT BUTTON
public void CloseMe(){
	this.dispose();
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
        	  
         sql = "SELECT medicine_code, company_name, brand_name, generic_name, quantity, price, description FROM pharmacy";
         ResultSet rs = stmt.executeQuery(sql);
         table = new JTable(model);
   		 JScrollPane scrollPane = new JScrollPane(table);
   		 table.setPreferredScrollableViewportSize(new Dimension(500, 70));
   		 table.setFillsViewportHeight(true);
   		 table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
   		 table.setBackground(Color.LIGHT_GRAY);
   		 scrollPane.setBounds(430,80,540,230);
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
        	  
        	sql = "SELECT medicine_code, company_name, brand_name, generic_name, quantity, price, description FROM pharmacy WHERE medicine_code LIKE "+"\""+medsCode.getText().toString()+"%\"";
        	ResultSet rs = stmt.executeQuery(sql);
        	table.setModel(DbUtils.resultSetToTableModel(rs));
         
    
	 }catch(Exception moriel){
		 
		 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
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


	
	
	
	//NEW CLASS PARA SA BRAND NAME SEARCHING
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
		        	  
		        	sql = "SELECT medicine_code, company_name, brand_name, generic_name, quantity, price, description FROM pharmacy WHERE brand_name LIKE "+"\""+brandName.getText().toString()+"%\"";
		        	ResultSet rs = stmt.executeQuery(sql);
		        	table.setModel(DbUtils.resultSetToTableModel(rs));
		         
		    
			 }catch(Exception moriel){
				 
				 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }
			 
			
		}
	}
	
	
	
	//ANOTHER CLASS PARA SA NAMAN SA GENERIC NAME SEARCHING
		public class MorielKim implements KeyListener{
						

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
			        	  
			        	sql = "SELECT medicine_code, company_name, brand_name, generic_name, quantity, price, description FROM pharmacy WHERE generic_name LIKE "+"\""+genericName.getText().toString()+"%\"";
			        	ResultSet rs = stmt.executeQuery(sql);
			        	table.setModel(DbUtils.resultSetToTableModel(rs));
			         
			    
				 }catch(Exception moriel){
					 
					 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
					 
				 }
				 
				
			}
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
