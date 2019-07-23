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

public class Discharge extends JFrame implements ActionListener, Printable, MouseListener, KeyListener{
	
	JLabel labels,title;
	
	
	JTextField PID, caseNo, FN, MN, LN, address, admitDate, dischargeDate, ward, bed_no, numDays, physician;
	
	JButton clear, edit, exit, print;
	
	JTable table;

	DefaultTableModel model;
	
	
	String G[] = {"", "Male", "Female"};
	String getGender;
	JComboBox gender;
	
	String S[] = {"", "Single", "Married", "Widowed","Divorced","Separated"};
	String getStatus;
	JComboBox status;
	
	
	String D[] = {"", "Discharge", "HAMA", "Transferred", "Absconded"};
	String getDispo;
	JComboBox dispo;
	
	String W[] = {"", "Private", "Pay Ward", "Charity Ward"};
	JComboBox wardType;
	
	String R[] = {"", "Recovered","Died","Autopsy", "Improved", "Unimproved", "Others","-48 hours","+48 hours"};
	String getResult;
	JComboBox result;
	
	
	JTextArea admitDiag, finalDiag, otherDiag;
	JScrollPane admitScroll, finalScroll, otherScroll;
	
	//VARS PARA SA PRINTING
	Graphics2D g2;
	Image logo;
	Font font1, font2, font3, font4, font5;
	
	//COLORS PARA SA CHECKING INPUTS
	Color inputFailed = Color.RED;
	Color changeInput = Color.WHITE;
	Color inputPass = Color.WHITE;
	Color returnInput = Color.BLACK;
		
	
	//PREPARE THE CLASS PARA SA LAST NAME SEARCHING
	Moriel Kim = new Moriel();
	
	
	public Discharge(){
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
				"                             " +
				"          PATIENT DISCHARGE");
		title.setBounds(0,0,1250,25);
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

public void setLabels(){
	
	prepareLabels(" Patient ID:",30,90,90,18);
	prepareLabels(" First Name:",30,120,90,18);
	prepareLabels(" Middle Name:",30,150,90,18);
	prepareLabels(" Last Name:",30,180,90,18);
	prepareLabels(" Address:",30,210,90,18);
	
	prepareLabels(" Case No:",30,280,130,18);
	prepareLabels(" Ward/Room No:",30,310,130,18);
	prepareLabels(" Ward/Room Type:",30,340,130,18);
	prepareLabels(" Admitting Date:",30,370,130,18);
	prepareLabels(" Discharge Date:",30,400,130,18);
	prepareLabels(" Number  Of Days:",30,430,130,18);
	prepareLabels(" Disposition:",30,460,130,18);
	prepareLabels(" Result:",30,490,130,18);
	prepareLabels(" Attending Physician:",30,520,130,18);
	prepareLabels(" Bed No:",30,550,130,18);
	
	prepareLabels(" Admitting Diagnosis:",400,280,140,18);
	prepareLabels(" Final Diagnosis:",400,340,140,18);
	prepareLabels(" Other Diagnosis:",400,400,140,18);
	
	
}


public void setPanels(){
	
	JLabel panel1 = new JLabel();
	panel1.setBounds(20,70,760,170);
	panel1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel1);
	
	JLabel panel2 = new JLabel();
	panel2.setBounds(790,70,450,510);
	panel2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel2);
	
	JLabel panelLabel1 = new JLabel("  Personal Information ");
	panelLabel1.setBounds(20,50,130,20);
	panelLabel1.setOpaque(true);
	panelLabel1.setBackground(Color.CYAN);
	add(panelLabel1);
	

	JLabel panelLabel2 = new JLabel("  Other Information ");
	panelLabel2.setBounds(20,250,130,20);
	panelLabel2.setOpaque(true);
	panelLabel2.setBackground(Color.CYAN);
	add(panelLabel2);
	
	JLabel panel3 = new JLabel();
	panel3.setBounds(20,270,760,310);
	panel3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	add(panel3);
	
}


public void setBackground(){

	
	JLabel background = new JLabel(new ImageIcon("res/discharge.png"));
	 background.setBounds(0,5,1250,700);
	 background.setBackground(new Color(65, 105, 225));
	// background.setBackground(Color.LIGHT_GRAY);
	 background.setOpaque(true);
	 add(background);
	 
	 
	 
	 
}


