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

import Moriel.PreBilling.Moriel;

import com.mysql.jdbc.Connection;

public class MedicalCert extends JFrame implements ActionListener, Printable, KeyListener, MouseListener{
	
	JLabel labels,title;
	
	
	JTextField PID, FN, MN, LN, address, treatmentDate, physician;
	
	JTextArea diag;
	JScrollPane scroll;
	
	JTable table;
	DefaultTableModel model;
	
	JButton print, clear, exit;
	
	
	//COLORS PARA SA CHECKING INPUTS
		Color inputFailed = Color.RED;
		Color changeInput = Color.WHITE;
		Color inputPass = Color.WHITE;
		Color returnInput = Color.BLACK;
		
		//VARS PARA SA PRINTING
		Graphics2D g2;
		Image logo;
		
		//PREPARE THE CLASS PARA SA LAST NAME SEARCHING
		Moriel Kim = new Moriel();
		
	public MedicalCert(){
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
				"               MEDICAL CERTIFICATE");
		title.setBounds(0,0,910,25);
		title.setOpaque(true);
		title.setBackground(Color.blue);
		title.setFont(new Font("OLD ENGLISH MT",Font.BOLD,20));
		add(title);
	}



public void setBackground(){

	JLabel background = new JLabel(new ImageIcon("res/m_cert.png"));
	 background.setBounds(0,0,910,620);
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
	

	prepareLabels(" Patient ID:",30,80,90,18);
	prepareLabels(" First Name:",30,110,90,18);
	prepareLabels(" Middle Name:",30,140,90,18);
	prepareLabels(" Last Name:",30,170,90,18);
	prepareLabels(" Address:",30,200,90,18);
	
	prepareLabels(" Date Of Examination/Treatment:",30,280,210,18);
	prepareLabels(" Physician:",30,310,210,18);
	prepareLabels(" Diagnosis:",30,340,210,18);
	
	

	
}


public void setPanels(){
	
	JLabel panel1 = new JLabel();
	panel1.setBounds(20,70,445,160);
	panel1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel1);
	
	JLabel panel2 = new JLabel();
	panel2.setBounds(480,70,400,340);
	panel2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel2);
	
	JLabel panelLabel1 = new JLabel("  Personal Information ");
	panelLabel1.setBounds(20,50,130,20);
	panelLabel1.setOpaque(true);
	panelLabel1.setBackground(Color.CYAN);
	add(panelLabel1);
	

	JLabel panelLabel2 = new JLabel("  Medical Information ");
	panelLabel2.setBounds(20,250,130,20);
	panelLabel2.setOpaque(true);
	panelLabel2.setBackground(Color.CYAN);
	add(panelLabel2);
	
	JLabel panel3 = new JLabel();
	panel3.setBounds(20,270,445,140);
	panel3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel3);
	
	
	
	
	
}


public void setFields(){
	
	PID = new JTextField();
	PID.setBounds(130,80,200,18);
	PID.addKeyListener(this);
	add(PID);
	
	FN = new JTextField();
	FN.setBounds(130,110,200,18);
	add(FN);
	
	MN = new JTextField();
	MN.setBounds(130,140,200,18);
	add(MN);
	
	LN = new JTextField();
	LN.setBounds(130,170,200,18);
	LN.addKeyListener(Kim);
	add(LN);
	
	address = new JTextField();
	address.setBounds(130,200,200,18);
	add(address);
	
	treatmentDate = new JTextField("Jamuary 21, 2017");
	treatmentDate.setBounds(250,280,200,18);
	add(treatmentDate);
	
	physician = new JTextField();
	physician.setBounds(250,310,200,18);
	add(physician);
	
	
	diag=new JTextArea();
	//Remarks.setBounds(100,505,250,240);
	diag.setToolTipText("Please use 'Enter Key' to use line break!");
	diag.setLineWrap(true);
	diag.setWrapStyleWord(true);
	//add(Remarks);
	scroll=new JScrollPane(diag);
	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	//scrollRemarks.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	scroll.setBounds(250,340,200,50);
	add(scroll);
			
			
	
}


