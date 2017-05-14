import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DBSU extends JFrame{
	private JTextField url = new JTextField();
	private JTextField dbn = new JTextField();
	private JTextField user = new JTextField();
	private JTextField pass = new JTextField();
	private JTextField suser = new JTextField();
	private JTextField spass = new JTextField();
	
	private JLabel urll = new JLabel("Enter URL:");
	private JLabel dbl = new JLabel("Enter DB name:");
	private JLabel userl = new JLabel("Enter DB User name:");
	private JLabel passl = new JLabel("Enter DB Passward:");
	private JLabel snam = new JLabel("Enter User name:");
	private JLabel spa = new JLabel("Enter Passowerd:");
	
	private JPanel pcenter = new JPanel();
	private JPanel psouth = new JPanel();
	
	private JButton ok = new JButton("Enter");
	
	private settingsDB dbs = new settingsDB();
	private users us = new users();
	
	public DBSU(){
		pcenter.setLayout(new GridLayout(6,2));
		pcenter.add(urll);
		pcenter.add(url);
		pcenter.add(dbl);
		pcenter.add(dbn);
		pcenter.add(userl);
		pcenter.add(user);
		pcenter.add(passl);
		pcenter.add(pass);
		pcenter.add(snam);
		pcenter.add(suser);
		pcenter.add(spa);
		pcenter.add(spass);
		add(pcenter, BorderLayout.CENTER);
		add(ok, BorderLayout.SOUTH);
		
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dbs.seturl(url.getText(), dbn.getText(), user.getText(), pass.getText());
				dbs.setFruitTab();
				dbs.setUserTab();
				us.addUser(Integer.parseInt(suser.getText()), Integer.parseInt(spass.getText()),
						"m", "Admin");
				dispose();
			}
		});
		
	}
	
	
	
	
	
}
