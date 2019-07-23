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
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Reports extends JFrame implements ActionListener{
	
	JLabel labels,title;
	
	JButton exit, search, print;
	
	JTable table;

	DefaultTableModel model;
	
	//VARS FOR DATE
	String M[] = {"Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	String D[] = {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	JComboBox Month, Day;
	
	JTextField Yr;
	
	JTextField total;
	
	double getBill;
	
	
	//VARS PARA SA PRINTING
	Graphics2D g2;
	Image logo;
	
	
	//COLORS PARA SA CHECKING INPUTS
		Color inputFailed = Color.RED;
		Color changeInput = Color.WHITE;
		Color inputPass = Color.WHITE;
		Color returnInput = Color.BLACK;
	
	public Reports(){
		super("HOSPITAL MANAGEMENT SYSTEM");
		setLayout(null);
		
		setTitle();
		setLabels();
		setFields();
		setButtons();
		setPanel();
		setLine();
		tableModel();
		setBackground();
		
		
	}
	
	
public void setTitle(){
		
		title = new JLabel("                   " +
				"                      " +
				"                   REPORTS");
		title.setBounds(0,0,860,25);
		title.setOpaque(true);
		title.setBackground(Color.green);
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

	JLabel background = new JLabel(new ImageIcon("res/reports.png"));
	 background.setBounds(0,0,860,580);
	 background.setBackground(new Color(0,0,255));
	// background.setBackground(Color.LIGHT_GRAY);
	 background.setOpaque(true);
	 add(background);
	 
}


public void setLabels(){
	
	
	prepareLabels(" Select Date:",130,70,90,18);
	
	prepareLabels(" Total:",630,450,50,18);
	
	
}


public void setPanel(){
	
	JLabel panel1 = new JLabel();
	panel1.setBounds(120,60,670,420);
	panel1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel1);
	
	JLabel panel2 = new JLabel();
	panel2.setBounds(320,70,500,440);
	panel2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	//add(panel2);
	
	JLabel panelLabel1 = new JLabel("  Personal Information ");
	panelLabel1.setBounds(20,50,130,20);
	panelLabel1.setOpaque(true);
	panelLabel1.setBackground(Color.CYAN);
	//add(panelLabel1);
	

	JLabel panelLabel2 = new JLabel("  Charges Information ");
	panelLabel2.setBounds(20,270,130,20);
	panelLabel2.setOpaque(true);
	panelLabel2.setBackground(Color.CYAN);
	//add(panelLabel2);
	
	JLabel panel3 = new JLabel();
	panel3.setBounds(20,290,280,220);
	panel3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	//add(panel3);
	
	
	
	
	
}


public void setFields(){
	
	
	
		Month = new JComboBox(M);
		Month.setBounds(230,70,90,18);
		Month.setSelectedIndex(0);
		add(Month);

		Day = new JComboBox(D);
		Day.setBounds(330,70,50,18);
		Day.setSelectedIndex(0);
		add(Day);


			
			Yr = new JTextField("2017");
			Yr.setBounds(390,70,50,18);
			Yr.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent saber) {
					// TODO Auto-generated method stub
					SearchSQL();
					
				}
				
			});
			add(Yr);
			
			
			
			total = new JTextField();
			total.setBounds(690,450,90,18);
			total.setEditable(false);
			add(total);
			
			
	
}


public void setButtons(){
	
	
	search = new JButton("OK");
	search.setBounds(460,70,70,20);
	search.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			SearchSQL();
			SearchBill();
		}
		
	});
	add(search);
	
	
	
	print = new JButton("PRINT REPORT");
	print.setBounds(260,510,120,25);
	print.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
	
			 MessageFormat header = new MessageFormat("REPORTS");
			 MessageFormat footer = new MessageFormat("page{0,number,integer}");
			 
			 try {
			table.print(PrintMode.FIT_WIDTH, header, footer);
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 
	
			
		}
		
	});
	add(print);
	
	
	exit = new JButton("EXIT");
	exit.setBounds(420,510,100,25);
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
	line.setBounds(0,490,860,1);
	line.setOpaque(true);
	line.setBackground(Color.RED);
	add(line);
}