public void setButtons(){
	
	
	print = new JButton("PRINT");
	print.setBounds(180,460,120,25);
	print.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			checkInputs();
			
		}
		
	});
	add(print);
	
	
	clear = new JButton("CLEAR FIELDS");
	clear.setBounds(340,460,120,25);
	clear.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			ClearFields();
			
		}
		
	});
	add(clear);
	
	
	exit = new JButton("EXIT");
	exit.setBounds(500,460,120,25);
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
	line.setBounds(0,420,910,1);
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
	
	if(address.getText().toString().equals("")){
		address.setBackground(inputFailed);
		address.setForeground(changeInput);
	}else{
		address.setBackground(inputPass);
		address.setForeground(returnInput);
	}
	
	
	
	if(treatmentDate.getText().toString().equals("")){
		treatmentDate.setBackground(inputFailed);
		treatmentDate.setForeground(changeInput);
	}else{
		treatmentDate.setBackground(inputPass);
		treatmentDate.setForeground(returnInput);
	}
	
	if(diag.getText().toString().equals("")){
		diag.setBackground(inputFailed);
		diag.setForeground(changeInput);
	}else{
		diag.setBackground(inputPass);
		diag.setForeground(returnInput);
	}
	
	if(physician.getText().toString().equals("")){
		physician.setBackground(inputFailed);
		physician.setForeground(changeInput);
	}else{
		physician.setBackground(inputPass);
		physician.setForeground(returnInput);
	}
	

	
	if(PID.getText().toString().equals("") || FN.getText().toString().equals("") ||  MN.getText().toString().equals("") || LN.getText().toString().equals("") 
			|| address.getText().toString().equals("") || treatmentDate.getText().equals("") ||
			diag.getText().toString().equals("") || physician.getText().toString().equals("")){
		
		JOptionPane.showMessageDialog(null,"Please Fill All Fields Required In Red Color!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
		
	}
	
	else{
	
		printMe();
	}
	
}


//CLEAR THE FIELDS
public void ClearFields(){
	
	FN.setText("");
	MN.setText("");
	LN.setText(""); 
	address.setText("");
	treatmentDate.setText("");
	physician.setText("");
	diag.setText("");
}



//METHOD FOR SEARCHING ACCOUNT BAKAYAROU!
public void SearchSQL(){
	
	ClearFields();
	
	
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
				 address.setText(rs.getString("address")); 
				 diag.setText(rs.getString("diagnosis")); 
				
				
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
      	
       sql = "SELECT patient_id, first_name, middle_name, last_name, diagnosis FROM patient_info WHERE patient_id LIKE "+"\""+PID.getText().toString()+"%\"";
   
      	ResultSet rs = stmt.executeQuery(sql);
      	table.setModel(DbUtils.resultSetToTableModel(rs));
       
  
	 }catch(Exception moriel){
		 
		 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
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
  	  
   sql = "SELECT patient_id, first_name, middle_name, address, diagnosis FROM patient_info";
   ResultSet rs = stmt.executeQuery(sql);
   table = new JTable(model);
		 JScrollPane scrollPane = new JScrollPane(table);
		 table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		 table.setFillsViewportHeight(true);
		 table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 table.setBackground(Color.LIGHT_GRAY);
		 scrollPane.setBounds(490,80,380,320);
		 scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		 scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		 add(scrollPane);
		 table.setModel(DbUtils.resultSetToTableModel(rs));
		 table.addMouseListener(this);
  	
   

	 }catch(Exception moriel){
		 
		 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
	 }

}



