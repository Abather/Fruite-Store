import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.awt.event.*;
import java.sql.ResultSet;

public class MainWindo extends JFrame {
	private JButton bsell = new JButton("Sell");
	private JButton cpass = new JButton("Chang Password");
	private JButton bsearch = new JButton("Search");

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
	private FruitDB fruit = new FruitDB();
	
	
	public MainWindo(int i){
		setUndecorated(true);
		userId = i;
		name.setText("Casher: " + user.getName(i));
		id.setText("\tID: " +i);
		pnorth.setLayout(new GridLayout(1,3));
		pnorth.add(name);
		pnorth.add(id);
		pnorth.add(logout);
		add(pnorth, BorderLayout.NORTH);
		
		storename.setHorizontalAlignment(SwingConstants.CENTER);
		
		pcenter.setLayout(new GridLayout(2,2));
		pcenter.add(bsell);
		pcenter.add(cpass);

		pcenter.add(bsearch);
		add(pcenter, BorderLayout.CENTER);
		
		psouth.setLayout(new GridLayout(1,2));
		psouth.add(date);
		psouth.add(ext);
		add(psouth, BorderLayout.SOUTH);
		
		ext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
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
		
		bsell.addActionListener(new sellWindoAction());
		cpass.addActionListener(new chPass());
		bsearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				searchStore mw = new searchStore();
				mw.setTitle("Search Stock");
				mw.pack();
				mw.setAlwaysOnTop(true);
				mw.setLocationRelativeTo(null);
				mw.setVisible(true);
			}
		});
		
		
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
	
	class chPass implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ChangPass mw = new ChangPass(userId);
			mw.setTitle("Fruit Store");
			mw.pack();
			mw.setAlwaysOnTop(true);
			mw.setLocationRelativeTo(null);
			mw.setVisible(true);
		}
	}
	
	private class searchStore extends JFrame{
		private JButton searchb = new JButton("Search");
		private JButton updateb = new JButton("Update Stock");
		private JButton backb = new JButton("Back");
		
		private JTextField searcht = new JTextField();
		private JTextField pricet = new JTextField();
		private JTextField qunt = new JTextField();
		
		private JLabel pricel = new JLabel("Price: ");
		private JLabel quntl = new JLabel("Quntity: ");
		private JLabel emp = new JLabel();
		
		private JPanel pnorth = new JPanel();
		private JPanel pcenter = new JPanel();
		private JPanel psouth = new JPanel();
		
		public searchStore(){
			pnorth.setLayout(new GridLayout(1,2));
			pnorth.add(searcht);
			add(pnorth, BorderLayout.NORTH);
			
			pcenter.setLayout(new GridLayout(2,2));
			pcenter.add(pricel);
			pcenter.add(pricet);
			pricet.setEditable(false);
			pcenter.add(quntl);
			pcenter.add(qunt);
			qunt.setEditable(false);
			add(pcenter, BorderLayout.CENTER);
			
			psouth.setLayout(new GridLayout(1,3));
			psouth.add(searchb);
			psouth.add(emp);
			psouth.add(backb);
			add(psouth, BorderLayout.SOUTH);
			
			backb.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					dispose();
				}
			});
			
			searchb.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String name = searcht.getText();
					if(fruit.isInStore(name)){
						ResultSet res = fruit.getFruit(name);
						try{
							res.next();
							pricet.setText(res.getDouble(3)+"");
							qunt.setText(res.getInt(2)+"");
						}catch(Exception s){
							System.out.println(s.getMessage());
						}
					}else{
						pricet.setText(null);
						qunt.setText(null);
						searcht.setText("");
						JDialog jdia = new JDialog();
						jdia.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(jdia,
							    "Fruit Not Found!.",
							    "Not Found!",
							    JOptionPane.WARNING_MESSAGE);
					}
				}
			});

			
		}
	}

}
