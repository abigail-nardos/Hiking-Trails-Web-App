package simple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.tinylog.Logger;

public class DBConnection {
	/**
	* URL of the database
	*/
	private String url = "jdbc:postgresql://xpgdev1.nfis.org:5432/sandbox";
	/**
	* User name for the database.
	*/
	private String user = "nkievit";
	/**
	* Password for the database.
	*/
	private String password = "Gr!tcH$t";
	
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
