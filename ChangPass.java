import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class ChangPass extends JFrame{
	private JLabel oldp = new JLabel("Enter Old Password: ");
	private JLabel newp = new JLabel("Enter the New Password: ");
	private JLabel rnew = new JLabel("Renter the New Password: ");
	
	private JButton chan = new JButton("Change Password");
	private JButton cancl = new JButton("Cancel");
	
	private JPasswordField oldf  = new JPasswordField();
	private JPasswordField new1 = new JPasswordField();
	private JPasswordField new2 = new JPasswordField();
	
	private JPanel pcenter = new JPanel();
	
	private users user = new users();
	private int userId;
	
	public ChangPass(int i){
		userId = i;
		pcenter.setLayout(new GridLayout(4,2));
		pcenter.add(oldp);
		pcenter.add(oldf);
		pcenter.add(newp);
		pcenter.add(new1);
		pcenter.add(rnew);
		pcenter.add(new2);
		pcenter.add(chan);
		pcenter.add(cancl);
		add(pcenter);
		
		chan.addActionListener(new ChangePassAction());
		cancl.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		
	}
	
	class ChangePassAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFrame frame = new JFrame();
			frame.setAlwaysOnTop(true);
			int tpass = user.getPassword(userId);
			int opass = Integer.parseInt(oldf.getText());
			int npass = Integer.parseInt(new1.getText());
			int rnpass = Integer.parseInt(new2.getText());
			
			if(tpass == opass){
				if(npass == rnpass){
					dispose();
					user.setPass(userId, npass);
					JOptionPane.showMessageDialog(frame,
						    "The Password Changed",
						    "Don!",
						    JOptionPane.PLAIN_MESSAGE);
				}else{
					oldf.setText("");
					new1.setText("");
					new2.setText("");
					JOptionPane.showMessageDialog(frame,
						    "The New Password Does not match, please Reenter it",
						    "Error",
						    JOptionPane.PLAIN_MESSAGE);
				}
			}else{
				oldf.setText("");
				new1.setText("");
				new2.setText("");
				JOptionPane.showMessageDialog(frame,
					    "The  Password Wrong, please Re-Enter it",
					    "Error",
					    JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

}
