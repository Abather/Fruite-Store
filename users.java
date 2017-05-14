import java.sql.*;

public class users {
	 private Connection conn;
	 private Statement dstm;
	 private settingsDB  dbs = new settingsDB();
	public users(){
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
	
	public void addUser(int i, int p, String t, String name){
		try{
			
			
			dstm.executeUpdate("insert into users (id, password, type, name) values ('"+i+"', '"+p+"', '"+t+"', '"+name+"')");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public int getPassword(int i){
		int pass = 0;
		String user = i+"";
		
		try{
			
			ResultSet res  = dstm.executeQuery("select password from users where id = '"+user+"'");
			while(res.next()){
				pass = res.getInt(1);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return pass;
	}
	
	public String getType(int i){
		String typ = "s";
		
		try{
			
			ResultSet res  = dstm.executeQuery("select type from users where id = '"+i+"'");
			while(res.next()){
				typ = res.getString(1);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return typ;
	}
	
	public String getName(int i){
		String name = "s";
		
		try{
			
			ResultSet res  = dstm.executeQuery("select name from users where id = '"+i+"'");
			while(res.next()){
				name = res.getString(1);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return name;
	}
	
	public void setPass(int i, int p){
		try{
			PreparedStatement prStmt = conn.prepareStatement("update users set password = "+p+" where id = '"+i+"'");
			prStmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void updateUserName(int i, String name){
		try{
			PreparedStatement prStmt = conn.prepareStatement("update users set name = "+name+" where id = '"+i+"'");
			prStmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteUser(int i){
		try{
			PreparedStatement prStmt = conn.prepareStatement("delete from users where id = ?");
			prStmt.setInt(1, i);
			prStmt.execute();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public boolean isItUser(int i){
		int p=-1;
		
		try{
			
			ResultSet res  = dstm.executeQuery("select id from users where id = '"+i+"'");
			res.next();
				p = res.getInt(1);
		}catch(Exception e){
			System.out.println(e.getMessage());
	}
		if(p == i){
			return true;
		}else{
			return false;
		}

}
	public boolean isAdimn(int i){
		String ad = null;
	try{
				
				ResultSet res  = dstm.executeQuery("select type from users where id = '"+i+"'");
				res.next();
				ad = res.getString(1);
			}catch(Exception e){
				System.out.println(e.getMessage());
		}
			if(ad.equals("m")){
				return true;
			}else{
				return false;
			}
		}
}
