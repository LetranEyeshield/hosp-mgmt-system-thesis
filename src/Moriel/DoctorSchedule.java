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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DoctorSchedule extends JFrame implements ActionListener, MouseListener, KeyListener{
	
	JLabel labels,title, pixBox, doctor_count;
	
	
	JTextField did, docName, date, time, patient, subject, sched_id;
	
	JButton add, edit, clear, exit;
	
	JTable docTable;
	DefaultTableModel docTableModel;
	
	JTable schedTable;
	DefaultTableModel schedModel;
	
	
	//COLORS PARA SA CHECKING INPUTS
	Color inputFailed = Color.RED;
	Color changeInput = Color.WHITE;
	Color inputPass = Color.WHITE;
	Color returnInput = Color.BLACK;
		
	//PREPARE THE CLASS PARA SA DOCTOR NAME SEARCHING
	Moriel Kim = new Moriel();
	
	//PREPARE THE OTHER CLASS PARA NAMAN SA DATE SEARCHING
	MorielKim Manaay = new MorielKim();
	
	public DoctorSchedule(){
		super("HOSPITAL MANAGEMENT SYSTEM");
		setLayout(null);
		
		setTitle();
		setLabels();
		setFields();
		setButtons();
		setPanels();
		setLine();
		tableModel();
		tableSched();
		setBackground();
		
	
		
	}
	
	
public void setTitle(){
		
		title = new JLabel("                   " +
				"                      " +
				"           DOCTOR SCHEDULE");
		title.setBounds(0,0,860,25);
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
	
	
	JLabel labelSched = new JLabel(" Schedule List");
	labelSched.setBounds(590,70,130,20);
	labelSched.setOpaque(true);
	labelSched.setBackground(Color.CYAN);
	labelSched.setFont(new Font("Tahoma",Font.PLAIN,18));
	add(labelSched);
	
	JLabel labelDP = new JLabel(" Doctor Schedule");
	labelDP.setBounds(150,60,150,20);
	labelDP.setOpaque(true);
	labelDP.setBackground(Color.CYAN);
	labelDP.setFont(new Font("Tahoma",Font.PLAIN,18));
	add(labelDP);
	
	
	JLabel labelDocList = new JLabel(" Doctor List");
	labelDocList.setBounds(30,390,100,16);
	labelDocList.setOpaque(true);
	labelDocList.setBackground(Color.CYAN);
	labelDocList.setFont(new Font("Tahoma",Font.PLAIN,14));
	add(labelDocList);
	
}


public void setBackground(){

	JLabel background = new JLabel(new ImageIcon("res/doctor_sched.png"));
	 background.setBounds(0,0,860,620);
	 background.setBackground(new Color(0,0,255));
	// background.setBackground(Color.LIGHT_GRAY);
	 background.setOpaque(true);
	 add(background);
	 
}


public void setPanels(){
	
	JLabel panel1 = new JLabel();
	panel1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	panel1.setBounds(20,50,400,320);
	add(panel1);
	
	
	JLabel panel2 = new JLabel();
	panel2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	panel2.setBounds(440,50,400,320);
	add(panel2);
	
	
	JLabel panel3 = new JLabel();
	panel3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	panel3.setBounds(20,410,820,160);
	add(panel3);
	
}

public void setLabels(){
	
	//DAHIL SA PAGBABAGONG RECOMMENDATION NI SIR SALAYOG, I DECIDE TO COMMENT IT OUT
	//prepareLabels(" Schedule ID: ",30,70,90,18);
	
	
	prepareLabels(" Doctor ID: ",30,100,110,18);
	prepareLabels(" Doctor Name: ",30,130,110,18);
	prepareLabels(" Date: ",30,160,110,18);
	prepareLabels(" Time: ",30,190,110,18);
	prepareLabels(" Name of Patient: ",30,220,110,18);
	prepareLabels(" Subject:",30,250,110,18);
	
	
	
}


public void setFields(){
	
	//BECAUSE OF THE RECOMMENDATION OF SIR SALAYOG, I DECIDE TO SETVISIBLE TO FALSE PERO BAKA MAGAMIT NYO RIN IN THE FUTURE
	//MGA FUTURE RESEARCHERS HEHE...
	sched_id = new JTextField();
	sched_id.setBounds(130,70,120,18);
	sched_id.setEditable(false);
	sched_id.setVisible(false);
	add(sched_id);
	
	did = new JTextField();
	did.setBounds(150,100,200,18);
	//did.setEditable(false);
	did.addKeyListener(this);
	add(did);
	
	docName = new JTextField();
	docName.setBounds(150,130,200,18);
	//docName.setEditable(false);
	docName.addKeyListener(Kim);
	add(docName);
	
	date = new JTextField("Jamuary 21, 2017");
	date.setBounds(150,160,200,18);
	date.addKeyListener(Manaay);
	add(date);
	
	time = new JTextField("2:00 pm");
	time.setBounds(150,190,200,18);
	add(time);
	
	patient = new JTextField("Juan Dela Cruz");
	patient.setBounds(150,220,200,18);
	add(patient);
	
	subject = new JTextField("Consultation");
	subject.setBounds(150,250,200,18);
	add(subject);
	
	
			
	
}


public void setButtons(){
	

	
	add = new JButton("ADD");
	add.setBounds(30,300,60,25);
	add.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			InsertSQL();
		}
		
	});
	add(add);
	
	
	
	edit = new JButton("EDIT");
	edit.setBounds(100,300,70,25);
	edit.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			EditSQL();
			
		}
		
	});
	add(edit);
	
	
	clear = new JButton("CLEAR FIELDS");
	clear.setBounds(185,300,120,25);
	clear.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			ClearFields();
			
		}
		
	});
	add(clear);
	
	
	exit = new JButton("EXIT");
	exit.setBounds(320,300,70,25);
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
	line.setBounds(0,380,860,2);
	line.setOpaque(true);
	line.setBackground(Color.RED);
	add(line);
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



