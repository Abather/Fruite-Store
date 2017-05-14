import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.sql.*;

public class shopLogin extends JFrame{
	private JLabel lusername = new JLabel("employee ID:", SwingConstants.CENTER);
	private JLabel lpassword = new JLabel("password:", SwingConstants.CENTER);
	private JLabel lwelcome = new JLabel("Wellocome to the Store, \npleas enter your ID and password");
	
	private JTextField tusername = new JTextField();
	private JPasswordField tpassword = new JPasswordField();
	
	private JButton login = new JButton("Login");
	private JButton exit = new JButton("Exit");
	private JButton about = new JButton("About");
	private JButton dbsett = new JButton("DB Settings");
	
	private JPanel psouth = new JPanel();
	private JPanel pcenter = new JPanel();
	private JPanel pcentern = new JPanel();
	private JPanel pcenters = new JPanel();
	
	private users uID = new users();
	
	public shopLogin(){

		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(lwelcome, BorderLayout.NORTH);
		
		pcentern.setLayout(new GridLayout(2,2));
		pcentern.add(lusername);
		pcentern.add(tusername);
		pcentern.add(lpassword);
		pcentern.add(tpassword);
		
		pcenters.add(login);
		
		pcenter.setLayout(new GridLayout(2,1));
		pcenter.add(pcentern);
		pcenter.add(pcenters);
		add(pcenter, BorderLayout.CENTER);
		
		psouth.setLayout(new GridLayout(1,3));
		psouth.add(about);
		psouth.add(dbsett);
		psouth.add(exit);
		add(psouth, BorderLayout.SOUTH);
		
		login.addActionListener(new loginAction());
		
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		about.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String abo = "This Application Developed by:\n"
						+ "Mohammed Sadiq AlJuraysh\nSaleh AbaHussain\nAli Almohsin"
						+ "\nWiht Help of: \nDr. Sajjad";
				JDialog jdia = new JDialog();
				jdia.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jdia,
						abo,
					    "About!",
					    JOptionPane.WARNING_MESSAGE);
			}
		});
		
		dbsett.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				DBSU mw = new DBSU();				
				mw.setTitle("Fruit Store");
				mw.pack();
				mw.setLocationRelativeTo(null);
				mw.setVisible(true);
			}
		});
	}
	
	
	public void setEID(int i, int p){
		String name = i+".txt";
		String pss = p+"";
		try{
			File idpass = new File(name);
			
			PrintWriter passr = new PrintWriter(idpass);
			passr.write(pss);
			
			passr.close();
		}catch(Exception e){
			
		}
	}
	
	public int getPass(int i){
		int pass = 0;
		String name = i+".txt";
		
		try{
			File idf = new File(name);
			Scanner  passr = new Scanner(idf);
			pass = passr.nextInt();
		}catch(Exception e){
			
		}
		
		return pass;
	}
	
	class loginAction implements ActionListener{
		private JFrame frame = new JFrame();
		public void actionPerformed(ActionEvent e){

				String uid = tusername.getText();
				int id = Integer.parseInt(uid);
				int pass = uID.getPassword(id);
				int epass = Integer.parseInt(tpassword.getText());
				String typ = uID.getType(id);
				
				
				if(pass == epass && typ.equals("s")){
					MainWindo mw = new MainWindo(id);				
					mw.setTitle("Fruit Store");
					mw.pack();
					mw.setLocationRelativeTo(null);
					mw.setVisible(true);
					dispose();
				}else if(pass == epass && typ.equals("m")){
					AdmiMainWindo mw = new AdmiMainWindo(id);				
					mw.setTitle("Fruit Store");
					mw.pack();
					mw.setLocationRelativeTo(null);
					mw.setVisible(true);
					dispose();
					
				}else{
					tpassword.setText("");
					tusername.setText("");
					JOptionPane.showMessageDialog(frame,
						    "Wrong ID or Password!.",
						    "Wrong",
						    JOptionPane.WARNING_MESSAGE);
				}
			
			
		}
	}
	
	public void CreatDataUsers(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn =  DriverManager.getConnection("jdbc:mysql://10.31.33.63/testdb", "student", "123456");
			Statement dstm =  conn.createStatement();
			dstm.executeUpdate("create tabel users (id integer, password integer, type varchar(10) primary key(id))");
			
		}catch(Exception e){
			
		}
	}

}
