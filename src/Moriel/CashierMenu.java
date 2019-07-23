package Moriel;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.mysql.jdbc.PreparedStatement;



public class CashierMenu extends JFrame implements ActionListener, MenuListener{
	
	JLabel title, background;
	
	JButton Users,
			Admission, 
			Consultation,
			PatientInfo,
			Ward, 
			Billing,
			Discharge, 
			DoctorInfo,
			pharmacy;
	
	JMenuBar bar;
	JMenu about, exit;

	JMenuItem creators, system, ddcationToMoriel;

	JMenuItem Kevin, Eyeshield, Brain, Herbs, Jhe, Ryn, Shaira, Gina ;

	About abt = new About();
	
	Login l = new Login();
	
	public CashierMenu(){
		super("HOSPITAL MANAGEMENT SYSTEM");
		setLayout(null);
		
		//setTitle();
		
		setMenus();
		setMenuButts();
		setBackground();
		
		
	}
	
	
	public void setTitle(){
		
		title = new JLabel("                                 " +
		"  HOSPITAL MANAGEMENT SYSTEM");
		title.setBounds(0,0,660,25);
		title.setOpaque(true);
		title.setBackground(Color.RED);
		title.setFont(new Font("OLD ENGLISH MT",Font.BOLD,20));
		add(title);
	}
	
	
	
	public void setBackground(){

		background = new JLabel();
		 background = new JLabel(new ImageIcon("res/cashier.png"));
		 background.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		 background.setBounds(0,0,650,490);
		//background.setBounds(50,70,350,330);
		 background.setOpaque(true);
		 add(background);
		 
	}
	
	
	public void setMenuButts(){
		
		
		PatientInfo = new JButton("PATIENT INFO");
		PatientInfo.setBounds(480,80,140,30);
		PatientInfo.setEnabled(false);
		PatientInfo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent moriel){

				
				PatientInfo PI = new PatientInfo();
				PI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				PI.setSize(1250,730);
				PI.setVisible(true);
				PI.setResizable(false);
				
			}
			}
			); 
		add(PatientInfo);
		
		Consultation  = new JButton("CONSULTATION");
		Consultation.setBounds(480,120,140,30);
		Consultation.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent moriel){


				Consultation C = new Consultation();
				C.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				C.setSize(1000,700);
				C.setVisible(true);
				C.setResizable(false);

			}
			}
			); 
		add(Consultation);
		
		
		Admission = new JButton("ADMISSION");
		Admission.setBounds(480,160,140,30);
		Admission.setEnabled(false);
		Admission.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent moriel){

				Admission A = new Admission();
				A.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				A.setSize(1250,670);
				A.setVisible(true);
				A.setResizable(false);
				
			}
			}
			); 
		add(Admission);
		
		
		
		
		Ward = new JButton("WARDS/ROOMS");
		Ward.setBounds(480,200,140,30);
		Ward.setEnabled(false);
		Ward.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent moriel){

				
				Ward W = new Ward();
				W.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				W.setSize(1200,580);
				W.setVisible(true);
				W.setResizable(false);
				
			}
			}
			); 
		add(Ward);
		
		
		
		Billing = new JButton("BILLING");
		Billing.setBounds(480,240,140,30);
		Billing.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent moriel){

				
				Billing B = new Billing();
				B.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				B.setSize(1250,670);
				B.setVisible(true);
				B.setResizable(false);
				
				
			}
			}
			); 
		add(Billing);
		
		
		
		Discharge = new JButton("DISCHARGE");
		Discharge.setBounds(480,280,140,30);
		Discharge.setEnabled(false);
		Discharge.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent moriel){
				
				Discharge D = new Discharge();
				D.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				D.setSize(1250,700);
				D.setVisible(true);
				D.setResizable(false);
				
			}
			}
			); 
		add(Discharge);
		
		
		
		DoctorInfo = new JButton("DOCTOR INFO");
		DoctorInfo.setBounds(480,320,140,30);
		DoctorInfo.setEnabled(false);
		DoctorInfo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent moriel){

				Doctor Doc = new Doctor();
				Doc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				Doc.setSize(1200,600);
				Doc.setVisible(true);
				Doc.setResizable(false);
				
				
			}
			}
			); 
		add(DoctorInfo);
		
		
		Users = new JButton("USER ACCOUNTS");
		Users.setBounds(480,360,140,30);
		Users.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent moriel){
				
				Users U = new Users();
				U.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				U.setSize(800,470);
				U.setVisible(true);
				U.setResizable(false);
				
			}
			}
			); 
		add(Users);
		
		
		pharmacy = new JButton("PHARMACY");
		pharmacy.setBounds(480,400,140,30);
		pharmacy.setEnabled(false);
		pharmacy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent moriel){
				
				Pharmacy P = new Pharmacy();
				P.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				P.setSize(760,560);
				P.setVisible(true);
				P.setResizable(false);
				
			}
			}
			); 
		add(pharmacy);
		
		
		
	}
	
	
	public void setMenus(){
		bar = new JMenuBar();
		setJMenuBar(bar);
		
		about = new JMenu("About");
		exit = new JMenu("Exit");
		exit.addMenuListener(this);
		
		creators = new JMenu("Creators");
		
		about.add(creators);


	 	Kevin = new JMenuItem("Kevin Mejia");
		Kevin.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent j){

		abt.Shiten();

		}
		}
		);
		
		Eyeshield = new JMenuItem("Michael Cris Rosalin");
		Eyeshield.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent j){

			abt.Eyeshield();
		}
		}
		);
		
		Brain = new JMenuItem("Braindon Purganan");
		Brain.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent j){

			abt.Brain();

		}
		}
		);
		
		Herbs = new JMenuItem("Herbert Gamboa");
		Herbs.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent j){

			abt.Herbs();

		}
		}
		);
		
		
		Jhe = new JMenuItem("Jericoh Gutierrez");
		Jhe.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent j){

			abt.Jhe();

		}
		}
		);
		
		Ryn = new JMenuItem("Rynalyn Aquino");
		Ryn.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent j){

			abt.Ryn();

		}
		}
		);
		
		Shaira = new JMenuItem("Shaira Joy Singh");
		Shaira.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent j){

			
			abt.Shai();

		}
		}
		);
		
		
		Gina = new JMenuItem("Ginalyn Salon");
		Gina.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent j){

			abt.gina();

		}
		}
		);
		
		creators.add(Kevin);
		creators.add(Eyeshield);
		creators.add(Brain);
		creators.add(Herbs);
		creators.add(Jhe);
		creators.add(Ryn);
		creators.add(Shaira);
		creators.add(Gina);
		
		
		system = new JMenuItem("System");
		system.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent j){

			abt.System();
		}
		}
		);
		
		
		
		
		ddcationToMoriel = new JMenuItem("Eyeshield's Dedication To Moriel");
		ddcationToMoriel.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent j){
			
			abt.Moriel();
			

		}
		}
		);
		
		about.add(system);
		about.add(ddcationToMoriel);
	
		
	bar.add(about);
	bar.add(exit);
		

	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void menuCanceled(MenuEvent Moriel) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void menuDeselected(MenuEvent Moriel) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void menuSelected(MenuEvent Moriel) {
		// TODO Auto-generated method stub

		System.exit(0);
		
		
	}

}