java.sql.Statement stmt = conn.createStatement();
 
	
	 
	 String query = "INSERT INTO doctors_schedule (doctor_id, doctor_name, date, time, patient, subject)" + "values(?,?,?,?,?,?)";

	 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);

	 ps.setString(1, did.getText().toString());

	 ps.setString(2, docName.getText().toString()); 

	 ps.setString(3, date.getText().toString()); 

	 ps.setString(4, time.getText().toString()); 
	  
	 ps.setString(5, patient.getText().toString());

	 ps.setString(6, subject.getText().toString()); 

	
	 ps.execute();

	  JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 
	 


conn.close();

}catch (Exception moriel){

 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

	} 



SearchAllSchedOfDoctor();



} 





//METHOD FOR SEARCHING DOCTORS BAKAYAROU!
public void SearchSQL(){
	
	ClearFields();
	
	String check = "SELECT * FROM doctors WHERE doctor_id="+"\""+did.getText().toString()+"\"";

	
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

				did.setText(rs.getString("doctor_id"));
				docName.setText(rs.getString("doctor_name"));
				sched_id.setText("");
				date.setText("");
				time.setText("");
				patient.setText("");
				subject.setText("");
				
				
		counter++;
			 }
			 

			 if(counter<1){
				 
				 //JOptionPane.showMessageDialog(null,"Account Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }

		conn.close();

		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 

			} 
	
	SearchAllSchedOfDoctor();
	

} 


//METHOD PARA SA DOCTOR'S TABLE ROW CLICKING
public void doctorTableClickSearch(){
	
	
	String check = "SELECT * FROM doctors WHERE doctor_id="+"\""+docTable.getValueAt(docTable.getSelectedRow(), 1)+"\"";

	
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

				did.setText(rs.getString("doctor_id"));
				docName.setText(rs.getString("doctor_name"));
				sched_id.setText("");
				date.setText("");
				time.setText("");
				patient.setText("");
				subject.setText("");
				
				
		counter++;
			 }
			 

			 if(counter<1){
				 
				 JOptionPane.showMessageDialog(null,"Account Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }

		conn.close();

		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 

			} 
	
	SearchAllSchedOfDoctor();
	

} 



