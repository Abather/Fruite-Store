import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class SellWindo extends JFrame{
	private JPanel pnorth = new JPanel();
	private JPanel psouth = new JPanel();
	private JPanel psouthu = new JPanel();
	private JPanel psouthd = new JPanel();
	private JPanel pcenter = new JPanel();
	
	private JTextField qunt = new JTextField();
	private JTextField get = new JTextField();
	private JTextField tqunt = new JTextField();
	private JTextField total = new JTextField();
	
	private JComboBox CPaid = new JComboBox();
	private JTextField pqun = new JTextField("", SwingConstants.CENTER);
	private JLabel pttt = new JLabel("", SwingConstants.CENTER);
	
	private JLabel price = new JLabel("", SwingConstants.CENTER);
	private JLabel items = new JLabel("Number of items", SwingConstants.CENTER);
	private JLabel ltotal = new JLabel("Total", SwingConstants.CENTER);
	private JLabel cusno = new JLabel();
	private JTextArea baiediTems = new JTextArea(5, 10);
	private JScrollPane textScro = new JScrollPane(baiediTems);
	
	private JComboBox fruits = new JComboBox();
	private Checkbox delete = new Checkbox("Delet it");
	
	private JButton add = new JButton("Add");
	private JButton exit = new JButton("Exit");
	private JButton purch = new JButton("Purchase");
	private JButton updateFruit = new JButton("Update");
	
	private ImageIcon icon = new ImageIcon("warning.png");
	private FruitDB fruitdb = new FruitDB();
	private ArrayList<String> itemname = new ArrayList<>();
	private ArrayList<Integer> itemQunt = new ArrayList<>();
	private ArrayList<Double> itemPrice = new ArrayList<>();
	private ArrayList<String> itemString = new ArrayList<>();
	
	
	private int totalquntup = 0;
	private double pricetotal = 0.0;
	public SellWindo(){
		itemString.add("Fruit \t Quntity \t Price");
		setUndecorated(true);
		get.setText(""+0.0);
		
		pnorth.setLayout(new GridLayout(2,4));
		pnorth.add(fruits);
		pnorth.add(qunt);
		pnorth.add(price);
		pnorth.add(add);
		pnorth.add(CPaid);
		pnorth.add(pqun);
		pnorth.add(delete);
		pnorth.add(updateFruit);
		add(pnorth, BorderLayout.NORTH);
		
		baiediTems.setEditable(false);
		add(textScro, BorderLayout.CENTER);
		
		psouthu.setLayout(new GridLayout(1,6));
		psouthu.add(items);
		psouthu.add(tqunt);
		tqunt.setEditable(false);
		psouthu.add(ltotal);
		psouthu.add(total);
		total.setEditable(false);
		psouthu.add(cusno);
		
		psouthd.setLayout(new GridLayout(1,3));
		psouthd.add(get);
		psouthd.add(purch);
		psouthd.add(exit);
		
		psouth.setLayout(new GridLayout(2,1));
		psouth.add(psouthu);
		psouth.add(psouthd);
		
		add(psouth, BorderLayout.SOUTH);
		
		fruits.setEditable(true);
		fruits.setModel(new DefaultComboBoxModel(getArray().toArray()));
		fruits.addActionListener(new FruitAction());
		exit.addActionListener(new CloseAction());
		add.addActionListener(new fruitAddea());
		purch.addActionListener(new purchActionListener());
		updateFruit.addActionListener(new updateAction());
	}
	
	class CloseAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JDialog jdia = new JDialog();
			jdia.setAlwaysOnTop(true);
			
			Object[] options = {"No", "Yes"};

			if(JOptionPane.showOptionDialog(
					jdia, "Are you Sure you went to exit? ",
					"Exit Warning",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					icon, 
					options,
					options[1]) == 1){
				dispose();
			}

		}
	}
	
	public ArrayList getArray(){
		ArrayList<String> fruit = new ArrayList<>();
		ResultSet res = fruitdb.getStroeFruits();
		fruit.add("--Select--");
		
		try{
			while(res.next()){
				fruit.add(res.getString(1));
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return fruit;
	}
	
	private class FruitAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			 String selected = (String)fruits.getSelectedItem();
			 if(fruitdb.isInStore(selected)){
		     ResultSet  res = fruitdb.getFruit(selected);
		     
		     try{
		    	 res.next();
		    	 price.setText("Price: "+res.getDouble(3)+" SR");
		     }catch(Exception s){
		    	 System.out.println(s.getMessage());
		     }
		}else if(selected.equals("--Select--")){
			price.setText("");
		}else{
			JDialog jdia = new JDialog();
			jdia.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jdia,
				    "Fruit Not Found!.",
				    "Not Found!",
				    JOptionPane.WARNING_MESSAGE);
			fruits.setSelectedIndex(0);
			
		}
		}
	}
	
	private class fruitAddea implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			String selected = (String)fruits.getSelectedItem();
			ResultSet res = fruitdb.getFruit(selected);
			int pqunt = Integer.parseInt(qunt.getText());
			try{ 
				res.next();
					int oldQunt = res.getInt(2);
					
				if(pqunt <= oldQunt){
					itemname.add(res.getString(1));
					itemQunt.add(pqunt);
					itemPrice.add(res.getDouble(3));
					double pprice = pqunt * res.getDouble(3);
					totalquntup += pqunt;
					pricetotal += pprice;
					tqunt.setText(totalquntup+" items");
					total.setText(pricetotal + " SR");
					itemString.add(selected + "\t" + pqunt + "\t" + pprice);
					baiediTems.setText("");
					for(int i =0; i < itemString.size(); i++){
						String mn = baiediTems.getText() + "\n" + itemString.get(i);
						baiediTems.setText(mn);
						CPaid.setModel(new DefaultComboBoxModel(itemname.toArray()));
					}
				
				}else{
					JDialog jdia = new JDialog();
					jdia.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(jdia,
						    "Ther are only " + res.getInt(2) + " of " + res.getString(1)
						    + "\nin the Store",
						    "Quntity Not enough!",
						    JOptionPane.WARNING_MESSAGE);
					qunt.setText(""+res.getInt(2));
				}
			}catch(Exception s){
				System.err.print(s.getMessage());
			}
		
		}
	}

	private class purchActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			double pa = Double.parseDouble(get.getText());
			if(pricetotal <= pa){
				double returned  = pa - pricetotal;
				for(int i = 0; i < itemname.size(); i++){
					String name = itemname.get(i);
					int qqq = itemQunt.get(i);
					fruitdb.updateQunt(name, qqq);
				}
				JDialog jdia = new JDialog();
				jdia.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jdia,
					    "Total is " + pricetotal + " you pay " + pa
					    + "\nreturn " + returned,
					    "Thank you for Pay!",
					    JOptionPane.WARNING_MESSAGE);
				
				dispose();
			}else{
				 
				JDialog jdia = new JDialog();
				jdia.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jdia,
					    "Total is " + pricetotal + " you pay " + pa
					    + "\n Your Payment Not Enough",
					    "Payment Not Enough!",
					    JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	private class updateAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int index = CPaid.getSelectedIndex();
			int mqunt = itemQunt.get(index);
			double mprice = mqunt*itemPrice.get(index);
			
			if(delete.getState()){
				itemname.remove(index);
				itemQunt.remove(index);
				itemString.remove(index+1);
				baiediTems.setText(null);
				totalquntup -= mqunt;
				pricetotal -= mprice;
				
				tqunt.setText(totalquntup + " items");
				total.setText(pricetotal + " SR");
				for(int i =0; i < itemString.size(); i++){
					String mn = baiediTems.getText() + "\n" + itemString.get(i);
					baiediTems.setText(mn);
					CPaid.setModel(new DefaultComboBoxModel(itemname.toArray()));
				}
			}else{
				ResultSet res = fruitdb.getFruit(itemname.get(index));
				int oldq = itemQunt.get(index);
				try{
					res.next();
					if(Integer.parseInt(pqun.getText()) <= res.getInt(2)){
						
						itemQunt.set(index,Integer.parseInt(pqun.getText()));
						totalquntup = totalquntup - oldq + itemQunt.get(index);
						pricetotal = pricetotal - (oldq*itemPrice.get(index)) + (itemQunt.get(index) * itemPrice.get(index));
						tqunt.setText(totalquntup + " items");
						total.setText(pricetotal + " SR");
						Double pr = itemPrice.get(index)*itemQunt.get(index);
						String ss = itemname.get(index) + "\t" + itemQunt.get(index)
							+ "\t" + pr;
						itemString.set(index+1,ss);
						baiediTems.setText(null);
						for(int i =0; i < itemString.size(); i++){
							String mn = baiediTems.getText() + "\n" + itemString.get(i);
							baiediTems.setText(mn);
						}
					}else{
						JDialog jdia = new JDialog();
						jdia.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(jdia,
							    "Ther are only " + res.getInt(2) + " of " + res.getString(1)
							    + "\nin the Store",
							    "Quntity Not enough!",
							    JOptionPane.WARNING_MESSAGE);
						pqun.setText(""+res.getInt(2));
					}
					
				}catch(Exception s){
					System.out.println(s.getMessage());
			}
		}
			pqun.setText(null);
	}
	
	
}
}
