import java.sql.*;
import java.io.*;
import java.util.*;

public class settingsDB {
	private String url, dbase, username, pass;
	private Connection conn;
	private Statement dstm;

	
	public settingsDB(){
		try{
			File file = new File("urldb.txt");
			Scanner ss = new Scanner(file);
			this.url = ss.nextLine();
			this.dbase = ss.nextLine();
			this.username = ss.nextLine();
			this.pass = ss.nextLine();
			ss.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
	public void setFruitTab(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(getUrl(), getUsern(), getPass());
			dstm = conn.createStatement();
			dstm.executeUpdate("create table Fruit (name varchar(100), qunt integer, price double,  primary key(name))");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void setUserTab(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn =  DriverManager.getConnection(getUrl(), getUsern(), getPass());
			dstm =  conn.createStatement();
			dstm.executeUpdate("create table users (id integer, password integer, type varchar(10), name varchar(100), primary key(id))");	
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}catch(Exception e){
			System.out.println("Driver not loaded ");
		}
	}
	
	public void seturl(String u, String db, String un, String p){
		try{
			File file = new File("urldb.txt");
			PrintWriter sc = new PrintWriter(file);
			sc.println(u);
			sc.println(db);
			sc.println(un);
			sc.println(p);
			sc.close();	
		}catch(Exception r){
			System.out.println(r.getMessage());
		}
	}
	
	public String getUrl(){
		return "jdbc:mysql://" + this.url+ "/" + this.dbase;
	}
	
	public String getPass(){
		return this.pass;
	}
	
	public String getUsern(){
		return this.username;
	}
	
	
}