//METHOD FOR SEARCHING SCHEDULE WHEN CLICKED UNG SCHED TABLE BAKAYAROU!
public void SearchSchedTableSQL(){
	
	
	String check = "SELECT * FROM doctors_schedule WHERE schedule_id="+"\""+schedTable.getValueAt(schedTable.getSelectedRow(), 0)+"\"";

	
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
				 
				sched_id.setText(rs.getString("schedule_id"));
				did.setText(rs.getString("doctor_id"));
				docName.setText(rs.getString("doctor_name"));
				date.setText(rs.getString("date"));
				time.setText(rs.getString("time"));
				patient.setText(rs.getString("patient"));
				subject.setText(rs.getString("subject"));
				
				
				
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


//METHOD FOR SEARCHING ALL DOCTOR'S SCHED KONOYAROU!
public void SearchAllSchedOfDoctor(){
	
	
	String sql = "SELECT * FROM doctors_schedule WHERE doctor_id="+"\""+did.getText().toString()+"\"";
	
	
	String URL = "jdbc:mysql://localhost:3306/h_m_s";
	String userid = "root";
	String password = "";

	try  {
		
		Connection connection = (Connection) DriverManager.getConnection( URL, userid, password );
	    Statement stmt = connection.createStatement();
	    ResultSet rs = stmt.executeQuery(sql);
			
			 schedTable.setModel(DbUtils.resultSetToTableModel(rs));

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
			String query = "UPDATE doctors_schedule SET date=?, time=?, patient=?, subject=? WHERE schedule_id = "+"\""+sched_id.getText().toString()+"\"";

			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
						
			ps.setString(1, date.getText().toString()); 
			
			ps.setString(2, time.getText().toString()); 
			
			ps.setString(3, patient.getText().toString()); 
			
			ps.setString(4, subject.getText().toString()); 

			
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
	
	
	SearchAllSchedOfDoctor();
	  
	
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
         docTable = new JTable(docTableModel);
   		 JScrollPane scrollPane = new JScrollPane(docTable);
   		 docTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
   		 docTable.setFillsViewportHeight(true);
   		 docTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
   		 docTable.setBackground(Color.LIGHT_GRAY);
   		 scrollPane.setBounds(40,420,790,140);
   		 scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
   		 scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
   		 add(scrollPane);
   		 docTable.setModel(DbUtils.resultSetToTableModel(rs));
   		 docTable.addMouseListener(this);
        	
         
    
	 }catch(Exception moriel){
		 
		 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
	 }
    
}


//CODE PARA SA TABLE NG DOCTOR SCHED
public void tableSched(){
	
	 String url = "jdbc:mysql://localhost:3306/h_m_s";
   String userid = "root";
   String password = "";
   String sql;
  
	 try  {
   	
   	Connection connection = (Connection) DriverManager.getConnection( url, userid, password );
       Statement stmt = connection.createStatement();
      	  
       sql = "SELECT * FROM doctors_schedule";
       ResultSet rs = stmt.executeQuery(sql);
       schedTable = new JTable(schedModel);
 		 JScrollPane scrollPane = new JScrollPane(schedTable);
 		 schedTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
 		 schedTable.setFillsViewportHeight(true);
 		 schedTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 		 schedTable.setBackground(Color.LIGHT_GRAY);
 		 scrollPane.setBounds(450,130,370,230);
 		 scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
 		 scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
 		 add(scrollPane);
 		 schedTable.setModel(DbUtils.resultSetToTableModel(rs));
 		 schedTable.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent saber) {
				// TODO Auto-generated method stub
				SearchSchedTableSQL();
				
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
 			 
 		 });
      	
       
  
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
        	  
        	sql = "SELECT * FROM doctors WHERE doctor_id LIKE "+"\""+did.getText().toString()+"%\"";
        	ResultSet rs = stmt.executeQuery(sql);
        	docTable.setModel(DbUtils.resultSetToTableModel(rs));
         
    
	 }catch(Exception moriel){
		 
		 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
	 }
	 
	
}





//CLEAR THE FIELDS
public void ClearFields(){
	
	
	sched_id.setText("");
	//did.setText("");
	docName.setText("");
	date.setText("");
	time.setText("");
	patient.setText("");
	subject.setText("");
	
	
}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		AlternateSearchSQL();
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		doctorTableClickSearch();
		
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
	SearchAllSchedOfDoctor();
	
}


@Override
public void keyReleased(KeyEvent arg0) {
	// TODO Auto-generated method stub
	SearchSQL();
	AlternateSearchSQL();
	SearchAllSchedOfDoctor();
}


