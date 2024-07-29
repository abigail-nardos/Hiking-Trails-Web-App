package simple;

import java.util.ArrayList;
import java.util.List;

import org.tinylog.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrailDAO {
	/**
	* SQL query to select all values from trails table.
	*/
	public static final String SELECT_ALL_TRAILS = "select * from trails order by name";
	
	/**
	* Queries all the trails from the database and formats them in a list.
	* 
	* @param  con   Connection to the database.
	* @return A list of all the trails in the database.     
	* @throws SQLException
	*/ 
	public List<Trail> selectAllTrails(Connection con) throws SQLException {
		List<Trail> trails = new ArrayList<>();
		
		PreparedStatement ps = con.prepareStatement(SELECT_ALL_TRAILS);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			String name = rs.getString("name");
	    	String location = rs.getString("location");
	    	String difficulty = rs.getString("difficulty");
	    	double distance = Math.round(rs.getFloat("distance")*100);
	    	int elevation = rs.getInt("elevation");
	    	trails.add(new Trail(name, location, difficulty, distance, elevation));
		}

		return trails;
	}
}
