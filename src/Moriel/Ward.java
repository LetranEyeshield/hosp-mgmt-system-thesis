package Moriel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import Moriel.Doctor.Moriel;

import net.proteanit.sql.DbUtils;

public class Ward extends JFrame implements ActionListener, MouseListener, KeyListener{
	
	JLabel labels,title, ward_count;
	
	String T[] = {"", "Private", "Pay Ward", "Charity Ward"};
	JComboBox type;
	
	String A[] = {"", "Available", "Not Available"};
	JComboBox availability;
	
	
	//CAD MEANS CHECK AVAILABLE WARD BAKAYAROU!
	String CAW[] = {"", "Private", "Pay Ward", "Charity Ward"};
	JComboBox caw;
	
	
	JTextField wardID, wardNo, bed_no, numBeds, docEx, price;
	JButton add, clear, edit, exit;
	
	JTable table;
	DefaultTableModel model;
	
	JTextArea desc;
	JScrollPane scroll;
	
	
	//VARS PARA SA PRINTING
	Graphics2D g2;
	Image logo;
	Font font1, font2, font3, font4, font5;
	
	
	//COLORS PARA SA CHECKING INPUTS
	Color inputFailed = Color.RED;
	Color changeInput = Color.WHITE;
	Color inputPass = Color.WHITE;
	Color returnInput = Color.BLACK;
	
	

	//PREPARE THE CLASS PARA SA ROOM NUMBER SEARCHING DEPENDE SA WARD TYPE
	Moriel Kim = new Moriel();
	
	
	
	public Ward(){
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
		getWardCount();
		
		
	}
	
	
public void setTitle(){
		
		title = new JLabel("                                  " +
				"                              "
				+ "              WARDS/ROOMS");
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


public void setLabels(){
	
	prepareLabels(" Ward ID:",30,80,150,18);
	prepareLabels(" Type:",30,110,150,18);
	prepareLabels(" Ward/Room No:",30,140,150,18);
	prepareLabels(" Bed No:",30,170,150,18);
	prepareLabels(" Name/Number Of Beds:",30,200,150,18);
	prepareLabels(" Doctor Experties:",30,230,150,18);
	prepareLabels(" Description:",30,260,150,18);
	prepareLabels(" Price Per Day:",30,320,150,18);
	prepareLabels(" Availability:",30,350,150,18);
	
	
	// label para  kunin ung number of ward mamaya obvious diba? haha!
		ward_count = new JLabel("No. Of Wards/Rooms ");
		ward_count.setBounds(690,350,200,20);
		ward_count.setOpaque(true);
		ward_count.setBackground(Color.CYAN);
		add(ward_count);
	
}


public void setPanels(){
	
	
	JLabel panelLabel1 = new JLabel("  Ward/Room Information ");
	panelLabel1.setBounds(20,50,150,20);
	panelLabel1.setOpaque(true);
	panelLabel1.setBackground(Color.CYAN);
	add(panelLabel1);
	
	JLabel panel1 = new JLabel();
	panel1.setBounds(20,70,390,310);
	panel1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel1);
	
	
	JLabel panelLabel2 = new JLabel(" Check Available Wards/Rooms: ");
	panelLabel2.setBounds(450,80,190,20);
	panelLabel2.setOpaque(true);
	panelLabel2.setBackground(Color.CYAN);
	add(panelLabel2);
	
	JLabel panel2 = new JLabel();
	panel2.setBounds(440,70,720,310);
	panel2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel2);
	
	
	
}


public void setBackground(){

	JLabel background = new JLabel(new ImageIcon("res/ward.png"));
	 background.setBounds(0,0,1200,550);
	// background.setBackground(new Color(0,0,255));
	// background.setBackground(Color.LIGHT_GRAY);
	 background.setOpaque(true);
	 add(background);
	 
}




