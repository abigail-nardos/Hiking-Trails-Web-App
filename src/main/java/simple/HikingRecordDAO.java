package simple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;

public class HikingRecordDAO {
	/**
	* SQL query to select all values from records table.
	*/
	public static final String SELECT_ALL_RECORDS = "select * from records order by hike_date";
	/**
	* SQL query to insert given values into records table.
	*/
	public static final String INSERT_RECORDS_SQL = "INSERT INTO records (trail_name, user_name, hike_date, start_time, end_time) VALUES (?, ?, ?, ?, ?);";
	/**
	* SQL query to delete given row from the records table.
	*/
	public static final String DELETE_RECORDS_SQL = "delete from records where trail_name = ? and user_name = ? and hike_date = ?;";
	/**
	* SQL query to update given row in the records table.
	*/
	public static final String UPDATE_RECORDS_SQL = "update records set trail_name = ?, user_name = ?, hike_date = ?, start_time = ?, end_time = ? where trail_name = ? and user_name = ? and hike_date = ?;";
	/**
	* SQL query to select row from records table given the key.
	*/
	public static final String SELECT_RECORD_SQL = "select * from records where trail_name=? and user_name = ? and hike_date = ?;";
	/**
	* SQL query to select row from records table given the key except given record.
	*/
	public static final String SELECT_ALL_RECORDS_EXCEPT = "select * from records where trail_name <> ? or user_name <> ? or hike_date <> ?;";
	/**
	* Error message to be sent and handled by the servlet.
	*/
	public String errorMessage = "";
	
	/**
	* Updates the database from the previous hiking record to the current hiking record.
	* 
	* @param  prevRecord  HikingRecord that was displayed before being updated in the UI.
	* @param  curRecord   HikingRecord that was updated to in the UI.
	* @param  con          Connection to the database.
	* @return Boolean value indicating if the given row was successfully updated.
	* @throws SQLException
	* @throws IllegalArgumentException
	 * @throws ParseException 
	*/
	public boolean updateRecord(HikingRecord prevRecord, HikingRecord curRecord, Connection con) throws SQLException, IllegalArgumentException, ParseException {
		boolean rowUpdated = false;
		boolean newKey = true;
		
		PreparedStatement ps = con.prepareStatement(UPDATE_RECORDS_SQL);
		ps.setString(1,  curRecord.getTrailName());
		ps.setString(2,  curRecord.getUserName());
		ps.setString(3,  curRecord.getDate());
		ps.setString(4,  curRecord.getStartTime());
		ps.setString(5,  curRecord.getEndTime());
		ps.setString(6,  prevRecord.getTrailName());
		ps.setString(7,  prevRecord.getUserName());
		ps.setString(8,  prevRecord.getDate());
		
		//check if the key has been updated
		if (curRecord.getTrailName().equals(prevRecord.getTrailName()) && curRecord.getUserName().equals(prevRecord.getUserName()) && curRecord.getDate().equals(prevRecord.getDate())) {
			newKey = false;
		}
		
		//validate input
		if (!validateTrailName(curRecord.getTrailName(), con) || !validateUserName(curRecord.getUserName(), con) || !validateTime(curRecord.getStartTime(), curRecord.getEndTime()) || !validateDate(curRecord.getDate()) || (newKey && !isUnique(curRecord,con))) {
			throw new IllegalArgumentException(errorMessage);
		}
		
		//execute the prepared statement
		rowUpdated = ps.executeUpdate() > 0;
		
		//close the db utils
		ps.close();
		
		return rowUpdated;
	}
	
	/**
	* Queries all the hiking records from the database and formats them in a list.
	* 
	* @param  con   Connection to the database.
	* @return A list of all the hiking records in the database.     
	* @throws SQLException 
	*/
	public List<HikingRecord> selectAllRecords(Connection con) throws SQLException {
		List<HikingRecord> records = new ArrayList<>();
		
		PreparedStatement ps = con.prepareStatement(SELECT_ALL_RECORDS);
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			String trailName = rs.getString("trail_name");
	    	String userName = rs.getString("user_name");
	    	String hikeDate = rs.getString("hike_date");
	    	String startTime = rs.getString("start_time");
	    	String endTime = rs.getString("end_time");
	    	
	    	records.add(new HikingRecord(trailName, userName, hikeDate, startTime, endTime));
		}
		
