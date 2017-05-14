import java.sql.*;

public class FruitDB {
	 private Connection conn;
	 private Statement dstm;
	 private settingsDB  dbs = new settingsDB();
	 
	 public FruitDB(){
		 try{
				Class.forName("com.mysql.jdbc.Driver");
				conn =  DriverManager.getConnection(dbs.getUrl(), dbs.getUsern(), dbs.getPass());
				dstm =  conn.createStatement();
				
			}catch(SQLException e){
				System.out.println(e.getMessage());
			}catch(Exception e){
				System.out.println("Driver not loaded ");
			}
	 }
	 
	 public void newFruit(String n, int q, double p){
		 try{
				dstm.executeUpdate("insert into Fruit (name, qunt, price) values ('"+n+"', '"+q+"', '"+p+"')");
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
	 }
	 
	 public ResultSet getFruit(String n){
		 ResultSet res = null;
		 try{
				
				res  = dstm.executeQuery("select name, qunt, price from Fruit where name = '"+n+"'");
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		 return res;
	 }
	 
	 public ResultSet getStroeFruits(){
		 ResultSet res = null;
		 try{
				
				res  = dstm.executeQuery("select * from Fruit");
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		 return res;
	 }
	 
	 public void updatFruit(String n, int q, double p){
		 try{
				PreparedStatement prStmt = conn.prepareStatement("update Fruit set qunt = "+q+", price = "+p+" where name = '"+n+"'");
				prStmt.executeUpdate();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
	 }
	 
	 public void updateQunt(String name, int q){
		 try{

				PreparedStatement prStmt = conn.prepareStatement("update Fruit set qunt = qunt - " +q+" where name = '"+name+"'");
				prStmt.executeUpdate();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
	 }
	 
	 public void deleteFruit(String n){
		 try{
				PreparedStatement prStmt = conn.prepareStatement("delete from Fruit where name = ?");
				prStmt.setString(1, n);
				prStmt.execute();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
	 }
	 
	 public boolean isInStore(String n){
		 ResultSet res = null;
		 boolean isIt = false;
		 try{
				
				res  = dstm.executeQuery("select name from Fruit where name = '"+n+"'");
				if(res.next()){
					 isIt = true;
				 }
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		 return isIt;
		 
	 }
}
