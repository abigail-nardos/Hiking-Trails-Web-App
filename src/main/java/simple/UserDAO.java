package simple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	/**
	* SQL query to select all values from users table.
	*/
	public static final String SELECT_ALL_USERS = "select * from users order by name";
	/**
	* SQL query to insert given values into users table.
	*/
	public static final String INSERT_USERS_SQL = "INSERT INTO users (name, birthday) VALUES (?, ?);";
	/**
	* SQL query to delete given row from the users table.
	*/
	public static final String DELETE_USERS_SQL = "delete from users where name = ?";
	/**
	* SQL query to update given row in the users table.
	*/
	public static final String UPDATE_USERS_SQL = "update users set name = ?, birthday = ? where name = ?;";
	/**
	* SQL query to select row from users table given the name.
	*/
	public static final String SELECT_USER_BY_NAME_SQL = "select name, birthday from users where name=?";
	/**
	* SQL query to select row from users table given the name except given user.
	*/
	public static final String SELECT_ALL_USERS_EXCEPT = "select * from users where name <> ?;";
	/**
	* Error message to be sent and handled by the servlet.
	*/
	public String errorMessage = "";
	
	/**
	* Updates the database from the previous user to the current user.
	* 
	* @param  prev_user  User that was displayed before being updated in the UI.
	* @param  cur_user   User that was updated to in the UI.
	* @param  con        Connection to the database.
	* @return Boolean value indicating if the given row was successfully updated.
	* @throws SQLException
	* @throws IllegalArgumentException
	*/
	public boolean updateUser(User prevUser, User curUser, Connection con) throws SQLException, IllegalArgumentException {
		boolean rowUpdated = false;
		boolean newKey = true;
		
		PreparedStatement ps = con.prepareStatement(UPDATE_USERS_SQL);
		ps.setString(1,  curUser.getName());
		ps.setString(2,  curUser.getBirthday());
		ps.setString(3, prevUser.getName());
		
		//check if the key has been updated
		if (curUser.getName().equals(prevUser.getName())) {
			newKey = false;
		}
		
		//validate input
		if (!validateName(curUser.getName()) || !validateBirthday(curUser.getBirthday()) || (newKey && !isUnique(curUser,con))) {
			throw new IllegalArgumentException(errorMessage);
			
		} 
		
		//execute prepared statement
		rowUpdated = ps.executeUpdate() > 0;
		
		//close db utils
		ps.close();
		
		return rowUpdated;
	}
	
	/**
	* Queries all the users from the database and formats them in a list.
	* 
	* @param  con   Connection to the database.
	* @return A list of all the users in the database.     
	* @throws SQLException 
	*/
	public List<User> selectAllUsers(Connection con) throws SQLException {
		List<User> users = new ArrayList<>();
		
		PreparedStatement ps = con.prepareStatement(SELECT_ALL_USERS);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			String name = rs.getString("name");
	    	String birthday = rs.getString("birthday");
	    	
	    	users.add(new User(name, birthday));
		}
		
		//close db utils
		ps.close();
		rs.close();
		
		return users;
	}
	
	/**
	* Deletes the a row from the users table given the user name.
	* 
	* @param  name  The name of the user in the user table to be deleted
	* @param  con   Connection to the database.
	* @return Boolean value indicating if the given row was successfully deleted. 
	* @throws SQLException
	*/
	public boolean deleteUser(String name, Connection con) throws SQLException {
		boolean rowDeleted = false;
		
		PreparedStatement ps = con.prepareStatement(DELETE_USERS_SQL);
		ps.setString(1, name);
		
		//execute prepared statement
		rowDeleted = ps.executeUpdate() > 0;
		
		//close db utils
		ps.close();
		
		return rowDeleted;
	}
	
	/**
	* Adds the given user into the database.
	* 
	* @param  user  The user to be added to the users table in the database. 
	* @param  con   Connection to the database.
	* @throws SQLException
	* @throws IllegalArgumentException
	*/
	public void insertUser(User user, Connection con) throws SQLException, IllegalArgumentException {
		PreparedStatement ps = con.prepareStatement(INSERT_USERS_SQL);
		ps.setString(1, user.getName());
		ps.setString(2,  user.getBirthday());
		
		//validate input
		if (!validateName(user.getName()) || !validateBirthday(user.getBirthday()) || !isUnique(user, con)) {
			throw new IllegalArgumentException(errorMessage);
		}
		
		//close db utils
		ps.execute();
		ps.close();
	}
	
	/**
	* Gets a user from the database given the name.
	* 
	* @param  name  The name of the user in the users table to be selected.
	* @param  con   Connection to the database.
	* @return A user from the database given the name.     
	* @throws SQLException   
	* @throws IllegalArgumentException
	*/
	public User selectUser(String name, Connection con) throws SQLException, IllegalArgumentException {
		User user = null;
		
		PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_NAME_SQL);
		ps.setString(1, name);
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			String birthday = rs.getString("birthday");
			user = new User(name, birthday);
		} 
		
		//close db utils
		ps.close();
		rs.close();
		
		return user;
		
	}
	
	/**
	* Iterates through the list of users and determines if the given name is unique.
	* 
	* @param  con  The connection to the database.
	* @param name  The name to check.
	* @return boolean If the name is unique.
	* @throws SQLException 
	*/
	public boolean isUnique(User user, Connection con) throws SQLException {
		boolean isUnique = true;
		
		//get all the users from the database
		List<User> allUsers = selectAllUsers(con);
		
		//check if any of the keys are the same
		for (int i=0; i<allUsers.size(); i++) {
			if (allUsers.get(i).getName().equals(user.getName())) {
				isUnique = false;
			}
		}
		
		//set error message
		if (!isUnique) {
			errorMessage = "This name already exists";
		}
		
		return isUnique;
	}
	
	/**
	* Gets a name and returns a boolean if it is legal or not.
	* Only alphabet characters, limited to length of 25 characters, not empty.
	* 
	* @param  name  The name of the user in the users table to be validated.
	* @return boolean  Indicating if the name is valid or not.
	*/
	public boolean validateName(String name) {
		boolean isValid = (!name.equals("")) && name.matches("^[a-zA-Z]*$") && (name.length()<25);
		
		//set error message
		if(!isValid) {
			errorMessage = "Your name is in the wrong format";
		}
		
		return isValid;
	}
	
	/**
	* Gets a birthday and returns a boolean if it is legal or not.
	* Birthday must be in YYYY-MM-DD format or be empty.
	* Birthday must not be more than six years ago.
	* 
	* @param  birthday  The birthday of the user in the users table to be validated.
	* @return boolean  Indicating if the birthday is valid or not.
	*/
	public boolean validateBirthday(String birthday) { 
		boolean isValid = birthday.matches("^([0-9]{4})-([0-9]{2})-([0-9]{2})$") ||  birthday.equals("");
		
		//set error message
		if (!isValid) {
			errorMessage = "Your birthday is in the wrong format";
		}

		if (isValid && !birthday.equals("")) {
			LocalDate presentDate = LocalDate.now();
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate curDate = LocalDate.parse(birthday, dateFormat);
			
			//check if the birthday is in the future
			if (curDate.isAfter(presentDate)) {
				errorMessage = "You're not born yet? ʱªʱªʱª(ᕑᗢूᓫ∗) ";
				isValid = false;
			//check if user is too young
			} else if (curDate.isAfter(presentDate.minusYears(6))) {
				errorMessage = "You're too young buddy";
				isValid = false;
			}
		}
		
		return isValid;
	}
}