//METHOD PARA SA TABLE ROW CLICKING
public void tableClickSearch(){
	  
	
	// RENDERING NA NG RESULTS
	String check = "SELECT * FROM patient_info WHERE patient_id="+"\""+table.getValueAt(table.getSelectedRow(), 0)+"\"";

	
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
				  address.setText(rs.getString("address"));		 
				  diag.setText(rs.getString("diagnosis"));
				  treatmentDate.setText("");
				
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
    g.drawString("Medical Certificate", 240,135);
    
    
    g.setFont(new Font("Tahoma",Font.ITALIC,7));
    g.setColor(Color.BLACK);
    g.drawString(dateFormat.format(date), 410,135);
    
    
    
    g.setFont(new Font("Tahoma",Font.PLAIN,8));
    g.setColor(Color.BLACK);

    g.drawString("Patient ID: "+PID.getText().toString(), 100,170);
    
    g.setFont(new Font("Tahoma",Font.BOLD,10));
    g.setColor(Color.BLACK);
    g.drawString("To Whom It May Concern:", 100,190);
    
    
    g.setFont(new Font("Tahoma",Font.PLAIN,8));
    g.setColor(Color.BLACK);
    
    
    g.drawString("THIS IS TO CERTIFY that "+FN.getText().toString().toUpperCase()+" "+MN.getText().toString().toUpperCase()+" "+
  			LN.getText().toString().toUpperCase()+" of "+address.getText().toString().toUpperCase(), 130,210);
  
    g.drawString("Was examined and treated at the  HOSPITAL MANAGEMENT SYSTEM on "+treatmentDate.getText().toString().toUpperCase(), 100,220);
    
   
    g.drawString("with the following diagnosis: ", 100,230);
    
    g.setFont(new Font("Tahoma",Font.ITALIC,10));
    g.drawString(diag.getText().toString(), 100,240);
    
    
    g.setFont(new Font("Tahoma",Font.PLAIN,8));
    g.drawString("And would need medical attention for "+physician.getText().toString().toUpperCase()+", days barring ", 100,270);
    
    g.drawString("complication", 100,280);
    
    g.drawString(physician.getText().toString(), 360,300);
    g.drawString("___________________________", 350,302);
    g.drawString("    Physician", 380,312);
    
   g.setFont(new Font("Tahoma",Font.ITALIC,10));
    g.setColor(Color.BLACK);
    //draw line separator
   g.drawString("_______________________________________" +
    		"_________________________________", 70, 320);
  

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
    		"_ _ _ _ _ _ _ _ _ _ _ _", 70, 380);
  
  
    
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
    g.drawString("Medical Certificate", 240,510);
    
    
    g.setFont(new Font("Tahoma",Font.ITALIC,7));
    g.setColor(Color.BLACK);
    g.drawString(dateFormat.format(date), 410,510);
    
    
    
    g.setFont(new Font("Tahoma",Font.PLAIN,8));
    g.setColor(Color.BLACK);

    g.drawString("Patient ID: "+PID.getText().toString(), 100,530);
    
    g.setFont(new Font("Tahoma",Font.BOLD,10));
    g.setColor(Color.BLACK);
    g.drawString("To Whom It May Concern:", 100,550);
    
    
    g.setFont(new Font("Tahoma",Font.PLAIN,8));
    g.setColor(Color.BLACK);
    
    
    g.drawString("THIS IS TO CERTIFY that "+FN.getText().toString().toUpperCase()+" "+MN.getText().toString().toUpperCase()+" "+
  			LN.getText().toString().toUpperCase()+" of "+address.getText().toString().toUpperCase(), 130,560);
  
    g.drawString("Was examined and treated at the  HOSPITAL MANAGEMENT SYSTEM on "+treatmentDate.getText().toString().toUpperCase(), 100,570);
    
   
    g.drawString("with the following diagnosis: ", 100,580);
    
    g.setFont(new Font("Tahoma",Font.ITALIC,10));
    g.drawString(diag.getText().toString(), 100,590);
    
    
    g.setFont(new Font("Tahoma",Font.PLAIN,8));
    g.drawString("And would need medical attention for "+physician.getText().toString().toUpperCase()+", days barring ", 100,620);
    
    g.drawString("complication", 100,630);
    
    
    g.drawString(physician.getText().toString(), 360,650);
    g.drawString("___________________________", 350,652);
    g.drawString("    Physician", 380,662);
    

   g.setFont(new Font("Tahoma",Font.ITALIC,10));
    g.setColor(Color.BLACK);
    //draw line separator
    g.drawString("_______________________________________" +
    		"_________________________________", 70, 675);
  

    g.setFont(new Font("Tahoma",Font.ITALIC,8));
    g.drawString("Email Address: region1mc2003@yahoo.com", 100,690);
    
    g.setFont(new Font("Tahoma",Font.ITALIC,8));
    g.drawString("Telephone Numbers: (075) 515-89-16 ; (075)515-89-18 ; (075)515-89-01", 100,700);

    g.setFont(new Font("Tahoma",Font.ITALIC,8));
    g.drawString("Fax: (075) 523-41-03", 100,710);



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
	      	  
	      	//sql = "SELECT * FROM admission WHERE first_name LIKE "+"\""+alternateSearch.getText().toString()+"%\""+"OR last_name LIKE "+"\""+alternateSearch.getText().toString()+"%\""+"OR patient_id LIKE "+"\""+alternateSearch.getText().toString()+"%\"";
	      	
	       sql = "SELECT patient_id, first_name, middle_name, last_name, diagnosis FROM patient_info WHERE last_name LIKE "+"\""+LN.getText().toString()+"%\"";
	   
	      	ResultSet rs = stmt.executeQuery(sql);
	      	table.setModel(DbUtils.resultSetToTableModel(rs));
	       
	  
		 }catch(Exception moriel){
			 
			 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
			 
		 }
		 
		
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