public void setFields(){
	
	PID = new JTextField();
	PID.setBounds(130,90,230,18);
	PID.addKeyListener(this);
	add(PID);
	
	caseNo = new JTextField();
	caseNo.setBounds(170,280,200,18);
	add(caseNo);
	
	FN = new JTextField();
	FN.setBounds(130,120,230,18);
	add(FN);
	
	MN = new JTextField();
	MN.setBounds(130,150,230,18);
	add(MN);
	
	LN = new JTextField();
	LN.setBounds(130,180,230,18);
	LN.addKeyListener(Kim);
	add(LN);
	
	admitDate = new JTextField("Jamuary 16, 2017");
	admitDate.setBounds(170,370,200,18);
	add(admitDate);
	
	dischargeDate = new JTextField("Jamuary 17, 2017");
	dischargeDate.setBounds(170,400,200,18);
	add(dischargeDate);
	
		
			dispo = new JComboBox(D);
			dispo.setBounds(170,460,200,18);
			dispo.setSelectedIndex(0);
			dispo.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent moriel){

			if(moriel.getSource()==dispo){
			JComboBox cb = (JComboBox)moriel.getSource();
			getDispo = (String)cb.getSelectedItem();

			

			}

				}
				}
				); 
			add(dispo);
		
			
			result = new JComboBox(R);
			result.setBounds(170,490,200,18);
			result.setSelectedIndex(0);
			result.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent moriel){

			if(moriel.getSource()==result){
			JComboBox cb = (JComboBox)moriel.getSource();
			getResult = (String)cb.getSelectedItem();

			

			}

				}
				}
				); 
			add(result);
			
			
			address = new JTextField();
			address.setBounds(130,210,230,18);
			add(address);
			
			
			
			ward = new JTextField();
			ward.setBounds(170,310,200,18);
			add(ward);
			
			
			wardType = new JComboBox(W);
			wardType.setBounds(170,340,200,18);
			wardType.setSelectedIndex(0);
			add(wardType);
			
			numDays = new JTextField();
			numDays.setBounds(170,430,200,18);
			add(numDays);
		
			physician = new JTextField();
			physician.setBounds(170,520,200,18);
			add(physician);
			
			bed_no = new JTextField();
			bed_no.setBounds(170,550,200,18);
			add(bed_no);
			
			
			admitDiag=new JTextArea();
			//Remarks.setBounds(100,505,250,240);
			admitDiag.setToolTipText("Please use 'Enter Key' to use line break!");
			admitDiag.setLineWrap(true);
			admitDiag.setWrapStyleWord(true);
			//add(Remarks);
			admitScroll=new JScrollPane(admitDiag);
			admitScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			//scrollRemarks.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			admitScroll.setBounds(550,280,220,50);
			add(admitScroll);
			
			
			finalDiag=new JTextArea();
			//Remarks.setBounds(100,505,250,240);
			finalDiag.setToolTipText("Please use 'Enter Key' to use line break!");
			finalDiag.setLineWrap(true);
			finalDiag.setWrapStyleWord(true);
			//add(Remarks);
			finalScroll=new JScrollPane(finalDiag);
			finalScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			//scrollRemarks.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			finalScroll.setBounds(550,340,220,50);
			add(finalScroll);
			
			
			otherDiag=new JTextArea();
			//Remarks.setBounds(100,505,250,240);
			otherDiag.setToolTipText("Please use 'Enter Key' to use line break!");
			otherDiag.setLineWrap(true);
			otherDiag.setWrapStyleWord(true);
			//add(Remarks);
			otherScroll=new JScrollPane(otherDiag);
			otherScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			//scrollRemarks.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			otherScroll.setBounds(550,400,220,50);
			add(otherScroll);
			

			
	
}