//METHOD FOR SEARCHING BAKAYAROU!
public void SearchSQL(){
	
	String query = "SELECT patient_id,first_name,middle_name,last_name," +
			"ward_type,ward_charge,phic,month,day,year,doctor_charge," +
			"other_charge,surgery_charge,laboratory_charge,bill FROM billing_history WHERE month = "+"\""+Month.getSelectedItem().toString()+"\""
			+" AND day = "+"\""+Day.getSelectedItem().toString()+"\""+" AND year = "+"\""+Yr.getText().toString()+"\"";
	
	
	if(Month.getSelectedItem().equals("Month") && Day.getSelectedItem().equals("Day")){
		
		query = "SELECT patient_id,first_name,middle_name,last_name," +
				"ward_type,ward_charge,phic,month,day,year," +
				"doctor_charge,other_charge,surgery_charge,laboratory_charge,bill FROM billing_history WHERE year= "+"\""+Yr.getText().toString()+"\"";
		
	}else if(Day.getSelectedItem().equals("Day")){
		
		query = "SELECT patient_id,first_name,middle_name,last_name,ward_type," +
				"ward_charge,phic,month,day,year," +
				"doctor_charge,other_charge,surgery_charge," +
				"laboratory_charge,bill FROM billing_history WHERE month = "+"\""+Month.getSelectedItem().toString()+"\""
				+" AND year = "+"\""+Yr.getText().toString()+"\"";
	}
	  
	
	
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
		
		 table.setModel(DbUtils.resultSetToTableModel(rs));

		conn.close();

		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 

			} 

} 


//METHOD FOR SEARCHING BAKAYAROU!
public void SearchBill(){
	
	getBill=0;
	
	String query = "SELECT * FROM billing_history WHERE month = "+"\""+Month.getSelectedItem().toString()+"\""
			+" AND day = "+"\""+Day.getSelectedItem().toString()+"\""+" AND year = "+"\""+Yr.getText().toString()+"\"";
	
	
	if(Month.getSelectedItem().equals("Month") && Day.getSelectedItem().equals("Day")){
		
		query = "SELECT * FROM billing_history WHERE year= "+"\""+Yr.getText().toString()+"\"";
		
	}else if(Day.getSelectedItem().equals("Day")){
		
		query = "SELECT * FROM billing_history WHERE month = "+"\""+Month.getSelectedItem().toString()+"\""
				+" AND year = "+"\""+Yr.getText().toString()+"\"";
	}
	  
	
	
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
		
		 while(rs.next()){
			 
      	 String b =rs.getString("bill");
      	 double x = Double.parseDouble(b);
      	 double a[] = {x};
      	 
      	 
      	 for(int i=0; i<a.length; i++){
      		 
      	 getBill += a[i];
      	 
      	 total.setText(""+getBill);
      	
      	 }
      	 
      	
      	
      	 
       }
		 
		
		
		 
		 
	

		conn.close();

		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 

			} 

} 



//CODE PARA SA JTABLE NATIN NAHIRAPAN TALAGA AKO DITO CHIKUSHO!
public void tableModel(){
	
	SearchBill();
	
	 String url = "jdbc:mysql://localhost:3306/h_m_s";
     String userid = "root";
     String password = "";
     String sql;
    
	 try  {
     	
     	Connection connection = (Connection) DriverManager.getConnection( url, userid, password );
         Statement stmt = connection.createStatement();
        	  
         sql = "SELECT patient_id, first_name, middle_name, last_name," +
         		"ward_type,ward_charge,phic,month,day,year," +
         		"doctor_charge,other_charge,surgery_charge,laboratory_charge,bill FROM billing_history";
         ResultSet rs = stmt.executeQuery(sql);
         table = new JTable(model);
   		 JScrollPane scrollPane = new JScrollPane(table);
   		 table.setPreferredScrollableViewportSize(new Dimension(500, 100));
   		 table.setFillsViewportHeight(true);
   		 table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
   		 table.setBackground(Color.LIGHT_GRAY);
   		 scrollPane.setBounds(130,130,650,300);
   		 scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
   		 scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
   		 add(scrollPane);
   		 table.setModel(DbUtils.resultSetToTableModel(rs));
   		 
   		
 
         
    
	 }catch(Exception moriel){
		 
		 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
	 }
    
}


//METHOD PARA SA EXIT BUTTON
public void CloseMe(){
	this.dispose();
}



@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