public void setFields(){
	
	
	wardID = new JTextField();
	wardID.setBounds(190,80,200,18);
	//wardID.setEditable(false);
	wardID.addKeyListener(this);
	add(wardID);
	
	type = new JComboBox(T);
	type.setBounds(190,110,200,18);
	type.setSelectedIndex(0);
	type.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent jammi) {
			// TODO Auto-generated method stub
			String getType = type.getSelectedItem().toString();
			switch(getType){
			
			case "Private":
				
				price.setText("500");
				
				break;
				
			case "Pay Ward":
				
				price.setText("250");
				
				break;
				
			case "Charity Ward":
				
				price.setText("0");
				
				break;
				
				default:
					
					price.setText("0");
					break;
			
			}
			
			
			WardTypeSearch();
			
		}
		
		
	});
	add(type);
	
	wardNo = new JTextField();
	wardNo.setBounds(190,140,200,18);
	wardNo.addKeyListener(Kim);
	add(wardNo);
	
	bed_no = new JTextField();
	bed_no.setBounds(190,170,200,18);
	add(bed_no);
	
	numBeds = new JTextField();
	numBeds.setBounds(190,200,200,18);
	add(numBeds);
			
	docEx = new JTextField();
	docEx.setBounds(190,230,200,18);
	add(docEx);
	
	price = new JTextField();
	price.setBounds(190,320,200,18);
	add(price);
	
	
	availability = new JComboBox(A);
	availability.setBounds(190,350,200,18);
	availability.setSelectedIndex(0);
	add(availability);
			
	desc=new JTextArea();
	//Remarks.setBounds(100,505,250,240);
	desc.setToolTipText("Please use 'Enter Key' to use line break!");
	desc.setLineWrap(true);
	desc.setWrapStyleWord(true);
	//add(Remarks);
	scroll=new JScrollPane(desc);
	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	//scrollRemarks.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	scroll.setBounds(190,260,200,50);
	add(scroll);
			
	
	
	caw = new JComboBox(CAW);
	caw.setBounds(650,80,100,18);
	caw.setSelectedIndex(0);
	caw.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent jammi) {
			// TODO Auto-generated method stub
			
			String getType = caw.getSelectedItem().toString();
			switch(getType){
			
			case "Private":
				
				price.setText("500");
				
				break;
				
			case "Pay Ward":
				
				price.setText("250");
				
				break;
				
			case "Charity Ward":
				
				price.setText("0");
				
				break;
				
				default:
					
					price.setText("0");
					break;
			
			}
			
			CheckAvailable();
			
			
			
		}
		
	});
	add(caw);
			
	
}


public void setButtons(){
	

	
	add = new JButton("ADD");
	add.setBounds(310,430,90,25);
	add.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			checkInputs();
		}
		
	});
	add(add);
	
	
	edit = new JButton("EDIT");
	edit.setBounds(420,430,70,25);
	edit.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			EditSQL();
			
		}
		
	});
	add(edit);
	
	
	
	clear = new JButton("CLEAR FIELDS");
	clear.setBounds(510,430,120,25);
	clear.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			ClearFields();
			
		}
		
	});
	add(clear);
	
	
	exit = new JButton("EXIT");
	exit.setBounds(650,430,90,25);
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
	line.setBounds(0,390,1200,1);
	line.setOpaque(true);
	line.setBackground(Color.RED);
	add(line);
	
	
	
}


