import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsersSettings extends JFrame{
	private JLabel tit = new JLabel("Users Settings");
	
	private JButton addn = new JButton("New User");
	private JButton upd = new JButton("Updat User");
	private JButton del = new JButton("Delete User");
	private JButton chn = new JButton("Change password");
	private JButton back = new JButton("Back");
	
	private JPanel pcenter = new JPanel();
	private JPanel pnorth = new JPanel();
	private JPanel psouth = new JPanel();
	
	private users user = new users();
	
	public UsersSettings(int i){
		pnorth.add(tit, SwingConstants.CENTER);
		add(pnorth, BorderLayout.NORTH);
		
		pcenter.setLayout(new GridLayout(2,2));
		pcenter.add(addn);
		pcenter.add(upd);
		pcenter.add(del);
		pcenter.add(chn);
		add(pcenter, BorderLayout.CENTER);
		
		psouth.setLayout(new GridLayout(2,1));
		psouth.add(back);
		add(psouth, BorderLayout.SOUTH);
		
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		
		addn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addNewUser mw = new addNewUser();
				mw.setTitle("Add New User");
				mw.pack();
				mw.setLocationRelativeTo(null);
				mw.setVisible(true);
			}
		});
		
		del.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				deletUser mw = new deletUser();
				mw.setTitle("Delete User");
				mw.pack();
				mw.setLocationRelativeTo(null);
				mw.setVisible(true);
			}
		});
		
		chn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ChangPass mw = new ChangPass(i);
				mw.setTitle("Change your Password");
				mw.pack();
				mw.setLocationRelativeTo(null);
				mw.setVisible(true);
			}
		});
		
		upd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				UserInfo mw = new UserInfo();
				mw.setTitle("Users Info");
				mw.pack();
				mw.setLocationRelativeTo(null);
				mw.setVisible(true);
			}
		});
		
		
	}
	
	
	private class addNewUser extends JFrame{
		private JLabel tit = new JLabel("Add New User", SwingConstants.CENTER);
		private JLabel uname = new JLabel("User Name: ");
		private JLabel uid = new JLabel("User ID: ");
		private JLabel upass = new JLabel("User Password: ");
		
		private JTextField unamef = new JTextField();
		private JTextField uidf = new JTextField();
		private JTextField upassf = new JTextField();
		
		private JCheckBox addm = new JCheckBox("This is Admin User", false);
		
		private JButton ok = new JButton("Ok");
		private JButton cancel = new JButton("Cancel");
		
		private JPanel psouth = new JPanel();
		private JPanel pcenter = new JPanel();
		
		private users user = new users();
		
		public addNewUser(){
			add(tit, BorderLayout.NORTH);
			
			pcenter.setLayout(new GridLayout(4,2));
			pcenter.add(uname);
			pcenter.add(unamef);
			pcenter.add(uid);
			pcenter.add(uidf);
			pcenter.add(upass);
			pcenter.add(upassf);
			pcenter.add(addm);
			add(pcenter, BorderLayout.CENTER);
			
			psouth.setLayout(new GridLayout(1,2));
			psouth.add(ok);
			psouth.add(cancel);
			add(psouth, BorderLayout.SOUTH);
			
			cancel.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					dispose();
				}
			});
			
			ok.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					int id = Integer.parseInt(uidf.getText());
					int pass = Integer.parseInt(upassf.getText());
					String name = unamef.getText();
					String admin;
					if(!user.isItUser(id)){
					dispose();
					
					
					if(addm.isSelected()){
						admin = "m";
					}else{
						admin = "s";
					}
					user.addUser(id, pass, admin, name);
					
					JOptionPane.showMessageDialog(new JFrame(),
						    "User "+ name + " is Created\n"
						    +"With ID: " +id+ " and Password: "
						    +pass,
						    "Useer Added",
						    JOptionPane.WARNING_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(new JFrame(),
						    "A User with ID: "+id+ " is alreadey Exit",
						    "Useer Already Exit",
						    JOptionPane.WARNING_MESSAGE);
				}
				}
			});
			
		}
		
		
	}
	
	private class deletUser extends JFrame{
		private JLabel idl = new JLabel("Enter User ID: ");
		private JTextField idf = new JTextField();
		
		private JButton delete = new JButton("Delete");
		private JButton cancel = new JButton("Cancel");
		
		private JPanel psouth = new JPanel();
		private JPanel pcenter = new JPanel();
		
		private users user = new users();
		
		public deletUser(){
			pcenter.setLayout(new GridLayout(1,2));
			pcenter.add(idl);
			pcenter.add(idf);
			add(pcenter, BorderLayout.CENTER);
			
			psouth.setLayout(new GridLayout(1,2));
			psouth.add(delete);
			psouth.add(cancel);
			add(psouth, BorderLayout.SOUTH);
			
			delete.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e){
					
					if(user.isItUser(Integer.parseInt(idf.getText()))){
						dispose();
					user.deleteUser(Integer.parseInt(idf.getText()));
					JOptionPane.showMessageDialog(new JFrame(),
						    "User "+ idf.getText() + " Was Deleted",
						    "Useer Deleted",
						    JOptionPane.WARNING_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(new JFrame(),
							    "There is no User With such ID: "+ idf.getText(),
							    "ID Error",
							    JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			
			cancel.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					dispose();
				}
			});
		}
		
	}
	
	private class ChangPass extends JFrame{
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
	
	
	private class UserInfo extends JFrame{
		private JLabel idl = new JLabel("User ID: ");
		private JLabel namel = new JLabel("User Name: ");
		private JLabel passl = new JLabel("Change User Password: ");
		
		private JTextField idf = new JTextField();
		private JTextField namef = new JTextField();
		private JTextField passf = new JTextField();
		
		private JButton search = new JButton("Search");
		private JButton updinfo = new JButton("Update Info");
		private JButton cancel  =new JButton("Cancel");
		
		private JCheckBox addm = new JCheckBox("This is Admin User");
		
		private JPanel psouth = new JPanel();
		private JPanel pcenter = new JPanel();
		private JPanel pnorth = new JPanel();
		
		private users user = new users();
		
		public UserInfo(){
			pnorth.setLayout(new GridLayout(1,3));
			pnorth.add(idl);
			pnorth.add(idf);
			pnorth.add(search);
			add(pnorth, BorderLayout.NORTH);
			
			pcenter.setLayout(new GridLayout(3, 2));
			pcenter.add(namel);
			pcenter.add(namef);
			pcenter.add(passl);
			pcenter.add(passf);
			pcenter.add(addm);
			add(pcenter, BorderLayout.CENTER);
			
			psouth.setLayout(new GridLayout(1,2));
			psouth.add(updinfo);
			psouth.add(cancel);
			add(psouth, BorderLayout.SOUTH);
			
			cancel.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					dispose();
				}
			});
			
			search.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					int id = Integer.parseInt(idf.getText());
					
					if(user.isItUser(id)){
						namef.setText(user.getName(id));
						if(user.isAdimn(id)){
							addm.setSelected(true);
						}else{
							addm.setSelected(false);
						}
					}else{
						idf.setText("");
						JOptionPane.showMessageDialog(new JFrame(),
							    "There is no User With such ID: "+ idf.getText(),
							    "ID Error",
							    JOptionPane.WARNING_MESSAGE);
					}
					
				}
			});
			
			updinfo.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					int id = Integer.parseInt(idf.getText());
					int pas = user.getPassword(id);
					user.deleteUser(id);
					
					String type = "s";
					if(addm.isSelected()){
						type = "m";
					}
					user.addUser(id, pas, type, namef.getText());
					
					if(passf.getText() != null){
						user.setPass(id, Integer.parseInt(passf.getText()));
					}
					JOptionPane.showMessageDialog(new JFrame(),
						    "User Information Updated.",
						    "Info Updated",
						    JOptionPane.WARNING_MESSAGE);
					passf.setText("");
				}
			});
		}
	}
}