@Override
public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub
	SearchSQL();
	AlternateSearchSQL();
	SearchAllSchedOfDoctor();
}
	
	
	
//NEW CLASS PARA SA DOCTOR NAME SEARCHING
public class Moriel implements KeyListener{

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		AlternateSearchSQL();
		schedTableUpdate();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		AlternateSearchSQL();
		schedTableUpdate();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		AlternateSearchSQL();
		schedTableUpdate();
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
	        	  
	        	sql = "SELECT * FROM doctors WHERE doctor_name LIKE "+"\""+docName.getText().toString()+"%\"";
	        	ResultSet rs = stmt.executeQuery(sql);
	        	docTable.setModel(DbUtils.resultSetToTableModel(rs));
	         
	    
		 }catch(Exception moriel){
			 
			 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
			 
		 }
		 
		
	}
	
	
	//METHOD PARA NAMAN SA SCHED TABLE
	public void schedTableUpdate(){
		
		 String url = "jdbc:mysql://localhost:3306/h_m_s";
	     String userid = "root";
	     String password = "";
	     String sql;
	    
	   
	 	
		 try  {
	     	
	     	Connection connection = (Connection) DriverManager.getConnection( url, userid, password );
	         Statement stmt = connection.createStatement();
	        	  
	        	sql = "SELECT * FROM doctors_schedule WHERE doctor_name LIKE "+"\""+docName.getText().toString()+"%\"";
	        	ResultSet rs = stmt.executeQuery(sql);
	        	schedTable.setModel(DbUtils.resultSetToTableModel(rs));
	         
	    
		 }catch(Exception moriel){
			 
			 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
			 
		 }
		 
		
	}
	
	
}
	
	
	
	
	
//ITO NAMAN NEW CLASS PARA SA DATE SEARCHING
	//HAAY NAKU NAHIRAPAN AKONG DISKARTEHIN TONG DOCTOR SCHED NA TO!
	//MORIEL!!!!!
	public class MorielKim implements KeyListener{
		
		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			schedTableUpdate();
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			schedTableUpdate();
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			schedTableUpdate();
		}
		
		
		//METHOD PA I-UPDATE ANG TABLE SCHEDULE TRU DATE
		public void schedTableUpdate(){
			
			 String url = "jdbc:mysql://localhost:3306/h_m_s";
		     String userid = "root";
		     String password = "";
		     String sql="";
		    
		     
		     //ETO UNG MGA GINAWA KONG QUERY DEPENDS ON THE SITUATION I ASSUMED
		     //FOR THE NEXT RESEARCHERS, KAU NG BAHALA KUNG BABAGUHIN NYO TO YA-HA!
		     if(did.getText().toString().equals("")||docName.getText().toString().equals("")){
		    	 
		    	 sql = "SELECT * FROM doctors_schedule WHERE date LIKE "+"\""+date.getText().toString()+"%\"";
		    	 
		     }else if(did.getText().toString().equals("")){
		    	 
		    	 sql = "SELECT * FROM doctors_schedule WHERE doctor_name LIKE "+"\""+docName.getText().toString()+"%\""+"AND date LIKE "+"\""+date.getText().toString()+"%\"";
		    	 
		     }else if(docName.equals("")){
		    	 
		    	 sql = "SELECT * FROM doctors_schedule WHERE doctor_id = "+"\""+did.getText().toString()+"\""+"AND date LIKE "+"\""+date.getText().toString()+"%\"";
		    	 
		     }else{
		    	 
		    	 sql = "SELECT * FROM doctors_schedule WHERE doctor_id = "+"\""+did.getText().toString()+"\""+"AND doctor_name = "+"\""+docName.getText().toString()+"\""+"AND date = "+"\""+date.getText().toString()+"\"";
		    	 
		     }
		     
		     
		   
		 	
			 try  {
		     	
		     	Connection connection = (Connection) DriverManager.getConnection( url, userid, password );
		         Statement stmt = connection.createStatement();
		        	  
		        	
		        	ResultSet rs = stmt.executeQuery(sql);
		        	schedTable.setModel(DbUtils.resultSetToTableModel(rs));
		         
		    
			 }catch(Exception moriel){
				 
				 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }
			 
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
