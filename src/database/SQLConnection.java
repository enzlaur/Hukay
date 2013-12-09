package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JTextField;

public class SQLConnection 
{
	/** db access stuff **/
	public static String dbUrl;
	public static String dbName;
	public static String dbDriver;
	public static String username;
	public static String password;
	public static Connection conn;
	public static JTextField txtType;
	
	public void connect() {
	  dbUrl = "jdbc:mysql://localhost:3306/";
	  dbName = "advandb";
	  dbDriver = "com.mysql.jdbc.Driver";
	  username = "root";
	  password = "laurenz";

          try {
            Class.forName(dbDriver).newInstance();
            conn = DriverManager.getConnection(dbUrl+dbName,username,password);
          } catch(Exception e){
            e.printStackTrace();
          }
	}
	
	public Connection getConnection()
	{
	  return conn;
	}
	
	public void disconnect()
	{
	  try 
	  {
	    conn.close();
	  } 
	  catch (Exception ex) 
	  {
	    ex.printStackTrace();
	  }
	}

	public Statement createStatement() {
		// TODO Auto-generated method stub
		return null;
	}
}
