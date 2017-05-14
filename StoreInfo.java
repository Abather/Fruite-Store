import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.table.*;


public class StoreInfo extends JFrame{
	private JButton stock = new JButton("Stock");
	private JButton search = new JButton("Search/Update");
	private JButton Addfruit = new JButton("Add Fruit");
	private JButton back = new JButton("Back");
	
	private JPanel pcenter = new JPanel();
	private JPanel pnorth  = new JPanel();
	
	private JTextArea data = new JTextArea();
	private JScrollPane textScro = new JScrollPane(data);
	
	private FruitDB fruit = new FruitDB(); 
	
	public StoreInfo(){
		setSize(500,300);
		pnorth.setLayout(new GridLayout(1,4));
		pnorth.add(stock);
		pnorth.add(search);
		pnorth.add(Addfruit);
		pnorth.add(back);
		add(pnorth, BorderLayout.NORTH);
		
		
		add(textScro, BorderLayout.CENTER);
		
		
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		
		stock.addActionListener(new stockWinAction());
		search.addActionListener(new ActionListener(){
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
		
		Addfruit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addToStore mw = new addToStore();
				mw.setTitle("Add Fruit");
				mw.setAlwaysOnTop(true);
				mw.setUndecorated(true);
				mw.setLocationRelativeTo(null);
				mw.pack();
				mw.setVisible(true);
			}
		});
		
		
		
		
	}
	
	
	
	
	class stockWinAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ResultSet res = fruit.getStroeFruits();
			try{
				stock.setText("Refresh");
				data.setText("Fruit\tQuntity\tPrice SR");
				while(res.next()){
					data.append("\n---------------------------------");
					data.append("\n" + res.getString(1) + "  \t"
							+ res.getInt(2) + " \t" + res.getDouble(3));
				}   
			}catch(Exception p){
				System.out.println(p.getMessage());
			}
			 
		}
	}
	
		
	
	private class addToStore extends JFrame{
		private JButton add = new JButton("Add to Stock");
		private JButton backb = new JButton("Back");
		
		private JTextField namet = new JTextField();
		private JTextField pricet = new JTextField();
		private JTextField quntt = new JTextField();
		
		private JLabel pricel = new JLabel("Price: ");
		private JLabel quntl = new JLabel("Quntity: ");
		private JLabel namel = new JLabel("Fruit: ");
		private JLabel emp = new JLabel();
		
		private JPanel pcenter = new JPanel();
		private JPanel psouth = new JPanel();
		
		public addToStore(){
			pcenter.setLayout(new GridLayout(3, 2));
			pcenter.add(namel);
			pcenter.add(namet);
			pcenter.add(pricel);
			pcenter.add(pricet);
			pcenter.add(quntl);
			pcenter.add(quntt);
			add(pcenter, BorderLayout.CENTER);
			
			psouth.setLayout(new GridLayout(1, 3));
			psouth.add(add);
			psouth.add(emp);
			psouth.add(backb);
			add(psouth, BorderLayout.SOUTH);
			
			backb.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					dispose();
				}
			});
			
			add.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String name = namet.getText();
					if(!fruit.isInStore(name)){
						int qu = Integer.parseInt(quntt.getText());
						double pr = Double.parseDouble(pricet.getText());
						fruit.newFruit(name, qu, pr);
						quntt.setText("");
						namet.setText("");
						pricet.setText("");
						JDialog jdia = new JDialog();
						jdia.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(jdia,
							    "Fruit: " + name + " add to stock\nwith price: "
							    + pr + " SR and Quntitey: " + qu,
							    "Fruit Added!",
							    JOptionPane.WARNING_MESSAGE);
					}else{
						quntt.setText("");
						namet.setText("");
						pricet.setText("");
						JDialog jdia = new JDialog();
						jdia.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(jdia,
							    name + " is in the Stock",
							    "Furit in the Stock!",
							    JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}
	}
}
