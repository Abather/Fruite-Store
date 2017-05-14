import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;



public class AdmiMainWindo extends JFrame{
	private JButton bsell = new JButton("Sell");
	private JButton Users = new JButton("Users Settings");
	private JButton bsearch = new JButton("Search");
	private JButton bupdate = new JButton("Store Info");
	private JButton ext = new JButton("Exit");
	
	private JLabel storename = new JLabel("Fruit Store");
	private JLabel date = new JLabel(""+ new Date());
	private JLabel name = new JLabel();
	private JLabel id = new JLabel();
	private JButton logout = new JButton("logout");
	
	private JPanel pcenter = new JPanel();
	private JPanel psouth = new JPanel();
	private JPanel pnorth = new JPanel();
	
	private users user = new users();
	private int userId;
	
	public AdmiMainWindo(int i){
		userId = i;
		setUndecorated(true);
		
		add(storename, BorderLayout.NORTH);
		storename.setHorizontalAlignment(SwingConstants.CENTER);
		
		name.setText("Manger: " + user.getName(i));
		id.setText("\tID: " +i);
		pnorth.setLayout(new GridLayout(1,3));
		pnorth.add(name);
		pnorth.add(id);
		pnorth.add(logout);
		add(pnorth, BorderLayout.NORTH);
		
		pcenter.setLayout(new GridLayout(2,2));
		pcenter.add(bsell);
		pcenter.add(bupdate);
		pcenter.add(Users);
		pcenter.add(bsearch);
		add(pcenter, BorderLayout.CENTER);
		
		psouth.setLayout(new GridLayout(1,2));
		psouth.add(date);
		psouth.add(ext);
		add(psouth, BorderLayout.SOUTH);
		
		logout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				shopLogin mw = new shopLogin();
				mw.setTitle("Fruit Store");
				mw.pack();
				mw.setLocationRelativeTo(null);
				mw.setVisible(true);
			}
		});
		
		ext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		bsearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				SearchFruit mw = new SearchFruit();
				mw.setTitle("Search/Update Store");
				mw.setAlwaysOnTop(true);
				mw.setUndecorated(true);
				mw.setLocationRelativeTo(null);
				mw.pack();
				mw.setVisible(true);
			}
		});
		
		bsell.addActionListener(new sellWindoAction());
		Users.addActionListener(new UsersWindoAction());
		bupdate.addActionListener(new StoreWindoAction());
		
	}
	
	class sellWindoAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			SellWindo mw = new SellWindo();
			mw.setTitle("Fruit Store");
			mw.setSize(650, 400);
			mw.setAlwaysOnTop(true);
			mw.setLocationRelativeTo(null);
			mw.setVisible(true);
		}
	}
	
	class UsersWindoAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			UsersSettings mw = new UsersSettings(userId);
			mw.setTitle("User Settings");
			mw.pack();
			mw.setLocationRelativeTo(null);
			mw.setVisible(true);
		}
	}
	
	class StoreWindoAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			StoreInfo mw = new StoreInfo();
			mw.setTitle("Store Information");
			mw.setSize(400, 400);
			mw.setLocationRelativeTo(null);
			mw.setVisible(true);
		}
	}
}
