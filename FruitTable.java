import javax.swing.*;

public class FruitTable extends JTable{
	private Object [][] rowData;
	private String [] columnNames;
	private JTable fruittable;
	
	public FruitTable(Object[][] o, String[] c){
		this.rowData = o;
		this.columnNames = c;
		this.fruittable = new JTable(o, c);
	}
	
	public JTable getTable(){
		return this.fruittable;
	}
	
	public void setTable(Object[][] o, String[] c){
		//this.fruittable
	}
}