//CHECKING USER INPUTS
public void checkInputs(){
	
	if(wardNo.getText().toString().equals("")){
		wardNo.setBackground(inputFailed);
		wardNo.setForeground(changeInput);
	}else{
		wardNo.setBackground(inputPass);
		wardNo.setForeground(returnInput);
	}
	
	if(numBeds.getText().toString().equals("")){
		numBeds.setBackground(inputFailed);
		numBeds.setForeground(changeInput);
	}else{
		numBeds.setBackground(inputPass);
		numBeds.setForeground(returnInput);
	}
	
	if(docEx.getText().toString().equals("")){
		docEx.setBackground(inputFailed);
		docEx.setForeground(changeInput);
	}else{
		docEx.setBackground(inputPass);
		docEx.setForeground(returnInput);
	}
	
	if(price.getText().toString().equals("")){
		price.setBackground(inputFailed);
		price.setForeground(changeInput);
	}else{
		price.setBackground(inputPass);
		price.setForeground(returnInput);
	}
	
	if(desc.getText().toString().equals("")){
		desc.setBackground(inputFailed);
		desc.setForeground(changeInput);
	}else{
		desc.setBackground(inputPass);
		desc.setForeground(returnInput);
	}
	
	if(type.getSelectedItem().toString().equals("")){
		type.setBackground(inputFailed);
		type.setForeground(changeInput);
	}else{
		type.setBackground(inputPass);
		type.setForeground(returnInput);
	}
	
	if(bed_no.getText().toString().equals("")){
		bed_no.setBackground(inputFailed);
		bed_no.setForeground(changeInput);
	}else{
		bed_no.setBackground(inputPass);
		bed_no.setForeground(returnInput);
	}
	
	if(availability.getSelectedItem().toString().equals("")){
		availability.setBackground(inputFailed);
		availability.setForeground(changeInput);
	}else{
		availability.setBackground(inputPass);
		availability.setForeground(returnInput);
	}
	
	if(wardNo.getText().toString().equals("") || numBeds.getText().toString().equals("") ||
			docEx.getText().toString().equals("") || price.getText().toString().equals("") 
			|| desc.getText().toString().equals("") || bed_no.getText().toString().equals("") 
			|| type.getSelectedItem().equals("") || availability.getSelectedItem().equals("")){
		
		JOptionPane.showMessageDialog(null,"Please Fill All Fields Required In Red Color!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);	
		
	}
	
	else{
		
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

//set the sql query
String query = "INSERT INTO ward (type,number,num_beds,doctor_experties,description,price_per_day,availability,bed_no)" + "values(?,?,?,?,?,?,?,?)";


java.sql.Statement stmt = conn.createStatement();


	 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);

	 
	 	ps.setString(1, (String) type.getSelectedItem());

	    ps.setString(2, wardNo.getText().toString()); 

	    ps.setString(3, numBeds.getText().toString()); 

	    ps.setString(4, docEx.getText().toString()); 
	  
	    ps.setString(5, desc.getText().toString());

	    ps.setString(6, price.getText().toString()); 
	    
	    ps.setString(7, (String) availability.getSelectedItem()); 
	    
	    ps.setString(8, bed_no.getText().toString()); 

	    ps.execute();

	  JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
	 
	  ClearFields();
	
	  getWardCount();

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
   	  
    sql = "SELECT * FROM ward";
    ResultSet rs = stmt.executeQuery(sql);
		
		 table.setModel(DbUtils.resultSetToTableModel(rs));

}catch (Exception moriel){

		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

}




} 




//MUNTIK KO NG HINDI MAKITA ANG METHOD NA ITO SA DAMI NG CODES
//MORIEL!!!! HUHU!
public void ClearFields(){
	
	//wardID.setText("");
	
	type.setSelectedItem("");

	wardNo.setText("");
	
	bed_no.setText("");

	numBeds.setText("");

	docEx.setText("");
  
	desc.setText("");

    price.setText("");
    
    availability.setSelectedItem("");
	 
	
}


//METHOD FOR SEARCHING ACCOUNT BAKAYAROU!
public void SearchSQL(){
	  
	ClearFields();
	
	String query = "SELECT * FROM ward WHERE ward_id="+"\""+wardID.getText().toString()+"\"";

	
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

				 	wardID.setText(rs.getString("ward_id"));
				 	
				 	type.setSelectedItem(rs.getString("type"));

					wardNo.setText(rs.getString("number"));
					
					bed_no.setText(rs.getString("bed_no"));

					numBeds.setText(rs.getString("num_beds"));

					docEx.setText(rs.getString("doctor_experties"));
				  
					desc.setText(rs.getString("description"));

				    price.setText(rs.getString("price_per_day"));
				    
				    availability.setSelectedItem(rs.getString("availability"));

				
		counter++;
			 }
			 

			 if(counter<1){
				 
			//	 JOptionPane.showMessageDialog(null,"Ward Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }

		conn.close();

		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 

			} 

} 



