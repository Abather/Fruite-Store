import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchFruit extends JFrame{
		private JButton searchb = new JButton("Search");
		private JButton updateb = new JButton("Update Stock");
		private JButton backb = new JButton("Back");
		private JButton delt = new JButton("Delete");
		
		private JTextField searcht = new JTextField();
		private JTextField pricet = new JTextField();
		private JTextField qunt = new JTextField();
		
		private JLabel pricel = new JLabel("Price: ");
		private JLabel quntl = new JLabel("Quntity: ");
		
		private JPanel pnorth = new JPanel();
		private JPanel pcenter = new JPanel();
		private JPanel psouth = new JPanel();
		private FruitDB fruit = new FruitDB();
		
		public SearchFruit(){
			pnorth.setLayout(new GridLayout(1,2));
			pnorth.add(searcht);
			pnorth.add(searchb);
			add(pnorth, BorderLayout.NORTH);
			
			pcenter.setLayout(new GridLayout(2,2));
			pcenter.add(pricel);
			pcenter.add(pricet);
			pcenter.add(quntl);
			pcenter.add(qunt);
			add(pcenter, BorderLayout.CENTER);
			
			psouth.setLayout(new GridLayout(1,3));
			psouth.add(updateb);
			psouth.add(delt);
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
						searcht.setText("");
						pricet.setText("");
						qunt.setText("");
						JDialog jdia = new JDialog();
						jdia.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(jdia,
							    "Fruit Not Found!.",
							    "Not Found!",
							    JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			
			updateb.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String name = searcht.getText();
					if(fruit.isInStore(name)){
						double price = Double.parseDouble(pricet.getText());
						int quntt = Integer.parseInt(qunt.getText());
						fruit.updatFruit(name, quntt, price);
						JDialog jdia = new JDialog();
						jdia.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(jdia,
							    "Stock Updated " + name + 
							    "\nwith price: " + price + " SR and Quntitey: " + quntt,
							    "Updated Don!",
							    JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			
			delt.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String name = searcht.getText();
					if(fruit.isInStore(name)){
						searcht.setText("");
						pricet.setText("");
						qunt.setText("");
						fruit.deleteFruit(name);
						JDialog jdia = new JDialog();
						jdia.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(jdia,
							    "Stock Updated " + name + 
							    "\n\tWas Deleted",
							    "Delete Don!",
							    JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			
		}
	}