public void setButtons(){
	
	
	clear = new JButton("CLEAR FIELDS");
	clear.setBounds(180,610,120,25);
	clear.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			ClearFields();
			
		}
		
	});
	add(clear);
	
	
	edit = new JButton("UPDATE");
	edit.setBounds(320,610,80,25);
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
	print.setBounds(420,610,90,25);
	print.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent moriel) {
			// TODO Auto-generated method stub
			printMe();
		}
		
	});
	add(print);
	
	
	exit = new JButton("EXIT");
	exit.setBounds(530,610,90,25);
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
	line.setBounds(0,585,1250,1);
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
	
	if(caseNo.getText().toString().equals("")){
		caseNo.setBackground(inputFailed);
		caseNo.setForeground(changeInput);
	}else{
		caseNo.setBackground(inputPass);
		caseNo.setForeground(returnInput);
	}
	
	if(address.getText().toString().equals("")){
		address.setBackground(inputFailed);
		address.setForeground(changeInput);
	}else{
		address.setBackground(inputPass);
		address.setForeground(returnInput);
	}
	
	if(admitDate.getText().toString().equals("")){
		admitDate.setBackground(inputFailed);
		admitDate.setForeground(changeInput);
	}else{
		admitDate.setBackground(inputPass);
		admitDate.setForeground(returnInput);
	}
	
	if(dischargeDate.getText().toString().equals("")){
		dischargeDate.setBackground(inputFailed);
		dischargeDate.setForeground(changeInput);
	}else{
		dischargeDate.setBackground(inputPass);
		dischargeDate.setForeground(returnInput);
	}
	
	if(ward.getText().toString().equals("")){
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
	
	if(numDays.getText().toString().equals("")){
		numDays.setBackground(inputFailed);
		numDays.setForeground(changeInput);
	}else{
		numDays.setBackground(inputPass);
		numDays.setForeground(returnInput);
	}
	
	if(physician.getText().toString().equals("")){
		physician.setBackground(inputFailed);
		physician.setForeground(changeInput);
	}else{
		physician.setBackground(inputPass);
		physician.setForeground(returnInput);
	}
	
	if(dispo.getSelectedItem().toString().equals("")){
		dispo.setBackground(inputFailed);
		dispo.setForeground(changeInput);
	}else{
		dispo.setBackground(inputPass);
		dispo.setForeground(returnInput);
	}
	
	if(result.getSelectedItem().toString().equals("")){
		result.setBackground(inputFailed);
		result.setForeground(changeInput);
	}else{
		result.setBackground(inputPass);
		result.setForeground(returnInput);
	}
	
	if(admitDiag.getText().toString().equals("")){
		admitDiag.setBackground(inputFailed);
		admitDiag.setForeground(changeInput);
	}else{
		admitDiag.setBackground(inputPass);
		admitDiag.setForeground(returnInput);
	}
	
	if(finalDiag.getText().toString().equals("")){
		finalDiag.setBackground(inputFailed);
		finalDiag.setForeground(changeInput);
	}else{
		finalDiag.setBackground(inputPass);
		finalDiag.setForeground(returnInput);
	}
	
	if(otherDiag.getText().toString().equals("")){
		otherDiag.setBackground(inputFailed);
		otherDiag.setForeground(changeInput);
	}else{
		otherDiag.setBackground(inputPass);
		otherDiag.setForeground(returnInput);
	}
	
	if(bed_no.getText().toString().equals("")){
		bed_no.setBackground(inputFailed);
		bed_no.setForeground(changeInput);
	}else{
		bed_no.setBackground(inputPass);
		bed_no.setForeground(returnInput);
	}
	
	
	if(PID.getText().toString().equals("") || FN.getText().toString().equals("") ||  MN.getText().toString().equals("") || LN.getText().toString().equals("") 
			|| caseNo.getText().toString().equals("") || address.getText().equals("") ||
			admitDate.getText().toString().equals("") ||  dischargeDate.getText().toString().equals("")
			|| ward.getText().toString().equals("") || numDays.getText().equals("") || physician.getText().toString().equals("")
			|| dispo.getSelectedItem().equals("") || result.getSelectedItem().equals("") || wardType.getSelectedItem().equals("")
			|| admitDiag.getText().toString().equals("") || finalDiag.getText().toString().equals("")
			|| otherDiag.getText().toString().equals("") || bed_no.getText().toString().equals("")
			){
		
		JOptionPane.showMessageDialog(null,"Please Fill All Fields Required In Red Color!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
		 
	
		
	}
	
	else{
		
		
		InsertSQL();
		EditSQL();
		
		
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


java.sql.Statement stmt = conn.createStatement();

String query = "INSERT INTO discharge_history (patient_id, case_no, first_name, middle_name, last_name, address, ward, ward_type, admit_date, discharge_date, days, disposition, result, physician, admit_diagnosis, final_diagnosis, other_diagnosis,bed_no)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	 
	 PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
	 

	 ps.setString(1, PID.getText().toString());
	 
	 ps.setString(2, caseNo.getText().toString()); 

	 ps.setString(3, FN.getText().toString()); 

	ps.setString(4, MN.getText().toString()); 
	  
	ps.setString(5,  LN.getText().toString());
	
	 ps.setString(6, address.getText().toString()); 

	 ps.setString(7, ward.getText().toString());
	 
	 ps.setString(8, (String) wardType.getSelectedItem());

	ps.setString(9,  admitDate.getText().toString()); 

	ps.setString(10, dischargeDate.getText().toString()); 

	ps.setString(11, numDays.getText().toString());
	  
	ps.setString(12, getDispo);

	ps.setString(13, getResult); 

	 ps.setString(14, physician.getText().toString()); 

	  ps.setString(15, admitDiag.getText().toString());
	  
	  ps.setString(16, finalDiag.getText().toString());

	  ps.setString(17, otherDiag.getText().toString()); 
	  
	  ps.setString(18, bed_no.getText().toString()); 
	  


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
	 
	 caseNo.setText("");

	 FN.setText("");

	MN.setText(""); 
	  
	LN.setText("");
	
	 address.setText("");

	 ward.setText("");
	 
	 wardType.setSelectedItem("");
	 
	 bed_no.setText("");

	admitDate.setText(""); 

	dischargeDate.setText(""); 

	numDays.setText("");
	  
	dispo.setSelectedItem("");

	result.setSelectedItem("");

	 physician.setText("");

	  admitDiag.setText("");
	  
	  finalDiag.setText("");

	  otherDiag.setText("");
	  
	 
	
	
}



//METHOD FOR SEARCHING ACCOUNT BAKAYAROU!
public void SearchSQL(){
	
	ClearFields();
	
	String check = "SELECT * FROM discharge WHERE patient_id="+"\""+PID.getText().toString()+"\"";

	
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
					 
					 caseNo.setText(rs.getString("case_no"));

					 FN.setText(rs.getString("first_name"));

					MN.setText(rs.getString("middle_name")); 
					  
					LN.setText(rs.getString("last_name"));
					
					 address.setText(rs.getString("address"));

					 ward.setText(rs.getString("ward"));
					 
					 bed_no.setText(rs.getString("bed_no"));
					 
					 wardType.setSelectedItem(rs.getString("ward_type"));
					 
					admitDate.setText(rs.getString("admit_date")); 

					dischargeDate.setText(rs.getString("discharge_date")); 

					numDays.setText(rs.getString("days"));
					  
					dispo.setSelectedItem(rs.getString("disposition"));

					result.setSelectedItem(rs.getString("result"));

					 physician.setText(rs.getString("physician"));

					  admitDiag.setText(rs.getString("admit_diagnosis"));
					  
					  finalDiag.setText(rs.getString("final_diagnosis"));

					  otherDiag.setText(rs.getString("other_diagnosis"));

				 
		counter++;
			 }
			 

			 if(counter<1){
				 
				 //JOptionPane.showMessageDialog(null,"Account Not Found!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }

		conn.close();

		}catch (Exception moriel){

		 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 

			} 

} 


//METHOD PARA SA TABLE ROW CLICKING
public void tableClickSearch(){
	
	
	String check = "SELECT * FROM discharge WHERE patient_id="+"\""+table.getValueAt(table.getSelectedRow(), 0)+"\"";

	
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
					 
					 caseNo.setText(rs.getString("case_no"));

					 FN.setText(rs.getString("first_name"));

					MN.setText(rs.getString("middle_name")); 
					  
					LN.setText(rs.getString("last_name"));
					
					 address.setText(rs.getString("address"));

					 ward.setText(rs.getString("ward"));
					 
					 bed_no.setText(rs.getString("bed_no"));
					 
					 wardType.setSelectedItem(rs.getString("ward_type"));
					 
					admitDate.setText(rs.getString("admit_date")); 

					dischargeDate.setText(rs.getString("discharge_date")); 

					numDays.setText(rs.getString("days"));
					  
					dispo.setSelectedItem(rs.getString("disposition"));

					result.setSelectedItem(rs.getString("result"));

					 physician.setText(rs.getString("physician"));

					  admitDiag.setText(rs.getString("admit_diagnosis"));
					  
					  finalDiag.setText(rs.getString("final_diagnosis"));

					  otherDiag.setText(rs.getString("other_diagnosis"));

				 
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
			String query = "UPDATE discharge SET patient_id=?, case_no=?, first_name=?, middle_name=?, last_name=?, address=?, ward=?, ward_type=?, admit_date=?, discharge_date=?, days=?, disposition=?, result=?, physician=?, admit_diagnosis=?, final_diagnosis=?, other_diagnosis=?, bed_no=? WHERE patient_id = "+"\""+this.PID.getText().toString()+"\"";

			
			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			
		
			 ps.setString(1, PID.getText().toString());
			 
			 ps.setString(2, caseNo.getText().toString()); 

			 ps.setString(3, FN.getText().toString()); 

			ps.setString(4, MN.getText().toString()); 
			  
			ps.setString(5,  LN.getText().toString());
			
			 ps.setString(6, address.getText().toString()); 

			 ps.setString(7, ward.getText().toString()); 
			 
			 ps.setString(8, (String) wardType.getSelectedItem());

			ps.setString(9,  admitDate.getText().toString()); 

			ps.setString(10, dischargeDate.getText().toString()); 

			ps.setString(11, numDays.getText().toString());
			  
			ps.setString(12, (String) dispo.getSelectedItem());

			ps.setString(13, (String) result.getSelectedItem()); 

			 ps.setString(14, physician.getText().toString()); 

			  ps.setString(15, admitDiag.getText().toString());
			  
			  ps.setString(16, finalDiag.getText().toString());

			  ps.setString(17, otherDiag.getText().toString()); 
			  
			  ps.setString(18, bed_no.getText().toString()); 
			   

			
			ps.execute();

			 JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 

			conn.close();
			
			
			//this.user.enable(true);

			}catch (Exception moriel){

			 		 JOptionPane.showMessageDialog(null,"ERROR!!!\n"+"PLEASE CONTACT DATABASE ADMIN\n\n"+moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE); 

				} 
		
		
		
		
		//CODE PARA SA EDITING WARD FOR DISCHARGE NG PATIENT
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
			String sql = "UPDATE ward SET availability=? WHERE number = "+"\""+ward.getText().toString()+"\""+"AND type = "+"\""+wardType.getSelectedItem()+"\""+"AND bed_no = "+"\""+bed_no.getText().toString()+"\"";;

			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			
			
			ps.setString(1, "Available");

			
		    ps.execute();

			 //JOptionPane.showMessageDialog(null,"Record Saved!"," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.INFORMATION_MESSAGE); 

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
	   	  
	    sql = "SELECT * FROM discharge";
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
        	  
         sql = "SELECT * FROM discharge";
         ResultSet rs = stmt.executeQuery(sql);
         table = new JTable(model);
   		 JScrollPane scrollPane = new JScrollPane(table);
   		 table.setPreferredScrollableViewportSize(new Dimension(500, 70));
   		 table.setFillsViewportHeight(true);
   		 table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
   		 table.setBackground(Color.LIGHT_GRAY);
   		 scrollPane.setBounds(800,90,430,480);
   		 scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
   		 scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
   		 add(scrollPane);
   		 table.setModel(DbUtils.resultSetToTableModel(rs));
   		 table.addMouseListener(this);
        	
         
    
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
        	  
        	sql = "SELECT * FROM discharge WHERE patient_id LIKE "+"\""+PID.getText().toString()+"%\"";
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
        g.drawString("Patient Discharge", 230,125);
        
        
        g.setFont(new Font("Tahoma",Font.ITALIC,7));
        g.setColor(Color.BLACK);
        g.drawString(dateFormat.format(date), 410,125);
        
        
      
        g.setFont(new Font("Tahoma",Font.PLAIN,8));
        g.setColor(Color.BLACK);
        g.drawString("Patient ID: "+PID.getText().toString(), 100,140);

       
        g.drawString("Case No: "+ caseNo.getText().toString(), 100,150);
        
       
        g.drawString("Name: "+FN.getText().toString()+" "+MN.getText().toString()+" "+LN.getText().toString(), 100,160);
      
       
        g.drawString("Address: "+address.getText().toString(), 100,170);

        
        g.drawString("Ward/Room No: "+ward.getText().toString(), 100,180);
        
       
        
        g.drawString("Attending Physician : "+ physician.getText().toString(), 100,190);

       
       
        g.drawString("Admitting Diagnosis: "+admitDiag.getText().toString(), 100,200);

       
        g.drawString("Final Diagnosis: "+finalDiag.getText().toString(), 100,220);

        
        g.drawString("Other Diagnosis: "+otherDiag.getText().toString(), 100,240);

      
       
        g.drawString("Date Of Admission: "+admitDate.getText().toString(), 100,260);

      
       
        g.drawString("Date Of Discharge: "+dischargeDate.getText().toString(), 100,270);

      
       
        g.drawString("Number Of Days: "+numDays.getText().toString(), 100,280);


        g.drawString("Disposition: "+dispo.getSelectedItem(), 100,290);

       
        g.drawString("Result: "+result.getSelectedItem(), 100,300);

       
       
        
        
        g.setFont(new Font("Tahoma",Font.ITALIC,10));
        g.setColor(Color.BLACK);
        //draw line separator
        g.drawString("_______________________________________" +
        		"________________________________________________", 70, 315);
      
       
        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.drawString("Email Address: region1mc2003@yahoo.com", 100,330);
        
        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.drawString("Telephone Numbers: (075) 515-89-16 ; (075)515-89-18 ; (075)515-89-01", 100,340);

        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.drawString("Fax: (075) 523-41-03", 100,350);
      
        
        //LINE SEPARATOR FOR CUTTING NANDE KUSO
        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.setColor(Color.BLACK);
        //draw line separator
        g.drawString("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ " +
        		"_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ " +
        		"_ _ _ _ _ _ _ _ _ _ _ _", 70, 370);

        
        //2ND COPY
        
        g.drawImage(logo, 270, 390, 60, 60, this);

        g.setFont(new Font("Tahoma",Font.BOLD,20));
        g.setColor(Color.RED);
        g.drawString(" HOSPITAL MANAGEMENT SYSTEM", 140, 470);
       
        
        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.setColor(Color.BLACK);
        g.drawString("Arellano Street, Dagupan City 2400, Pangasinan, Philippines", 180,480);

        //draw line separator
        g.drawString("_______________________________________" +
        		"________________________________________________", 70, 490);
        
        g.setFont(new Font("Tahoma",Font.ITALIC,10));
        g.setColor(Color.BLACK);
        g.drawString("Patient Discharge", 230,500);
        
        
        g.setFont(new Font("Tahoma",Font.ITALIC,7));
        g.setColor(Color.BLACK);
        g.drawString(dateFormat.format(date), 410,500);
        
        
      
        g.setFont(new Font("Tahoma",Font.PLAIN,8));
        g.setColor(Color.BLACK);
        g.drawString("Patient ID: "+PID.getText().toString(), 100,515);

       
        g.drawString("Case No: "+ caseNo.getText().toString(), 100,525);
        
       
        g.drawString("Name: "+FN.getText().toString()+" "+MN.getText().toString()+" "+LN.getText().toString(), 100,535);
      
       
        g.drawString("Address: "+address.getText().toString(), 100,545);

        
        g.drawString("Ward/Room No: "+ward.getText().toString(), 100,555);
        
       
        
        g.drawString("Attending Physician : "+ physician.getText().toString(), 100,565);

       
       
        g.drawString("Admitting Diagnosis: "+admitDiag.getText().toString(), 100,575);

       
        g.drawString("Final Diagnosis: "+finalDiag.getText().toString(), 100,605);

        
        g.drawString("Other Diagnosis: "+otherDiag.getText().toString(), 100,635);

      
       
        g.drawString("Date Of Admission: "+admitDate.getText().toString(), 100,665);

      
       
        g.drawString("Date Of Discharge: "+dischargeDate.getText().toString(), 100,675);

      
       
        g.drawString("Number Of Days: "+numDays.getText().toString(), 100,685);


        g.drawString("Disposition: "+dispo.getSelectedItem(), 100,695);

       
        g.drawString("Result: "+result.getSelectedItem(), 100,705);

       
       
        
        
        g.setFont(new Font("Tahoma",Font.ITALIC,10));
        g.setColor(Color.BLACK);
        //draw line separator
        g.drawString("_______________________________________" +
        		"________________________________________________", 70, 710);
      
       
        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.drawString("Email Address: region1mc2003@yahoo.com", 100,725);
        
        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.drawString("Telephone Numbers: (075) 515-89-16 ; (075)515-89-18 ; (075)515-89-01", 100,735);

        g.setFont(new Font("Tahoma",Font.ITALIC,8));
        g.drawString("Fax: (075) 523-41-03", 100,745);
        
        

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
		        	  
		        	sql = "SELECT * FROM discharge WHERE last_name LIKE "+"\""+LN.getText().toString()+"%\"";
		        	ResultSet rs = stmt.executeQuery(sql);
		        	table.setModel(DbUtils.resultSetToTableModel(rs));
		         
		    
			 }catch(Exception moriel){
				 
				 JOptionPane.showMessageDialog(null,moriel.getMessage()," HOSPITAL MANAGEMENT SYSTEM",JOptionPane.ERROR_MESSAGE);
				 
			 }
			 
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