//METHOD PARA SA TABLE ROW CLICKING
public void tableClickSearch(){
	  
	
	String query = "SELECT * FROM ward WHERE ward_id="+"\""+table.getValueAt(table.getSelectedRow(), 0)+"\"";

	
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

				 	wardID.setText(rs.getString("ward_id"));
				 	
				 	type.setSelectedItem(rs.getString("type"));

					wardNo.setText(rs.getString("number"));
					
					bed_no.setText(rs.getString("bed_no"));

					numBeds.setText(rs.getString("num_beds"));

					docEx.setText(rs.getString("doctor_experties"));
				  
					desc.setText(rs.getString("description"));

				    price.setText(rs.getString("price_per_day"));
				    
				    availability.setSelectedItem(rs.getString("availability"));

				
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
			String query = "UPDATE ward SET type=?,number=?,num_beds=?,doctor_experties=?,description=?,price_per_day=?,availability=?,bed_no=? WHERE ward_id = "+"\""+wardID.getText().toString()+"\"";

			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			
			
			ps.setString(1, (String) type.getSelectedItem());

		    ps.setString(2, wardNo.getText().toString()); 

		    ps.setString(3, numBeds.getText().toString()); 

		    ps.setString(4, docEx.getText().toString()); 
		  
		    ps.setString(5, desc.getText().toString());

		    ps.setString(6, price.getText().toString()); 
		    
		    ps.setString(7, (String) availability.getSelectedItem());
		    
		    ps.setString(8, bed_no.getText().toString()); 
			
		    ps.execute();

			 JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 

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
	   	  
	    sql = "SELECT * FROM ward";
	    ResultSet rs = stmt.executeQuery(sql);
			
			 table.setModel(DbUtils.resultSetToTableModel(rs));

	}catch (Exception moriel){

			 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

	}
	
	getWardCount();
	  
	
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
        	  
         sql = "SELECT * FROM ward";
         ResultSet rs = stmt.executeQuery(sql);
         table = new JTable(model);
   		 JScrollPane scrollPane = new JScrollPane(table);
   		 table.setPreferredScrollableViewportSize(new Dimension(500, 70));
   		 table.setFillsViewportHeight(true);
   		 table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
   		 table.setBackground(Color.LIGHT_GRAY);
   		 scrollPane.setBounds(450,110,700,230);
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
        	  
        	sql = "SELECT * FROM ward WHERE ward_id LIKE "+"\""+wardID.getText().toString()+"%\"";
        	ResultSet rs = stmt.executeQuery(sql);
        	table.setModel(DbUtils.resultSetToTableModel(rs));
         
    
	 }catch(Exception moriel){
		 
		 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
	 }
	 
	
}


//METHOD FOR SEARCHING WARD WHEN TYPE IS CLICKED!
public void WardTypeSearch(){
	
	 String url = "jdbc:mysql://localhost:3306/h_m_s";
   String userid = "root";
   String password = "";
   String sql;
  
 
	
	 try  {
   	
   	Connection connection = (Connection) DriverManager.getConnection( url, userid, password );
       Statement stmt = connection.createStatement();
      	  
      	sql = "SELECT * FROM ward WHERE type LIKE "+"\""+type.getSelectedItem().toString()+"%\"";
      	ResultSet rs = stmt.executeQuery(sql);
      	table.setModel(DbUtils.resultSetToTableModel(rs));
       
  
	 }catch(Exception moriel){
		 
		 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
	 }
	 
	
}






//METHOD PARA I-CHECK ANG AVAILABLE ROOMS CHIKUSHO!
public void CheckAvailable(){
	
	 String url = "jdbc:mysql://localhost:3306/h_m_s";
   String userid = "root";
   String password = "";
   String sql;
  
 
	
	 try  {
   	
   	Connection connection = (Connection) DriverManager.getConnection( url, userid, password );
       Statement stmt = connection.createStatement();
      	  
      	sql = "SELECT * FROM ward WHERE availability = 'Available' AND type LIKE "+"\""+caw.getSelectedItem()+"%\""+"";
      	ResultSet rs = stmt.executeQuery(sql);
      	table.setModel(DbUtils.resultSetToTableModel(rs));
       
  
	 }catch(Exception moriel){
		 
		 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
	 }
	 
	
}


public void getWardCount(){
	
	
String query = "SELECT * FROM ward ORDER BY ward_id ASC";

	
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

				 
				ward_count.setText("  No. Of Wards/Rooms:  "+rs.getInt("ward_id"));
				
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


	
	//NEW CLASS PARA SA ROOM NUMBER SEARCH DEPENDE SA WARD TYPE
	public class Moriel implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			 WardRoomSearch();
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			 WardRoomSearch();
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			 WardRoomSearch();
		}
		
		
		//METHOD FOR SEARCHING WARD TYPE AND ROOM NUMBER PAG NI-TYPE UNG ROOM NUMBER SA FIELD BAKAYAROU!
		public void WardRoomSearch(){
			
			 String url = "jdbc:mysql://localhost:3306/h_m_s";
		   String userid = "root";
		   String password = "";
		   String sql;
		  
		 
			
			 try  {
		   	
		   	Connection connection = (Connection) DriverManager.getConnection( url, userid, password );
		       Statement stmt = connection.createStatement();
		      	  
		      	sql = "SELECT * FROM ward WHERE type LIKE "+"\""+type.getSelectedItem().toString()+"%\""+" AND number LIKE "+"\""+wardNo.getText().toString()+"%\"";
		      	ResultSet rs = stmt.executeQuery(sql);
		      	table.setModel(DbUtils.resultSetToTableModel(rs));
		       
		  
			 }catch(Exception moriel){
				 
				 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }
			 
			
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