		//close db utils
		ps.close();
		rs.close();
		
		return records;
	}
	
	/**
	* Deletes the a row from the records table given the keys.
	* 
	* @param  trailName  The name of the trail in the records table to be deleted
	* @param  userName   The name of the user in the records table to be deleted.
	* @param  date        The date of the hike in the records table to be deleted.
	* @param  con         Connection to the database.
	* @return Boolean value indicating if the given row was successfully deleted. 
	* @throws SQLException
	* @throws IllegalArgumentException
	* @throws ParseException 
	*/
	public boolean deleteRecord(String trailName, String userName, String date, Connection con) throws SQLException, IllegalArgumentException, ParseException {
		boolean rowDeleted = false;
		
		PreparedStatement ps = con.prepareStatement(DELETE_RECORDS_SQL);
		ps.setString(1,  trailName);
		ps.setString(2,  userName);
		ps.setString(3,  date);
		
		//validate input
		if (!validateTrailName(trailName, con) || !validateUserName(userName, con) || !validateDate(date)) {
			throw new IllegalArgumentException(errorMessage);
		}
		
		//execute the prepared statement
		rowDeleted = ps.executeUpdate() > 0;
		
		//close db utils
		ps.close();
		
		return rowDeleted;
	}
	
	/**
	* Adds the given record into the database.
	* 
	* @param  record  The hiking record to be added to the records table in the database. 
	* @param  con     Connection to the database.
	* @throws SQLException
	* @throws IllegalArgumentException
	 * @throws ParseException 
	*/
	public void insertRecord(HikingRecord record, Connection con) throws SQLException, IllegalArgumentException, ParseException {
		
		PreparedStatement ps = con.prepareStatement(INSERT_RECORDS_SQL);
		ps.setString(1, record.getTrailName());
		ps.setString(2,  record.getUserName());
		ps.setString(3,  record.getDate());
		ps.setString(4, record.getStartTime());
		ps.setString(5, record.getEndTime());
	
		//validate input
		if (!validateTrailName(record.getTrailName(), con) || !validateUserName(record.getUserName(), con) || !validateTime(record.getStartTime(), record.getEndTime()) || !validateDate(record.getDate()) || !isUnique(record, con)) {
			throw new IllegalArgumentException(errorMessage);
		}
		
		//execute the prepared statement
		ps.execute();
		
		//close db utils
		ps.close();
	}
	
	/**
	* Gets a record from the database given the keys.
	* 
	* @param  trailName  The name of the trail in the records table to be selected.
	* @param  userName   The name of the user in the records table to be selected.
	* @param  date        The date of the hike in the records table to be selected.
	* @param  con         Connection to the database.
	* @return A hiking record from the database given the inputted keys.  
	* @throws SQLException      
	* @throws IllegalArgumentException
	 * @throws ParseException 
	*/
	public HikingRecord selectRecord(String trailName, String userName, String date, Connection con) throws SQLException, IllegalArgumentException, ParseException {
		HikingRecord record = null;

		PreparedStatement ps = con.prepareStatement(SELECT_RECORD_SQL);
		ps.setString(1, trailName);
		ps.setString(2, userName);
		ps.setString(3, date);
		
		//validate input
		if (!validateTrailName(trailName, con) || !validateUserName(userName, con) || !validateDate(date)) {
			throw new IllegalArgumentException();
		}
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			String start_time = rs.getString("start_time");
			String end_time = rs.getString("end_time");
			record = new HikingRecord(trailName, userName, date, start_time, end_time);
		} 
		
		//close db utils
		ps.close();
		rs.close();
		
		return record;
		
	}
	
	/**
	* Check if a given name is unique in the database.
	* 
	* @param  record  Hiking record with name we want to check if is unique.
	* @param  con         Connection to the database.
	* @return A boolean value indicating if the record is unique or not.
	* @throws SQLException      
	*/
	public boolean isUnique(HikingRecord record, Connection con) throws SQLException {
		boolean isUnique = true;
		
		//get all the records from the database
		List<HikingRecord> allRecords = selectAllRecords(con);
		
		//check if any of the keys are the same
		for (int i=0; i<allRecords.size(); i++) {
			
			if (allRecords.get(i).getTrailName().equals(record.getTrailName()) && allRecords.get(i).getUserName().equals(record.getUserName()) && allRecords.get(i).getDate().equals(record.getDate())) {
				isUnique = false;
			}
		}
		
		//set error message
		if (!isUnique) {
			errorMessage = "This hiking record already exists";
		}
		
		return isUnique;
	}
	
	/**
	* Gets user name and returns a boolean if it is valid or not.
	* Must be an existing name in the database.
	* 
	* @param  userName  The user name of the hiking record to be validated.
	* @return A boolean value indicating if the user name is valid or not.
	 * @throws SQLException 
	*/
	public boolean validateUserName(String userName, Connection con) throws SQLException {
		boolean isValid = false;
		
		UserDAO userDAO = new UserDAO();
		
		List<User> allUsers = userDAO.selectAllUsers(con);
		
		//check if userName is in the list of all users
		for (int i=0; i<allUsers.size(); i++) {
			
			if (allUsers.get(i).getName().equals(userName)) {
				isValid = true;
			}
		}
		
		//set error message
		if (!isValid) {
			errorMessage = "This user does not exist";
		}

		return isValid;
	}
	
	/**
	* Gets  trail and returns a boolean if it is valid or not.
	* Must be an existing trail name in the database.
	* 
	* @param  name  The trail name of the hiking record to be validated.
	* @return A boolean value indicating if the user name is valid or not.
	 * @throws SQLException 
	*/
	public boolean validateTrailName(String trailName, Connection con) throws SQLException {
		boolean isValid = false;

		TrailDAO trailDAO = new TrailDAO();
		
		List<Trail> allTrails = trailDAO.selectAllTrails(con);

		//check if trailName is in the list of all trails
		for (int i=0; i<allTrails.size(); i++) {
			
			if (allTrails.get(i).getName().equals(trailName)) {
				isValid = true;
			}
		}

		//set error message
		if (!isValid) {
			errorMessage = "This trail does not exist";
		}

		return isValid;
	}
	
	
	/**
	* Gets a date and returns a boolean if it is valid or not.
	* Must be in YYYY-MM-DD format or null.
	* 
	* @param  date  The date of the hiking record to be validated.
	* @return A boolean value indicating if the input is valid or not.
	* @throws ParseException 
	*/
	public boolean validateDate(String date) throws ParseException {
		boolean isValid = date.matches("^([0-9]{4})-([0-9]{2})-([0-9]{2})$");

		//set error message
		if (!isValid) {
			errorMessage = "This date is in the wrong format";
		}
		
		//check if date is in the future
		if (isValid) {
			LocalDate presentDate = LocalDate.now();
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate curDate = LocalDate.parse(date, dateFormat);
			
			if (curDate.isAfter(presentDate)) {
				errorMessage = "This date is in the future";
				isValid = false;
				
			//check if the date is before Victoria became a city - 1862
			} else if (curDate.isBefore(presentDate.minusYears(162))) {
				errorMessage = "Victoria didn't exist at this date";
				isValid = false;
			}
		}

		return isValid;
	}
	
	/**
	* Gets a time and returns a boolean if it is valid or not.
	* Must be in HH-MM format or empty.
	* End time cannot be before start time.
	* 
	* @param  time  The time of the hiking record to be validated.
	* @return A boolean value indicating if the input is valid or not.
	*/
	public boolean validateTime(String startTime, String endTime) {
		
		boolean isValid = (startTime.matches("^(0?[1-9]|1[0-2]):[0-5][0-9]$") || startTime.equals("")) && (endTime.matches("^(0?[1-9]|1[0-2]):[0-5][0-9]$") || endTime.equals(""));
		
		//set error message
		if (!isValid) {
			errorMessage = "The time is in the wrong format";
		}
		
		//TO DO: fix the below so it takes into account AM and PM
		/*
		//check if end time is before start time
		if (isValid && !startTime.equals("") && !endTime.equals("")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
			Date s_time = dateFormat.parse(startTime);
			Date e_time = dateFormat.parse(endTime);
			
			errorMessage = "End time is before start time";
			isValid = !(e_time.before(s_time));
		}*/
		
		return isValid;
	}
}

