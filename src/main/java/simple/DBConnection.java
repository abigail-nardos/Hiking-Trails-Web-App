package simple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.tinylog.Logger;

public class DBConnection {
	/**
	* URL of the database
	*/
	private String url = "";
	/**
	* User name for the database.
	*/
	private String user = "";
	/**
	* Password for the database.
	*/
	private String password = "";
	
	/**
	* Creates a connection to the database given the url, user name, and password global variables.
	* 
	* @return Connection to the database.         
	* @throws ClassNotFoundException 
	* @throws SQLException 
	*/
	protected Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = null;
		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection(url, user, password);
		
		return con;
	}
	
	/**
	* Closes the given connection to the database.
	* 
	* @param con  Connection to the database.         
	* @throws SQLException 
	*/
	protected void closeConnection(Connection con) {
		try {
			con.close();
			
		} catch (SQLException e) {
			Logger.error("Error Closing Connection");
		}
	}
}
